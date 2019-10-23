package com.magicloud.entity;

/**
 * 角色表
 * @author pc
 *
 */
public class Role {

	private String roleId;
	private String roleName;
	private String state;
	private int level;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Role(String roleId, String roleName, String state, int level) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.state = state;
		this.level = level;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
}
