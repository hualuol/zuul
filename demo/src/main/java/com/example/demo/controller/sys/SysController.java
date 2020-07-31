package com.example.demo.controller.sys;

import com.example.demo.util.DES.AESUtils;
import com.example.demo.util.redis.RedisUtil;
import com.example.demo.util.serverUtil.NetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

//手动加载自定义配置文件
@PropertySource(value = {
        "classpath:config/expire-time.properties",
}, encoding = "utf-8")
@Controller
public class SysController {

    private static final Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Value("${des}")
    private Long desExpireTime;
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        try {
            String ip = NetworkUtil.getIpAddress(request);
            String key=RandomDESKey( 16);
            String iv = RandomDESIV( 16);
            //将原始密钥存进redis
            redisUtil.set("client-key-"+ip,key,desExpireTime);
            redisUtil.set("client-iv-"+ip,iv,desExpireTime);
            //将加密后的密钥发送到前台
            key=AESUtils.encrypt(key,"zjky@10220000000","zjky923000000000");
            iv=AESUtils.encrypt(iv,"zjky@10220000000","zjky923000000000");
            System.out.println(key);
            mv.addObject("desKey",key);
            mv.addObject("desiv",iv);
            System.out.println(AESUtils.decrypt(key,"zjky@10220000000","zjky923000000000"));
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        } catch (Exception e) {
            logger.error("加密出错  "+e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        }
        mv.setViewName("login.html");
        return mv;
    }
    @RequestMapping("/logout")
    public ModelAndView logout(String id,HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        if(id!=null){
            redisUtil.del("user-"+id);
            try {
                String ip = NetworkUtil.getIpAddress(request);
                String key=RandomDESKey( 16);
                String iv = RandomDESIV( 16);
                //将原始密钥存进redis
                redisUtil.set("client-key-"+ip,key,desExpireTime);
                redisUtil.set("client-iv-"+ip,iv,desExpireTime);
                //将加密后的密钥发送到前台
                key=AESUtils.encrypt(key,"zjky@10220000000","zjky923000000000");
                iv=AESUtils.encrypt(iv,"zjky@10220000000","zjky923000000000");
                mv.addObject("desKey",key);
                mv.addObject("desiv",iv);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                mv.setViewName("/error");
                return mv;
            } catch (Exception e) {
                logger.error("加密出错  "+e.getMessage(),e);
                mv.setViewName("/error");
                return mv;
            }
            mv.setViewName("login.html");
        }else{
            mv.setViewName("/logoutWithoutAccount");
        }
        return mv;
    }
    @RequestMapping("loginByAccount")
    public String loginByAccount(){
        return "index";
    }
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        try {
            String ip = NetworkUtil.getIpAddress(request);
            String key=RandomDESKey( 16);
            String iv = RandomDESIV( 16);
            //将原始密钥存进redis
            redisUtil.set("client-key-"+ip,key,desExpireTime);
            redisUtil.set("client-iv-"+ip,iv,desExpireTime);
            //将加密后的密钥发送到前台
            key=AESUtils.encrypt(key,"zjky@10220000000","zjky923000000000");
            iv=AESUtils.encrypt(iv,"zjky@10220000000","zjky923000000000");
            mv.addObject("desKey",key);
            mv.addObject("desiv",iv);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        } catch (Exception e) {
            logger.error("加密出错  "+e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        }
        mv.setViewName("login.html");
        return mv;
    }
    @RequestMapping("reg")
    public ModelAndView reg(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        try {
            String ip = NetworkUtil.getIpAddress(request);
            String key=RandomDESKey( 16);
            String iv = RandomDESIV( 16);
            //将原始密钥存进redis
            redisUtil.set("client-key-"+ip,key,desExpireTime);
            redisUtil.set("client-iv-"+ip,iv,desExpireTime);
            //将加密后的密钥发送到前台
            key=AESUtils.encrypt(key,"zjky@10220000000","zjky923000000000");
            iv=AESUtils.encrypt(iv,"zjky@10220000000","zjky923000000000");
            mv.addObject("desKey",key);
            mv.addObject("desiv",iv);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        }catch (Exception e) {
            logger.error("加密出错  "+e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        }
        mv.setViewName("/sys/reg");
        return mv;
    }
    @RequestMapping("/")
    public ModelAndView firstPage(HttpServletRequest request) {
        ModelAndView mv=new ModelAndView();
        try {
            String ip = NetworkUtil.getIpAddress(request);
            String key=RandomDESKey( 16);
            String iv = RandomDESIV( 16);
            //将原始密钥存进redis
            redisUtil.set("client-key-"+ip,key,desExpireTime);
            redisUtil.set("client-iv-"+ip,iv,desExpireTime);
            //将加密后的密钥发送到前台
            key=AESUtils.encrypt(key,"zjky@10220000000","zjky923000000000");
            iv=AESUtils.encrypt(iv,"zjky@10220000000","zjky923000000000");
            System.out.println(key);
            mv.addObject("desKey",key);
            mv.addObject("desiv",iv);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        } catch (Exception e) {
            logger.error("加密出错  "+e.getMessage(),e);
            mv.setViewName("/error");
            return mv;
        }
        mv.setViewName("login.html");
        return mv;
    }

    public String RandomDESKey(int length) throws Exception {
        String randomArea="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r=new Random();
        String result = "";
        for(int i=0;i<length;i++){
            result+=randomArea.charAt(r.nextInt(randomArea.length()));
        }
        return result;
    }
    public String RandomDESIV(int length) throws Exception {
        String randomArea="0123456789abcdef";
        Random r=new Random();
        String result = "";
        for(int i=0;i<length;i++){
            result+=randomArea.charAt(r.nextInt(randomArea.length()));
        }
        return result;
                //AESUtils.encrypt(result,"zjky@10220000000","zjky923000000000");
    }
}
