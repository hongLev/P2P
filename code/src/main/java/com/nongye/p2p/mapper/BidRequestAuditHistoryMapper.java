package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.BidRequestAuditHistory;

/**
 * 借款历史记录
 * @author 89568
 *
 */
public interface BidRequestAuditHistoryMapper {
	/**
	 * 添加
	 * @param record
	 * @return
	 */
	int insert(BidRequestAuditHistory record);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	BidRequestAuditHistory selectByPrimaryKey(Long id);
	
	/**
	 * 根据一个标的查询该表的审核历史
	 */
	List<BidRequestAuditHistory> selsctBidRequestAuditHistory(Long id);
}
