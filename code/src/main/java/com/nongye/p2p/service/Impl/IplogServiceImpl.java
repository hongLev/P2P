package com.nongye.p2p.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Iplog;
import com.nongye.p2p.mapper.IplogMapper;
import com.nongye.p2p.quey.IplogObjectQuery;
import com.nongye.p2p.service.IplogService;
import com.nongye.p2p.util.PageResult;

@Service
public class IplogServiceImpl implements IplogService {

	@Autowired
	private IplogMapper iplogMapper;
	
	
	@Override
	public PageResult list(IplogObjectQuery iplog) {
		// TODO Auto-generated method stub
		//获取总记录数
		int count=this.iplogMapper.getCount(iplog);
		//判断是否有数据
		if(count>0){
			List<Iplog> list=this.iplogMapper.getAll(iplog);
			//分页存入数据
			return new PageResult(list, count, iplog.getCurrentPage(),iplog.getPageSize());
		}
		//未查询到数据
		return PageResult.empty(iplog.getPageSize());
	}

}
