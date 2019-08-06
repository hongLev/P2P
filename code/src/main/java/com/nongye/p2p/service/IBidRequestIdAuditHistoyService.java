package com.nongye.p2p.service;

import java.util.List;

import com.nongye.p2p.domain.BidRequestAuditHistory;

public interface IBidRequestIdAuditHistoyService {
	
	/**
	 * 根据借款人id获取借款历史审核信息
	 * @param id
	 * @return
	 */
	public List<BidRequestAuditHistory> listByBbidRequest(Long id);
}
