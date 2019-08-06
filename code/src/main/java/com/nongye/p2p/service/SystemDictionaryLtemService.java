package com.nongye.p2p.service;

import java.util.List;

import com.nongye.p2p.domain.SystemDictionaryItem;
import com.nongye.p2p.quey.SystemDictionaryItemQuery;
import com.nongye.p2p.util.PageResult;

public interface SystemDictionaryLtemService {
	
	/**
	 * 添加/或者修改
	 */
	public void AddSystem(SystemDictionaryItem add);
	
	/**
	 * 分页查询
	 */
	public PageResult selectSystem(SystemDictionaryItemQuery Sysquery);
	
	/**获取分页数据字典明细信息*/
	public List<SystemDictionaryItem> getBySnAll(String sn);
	
	/**
	 * 根据id 获取SystemDictionaryItem数据
	 * 
	 */
	public SystemDictionaryItem getByidSystemItem(Long id);
}

