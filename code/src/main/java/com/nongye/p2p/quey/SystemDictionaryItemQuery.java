package com.nongye.p2p.quey;

public class SystemDictionaryItemQuery extends ObjectQuery{
	/**查询条件*/
	private String keyword;
	/**父类ID*/
	private Long parentId;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
