package com.magicloud.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magicloud.common.SessionUtil;
import com.magicloud.entity.Power;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.PowerService;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	StringRedisTemplate redisTemplate;
	@Autowired
	PowerService powerService;

	// 登录页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request,HttpServletResponse response) {
		User user = MyInterceptor.getLocalUser();
		String userID = SessionUtil.getCookieID(user);
		Cookie cookie = new Cookie(userID, user.getToken());
		response.addCookie(cookie);
		List<Power> powerlist = powerService.getRolePower(user.getRoleId());
		model.addAttribute("powerlist", powerlist);
		model.addAttribute("userid", userID);
		model.addAttribute("rolename", user.getRoleName());
		model.addAttribute("username", user.getUsername());
		
		
		return "view/index";
	}
}
