package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.SystemDictionaryItem;
import com.nongye.p2p.mapper.SystemDictionaryMapper;
import com.nongye.p2p.quey.SystemDictionaryItemQuery;
import com.nongye.p2p.service.SystemDictionaryLtemService;
import com.nongye.p2p.util.JSONResult;


@Controller
public class systemDictionaryltemController {

	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	@Autowired
	private SystemDictionaryLtemService systemdictionaryItemService;
	
	/**分页查询*/
	@RequestMapping("systemDictionaryItem_list")
	public String systemDictionaryItem_list(@ModelAttribute("qo")SystemDictionaryItemQuery quer,Model model){
		model.addAttribute("system", this.systemDictionaryMapper.selectAll());
		model.addAttribute("pageResult", this.systemdictionaryItemService.selectSystem(quer));
		return "/systemdic/systemDictionaryItem_list";
	}
	
	/**添加/修改*/
	@RequestMapping("systemDictionaryItem_update")
	@ResponseBody
	public JSONResult systemDictionaryItem_update(SystemDictionaryItem of){
		JSONResult json=new JSONResult();
		try {
			systemdictionaryItemService.AddSystem(of);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
	
}
