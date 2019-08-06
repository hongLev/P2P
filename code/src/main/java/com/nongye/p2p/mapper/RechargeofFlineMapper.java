package com.nongye.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nongye.p2p.domain.RechargeofFline;
import com.nongye.p2p.quey.RechargeQuery;

/**
 * 充值数据接口类
 * @author 89568
 *
 */
public interface RechargeofFlineMapper {
	/**
	 * 添加
	 * @param rechargeofFline
	 */
	void insert(RechargeofFline rechargeofFline);
	/**
	 * 	查询记录数
	 */
	int queryCount(RechargeQuery query);
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	List<RechargeofFline> pageList(RechargeQuery query);
	/**
	 * 修改
	 * @param rechargeOffline
	 */
	void updateByPrimaryKey(RechargeofFline rechargeOffline);
	/**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
	RechargeofFline selectByPrimaryKey(Long id);
	
	/**
	 * 	查询记录数
	 */
	int queryCountUser(RechargeQuery query);
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	List<RechargeofFline> pageListUser(RechargeQuery query);
}
