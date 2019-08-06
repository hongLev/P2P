package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.Realauth;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.RealauthService;
import com.nongye.p2p.util.JSONResult;

@Controller
public class adminRealauthController {

	@Autowired
	private RealauthService realauthService;
	
	@RequestMapping("realAuth")
	public String realAuth(@ModelAttribute("qo")RealauthQuery qo,Model model){
		model.addAttribute("pageResult", this.realauthService.list(qo));
		return "/realAuth/list";
	}
	//审核
	@RequestMapping("realAuth_audit")
	@ResponseBody
	public JSONResult realAuth_audit(Realauth realauth){
		JSONResult json=new JSONResult();
		
		realauthService.AuditRealauth(realauth);
		
		return json;
	}
}
