package com.admin.web;

import com.admin.domain.Admin;
import com.admin.domain.PageBean;
import com.admin.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    private static AdminService adminService = new AdminService();

    /**
     * ɾ������Ա
     *
     * @param request
     * @param response
     * @return String
     * @throws SQLException
     * @throws IOException
     */
    public String delAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter( "id" );
        int result = adminService.delAdminById( Integer.parseInt( id ) );
        if (result > 0) {
            System.out.println( "ɾ���ɹ�" );
            return "/AdminServlet?action=getPageData&currentPage=1";
        } else {
            response.getWriter().print( "ɾ��ʧ�ܣ�������" );
            return "/AdminServlet?action=getPageData&currentPage=1";
        }
    }

    /**
     * ���Ĺ���Ա����
     *
     * @param request
     * @param response
     * @return String
     */
    public String updPassword(HttpServletRequest request, HttpServletResponse response) {
        String ctx = request.getContextPath();
        String id = request.getParameter( "id" );
        String password = request.getParameter( "password" );
        try {
            int result = adminService.updPasswordById( id, password );
            //System.out.println( "result=" + result );
            if (result > 0) {
                //response.getWriter().print("������³ɹ��������µ�¼");
                //response.sendRedirect("admin/login.jsp");
                //return "/AdminServlet?action=getPageData&currentPage=1";
                return "admin/login.jsp";
            } else {
                response.getWriter().print( "�������ʧ�ܣ�������" );
                return "/AdminServlet?action=getPageData&currentPage=1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��������UI
     *
     * @param request
     * @param response
     * @return String
     */
    public String updPasswordUI(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter( "id" );
        try {
            Admin admin = adminService.getAdminById( id );
            HttpSession session = request.getSession();
            session.setAttribute( "admin", admin );
            return "admin/adminPasswordUpd.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ӹ���Ա
     *
     * @param request
     * @param response
     * @return String
     * @throws ServletException
     * @throws IOException
     */
    public String addAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //��ȡ������Ĳ���
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        //System.out.println( "��ӹ���Ա,username = " + username + "password = " + password );
        try {
            adminService.addAdmin( username, password );
            //����ת������ǰServlet�Ļ�ȡ���й���Ա��Ϣ�ķ���
            return "/AdminServlet?action=getPageData&currentPage=1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ȡ��ҳ����
     *
     * @param request
     * @param response
     * @return String
     */
    public String getPageData(HttpServletRequest request, HttpServletResponse response) {
        try {
            //1.��ȡ��ǰҳ��
            String currentPage = request.getParameter( "currentPage" );
            //2.��ҳ���ҵ���  ����ҳ�����һ��pageBean
            PageBean pageBean = adminService.getPageBean( Integer.parseInt( currentPage ) );
            System.out.println( pageBean );
            //3.��pageBeanд������
            request.setAttribute( "pageBean", pageBean );
            //4.ת��
            return "admin/admin.jsp";
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * �˳���¼����
     *
     * @param request
     * @param response
     * @return String
     * @throws ServletException
     * @throws IOException
     */
    public String exitLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ctx = request.getContextPath();
        //��ȡsession����󣬽�admin����������Ƴ�
        HttpSession adminSession = request.getSession();
        //System.out.println(adminSession);
        if (adminSession != null) {
            adminSession.removeAttribute( "admin" );
            response.getWriter().print( "��л���Ĺ��٣��ټ���" );
            response.setHeader( "refresh", "3;url=" + ctx + "/admin/login.jsp" );
            //return "admin/login.jsp";
        }
        return null;
    }

    /**
     * ��¼����
     *
     * @param request
     * @param response
     * @return String
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        try {
            Admin admin = adminService.login( username, password );
            System.out.println( "username=" + admin.getUsername() );
            //��ȡsession����󣬽�admin���浽�������
            HttpSession adminSession = request.getSession();
            adminSession.setAttribute( "admin", admin );
            //��¼�ɹ���ҳ���ض�����ҳ
            response.sendRedirect( "admin/index.jsp" );
        } catch (Exception e) {
            //��¼ʧ��
            //��ʧ��ԭ�������Լ��׳����쳣��Ϣ�����ص���¼ҳ�棬���Դ�����Ϣ
            if (e.getMessage().equals( "�û�������Ϊ�գ�" )) {
                request.setAttribute( "errorMessage", e.getMessage() );
            } else if (e.getMessage().equals( "���벻��Ϊ�գ�" )) {
                request.setAttribute( "errorMessage", e.getMessage() );
            } else if (e.getMessage().equals( "�û������������" )) {
                request.setAttribute( "errorMessage", e.getMessage() );
            } else {
                //������������쳣��ջ��Ϣ
                e.printStackTrace();
            }
            return "admin/login.jsp";
        }
        return null;
    }


    /**
     * ע�᷽��
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ctx = request.getContextPath();
        //��ȡ������
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        try {
            //���÷����ע�᷽��
            boolean result = adminService.regist( username, password );
            if (result) {
                response.getWriter().write( "ע��ɹ���ҳ����ת�С���" );
                response.setHeader( "refresh", "3;url=/myStoreAdmin/admin/login.jsp" );
            } else {
                response.getWriter().write( "ע��ʧ�ܣ�������ע�ᣡ" );
                response.setHeader( "refresh", "3;url=/" + ctx + "/admin/regist.jsp" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
