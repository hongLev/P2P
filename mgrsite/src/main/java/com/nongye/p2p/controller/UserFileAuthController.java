package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.UserFile;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.IUserFileService;
import com.nongye.p2p.util.JSONResult;

@Controller
public class UserFileAuthController {
	
	@Autowired
	private IUserFileService userFileService;
	
	@RequestMapping("userFileAuth")
	public String userFileAuth(@ModelAttribute("qo")RealauthQuery qo,Model model){
		//分页查询
		model.addAttribute("pageResult",this.userFileService.list(qo));
		return "/userFileAuth/list";
	}
	
	@RequestMapping("userFile_audit")
	@ResponseBody
	public JSONResult userFile_audit(UserFile userFile){
		JSONResult json=new JSONResult();
		try {
			this.userFileService.AuthUserFile(userFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		
		return json;
	}
	
}
