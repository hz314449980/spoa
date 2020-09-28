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
	
	
	//@ResponseBody注解作用就是把后台查询出的数据，以Json格式响应到前台目标页面
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
		//拼接省市和输入的值
		String custAddress = provinceName+city+address;
		
		customer.setCustAddress(custAddress);
		//获取文件名
		String  fileName = picFile.getOriginalFilename();
		
		
		if (org.apache.commons.lang.StringUtils.isNotBlank(fileName)) {
			//重命名
			String newFileName = StringUtils.subStringName(fileName);
			//创建file对象
			File file = new File("D:\\image\\"+newFileName);
			
			
			try {
				//执行文件上传file
				picFile.transferTo(file);
				
				customer.setPicFilepath(newFileName);
				//调用service,完成数据的持久化
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		int rows = customerService.addCustomerInfo(customer);
		

		return "redirect:/showCustomer.jsp";
	}
	
	

	
	
	

}
