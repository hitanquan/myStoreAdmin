package com.admin.test;

import com.admin.dao.CategoryDao;
import org.junit.Test;

import java.sql.SQLException;

public class CategoryDaoTest {
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println(CategoryDao.queryAllCategory());
	}
}
