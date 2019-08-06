package com.nongye.p2p.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.base.BaseAuditDomain;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.LogininifMapper;
import com.nongye.p2p.mapper.VedioAuthMapper;

import com.nongye.p2p.quey.VedioauthQuery;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.VedioauthService;
import com.nongye.p2p.util.BitstateUtil;
import com.nongye.p2p.util.PageResult;
import com.nongye.p2p.util.UserContext;
@Service
public class VedioauthServiceImpl implements VedioauthService {

	@Autowired
	private VedioAuthMapper vedioAuthMapper;
	@Autowired
	private LogininifMapper logininfoMapper;
	@Autowired
	private IUserinfoService userinfoService; 
	
	//插入
	@Override
	public void AddRealauth(BaseAuditDomain vedio) {
		// TODO Auto-generated method stub
		this.vedioAuthMapper.insert(vedio);
	}

	//分页查询
	@Override
	public PageResult list(VedioauthQuery realauth) {
		
		try {
			int count = this.vedioAuthMapper.count(realauth);
			if(count>0){
				List<BaseAuditDomain> list=this.vedioAuthMapper.getAllRealauth(realauth);
				return new PageResult(list, count, realauth.getCurrentPage(), realauth.getPageSize());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageResult.empty(realauth.getPageSize());
	}
	
	//审核
	@Override
	public void AuditRealauth(BaseAuditDomain vedio,String loginInfoDisplay) {
		//先根据登录名找到Id,然后找出用户信息
		Logininfo login=this.logininfoMapper.getByUserName(loginInfoDisplay);
		Userinfo userinfo=this.userinfoService.get(Long.parseLong(login.getId()+""));
		Logininfo loginSession=UserContext.getContextUser();
		
			if(userinfo.getIsVedioAuth()){
				throw new RuntimeException("用户已认证，认证失败");
			}else{
				//成功
				if(vedio.getState()==1){
					userinfo.addState(BitstateUtil.OP_VEDIO_AUTH);
					this.userinfoService.updateUserInfo(userinfo);
				}
			}
				vedio.setApplier(login);
				vedio.setAuditor(loginSession);
				vedio.setAuditTime(new Date());
				vedio.setApplyTime(new Date());
				this.vedioAuthMapper.insert(vedio);
	}

	@Override
	public boolean checkUsernameService(String userName) {
		int count = this.vedioAuthMapper.getCountByUsername(userName);
		if(count>0){
			return true;
		}
		return false;
	}

}
