package com.nongye.p2p.quey;
/**
 * 借款查询
 * @author 89568
 *
 */
public class BidRequestQueryObject extends ObjectQuery{
	//借款状态集合
	private int[] bidRequestStates;
	//借款状态	
	private int bidRequestState=-1;
	//排序类型
	private String orderyBy;
	//升序 or 降序
	private String orderByType;
	public int[] getBidRequestStates() {
		return bidRequestStates;
	}

	public void setBidRequestStates(int[] bidRequestStates) {
		this.bidRequestStates = bidRequestStates;
	}

	public String getOrderyBy() {
		return orderyBy;
	}

	public void setOrderyBy(String orderyBy) {
		this.orderyBy = orderyBy;
	}

	public String getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	public int getBidRequestState() {
		return bidRequestState;
	}

	public void setBidRequestState(int bidRequestState) {
		this.bidRequestState = bidRequestState;
	}

}
