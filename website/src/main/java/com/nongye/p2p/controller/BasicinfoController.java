package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.SystemDictionaryLtemService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;
/**
 * 个人资料控制层
 * @author 89568
 *
 */
@Controller
public class BasicinfoController {
	
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private SystemDictionaryLtemService systemDictionaryItemService;
	/**
	 * 跳转填写个人资料
	 * @return
	 */
	@RequireLogin
	@RequestMapping("basicInfo")
	public String basicInfo(Model model){
		
		//1获取userinfo信息
		model.addAttribute("userinfo", userinfoService.get(Long.parseLong(UserContext.getContextUser().getId()+"")));
		//2.获取个人学历信息
		model.addAttribute("educationBackground", systemDictionaryItemService.getBySnAll("educationBackground"));
		//3.获取月收入信息
		model.addAttribute("incomeGrade", systemDictionaryItemService.getBySnAll("incomeGrade"));
		//4.获取婚姻情况
		model.addAttribute("marriage", systemDictionaryItemService.getBySnAll("marriage"));
		//5.获取子女情况
		model.addAttribute("kidCount", systemDictionaryItemService.getBySnAll("kidCount"));
		//6.获取住房条件
		model.addAttribute("houseCondition", systemDictionaryItemService.getBySnAll("houseCondition"));
		return "userInfo";
	}
	
	@RequestMapping("basicInfo_save")
	@ResponseBody
	@RequireLogin
	public JSONResult basicInfo_save(Userinfo userinfo){
		JSONResult json=new JSONResult();
		try {
			userinfoService.sendBasicInfo(userinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	回传信息
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		
		return json;	
	}
	/**
	 * 
	 */
	
}
