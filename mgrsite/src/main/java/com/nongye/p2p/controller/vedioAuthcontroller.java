package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.base.BaseAuditDomain;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.quey.VedioauthQuery;
import com.nongye.p2p.service.LogininfoService;
import com.nongye.p2p.service.VedioauthService;
import com.nongye.p2p.util.JSONResult;
/**
 * 视频认证控制层
 * @author 89568
 *
 */
@Controller
public class vedioAuthcontroller {
	
	@Autowired
	private VedioauthService vodioauthService;
	@RequestMapping("vedioAuth")
	public String vedioAuth(@ModelAttribute("qo")VedioauthQuery qo,Model model){
		try {
			model.addAttribute("pageResult", this.vodioauthService.list(qo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/vedioAuth/list";
	}
	//验证是否有用户
	@RequestMapping("checkUsernameVodie")
	@ResponseBody
	public boolean checkUsername(String loginInfoDisplay){
		return this.vodioauthService.checkUsernameService(loginInfoDisplay);
	}
	//审核
	@RequestMapping("vedioAuth_audit")
	@ResponseBody
	public JSONResult vedioAuth_audit(BaseAuditDomain base,String loginInfoDisplay){
		JSONResult json=new JSONResult();
		try {
			this.vodioauthService.AuditRealauth(base,loginInfoDisplay);
		} catch (Exception e) {
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
}	
