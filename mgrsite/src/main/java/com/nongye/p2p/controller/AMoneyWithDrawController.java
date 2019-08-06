package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.quey.MoneyWithDrawQuery;
import com.nongye.p2p.service.IMoneyWithDrawService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;

@Controller
public class AMoneyWithDrawController {

	@Autowired
	private IMoneyWithDrawService moneyWithDrawService;
	
	/**
	 * 后台审核页面
	 */
	@RequestMapping("moneyWithdraw")
	@RequireLogin
	public String moneyWithdraw(@ModelAttribute("qo") MoneyWithDrawQuery query,Model model){
		model.addAttribute("pageResult", this.moneyWithDrawService.query(query));
		return "moneywithdraw/list";
	}
	/**
	 * 后台审核
	 */
	@RequestMapping("moneyWithdraw_audit")
	@RequireLogin
	@ResponseBody
	public JSONResult autid(Integer id,Integer state,String remark){
		JSONResult json = new JSONResult();
		try {
			this.moneyWithDrawService.audit(Long.parseLong(id+""), remark, state);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
		}
		return json;
	}
}
