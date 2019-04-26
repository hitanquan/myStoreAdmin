package com.admin.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ��Ʒ��Ϣ��ҳ��ʾʵ����
 * @author tq
 */
@Setter@Getter
public class pageBean {
	
	//���浱ǰҳ��Ʒ��Ϣ�ļ���
	private List<Goods> goodsList = new ArrayList<Goods>();
	//���浱ǰҳ��ŵı���
	private Integer currentPage;
	//������ҳ���ı���
	private Integer totalPage;
	//�����ܼ�¼���ı���
	private Integer totalCount;
	
	@Override
	public String toString() {
		return "pageBean [goodsList=" + goodsList + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + "]";
	}
}
