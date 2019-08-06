package com.nongye.p2p.service.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.nongye.p2p.domain.Account;
import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.MoneyWithDraw;
import com.nongye.p2p.domain.UserbankInfo;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.MoneyWithDrawMapper;
import com.nongye.p2p.quey.MoneyWithDrawQuery;
import com.nongye.p2p.service.IAccountFlowService;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IMoneyWithDrawService;
import com.nongye.p2p.service.ISystemAccountService;
import com.nongye.p2p.service.IUserBankInfoService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.BitstateUtil;
import com.nongye.p2p.util.PageResult;
import com.nongye.p2p.util.UserContext;

@Service
public class MoneyWithDrawServiceImpl implements IMoneyWithDrawService {
	@Autowired
	private IUserinfoService userinfoSerive;
	@Autowired
	private MoneyWithDrawMapper moneyWithDrawMapper;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountFlowService accountFlowService;
	@Autowired
	private IUserBankInfoService userBankInfoService;
	@Autowired
	private ISystemAccountService systemAccountService;

	/**
	 * 添加
	 */
	@Override
	public void apply(BigDecimal moneyAmount) {
		try {
			// TODO Auto-generated method stub
			// 获取userinfo信息
			Userinfo user = this.userinfoSerive.getCurrentUserinfo();
			// 获取用户的Account 的信息
			Account account = this.accountService.get(Long.parseLong(user.getId() + ""));
			// System.out.println("是否绑定银行卡"+user.getIsBindBank()+"是否有一个申请流在处理"+!user.getHasWithdrawProcess());
			// 判断提现金额是否大于可用金额
			if (user.getIsBindBank() && !user.getHasWithdrawProcess()
					&& moneyAmount.compareTo(BidConst.MIN_WITHDRAW_AMOUNT) >= 0
					&& moneyAmount.compareTo(account.getUsableAmount()) <= 0) {
				// 获取用户绑定银行卡信息
				UserbankInfo ub = this.userBankInfoService.selectById(Long.parseLong(user.getId() + ""));
				// 创建MoneyWithDraw对象
				MoneyWithDraw m = new MoneyWithDraw();
				m.setAccountName(ub.getAccountName());
				m.setAccountNumber(ub.getAccountNumber());
				m.setAmount(moneyAmount);
				m.setApplier(UserContext.getContextUser());
				m.setApplyTime(new Date());
				m.setBankForkName(ub.getBankForkName());
				m.setBankName(ub.getBankName());
				m.setCharge(BidConst.MONEY_WITHDRAW_CHARGEFEE);
				m.setState(MoneyWithDraw.STATE_NORMAL);
				// 添加表 Money表
				this.moneyWithDrawMapper.insert(m);
				// 添加成功修改状态 ,userinfo表
				user.addState(BitstateUtil.OP_HAS_MONEYWITHDRAW_PROCESS);
				this.userinfoSerive.updateUserInfo(user);
				// 修改减少可用金额，增加冻结金额,修改account表
				account.setUsableAmount(account.getUsableAmount().subtract(moneyAmount));
				account.setFreezedAmount(account.getFreezedAmount().add(moneyAmount));
				this.accountService.updateAccount(account);
				// 添加流水明细
				this.accountFlowService.MoneyWith(moneyAmount, account);
			} else {
				// 失败抛出异常 提现申请失败
				throw new ReflectionException("提现申请失败,请稍后再试");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 分页界面
	 */
	@Override
	public PageResult query(MoneyWithDrawQuery qo) {
		int count = this.moneyWithDrawMapper.queryForCount(qo);
		if (count > 0) {
			List<MoneyWithDraw> list = this.moneyWithDrawMapper.query(qo);

			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	/**
	 * 提现审核
	 */
	@Override
	public void audit(Long id, String remark, int state) {
		// TODO Auto-generated method stub
		// 获取MoneyWith数据
		// 获取Account信息
		// 获取Userinfo信息
		// 判断审核是否成功
		// 修改moneyWith信息
		// 成功
		//
		// 2.修改account信息 可用资金不变 冻结资金减少
		//
		// 失败
		// 1.修改account信息 可用资金增加 冻结资金减少
		// 修改userinfo信息
		// 修改account信息
		// 添加accountFlow信息
		// 得到提现申请单
		MoneyWithDraw m = this.moneyWithDrawMapper.selectByPrimaryKey(id);
		// 1,判断:提现单状态
		if (m != null && m.getState() == MoneyWithDraw.STATE_NORMAL) {
			// 2,设置相关参数
			m.setAuditor(UserContext.getContextUser());
			m.setAuditTime(new Date());
			m.setRemark(remark);
			m.setState(state);

			Account account = this.accountService.get(Long.parseLong(m.getApplier().getId() + ""));
			if (state == MoneyWithDraw.STATE_AUDIT) {
				// 3,如果审核通过
				// 1,冻结金额减少(减少手续费),增加提现支付手续费流水;
				account.setFreezedAmount(account.getFreezedAmount().subtract(m.getCharge()));
				this.accountFlowService.withDrawChargeFee(m, account);
				// 2,系统账户增加可用余额,增加收取提现手续费流水;
				this.systemAccountService.chargeWithdrawFee(m);

				// 3,冻结金额减少(减少提现金额);增加提现成功流水;
				BigDecimal realWithdrawFee = m.getAmount().subtract(m.getCharge());
				account.setFreezedAmount(account.getFreezedAmount().subtract(realWithdrawFee));
				this.accountFlowService.withDrawSuccess(realWithdrawFee, account);

			} else {
				// 4,如果审核拒绝
				// 1,取消冻结金额,可用余额增加,增加去掉冻结流水
				account.setFreezedAmount(account.getFreezedAmount().subtract(m.getAmount()));
				account.setUsableAmount(account.getUsableAmount().add(m.getAmount()));
				this.accountFlowService.withDrawFailed(m, account);
			}
			this.accountService.updateAccount(account);
			this.moneyWithDrawMapper.updateByPrimaryKey(m);
			// 5,取消用户状态码
			Userinfo userinfo = this.userinfoSerive.get(Long.parseLong(m.getApplier().getId() + ""));
			userinfo.removeState(BitstateUtil.OP_HAS_MONEYWITHDRAW_PROCESS);
			this.userinfoSerive.updateUserInfo(userinfo);
		}
	}

}
