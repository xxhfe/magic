package com.magicloud.controller.business.menu;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magicloud.common.JsonUtil;
import com.magicloud.entity.Menu;
import com.magicloud.entity.SoupeModel;
import com.magicloud.service.MenuService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/menu")
public class MenuBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MenuService menuService;
	
	@ResponseBody
	@ApiOperation(value="添加菜单", notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "superiorId", value = "父菜单ID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "menuName", value = "菜单名称", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "menuLink", value = "菜单地址", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "topsort", value = "父菜单排序", required = true, dataType = "int"),
		@ApiImplicitParam(paramType = "query", name = "sort", value = "菜单排序", required = true, dataType = "int") })
	@RequestMapping(value="/addmenu",method = RequestMethod.POST)
	public String addmenu(@ModelAttribute("menu") Menu menu,HttpServletRequest request) {
		SoupeModel spm = new SoupeModel();
		menu.setMenuId(UUID.randomUUID().toString());
		menu.setState("1");
		menu.setLevel(99);
		try {
			menuService.addMenu(menu);
			spm.setStatus("success");
			return JsonUtil.toJson(spm);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			spm.setStatus("err");
			return JsonUtil.toJson(spm);
		}
		
	}
	
}
