package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.quey.BidRequestQueryObject;

/**
 * 信用借数据接口层
 * @author 89568
 *
 */
public interface BidRequestMapper {
	/**
	 * 添加
	 */
	public void insert(BidRequest bidRequest);
	/**
	 * 查询总记录数
	 * @param query
	 * @return
	 */
	public int queryCount(BidRequestQueryObject query);
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public List<BidRequest> pageList(BidRequestQueryObject query);
	/**
	 * 根据主键ID查找
	 */
	BidRequest selectByPrimaryKey(Long id);
	
	int updateByPrimaryKey(BidRequest bidRequest);
}
