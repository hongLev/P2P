package com.nongye.p2p.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.VerifyCodeVo;

/**
 * 用户上下文工具类
 * 	用来Session 存取
 * @author 89568
 *
 */
public class UserContext {
	/**定义key*/
	public static final String SESSION_LOGIN="logininfo";
	
	/**定义手机验证key*/
	public static final String SESSION_VERIFY_CODE="verifycoke";
	
	/**
	 * 存入手机验证信息
	 * @param vo
	 */
	public static void putVerifycoke(VerifyCodeVo vo){
		UserContext.session().setAttribute(UserContext.SESSION_VERIFY_CODE, vo);
	}
	/**
	 * 获取手机验证信息
	 * @return
	 */
	public static VerifyCodeVo getVerifycoke(){
		return (VerifyCodeVo)UserContext.session().getAttribute(SESSION_VERIFY_CODE);
	}
	
	
	public static HttpSession session(){
		
		//获取session对象
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	/**
	 * 存放房前用户登入信息
	 * @param logininfo
	 */
	public static void putContext(Logininfo logininfo){
		UserContext.session().setAttribute(UserContext.SESSION_LOGIN, logininfo);
	}
	/**
	 * 获取Session数据
	 */
	public static Logininfo getContextUser(){
		
		return (Logininfo)UserContext.session().getAttribute(SESSION_LOGIN);
	}
}
