package com.nongye.p2p.quey;

public class ObjectQuery {

	/**当前页*/
	private int currentPage=1;
	/**每页显示数据多少条*/
	private int pageSize=10;
	
	
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	/**计算起始页*/
	public int getStart(){
		return (currentPage-1)*pageSize;
	}
}
