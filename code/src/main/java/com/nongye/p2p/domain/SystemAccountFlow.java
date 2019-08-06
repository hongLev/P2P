package com.nongye.p2p.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.nongye.p2p.base.BaseDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统账户账户流水
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class SystemAccountFlow extends BaseDomain {

	private Date createdDate;// 流水创建时间
	private int accountActionType;// 系统账户流水类型
	private BigDecimal amount;// 流水相关金额
	private String note;
	private BigDecimal balance;// 流水产生之后系统账户的可用余额;
	private BigDecimal freezedAmount;// 流水产生之后系统账户的冻结余额;
	private Long systemAccountId;// 对应的系统账户的id
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getAccountActionType() {
		return accountActionType;
	}
	public void setAccountActionType(int accountActionType) {
		this.accountActionType = accountActionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getFreezedAmount() {
		return freezedAmount;
	}
	public void setFreezedAmount(BigDecimal freezedAmount) {
		this.freezedAmount = freezedAmount;
	}
	public Long getSystemAccountId() {
		return systemAccountId;
	}
	public void setSystemAccountId(Long systemAccountId) {
		this.systemAccountId = systemAccountId;
	}
}
