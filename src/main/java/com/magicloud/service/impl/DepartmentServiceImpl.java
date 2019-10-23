package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.common.Page;
import com.magicloud.dao.DepartmentDao;
import com.magicloud.entity.Department;
import com.magicloud.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public void addDepartment(Department d) {
		// TODO Auto-generated method stub
		departmentDao.addDepartment(d);
	}
	@Override
	public List<Department> getDepartmentList(Page page) {
		// TODO Auto-generated method stub
		return departmentDao.getDepartmentList(page);
	}
	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.getAllDepartment();
	}
}
