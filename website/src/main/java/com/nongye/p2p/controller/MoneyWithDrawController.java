package com.nongye.p2p.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.quey.MoneyWithDrawQuery;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IMoneyWithDrawService;
import com.nongye.p2p.service.IUserBankInfoService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;

/**
 * 提现申请
 * @author 89568
 *
 */
@Controller
public class MoneyWithDrawController {

	@Autowired
	private IAccountService account;
	@Autowired
	private IUserBankInfoService userBankInfoService;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private IMoneyWithDrawService moneyWithDrawService;
	/**
	 * 提现申请页面
	 * @param model
	 * @return
	 */
	@RequestMapping("moneyWithdraw")
	@RequireLogin
	public String moneyWithdraw(Model model){
		
		model.addAttribute("haveProcessing", this.userinfoService.getCurrentUserinfo().getHasWithdrawProcess());
		model.addAttribute("account", this.account.get(Long.parseLong(UserContext.getContextUser().getId()+"")));
		model.addAttribute("bankInfo", this.userBankInfoService.selectById(Long.parseLong(UserContext.getContextUser().getId()+"")));
		return "moneyWithdraw_apply";
	}
	/**
	 * 提交申请
	 */
	@RequestMapping("moneyWithdraw_apply")
	@RequireLogin
	@ResponseBody
	public JSONResult moneyWithdraw_apply(BigDecimal moneyAmount){
		JSONResult json = new JSONResult();
		//System.out.println(moneyAmount);
		try {
			this.moneyWithDrawService.apply(moneyAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
	
}

