package com.hz.ssm.utils;

import java.util.List;
/**
 * ��װ��ҳ�����Ĺ�����
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean<T> {
	
	private Integer indexpage;  //��ǰҳ   2
	
	private Integer pageSize;  //ÿҳ��С 5
	
	private Integer  beginRows;  //��ʼ��
	
	private Long countRows;  //�ܼ�¼��  select count(1) from  ��ǰ��
	
	private Integer totalPage;  //��ҳ��
	
	private List<T>  dataList;  //����ÿҳ������

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Integer getIndexpage() {
		return indexpage;
	}

	public void setIndexpage(Integer indexpage) {
		this.indexpage = indexpage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	//�Զ���ȡ��ʼ��
	public Integer getBeginRows() {
		
		
		return (getIndexpage() -1)*getPageSize();
	}


	public Long getCountRows() {
		return countRows;
	}

	public void setCountRows(Long countRows) {
		this.countRows = countRows;
	}

	//�Զ���ȡ��ҳ��
	public Integer getTotalPage() {
		
		
		return (int) ((getCountRows()%getPageSize()==0)?getCountRows()/getPageSize():getCountRows()/getPageSize()+1);
	}

	
	
	
}
