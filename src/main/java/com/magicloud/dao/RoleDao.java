package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.magicloud.entity.Role;
@Mapper
public interface RoleDao {
	public Role getRole(String roleID);
	public List<Role> getRoleList(int level);
	public void addRole(Role role);
	public void updateRole(Role role);
}
