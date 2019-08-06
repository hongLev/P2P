package com.nongye.p2p.quey;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 审核查询条件
 * @author 89568
 *
 */
public class RealauthQuery extends ObjectQuery{
	//查询状态
	private int state=0;
	/**开始时间*/
	private Date beginDate;
	/**结束时间*/
	private Date endDate;
	/**
	 * 
	 * @return
	 */
	private int applierId;
	
	public int getApplierId() {
		return applierId;
	}
	public void setApplierId(int applierId) {
		this.applierId = applierId;
	}
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
