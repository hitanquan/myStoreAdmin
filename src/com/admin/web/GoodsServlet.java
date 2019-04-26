package com.admin.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.admin.domain.Category;
import com.admin.domain.Goods;
import com.admin.domain.pageBean;
import com.admin.service.CategoryService;
import com.admin.service.GoodsService;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	
	public String getPageData(HttpServletRequest request, HttpServletResponse response) {
		try {
			//1.��ȡ��ǰҳ��
			String currentPage = request.getParameter("currentPage");
			//2.��ҳ���ҵ���  ����ҳ�����һ��pageBean
			pageBean pageBean = new GoodsService().getPageBean(Integer.parseInt(currentPage));
			System.out.println(pageBean);
			//3.��pageBeanд���� ����
			request.setAttribute("pageBean", pageBean);
			//4.ת��
			return "admin/main.jsp";
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ������Ʒ�ķ���
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		//��ȡ���ݹ����Ĳ���
		Map<String, String[]> parameterMap = request.getParameterMap();
		Goods goods = new Goods();
		try {
			//��װ��Goods����
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("1.jpg");
			System.out.println(goods);
			//����ҵ�����ݸ������ݵķ���
			GoodsService gs = new GoodsService();
			gs.updataGoods(goods);
			//����ת������ǰServlet�Ļ�ȡ������Ʒ��Ϣ�ķ���
			return "/GoodsServlet?action=getPageData&currentPage="+currentPage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��ȡ���ݹ����Ĳ���id
		int id = Integer.parseInt(request.getParameter("id"));

		//��ȡ��ǰ��Ʒ��Ϣ
		//1.����ҵ������id��ȡ���ݵķ���
		GoodsService gs = new GoodsService();
		Goods goods;
		try {
			goods = gs.getGoodsById(id);
			//System.out.println(goods);
			//2.����ȡ�������ݱ��浽request��
			request.setAttribute("goods", goods);
			//3.ת������Ʒ��Ϣ�༭ҳ��
			//request.getRequestDispatcher("admin/goodsAdd.jsp").forward(request, response);
			//��ȡ��Ʒ������Ϣ
			CategoryService cs = new CategoryService();
			List<Category> allCategory = cs.getAllCategory();
			request.setAttribute("allCategory", allCategory);
			//��ҳ���л�����������Ʒ��Ϣ
			return "admin/goodsUpd.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	/**
	 * ��Ʒ��Ϣ��ӷ���
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��ȡ������Ĳ���
		Map<String, String[]> pMap = request.getParameterMap();
		//��װ��Goods����
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, pMap);
			goods.setImage("goods_001.png");
			System.out.println(goods);
			//����ҵ���������ݵķ���
			GoodsService gs = new GoodsService();
			gs.addGoods(goods);
			//����ת������ǰServlet�Ļ�ȡ������Ʒ��Ϣ�ķ���
			return "/GoodsServlet?action=getPageData";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public String addUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//���÷���ҵ��㷽���õ���������
		CategoryService cs = new CategoryService();

		try {
			List<Category> allCategory = cs.getAllCategory();
			//����ȡ������Ʒ������Ϣ���浽request��
			request.setAttribute("allCategory", allCategory);
			//����ת������Ʒ��Ϣ���ҳ����ʾ��Ʒ������Ϣ
			return "admin/goodsAdd.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String delGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��ȡid
		String id = request.getParameter("id");
		//����ҵ���ɾ����Ʒ�ķ���
		GoodsService gs = new GoodsService();
		try {
			gs.delGoods(Integer.parseInt(id));
			System.out.println("ɾ���ɹ�");
			//����ת������ǰServlet�Ļ�ȡ������Ʒ��Ϣ�ķ���
			return "/GoodsServlet?action=getPageData";
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String getAllGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��service��Ҫ����
		try {
			List<Goods> goodsList = new GoodsService().getAllGoods();
			//System.out.println(goodsList);
			//���Ϸ�ת���������ݿ��л�ȡ���ݵ�˳��ߵ���������������ӵ����ݾ���ʾ��ǰ��
			Collections.reverse(goodsList);
			request.setAttribute("goodsList", goodsList);
			//������ת������Ʒ�б�ҳ��ʾ
			return "admin/main.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
