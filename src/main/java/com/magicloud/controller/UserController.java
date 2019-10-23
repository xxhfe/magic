package com.magicloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magicloud.common.SessionUtil;
import com.magicloud.common.StateUtil;
import com.magicloud.dao.RoleDao;
import com.magicloud.dao.UserDao;
import com.magicloud.entity.Department;
import com.magicloud.entity.Power;
import com.magicloud.entity.Role;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.DepartmentService;
import com.magicloud.service.RoleService;
import com.magicloud.service.UserService;


@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserService userService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String userlist(Model model,HttpServletRequest request) {
		User user = MyInterceptor.getLocalUser();
		List<User> userlist = userService.getUserList(user.getLevel());  // 管理员列表
		List<Department> departmentlist = departmentService.getAllDepartment(); // 部门列表
		List<Role> rolelist = roleService.getRoleList(user.getLevel()); // 角色列表
		String userID = SessionUtil.getCookieID(user);
		model.addAttribute("userid",userID);
		model.addAttribute("userlist",userlist);
		model.addAttribute("departmentlist",departmentlist);
		model.addAttribute("rolelist",rolelist);
		model.addAttribute("stateMap",StateUtil.StateMap());
		Power power = MyInterceptor.getLocalPower();
		model.addAttribute("pageTitle", power.getMenuName());
		return "view/user/userlist";
	}
}
