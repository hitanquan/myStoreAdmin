package com.admin.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ��ȡͨ��Servlet��
 * @author ̷��
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * ÿ�����󶼻�ִ�и÷�����ͨ�����似������̬����ҵ������
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("=========BaseServlet===============");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // ���ղ���
        String action = request.getParameter("action");
        try {
            //��ȡ��ǰ������ֽ���
            Class clazz = this.getClass();
            //System.out.println("��ǰ������ֽ���:"+clazz);
            Method method = clazz.getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
            //�ж���û�д���ķ���
            if(method != null) {
                //����о͵���
                String desPath =  (String)method.invoke( this, request, response );
                //System.out.println("desPath="+desPath);
                //ת��
                if(desPath != null) {
                    request.getRequestDispatcher(desPath).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}