package com.nongye.p2p.controller;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 个人借款控制层
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nongye.p2p.domain.BidConst;
import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.service.IAccountService;
import com.nongye.p2p.service.IBidRequestService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.util.RequireLogin;
import com.nongye.p2p.util.UserContext;

@Controller
public class BorrowController {
	@Autowired
	public IUserinfoService userinfoService;
	@Autowired
	public IAccountService accountSercice;
	@Autowired
	public IBidRequestService bidRequestService;
	
	@RequireLogin
	@RequestMapping("borrow")
	public String borrow(Model model){
		//1.从session获取用户登入信息
		Logininfo current=UserContext.getContextUser();
		//2:判断
		if(current==null){
			return "redirect:borrow.html";
		}
		//获取userinfo 信息
		model.addAttribute("userinfo",this.userinfoService.get(Long.parseLong(current.getId()+"")));
		//获取account 信息
		model.addAttribute("account", this.accountSercice.get(Long.parseLong(current.getId()+"")));
		//我要借款风控应该要多少分
		model.addAttribute("borrowBasicSorce", BidConst.BASE_BORROW_SCORE);
		return "borrow";
	}
	/**
	 * 借款跳转
	 * @return
	 */
	@RequireLogin
	@RequestMapping("borrowInfo")
	public String borrowInfo(Model model){
		//判断是否是第一次
		Logininfo logininfo = UserContext.getContextUser();
		//验证是否满足条件
		if(this.bidRequestService.canApplyBidRequest(logininfo)){
			//封装数据  传入页面
			//最小借款金额 500  
			model.addAttribute("minBidRequestAmount", BidConst.SMALLEST_BIDREQUEST_AMOUNT);
			//根据当前登录的用户id获取account信息
			model.addAttribute("account", this.accountSercice.get(Long.parseLong(logininfo.getId()+"")));
			//最小投标金额
			model.addAttribute("minBidAmount", BidConst.SMALLEST_BID_AMOUNT);

			return "borrow_apply";
		}
		//已投标
		return "borrow_apply_result";
	}
	
	/**
	 * 申请借款
	 */
	@RequireLogin
	@RequestMapping("borrow_apply")
	public ModelAndView Apply(BidRequest bidrequest,ModelAndView mv){
		this.bidRequestService.apply(bidrequest);
		mv.setViewName("forward:borrowInfo.do");
		return mv; 
	}
}
