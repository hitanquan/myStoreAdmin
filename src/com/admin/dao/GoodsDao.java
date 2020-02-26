package com.admin.dao;

import com.admin.domain.Goods;
import com.admin.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品数据操纵类
 * @author 谭少
 */
public class GoodsDao {
	
	private static QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	private static String sql = null;
	
	//获取所有商品
	public static List<Goods>queryAllGoods() throws SQLException{
		sql = "select * from t_goods";
		return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
	}
	
	//添加商品
	public static int insertGoods(Goods goods) throws SQLException{
		sql = "insert into t_goods(name,price,image,gdesc,is_hot,cid) values(?,?,?,?,?,?)";
		return qr.update(sql, goods.getName(),goods.getPrice(),goods.getImage(),
		goods.getGdesc(),goods.getIs_hot(),goods.getCid());
	}
	
	//修改商品
	public static int updataGoods(Goods goods) throws SQLException{
		sql = "update t_goods set name = ?, price = ?, image = ?,gdesc = ?,is_hot = ?,cid = ? where id = ?";
		return qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),
		goods.getGdesc(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}
	
	//删除商品
	public static int deleteGoods(int id) throws SQLException{
		sql = "delete from t_goods where id = ?";
		return qr.update(sql,id);
	}

	//通过id查询商品
	public static Goods queryGoodsById(int id) throws SQLException {
		sql = "select * from t_goods where id = ?";
		return qr.query(sql, new BeanHandler<Goods>(Goods.class),id);
	}

	//查询商品总记录数
	public static long queryCount() throws SQLException {
		sql = "select count(*) from t_goods";
        return (long) qr.query( sql, new ScalarHandler() );
	}
	
	//查询分页数据
	public static List<Goods> queryPageData(Integer index, Integer pageCount) throws SQLException {
		sql = "select * from t_goods limit ?,?";
        return qr.query( sql, new BeanListHandler<Goods>( Goods.class ), index, pageCount );
	}
}
