package com.nongye.p2p.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.nongye.p2p.service.LogininfoService;

/**
 * 初始化后台管理监听器
 * @author 89568
 *
 */
@Component
public class LogininfoListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private LogininfoService  logininfoService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		//直接运行
		this.logininfoService.insertAdminService();
	}

}
