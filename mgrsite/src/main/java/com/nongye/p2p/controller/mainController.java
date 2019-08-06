package com.nongye.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;

@Controller
public class mainController {
	@RequireLogin
	@RequestMapping("main")
	public String loginAdmin(Model model){
		
		
		return "main";
	}
	
}
