package com.nongye.p2p.service.Impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.MoneyWithDraw;
import com.nongye.p2p.domain.SystemAccount;
import com.nongye.p2p.domain.SystemAccountFlow;
import com.nongye.p2p.mapper.SystemAccountFlowMapper;
import com.nongye.p2p.mapper.SystemAccountMapper;
import com.nongye.p2p.service.ISystemAccountService;



@Service
public class SystemAccountServiceImpl implements ISystemAccountService {

	@Autowired
	private SystemAccountMapper systemAccountMapper;

	@Autowired
	private SystemAccountFlowMapper systemAccountFlowMapper;

	public void update(SystemAccount systemAccount) {
		int ret = this.systemAccountMapper.updateByPrimaryKey(systemAccount);
		if (ret == 0) {
			throw new RuntimeException("系统账户对象乐观锁失败");
		}
	}

	@Override
	public void initAccount() {
		SystemAccount current = this.systemAccountMapper.selectCurrent();
		if (current == null) {
			current = new SystemAccount();
			this.systemAccountMapper.insert(current);
		}
	}

	@Override
	public void chargeBorrowFee(BidRequest br, BigDecimal manageChargeFee) {
		// 1,得到当前系统账户;
		SystemAccount current = this.systemAccountMapper.selectCurrent();
		// 2,修改账户余额;
		current.setUsableAmount(current.getUsableAmount().add(manageChargeFee));
		// 3,生成收款流水
		SystemAccountFlow flow = new SystemAccountFlow();
		flow.setAccountActionType(BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE);
		flow.setAmount(manageChargeFee);
		flow.setBalance(current.getUsableAmount());
		flow.setCreatedDate(new Date());
		flow.setFreezedAmount(current.getFreezedAmount());
		flow.setNote("借款" + br.getTitle() + "成功,收取借款手续费:" + manageChargeFee);
		this.systemAccountFlowMapper.insert(flow);
		this.update(current);
	}

	@Override
	public void chargeWithdrawFee(MoneyWithDraw m) {
		// 1,得到当前系统账户;
		SystemAccount current = this.systemAccountMapper.selectCurrent();
		// 2,修改账户余额;
		current.setUsableAmount(current.getUsableAmount().add(m.getCharge()));
		// 3,生成收款流水
		SystemAccountFlow flow = new SystemAccountFlow();
		flow.setAccountActionType(BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_WITHDRAW_MANAGE_CHARGE);
		flow.setAmount(m.getCharge());
		flow.setBalance(current.getUsableAmount());
		flow.setCreatedDate(new Date());
		flow.setFreezedAmount(current.getFreezedAmount());
		flow.setNote("用户" + m.getApplier().getUserName() + "提现" + m.getAmount()
				+ "成功,收取提现手续费:" + m.getCharge());
		
		this.systemAccountFlowMapper.insert(flow);
		this.update(current);
	}


}
