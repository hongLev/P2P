package com.nongye.p2p.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.SystemDictionaryItem;
import com.nongye.p2p.mapper.SystemDictionaryItemMapper;
import com.nongye.p2p.mapper.SystemDictionaryMapper;
import com.nongye.p2p.quey.SystemDictionaryItemQuery;
import com.nongye.p2p.quey.SystemDictionaryQuery;
import com.nongye.p2p.service.SystemDictionaryLtemService;
import com.nongye.p2p.util.PageResult;
@Service
public class SystemDictionaryItemServiceImpl implements SystemDictionaryLtemService {

	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItmeMapper; 
	
	
	@Override
	public void AddSystem(SystemDictionaryItem add) {
		// TODO Auto-generated method stub
		try {
			if(add.getId()>0&&add!=null){
				//修改
				systemDictionaryItmeMapper.updateByPrimaryKey(add);
			}else{
				//添=加
				systemDictionaryItmeMapper.insert(add);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("失败，请重试!");
		}
	}
	/**
	 * 分页查询
	 */
	@Override
	public PageResult selectSystem(SystemDictionaryItemQuery Sysquery) {
		try {
			int count = systemDictionaryItmeMapper.queryForCount(Sysquery);
			if(count>0){
				List<SystemDictionaryItem> list= systemDictionaryItmeMapper.query(Sysquery);
				return new PageResult(list, count, Sysquery.getCurrentPage(), Sysquery.getPageSize());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PageResult.empty(Sysquery.getPageSize());
	}
	//获取数据字典明细
	public List<SystemDictionaryItem> getBySnAll(String sn){
		//返回出去
		return this.systemDictionaryItmeMapper.listByParentSn(sn);
	}
	
	public SystemDictionaryItem getByidSystemItem(Long id){
		return this.systemDictionaryItmeMapper.selectByPrimaryKey(id);
	}
}
