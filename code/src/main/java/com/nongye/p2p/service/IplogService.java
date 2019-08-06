package com.nongye.p2p.service;

import com.nongye.p2p.quey.IplogObjectQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 日志接口业务层
 * @author 89568
 *
 */
public interface IplogService {
	
	/**
	 * 分页数据
	 * @param iplog
	 * @return
	 */
	public PageResult list(IplogObjectQuery iplog);
	
}
