package com.nongye.p2p.service;

import java.util.List;

import com.nongye.p2p.domain.CompanyBankinfo;
import com.nongye.p2p.quey.CompanyBankinfoQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 平台账号信息业务接口层
 */
public interface ICompanyBankinfoService {
	/**
	 * 添加平台账号信息
	 */
	public void AddCompanyBankinfo(CompanyBankinfo companybankinfo);
	/**
	 * 分页查询
	 */
	public PageResult list(CompanyBankinfoQuery query);
	/**
	 * 
	 * 修改
	 */
	public void UpdateCompany(CompanyBankinfo companybankinfo);
	/**
	 * 查询所有
	 */
	
	public List<CompanyBankinfo> selectAll();
}
