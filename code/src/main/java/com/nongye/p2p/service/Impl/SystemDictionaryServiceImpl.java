package com.nongye.p2p.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.SystemDictionary;
import com.nongye.p2p.mapper.SystemDictionaryMapper;
import com.nongye.p2p.quey.SystemDictionaryQuery;
import com.nongye.p2p.service.SystemDictionaryService;
import com.nongye.p2p.util.PageResult;
@Service
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	
	//添加
	@Override
	public void addSystemDictionary(SystemDictionary systemDomain) {
		// TODO Auto-generated method stub
		
		try {
			if(systemDomain.getId()==0){
				this.systemDictionaryMapper.insert(systemDomain);
			}else{
				this.systemDictionaryMapper.updateByPrimaryKey(systemDomain);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("添加失败");
			
		}
	}
	//查询
	@Override
	public PageResult selectSystemDictionary(SystemDictionaryQuery system) {
		// TODO Auto-generated method stub
		//查询总记录数
		try {
			int count=systemDictionaryMapper.queryForCount(system);
			if(count>0){
				List<SystemDictionary> list=systemDictionaryMapper.query(system);
				//存入分页数据
				return new PageResult(list, count, system.getCurrentPage(), system.getPageSize());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageResult.empty(system.getPageSize());
	}

}
