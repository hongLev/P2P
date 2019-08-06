package com.nongye.p2p.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.SystemDictionaryItem;
import com.nongye.p2p.domain.UserFile;
import com.nongye.p2p.domain.Userinfo;
import com.nongye.p2p.mapper.UserFileMapper;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.IUserFileService;
import com.nongye.p2p.service.IUserinfoService;
import com.nongye.p2p.service.SystemDictionaryLtemService;
import com.nongye.p2p.util.PageResult;
import com.nongye.p2p.util.UserContext;
@Service
public class UserFileServiceImpl implements IUserFileService {
	@Autowired
	private UserFileMapper userFileMapper;
	@Autowired
	private SystemDictionaryLtemService systemDictionaryItemService;
	@Autowired
	private IUserinfoService userInfoService;
	
	@Override
	public void save(String imageName) {
		// TODO Auto-generated method stub
		//新增
		UserFile user = new UserFile();
		user.setApplier(UserContext.getContextUser());//当前用户
		user.setImage(imageName);//图片
		user.setApplyTime(new Date());//新增时间 
		user.setState(UserFile.STATE_NORMAL);//状态正常
		this.userFileMapper.insert(user);
	}
	//根据当前用户id获取他的风控资料。
	@Override
	public List<UserFile> getListUserFileById(Long id, boolean type) {
		// TODO Auto-generated method stub
		return this.userFileMapper.getUserFileHasType(id, type);
	}
	//修改材料
	@Override
	public void updateUserFile(Long[] id, Long[] type) {
		// TODO Auto-generated method stub
		for(int i=0;i<id.length;i++){
			UserFile ufs = this.userFileMapper.selectByPrimaryKey(id[i]);
			SystemDictionaryItem system = this.systemDictionaryItemService.getByidSystemItem(type[i]);
			ufs.setFileType(system);
			this.userFileMapper.updateByPrimaryKey(ufs);
		}
	}
	//分页查询
	@Override
	public PageResult list(RealauthQuery User_Auth) {
		// TODO Auto-generated method stub
		int count=this.userFileMapper.count(User_Auth);
		if(count>0){
			List<UserFile> list = this.userFileMapper.query(User_Auth);
			return new PageResult(list,count, User_Auth.getCurrentPage(), User_Auth.getPageSize());
		}
		
		return PageResult.empty(User_Auth.getPageSize());
	}
	@Override
	public void AuthUserFile(UserFile userFile) {
		// TODO Auto-generated method stub
		//根据id获取数据
		UserFile ufs=this.userFileMapper.selectByPrimaryKey(Long.parseLong(userFile.getId()+""));
		//获取用户信息
		Userinfo userinfo=this.userInfoService.get(Long.parseLong(ufs.getApplier().getId()+""));
		//获取后台登入人员信息
		Logininfo loginadmin=UserContext.getContextUser();
		
		if(userFile.getState()==1){
			//成功
			ufs.setState(UserFile.STATE_AUDIT);
			ufs.setScore(userFile.getScore());
			userinfo.setScore(userinfo.getScore()+userFile.getScore());
			this.userInfoService.updateUserInfo(userinfo);
		}else if(userFile.getState()==2){
			//失败
			ufs.setState(UserFile.STATE_REJECT);
			
		}else{
			throw new RuntimeException("系统出错,请稍后再试");
		}
		ufs.setRemark(userFile.getRemark());
		ufs.setAuditTime(new Date());
		ufs.setAuditor(loginadmin);
		this.userFileMapper.updateByPrimaryKey(ufs);
		
	}
	@Override
	public void deleteUserFileImg(Long id) {
		// TODO Auto-generated method stub
		this.userFileMapper.delImg(id);
	}

}
