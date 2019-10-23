package com.magicloud.entity;

import java.io.Serializable;

/**
 * 用户表
 * @author pc
 *
 */
public class User implements Serializable{

	private String userId;  //用户唯一id
	private String user;    //帐号
	private String pwd;     //密码
	private String state;      //账户状态，0:删除，1:正常，2:冻结
	private int level;      //可见等级
	private String roleId;  //角色id
	private String departmentId;  // 部门id
	private String username; //姓名
	private int errcount;
	// 
	private String roleName;
	private String token;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userId, String user, String pwd, String state, int level, String roleId,String departmentId,String username,String token,int errcount) {
		super();
		this.userId = userId;
		this.user = user;
		this.pwd = pwd;
		this.state = state;
		this.level = level;
		this.roleId = roleId;
		this.departmentId = departmentId;
		this.username = username;
		this.token = token;
		this.errcount = errcount;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getErrcount() {
		return errcount;
	}
	public void setErrcount(int errcount) {
		this.errcount = errcount;
	}
}
