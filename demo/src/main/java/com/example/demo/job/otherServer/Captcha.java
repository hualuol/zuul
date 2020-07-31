package com.example.demo.job.otherServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class Captcha {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Resource
    private RestTemplate restTemplate;

    public String getImage(HttpServletRequest request, HttpServletResponse response){
        List<String> services = new ArrayList<>();
        List<String> serviceNames = discoveryClient.getServices();
        for(String serviceName : serviceNames){
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            for(ServiceInstance serviceInstance : serviceInstances){
                services.add(String.format("%s:%s",serviceName,serviceInstance.getUri()));
            }
        }
        List<ServiceInstance> instances=discoveryClient.getInstances("springboot-captcha");
        ServiceInstance serviceInstance = instances.get(0);
        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/getImage?request="+request+"&response="+response;
        return restTemplate.getForObject(url,String.class);
    }
}
