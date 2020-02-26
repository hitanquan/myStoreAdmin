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
		//���õ�ǰҳ��
		pageBean.setCurrentPage( currentPage );
		//��ѯ�ܼ�¼��
		Long totalCount = CategoryDao.queryCount();
		//�����ܼ�¼��
		pageBean.setTotalCount( totalCount.intValue() );
		//��ÿҳ��ʾ5������
		Integer pageCount = 5;
		//��ȡ��ҳ�����ܼ�¼��/ÿҳ��¼����
		double totalPage = Math.ceil( 1.0 * pageBean.getTotalCount() / pageCount );
		//������ҳ��
		pageBean.setTotalPage( (int) totalPage );
		//��ǰҳ��ѯ�ĽǱ�
		Integer index = (pageBean.getCurrentPage() - 1) * pageCount;
		//��ȡ����Ա��ҳ����
		List<Category> categoryList = CategoryDao.queryPageData( index, pageCount );
		//���Ϸ�ת���ú���ӵ�������ʾ��ǰ��
		Collections.reverse( categoryList );
		//���ù���Ա��Ϣ
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
