package com.admin.service;

import com.admin.dao.CategoryDao;
import com.admin.domain.Category;
import com.admin.domain.PageBean;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CategoryService {

	public PageBean getPageBean(int currentPage) throws SQLException {
		PageBean pageBean = new PageBean();
		//设置当前页数
		pageBean.setCurrentPage( currentPage );
		//查询总记录数
		Long totalCount = CategoryDao.queryCount();
		//设置总记录数
		pageBean.setTotalCount( totalCount.intValue() );
		//让每页显示5条数据
		Integer pageCount = 5;
		//获取总页数：总记录数/每页记录条数
		double totalPage = Math.ceil( 1.0 * pageBean.getTotalCount() / pageCount );
		//设置总页数
		pageBean.setTotalPage( (int) totalPage );
		//当前页查询的角标
		Integer index = (pageBean.getCurrentPage() - 1) * pageCount;
		//获取管理员分页数据
		List<Category> categoryList = CategoryDao.queryPageData( index, pageCount );
		//集合反转，让后添加的数据显示在前面
		Collections.reverse( categoryList );
		//设置管理员信息
		pageBean.setCategoryList( categoryList );
		return pageBean;
	}


	public List<Category> getAllCategory() throws SQLException {
		return CategoryDao.queryAllCategory();
	}

	public int delCategoryById(int id) throws SQLException {
		return CategoryDao.delCategoryById( id );
	}

	public int updCategoryById(String id, String cateName) throws SQLException {
		return CategoryDao.updCategoryById( id, cateName );
	}

	public Category getCategoryById(String id) throws SQLException {
		return CategoryDao.getCategoryById( id );
	}

	public int addCategory(String cateName) throws SQLException {
		return CategoryDao.addCategory( cateName );
	}
}
