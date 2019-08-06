package com.nongye.p2p.mapper;

import java.util.List;
import java.util.Map;

import com.nongye.p2p.domain.Userinfo;


public interface UserinfoMapper {

	int insert(Userinfo record);

	Userinfo selectByPrimaryKey(Long id);

	int updateByPrimaryKey(Userinfo record);

	List<Map<String, Object>> autocomplate(String keyword);
}