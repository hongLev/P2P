package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.quey.RechargeQuery;
import com.nongye.p2p.service.ICompanyBankinfoService;
import com.nongye.p2p.service.IRechargeofFlineService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;

/**
 * 线下充值提交审核
 * @author 89568
 *
 */

@Controller 
public class RecharOfflineController {
	
	@Autowired
	private IRechargeofFlineService rechargeOfflineService;
	@Autowired
	private ICompanyBankinfoService companyBankinfoService;
	
	/**
	 * 充值分页页面
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping("rechargeOffline")
	@RequireLogin
	public String auditor(@ModelAttribute("qo") RechargeQuery query,Model model){
		model.addAttribute("pageResult", this.rechargeOfflineService.list(query));
		model.addAttribute("banks", this.companyBankinfoService.selectAll());
		return "rechargeoffline/list";
	}
	/**
	 * 
	 */
	@RequestMapping("rechargeOffline_audit")
	@ResponseBody
	@RequireLogin
	public JSONResult rechargeOffline_audit(Integer id,Integer state,String remark){
		JSONResult json = new JSONResult();
		try {
			this.rechargeOfflineService.rechargeOfflineAudit(id, state, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
}
