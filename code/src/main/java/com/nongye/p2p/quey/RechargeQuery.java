package com.nongye.p2p.quey;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Recharge查询分页工具类
 * @author 89568
 *
 */
public class RechargeQuery extends ObjectQuery{
	/**银行号*/
	private int bankInfoid;
	/**交易流水号*/
	private String tradeCode;
	/**状态*/
	private int state=-1;
	/**开始时间*/
	private Date beginDate;
	/**结束时间*/
	private Date endDate;
	/**用户id*/
	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBankInfoid() {
		return bankInfoid;
	}

	public void setBankInfoid(int bankInfoid) {
		this.bankInfoid = bankInfoid;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

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
}
