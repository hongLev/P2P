package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.UserbankInfo;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.service.IUserBankInfoService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;

/**
 * 用户绑定银行卡Controller
 * @author 89568
 *
 */
@Controller
public class UserBankInfoController {

	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private IUserBankInfoService userBankInfoService;
	/**
	 * 跳转页面
	 * @param model
	 * @return
	 */
	@RequestMapping("bankInfo")
	@RequireLogin
	public String bankInfo(Model model){
		//先获取userInfo信息
		Userinfo userinfo = this.userinfoService.getCurrentUserinfo();
		if(userinfo.getIsBindBank()){
			//已绑定 
			model.addAttribute("bankInfo", this.userBankInfoService.selectById(Long.parseLong(userinfo.getId()+"")));
			return "bankInfo_result";
		}else{
			//未绑定
			model.addAttribute("userinfo", userinfo);
			return "bankInfo";
		}
	}
	/**
	 * 绑定银行卡
	 * @param userBank
	 * @return
	 */
	@RequestMapping("bankInfo_save")
	@RequireLogin
	@ResponseBody
	public JSONResult bankInfo_save(UserbankInfo userBank){
		JSONResult json = new JSONResult();
		try {
			userBank.setLogininfo(UserContext.getContextUser());
			userBank.setAccountName(UserContext.getContextUser().getUserName());
			this.userBankInfoService.add(userBank);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage("绑定失败");
		}
		return json;
	}
}
