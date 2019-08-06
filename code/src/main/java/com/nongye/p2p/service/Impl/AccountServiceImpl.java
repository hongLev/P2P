package com.nongye.p2p.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Account;
import com.nongye.p2p.mapper.AccountMapper;
import com.nongye.p2p.service.IAccountService;
@Service
public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		int version=this.accountMapper.updateByPrimaryKey(account);
		if(version==0){
			throw new RuntimeException("版本更新失效！account="+account.getId());
		}
	}

	@Override
	public Account get(Long id) {
		// TODO Auto-generated method stub
		return accountMapper.selectByPrimaryKey(id);
	}

}
