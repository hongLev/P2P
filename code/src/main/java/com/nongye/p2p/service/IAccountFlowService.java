package com.nongye.p2p.service;

import java.math.BigDecimal;

import com.nongye.p2p.domain.Account;
import com.nongye.p2p.domain.Bid;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.MoneyWithDraw;
import com.nongye.p2p.domain.RechargeofFline;


/**
 * 账户流水对象
 * 
 * @author Administrator
 * 
 */
public interface IAccountFlowService {
	/**
	 * 充值申请流水
	 * @param recharge
	 * @param account
	 */
	public void insert(RechargeofFline recharge, Account account);
	/**
	 * 投标流水
	 * @param account
	 * @param cuurentAccount
	 */
	public void bid(BigDecimal account, Account cuurentAccount);
	/**
	 * 投标失败流水
	 * @param account
	 * @param cuurentAccount
	 */
	public void fullAudit1(BigDecimal account, Account cuurentAccount);
	/**
	 * 提现申请流水
	 * @param account
	 * @param cuurentAccount
	 */
	public void MoneyWith(BigDecimal account, Account cuurentAccount);
	/**
	 * 提现手续费流水
	 * 
	 * @param m
	 * @param account
	 */
	void withDrawChargeFee(MoneyWithDraw m, Account cuurentAccount);

	/**
	 * 提现成功流水
	 * 
	 * @param m
	 * @param account
	 */
	void withDrawSuccess(BigDecimal amount, Account cuurentAccount);
	/**
	 * 生成提现申请拒绝流水
	 * 
	 * @param m
	 * @param account
	 */
	void withDrawFailed(MoneyWithDraw m, Account cuurentAccount);
	/**
	 * 生成借款成功流水
	 * @param m
	 * @param cuurentAccount
	 */
	void borrowSuccess(BidRequest m, Account cuurentAccount);
	/**
	 * 生成借款成功 扣除手续费流水
	 * @param account
	 * @param cuurentAccount
	 */
	void borrowChargeFee(BigDecimal account, Account cuurentAccount);
	/**
	 * 生成成功投标流水
	 * @param bid
	 * @param cuurentAccount
	 */
	void bidSuccess(Bid bid, Account cuurentAccount);
	
}
