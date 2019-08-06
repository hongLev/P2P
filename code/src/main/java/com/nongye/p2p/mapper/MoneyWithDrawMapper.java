package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.MoneyWithDraw;
import com.nongye.p2p.quey.MoneyWithDrawQuery;

/**
 * 提现申请接口类
 * @author 89568
 *
 */
public interface MoneyWithDrawMapper {
	/**
	 * 添加
	 * @param money
	 */
	void insert(MoneyWithDraw money);
	/**
	 * 修改
	 * @param money
	 */
	void updateByPrimaryKey(MoneyWithDraw money);
	/**
	 * 根据主键id查找
	 * @param id
	 * @return
	 */
	MoneyWithDraw selectByPrimaryKey(Long id);
	/**
	 * 查询总个数
	 * @param query
	 * @return
	 */
	int queryForCount(MoneyWithDrawQuery query);
	/**
	 * 查询分页
	 * @param query
	 * @return
	 */
	
	List<MoneyWithDraw> query(MoneyWithDrawQuery query);
}
