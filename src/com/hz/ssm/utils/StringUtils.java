package com.hz.ssm.utils;

import java.util.UUID;

public class StringUtils {
	
	public static void main(String[] args) {
		String nameString = subStringName("0012.jpg");
		
		System.out.println(nameString);
	}
	
	/**
	 * ��ȡ�ļ����ĺ�׺,��������ͼƬ����
	 * @param name
	 * @return
	 */
	public static String  subStringName(String name){
	
		String replace = UUID.randomUUID().toString().replace("-", "");
		
		String uuidStr = replace.substring(replace.length()-10, replace.length());
		
		String subStr = name.substring(name.lastIndexOf("."), name.length());
		
		
		
		
		return uuidStr+subStr;
	}
	
	
	

}
