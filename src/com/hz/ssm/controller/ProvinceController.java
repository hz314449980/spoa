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
		//��ȡjedis����
		Jedis jedis = jedisPool.getResource();
		
		//������redis�������ݿ�
		String provinListStr = jedis.get("provinList");
		//����ȸ��ṩ��һ����java�õ�������ת��Ϊjson��ʽ
		Gson gson = new Gson();
		
		if (StringUtils.isBlank(provinListStr)) {
			//��Ҫ��ԭ����д����һ��ı�,����ǵ�һ�ν�������redis�����ݿ�϶�û��
			List<Province> provinList = provinceService.findProInfo();

			 provinListStr =  gson.toJson(provinList);
			
			//�Ѵ����ݿ��в�ѯ����ֵ�����û��߱��浽redis��
			jedis.set("provinList", provinListStr);
		
		}
		
	
		
		
		
		
		
		
		
		
		
		return provinListStr;
	}
	

}
