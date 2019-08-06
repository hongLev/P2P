package com.nongye.p2p.service;


import com.nongye.p2p.base.BaseAuditDomain;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.quey.VedioauthQuery;
import com.nongye.p2p.util.PageResult;


public interface VedioauthService {
	/**
	 * 添加认证信息
	 */
	public void AddRealauth(BaseAuditDomain vedio);
	/**
	 * 分页
	 * @param realauth
	 * @return
	 */
	public PageResult list(VedioauthQuery realauth);
	/**
	 * 审核
	 */
	public void	AuditRealauth(BaseAuditDomain vedio,String loginInfoDisplay);
	/**
	 * 查看用户名是否存在
	 * @param userName
	 * @return
	 */
	public boolean checkUsernameService(String userName);
}
