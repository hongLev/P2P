package com.nongye.p2p.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nongye.p2p.domain.AccountFlow;

/**
 * 账户流水数据接口类
 * @author 89568
 *
 */
public interface AccountFlowMapper {
	/**
	 * 添加
	 * @param accountFlow
	 */
	void insert(AccountFlow accountFlow);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	AccountFlow selectByPrimaryKey(Long id);
	
	
}
