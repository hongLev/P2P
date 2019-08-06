package com.nongye.p2p.service;

import com.nongye.p2p.domain.SystemDictionary;
import com.nongye.p2p.quey.SystemDictionaryQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 数据字典管理业务逻辑层
 * @author 89568
 *
 */
public interface SystemDictionaryService {
	/**添加字典*/
	public void addSystemDictionary(SystemDictionary systemDomain);
	
	/**分页查询查询字典*/
	public PageResult selectSystemDictionary(SystemDictionaryQuery system);
	
}
