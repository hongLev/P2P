package com.nongye.p2p.service.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Account;
import com.nongye.p2p.domain.AccountFlow;
import com.nongye.p2p.domain.Bid;
import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.MoneyWithDraw;
import com.nongye.p2p.domain.RechargeofFline;
import com.nongye.p2p.mapper.AccountFlowMapper;
import com.nongye.p2p.service.IAccountFlowService;
import com.nongye.p2p.util.BitstateUtil;

@Service
public class AccountFlowServiceImpl implements IAccountFlowService {

	@Autowired
	private AccountFlowMapper accountFlowMapper;

	@Override
	public void insert(RechargeofFline recharge, Account account) {
		// TODO Auto-generated method stub
		// 创建表 并且封装数据
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(account.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_RECHARGE_OFFLINE);// 流水类型
		accountFlow.setAmount(recharge.getAmount());// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote(recharge.getNote());
		accountFlow.setUsableAmount(account.getUsableAmount());
		accountFlow.setFreezedAmount(account.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void bid(BigDecimal account, Account cuurentAccount) {
		// TODO Auto-generated method stub
		// 创建表 并且封装数据
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_FREEZED);// 流水类型
		accountFlow.setAmount(account);// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("投标成功" + account + "投标账户" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void fullAudit1(BigDecimal account, Account cuurentAccount) {
		// TODO Auto-generated method stub
		// 创建表 并且封装数据
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
		accountFlow.setAmount(account);// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("退款成功" + account + "退款招标人" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void MoneyWith(BigDecimal account, Account cuurentAccount) {
		// TODO Auto-generated method stub
		// 创建表 并且封装数据
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
		accountFlow.setAmount(account);// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("提现申请:" + account + "\t提现申请人ID:" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	public void withDrawChargeFee(MoneyWithDraw m, Account cuurentAccount) {
		// TODO Auto-generated method stub
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
		accountFlow.setAmount(m.getAmount());// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("提现申请手续费:" + m.getAmount() + "\t提现申请人ID:" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void withDrawSuccess(BigDecimal amount, Account cuurentAccount) {
		// TODO Auto-generated method stub
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
		accountFlow.setAmount(amount);// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("提现申请成功:" + amount + "\t提现申请人ID:" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);

	}

	@Override
	public void withDrawFailed(MoneyWithDraw m, Account cuurentAccount) {
		// TODO Auto-generated method stub
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
		accountFlow.setAmount(m.getAmount());// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("提现申请拒绝:" + m.getAmount() + "\t申请人ID:" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void borrowSuccess(BidRequest m, Account cuurentAccount) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				AccountFlow accountFlow = new AccountFlow();
				accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
				accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
				accountFlow.setAmount(m.getBidRequestAmount());// 充值资金
				accountFlow.setTradeTime(new Date());
				accountFlow.setNote("借款成功:" + m.getBidRequestAmount() + "\t申请人ID:" + cuurentAccount.getId());
				accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
				accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
				this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void borrowChargeFee(BigDecimal account, Account cuurentAccount) {
		// TODO Auto-generated method stub
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
		accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
		accountFlow.setAmount(account);// 充值资金
		accountFlow.setTradeTime(new Date());
		accountFlow.setNote("借款成功手续费:" + account+ "\t申请人ID:" + cuurentAccount.getId());
		accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
		accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
		this.accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void bidSuccess(Bid bid, Account cuurentAccount) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				AccountFlow accountFlow = new AccountFlow();
				accountFlow.setAccountId(Long.parseLong(cuurentAccount.getId() + ""));// 账户信息id
				accountFlow.setAccountType(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);// 流水类型
				accountFlow.setAmount(bid.getAvailableAmount());// 充值资金
				accountFlow.setTradeTime(new Date());
				accountFlow.setNote("投标成功金额:" + bid.getAvailableAmount()+ "\t申请人ID:" + cuurentAccount.getId());
				accountFlow.setUsableAmount(cuurentAccount.getUsableAmount());
				accountFlow.setFreezedAmount(cuurentAccount.getFreezedAmount());
				this.accountFlowMapper.insert(accountFlow);
	}

}
