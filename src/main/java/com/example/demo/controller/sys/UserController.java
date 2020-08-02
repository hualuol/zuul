package com.example.demo.controller.sys;

import com.example.demo.entity.sys.User;
import com.example.demo.job.otherServer.Captcha;
import com.example.demo.service.sys.UserService;
import com.example.demo.util.DES.AESUtils;
import com.example.demo.util.DES.DESUtils;
import com.example.demo.util.DES.MD5Utils;
import com.example.demo.util.activeMQ.Producer;
import com.example.demo.util.redis.RedisUtil;
import com.example.demo.util.serverUtil.NetworkUtil;
import com.example.demo.util.verificationCode.VerifyCode;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;
    @Autowired
    private Captcha captcha;
    @RequestMapping("/register")
    public String register(User user,HttpServletRequest request){
        try {
            String ip="";
            try {
                //获取用户登录的IP地址
                ip = NetworkUtil.getIpAddress(request);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                return "IP地址解析错误";
            }
            //判断用户是否已经获取过des密钥
            if(redisUtil.hasKey("client-key-"+ip)&&redisUtil.hasKey("client-iv-"+ip)){
                //从redis中获取des密钥
                String key= (String) redisUtil.get("client-key-"+ip);
                String iv= (String) redisUtil.get("client-iv-"+ip);
                //解密 密码
                String password = null;
                try {
                    password = AESUtils.decrypt(user.getPassword(),key,iv);
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                    return "解密出错";
                }
                user.setPassword(password);
            }else{
                return "登陆失败，请刷新页面，重新登录";
            }
            if(userService.getUserInfoByAccount(user.getAccount())!=null){
                return "账号已注册，请重新输入！";
            }
            user.setId(UUID.randomUUID().toString());
            user.setCreatedAt(new Date());
            user.setIsUsed(0);
            user.setPassword(MD5Utils.md5(user.getPassword(),user.getAccount()));
            userService.insert(user);
            redisUtil.set("user-"+user.getId(),user,60);

            User oldUser = (User)redisUtil.get("user-"+user.getId());
            if(user.getId().equals(oldUser.getId())){
                redisUtil.del("user-"+user.getId());
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return "操作成功";
    }
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        String ip="";
        try {
            //获取用户登录的IP地址
            ip = NetworkUtil.getIpAddress(request);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return "IP地址解析错误";
        }
        //判断用户是否已经获取过des密钥
        if(redisUtil.hasKey("client-key-"+ip)&&redisUtil.hasKey("client-iv-"+ip)){
            //从redis中获取des密钥
            String key= (String) redisUtil.get("client-key-"+ip);
            String iv= (String) redisUtil.get("client-iv-"+ip);
            //解密 密码
            String password = null;
            try {
                password = AESUtils.decrypt(user.getPassword(),key,iv);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                return "解密出错";
            }
            password = MD5Utils.md5(password,user.getAccount());
            user.setPassword(password);
        }else{
            return "登陆失败，请刷新页面，重新登录";
        }

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                    user.getAccount(),
                    user.getPassword()
            );
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(usernamePasswordToken);
                // subject.checkRole("admin");
                // subject.checkPermissions("query", "add");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "账号或密码错误！";
            } catch (AuthorizationException e) {
                e.printStackTrace();
                return "没有权限";
            }
        }
        /*写seesion，保存当前user对象*/
        //从shiro中获取当前用户
        subject.getSession().setAttribute("user-"+user.getAccount(), user);
        return "success";
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getImage")
    public String getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
       /* VerifyCode vc = new VerifyCode();
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("image/jpeg");
        BufferedImage bim = vc.getImage();
        ImageIO.write(bim, "JPEG", response.getOutputStream());
        String verifycode = vc.getText();
        request.getSession().setAttribute("verifyCode", verifycode);*/ //自己生成的
        String img=captcha.getImage(request,response);
        //response.setHeader("Cache-Control", "no-cache");
        //response.setContentType("image/jpeg");
        //request.getSession().setAttribute("verifyCode", img);
        return img;
    }
}
