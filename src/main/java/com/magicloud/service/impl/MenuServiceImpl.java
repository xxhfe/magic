package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.dao.MenuDao;
import com.magicloud.entity.Menu;
import com.magicloud.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuDao menuDao;
	
	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.addMenu(menu);
	}
	@Override
	public Menu getMenu(String menuID) {
		// TODO Auto-generated method stub
		return menuDao.getMenu(menuID);
	}
	@Override
	public List<Menu> getMenu(String menuID, String superiorID) {
		// TODO Auto-generated method stub
		return menuDao.getMenu(menuID, superiorID);
	}
	@Override
	public List<Menu> getMenuList(int level) {
		// TODO Auto-generated method stub
		return menuDao.getMenuList(level);
	}
	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.updateMenu(menu);
	}
	@Override
	public Menu getMenuByLink(String link) {
		// TODO Auto-generated method stub
		return menuDao.getMenuByLink(link);
	}
}
