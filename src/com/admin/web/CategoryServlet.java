package com.admin.web;

import com.admin.domain.Category;
import com.admin.domain.PageBean;
import com.admin.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    private static CategoryService categoryService = new CategoryService();

    /**
     * 删除分类信息
     *
     * @param request
     * @param response
     * @return String
     * @throws SQLException
     * @throws IOException
     */
    public String delCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter( "id" );
        int result = categoryService.delCategoryById( Integer.parseInt( id ) );
        if (result > 0) {
            System.out.println( "删除成功" );
            return "/CategoryServlet?action=getPageData&currentPage=1";
        } else {
            response.getWriter().print( "删除失败，请重试" );
            return "/CategoryServlet?action=getPageData&currentPage=1";
        }
    }

    /**
     * 更新分类信息
     *
     * @param request
     * @param response
     * @return String
     */
    public String updCategory(HttpServletRequest request, HttpServletResponse response) {
        String ctx = request.getContextPath();
        String id = request.getParameter( "id" );
        String cateName = request.getParameter( "cateName" );
        try {
            int result = categoryService.updCategoryById( id, cateName );
            //System.out.println( "result=" + result );
            if (result > 0) {
                //response.getWriter().print("密码更新成功，请重新登录");
                //response.sendRedirect("admin/login.jsp");
                System.out.println( "分类信息更新成功" );
                return "/CategoryServlet?action=getPageData&currentPage=1";
                //return "admin/login.jsp";
            } else {
                System.out.println( "分类信息更新失败，请重试" );
                return "/CategoryServlet?action=getPageData&currentPage=1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新分类UI
     *
     * @param request
     * @param response
     * @return String
     * @throws SQLException
     */
    public String updCategoryUI(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter( "id" );
        Category category = categoryService.getCategoryById( id );
        HttpSession session = request.getSession();
        session.setAttribute( "category", category );
        return "admin/categoryUpd.jsp";
    }

    /**
     * 添加分类信息
     *
     * @param request
     * @param response
     * @return String
     * @throws ServletException
     * @throws IOException
     */
    public String addCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取表单输入的参数
        String cateName = request.getParameter( "cateName" );
        //System.out.println( "cateName=" + cateName );
        try {
            int result = categoryService.addCategory( cateName );
            if (result > 0) {
                System.out.println( "分类信息添加成功" );
                //重新转发到当前Servlet的获取所有管理员信息的方法
                return "/CategoryServlet?action=getPageData&currentPage=1";
            } else {
                System.out.println( "分类信息添加失败，请重试" );
                return "/CategoryServlet?action=getPageData&currentPage=1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取分页信息
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
            PageBean pageBean = categoryService.getPageBean( Integer.parseInt( currentPage ) );
            //System.out.println( pageBean );
            //3.把pageBean写到域当中
            request.setAttribute( "pageBean", pageBean );
            //4.转发
            return "admin/category.jsp";
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
