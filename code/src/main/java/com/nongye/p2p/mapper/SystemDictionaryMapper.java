package com.nongye.p2p.mapper;


import java.util.List;

import com.nongye.p2p.domain.SystemDictionary;
import com.nongye.p2p.quey.SystemDictionaryQuery;

/**
 * 数据字典数据接口层
 * @author 89568
 *
 */

public interface SystemDictionaryMapper {
	
	//添加
	int insert(SystemDictionary record);

	SystemDictionary selectByPrimaryKey(Long id);

	List<SystemDictionary> selectAll();
	
	void updateByPrimaryKey(SystemDictionary record);
	
	/**总记录数*/
	int queryForCount(SystemDictionaryQuery system);
	
	/**
	 * 分页的方法
	 * @param record
	 * @return
	 */
	List<SystemDictionary> query(SystemDictionaryQuery system);

}