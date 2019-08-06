package com.nongye.p2p.quey;

public class SystemDictionaryQuery extends ObjectQuery{
	/**查询条件*/
	private String keyword;
	/**状态*/
	private int state=-1;
	/**
	 * 父类
	 * @return
	 */
	
	public String getKeyword() {
		return keyword;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
}
