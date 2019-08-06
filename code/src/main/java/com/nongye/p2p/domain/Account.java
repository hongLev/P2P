package com.nongye.p2p.domain;

import java.math.BigDecimal;

import com.nongye.p2p.base.BaseDomain;
/**
 * 账户信息表
 * @author 89568
 *
 */
public class Account extends BaseDomain{

	private int version;
	private String tradePassword;
	private BigDecimal usableAmount = BidConst.ZERO;//可用资金
	private BigDecimal freezedAmount = BidConst.ZERO;//冻结资金
	private BigDecimal unReceiveInterest = BidConst.ZERO;
	private BigDecimal unReceivePrincipal = BidConst.ZERO;
	private BigDecimal unReturnAmount = BidConst.ZERO;//待还
	private BigDecimal remainBorrowLimit = BidConst.INIT_BORROW_LIMIT;
	private BigDecimal borrowLimit = BidConst.INIT_BORROW_LIMIT;
	private String verifyCode;// 做数据校验的
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getTradePassword() {
		return tradePassword;
	}
	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
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
	public BigDecimal getUnReceiveInterest() {
		return unReceiveInterest;
	}
	public void setUnReceiveInterest(BigDecimal unReceiveInterest) {
		this.unReceiveInterest = unReceiveInterest;
	}
	public BigDecimal getUnReceivePrincipal() {
		return unReceivePrincipal;
	}
	public void setUnReceivePrincipal(BigDecimal unReceivePrincipal) {
		this.unReceivePrincipal = unReceivePrincipal;
	}
	public BigDecimal getUnReturnAmount() {
		return unReturnAmount;
	}
	public void setUnReturnAmount(BigDecimal unReturnAmount) {
		this.unReturnAmount = unReturnAmount;
	}
	public BigDecimal getRemainBorrowLimit() {
		return remainBorrowLimit;
	}
	public void setRemainBorrowLimit(BigDecimal remainBorrowLimit) {
		this.remainBorrowLimit = remainBorrowLimit;
	}
	public BigDecimal getBorrowLimit() {
		return borrowLimit;
	}
	public void setBorrowLimit(BigDecimal borrowLimit) {
		this.borrowLimit = borrowLimit;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public BigDecimal getTotalAmount(){
		return usableAmount.add(freezedAmount).add(unReceivePrincipal);
	}
	
}
