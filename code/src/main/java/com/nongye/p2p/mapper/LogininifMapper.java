package com.nongye.p2p.mapper;

import com.nongye.p2p.domain.Logininfo;
/**
 * 登入注册
 * @author 89568
 *
 */

public interface LogininifMapper {
	/**
	 * 用户注册功能
	 * @param lo
	 */
	public void insertLogininif(Logininfo lo);
	/**
	 * 根据用户名获取记录
	 */
	public int getCountByUsername(String UserName);
	/**
	 * 根据用户名和密码进行登入
	 * @param lo
	 * @return
	 */
	public Logininfo getByLoininfo(Logininfo lo);
	/**
	 * 查询时候存在后台用户
	 * @param type
	 * @return
	 */
	public int getUserType(int type);
	
	/**
	 * 根据id查询登入账户信息
	 */
	public Logininfo geyUserinfoById(int id);
	/**
	 * 根据userName查找登入用户信息
	 * 
	 */
	public Logininfo getByUserName(String userName);
}
