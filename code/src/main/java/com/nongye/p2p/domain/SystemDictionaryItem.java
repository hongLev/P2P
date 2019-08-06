package com.nongye.p2p.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseDomain;

public class SystemDictionaryItem extends BaseDomain{
	private Long parentId;//id
	private String title;//标题
	private int sequence;
	
	//回显设置
	public String getJsonStr(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", this.getId());
		map.put("title", this.getTitle());
		map.put("parentId", this.getParentId());
		map.put("sequence", this.getSequence());
		
		//返回
		return JSONObject.toJSON(map).toString();
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
}
