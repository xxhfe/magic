package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magicloud.common.Page;
import com.magicloud.entity.Department;
@Mapper
public interface DepartmentDao {
	public List<Department> getDepartmentList(Page page);
	public List<Department> getAllDepartment();
	public void addDepartment(Department d);
}
