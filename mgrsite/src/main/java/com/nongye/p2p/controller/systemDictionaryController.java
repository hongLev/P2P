package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.SystemDictionary;
import com.nongye.p2p.quey.SystemDictionaryQuery;
import com.nongye.p2p.service.SystemDictionaryService;
import com.nongye.p2p.util.JSONResult;

@Controller
public class systemDictionaryController {
	
	@Autowired
	private SystemDictionaryService systemDictionaryService;
	
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("systemDictionary_list")
	public String systemDictionary_list(SystemDictionaryQuery sysquery,Model model){
		/*SystemDictionaryQuery sysquery=new SystemDictionaryQuery();
		sysquery.setKeyword(keyword);*/
		try {
			model.addAttribute("system",systemDictionaryService.selectSystemDictionary(sysquery));
			model.addAttribute("qo", sysquery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/systemdic/systemDictionary_list";
	}
	//添加操作
	@RequestMapping("systemDictionary_update")
	@ResponseBody
	public JSONResult systemDictionary_update(SystemDictionary SystemAdd){
		JSONResult json=new JSONResult();
		try {
			systemDictionaryService.addSystemDictionary(SystemAdd);
		} catch (Exception e) {
			json.setFlag(false);
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage()); 
		}
		
		return json;
	}
	

}
