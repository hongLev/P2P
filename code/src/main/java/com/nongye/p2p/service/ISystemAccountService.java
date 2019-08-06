package com.nongye.p2p.service;

import java.math.BigDecimal;

import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.MoneyWithDraw;
import com.nongye.p2p.domain.SystemAccount;


/**
 * 系统账户相关服务
 * 
 * @author Administrator
 * 
 */
public interface ISystemAccountService {

	void update(SystemAccount systemAccount);

	/**
	 * 检查并初始化系统账户
	 */
	void initAccount();

	/**
	 * 系统账户收到借款管理费
	 * 
	 * @param br
	 * @param manageChargeFee
	 */
	void chargeBorrowFee(BidRequest br, BigDecimal manageChargeFee);

	/**
	 * 系统账户收取提现手续费
	 * 
	 * @param charge
	 */
	void chargeWithdrawFee(MoneyWithDraw m);


}
