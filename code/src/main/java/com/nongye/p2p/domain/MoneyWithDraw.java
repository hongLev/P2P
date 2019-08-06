package com.nongye.p2p.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseAuditDomain;

/**
 * 用户体现实体类
 * @author 89568
 *
 */
public class MoneyWithDraw extends BaseAuditDomain{
	private BigDecimal amount;// 提现金额
	private BigDecimal charge;// 提现手续费
	private String bankName;// 银行名称
	private String accountName;// 开户人姓名
	private String accountNumber;// 银行账号
	private String bankForkName;// 开户支行
	
	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", this.getId());
		json.put("username", this.applier.getUserName());
		json.put("realName", this.getAccountName());
		json.put("applyTime", this.getApplyTime());
		json.put("bankName", this.getBankName());
		json.put("accountNumber", this.getAccountNumber());
		json.put("bankforkname", this.getBankForkName());
		json.put("moneyAmount", this.getAmount());
		return JSONObject.toJSONString(json);
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
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
	
	
}
