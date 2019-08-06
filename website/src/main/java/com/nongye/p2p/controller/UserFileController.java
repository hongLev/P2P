package com.nongye.p2p.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nongye.p2p.domain.UserFile;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.service.IUserFileService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.SystemDictionaryLtemService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UploadUtil;


@Controller
public class UserFileController {

	@Autowired
	private IUserFileService userFileService;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private javax.servlet.ServletContext ServletContext;
	@Autowired
	private SystemDictionaryLtemService systemDictionaryItemService;
	
	@RequestMapping("/userFile")
	@RequireLogin
	public String userFile(Model model,HttpServletRequest request){
		//获取当前的userinfo信息
		Userinfo cureent = this.userinfoService.getCurrentUserinfo();
		
		List<UserFile> list=this.userFileService.getListUserFileById(Long.parseLong(cureent.getId()+""), false);
		if(list!=null&&list.size()>0){
			// 提交了材料但是没有材料类型
			//获取数据字典明细
			model.addAttribute("userFlieType", this.systemDictionaryItemService.getBySnAll("userFlieType"));
			//获取照片回显
			model.addAttribute("UserFile", list);
			return "userFiles_commit";
			
		}else{
			//第一次点击
			
			model.addAttribute("sessionid", request.getSession().getId());
			model.addAttribute("type", this.userFileService.getListUserFileById(Long.parseLong(cureent.getId()+""), true));
			return "userFiles";
		}
		
		
	}
	/**
	 * 风控材料添加
	 * @param file
	 */
	@RequestMapping("userFileUpload")
	@ResponseBody
	public void userFileUpload(MultipartFile file){
		// 根据相对路径获取绝对路径
		String basePath=this.ServletContext.getRealPath("/upload");
		// 调用工具类完成上传
		String fileName=UploadUtil.upload(file, basePath);
		String ObjectName="/upload/"+fileName;
		this.userFileService.save(ObjectName);
		// 完成风控材料添加操作
	}
	/**
	 * 提交风控材料审核
	 */
	@RequestMapping("userFile_selectType")
	@ResponseBody
	public JSONResult userFile_selectType(Long[] id,Long[] fileType){
		
		this.userFileService.updateUserFile(id, fileType);
		return new JSONResult();
	}
	
	/**
	 * 删除审核失败照片照片
	 */
	@RequestMapping("updateImg")
	@ResponseBody
	public ModelAndView updateImg(ModelAndView mv,Long imgId){
		this.userFileService.deleteUserFileImg(imgId);
		//重定向。删除
		mv.setViewName("forward:userFile.do");
		
		return mv;
	}
}
