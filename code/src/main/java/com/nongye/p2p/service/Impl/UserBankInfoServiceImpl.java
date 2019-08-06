package com.nongye.p2p.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.UserbankInfo;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.UserBankInfoMapper;
import com.nongye.p2p.service.IUserBankInfoService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.BitstateUtil;
@Service
public class UserBankInfoServiceImpl implements IUserBankInfoService {
	@Autowired
	private UserBankInfoMapper userBankInfoMapper;
	@Autowired
	private IUserinfoService userinfoService;
	//添加
	@Override
	public void add(UserbankInfo userbank) {
		// TODO Auto-generated method stub
		if(userbank !=null ){
			Userinfo user = this.userinfoService.get(Long.parseLong(userbank.getLogininfo().getId()+""));
			//添加状态
			user.addState(BitstateUtil.OP_BIND_BANKINFO);
			this.userinfoService.updateUserInfo(user);
			this.userBankInfoMapper.insert(userbank);
		}
		
	}
	//根据userid查找
	@Override
	public UserbankInfo selectById(Long id) {
		// TODO Auto-generated method stub
		return this.userBankInfoMapper.selectByUser(id);
	}

}
