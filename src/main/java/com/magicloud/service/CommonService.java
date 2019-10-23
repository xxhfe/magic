package com.magicloud.service;

public interface CommonService {

	/**
	 * 查询总记录数
	 * @param parameter  表名和条件
	 * @return
	 */
	public int getTotal(String parameter);
}
