package com.nongye.p2p.domain;

import com.nongye.p2p.base.BaseDomain;

/**
 * 绑定用户银行卡实体类
 * @author 89568
 *
 */
public class UserbankInfo extends BaseDomain{
	private String bankName;// 银行名称
	private String accountName;// 开户人姓名
	private String accountNumber;// 银行账号
	private String bankForkName;// 开户支行

	private Logininfo logininfo;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankForkName() {
		return bankForkName;
	}

	public void setBankForkName(String bankForkName) {
		this.bankForkName = bankForkName;
	}

	public Logininfo getLogininfo() {
		return logininfo;
	}

	public void setLogininfo(Logininfo logininfo) {
		this.logininfo = logininfo;
	}
}
