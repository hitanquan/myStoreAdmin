package com.admin.service;


import com.admin.dao.AdminDao;
import com.admin.domain.Admin;
import com.admin.domain.PageBean;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * ����Աҵ��
 * @author ̷��
 */
public class AdminService {

	public PageBean getPageBean(int currentPage) throws SQLException {
		PageBean pageBean = new PageBean();
		//���õ�ǰҳ��
		pageBean.setCurrentPage( currentPage );
		//��ѯ�ܼ�¼��
		Long totalCount = AdminDao.queryCount();
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
		List<Admin> adminsList = AdminDao.queryPageData( index, pageCount );
		//���Ϸ�ת���ú���ӵ�������ʾ��ǰ��
		Collections.reverse( adminsList );
		//���ù���Ա��Ϣ
		pageBean.setAdminList( adminsList );
		return pageBean;
	}

	public Admin login(String username, String password) throws Exception {
		//����dao���ȡ����Ա���ݵķ���
		Admin admin = new AdminDao().getAdminByUsernameAndPassword( username, password );
		//��¼У��
		//����ȡ�����ݲ�Ϊ�գ�˵�����ڸù���Ա������
		if(admin != null){
			return admin;
		}else{
			//�����׳��쳣
			if(username==""){
				throw new Exception("�û�������Ϊ�գ�");
			}else if(password==""){
				throw new Exception("���벻��Ϊ�գ�");
			}else{
				throw new Exception("�û������������");
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

