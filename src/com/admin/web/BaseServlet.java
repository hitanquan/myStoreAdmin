package com.admin.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 抽取通用Servlet类
 * @author 谭少
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 每次请求都会执行该方法，通过反射技术来动态调用业务处理方法
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
        // 接收参数
        String action = request.getParameter("action");
        try {
            //获取当前对象的字节码
            Class clazz = this.getClass();
            //System.out.println("当前对象的字节码:"+clazz);
            Method method = clazz.getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
            //判断有没有传入的方法
            if(method != null) {
                //如果有就调用
                String desPath =  (String)method.invoke( this, request, response );
                //System.out.println("desPath="+desPath);
                //转发
                if(desPath != null) {
                    request.getRequestDispatcher(desPath).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}