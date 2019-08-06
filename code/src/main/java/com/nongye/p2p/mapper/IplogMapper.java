package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.Iplog;
import com.nongye.p2p.quey.IplogObjectQuery;

/**
 * 登入日志接口层
 * @author 89568
 *
 */
public interface IplogMapper {
	
	/**新增登入日志*/
	public void insert(Iplog iplog);
	
	/**查询本人的所有登入日志*/
	public List<Iplog> getAll(IplogObjectQuery iplog);
	
	/**查询总记录数*/
	public int getCount(IplogObjectQuery iplog);
}
