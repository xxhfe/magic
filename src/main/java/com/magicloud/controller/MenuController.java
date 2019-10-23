package com.magicloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magicloud.common.SessionUtil;
import com.magicloud.common.StateUtil;
import com.magicloud.entity.Menu;
import com.magicloud.entity.Power;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.MenuService;

@Controller
public class MenuController{
	@Autowired
	MenuService menuService;
	
	@RequestMapping(value="menulist")
	public String menulist(HttpServletRequest request,Model model) {
		Logger logger = LoggerFactory.getLogger(getClass());
		User user = MyInterceptor.getLocalUser();
		if(user.getLevel()<99) {
			return "login";
		}
		Map menumap = new HashMap<String,Object>();
		menumap.put("0","顶级菜单");
		List<Menu> menulist = menuService.getMenuList(user.getLevel());
		for(Menu m : menulist) {
			if("0".equals(m.getSuperiorId())) {
				menumap.put(m.getMenuId(),m.getMenuName());
			}
		}
		model.addAttribute("menulist",menulist);
		model.addAttribute("menumap",menumap);
		String userID = SessionUtil.getCookieID(user);
		model.addAttribute("userid",userID);
		model.addAttribute("stateMap",StateUtil.StateMap());
		model.addAttribute("havepower",MyInterceptor.getLocalPower());
		Power power = MyInterceptor.getLocalPower();
		model.addAttribute("pageTitle", power.getMenuName());
		return "view/menu/menulist";
		
	}
}