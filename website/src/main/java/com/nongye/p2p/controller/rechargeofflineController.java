package com.nongye.p2p.controller;

import javax.xml.ws.soap.Addressing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.RechargeofFline;
import com.nongye.p2p.quey.RechargeQuery;
import com.nongye.p2p.service.ICompanyBankinfoService;
import com.nongye.p2p.service.IRechargeofFlineService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;
/**
 * 账户充值Controller 
 * @author 89568
 *
 */
@Controller
public class rechargeofflineController {
	@Autowired
	private ICompanyBankinfoService conpanyBankinfo;
	@Autowired
	private IRechargeofFlineService rechargeOffline;
	
	/**
	 * 展示页面
	 * @param model
	 * @return
	 */
	@RequireLogin
	@RequestMapping("recharge")
	public String recharge(Model model){
		model.addAttribute("banks", this.conpanyBankinfo.selectAll());
		return "recharge";
	}
	/**
	 * 提交充值申请
	 */
	@ResponseBody
	@RequireLogin
	@RequestMapping("recharge_save")
	public JSONResult rechargeForm(RechargeofFline rechargeofFline){
		JSONResult json = new JSONResult();
		try {
			this.rechargeOffline.insert(rechargeofFline);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
	/**
	 * 充值申请审核
	 */
	@RequestMapping("recharge_list")
	@RequireLogin
	public String recharge_list(Model model,@ModelAttribute("qo") RechargeQuery query){
		model.addAttribute("pageResult", this.rechargeOffline.listByUserId(query,UserContext.getContextUser().getId()));
		return "recharge_list";
	}
	
}
