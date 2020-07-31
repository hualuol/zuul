package com.example.demo.util.serverUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 常用获取客户端信息的工具类
 * 
 * @author yjm
 * @version $Id: NetworkUtil.java, v 0.1 2017年3月21日 下午3:43:06 yjm Exp $
 */
public class NetworkUtil {

    public static final String  REQ_TOKEY_KEY = "Authorization";
    /** 
    * Logger for this class 
    */
    private static final Logger LOGGER  = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * 方法名: getIpAddress
     * 方法描述: 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  

        String ip = request.getHeader("X-Forwarded-For");
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(
                        "getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(
                        "getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(
                        "getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (LOGGER.isInfoEnabled()) {
                    LOGGER
                        .info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
                              + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (LOGGER.isInfoEnabled()) {
                    LOGGER
                        .info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}
