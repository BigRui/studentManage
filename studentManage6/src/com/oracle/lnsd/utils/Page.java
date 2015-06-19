package com.oracle.lnsd.utils;

import java.util.List;

public class Page<T> {
	public static final int DEFAULT_CURRENT_PAGE = 1;
	public static final int DEFAULT_NUM_PER_PAGE = 10;
	private List<T> list;
	//����ʾ���ǵڼ�ҳ��
	private int currentPage;
	//ÿҳ������
	private int numPerPage;
	//���ж�����
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
	 * ȡ��Ӧ������������
	 * @return
	 */
	public int getOffset() {
		return (this.currentPage - 1) * this.numPerPage;
	}
	/**
	 * �õ��ܹ����ж���ҳ
	 */
	public int getTotalPageNum() {
		//�ǳ������һ���㷨
		return (this.totalNum + this.numPerPage - 1) / this.numPerPage;
		//��������㷨
//		if(this.totalNum % this.numPerPage == 0) {
//			return this.totalNum/this.numPerPage;
//		}else {
//			return 1+ this.totalNum/this.numPerPage;
//		}
	}
}
