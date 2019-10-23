package com.magicloud.service;

import java.util.List;

import com.magicloud.entity.Menu;


public interface MenuService {
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
	public Menu getMenuByLink(String link);
}
