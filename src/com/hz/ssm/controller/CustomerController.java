package com.hz.ssm.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.hz.ssm.pojo.Customer;
import com.hz.ssm.service.CustomerService;
import com.hz.ssm.utils.PageBean;
import com.hz.ssm.utils.StringUtils;

@Controller
@RequestMapping("/cust/api/v_1.0")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	
	//@ResponseBodyע�����þ��ǰѺ�̨��ѯ�������ݣ���Json��ʽ��Ӧ��ǰ̨Ŀ��ҳ��
	@ResponseBody
	@RequestMapping("/findCustByPage")
	public PageBean<Customer> findCustByPage(Integer indexpage,String custName,String custLevel){
		
		System.out.println("custName="+custName+",custLevel="+custLevel);
		
		PageBean<Customer> pageBean = customerService.findCustByPage(indexpage,custName,custLevel);
		
		
		return pageBean;
	}
	
	@ResponseBody
	@RequestMapping("/findCustByPage2")
	public PageInfo<Customer> findCustByPage2(Integer indexpage){
		
		PageInfo<Customer> Info = customerService.findCustByPage2(indexpage);
		
		
		return Info;
	}
	
	@RequestMapping("/delCustById")
	public String delCustById(Long custId){
		
		System.out.println("id=="+custId);
		int rows = customerService.delCustById(custId);
		
		return "redirect:/showCustomer.jsp";
	}
	
	@RequestMapping("/addCustomerInfo")
	public String addCustomerInfo(String provinceName,String city,String address,MultipartFile picFile,Customer customer){
		
		System.out.println("province=="+provinceName);
		System.out.println("city=="+city);
		System.out.println("address=="+address);
		//ƴ��ʡ�к������ֵ
		String custAddress = provinceName+city+address;
		
		customer.setCustAddress(custAddress);
		//��ȡ�ļ���
		String  fileName = picFile.getOriginalFilename();
		
		
		if (org.apache.commons.lang.StringUtils.isNotBlank(fileName)) {
			//������
			String newFileName = StringUtils.subStringName(fileName);
			//����file����
			File file = new File("D:\\image\\"+newFileName);
			
			
			try {
				//ִ���ļ��ϴ�file
				picFile.transferTo(file);
				
				customer.setPicFilepath(newFileName);
				//����service,������ݵĳ־û�
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		int rows = customerService.addCustomerInfo(customer);
		

		return "redirect:/showCustomer.jsp";
	}
	
	

	
	
	

}
