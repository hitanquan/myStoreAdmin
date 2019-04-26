package com.admin.service;


import com.admin.dao.AdminDao;
import com.admin.domain.Admin;

/**
 * ����Աҵ��
 * @author tq
 */
public class AdminService {

	/**
	 * ��½ҵ�񷽷�
	 * @param username
	 * @param password
	 * @return Admin
	 * @throws Exception 
	 */
	public Admin login(String username, String password) throws Exception {
		//����dao���ȡ����Ա���ݵķ���
		Admin admin = new AdminDao().getAdmin(username,password);
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
}
