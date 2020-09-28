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
import com.hz.ssm.pojo.Province;
import com.hz.ssm.service.ProvinceService;
@Controller
@RequestMapping("/pro")
public class ProvinceController {
	@Autowired
	private ProvinceService provinceService;
	
	
	
	@Resource(name="jedisPool")
	private JedisPool jedisPool;
	
	@ResponseBody
	@RequestMapping("/findAllProInfo")
	public String  findAllProInfo(){
		//获取jedis连接
		Jedis jedis = jedisPool.getResource();
		
		//我先找redis再找数据库
		String provinListStr = jedis.get("provinList");
		//这个谷歌提供的一个把java得到的数据转换为json格式
		Gson gson = new Gson();
		
		if (StringUtils.isBlank(provinListStr)) {
			//需要对原来的写法有一点改变,如果是第一次进来，从redis找数据库肯定没有
			List<Province> provinList = provinceService.findProInfo();

			 provinListStr =  gson.toJson(provinList);
			
			//把从数据库中查询到的值，设置或者保存到redis中
			jedis.set("provinList", provinListStr);
		
		}
		
	
		
		
		
		
		
		
		
		
		
		return provinListStr;
	}
	

}
