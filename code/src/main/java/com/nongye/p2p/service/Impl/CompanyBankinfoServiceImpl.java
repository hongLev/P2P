package com.nongye.p2p.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.CompanyBankinfo;
import com.nongye.p2p.mapper.CompanyBankinfoMapper;
import com.nongye.p2p.quey.CompanyBankinfoQuery;
import com.nongye.p2p.service.ICompanyBankinfoService;
import com.nongye.p2p.util.PageResult;
@Service
public class CompanyBankinfoServiceImpl implements ICompanyBankinfoService {
	
	@Autowired
	private CompanyBankinfoMapper companyBankinfoMapper;
	
	/**
	 *添加
	 */
	@Override
	public void AddCompanyBankinfo(CompanyBankinfo companybankinfo) {
		// TODO Auto-generated method stub
		this.companyBankinfoMapper.insert(companybankinfo);
	}
	/**
	 * 分页查询展示页面
	 */
	@Override
	public PageResult list(CompanyBankinfoQuery query) {
		int count = this.companyBankinfoMapper.queryCount(query);
		if(count>0){
			List<CompanyBankinfo> list = this.companyBankinfoMapper.pageList(query);
			return new PageResult(list, count, query.getCurrentPage(), query.getPageSize());
		}
		return PageResult.empty(query.getPageSize());	
	}
	/**
	 * 修改
	 */
	@Override
	public void UpdateCompany(CompanyBankinfo companybankinfo) {
		// TODO Auto-generated method stub
		this.companyBankinfoMapper.updateByPrimaryKey(companybankinfo);
	}
	@Override
	public List<CompanyBankinfo> selectAll() {
		// TODO Auto-generated method stub
		return this.companyBankinfoMapper.list();
	}

}
