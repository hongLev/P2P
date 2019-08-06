package com.nongye.p2p.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseAuditDomain;

/**
 * 账户充值信息实体类
 * @author 89568
 *
 */
public class RechargeofFline extends BaseAuditDomain{
	/**充值交易流水号*/
	private String tradeCode;
	/**充值交易时间*/
	private Date tradeTime;
	/**充值金额*/
	private BigDecimal amount;
	/**审核意见*/
	private String  note;
	/**关联平台账号信息*/
	private CompanyBankinfo bankinfo_id;
	
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getTradeTime() {
		return tradeTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public CompanyBankinfo getBankinfo_id() {
		return bankinfo_id;
	}
	public void setBankinfo_id(CompanyBankinfo bankinfo_id) {
		this.bankinfo_id = bankinfo_id;
	}
	
	public String getJsonString(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", this.getId());
		map.put("username", this.getApplier().getUserName());
		map.put("tradeCode", this.getTradeCode());
		map.put("amount", this.getAmount());
		map.put("tradeTime", this.getTradeTime());
		return JSONObject.toJSONString(map);
	}

}
