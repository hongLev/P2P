package com.nongye.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nongye.p2p.domain.UserFile;
import com.nongye.p2p.quey.RealauthQuery;

/**
 * 用户认证材料接口数据层
 * @author 89568
 *
 */
public interface UserFileMapper {

	/**添加对象*/
	void insert(UserFile userFile);
	
	/**
	 * 根据用户Id  获取用户认证材料信息
	 */
	List<UserFile> getUserFileHasType(@Param("userId")Long userId,@Param("type") boolean type);
	
	/**
	 * 根据材料Id进行修改
	 */
	void updateByPrimaryKey(UserFile ufs);
	/**
	 * 获取UserFile数据
	 * @param id
	 * @return
	 */
	UserFile selectByPrimaryKey(Long id);
	/**
	 * 统计多少条数据
	 * @param User_Auth
	 * @return
	 */
	int count(RealauthQuery User_Auth);
	/**
	 * 查询数据并进行分页
	 * @param User_Auth
	 * @return
	 */
	List<UserFile> query(RealauthQuery User_Auth);
	/**
	 * 根据Id删除照片
	 * @param userId
	 */
	void delImg(@Param("userId")Long userId);
}
