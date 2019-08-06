package com.nongye.p2p.mapper;

import java.util.List;

import com.nongye.p2p.domain.SystemDictionaryItem;
import com.nongye.p2p.quey.SystemDictionaryItemQuery;




public interface SystemDictionaryItemMapper {

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SystemDictionaryItem record);
    
    /**
     * 分页相关的查询
     */
    List<SystemDictionaryItem> query(SystemDictionaryItemQuery query);
    
    /**
     * 统计总记录数
     */
    int queryForCount(SystemDictionaryItemQuery query);
    
    /**
     * 根据数据字典分类sn查询明细
     * @param sn
     * @return
     */
	List<SystemDictionaryItem> listByParentSn(String sn);
}