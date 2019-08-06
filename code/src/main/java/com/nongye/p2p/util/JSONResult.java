package com.nongye.p2p.util;
/**
 * 返回json处理对象
 * @author 89568
 *
 */
public class JSONResult {
	/**标记是否成功*/
	
	private boolean flag=true;
	/**提示信息*/
	
	private String message;
	public JSONResult(){
		
	}
	
	public JSONResult(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}
	
	public JSONResult(String message){
		super();
		this.message=message;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
