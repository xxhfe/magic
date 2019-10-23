package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.common.Page;
import com.magicloud.dao.EmployeeDao;
import com.magicloud.entity.Employee;
import com.magicloud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;
	@Override
	public List<Employee> getEmployeeList(String parameter) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeList(parameter);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.addEmployee(employee);
	}
	@Override
	public Employee getEmployeeByID(String employeeID) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeByID(employeeID);
	}
	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.updateEmployee(employee);
	}
	@Override
	public void updateEmployeeState(String empID, String empState) {
		// TODO Auto-generated method stub
		employeeDao.updateEmployeeState(empID, empState);
	}
	@Override
	public List<Employee> getEmployeeListByJob(String parameter) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeListByJob(parameter);
	}
}
