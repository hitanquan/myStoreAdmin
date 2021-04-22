package com.admin.test;

import com.admin.dao.GoodsDao;

import java.sql.SQLException;

public class GoodsDaoTest {
	
	public static void main(String[] args) throws SQLException {
		//测试数据库连接
		//System.out.println(JdbcUtil.getConn());
		//System.out.println(JdbcUtil.getDataSource());
		
		//测试增删改查
		//System.out.println(gd.getAllGoods());
		
		/*Goods goods = new Goods();
		goods.setId(1);
		goods.setName("梵希蔓短袖衬衣女2018新款夏季气质韩版通勤");
		goods.setPrice(159d);
		goods.setImage("goods_001.png");
		goods.setGdesc("哈哈哈哈哈");
		goods.setIs_hot(1);
		goods.setCid(5);
		System.out.println(GoodsDao.insertGoods(goods));*/
		
		/*Goods goods = new Goods();
		goods.setId(15);
		goods.setName("买卖啊");
		goods.setPrice(299D);
		goods.setImage("fffffffff");
		goods.setGdesc("哈哈哈哈哈");
		goods.setIs_hot(1);
		goods.setCid(5);
		System.out.println(GoodsDao.updataGoods(goods));*/
		
		//System.out.println(gd.deleteGoods(16));
		
		//System.out.println(GoodsDao.queryCount());
		
		System.out.println(GoodsDao.queryPageData(0, 4));
		
	}

}
