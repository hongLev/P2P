package com.nongye.p2p.domain;

import com.nongye.p2p.base.BaseDomain;
import com.nongye.p2p.util.BitstateUtil;
/**
 * 用户信息表
 * @author 89568
 *
 */
public class Userinfo extends BaseDomain{	
	private int version;// 版本号
	private long bitState=0;// 用户状态码
	private String realName;
	private String idNumber;
	private String phoneNumber;
	private String email;
	private int score;// 风控累计分数;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public long getBitState() {
		return bitState;
	}
	public void setBitState(long bitState) {
		this.bitState = bitState;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Long getRealAuthId() {
		return realAuthId;
	}
	public void setRealAuthId(Long realAuthId) {
		this.realAuthId = realAuthId;
	}
	public SystemDictionaryItem getIncomeGrade() {
		return incomeGrade;
	}
	public void setIncomeGrade(SystemDictionaryItem incomeGrade) {
		this.incomeGrade = incomeGrade;
	}
	public SystemDictionaryItem getMarriage() {
		return marriage;
	}
	public void setMarriage(SystemDictionaryItem marriage) {
		this.marriage = marriage;
	}
	public SystemDictionaryItem getKidCount() {
		return kidCount;
	}
	public void setKidCount(SystemDictionaryItem kidCount) {
		this.kidCount = kidCount;
	}
	public SystemDictionaryItem getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(SystemDictionaryItem educationBackground) {
		this.educationBackground = educationBackground;
	}
	public SystemDictionaryItem getHouseCondition() {
		return houseCondition;
	}
	public void setHouseCondition(SystemDictionaryItem houseCondition) {
		this.houseCondition = houseCondition;
	}
	private Long realAuthId;// 该用户对应的实名认证对象id

	private SystemDictionaryItem incomeGrade;// 收入
	private SystemDictionaryItem marriage;//
	private SystemDictionaryItem kidCount;//
	private SystemDictionaryItem educationBackground;//
	private SystemDictionaryItem houseCondition;//
	/**
	 * 添加状态码
	 * 
	 * @param state
	 */
	public void addState(long state) {
		this.setBitState(BitstateUtil.addState(this.bitState, state));
	}

	public void removeState(long state) {
		this.setBitState(BitstateUtil.removeState(this.bitState, state));
	}
	
	public boolean getIsBindPhone(){
		//判断手机是否已经认证
		return BitstateUtil.hasState(bitState, BitstateUtil.OP_BIND_PHONE);
	}
	
	public boolean getIsBindEmail(){
		//验证邮箱是否已经认证
		return BitstateUtil.hasState(bitState, BitstateUtil.OP_BIND_EMAIL);
	}
	
	/**
	 * 返回用户是否已经填写了基本资料
	 * 
	 * @return
	 */
	public boolean getIsBasicInfo() {
		return BitstateUtil.hasState(this.bitState,BitstateUtil.OP_BASIC_INFO);
	}

	/**
	 * 返回用户是否已经实名认证
	 * 
	 * @return
	 */
	public boolean getIsRealAuth() {
		return BitstateUtil.hasState(this.bitState,BitstateUtil.OP_REAL_AUTH);
	}

	/**
	 * 返回用户是否视频认证
	 * 
	 * @return
	 */
	public boolean getIsVedioAuth() {
		return BitstateUtil.hasState(this.bitState,BitstateUtil.OP_VEDIO_AUTH);
	}

	/**
	 * 返回用户是否绑定银行卡
	 * 
	 * @return
	 */
	public boolean getIsBindBank() {
		return BitstateUtil.hasState(this.bitState,BitstateUtil.OP_BIND_BANKINFO);
	}
	
	/**
	 * 验证是否已经申请贷款状态
	 */
	public boolean getIsBidRequest(){
		
		return BitstateUtil.hasState(this.bitState, BitstateUtil.OP_HAS_BIDREQUEST_PROCESS);
	}
	/**
	 * 返回用户是否有一个提现申请在处理流程当中
	 * 
	 * @return
	 */
	public boolean getHasWithdrawProcess() {
		return BitstateUtil.hasState(this.bitState,BitstateUtil.OP_HAS_MONEYWITHDRAW_PROCESS);
	}
	
}
