package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.magicloud.common.Page;
import com.magicloud.entity.Employee;
@Mapper
public interface EmployeeDao {
	public List<Employee> getEmployeeList(@Param("parameter")String parameter);
	public void addEmployee(Employee employee);
	public Employee getEmployeeByID(String employeeID);
	public void updateEmployee(Employee employee);
	/**
	 * 更新状态
	 * @param empID
	 */
	public void updateEmployeeState(String empID,String empState);
	public List<Employee> getEmployeeListByJob(@Param("parameter")String parameter);
}
