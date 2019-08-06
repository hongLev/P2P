package com.nongye.p2p.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.BidRequestAuditHistory;
import com.nongye.p2p.mapper.BidRequestAuditHistoryMapper;
import com.nongye.p2p.service.IBidRequestIdAuditHistoyService;

/**
 * 借款审核历史信息
 * @author 89568
 *
 */
@Service
public class IBidRequestIdAuditHistoyServiceImpl implements IBidRequestIdAuditHistoyService{

	@Autowired
	private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;
	/**
	 * 根据借款人id获取借款历史审核信息
	 * @param id
	 * @return
	 */
	public List<BidRequestAuditHistory> listByBbidRequest(Long id) {
		return this.bidRequestAuditHistoryMapper.selsctBidRequestAuditHistory(id);
	}
}
