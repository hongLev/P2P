package com.nongye.p2p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.quey.BidRequestQueryObject;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IBidRequestIdAuditHistoyService;
import com.nongye.p2p.service.IBidRequestService;
import com.nongye.p2p.service.IUserFileService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.RealauthService;
import com.nongye.p2p.util.UserContext;

@Controller
public class indexController {

	@Autowired
	private IBidRequestService bidRequestService;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private RealauthService realauthService;
	@Autowired
	private IUserFileService userFileService;
	@Autowired
	private IAccountService accountService;

	@RequestMapping("index")
	public String index(Model model) {

		model.addAttribute("bidRequests", this.bidRequestService.main(5));

		return "main";
	}

	/**
	 * 跳转投资列表页面
	 */
	@RequestMapping("invest")
	public String invest() {
		/*
		 * model.addAttribute("bidRequests",this.bidRequestService.main(5));
		 */
		return "invest";
	}

	/**
	 * 获取投资列表数据  然后跳转list页面
	 */
	@RequestMapping("invest_list")
	public String investList(BidRequestQueryObject qo,Model model){
		//获取投资列表中显示所有投标中，审核中，还款中，已完成的标信息
		if(qo.getBidRequestState() == -1){
			//查询全部
			qo.setBidRequestStates(new int[] { BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK,
					BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
		}
		model.addAttribute("pageResult", this.bidRequestService.list(qo));

		
		return"invest_list";
	}
	/**
	 * 查看信息
	 */
	@RequestMapping("borrow_info")
	public String borrow_info(Integer id,Model model){
		// bidRequest;
		BidRequest bidRequest = this.bidRequestService.getBidRequestById(Long.parseLong(id + ""));
		if (bidRequest != null) {
			// userInfo
			Userinfo applier = this.userinfoService.get(Long.parseLong(bidRequest.getCreateUser().getId() + ""));
			// realAuth:借款人实名认证信息
			model.addAttribute("realAuth",
					this.realauthService.getRealAuthById(Integer.parseInt(applier.getRealAuthId() + "")));
			// userFiles:借款人风控材料
			RealauthQuery qo = new RealauthQuery();
			qo.setApplierId(applier.getId());
			qo.setPageSize(-1);
			qo.setCurrentPage(1);
			model.addAttribute("userFiles", this.userFileService.list(qo).getListData());

			model.addAttribute("bidRequest", bidRequest);
			model.addAttribute("userInfo", applier);

			if (UserContext.getContextUser() != null) {
				// self:当前用户是否是借款人自己
				if (UserContext.getContextUser().getId() == applier.getId()) {
					model.addAttribute("self", true);
				} else {
					// account
					model.addAttribute("self", false);
					model.addAttribute("account", this.accountService.get(Long.parseLong(UserContext.getContextUser().getId()+"")));
					model.addAttribute("sum", this.bidRequestService.sumBid(Long.parseLong(bidRequest.getId()+""),Long.parseLong(UserContext.getContextUser().getId()+"")));
				}
			} else {
				model.addAttribute("self", false);
			}
		}

		return "borrow_info";
	}
	
}
