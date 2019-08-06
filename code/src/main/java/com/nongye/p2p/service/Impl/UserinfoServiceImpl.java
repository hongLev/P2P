package com.nongye.p2p.service.Impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.Mailverify;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.domain.VerifyCodeVo;
import com.nongye.p2p.mapper.MailVerifyMapper;
import com.nongye.p2p.mapper.UserinfoMapper;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.MailVerifyService;
import com.nongye.p2p.util.BitstateUtil;
import com.nongye.p2p.util.DateUtil;
import com.nongye.p2p.util.UserContext;
@Service
public class UserinfoServiceImpl implements IUserinfoService{
	
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private MailVerifyService mailVerifyService;
	@Autowired
	private MailVerifyMapper mailVerifyMapper;
	
	@Override
	public void updateUserInfo(Userinfo userinfo) {
		// TODO Auto-generated method stub
		int version=this.userinfoMapper.updateByPrimaryKey(userinfo);
		if(version==0){
			throw new RuntimeException("乐观锁失效！Userinfo="+userinfo.getId());
		}
	}

	@Override
	public Userinfo get(Long id) {
		// TODO Auto-generated method stub
		return userinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void sendVerifyCode(String phoneNumber) {
		// TODO Auto-generated method stub
		//1.获取session是否有值 
		VerifyCodeVo vo=UserContext.getVerifycoke();
		if(vo==null||DateUtil.secondsBetween(new Date(),vo.getLastSendDate())>=10){
			//发送验证码
			String code=UUID.randomUUID().toString().substring(0, 4);
			System.out.println("手机验证码==="+code);
			//封装数据
			VerifyCodeVo vc=new VerifyCodeVo();
			vc.setPhoneNumber(phoneNumber);
			vc.setLastSendDate(new Date());
			vc.setCode(code);
			//存入session
			UserContext.putVerifycoke(vc);
		}else{
			throw new RuntimeException("发送过于频繁");
		}
	}
	
	@Override
	public void bindPhone(String phoneNumber, String code) {
		// TODO Auto-generated method stub
		//获取当前用户Id
		Logininfo user=UserContext.getContextUser();
		//判断
		if(user!=null){
			//根据ID获取到用户信息
			Userinfo userinfo=userinfoMapper.selectByPrimaryKey(Long.parseLong(user.getId()+""));
			//判断
			if(userinfo!=null&&!userinfo.getIsBindPhone()){
				boolean flag=this.checkVerifyCode(phoneNumber, code);
		          if(flag){
		        	  //更新数据
		        	  userinfo.addState(BitstateUtil.OP_BIND_PHONE);
		        	  userinfo.setPhoneNumber(phoneNumber);
		        	  this.updateUserInfo(userinfo);
		          }else{
		        	  throw new RuntimeException("更新手机失败！");
		          }

			}else{
				throw new RuntimeException("更新手机失败！");
			}
			
		}
		
		
	}

	private boolean checkVerifyCode(String phoneNumber, String verifyCode){
		//从session 获取验证码信息
 		VerifyCodeVo vo=UserContext.getVerifycoke();
	   if(vo!=null){
		   if(phoneNumber.equals(vo.getPhoneNumber())
			&& 	verifyCode.equals(vo.getCode())
			&& DateUtil.secondsBetween(new Date(), vo.getLastSendDate())<=BidConst.VERIFYCODE_VAILDATE_SECOND){
			   return true;
		   }
	   }
	   return false;
	
	}
	//连接
	@Value("${mail.hostUrl}")
	 private String hostUrl;

	
	@Override
	public void sendEmail(String Email) {
		// TODO Auto-generated method stub
		//获取用户信息
		Userinfo userinfo=this.get(Long.parseLong(UserContext.getContextUser().getId()+""));
		//判断用户信息是否为空  或者已经注册过
		if(userinfo!=null||!userinfo.getIsBindEmail()){
			//2：生成验证码
			String uuid=UUID.randomUUID().toString();
			//3:封装邮件数据 <a href='http://localhost:8080/bindEmail.do?key=sdfasd1231'></a>
			StringBuilder content = new StringBuilder(100).append("点击<a href='").append(this.hostUrl)
					.append(uuid).append("'>这里</a>完成邮箱绑定,有效期为")
					.append(BidConst.VERIFYEMAIL_VAILDATE_DAY).append("天");

		
			//4:发送邮件
			this.mailVerifyService.sendEmail(Email, "邮箱验证", content.toString());
			
			//3:保存邮件信息
			Mailverify mv=new Mailverify();
			mv.setEmail(Email);
			mv.setSendDate(new Date());
			mv.setUserInfoId(userinfo.getId());
			mv.setUuid(uuid);
			this.mailVerifyService.addMailVerify(mv); 
		}else{
			throw new RuntimeException("发送邮件失败");
		}
		
       

		}

	@Override
	public void bindEmail(String uuid) {
		try {
			// TODO Auto-generated method stub
			//1:根据uuid获取MailVerify数据
			Mailverify mv=this.mailVerifyMapper.getByUUid(uuid);
			//2:根据MailVerify——userinfoid 获取userinfo数据
			if(mv!=null){
				Userinfo userinfo=this.get(Long.parseLong(mv.getUserInfoId()+""));
				//3:判断用户是否绑定邮箱了
				if(userinfo!=null&&!userinfo.getIsBindEmail()){
					//判断是否在有效期
					if(DateUtil.secondsBetween(new Date(), mv.getSendDate())<=BidConst.VERIFYEMAIL_VAILDATE_DAY*24*60*60){
						//添加邮箱状态
						userinfo.addState(BitstateUtil.OP_BIND_EMAIL);
						//添加邮箱
						userinfo.setEmail(mv.getEmail());
						this.userinfoMapper.updateByPrimaryKey(userinfo);
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("绑定邮箱失败");
		}
	}

	@Override
	public void sendBasicInfo(Userinfo userinfo) {
		// TODO Auto-generated method stub
		Userinfo current=get(Long.parseLong(UserContext.getContextUser().getId()+""));
		//2.更新数据
		current.setIncomeGrade(userinfo.getIncomeGrade());//收入
		current.setEducationBackground(userinfo.getEducationBackground());//个人学历信息
		current.setKidCount(userinfo.getKidCount());//获取子女情况
		current.setMarriage(userinfo.getMarriage());//婚姻情况
		current.setHouseCondition(userinfo.getHouseCondition());//住房条件
		
		//更改状态
		current.addState(BitstateUtil.OP_BASIC_INFO);
		//修改信息
		this.updateUserInfo(current);
	}
	//获取userinfo信息
	@Override
	public Userinfo getCurrentUserinfo() {
		// TODO Auto-generated method stub
		return get(Long.parseLong(UserContext.getContextUser().getId()+""));
	}
	
}