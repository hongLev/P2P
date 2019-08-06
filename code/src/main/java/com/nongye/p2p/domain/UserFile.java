package com.nongye.p2p.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseAuditDomain;
/**
 * 风控材料实体类
 * @author 89568
 *
 */

public class UserFile extends BaseAuditDomain{
	private String image;//风控材料图片
	private SystemDictionaryItem fileType;//风控
	private int score;//风控材料分数
	
	public String getJonsStr(){
		Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", this.getId());
			map.put("state",this.getState());
			map.put("applier", this.getApplier());
			map.put("image", this.getImage());
			map.put("fileType", this.getFileType());
			map.put("score", this.getScore());
		
		return JSONObject.toJSON(map).toString();
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public SystemDictionaryItem getFileType() {
		return fileType;
	}
	public void setFileType(SystemDictionaryItem fileType) {
		this.fileType = fileType;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}