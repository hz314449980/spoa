package com.hz.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.ssm.dao.ProvinceMapper;
import com.hz.ssm.pojo.Province;
import com.hz.ssm.pojo.ProvinceExample;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	  
	  @Autowired
	  private ProvinceMapper provinceMapper;
	
	  
	  public List<Province> findProInfo(){
		 ProvinceExample example = new ProvinceExample();
		 List<Province> proList = provinceMapper.selectByExample(example);
		  
		 System.out.println("proList==="+proList);
		  return proList;
	  }
	  
		
		
		
		
		
	
}
