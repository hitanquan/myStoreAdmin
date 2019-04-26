package com.admin.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�����ȡ����Ƶ���õ��Ĵ����ȡ����һ���࣬��������̳�
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ʹ�÷�����GeneralServletTest����if�жϴ��뷱������
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������������Ӧ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��Ҫת����Ŀ��ҳ��·��
		String path = null;
		//�����������
		String action = request.getParameter("action");
		//��ȡ��ǰ����ֽ���
		Class<? extends BaseServlet> clazz = this.getClass();
		//���ݴ����������������ȡ��Ӧ�ķ���
		try {
			Method method = clazz.getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			//�Ի�ȡ�ķ������зǿ��ж�
			//���ǿգ���ִ�и÷���
			if(method!=null){
				//���ص����ַ���·��
				path = (String) method.invoke(this, request,response);
				//��·�����зǿ��ж�
				//�����գ�������ת����·�����ڵ�ҳ��
				if(path!=null){
					request.getRequestDispatcher(path).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ķ��������ڣ�");
		}
	}
}
