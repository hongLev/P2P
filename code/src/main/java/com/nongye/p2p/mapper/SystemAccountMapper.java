package com.nongye.p2p.mapper;

import com.nongye.p2p.domain.SystemAccount;

public interface SystemAccountMapper {

	int insert(SystemAccount record);

	/**
	 * 返回当前活动的那个系统账户
	 * 
	 * @return
	 */
	SystemAccount selectCurrent();

	int updateByPrimaryKey(SystemAccount record);
}