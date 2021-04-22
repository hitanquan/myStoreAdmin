package com.admin.service;

import com.admin.dao.GoodsDao;
import com.admin.domain.Goods;
import com.admin.domain.PageBean;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * ��Ʒҵ����
 * @author ̷��
 */
public class GoodsService {

	public PageBean getPageBean(int currentPage) throws SQLException {
		PageBean pageBean = new PageBean();
		//���õ�ǰҳ��
		pageBean.setCurrentPage(currentPage);
		//��ѯ�ܼ�¼��
		Long totalCount = GoodsDao.queryCount();
		//�����ܼ�¼��
		pageBean.setTotalCount(totalCount.intValue());
		//��ÿҳ��ʾ5������
		Integer pageCount = 5;
		//��ȡ��ҳ�����ܼ�¼��/ÿҳ��¼����
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);
		//������ҳ��
		pageBean.setTotalPage((int)totalPage);
		//��ǰҳ��ѯ�ĽǱ�
		Integer index = (pageBean.getCurrentPage()-1) * pageCount;
		//��ȡ��ҳ��Ʒ
		List<Goods> goodsList = GoodsDao.queryPageData(index,pageCount);
		//���Ϸ�ת���ú���ӵ�������ʾ��ǰ��
		Collections.reverse( goodsList );
		//������Ʒ��Ϣ
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
