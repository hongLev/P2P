package com.nongye.p2p.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.service.LogininfoService;
import com.nongye.p2p.util.JSONResult;

/**
 * 用户注册控制层
 * @author 89568
 *
 */
@Controller
public class RegisterController {
	
	/**依赖logininfoService*/
	@Autowired
	private LogininfoService logininfoservice;
	
	
	@RequestMapping("register")
	@ResponseBody
	public JSONResult register(String username,String password){
		JSONResult json=new JSONResult();
			try {
				logininfoservice.insertLoginService(username, password);
			} catch (RuntimeException e) {
				//错误的话
				json.setFlag(false);
				json.setMessage(e.getMessage());
				
			}
		return json;
	}
	
	@RequestMapping("checkUsername")
	@ResponseBody
	public boolean checkUsername(String username){
		
		try {
			//验证是用户名是否纯在
			return !logininfoservice.checkUsernameService(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**登入*/
	@RequestMapping("login")
	@ResponseBody
	public JSONResult login(String username,String password,HttpServletRequest request){
		JSONResult json=new JSONResult();
		//获取用户数据
		Logininfo loginUser=logininfoservice.loginUserService(username, password, Logininfo.USER_CLIENT,request.getRemoteAddr());
		if(loginUser==null){
			//登入失败
			json.setFlag(false);
			json.setMessage("用户或密码错误");
		}
		return json;
		
	}
	
	
}
