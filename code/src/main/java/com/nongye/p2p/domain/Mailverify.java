package com.nongye.p2p.domain;

import java.util.Date;

import com.nongye.p2p.base.BaseDomain;

/**
 * 邮箱认证 信息
 * @author Administrator
 *
 */

public class Mailverify extends BaseDomain{
	/**用户ID*/
	private int userInfoId;
	/**发送时间*/
	private Date sendDate;
	/**邮箱激活码*/
	private String uuid;
	/**邮箱*/
	private String email;

	
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
