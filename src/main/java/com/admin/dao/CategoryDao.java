package com.admin.dao;

import com.admin.domain.Category;
import com.admin.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
	private static QueryRunner qr = new QueryRunner( JdbcUtil.getDataSource() );
	private static String sql = null;

	public static Long queryCount() throws SQLException {
		sql = "select count(*) from t_category";
		return (long) qr.query( sql, new ScalarHandler() );
	}

	public static List<Category> queryPageData(Integer index, Integer pageCount) throws SQLException {
		sql = "select * from t_category limit ?,?";
		return qr.query( sql, new BeanListHandler<Category>( Category.class ), index, pageCount );
	}

	public static List<Category> queryAllCategory() throws SQLException {
		sql = "select * from t_category";
		return qr.query( sql, new BeanListHandler<Category>( Category.class ) );
	}

	public static int delCategoryById(int id) throws SQLException {
		sql = "delete from t_category where cid =?";
		return qr.update( sql, id );
	}

	public static int updCategoryById(String id, String cateName) throws SQLException {
		sql = "update t_category set cname =? where cid = ?";
		return qr.update( sql, cateName, id );
	}

	public static Category getCategoryById(String id) throws SQLException {
		sql = "select * from t_category where cid =?";
		return qr.query( sql, new BeanHandler<Category>( Category.class ), id );
	}

	public static int addCategory(String cateName) throws SQLException {
		sql = "insert into t_category(cname) values(?)";
		return qr.update( sql, cateName );
	}
}
