package com.admin.web;

import com.admin.domain.Category;
import com.admin.domain.Goods;
import com.admin.domain.PageBean;
import com.admin.service.CategoryService;
import com.admin.service.GoodsService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private static GoodsService goodsService = new GoodsService();
	
	public String getPageData(HttpServletRequest request, HttpServletResponse response) {
		try {
			//1.获取当前页码
			String currentPage = request.getParameter("currentPage");
			System.out.println( "currentPage=" + currentPage );
			//2.把页码给业务层  根据页码给我一个pageBean
			PageBean pageBean = goodsService.getPageBean( Integer.parseInt( currentPage ) );
			System.out.println(pageBean);
			//Collections.reverse(pageBean);
			//3.把pageBean写到域当中
			request.setAttribute("pageBean", pageBean);
			//4.转发
			return "admin/goodsList.jsp";
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新商品的方法
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		//获取传递过来的参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		//System.out.println("更新商品，传递过来的参数："+parameterMap);
		Goods goods = new Goods();
		try {
			//封装成Goods对象
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("1.jpg");
			//System.out.println(goods);
			//调用业务层根据更新数据的方法
			int result = goodsService.updataGoods( goods );
			if (result > 0) {
				System.out.println( "商品信息更新成功" );
				//重新转发到当前Servlet的获取所有商品信息的方法
				return "/GoodsServlet?action=getPageData&currentPage=" + currentPage;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取传递过来的参数id
		int id = Integer.parseInt(request.getParameter("id"));
		//获取当前商品信息
		//1.调用业务层根据id获取数据的方法
		Goods goods;
		try {
			goods = goodsService.getGoodsById( id );
			System.out.println( "更新商品，当前商品信息" + goods );
			//2.将获取到的数据保存到request域
			request.setAttribute("goods", goods);
			//3.转发到商品信息编辑页面
			//request.getRequestDispatcher("admin/goodsAdd.jsp").forward(request, response);
			//获取商品分类信息
			CategoryService cs = new CategoryService();
			List<Category> allCategory = cs.getAllCategory();
			request.setAttribute("allCategory", allCategory);
			//在页面中回显了所有商品信息
			return "admin/goodsUpd.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	/**
	 * 商品信息添加方法
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取表单输入的参数
		Map<String, String[]> pMap = request.getParameterMap();
		//System.out.println("添加商品，传递过来的参数："+pMap);
		//封装成Goods对象
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, pMap);
			goods.setImage("goods_001.png");
			System.out.println(goods);
			//调用业务层添加数据的方法
			goodsService.addGoods( goods );
			//重新转发到当前Servlet的获取所有商品信息的方法
			return "/GoodsServlet?action=getPageData&currentPage=1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public String addUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//调用分类业务层方法得到分类数据
		CategoryService cs = new CategoryService();
		try {
			List<Category> allCategory = cs.getAllCategory();
			//将获取到的商品分类信息保存到request域
			request.setAttribute("allCategory", allCategory);
			//请求转发到商品信息添加页面显示商品分类信息
			return "admin/goodsAdd.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String delGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取id
		String id = request.getParameter("id");
		//调用业务层删除商品的方法
		try {
			goodsService.delGoods( Integer.parseInt( id ) );
			System.out.println("删除成功");
			//重新转发到当前Servlet的获取所有商品信息的方法
			return "/GoodsServlet?action=getPageData";
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String getAllGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//向service层要数据
		try {
			List<Goods> goodsList = goodsService.getAllGoods();
			//System.out.println(goodsList);
			//集合反转，将从数据库中获取数据的顺序颠倒过来，这样后添加的数据就显示在前面
			Collections.reverse(goodsList);
			request.setAttribute("goodsList", goodsList);
			//将数据转发到商品列表页显示
			return "admin/goodsList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
