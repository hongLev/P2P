package com.nongye.p2p.service;

import java.math.BigDecimal;

import com.nongye.p2p.quey.MoneyWithDrawQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 提现业务逻辑层
 * @author 89568
 *
 */
public interface IMoneyWithDrawService {
	 

	/**
	 * 提现申请
	 * 
	 * @param moneyAmount
	 */
	void apply(BigDecimal moneyAmount);

	PageResult query(MoneyWithDrawQuery qo);

	/**
	 * 提现审核
	 * 
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit(Long id, String remark, int state);
}
