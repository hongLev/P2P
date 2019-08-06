package com.nongye.p2p.domain;

import java.math.BigDecimal;
import java.util.Date;


import com.nongye.p2p.base.BaseDomain;

/**
 * 账户流水实体类
 * @author 89568
 *
 */
public class AccountFlow extends BaseDomain{

	private Long accountId;// 流水是关于哪个账户的
	private BigDecimal amount;// 这次账户发生变化的金额
	private Date tradeTime;// 这次账户发生变化的时间
	private int accountType;// 资金变化类型
	private BigDecimal usableAmount;// 发生变化之后的可用余额;
	private BigDecimal freezedAmount;// 发生变化之后的冻结金额;
	private String note;//账户流水说明
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getUsableAmount() {
		return usableAmount;
	}
	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount = usableAmount;
	}
	public BigDecimal getFreezedAmount() {
		return freezedAmount;
	}
	public void setFreezedAmount(BigDecimal freezedAmount) {
		this.freezedAmount = freezedAmount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
