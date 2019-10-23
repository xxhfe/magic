package com.magicloud.service;

import java.util.List;

import com.magicloud.common.Page;
import com.magicloud.entity.Department;

public interface DepartmentService {

	public List<Department> getDepartmentList(Page page);
	public List<Department> getAllDepartment();
	public void addDepartment(Department d);
}
