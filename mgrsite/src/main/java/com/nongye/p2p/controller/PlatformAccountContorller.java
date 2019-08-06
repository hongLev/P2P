package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.CompanyBankinfo;
import com.nongye.p2p.quey.CompanyBankinfoQuery;
import com.nongye.p2p.service.ICompanyBankinfoService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;

/**
 * 平台账号controller
 * @author 89568
 *
 */

@Controller
public class PlatformAccountContorller {
	@Autowired
	private ICompanyBankinfoService companyBankinfoService;
	
	/**
	 * 平台账号管理页面
	 * @param model
	 * @return
	 */
	@RequireLogin
	@RequestMapping("companyBank_list")
	public String companyBank_list(Model model,CompanyBankinfoQuery query){
		try {
			model.addAttribute("pageResult", this.companyBankinfoService.list(query));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "platformbankinfo/list";
	}
	/**
	 * 修改添加共通方法
	 */
	@RequireLogin
	@ResponseBody
	@RequestMapping("companyBank_update")
	public JSONResult companyBank_update(CompanyBankinfo com){
		JSONResult json = new JSONResult();
		try {
			if(com.getId()==0){
				//添加
				this.companyBankinfoService.AddCompanyBankinfo(com);
			}{
				//修改
				this.companyBankinfoService.UpdateCompany(com);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(true);
			json.setMessage("修改失败，请稍后尝试");
		}
		
		return json;
	}
	
	
}
