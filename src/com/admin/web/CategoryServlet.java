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
     * ɾ��������Ϣ
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
            System.out.println( "ɾ���ɹ�" );
            return "/CategoryServlet?action=getPageData&currentPage=1";
        } else {
            response.getWriter().print( "ɾ��ʧ�ܣ�������" );
            return "/CategoryServlet?action=getPageData&currentPage=1";
        }
    }

    /**
     * ���·�����Ϣ
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
                //response.getWriter().print("������³ɹ��������µ�¼");
                //response.sendRedirect("admin/login.jsp");
                System.out.println( "������Ϣ���³ɹ�" );
                return "/CategoryServlet?action=getPageData&currentPage=1";
                //return "admin/login.jsp";
            } else {
                System.out.println( "������Ϣ����ʧ�ܣ�������" );
                return "/CategoryServlet?action=getPageData&currentPage=1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���·���UI
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
     * ��ӷ�����Ϣ
     *
     * @param request
     * @param response
     * @return String
     * @throws ServletException
     * @throws IOException
     */
    public String addCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //��ȡ������Ĳ���
        String cateName = request.getParameter( "cateName" );
        //System.out.println( "cateName=" + cateName );
        try {
            int result = categoryService.addCategory( cateName );
            if (result > 0) {
                System.out.println( "������Ϣ��ӳɹ�" );
                //����ת������ǰServlet�Ļ�ȡ���й���Ա��Ϣ�ķ���
                return "/CategoryServlet?action=getPageData&currentPage=1";
            } else {
                System.out.println( "������Ϣ���ʧ�ܣ�������" );
                return "/CategoryServlet?action=getPageData&currentPage=1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ȡ��ҳ��Ϣ
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
            PageBean pageBean = categoryService.getPageBean( Integer.parseInt( currentPage ) );
            //System.out.println( pageBean );
            //3.��pageBeanд������
            request.setAttribute( "pageBean", pageBean );
            //4.ת��
            return "admin/category.jsp";
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
