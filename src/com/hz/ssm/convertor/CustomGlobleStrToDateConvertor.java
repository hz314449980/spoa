package com.hz.ssm.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义字符串转日期的类
 * 
 * Source  源    String数据
 * Target  目标  date  数据
 * @author Administrator
 *
 */
public class CustomGlobleStrToDateConvertor implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		try {
			//注意当前的pattern的格式应该和前端显示的格式一致
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
