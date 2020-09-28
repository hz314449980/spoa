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
	 * �����pagehelper��̨��ҳ�����ɷ�ҳ
	 * 1���������jar
	 * 2.��ʼ����ҳ����
	 * 3.��ѯ��ǰҳ����
	 * 4.��װ��pageInfo��
	 * 5.�����ݷ�װ��ĳ����װ���ݵĹ�����
	 */
	@Override
	public PageBean<Customer> findCustByPage(Integer indexpage,String custName,String custLevel) {
		//Ĭ����ҳ
		if (indexpage == null) {
			indexpage = 1;
		}
		
		//2.��ʼ����ҳ����PageHelper.startPage("��ǰҳ��", "ÿҳ��ʾ����")
		PageHelper.startPage(indexpage, 5);
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		
		custName = "%"+custName+ "%";
		custLevel = "%"+custLevel+ "%";
		//�ڴ��ݵ��־ò���Ҫƴ��%
		criteria.andCustNameLike(custName);
		criteria.andCustLevelLike(custLevel);
		
		
		
		//3��ȡ��ǰҳ������
		List<Customer> custList = customerMapper.selectByExample(example);
		
		//4�������ݹ���pageInfo��
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(custList);
		
		System.out.println("�ܵļ�¼��==="+pageInfo.getTotal());
		System.out.println("ÿҳ��С==="+pageInfo.getPageSize());
		System.out.println("�ܵ�ҳ��==="+pageInfo.getPages());
		System.out.println("��ǰҳ����==="+pageInfo.getList());
		
		//5.��service������ݷ�װ��һ������
		
		PageBean<Customer>  pageBean = new PageBean<Customer>();
		pageBean.setIndexpage(indexpage);
		pageBean.setPageSize(pageInfo.getPageSize());
		pageBean.setCountRows(pageInfo.getTotal());
		pageBean.setDataList(custList);
		
		
		return pageBean;
	}


	@Override
	public PageInfo<Customer> findCustByPage2(Integer indexpage) {
		//Ĭ����ҳ
				if (indexpage == null) {
					indexpage = 1;
				}
				
				//2.��ʼ����ҳ����PageHelper.startPage("��ǰҳ��", "ÿҳ��ʾ����")
				PageHelper.startPage(indexpage, 5);
				CustomerExample example = new CustomerExample();
				//3��ȡ��ǰҳ������
				List<Customer> custList = customerMapper.selectByExample(example);
				
				//4�������ݹ���pageInfo��
				PageInfo<Customer> pageInfo = new PageInfo<Customer>(custList);
				
				System.out.println("�ܵļ�¼��==="+pageInfo.getTotal());
				System.out.println("ÿҳ��С==="+pageInfo.getPageSize());
				System.out.println("�ܵ�ҳ��==="+pageInfo.getPages());
				System.out.println("��ǰҳ����==="+pageInfo.getList());
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
