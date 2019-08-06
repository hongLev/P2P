package com.nongye.p2p.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Account;
import com.nongye.p2p.domain.Bid;
import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.BidRequestAuditHistory;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.BidMapper;
import com.nongye.p2p.mapper.BidRequestAuditHistoryMapper;
import com.nongye.p2p.mapper.BidRequestMapper;
import com.nongye.p2p.quey.BidRequestQueryObject;
import com.nongye.p2p.service.IAccountFlowService;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IBidRequestService;
import com.nongye.p2p.service.ISystemAccountService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.BitstateUtil;
import com.nongye.p2p.util.CalculatetUtil;
import com.nongye.p2p.util.DecimalFormatUtil;
import com.nongye.p2p.util.PageResult;
import com.nongye.p2p.util.UserContext;

@Service
public class BidRequestServiceImpl implements IBidRequestService {

	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private BidRequestMapper bidRequestMapper;
	@Autowired
	private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private BidMapper bidMapper;
	@Autowired
	private IAccountFlowService accountFlowService;
	@Autowired
	private ISystemAccountService systemAccountService;

	@Override
	public boolean canApplyBidRequest(Logininfo logininfo) {
		/**
		 * 1:验证基本质料 2:身份证是否已认证 3:认证材料是否30分 4:视频已经认证 5:是否在借款流程中
		 */
		// 获取当前的userinfo
		Userinfo userinfo = this.userinfoService.get(Long.parseLong(logininfo.getId() + ""));

		return userinfo != null && userinfo.getIsBasicInfo() // 验证基本资料
				&& userinfo.getIsRealAuth()// 身份证时候已经认证
				&& userinfo.getIsVedioAuth()// 视频是否已经认证
				&& !userinfo.getIsBidRequest()// 是否已经投标
				&& userinfo.getScore() > BidConst.BASE_BORROW_SCORE;// 材料分是否大于30分
	}

	@Override
	public void apply(BidRequest bidRequest) {
		// TODO Auto-generated method stub
		// 1.验证是否瞒住借款申请条件
		// 1.1借款金额是否满足系统最低借款金额
		// 2:系统的最低利息《=借款利息<=系统最高利息
		// 3:最小投标金额必须大于系统最低投标金额
		if (bidRequest.getBidRequestAmount().compareTo(BidConst.SMALLEST_BIDREQUEST_AMOUNT) > 0 // 1.1借款金额是否满足系统最低借款金额
				&& bidRequest.getCurrentRate().compareTo(BidConst.SMALLEST_CURRENT_RATE) >= 0// 系统的最低利息《=借款利息<=系统最高利息
				&& bidRequest.getCurrentRate().compareTo(BidConst.MAX_CURRENT_RATE) <= 0//
				&& bidRequest.getMinBidAmount().compareTo(BidConst.SMALLEST_BID_AMOUNT) >= 0) {
			// 封装数据
			// 2:创建BidRequest对象
			BidRequest bid = new BidRequest();
			// 3:封装数据
			bid.setReturnType(bidRequest.getReturnType()); // 设置还款方式
			bid.setBidRequestType(BidConst.BIDREQUEST_TYPE_NORMAL); // 借款类型（信用标）
			bid.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING); // 借款状态（申请待发布）
			bid.setBidRequestAmount(bidRequest.getBidRequestAmount()); // 借款总金额
			bid.setCurrentRate(bidRequest.getCurrentRate()); // 年化利率
			bid.setMinBidAmount(bidRequest.getMinBidAmount()); // 最小借款金额
			bid.setMonthes2Return(bidRequest.getMonthes2Return()); // 借款期限
			bid.setTotalRewardAmount(CalculatetUtil.calTotalInterest(bidRequest.getReturnType(),
					bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(), bidRequest.getMonthes2Return()));// 总利息
			bid.setTitle(bidRequest.getTitle());// 借款标题
			bid.setDescription(bidRequest.getDescription());// 借款描述
			bid.setDisableDays(bidRequest.getDisableDays());// 招标天数
			bid.setApplyTime(new Date());// 申请时间
			bid.setCreateUser(UserContext.getContextUser());// 申请人

			// 新增

			this.bidRequestMapper.insert(bid);
			// 修改状态
			Userinfo userinfo = userinfoService.get(Long.parseLong(UserContext.getContextUser().getId() + ""));
			userinfo.addState(BitstateUtil.OP_HAS_BIDREQUEST_PROCESS);
			// 修改
			this.userinfoService.updateUserInfo(userinfo);

		}
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageResult list(BidRequestQueryObject query) {
		// 1:获取总记录数
		int count = this.bidRequestMapper.queryCount(query);
		if (count > 0) {
			// 2:分页查询借款人信息
			List<BidRequest> list = this.bidRequestMapper.pageList(query);
			// 3：封装pageResult
			return new PageResult(list, count, query.getCurrentPage(), query.getPageSize());
		}

		return PageResult.empty(query.getPageSize());

	}

