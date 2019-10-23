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
import com.magicloud.entity.Power;
import com.magicloud.entity.Role;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.RoleService;


@Controller
public class RoleController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/rolelist")
	public String rolelist(Model model,HttpServletRequest request) {
		User user = MyInterceptor.getLocalUser();
		String userID = SessionUtil.getCookieID(user);
		model.addAttribute("userid",userID);
		List<Role> rolelist = roleService.getRoleList(user.getLevel());
		model.addAttribute("rolelist",rolelist);
		model.addAttribute("stateMap",StateUtil.StateMap());
		model.addAttribute("havepower",MyInterceptor.getLocalPower());
		Power power = MyInterceptor.getLocalPower();
		model.addAttribute("pageTitle", power.getMenuName());
		return "view/role/rolelist";
	}
}
