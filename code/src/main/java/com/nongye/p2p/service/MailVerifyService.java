package com.nongye.p2p.service;

import com.nongye.p2p.domain.Mailverify;

/**
 * 邮箱认证业务逻辑接口层
 * @author 89568
 *
 */
public interface MailVerifyService {
	/**
     * 新增邮箱认证信息
     * @param mv
     */
	public void addMailVerify(Mailverify mv);
	
	/**
	 * 发送邮件
	 * @param email 接受邮箱
	 * @param Subject 标题
	 * @param content 内容
	 */
	public void sendEmail(String email, String Subject,String content);


}
