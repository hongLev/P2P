package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.Account;


public interface AccountMapper {
	
	//注册
	int insert(Account record);

	Account selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Account record);
	
	List<Account> selectAll();
}