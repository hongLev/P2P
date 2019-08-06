package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.Realauth;
import com.nongye.p2p.quey.RealauthQuery;

/**
 * 个人身份审核接口层
 * @author 89568
 *
 */
public interface RealauthMapper {
	/**
	 * 根据主键id获取实名认证
	 * @param id
	 * @return
	 */
	public Realauth getByPrimarykey(int id);
	/**
	 * 添加属性
	 */
	
	public void insert(Realauth realauth);
	/**
	 * 查询所有记录数
	 */
	public int count(RealauthQuery realauth);
	
	/**
	 *分页查询
	 */
	public List<Realauth> getAllRealauth(RealauthQuery qo);
	/**
	 * 修改
	 */
	public void updateByPrimaryKey(Realauth realauth);
	/**
	 * 根据用户id获取实名认证信息
	 */
	public Realauth selectApp(int id);
}
