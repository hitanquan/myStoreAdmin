package com.admin.test;

import java.sql.SQLException;

import com.admin.dao.GoodsDao;
import com.admin.domain.Goods;

public class GoodsDaoTest {
	
	public static void main(String[] args) throws SQLException {
		//�������ݿ�����
		//System.out.println(JdbcUtil.getConn());
		//System.out.println(JdbcUtil.getDataSource());
		
		//������ɾ�Ĳ�
		//System.out.println(gd.getAllGoods());
		
		/*Goods goods = new Goods();
		goods.setId(1);
		goods.setName("��ϣ���������Ů2018�¿��ļ����ʺ���ͨ��");
		goods.setPrice(159d);
		goods.setImage("goods_001.png");
		goods.setGdesc("����������");
		goods.setIs_hot(1);
		goods.setCid(5);
		System.out.println(GoodsDao.insertGoods(goods));*/
		
		/*Goods goods = new Goods();
		goods.setId(15);
		goods.setName("������");
		goods.setPrice(299D);
		goods.setImage("fffffffff");
		goods.setGdesc("����������");
		goods.setIs_hot(1);
		goods.setCid(5);
		System.out.println(GoodsDao.updataGoods(goods));*/
		
		//System.out.println(gd.deleteGoods(16));
		
		//System.out.println(GoodsDao.queryCount());
		
		System.out.println(GoodsDao.queryPageData(1, 4));
		
	}

}
