package com.magicloud.service;

import java.util.List;

import com.magicloud.entity.Role;

public interface RoleService {

	public Role getRole(String roleID);
	public List<Role> getRoleList(int level);
	public void addRole(Role role);
	public void updateRole(Role role);
}
