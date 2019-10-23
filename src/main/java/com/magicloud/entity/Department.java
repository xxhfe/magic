package com.magicloud.entity;

import java.io.Serializable;

public class Department implements Serializable{

	private int sid;
	private String departmentId;
	private String departmentName;
	private String state;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(int sid, String departmentId, String departmentName,String state) {
		super();
		this.sid = sid;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.state = state;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
