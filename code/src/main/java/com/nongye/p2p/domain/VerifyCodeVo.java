package com.nongye.p2p.domain;
/**
 * 手机号验证实体类
 */
import java.util.Date;

public class VerifyCodeVo {
	/**手机号*/
	private String phoneNumber;
	/**验证码*/
	private String code;
	/**最后发送的时间*/
	private Date lastSendDate;
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getLastSendDate() {
		return lastSendDate;
	}
	public void setLastSendDate(Date lastSendDate) {
		this.lastSendDate = lastSendDate;
	}

}
