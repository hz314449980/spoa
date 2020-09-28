package com.hz.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hz.ssm.pojo.Customer;
import com.hz.ssm.utils.PageBean;

public interface CustomerService {

	/**
	 * ��ҳ��ѯ�ķ���
	 * pagehelper��̨��ҳ���
	 * @param indexpage  ��ǰҳ��
	 * @return
	 */
	PageBean<Customer> findCustByPage(Integer indexpage,String custName,String custLevel);
	
	PageInfo<Customer> findCustByPage2(Integer indexpage);

	/**
	 * ����Idɾ���ķ���
	 * @param custId
	 * @return
	 */
	int delCustById(Long custId);
	/**
	 * ��ӿͻ�����ķ���
	 * @param customer
	 * @return
	 */
	int addCustomerInfo(Customer customer);

}
