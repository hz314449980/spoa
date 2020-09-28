package com.hz.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.ssm.dao.CustomerMapper;
import com.hz.ssm.pojo.Customer;
import com.hz.ssm.pojo.CustomerExample;
import com.hz.ssm.pojo.CustomerExample.Criteria;
import com.hz.ssm.utils.PageBean;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	
	/**
	 * 向采用pagehelper后台分页插件完成分页
	 * 1。导入相关jar
	 * 2.初始化分页参数
	 * 3.查询当前页数据
	 * 4.封装到pageInfo中
	 * 5.把数据封装成某个封装数据的工具类
	 */
	@Override
	public PageBean<Customer> findCustByPage(Integer indexpage,String custName,String custLevel) {
		//默认首页
		if (indexpage == null) {
			indexpage = 1;
		}
		
		//2.初始化分页参数PageHelper.startPage("当前页码", "每页显示条数")
		PageHelper.startPage(indexpage, 5);
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		
		custName = "%"+custName+ "%";
		custLevel = "%"+custLevel+ "%";
		//在传递到持久层需要拼接%
		criteria.andCustNameLike(custName);
		criteria.andCustLevelLike(custLevel);
		
		
		
		//3获取当前页的数据
		List<Customer> custList = customerMapper.selectByExample(example);
		
		//4，把数据灌入pageInfo中
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(custList);
		
		System.out.println("总的记录数==="+pageInfo.getTotal());
		System.out.println("每页大小==="+pageInfo.getPageSize());
		System.out.println("总的页数==="+pageInfo.getPages());
		System.out.println("当前页数据==="+pageInfo.getList());
		
		//5.在service层把数据封装到一个类型
		
		PageBean<Customer>  pageBean = new PageBean<Customer>();
		pageBean.setIndexpage(indexpage);
		pageBean.setPageSize(pageInfo.getPageSize());
		pageBean.setCountRows(pageInfo.getTotal());
		pageBean.setDataList(custList);
		
		
		return pageBean;
	}


	@Override
	public PageInfo<Customer> findCustByPage2(Integer indexpage) {
		//默认首页
				if (indexpage == null) {
					indexpage = 1;
				}
				
				//2.初始化分页参数PageHelper.startPage("当前页码", "每页显示条数")
				PageHelper.startPage(indexpage, 5);
				CustomerExample example = new CustomerExample();
				//3获取当前页的数据
				List<Customer> custList = customerMapper.selectByExample(example);
				
				//4，把数据灌入pageInfo中
				PageInfo<Customer> pageInfo = new PageInfo<Customer>(custList);
				
				System.out.println("总的记录数==="+pageInfo.getTotal());
				System.out.println("每页大小==="+pageInfo.getPageSize());
				System.out.println("总的页数==="+pageInfo.getPages());
				System.out.println("当前页数据==="+pageInfo.getList());
		return pageInfo;
	}


	@Override
	public int delCustById(Long custId) {
	
		return customerMapper.deleteByPrimaryKey(custId);

	}


	@Override
	public int addCustomerInfo(Customer customer) {
		int rows = customerMapper.insertSelective(customer);
		System.out.println("rows==="+rows);
		return rows;
	}

}
