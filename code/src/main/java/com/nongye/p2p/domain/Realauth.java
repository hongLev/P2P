package com.nongye.p2p.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.nongye.p2p.base.BaseAuditDomain;

/**
 * 实名认证实体类
 * @author 89568
 *
 */
public class Realauth extends BaseAuditDomain{

	
	public static final int SEX_MALE = 0;
	public static final int SEX_FEMALE = 1;

	private String realName;
	private int sex;
	private String idNumber;// 证件号码
	private String bornDate;// 出生日期
	private String address;// 证件地址
	private String image1;// 身份证正面图片地址
	private String image2;// 身份证反面图片地址
	
	public String getJsonStr(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("applier", this.getApplier());		
		map.put("realName", this.getRealName());
		map.put("idNumber", this.getIdNumber());
		map.put("sex", this.getSexDisplay());
		map.put("bornDate",this.getBornDate());
		map.put("address", this.getAddress());
		map.put("image1", this.getImage1());
		map.put("image2",this.getImage2());
		map.put("id", this.getId());
		return JSONObject.toJSON(map).toString();
	}
	
	
	public String getSexDisplay(){
		return sex==0?"男":"女";
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public static int getSexMale() {
		return SEX_MALE;
	}
	public static int getSexFemale() {
		return SEX_FEMALE;
	}
	/**
	 * 获取用户真实名字的隐藏字符串，只显示姓氏
	 * 
	 * @param realName
	 *            真实名字
	 * @return
	 */
	public String getAnonymousRealName() {
		if (StringUtils.hasLength(this.realName)) {
			int len = realName.length();
			String replace = "";
			replace += realName.charAt(0);
			for (int i = 1; i < len; i++) {
				replace += "*";
			}
			return replace;
		}
		return realName;
	}

	/**
	 * 获取用户身份号码的隐藏字符串
	 * 
	 * @param idNumber
	 * @return
	 */
	public String getAnonymousIdNumber() {
		if (StringUtils.hasLength(idNumber)) {
			int len = idNumber.length();
			String replace = "";
			for (int i = 0; i < len; i++) {
				if ((i > 5 && i < 10) || (i > len - 5)) {
					replace += "*";
				} else {
					replace += idNumber.charAt(i);
				}
			}
			return replace;
		}
		return idNumber;
	}

	/**
	 * 获取用户住址的隐藏字符串
	 * 
	 * @param currentAddress
	 *            用户住址
	 * @return
	 */
	public String getAnonymousAddress() {
		if (StringUtils.hasLength(address) && address.length() > 4) {
			String last = address.substring(address.length() - 4,
					address.length());
			String stars = "";
			for (int i = 0; i < address.length() - 4; i++) {
				stars += "*";
			}
			return stars + last;
		}
		return address;
	}

}
