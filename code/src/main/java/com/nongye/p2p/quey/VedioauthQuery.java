package com.nongye.p2p.quey;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 审核查询条件
 * @author 89568
 *
 */
public class VedioauthQuery extends ObjectQuery{
	//查询状态
	private int state=-1;
	/**开始时间*/
	private Date beginDate;
	/**结束时间*/
	private Date endDate;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	
}
