package com.magicloud.entity;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{

	private String employeeId;//唯一id
	private String employeeName;//姓名
	private String employeeSex;//性别
	private String employeePhone;//联系电话
	private String employeeEmergencyName;//紧急联系人
	private String employeeEmergencyPhone;//紧急联系人电话
	private String employeeNowAddress;//现居住地址
	private String employeeIDAddress;//身份证居住地址
	private String employeeIDNo;//身份证号码
	private Date employeeBirthday;//生日
	private String employeeface;// 人脸照片
	private Date employeeEntryDate;//入职日期
	private String employeeDepartureDate;//离职日期
	private String jobId;//职位    // 实习， 普通员工，经理....
	private String stateId;//状态 //  在职， 离职，调休.......
	private String departmentId;//部门编号
	private String orgId;// 机构编号
	private Date createdate;
	
	// 关联字段
	private String departmentName;
	private String jobName;
	private String stateName;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String employeeId, String employeeName, String employeeSex, String employeePhone,
			String employeeEmergencyName, String employeeEmergencyPhone, String employeeNowAddress,
			String employeeIDAddress, String employeeIDNo, Date employeeBirthday, Date employeeEntryDate,
			String employeeDepartureDate, String jobId, String stateId, String departmentId, Date createdate,String employeeface) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSex = employeeSex;
		this.employeePhone = employeePhone;
		this.employeeEmergencyName = employeeEmergencyName;
		this.employeeEmergencyPhone = employeeEmergencyPhone;
		this.employeeNowAddress = employeeNowAddress;
		this.employeeIDAddress = employeeIDAddress;
		this.employeeIDNo = employeeIDNo;
		this.employeeBirthday = employeeBirthday;
		this.employeeEntryDate = employeeEntryDate;
		this.employeeDepartureDate = employeeDepartureDate;
		this.jobId = jobId;
		this.stateId = stateId;
		this.departmentId = departmentId;
		this.createdate = createdate;
		this.employeeface = employeeface;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSex() {
		return employeeSex;
	}

	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmergencyName() {
		return employeeEmergencyName;
	}

	public void setEmployeeEmergencyName(String employeeEmergencyName) {
		this.employeeEmergencyName = employeeEmergencyName;
	}

	public String getEmployeeEmergencyPhone() {
		return employeeEmergencyPhone;
	}

	public void setEmployeeEmergencyPhone(String employeeEmergencyPhone) {
		this.employeeEmergencyPhone = employeeEmergencyPhone;
	}

	public String getEmployeeNowAddress() {
		return employeeNowAddress;
	}

	public void setEmployeeNowAddress(String employeeNowAddress) {
		this.employeeNowAddress = employeeNowAddress;
	}

	public String getEmployeeIDAddress() {
		return employeeIDAddress;
	}

	public void setEmployeeIDAddress(String employeeIDAddress) {
		this.employeeIDAddress = employeeIDAddress;
	}

	public String getEmployeeIDNo() {
		return employeeIDNo;
	}

	public void setEmployeeIDNo(String employeeIDNo) {
		this.employeeIDNo = employeeIDNo;
	}

	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}

	public void setEmployeeBirthday(Date employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}

	public Date getEmployeeEntryDate() {
		return employeeEntryDate;
	}

	public void setEmployeeEntryDate(Date employeeEntryDate) {
		this.employeeEntryDate = employeeEntryDate;
	}

	public String getEmployeeDepartureDate() {
		return employeeDepartureDate;
	}

	public void setEmployeeDepartureDate(String employeeDepartureDate) {
		this.employeeDepartureDate = employeeDepartureDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEmployeeface() {
		return employeeface;
	}

	public void setEmployeeface(String employeeface) {
		this.employeeface = employeeface;
	}
	
}
