package com.nongye.p2p.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.service.LogininfoService;
import com.nongye.p2p.util.JSONResult;

@Controller
public class adminController {

	@Autowired
	private LogininfoService logininfoService;
	
	
	@RequestMapping("adminLogin")
	@ResponseBody
	public JSONResult admin(String username,String password,HttpServletRequest request){
		JSONResult json=new JSONResult();
		Logininfo logininfo=logininfoService.loginUserService(username, password, Logininfo.USER_MANAGER, request.getLocalAddr());
		if(logininfo==null){
			json.setFlag(false);
			json.setMessage("用户或密码错误，请重试！");
		}
		return json;
	}
	
}
