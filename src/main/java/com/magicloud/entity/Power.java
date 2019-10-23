package com.magicloud.entity;

import java.io.Serializable;
/**
 * 权限表
 * @author pc
 *
 */
public class Power implements Serializable{

	private String powerId;   // 权限ID
	private String menuId;    // 菜单ID
	private String roleId;    // 角色ID
	private String powerAdd;  // 增加权限
	private String powerDel;  // 删除权限
  	private String powerUpd;  // 更新权限
	private String powerSel;  // 查询权限
	private String powerFile; // 文件操作权限
	private String state;     // 权限状态
	private int level;        // 可见等级
	
	/***
	 * 菜单部分字段
	 */
	private String menuName;   // 菜单名称
	private String menuLink;   // 菜单地址
	private String superNode;  // 上级菜单id
	private String ico;
	
	public Power() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Power(String powerId, String menuId, String roleId, String powerAdd, String powerDel, String powerUpd,
			String powerSel, String powerFile, String state, int level, String menuName, String menuLink,
			String superNode, String ico) {
		super();
		this.powerId = powerId;
		this.menuId = menuId;
		this.roleId = roleId;
		this.powerAdd = powerAdd;
		this.powerDel = powerDel;
		this.powerUpd = powerUpd;
		this.powerSel = powerSel;
		this.powerFile = powerFile;
		this.state = state;
		this.level = level;
		this.menuName = menuName;
		this.menuLink = menuLink;
		this.superNode = superNode;
		this.ico = ico;
	}



	public String getPowerId() {
		return powerId;
	}
	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getPowerAdd() {
		return powerAdd;
	}
	public void setPowerAdd(String powerAdd) {
		this.powerAdd = powerAdd;
	}
	public String getPowerDel() {
		return powerDel;
	}
	public void setPowerDel(String powerDel) {
		this.powerDel = powerDel;
	}
	public String getPowerUpd() {
		return powerUpd;
	}
	public void setPowerUpd(String powerUpd) {
		this.powerUpd = powerUpd;
	}
	public String getPowerSel() {
		return powerSel;
	}
	public void setPowerSel(String powerSel) {
		this.powerSel = powerSel;
	}
	public String getPowerFile() {
		return powerFile;
	}
	public void setPowerFile(String powerFile) {
		this.powerFile = powerFile;
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
	public String getSuperNode() {
		return superNode;
	}
	public void setSuperNode(String superNode) {
		this.superNode = superNode;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	
}
