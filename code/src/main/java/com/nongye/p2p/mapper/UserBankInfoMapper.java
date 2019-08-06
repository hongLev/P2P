package com.nongye.p2p.mapper;

import com.nongye.p2p.domain.UserbankInfo;

/**
 * 用户银行卡管理数据接口
 * @author 89568
 *
 */
public interface UserBankInfoMapper {
	/**
	 * 添加
	 * @param userinfo
	 */
	public void insert(UserbankInfo userinfo);
	/**
	 * 根据用户id查找
	 */
	public UserbankInfo selectByUser(Long id);
}
