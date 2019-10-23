package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magicloud.entity.Power;

@Mapper
public interface PowerDao {

	public List<Power> getRolePower(String roleID);
	public List<Power> getPowerList(String roleID);
	public void addPower(Power power);
	public void updatePower(Power power);
	public Power getPowerByMenu(String roleId,String menuId);
}
