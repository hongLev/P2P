package com.nongye.p2p.service;

import com.nongye.p2p.domain.Realauth;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 实名认证用户业务接口层
 * @author 89568
 *
 */
public interface RealauthService {
	/**
	 * 根据主键Id查询
	 * @param id
	 * @return
	 */
	public Realauth getRealAuthById(int id);
	/**
	 * 添加认证信息
	 */
	public void AddRealauth(Realauth realauth);
	/**
	 * 分页
	 * @param realauth
	 * @return
	 */
	public PageResult list(RealauthQuery realauth);
	/**
	 * 审核
	 */
	public void	AuditRealauth(Realauth realauth);
	
	/**
	 * 
	 */
	public Realauth getByAppReal(int id);
}
