package com.magicloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magicloud.common.Page;
import com.magicloud.common.SessionUtil;
import com.magicloud.common.StateUtil;
import com.magicloud.common.StringUtil;
import com.magicloud.entity.Department;
import com.magicloud.entity.Power;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.CommonService;
import com.magicloud.service.DepartmentService;

@Controller
public class DepartmentController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	DepartmentService departmentService;
	@Autowired
	CommonService commonService;
	
	@RequestMapping(value="departmentlist")
	public String departmentlist(Model model,@ModelAttribute("page")Page page,HttpServletRequest request) {
		int recordTotal = commonService.getTotal("department");
		page = Page.forInstance(recordTotal,page.getCurrentPageNo(),page.getMaxResults(),"/departmentlist");
		
		User user = MyInterceptor.getLocalUser();
		List<Department> list = departmentService.getDepartmentList(page);
		model.addAttribute("departmentlist",list);
		model.addAttribute("stateMap",StateUtil.StateMap());
		String userID = SessionUtil.getCookieID(user);
		model.addAttribute("userid",userID);
		model.addAttribute("page",page);
		Power power = MyInterceptor.getLocalPower();
		model.addAttribute("pageTitle", power.getMenuName());
		return "view/department/departmentlist";
	}
	@RequestMapping(value="departmentadd")
	public String departmentadd(HttpServletRequest request) {
		
		return "/departmentlist";
	}
}
