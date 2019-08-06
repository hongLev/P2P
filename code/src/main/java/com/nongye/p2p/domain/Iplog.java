package com.nongye.p2p.domain;

import java.util.Date;

import com.nongye.p2p.base.BaseDomain;

/**
 * 用户登入日志实体类
 * @author 89568
 *
 */
public class Iplog extends BaseDomain{
	/**登入成功*/
	public static final int LOGIN_SUCCESS=1;
	/**登入失败*/
	public static final int LOGIN_FAILED=0;
	
	/**登入IP*/
	private String ip;
	/**登入状态*/
	private int state;
	/**登入用户*/
	private String userName;
	/**登入人类型*/
	private int userType;
	/**登入时间*/
	private Date loginTime;
	
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
	
	public static int getLoginSuccess() {
		return LOGIN_SUCCESS;
	}
	public static int getLoginFailed() {
		return LOGIN_FAILED;
	}
	public String getStateDisplay(){
		return state==0?"登入失败":"登入成功";
	}
	
	public String getTypeDisplay(){
		return userType==0?"后台用户":"前台用户";
	}

	
}
