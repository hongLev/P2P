package com.nongye.p2p.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseDomain;

/**
 * 数据字典详情信息
 * @author 89568
 *
 */
public class SystemDictionary extends BaseDomain{

	private String title;
	private String sn;
	
	public String getJsonStr(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", this.getId());
		map.put("sn", this.getSn());
		map.put("title", this.getTitle());
		//将map转成json格式
		return JSONObject.toJSON(map).toString();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
}
