package com.magicloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonDao {

	public int getTotal(@Param("parameter") String parameter);
}
