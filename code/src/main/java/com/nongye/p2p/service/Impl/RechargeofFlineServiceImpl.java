package com.nongye.p2p.service.Impl;

import java.awt.image.RasterFormatException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.base.BaseAuditDomain;
import com.nongye.p2p.domain.Account;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.RechargeofFline;
import com.nongye.p2p.mapper.RechargeofFlineMapper;
import com.nongye.p2p.quey.RechargeQuery;
import com.nongye.p2p.service.IAccountFlowService;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IRechargeofFlineService;

import com.nongye.p2p.util.PageResult;

import com.nongye.p2p.util.UserContext;

@Service
public class RechargeofFlineServiceImpl implements IRechargeofFlineService {

	@Autowired
	private RechargeofFlineMapper rechargeofFlineMapper;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountFlowService accountFlowServcie;
	/*
	 * @Autowired private IUserinfoService userinfoService;
	 */
	/**
	 * 添加
	 */
	@Override
	public void insert(RechargeofFline rechergeofFline) {
		// TODO Auto-generated method stub
		// 获取申请人信息
		// 添加申请状态
		// 添加申请时间
		Logininfo login = UserContext.getContextUser();
		// 添加申请人
		rechergeofFline.setApplier(login);
		rechergeofFline.setState(BaseAuditDomain.STATE_NORMAL);// 设置状态为正常
		rechergeofFline.setApplyTime(new Date());// 设置申请时间

		this.rechargeofFlineMapper.insert(rechergeofFline);
	}
	/**
	 * 分页查询
	 */
	@Override
	public PageResult list(RechargeQuery query) {
		int count = this.rechargeofFlineMapper.queryCount(query);
		if(count>0){
			List<RechargeofFline> list = this.rechargeofFlineMapper.pageList(query);
			
			return new PageResult(list, count, query.getCurrentPage(), query.getPageSize()); 
		}
		return PageResult.empty(query.getPageSize());
	}
	@Override
	public void updateRechargeOff(RechargeofFline rechergeofFline) {
		// TODO Auto-generated method stub
		this.rechargeofFlineMapper.updateByPrimaryKey(rechergeofFline);
	}
	@Override
	public RechargeofFline selectById(Long id) {
		// TODO Auto-generated method stub
		return this.rechargeofFlineMapper.selectByPrimaryKey(id);
	}
	/**
	 * 审核
	 */
	@Override
	public void rechargeOfflineAudit(int id, int state, String remark) {
		// TODO Auto-generated method stub
		try {
			//获取充值单信息
			RechargeofFline rechar = selectById(Long.parseLong(id+""));
			if(rechar != null && rechar.getState() == rechar.STATE_NORMAL){
				//封装数据
				rechar.setAuditor(UserContext.getContextUser());
				rechar.setRemark(remark);
				rechar.setAuditTime(new Date());
				rechar.setState(state);
				if(state == rechar.STATE_AUDIT){
					//审核通过  封装账户流水明细表
					
					//获取账户信息 进行封装更新
					Account account = this.accountService.get(Long.parseLong(rechar.getApplier().getId()+""));
					//更新余额
					account.setUsableAmount(account.getUsableAmount().add(rechar.getAmount()));
					this.accountService.updateAccount(account);
					//添加交易流水
					this.accountFlowServcie.insert(rechar, account);
				}
				
				this.updateRechargeOff(rechar);
			}else{
				throw new RasterFormatException("修改错误");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 用户充值明细分页
	 */
	@Override
	public PageResult listByUserId(RechargeQuery query, int id) {
		query.setUserId(id);
		int count = this.rechargeofFlineMapper.queryCountUser(query);
		if(count>0){
			List<RechargeofFline> list = this.rechargeofFlineMapper.pageListUser(query);
			
			return new PageResult(list, count, query.getCurrentPage(), query.getPageSize()); 
		}
		return PageResult.empty(query.getPageSize());
	}

}
