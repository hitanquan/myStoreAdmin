package com.admin.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体类
 * @author 谭少
 */
public class PageBean {

	//保存当前页分类信息的集合
	private List<Category> categoryList = new ArrayList<Category>();
	//保存当前页管理员信息的集合
	private List<Admin> adminList = new ArrayList<Admin>();
	//保存当前页商品信息的集合
	private List<Goods> goodsList = new ArrayList<Goods>();
	//保存当前页编号的变量
	private Integer currentPage;
	//保存总页数的变量
	private Integer totalPage;
	//保存总记录数的变量
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
