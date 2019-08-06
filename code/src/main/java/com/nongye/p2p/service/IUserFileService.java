package com.nongye.p2p.service;

import java.util.List;

import com.nongye.p2p.domain.UserFile;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.util.PageResult;

/**
 * 风控质料业务逻辑层
 * @author 89568
 *
 */
public interface IUserFileService {
	/**
	 * 风控质料申请新增
	 */
	public void save(String imageName);
	/**
	 * 获取当前用户的风控资料
	 */
	public List<UserFile> getListUserFileById(Long id,boolean type);
	
	/**
	 * 提交材料
	 */
	public void updateUserFile(Long[] id,Long[] type);
	/**
	 * 后台：分页显示 
	 */
	public PageResult list(RealauthQuery User_Auth);
	/**
	 * 审核材料分
	 * @param userFile
	 */
	public void AuthUserFile(UserFile userFile);
	
	/**
	 * 删除照片
	 */
	public void deleteUserFileImg(Long id);
}
