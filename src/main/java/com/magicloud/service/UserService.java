package com.magicloud.service;

import java.util.List;

import com.magicloud.entity.User;

public interface UserService {

	public User getUser(String user);
	public List<User> getUserList(int level);
	public void addUser(User u);
	public void updUserForCount(User u);
}
