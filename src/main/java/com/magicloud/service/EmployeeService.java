package com.magicloud.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.magicloud.common.Page;
import com.magicloud.entity.Employee;

public interface EmployeeService {

	/**
	 * 员工列表
	 * @return
	 */
	public List<Employee> getEmployeeList(String parameter);
	public void addEmployee(Employee employee);
	public Employee getEmployeeByID(String employeeID);
	public void updateEmployee(Employee employee);
	/**
	 * 更新状态
	 * @param empID
	 */
	public void updateEmployeeState(String empID,String empState);
	/**
	 * 根据职位获取员工
	 * @param parameter
	 * @return
	 */
	public List<Employee> getEmployeeListByJob(String parameter);
}
