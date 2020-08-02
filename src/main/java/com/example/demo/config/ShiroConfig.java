package com.example.demo.config;

import com.example.demo.entity.sys.CategoryAuthority;
import com.example.demo.service.sys.UserService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Autowired
	private UserService userService;
	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		defaultAAP.setProxyTargetClass(true);
		return defaultAAP;
	}

	//将自己的验证方式加入容器
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm customRealm = new MyShiroRealm();
		return customRealm;
	}

	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// 设置session过期时间3600s
		sessionManager.setGlobalSessionTimeout(6000L);
		return sessionManager;
	}
	//权限管理，配置主要是Realm的管理认证
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	//Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> map = new HashMap<>();
		//将权限配置放进数据存储，方便管理
		List<CategoryAuthority> list=userService.getCategoryAuthority(0);
		for(CategoryAuthority ca:list){
			map.put(ca.getCategory(),ca.getAuthority());
		}
		//登出
		//map.put("/logout", "logout");
		//对所有用户认证
		//map.put("/user/login","anon");
		//map.put("/user/**", "authc");
		//map.put("/login","authc");

		//登录
		shiroFilterFactoryBean.setLoginUrl("/login");
		//首页
		shiroFilterFactoryBean.setSuccessUrl("/login");
		//错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}


	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}