package com.oracle.lnsd.utils;

import java.util.List;

public class Page<T> {
	public static final int DEFAULT_CURRENT_PAGE = 1;
	public static final int DEFAULT_NUM_PER_PAGE = 10;
	private List<T> list;
	//想显示的是第几页。
	private int currentPage;
	//每页多少行
	private int numPerPage;
	//共有多少行
	private int totalNum;
	
	public Page(int currentPage, int numPerPage, int totalNum) {
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalNum = totalNum;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 取得应该跳过的行数
	 * @return
	 */
	public int getOffset() {
		return (this.currentPage - 1) * this.numPerPage;
	}
	/**
	 * 得到总共能有多少页
	 */
	public int getTotalPageNum() {
		//非常巧妙的一个算法
		return (this.totalNum + this.numPerPage - 1) / this.numPerPage;
		//不巧妙的算法
//		if(this.totalNum % this.numPerPage == 0) {
//			return this.totalNum/this.numPerPage;
//		}else {
//			return 1+ this.totalNum/this.numPerPage;
//		}
	}
}
