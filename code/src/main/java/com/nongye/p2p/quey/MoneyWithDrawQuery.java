package com.nongye.p2p.quey;
/**
 * 提现审核查询分页类
 * @author 89568
 *
 */
public class MoneyWithDrawQuery extends RealauthQuery{

	/**
	 * 用户id
	 */
	private int applierId;

	public int getApplierId() {
		return applierId;
	}

	public void setApplierId(int applierId) {
		this.applierId = applierId;
	}
	
}
