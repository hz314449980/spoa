package com.hz.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.ssm.dao.CityMapper;
import com.hz.ssm.pojo.City;
import com.hz.ssm.pojo.CityExample;
import com.hz.ssm.pojo.CityExample.Criteria;


@Service
public class CityServivceImpl implements CityServivce {
	
	@Autowired
	private CityMapper cityMapper;

	@Override
	public List<City> findCityInfoByProvinceId(Integer provinceId) {
		CityExample example = new CityExample();
         //注意导入包的问题
		Criteria criteria =   example.createCriteria();
		criteria.andProvinceidEqualTo(provinceId);
		
		List<City>  cityList = cityMapper.selectByExample(example);
		
		
		System.out.println("cityList==="+cityList);
		return cityList;
	}
	
	
	
	
	
}
