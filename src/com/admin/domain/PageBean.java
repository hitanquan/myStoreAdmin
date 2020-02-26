package com.admin.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ҳʵ����
 * @author ̷��
 */
public class PageBean {

	//���浱ǰҳ������Ϣ�ļ���
	private List<Category> categoryList = new ArrayList<Category>();
	//���浱ǰҳ����Ա��Ϣ�ļ���
	private List<Admin> adminList = new ArrayList<Admin>();
	//���浱ǰҳ��Ʒ��Ϣ�ļ���
	private List<Goods> goodsList = new ArrayList<Goods>();
	//���浱ǰҳ��ŵı���
	private Integer currentPage;
	//������ҳ���ı���
	private Integer totalPage;
	//�����ܼ�¼���ı���
	private Integer totalCount;

	public List<Admin> getAdminList() {
		return adminList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

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
		return "PageBean{" +
				"categoryList=" + categoryList +
				", adminList=" + adminList +
				", goodsList=" + goodsList +
				", currentPage=" + currentPage +
				", totalPage=" + totalPage +
				", totalCount=" + totalCount +
				'}';
	}
}
