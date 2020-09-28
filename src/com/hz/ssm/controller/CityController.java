package com.hz.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.gson.Gson;
import com.hz.ssm.pojo.City;
import com.hz.ssm.service.CityServivce;

@Controller
@RequestMapping("/city")
public class CityController {
	
	@Resource(name="jedisPool")
	private JedisPool jedisPool;
	
	@Autowired
	private CityServivce cityServivce;
	
	
	@ResponseBody
	@RequestMapping("/findCityInfoByProvinceId")
	public String findCityInfoByProvinceId(Integer provinceId){
		//��ȡjedis
		Jedis jedis = jedisPool.getResource();
		
		//����redis�������ݿ�
		String cityListJsonStr = jedis.get("cityList");
		
		
		
		String jIdStr =  jedis.get("provinceId");
		
		int id = Integer.parseInt(jIdStr);
		if (cityListJsonStr == null || provinceId != id ) {
			//����jsonת��������
			Gson gson = new Gson();
			//�������ݿ��ѯ����
			List<City> cityList = cityServivce.findCityInfoByProvinceId(provinceId);
			//������ת��ΪJson��ʽ
			cityListJsonStr = gson.toJson(cityList);
			
			//�Ѳ�ѯ�������ݱ��浽redis��
			jedis.set("cityList", cityListJsonStr);
			
			//��redis�иı�����ݱ��浽redis��
			jedis.set("provinceId", provinceId+"");
			
		}
		
		return cityListJsonStr;
	}

}
