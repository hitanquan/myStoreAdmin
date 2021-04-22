package com.admin.service;

import com.admin.dao.GoodsDao;
import com.admin.domain.Goods;
import com.admin.domain.PageBean;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * 商品业务类
 * @author 谭少
 */
public class GoodsService {

	public PageBean getPageBean(int currentPage) throws SQLException {
		PageBean pageBean = new PageBean();
		//设置当前页数
		pageBean.setCurrentPage(currentPage);
		//查询总记录数
		Long totalCount = GoodsDao.queryCount();
		//设置总记录数
		pageBean.setTotalCount(totalCount.intValue());
		//让每页显示5条数据
		Integer pageCount = 5;
		//获取总页数：总记录数/每页记录条数
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);
		//设置总页数
		pageBean.setTotalPage((int)totalPage);
		//当前页查询的角标
		Integer index = (pageBean.getCurrentPage()-1) * pageCount;
		//获取分页商品
		List<Goods> goodsList = GoodsDao.queryPageData(index,pageCount);
		//集合反转，让后添加的数据显示在前面
		Collections.reverse( goodsList );
		//设置商品信息
		pageBean.setGoodsList(goodsList);
		return pageBean;
	}

	public List<Goods> getAllGoods() throws SQLException {
		return GoodsDao.queryAllGoods();
	}

	public int addGoods(Goods goods) throws SQLException {
		return GoodsDao.insertGoods( goods );
	}

	public int delGoods(int id) throws SQLException {
		return GoodsDao.deleteGoods( id );
	}

	public Goods getGoodsById(int id) throws SQLException {
		return GoodsDao.queryGoodsById( id );
	}

	public int updataGoods(Goods goods) throws SQLException {
		return GoodsDao.updataGoods( goods );
	}
}
