package com.hz.ssm.service;

import java.util.List;

import com.hz.ssm.pojo.City;

public interface CityServivce {
	/**
	 * 根据省份id查询对应的city信息
	 * @param provinceId
	 * @return
	 */
	public List<City> findCityInfoByProvinceId(Integer provinceId);

}
