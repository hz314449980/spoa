package com.hz.ssm.service;

import java.util.List;

import com.hz.ssm.pojo.City;

public interface CityServivce {
	/**
	 * ����ʡ��id��ѯ��Ӧ��city��Ϣ
	 * @param provinceId
	 * @return
	 */
	public List<City> findCityInfoByProvinceId(Integer provinceId);

}
