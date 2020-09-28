package com.hz.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hz.ssm.pojo.Customer;
import com.hz.ssm.utils.PageBean;

public interface CustomerService {

	/**
	 * 分页查询的方法
	 * pagehelper后台分页插件
	 * @param indexpage  当前页码
	 * @return
	 */
	PageBean<Customer> findCustByPage(Integer indexpage,String custName,String custLevel);
	
	PageInfo<Customer> findCustByPage2(Integer indexpage);

	/**
	 * 根据Id删除的方法
	 * @param custId
	 * @return
	 */
	int delCustById(Long custId);
	/**
	 * 添加客户对象的方法
	 * @param customer
	 * @return
	 */
	int addCustomerInfo(Customer customer);

}
