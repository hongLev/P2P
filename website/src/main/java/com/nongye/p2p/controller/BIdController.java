package com.nongye.p2p.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nongye.p2p.service.IBidRequestService;
import com.nongye.p2p.util.JSONResult;
import com.nongye.p2p.util.RequireLogin;

/**
 * 投标金额控制层
 * @author 89568
 *
 */

@Controller 
public class BIdController {
	
	@Autowired
	private IBidRequestService bidRequestService;
	
	/**
	 * 投标
	 * @param bidRequestId
	 * @param amount
	 * @return
	 */
	@ResponseBody
	@RequireLogin
	@RequestMapping("borrow_bid")
	public JSONResult bid(Long bidRequestId,BigDecimal amount){
		JSONResult json = new JSONResult();
		try {
			this.bidRequestService.bid(bidRequestId, amount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setFlag(false);
			json.setMessage(e.getMessage());
		}
		return json;
				
	}
	
}
