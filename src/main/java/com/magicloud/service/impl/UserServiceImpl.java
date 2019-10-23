package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.dao.UserDao;
import com.magicloud.entity.User;
import com.magicloud.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUser(String user) {
		// TODO Auto-generated method stub
		return userDao.getUser(user);
	}
	@Override
	public List<User> getUserList(int level) {
		// TODO Auto-generated method stub
		return userDao.getUserList(level);
	}
	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		userDao.addUser(u);
	}
	@Override
	public void updUserForCount(User u) {
		// TODO Auto-generated method stub
		userDao.updUserForCount(u);
	}
}
