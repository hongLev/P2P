package com.nongye.p2p.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;

/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	
	 @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 if(handler instanceof HandlerMethod){
			//对handler 强转
			 HandlerMethod hm=(HandlerMethod)handler;
			//获取用户正在类的方法是否有自定义注解
			 RequireLogin rl=hm.getMethodAnnotation(RequireLogin.class);
			 if(rl!=null && UserContext.getContextUser()==null){
				 // 说明该方法有自定义注解但是用户没有登录
				 response.sendRedirect("/login.html");
				 return false;
			 }
			
		} 
		return super.preHandle(request, response, handler);
	}
	
	
	
}
