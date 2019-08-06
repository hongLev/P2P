package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理 异步请求的控制层
 * @author Administrator
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;

/**
 * 发送手机验证码
 * @param phoneNumber
 * @return
 */


@Controller
public class AjasController {

	@Autowired
	private IUserinfoService userinfoService;
	
	@RequireLogin
	@RequestMapping("sendVerifyCode")
	@ResponseBody
	public JSONResult sendVerifyCode(String phoneNumber){
		System.out.println("手机号:"+phoneNumber);
		JSONResult json=new JSONResult();
		//调用方法发送验证码
		try {
			this.userinfoService.sendVerifyCode(phoneNumber);
		} catch (Exception e) {
			json.setFlag(false);
			json.setMessage("发送过于频繁");
		}
		
		return json;
	}
}
