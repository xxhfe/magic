package com.magicloud.service;

import java.util.List;

import com.magicloud.entity.Power;


public interface PowerService {

	/**
	 * 登录获取角色权限
	 * @param roleID:角色id
	 * @return
	 */
	public List<Power> getRolePower(String roleID);
	/**
	 * 权限赋予列表（返回所有可赋予的权限）
	 * @param roleID：标识已拥有的权限
	 * @return
	 */
	public List<Power> getPowerList(String roleID);
	
	/**
	 * 获得功能操作权限
	 * @param roleID
	 * @param menuID
	 * @return
	 */
	public Power getPowerByMenu(String roleID,String menuID);
	public void addPower(Power power);
	public void updatePower(Power power);
}
