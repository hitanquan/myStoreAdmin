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
     * 删除管理员
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
            System.out.println( "删除成功" );
            return "/AdminServlet?action=getPageData&currentPage=1";
        } else {
            response.getWriter().print( "删除失败，请重试" );
            return "/AdminServlet?action=getPageData&currentPage=1";
        }
    }

    /**
     * 更改管理员密码
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
                //response.getWriter().print("密码更新成功，请重新登录");
                //response.sendRedirect("admin/login.jsp");
                //return "/AdminServlet?action=getPageData&currentPage=1";
                return "admin/login.jsp";
            } else {
                response.getWriter().print( "密码更新失败，请重试" );
                return "/AdminServlet?action=getPageData&currentPage=1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更改密码UI
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
     * 添加管理员
     *
     * @param request
     * @param response
     * @return String
     * @throws ServletException
     * @throws IOException
     */
    public String addAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取表单输入的参数
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        //System.out.println( "添加管理员,username = " + username + "password = " + password );
        try {
            adminService.addAdmin( username, password );
            //重新转发到当前Servlet的获取所有管理员信息的方法
            return "/AdminServlet?action=getPageData&currentPage=1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取分页数据
     *
     * @param request
     * @param response
     * @return String
     */
    public String getPageData(HttpServletRequest request, HttpServletResponse response) {
        try {
            //1.获取当前页码
            String currentPage = request.getParameter( "currentPage" );
            //2.把页码给业务层  根据页码给我一个pageBean
            PageBean pageBean = adminService.getPageBean( Integer.parseInt( currentPage ) );
            System.out.println( pageBean );
            //3.把pageBean写到域当中
            request.setAttribute( "pageBean", pageBean );
            //4.转发
            return "admin/admin.jsp";
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 退出登录方法
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
        //获取session域对象，将admin从域对象中移除
        HttpSession adminSession = request.getSession();
        //System.out.println(adminSession);
        if (adminSession != null) {
            adminSession.removeAttribute( "admin" );
            response.getWriter().print( "感谢您的光临，再见！" );
            response.setHeader( "refresh", "3;url=" + ctx + "/admin/login.jsp" );
            //return "admin/login.jsp";
        }
        return null;
    }

    /**
     * 登录方法
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
            //获取session域对象，将admin保存到域对象中
            HttpSession adminSession = request.getSession();
            adminSession.setAttribute( "admin", admin );
            //登录成功，页面重定向到首页
            response.sendRedirect( "admin/index.jsp" );
        } catch (Exception e) {
            //登录失败
            //若失败原因是我自己抛出的异常信息，跳回到登录页面，回显错误信息
            if (e.getMessage().equals( "用户名不能为空！" )) {
                request.setAttribute( "errorMessage", e.getMessage() );
            } else if (e.getMessage().equals( "密码不能为空！" )) {
                request.setAttribute( "errorMessage", e.getMessage() );
            } else if (e.getMessage().equals( "用户名或密码错误！" )) {
                request.setAttribute( "errorMessage", e.getMessage() );
            } else {
                //否则输出其他异常堆栈信息
                e.printStackTrace();
            }
            return "admin/login.jsp";
        }
        return null;
    }


    /**
     * 注册方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ctx = request.getContextPath();
        //获取表单参数
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        try {
            //调用服务层注册方法
            boolean result = adminService.regist( username, password );
            if (result) {
                response.getWriter().write( "注册成功，页面跳转中……" );
                response.setHeader( "refresh", "3;url=/myStoreAdmin/admin/login.jsp" );
            } else {
                response.getWriter().write( "注册失败，请重新注册！" );
                response.setHeader( "refresh", "3;url=/" + ctx + "/admin/regist.jsp" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
