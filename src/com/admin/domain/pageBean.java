package com.admin.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ʒ��Ϣ��ҳ��ʾʵ����
 * @author tq
 */

public class pageBean {
	
	//���浱ǰҳ��Ʒ��Ϣ�ļ���
	private List<Goods> goodsList = new ArrayList<Goods>();
	//���浱ǰҳ��ŵı���
	private Integer currentPage;
	//������ҳ���ı���
	private Integer totalPage;
	//�����ܼ�¼���ı���
	private Integer totalCount;

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "pageBean [goodsList=" + goodsList + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + "]";
	}
}
