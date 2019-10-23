package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.dao.RoleDao;
import com.magicloud.entity.Role;
import com.magicloud.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDao roleDao;
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.addRole(role);
	}
	@Override
	public List<Role> getRoleList(int level) {
		// TODO Auto-generated method stub
		return roleDao.getRoleList(level);
	}
	@Override
	public Role getRole(String roleID) {
		// TODO Auto-generated method stub
		return roleDao.getRole(roleID);
	}
	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.updateRole(role);
	}
}
