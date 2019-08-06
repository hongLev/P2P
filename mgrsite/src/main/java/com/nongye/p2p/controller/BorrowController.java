package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.quey.BidRequestQueryObject;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.IBidRequestIdAuditHistoyService;
import com.nongye.p2p.service.IBidRequestService;
import com.nongye.p2p.service.IUserFileService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.RealauthService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;

/**
 * 后台审核借钱质料
 * @author 89568
 *
 */
@Controller
public class BorrowController {

	@Autowired
	private IBidRequestService bidRequestService;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private IBidRequestIdAuditHistoyService bidRequestIdautHistoyService;
	@Autowired
	private RealauthService realauthService;
	@Autowired
	private IUserFileService userFileService;
	/**
	 * 分页展示页面
	 * @param model
	 * @param query
	 * @return
	 */
	@RequestMapping("bidrequest_publishaudit_list")
	@RequireLogin
	public String list(Model model,@ModelAttribute("qo")BidRequestQueryObject query){
		/**插入待发布信息*/
		query.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
		/**调用*/
		model.addAttribute("pageResult", this.bidRequestService.list(query));
		
		return "/bidrequest/publish_audit";
	}
	
	/**
	 * 审核
	 */
	@RequestMapping("bidrequest_publishaudit")
	@RequireLogin
	@ResponseBody
	public JSONResult bidrequest_publishaudit(int id,int state,String remark){
		JSONResult json = new JSONResult();
		try {
			this.bidRequestService.bidRequestPublishAudit(id, state, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage("更新失败");
		}
		return json;
	}
	
	/**
	 * 查看详细情况
	 */
	@RequestMapping("borrow_info")
	@RequireLogin
	public String borrow_info(Model model,Integer id){
		//获取BidRequest信息
		BidRequest bidRequest =  this.bidRequestService.getBidRequestById(Long.parseLong(id+""));
		//获取Userinfo信息
		if(bidRequest!=null){
			model.addAttribute("bidRequest", bidRequest);
			model.addAttribute("userInfo", this.userinfoService.get(Long.parseLong(bidRequest.getCreateUser().getId()+"")));
			model.addAttribute("audits", this.bidRequestIdautHistoyService.listByBbidRequest(Long.parseLong(bidRequest.getCreateUser().getId()+"")));
			model.addAttribute("realAuth", this.realauthService.getByAppReal(bidRequest.getCreateUser().getId()));
			//创建RealauthQuery对象
			RealauthQuery qo = new RealauthQuery();
			qo.setApplierId(bidRequest.getCreateUser().getId());
			qo.setCurrentPage(1);
			qo.setPageSize(-1);
			model.addAttribute("userFiles", this.userFileService.list(qo).getListData());
			//model.addAttribute("UserFiles", )
		}
		return "/bidrequest/borrow_info";
	}
	/**
	 * 跳转到满标一审页面
	 * @param model
	 * @return
	 */
	@RequestMapping("bidrequest_audit1_list")
	@RequireLogin
	public String BidReqestAuditList(Model model,@ModelAttribute("qo")BidRequestQueryObject query){
		/**插入待发布信息*/
		query.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
		/**调用*/
		model.addAttribute("pageResult", this.bidRequestService.list(query));
		
		return "/bidrequest/audit1";
	}
	/**
	 * 
	 * 满标一审审核
	 */
	@RequestMapping("bidrequest_audit1")
	@RequireLogin
	@ResponseBody
	public JSONResult audit1(Integer id,Integer state,String remark){
		JSONResult json = new JSONResult();
		try {
			this.bidRequestService.bidRequestAudit1(id, state, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
	/**
	 * 满标二审页面
	 */
	@RequestMapping("bidrequest_audit2_list")
	@RequireLogin
	public String BidReqestAuditList2(Model model,@ModelAttribute("qo")BidRequestQueryObject query){
		/**插入待发布信息*/
		query.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
		/**调用*/
		model.addAttribute("pageResult", this.bidRequestService.list(query));
		
		return "/bidrequest/audit2";
	}
	
	/**
	 * 
	 * 满标一审审核
	 */
	@RequestMapping("bidrequest_audit2")
	@RequireLogin
	@ResponseBody
	public JSONResult audit2(Integer id,Integer state,String remark){
		JSONResult json = new JSONResult();
		try {
			this.bidRequestService.bidRequestAudit2(id, state, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
	}
}