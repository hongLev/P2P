package com.nongye.p2p.service.Impl;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.Realauth;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.LogininifMapper;
import com.nongye.p2p.mapper.RealauthMapper;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.RealauthService;
import com.nongye.p2p.util.BitstateUtil;
import com.nongye.p2p.util.PageResult;
import com.nongye.p2p.util.UserContext;

@Service
public class RealauthServiceImpl implements RealauthService{

	@Autowired
	private RealauthMapper realauthMapper;
	@Autowired
	private IUserinfoService userinfoService;

	@Override
	public Realauth getRealAuthById(int id) {
		// TODO Auto-generated method stub
		return this.realauthMapper.getByPrimarykey(id);
	}
	
	/**
	 * 添加
	 */
	@Override
	public void AddRealauth(Realauth realauth) {
		try {
			// TODO Auto-generated method stub
			//获取userinfo信息
			Userinfo current=this.userinfoService.getCurrentUserinfo();
			//判断
			if(current!=null&&current.getRealAuthId()==null){
				//封装实名认证信息
				//状态为正常
				realauth.setState(Realauth.STATE_NORMAL);
				//添加申请人
				realauth.setApplier(UserContext.getContextUser());
				//添加申请时间 
				realauth.setApplyTime(new Date());
				//添加实名认证信息
				realauthMapper.insert(realauth);
				//添加current状态
				current.setRealAuthId(Long.parseLong(realauth.getId()+""));
				//更行userinfo
				userinfoService.updateUserInfo(current);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PageResult list(RealauthQuery realauth) {
		//判断是否有数据
		int count= this.realauthMapper.count(realauth);
		if(count>0){
			List<Realauth> list=this.realauthMapper.getAllRealauth(realauth);
			
			return new PageResult(list, count, realauth.getCurrentPage(), realauth.getPageSize());
		}
		//无数据
		return PageResult.empty(realauth.getPageSize());
	}

	@Override
	public void AuditRealauth(Realauth realauth) {
		//1.接收到的数据 
			//1.1成功
				//1.1.1将用户状态设置为成功状态
			//1.2失败把Userinfo账户信息Realauthid重新设置为空
		//获取用户userinfo信息
		Realauth real=this.realauthMapper.getByPrimarykey(realauth.getId());
		Userinfo current=this.userinfoService.get(Long.parseLong(real.getApplier().getId()+""));
		//获取后台登入用户
		Logininfo logininfo = UserContext.getContextUser();
		
			if(realauth.getState()==1){
				//成功
				//设置成功信息
				current.addState(BitstateUtil.OP_REAL_AUTH);
				//添加审核状态
				real.setState(Realauth.STATE_AUDIT);
				//修改用户信息
				current.setIdNumber(real.getIdNumber());
				current.setRealName(real.getRealName());
			}else{
				//失败
				//修改用户的Realauthid重新设置为空
				current.setRealAuthId(null);
				//添加审核状态
				real.setState(Realauth.STATE_REJECT);
			}
			//添加审核人信息
			real.setAuditor(logininfo);
			//添加审核时间
			real.setAuditTime(new Date());
			//审核备注
			real.setRemark(realauth.getRemark());
			//修改Userinfo信息
			this.userinfoService.updateUserInfo(current);
			//修改realauth信息
			this.realauthMapper.updateByPrimaryKey(real);
		
		
	}

	@Override
	public Realauth getByAppReal(int id) {
		// TODO Auto-generated method stub
		return this.realauthMapper.selectApp(id);
	}

}
