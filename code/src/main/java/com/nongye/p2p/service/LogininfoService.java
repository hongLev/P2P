package com.nongye.p2p.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.mapper.LogininifMapper;

/**
 * 用户注册登入逻辑层
 * @author 89568
 *
 */


public interface LogininfoService {
	
	//用户注册功能
	public void insertLoginService(String userName,String Paw);
	
	//验证用户名是否存在
	public boolean checkUsernameService(String username);
	
	//登入用户
	public Logininfo loginUserService(String username,String password,int Type,String ip);
	
	//初始化后台用户
	public void insertAdminService();
	
}	
