package com.magicloud.controller.business.role;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magicloud.common.JsonUtil;
import com.magicloud.entity.Role;
import com.magicloud.entity.SoupeModel;
import com.magicloud.entity.User;
import com.magicloud.service.RoleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/role")
public class RoleBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	RoleService roleService;
	
	@ResponseBody
	@ApiOperation(value="添加角色", notes="")
	@ApiImplicitParam(paramType="query", name = "roleName", value = "角色名称", required = true, dataType = "String")
	@RequestMapping(value="/addrole",method = RequestMethod.POST)
	public String addrole(@RequestParam("roleName")String roleName,HttpServletRequest request) {

		Role role = new Role();
		role.setRoleId(UUID.randomUUID().toString());
		role.setRoleName(roleName);
		role.setState("1");
		role.setLevel(((User)request.getSession().getAttribute("user")).getLevel()-1);
		roleService.addRole(role);
		SoupeModel spm = new SoupeModel();
		spm.setStatus("success");
		return JsonUtil.toJson(spm);
	}
	
}
