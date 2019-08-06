package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.BidRequest;
import com.nongye.p2p.domain.CompanyBankinfo;
import com.nongye.p2p.quey.CompanyBankinfoQuery;

/**
 * 平台账号数据接口层
 * @author 89568
 *
 */
public interface CompanyBankinfoMapper {
	/**
	 * 添加
	 */
	void insert(CompanyBankinfo companybankinfo);
	/**
	 * 查询总记录数
	 * @param query
	 * @return
	 */
	int queryCount(CompanyBankinfoQuery query);
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	List<CompanyBankinfo> pageList(CompanyBankinfoQuery query);
	/**
	 * 根据主键ID查找
	 */
	BidRequest selectByPrimaryKey(Long id);
	/**
	 * 修改
	 * @param bidRequest
	 * @return
	 */
	int updateByPrimaryKey(CompanyBankinfo companybankinfo);
	
	/**
	 * 查询所有
	 */
	List<CompanyBankinfo> list();
}
