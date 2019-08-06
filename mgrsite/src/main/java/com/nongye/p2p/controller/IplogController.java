package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nongye.p2p.quey.IplogObjectQuery;
import com.nongye.p2p.service.IplogService;

@Controller
public class IplogController {
	
	@Autowired
	private IplogService iplogService;
	
	@RequestMapping("ipLog")
	public String list(@ModelAttribute("qo")IplogObjectQuery qo,Model model){
		//1:获取所有的 登录日志信息 存放到model中
		model.addAttribute("pageResult", iplogService.list(qo)); 
		//2:跳转到登录日志页
		return "/ipLog/list";
	}
}
