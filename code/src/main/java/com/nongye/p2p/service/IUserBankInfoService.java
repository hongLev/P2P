package com.nongye.p2p.service;

import com.nongye.p2p.domain.UserbankInfo;

/**
 * 用户银行账户逻辑层
 * @author 89568
 *
 */
public interface IUserBankInfoService {
	//添加
	public void add(UserbankInfo userbank);
	//查找
	public UserbankInfo selectById(Long id);
}
