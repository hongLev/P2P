package com.nongye.p2p.service;

import com.nongye.p2p.domain.Account;

/**
 * 账户信息业务接口层
 * @author 89568
 *
 */
public interface IAccountService {

	
	/**
	 * 更新账户
	 */
	public void updateAccount(Account account);
	
	/**
	 * 根据Id查询账户信息
	 * 
	 */
	public Account get(Long id);
}
