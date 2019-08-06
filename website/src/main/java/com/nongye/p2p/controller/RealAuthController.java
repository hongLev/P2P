package com.nongye.p2p.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nongye.p2p.domain.Realauth;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.RealauthService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UploadUtil;

@Controller
public class RealAuthController {

	@Autowired
	private javax.servlet.ServletContext ServletContext;
	@Autowired
	public IUserinfoService userinfoService;
	@Autowired
	public RealauthService realauthService;
	
	//实名认证页面
	@RequestMapping("realAuth")
	@RequireLogin
	public String realAuth(Model model){
		//获取当前用户对象
		Userinfo current=userinfoService.getCurrentUserinfo();
		if(current!=null&&current.getIsRealAuth()){
			//认证成功
			model.addAttribute("auditing", false);
			model.addAttribute("realAuth",this.realauthService.getRealAuthById(Integer.parseInt(current.getRealAuthId() + "")));
			return "realAuth_result";
		} else {
			if (current != null && current.getRealAuthId() != null) {
				model.addAttribute("auditing", true);
				return "realAuth_result";
			}
			return "realAuth";
		}

	}
	
	@RequestMapping("realAuthUpload")
	@ResponseBody
	public String realAuthUpload(MultipartFile file){
		//根据相对位置访问绝对路劲
		String basePath=this.ServletContext.getRealPath("/upload");
		System.out.println(basePath);
		String fileName=UploadUtil.upload(file, basePath);
		System.out.println("fileName");
		return "/upload/"+fileName;
	}
	//添加
	@RequestMapping("realAuth_save")
	@ResponseBody
	@RequireLogin
	public JSONResult realAuth_save(Realauth realauth){
		JSONResult json = new JSONResult();
		try {
			
			this.realauthService.AddRealauth(realauth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
