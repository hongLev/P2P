package com.nongye.p2p.Test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nongye.p2p.domain.Logininfo;
import com.nongye.p2p.domain.Realauth;
import com.nongye.p2p.mapper.BidMapper;
import com.nongye.p2p.mapper.LogininifMapper;
import com.nongye.p2p.mapper.RealauthMapper;
import com.nongye.p2p.mapper.VedioAuthMapper;
import com.nongye.p2p.quey.RealauthQuery;
import com.nongye.p2p.service.IBidRequestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
	
	
	@Autowired
	private RealauthMapper realauthMapper;
	
	@Autowired
	private LogininifMapper logininfo;
	@Autowired
	private VedioAuthMapper vedioAuthMapper;
	
	@Autowired
	private IBidRequestService bidRequestService;
	
	@Test
	public void logininfoMapperTest(){
		Logininfo lo=new Logininfo();
		lo.setUserName("你好xxxxx");
		lo.setPassWord("1234111");
		lo.setState(lo.STATE_NORMAL);
		lo.setType(lo.USER_CLIENT);
		logininfo.insertLogininif(lo);
	}
	
	@Test
	public void test01(){
		System.out.println("dfasdfaf");
	}
	
	//测试用户是否存在
	@Test
	public void Test2(){
		int count=logininfo.getCountByUsername("你好");
		System.out.println(count);
	}
	
	@Test
	public void Test3(){
		Logininfo lo=new Logininfo();
		lo.setUserName("你好");
		lo.setPassWord("1234");
		lo.setState(lo.STATE_NORMAL);
		lo.setType(lo.USER_CLIENT);
		Logininfo loo=logininfo.getByLoininfo(lo);
		System.out.println(loo.getId()+"");
	}
	//测试
	@Test
	public void Test4(){
		RealauthQuery r=new RealauthQuery();
		r.setCurrentPage(1);
		r.setPageSize(5);
		System.out.println(realauthMapper.getAllRealauth(r));
	}
	@Test
	public void test5(){
		try {
			List<String> list=vedioAuthMapper.getByUserName("1");
			for(String c:list){
				System.out.println(c+" ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test6(){
		try {
			System.out.println(this.bidRequestService.sumBid(Long.parseLong(6+""), Long.parseLong(28+"")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
