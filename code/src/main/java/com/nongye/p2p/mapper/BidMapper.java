package com.nongye.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.nongye.p2p.domain.Bid;

/**
 * 借款人数据接口层
 * @author 89568
 *
 */
public interface BidMapper {
	/**
	 * 添加
	 */
	void insert(Bid bid);
	
	/**
	 * 查询记录投标成功的金额数据
	 */
	List<Bid> selectByType(@Param("bidrequestid")Long bidrequestid,@Param("bidUserid")Long bidUserid);
}
