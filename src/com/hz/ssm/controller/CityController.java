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
		//获取jedis
		Jedis jedis = jedisPool.getResource();
		
		//先找redis再找数据库
		String cityListJsonStr = jedis.get("cityList");
		
		
		
		String jIdStr =  jedis.get("provinceId");
		
		int id = Integer.parseInt(jIdStr);
		if (cityListJsonStr == null || provinceId != id ) {
			//创建json转换工具类
			Gson gson = new Gson();
			//调用数据库查询数据
			List<City> cityList = cityServivce.findCityInfoByProvinceId(provinceId);
			//把数据转换为Json格式
			cityListJsonStr = gson.toJson(cityList);
			
			//把查询到的数据保存到redis中
			jedis.set("cityList", cityListJsonStr);
			
			//把redis中改变的数据保存到redis中
			jedis.set("provinceId", provinceId+"");
			
		}
		
		return cityListJsonStr;
	}

}
