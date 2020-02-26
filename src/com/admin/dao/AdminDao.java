package com.admin.dao;

import com.admin.domain.Admin;
import com.admin.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDao {

	private static QueryRunner qr = new QueryRunner( JdbcUtil.getDataSource() );
	private static String sql = null;


	public static Long queryCount() throws SQLException {
		sql = "select count(*) from t_admin";
		return (long) qr.query( sql, new ScalarHandler() );
	}

	public static List<Admin> queryPageData(Integer index, Integer pageCount) throws SQLException {
		sql = "select * from t_admin limit ?,?";
		return qr.query( sql, new BeanListHandler<Admin>( Admin.class ), index, pageCount );
	}

	public static Admin queryAdminById(String id) throws SQLException {
		sql = "select * from t_admin where id =?";
		return qr.query( sql, new BeanHandler<Admin>( Admin.class ), id );
	}

	public static int updPassword(String id, String password) throws SQLException {
		sql = "update t_admin set password =? where id = ?";
		return qr.update( sql, password, id );
	}

	public static int delAdminById(int id) throws SQLException {
		sql = "delete from t_admin where id =?";
		return qr.update( sql, id );
	}

	public Admin getAdminByUsernameAndPassword(String username, String password) throws SQLException {
		sql = "select * from t_admin where username = ? and password = ?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),username,password);
	}

	public static int insertAdmin(String username, String password) throws SQLException {
		sql = "insert into t_admin(username,password) values(?,?)";
		return qr.update(sql, username,password);
	}
}
