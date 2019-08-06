package com.nongye.p2p.service;

import java.math.BigDecimal;
import java.util.List;

import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.quey.BidRequestQueryObject;
import com.nongye.p2p.util.PageResult;

/**
 * 借款业务接口层
 * @author 89568
 *
 */
public interface IBidRequestService {

	/**
	 * 根据当年登入用户的Id,验证是否满足借款条件
	 */
	public boolean canApplyBidRequest(Logininfo logininfo);
	
	/**
	 * 借款申请功能
	 */
	public void apply(BidRequest bidRequest);
	/**
	 * 分页查询
	 * @param querys
	 * @return
	 */
	public PageResult list(BidRequestQueryObject query);
	/**
	 * 更新信息
	 * @param bidRequest
	 */
	public void updateBidRequest(BidRequest bidRequest);
	
	/**
	 * 发标前审核
	 * @param id
	 * @param state
	 * @param remark
	 */
	public void bidRequestPublishAudit(int id,int state,String remark);
	
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return
	 */
	public BidRequest getBidRequestById(Long id);
	/**
	 * 首页展示页面
	 * @param pageSize
	 * @return
	 */
	public List<BidRequest> main(int pageSize);
	/**
	 * 投标业务操作
	 * @param bidRequestId
	 * @param aumont
	 */
	public void bid(Long bidRequestId,BigDecimal amount);
	
	/**
	 * 用户投标总资金查询
	 */
	public BigDecimal sumBid(Long bidRequestId,Long bidUserId);
	
	/**
	 * 满标一审审核
	 */
	public void bidRequestAudit1(int id,int state,String remark);
	
	/**
	 * 满标二审审核
	 */
	public void bidRequestAudit2(int id,int state,String remark);
	
}
