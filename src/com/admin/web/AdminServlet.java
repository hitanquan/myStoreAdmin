package com.admin.web;

import com.admin.domain.Admin;
import com.admin.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * �˳���¼����
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String exitLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctx = request.getContextPath();
		//System.out.println("exitLogin");
		//��ȡsession����󣬽�admin����������Ƴ�
		HttpSession adminSession = request.getSession();
		//System.out.println(adminSession);
		if(adminSession!=null){
			adminSession.removeAttribute("admin");
			response.getWriter().print("��л���Ĺ��٣��ټ���");
			response.setHeader("refresh", "3;url="+ctx+"/admin/admin_login.jsp");
			//return "admin/admin_login.jsp";
		}
		return null;
	}

	/**
	 * ��¼����
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			Admin admin = new AdminService().login(username,password);
			//��ȡsession����󣬽�admin���浽�������
			HttpSession adminSession = request.getSession();
			adminSession.setAttribute("admin", admin);
			//��¼�ɹ���ҳ���ض�����ҳ
			response.sendRedirect("admin/admin_index.jsp");
		}catch (Exception e) {
			//��¼ʧ��
			//��ʧ��ԭ�������Լ��׳����쳣��Ϣ�����ص���¼ҳ�棬���Դ�����Ϣ
			if(e.getMessage().equals("�û�������Ϊ�գ�")){
				request.setAttribute("errorMessage", e.getMessage());
			}else if(e.getMessage().equals("���벻��Ϊ�գ�")){
				request.setAttribute("errorMessage", e.getMessage());
			}else if(e.getMessage().equals("�û������������")){
				request.setAttribute("errorMessage", e.getMessage());
			}else{
				//������������쳣��ջ��Ϣ
				e.printStackTrace();
			}
			return "admin/admin_login.jsp";
		}	
		return null;
	}


	/**
	 * ע�᷽��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��ȡ������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			//���÷����ע�᷽��
			boolean result = new AdminService().regist(username, password);
			if(result){
				response.getWriter().write("ע��ɹ���ҳ����ת�С���");
				response.setHeader("refresh", "3;url=/myStoreAdmin_war_exploded/admin/admin_login.jsp");
			}else{
				response.getWriter().write("ע��ʧ�ܣ�������ע�ᣡ");
				response.setHeader("refresh", "3;url=/myStoreAdmin_war_exploded/admin/admin_regist.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
