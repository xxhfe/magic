package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.magicloud.entity.Power;
import com.magicloud.entity.Role;
import com.magicloud.entity.User;

@Mapper
public interface UserDao {
	public User getUser(String user);
	public List<User> getUserList(int level);
	public void addUser(User u);
	public void updUserForCount(User u);
}
