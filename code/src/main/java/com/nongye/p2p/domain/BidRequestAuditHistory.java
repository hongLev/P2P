package com.nongye.p2p.domain;

import com.nongye.p2p.base.BaseAuditDomain;

/**
 * 借款审核历史对象
 */
public class BidRequestAuditHistory extends BaseAuditDomain{
	public static final int PUBLISH_AUDIT = 0;// 发标前审核
	public static final int FULL_AUDIT_1 = 1;// 满标一审
	public static final int FULL_AUDIT_2 = 2;// 满标二审
	
	/**借款对象Id*/
	private Long bidRequestId;
	/**审核类型*/
	private int auditType;

	public String getAuditTypeDisplay() {
		switch (this.auditType) {
		case PUBLISH_AUDIT:
			return "发标前审核";
		case FULL_AUDIT_1:
			return "满标一审";
		case FULL_AUDIT_2:
			return "满标二审";
		default:
			return "";
		}
	}

	public Long getBidRequestId() {
		return bidRequestId;
	}

	public void setBidRequestId(Long bidRequestId) {
		this.bidRequestId = bidRequestId;
	}

	public int getAuditType() {
		return auditType;
	}

	public void setAuditType(int auditType) {
		this.auditType = auditType;
	}

	
}
