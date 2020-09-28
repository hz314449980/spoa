package com.hz.ssm.service;

import java.util.List;

import com.hz.ssm.pojo.Province;

public interface ProvinceService {
	/**
	 * 查询所有省份信息
	 * @return
	 */
	 public List<Province> findProInfo();

}
