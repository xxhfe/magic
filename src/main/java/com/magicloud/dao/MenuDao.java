package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magicloud.entity.Menu;

@Mapper
public interface MenuDao {
	public Menu getMenu(String menuID);
	/**
	 * select * .. menu_id=param1 || superior_id=param2
	 * @param menuID
	 * @param superiorID
	 * @return
	 */
	public List<Menu> getMenu(String menuID,String superiorID);
	public List<Menu> getMenuList(int level);
	public void addMenu(Menu menu);
	public void updateMenu(Menu menu);
	public Menu getMenuByLink(String linkaddress);
}
