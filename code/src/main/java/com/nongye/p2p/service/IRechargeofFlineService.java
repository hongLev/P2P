package com.nongye.p2p.service;

import com.nongye.p2p.domain.RechargeofFline;
import com.nongye.p2p.quey.RechargeQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 充值业务逻辑接口层
 * @author 89568
 *
 */
public interface IRechargeofFlineService {
	/**
	 * 添加
	 * */
	public void insert(RechargeofFline rechergeofFline);
	/**
	 * 分页查询
	 */
	public PageResult list(RechargeQuery query);
	/**
	 * 修改
	 * @param rechergeofFline
	 */
	public void updateRechargeOff(RechargeofFline rechergeofFline);
	/**
	 * 根据主键id查找
	 */
	public RechargeofFline selectById(Long id);
	/**
	 * 审核
	 */
	public void rechargeOfflineAudit(int id, int state, String remark);
	/**
	 * 分页查询 用户充值明细
	 */
	public PageResult listByUserId(RechargeQuery query, int id);
}
