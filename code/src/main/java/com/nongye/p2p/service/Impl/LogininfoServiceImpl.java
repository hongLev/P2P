package com.nongye.p2p.service.Impl;

import java.util.Date;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nongye.p2p.domain.Account;
import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.Iplog;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.AccountMapper;
import com.nongye.p2p.mapper.IplogMapper;
import com.nongye.p2p.mapper.LogininifMapper;
import com.nongye.p2p.mapper.UserinfoMapper;
import com.nongye.p2p.service.LogininfoService;
import com.nongye.p2p.util.MD5;
import com.nongye.p2p.util.UserContext;

@Service
public class LogininfoServiceImpl implements LogininfoService{
	
	@Autowired
	private LogininifMapper logininifMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private IplogMapper iplogMapper;
	
	
	@Test
	public void insertLoginService(String userName,String Pwd) {
		// TODO Auto-generated method stub
		int count=logininifMapper.getCountByUsername(userName);
		if(count<1){
			//如果不存在 则添加
			Logininfo lo=new Logininfo();
			lo.setUserName(userName);
			lo.setPassWord(MD5.encode(Pwd));//密码加密
			lo.setState(lo.STATE_NORMAL);
			lo.setType(lo.USER_CLIENT);//正常状态
			logininifMapper.insertLogininif(lo);
			//注册用户信息
			Userinfo user=new Userinfo();
			user.setId(lo.getId());
			userinfoMapper.insert(user);
			//注册账户信息
			Account account=new Account();
			account.setId(lo.getId());
			accountMapper.insert(account);
		}else{
			throw new RuntimeException("该用户已经存在");
		}
	}
	
	/**验证用户是否存在*/
	@Override
	public boolean checkUsernameService(String username) {
		// TODO Auto-generated method stub
		return logininifMapper.getCountByUsername(username)>0?true:false;
	}
	/**登入*/
	@Override
	public Logininfo loginUserService(String username, String password, int Type,String ip) {
		// TODO Auto-generated method stub
		Logininfo login=new Logininfo();
		login.setUserName(username);
		login.setPassWord(MD5.encode(password));
		login.setType(Type);
		Logininfo userLogin=logininifMapper.getByLoininfo(login);
		//添加登入日志信息
		Iplog iplog=new Iplog();
		iplog.setIp(ip);
		iplog.setLoginTime(new Date());
		iplog.setUserName(username);
		iplog.setUserType(Type);
		//不为空则存入session
		if(userLogin!=null){
			UserContext.putContext(userLogin);
			//成功状态为1
			iplog.setState(iplog.LOGIN_SUCCESS);
		}else{
			//失败状态
			iplog.setState(iplog.LOGIN_FAILED);
		}
		//添加
		iplogMapper.insert(iplog);
		//返回出去
		return userLogin;
	}
	
	@Override
	public void insertAdminService() {
		// TODO Auto-generated method stub
		int count=logininifMapper.getUserType(Logininfo.USER_MANAGER);
		if(count<=0){
			//不存在后台用户
			Logininfo admin=new Logininfo();
			admin.setType(admin.USER_MANAGER);//后台用户
			admin.setUserName(BidConst.DEFAULT_ADMIN_NAME);//密码
			admin.setPassWord(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));//密码加密
			admin.setState(admin.STATE_NORMAL);//状态正常
			logininifMapper.insertLogininif(admin);
		}
		
	}



	

}
