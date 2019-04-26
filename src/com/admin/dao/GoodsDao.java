package com.admin.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.admin.domain.Goods;
import com.admin.domain.pageBean;
import com.admin.utils.JdbcUtil;

/**
 * ��Ʒ���ݲ�����
 * @author tq
 */
public class GoodsDao {
	
	private static QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	private static String sql = null;
	
	//��ȡ������Ʒ
	public static List<Goods>queryAllGoods() throws SQLException{
		sql = "select * from t_goods";
		return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
	}
	
	//�����Ʒ
	public static int insertGoods(Goods goods) throws SQLException{
		sql = "insert into t_goods(name,price,image,gdesc,is_hot,cid) values(?,?,?,?,?,?)";
		return qr.update(sql, goods.getName(),goods.getPrice(),goods.getImage(),
		goods.getGdesc(),goods.getIs_hot(),goods.getCid());
	}
	
	//�޸���Ʒ
	public static int updataGoods(Goods goods) throws SQLException{
		sql = "update t_goods set name = ?, price = ?, image = ?,gdesc = ?,is_hot = ?,cid = ? where id = ?";
		//System.out.println(sql);
		return qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),
		goods.getGdesc(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}
	
	//ɾ����Ʒ
	public static int deleteGoods(int id) throws SQLException{
		sql = "delete from t_goods where id = ?";
		System.out.println(sql);
		return qr.update(sql,id);
	}

	//ͨ��id��ѯ��Ʒ
	public static Goods queryGoodsById(int id) throws SQLException {
		sql = "select * from t_goods where id = ?";
		return qr.query(sql, new BeanHandler<Goods>(Goods.class),id);
	}

	//��ѯ��Ʒ�ܼ�¼��
	public static long queryCount() throws SQLException {
		sql = "select count(*) from t_goods";
		long count = (long) qr.query(sql, new ScalarHandler());
		return count;
	}
	
	//��ѯ��ҳ����
	public static List<Goods> queryPageData(Integer index, Integer pageCount) throws SQLException {
		sql = "select * from t_goods limit ?,?";
		List<Goods> pageGoods = qr.query(sql, new BeanListHandler<Goods>(Goods.class),index,pageCount);
		return pageGoods;
	}

}
