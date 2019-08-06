package com.nongye.p2p.service;

import com.nongye.p2p.domain.Userinfo;

/**
 * 用户信息接口层
 * @author 89568
 *
 */
public interface IUserinfoService {
	/**
	 * 记住,写完mapper之后立刻写service,因为这个update是支持乐观锁的
	 * 
	 */
	public void updateUserInfo(Userinfo userinfo);
	
	/**
	 * 根据主键ID获取用户信息
	 * @param id
	 * @return
	 */
	public Userinfo get(Long id);
	
	/**
	 * 获取手机验证码
	 */
	public void sendVerifyCode(String phoneNumber);
	/**
	 * 验证手机验证码
	 */
	public void bindPhone(String phoneNumber,String code);
	
	/**
	 * 发送邮箱验证
	 */
	public void sendEmail(String Email);
	
	/**
	 * 绑定邮箱
	 */
	public void bindEmail(String uuid);
	
	/**
	 * 保存个人资料信息
	 * 
	 */
	public void sendBasicInfo(Userinfo userinfo);
	/**
	 * 根据主键ID获取userinfo
	 */
	public Userinfo getCurrentUserinfo();
}

