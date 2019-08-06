package com.nongye.p2p.domain;

import com.nongye.p2p.base.BaseDomain;

/**
 * �û�����ע��  ��Ϣʵ����
 * @author 89568
 *
 */
public class Logininfo extends BaseDomain{

	public static final int STATE_NORMAL = 0;// ����
	public static final int STATE_LOCK = 1;// ����

	public static final int USER_MANAGER = 0;// 后台人员
	public static final int USER_CLIENT = 1;// 用户

	/**�û���*/
	private String userName;
	/**�û�����*/
	private String passWord;
	/**�û�״̬*/
	private int state;
	/***/
	private int type;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static int getStateNormal() {
		return STATE_NORMAL;
	}
	public static int getStateLock() {
		return STATE_LOCK;
	}
	public static int getUserManager() {
		return USER_MANAGER;
	}
	public static int getUserClient() {
		return USER_CLIENT;
	}
	
	
	
}
