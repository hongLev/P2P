package com.nongye.p2p.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseDomain;
/**
 * 平台账号管理实体类
 * @author 89568
 *
 */
public class CompanyBankinfo extends BaseDomain{
	/**银行名称*/
	private String bankName;
	
	/**开户人姓名*/
	private String accountName;
	
	/**卡号*/
	private String bankNumber;
	
	/**支行名称*/
	private String bankForkName;
	
	/**银行log*/
	private String iconCls;
	/**
	 * Json回显数据
	 * 
	*/
	public String getJsonString(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", this.getId());
		map.put("bankName",this.getBankName());
		map.put("accountName",this.getAccountName());
		map.put("accountNumber",this.getBankNumber());
		map.put("bankForkName",this.getBankForkName());
		return JSONObject.toJSONString(map);
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getBankForkName() {
		return bankForkName;
	}

	public void setBankForkName(String bankForkName) {
		this.bankForkName = bankForkName;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	/***/
	
}
