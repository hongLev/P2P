package com.nongye.p2p.base;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.nongye.p2p.domain.Logininfo;

/**
 * 审核相关信息共通类
 * @author 89568
 *
 */
public class BaseAuditDomain extends BaseDomain{

	public static final int STATE_NORMAL = 0;// 正常
	public static final int STATE_AUDIT = 1;// 审核通过
	public static final int STATE_REJECT = 2;// 审核拒绝

	protected String remark;// 审核备注
	protected int state;// 状态
	protected Logininfo applier;// 申请人;
	protected Logininfo auditor;// 审核人
	protected Date applyTime;// 申请时间
	protected Date auditTime;// 审核时间
	
	public String getStateDisplay(){
		if(state==0){
			return "申请中";
		}else if(state==1){
			return "审核通过";
		}else if(state==2){
			return "审核拒绝";
		}
		return null;
	}
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Logininfo getApplier() {
		return applier;
	}
	public void setApplier(Logininfo applier) {
		this.applier = applier;
	}
	public Logininfo getAuditor() {
		return auditor;
	}
	public void setAuditor(Logininfo auditor) {
		this.auditor = auditor;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public static int getStateNormal() {
		return STATE_NORMAL;
	}
	public static int getStateAudit() {
		return STATE_AUDIT;
	}
	public static int getStateReject() {
		return STATE_REJECT;
	}
	
	
	
	
	
}
