package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.base.BaseAuditDomain;
import com.nongye.p2p.quey.VedioauthQuery;

/**
 * 视频认证控制接口层
 * @author 89568
 *
 */

public interface VedioAuthMapper {
	
	/**
	 * 添加
	 * @param realauth
	 */
	public void insert(BaseAuditDomain vedio);
	/**
	 * 查询所有记录数
	 */
	public int count(VedioauthQuery realauth);
	
	/**
	 *分页查询
	 */
	public List<BaseAuditDomain> getAllRealauth(VedioauthQuery qo);
	/**
	 * 修改
	 */
	public void updateByPrimaryKey(BaseAuditDomain vedio);
	
	/**
	 * 检查用户名是否存在
	 */
	public int getCountByUsername(String UserName);
	
	/**
	 * 返回模糊查询数组
	 */
	public List<String> getByUserName(String userName);
}
