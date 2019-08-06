package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;

@Controller
public class personalController {
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUserinfoService userinfoService;
	
	//添加ID
	@RequireLogin
	@RequestMapping("personal")
	public String login(Model model){
		//获取Session里logininfo登入信息
		Logininfo logininfo=UserContext.getContextUser();
		//存入到model当中
		model.addAttribute("account", this.accountService.get(Long.parseLong(logininfo.getId()+"")));//根据获取id查询account信息
		model.addAttribute("userinfo", this.userinfoService.get(Long.parseLong(logininfo.getId()+"")));//根据获取id查询userinfo信息
		return "personal";
	}
	/**
	 * 绑定手机
	 * @param phoneNumber
	 * @param verifyCode
	 * @return
	 */
	@RequireLogin
	@RequestMapping("bindPhone")
	@ResponseBody
	public JSONResult bindPhone(String phoneNumber,String verifyCode){
		JSONResult json=new JSONResult();
		try {
			this.userinfoService.bindPhone(phoneNumber, verifyCode);
		} catch (RuntimeException e) {
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
	/**
	 * 邮箱认证
	 */
	@RequireLogin
	@RequestMapping("sendEmail")
	@ResponseBody
	public JSONResult sendEmail(String email){
		JSONResult json=new JSONResult();
		
		try {
			this.userinfoService.sendEmail(email);
		} catch (RuntimeException e) {
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		
		return json;
	}

	@RequestMapping("bindEmail")
	public String bindEmail(String key,Model model){
		try {
			this.userinfoService.bindEmail(key);
		    model.addAttribute("success", true);
		} catch (RuntimeException e) {
			 model.addAttribute("success", false);
			 model.addAttribute("msg", e.getMessage());
		}
		return "checkmail_result";

	}
	
}
