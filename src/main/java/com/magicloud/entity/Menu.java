package com.magicloud.entity;

import java.io.Serializable;

/**
 * 菜单表
 * @author pc
 *
 */
public class Menu implements Serializable{

	private String menuId;
	private String superiorId;
	private String menuName;
	private String menuLink;
	private String state;
	private int level;
	private int sort;
	private int topsort;
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(String menuId, String superiorId, String menuName, String menuLink, String state, int level, int sort,int topsort) {
		super();
		this.menuId = menuId;
		this.superiorId = superiorId;
		this.menuName = menuName;
		this.menuLink = menuLink;
		this.state = state;
		this.level = level;
		this.sort = sort;
		this.topsort=topsort;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSuperiorId() {
		return superiorId;
	}
	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getTopsort() {
		return topsort;
	}
	public void setTopsort(int topsort) {
		this.topsort = topsort;
	}
}
