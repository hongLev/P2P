package com.nongye.p2p.mapper;

import com.nongye.p2p.domain.Mailverify;

/**
 * 邮箱认证数据层接口
 * @author 89568
 *
 */
public interface MailVerifyMapper {

	/**
	 * 新增邮箱认证信息
	 * @param mv
	 */
	 public void insert(Mailverify mv);
	 
	 /**
	  * 根据uuid 获取邮箱认证信息
	  * @param uuid
	  * @return
	  */
	 public Mailverify getByUUid(String uuid);

}
