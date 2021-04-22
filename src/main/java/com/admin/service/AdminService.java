package com.admin.service;


import com.admin.dao.AdminDao;
import com.admin.domain.Admin;
import com.admin.domain.PageBean;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * 管理员业务
 * @author 谭少
 */
public class AdminService {

	public PageBean getPageBean(int currentPage) throws SQLException {
		PageBean pageBean = new PageBean();
		//设置当前页数
		pageBean.setCurrentPage( currentPage );
		//查询总记录数
		Long totalCount = AdminDao.queryCount();
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
		List<Admin> adminsList = AdminDao.queryPageData( index, pageCount );
		//集合反转，让后添加的数据显示在前面
		Collections.reverse( adminsList );
		//设置管理员信息
		pageBean.setAdminList( adminsList );
		return pageBean;
	}

	public Admin login(String username, String password) throws Exception {
		//调用dao层获取管理员数据的方法
		Admin admin = new AdminDao().getAdminByUsernameAndPassword( username, password );
		//登录校验
		//若获取的数据不为空，说明存在该管理员，返回
		if(admin != null){
			return admin;
		}else{
			//否则，抛出异常
			if(username==""){
				throw new Exception("用户名不能为空！");
			}else if(password==""){
				throw new Exception("密码不能为空！");
			}else{
				throw new Exception("用户名或密码错误！");
			}
		}
	}
	
	public boolean regist(String username, String password) throws Exception {
		int count = new AdminDao().insertAdmin(username, password);
		if(count > 0){
			 return true;
		}
		 return false;
	}

	public void addAdmin(String username, String password) throws SQLException {
		AdminDao.insertAdmin( username, password );
	}

	public Admin getAdminById(String id) throws SQLException {
		return AdminDao.queryAdminById( id );
	}

	public int updPasswordById(String id, String password) throws SQLException {
		return AdminDao.updPassword( id, password );
	}

	public int delAdminById(int id) throws SQLException {
		return AdminDao.delAdminById( id );
	}
}

