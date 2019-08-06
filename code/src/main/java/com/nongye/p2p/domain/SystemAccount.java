package com.nongye.p2p.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import com.nongye.p2p.base.BaseDomain;


/**
 * 平台账户
 * 
 * @author Administrator
 * 
 */
public class SystemAccount extends BaseDomain {
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	private int version;// 版本
	private BigDecimal usableAmount = BidConst.ZERO;// 平台账户剩余金额
	private BigDecimal freezedAmount = BidConst.ZERO;// 平台账户冻结金额
}