	@Override
	public void updateBidRequest(BidRequest bidRequest) {
		// TODO Auto-generated method stub
		int version = this.bidRequestMapper.updateByPrimaryKey(bidRequest);
		// 失效
		if (version == 0) {
			throw new RuntimeException("乐观锁失败   bidRequest:" + bidRequest.getId());
		}

	}

	@Override
	public void bidRequestPublishAudit(int id, int state, String remark) {
		// TODO Auto-generated method stub
		// 获取bidRequset信息
		BidRequest bidRequest = this.bidRequestMapper.selectByPrimaryKey(Long.parseLong(id + ""));
		// 创建审核历史信息
		BidRequestAuditHistory bidAuditH = new BidRequestAuditHistory();
		// 3：封装数据
		bidAuditH.setBidRequestId(Long.parseLong(id + "")); // BidRequet 对象
		bidAuditH.setAuditType(BidRequestAuditHistory.PUBLISH_AUDIT); // 审核类型
		bidAuditH.setRemark(remark); // 审核备注
		bidAuditH.setState(state); // 审核状态
		bidAuditH.setAuditor(UserContext.getContextUser()); // 审核人
		bidAuditH.setAuditTime(new Date()); // 审核时间
		// 4:添加审核记录
		this.bidRequestAuditHistoryMapper.insert(bidAuditH);
		// 审核通过
		if (state == BidConst.BIDREQUEST_STATE_BIDDING) {
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_BIDDING); // 招标中
			bidRequest.setNote(remark); // 风控意见
			bidRequest.setDisableDate(DateUtils.addDays(new Date(), bidRequest.getDisableDays()));// 招标截止日期
			bidRequest.setPublishTime(new Date()); // 发标时间

		} else {
			// 审核失败
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_REJECTED);
			// 5:根据申请人的id获取userInfo信息
			Userinfo userinfo = this.userinfoService.get(Long.parseLong(bidRequest.getCreateUser().getId() + ""));
			// 6：将状态删除
			userinfo.removeState(BitstateUtil.OP_HAS_BIDREQUEST_PROCESS);
			// 7:更新userinfo 信息
			this.userinfoService.updateUserInfo(userinfo);
		}
		// 更新bidRequest 信息
		this.updateBidRequest(bidRequest);

	}

	@Override
	public BidRequest getBidRequestById(Long id) {
		// TODO Auto-generated method stub
		return this.bidRequestMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BidRequest> main(int pageSize) {

		BidRequestQueryObject query = new BidRequestQueryObject();
		// 封装查询条件
		query.setBidRequestStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK,
				BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
		query.setCurrentPage(1);
		query.setPageSize(pageSize);
		query.setOrderyBy("bidRequestState");
		query.setOrderByType("ASC");
		return this.bidRequestMapper.pageList(query);
	}

	@Override
	public void bid(Long bidRequestId, BigDecimal amount) {
		// TODO Auto-generated method stub
		/** 1:根据bidRequestId 获取借款信息 */
		BidRequest bid = this.getBidRequestById(bidRequestId);
		Account account = this.accountService.get(Long.parseLong(UserContext.getContextUser().getId() + ""));
		/**
		 * 2:校验投标条件是否瞒住 2.1借款信息不能为空 2.2借款状态为投标中 2.3投标金额大于等于最小投标金额
		 * 2.4投标金额小于等于可用金额 2.5投标金额小于等于还需投标金额
		 */
		if (bid != null // 1借款信息不能为空
				&& bid.getBidRequestState() == BidConst.BIDREQUEST_STATE_BIDDING// 2借款状态为投标中
				&& amount.compareTo(bid.getMinBidAmount()) >= 0// 3投标金额大于等于最小投标金额
				&& amount.compareTo(account.getUsableAmount()) <= 0// 4投标金额小于等于可用金额
				&& amount.compareTo(bid.getRemainAmount()) <= 0// 5投标金额小于等于还需投标金额
		) {
			if (amount.compareTo(bid.getBidRequestAmount().subtract(bid.getCurrentSum())) > 0) {// 判断金额是否大于还需投资的金额
				throw new ReflectionException("投标失败！投标金额大于还需投标金额");
			}
			// 4:创建bid信息 投标信息 封装数据;
			Bid bi = new Bid();
			bi.setActualRate(bid.getCurrentRate());// 年利率吧
			bi.setAvailableAmount(amount);// 投标金额
			bi.setBidRequestId(Long.parseLong(bid.getId() + ""));// 关联借款信息
			bi.setBidRequestTitle(bid.getTitle());// 借款标题
			bi.setBidUser(UserContext.getContextUser());// 投资人
			bi.setBidTime(new Date());// 投标时间
			// 添加
			this.bidMapper.insert(bi);
			// 修改投资人账户信息
			account.setUsableAmount(account.getUsableAmount().subtract(amount));// 可用金额减少
			account.setFreezedAmount(account.getFreezedAmount().add(amount));// 冻结资金增加
			// 添加交易流水
			this.accountFlowService.bid(amount, account);
			// 更新借款信息
			bid.setBidCount(bid.getBidCount() + 1);
			bid.setCurrentSum(bid.getCurrentSum().add(amount));
			// 判断是否投满
			if (bid.getCurrentSum().compareTo(bid.getBidRequestAmount()) == 0) {
				// 修改状态
				bid.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
			}
			// 更新投资人账户信息
			this.accountService.updateAccount(account);
			// 更新借款信息
			this.updateBidRequest(bid);
		}

	}

	/**
	 * 计算对应发标已投的金额
	 */
	@Override
	public BigDecimal sumBid(Long bidRequestId, Long bidUserId) {
		// 获取用户已经对发标信投标成功的信息
		List<Bid> bid = this.bidMapper.selectByType(bidRequestId, bidUserId);
		// 判断
		BigDecimal sum = BigDecimal.ZERO;
		if (bid.size() > 0) {

			for (Bid list : bid) {

				sum = sum.add(list.getAvailableAmount());
			}
		}
		return sum;
	}

	/**
	 * 
	 * 满标一审审核
	 */
	@Override
	public void bidRequestAudit1(int id, int state, String remark) {
		// TODO Auto-generated method stub
		/** 添加 BidRequestidAuditHsitoy */
		/** 改动account表 */
		/** 改动accountFlow 明细表 */
		/** 改动BidRequest */
		try {
			BidRequest bid = this.bidRequestMapper.selectByPrimaryKey(Long.parseLong(id + ""));
			if (bid.getBidRequestState() == BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1 && bid != null) {
				// 满标一审添加审核记录
				BidRequestAuditHistory br = new BidRequestAuditHistory();
				br.setApplier(bid.getCreateUser());// 借款人id
				br.setApplyTime(bid.getApplyTime());// 申请时间
				br.setAuditType(br.FULL_AUDIT_1);// 申请类型
				br.setAuditor(UserContext.getContextUser());// 审核人
				br.setAuditTime(new Date());// 审核时间
				br.setRemark(remark);// 审核备注
				br.setState(state);// 审核状态
				br.setBidRequestId(Long.parseLong(bid.getId() + ""));
				this.bidRequestAuditHistoryMapper.insert(br);// 新增审核信息
				if (state == br.FULL_AUDIT_1) {
					// 成功
					bid.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);// 修改状态
				} else {
					// 失败 插入状态
					bid.setBidRequestState(BidConst.BIDREQUEST_STATE_REJECTED);// 修改状态
					/** 退钱 */
					returnMoney(bid);
					/** 将借款人借款流程结束掉 */
					// 获取借款人userinfo 信息
					Userinfo userinfo = this.userinfoService.get(Long.parseLong(bid.getCreateUser().getId() + ""));
					userinfo.removeState(BitstateUtil.OP_HAS_BIDREQUEST_PROCESS);
					this.userinfoService.updateUserInfo(userinfo);
				}
				// 修改
				this.bidRequestMapper.updateByPrimaryKey(bid);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ReflectionException("异常");
		}

	}

	/**
	 * 审核 失败 投标钱原路返回
	 */
	public void returnMoney(BidRequest br) {

		/** 获取所有投标人信息 */
		for (Bid bid : br.getBids()) {
			Account account = this.accountService.get(Long.parseLong(bid.getBidUser().getId() + ""));
			account.setUsableAmount(account.getUsableAmount().add(bid.getAvailableAmount()));// 可用余额增加
			account.setFreezedAmount(account.getFreezedAmount().subtract(bid.getAvailableAmount()));// 冻结金额减少
			this.accountFlowService.fullAudit1(bid.getAvailableAmount(), account);// 更新账户流水
			this.accountService.updateAccount(account);
		}

	}

	@Override
	public void bidRequestAudit2(int id, int state, String remark) {
		try {
			// TODO Auto-generated method stub
			// 得到借款对象,判断状态
			BidRequest br = this.getBidRequestById(Long.parseLong(id + ""));
			if (br != null && br.getBidRequestState() == BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2) {
				// 创建一个借款的审核流程对象,并设置相关参数;
				BidRequestAuditHistory history = new BidRequestAuditHistory();
				history.setApplier(br.getCreateUser());
				history.setApplyTime(new Date());
				history.setAuditor(UserContext.getContextUser());
				history.setAuditTime(new Date());
				history.setBidRequestId(Long.parseLong(br.getId() + ""));
				history.setRemark(remark);
				history.setState(state);
				history.setAuditType(BidRequestAuditHistory.FULL_AUDIT_2);
				this.bidRequestAuditHistoryMapper.insert(history);
				if (state == BidRequestAuditHistory.STATE_AUDIT) {
					// 审核通过
					// 1,对借款要做什么事情?
					// **1.1修改借款状态(还款中)
					br.setBidRequestState(BidConst.BIDREQUEST_STATE_PAYING_BACK);
					// 2,对借款人要做什么事情?
					// **2.1借款人收款操作
					Account borrowAccount = this.accountService.get(Long.parseLong(br.getCreateUser().getId()+""));
					// ***2.1.1账户余额增加,
					borrowAccount.setUsableAmount(borrowAccount.getUsableAmount().add(br.getBidRequestAmount()));
					// ***2.1.2生成收款流水;
					this.accountFlowService.borrowSuccess(br, borrowAccount);
					// ***2.1.3修改待还本息;
					borrowAccount.setUnReturnAmount(
							borrowAccount.getUnReturnAmount().add(br.getBidRequestAmount()).add(br.getTotalRewardAmount()));
					// ***2.1.4修改可用信用额度;
					borrowAccount
							.setRemainBorrowLimit(borrowAccount.getRemainBorrowLimit().subtract(br.getBidRequestAmount()));
					// **2.2移除借款人借款进行中状态码;
					Userinfo borrowUser = this.userinfoService.get(Long.parseLong(br.getCreateUser().getId()+""));
					borrowUser.removeState(BitstateUtil.OP_HAS_BIDREQUEST_PROCESS);
					this.userinfoService.updateUserInfo(borrowUser);
					// **2.3支付借款手续费
					// ***2.3.1可用余额减少
					BigDecimal manageChargeFee = CalculatetUtil.calAccountManagementCharge(br.getBidRequestAmount());
					borrowAccount.setUsableAmount(borrowAccount.getUsableAmount().subtract(manageChargeFee));
					// ***2.3.2生成支付借款手续费流水;
					this.accountFlowService.borrowChargeFee(manageChargeFee, borrowAccount);
					// ***2.3.3平台收取借款手续费;
					this.systemAccountService.chargeBorrowFee(br, manageChargeFee);

					// 3,对投资人要做什么事情?
					// **3.1遍历投标;
					Map<Long, Account> updates = new HashMap<Long, Account>();
					// 汇总利息,用于最后一个投标的用户的利息计算
					BigDecimal totalBidInterest = BidConst.ZERO;
					for (int i = 1; i <= br.getBids().size(); i++) {
						Bid bid = br.getBids().get(i - 1);

						// **3.2根据投标减少投资人的冻结金额;
						Long bidUserId = Long.parseLong(bid.getBidUser().getId()+"");
						Account bidAccount = updates.get(bidUserId);
						if (bidAccount == null) {
							bidAccount = this.accountService.get(bidUserId);
							updates.put(bidUserId, bidAccount);
						}
						bidAccount.setFreezedAmount(bidAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));
						// **3.3生成成功投标流水
						this.accountFlowService.bidSuccess(bid, bidAccount);
						// **3.4计算待收利息和待收本金
						// 待收本金==这次的投标金额
						bidAccount.setUnReceivePrincipal(bidAccount.getUnReceivePrincipal().add(bid.getAvailableAmount()));
						// 如果当前投标是整个投标列表中的最后一个投标;这个投标的利息=借款总回报利息-累加的投标利息|
						BigDecimal bidInterest = BidConst.ZERO;
						if (i < br.getBids().size()) {
							// 待收利息=投标金额/借款总金额*借款总回报利息
							bidInterest = bid.getAvailableAmount()
									.divide(br.getBidRequestAmount(), BidConst.CAL_SCALE, RoundingMode.HALF_UP)
									.multiply(br.getTotalRewardAmount());

							bidInterest = DecimalFormatUtil.formatBigDecimal(bidInterest, BidConst.STORE_SCALE);
							// 累加
							totalBidInterest = totalBidInterest.add(bidInterest);
						} else {
							bidInterest = br.getTotalRewardAmount().subtract(totalBidInterest);
						}
						bidAccount.setUnReceiveInterest(bidAccount.getUnReceiveInterest().add(bidInterest));
					}
					// 4,思考满标二审之后的流程(还款)对满标二审有什么影响
					// **4生成还款对象和回款对象
					
					this.accountService.updateAccount(borrowAccount);
					for (Account account : updates.values()) {
						this.accountService.updateAccount(account);
					}
				} else {
					// 审核拒绝
					// **1,修改借款状态;
					// **2,退款
					// **3,移除借款人正在借款状态码
					br.setBidRequestState(BidConst.BIDREQUEST_STATE_REJECTED);
					returnMoney(br);
					Userinfo borrowUser = this.userinfoService.get(Long.parseLong(br.getCreateUser().getId()+""));
					borrowUser.removeState(BitstateUtil.OP_HAS_BIDREQUEST_PROCESS);
					this.userinfoService.updateUserInfo(borrowUser);
				}
				this.updateBidRequest(br);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
