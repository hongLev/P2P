package com.nongye.p2p.quey;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 登入日志查询条件
 * @author 89568
 *
 */
public class IplogObjectQuery extends ObjectQuery{

	/**开始时间*/
	private Date beginDate;
	/**结束时间*/
	private Date endDate;
	/**状态*/
	private int state=-1;
	/**用户姓名*/
	private String userName;
	/**登入类型*/
	private int userType=-1;
	public Date getBeginDate() {
		return beginDate;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
}
