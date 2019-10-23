package com.magicloud.controller.business.department;

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

import com.magicloud.common.CodeUtil;
import com.magicloud.common.JsonUtil;
import com.magicloud.common.StringUtil;
import com.magicloud.entity.Department;
import com.magicloud.entity.SoupeModel;
import com.magicloud.service.DepartmentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/department")
public class DepartmentBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	DepartmentService departmentService;
	
	
	@ResponseBody
	@ApiOperation(value="添加部门", notes="")
	@ApiImplicitParam(paramType="query", name = "departmentName", value = "部门名称", required = true, dataType = "String")
	@RequestMapping(value="/adddepartm",method = RequestMethod.POST)
	public String addde(@RequestParam("departmentName")String departmentName,HttpServletRequest request) {
		SoupeModel spm = new SoupeModel();
		
		if(StringUtil.isNULL(departmentName)) {
			spm.setStatus(CodeUtil.PARAMETER_NULL);
			spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
			return JsonUtil.toJson(spm);
		}
		try {
			Department dd = new Department();
			dd.setDepartmentId(UUID.randomUUID().toString());
			dd.setDepartmentName(departmentName);
			dd.setState("1");
			departmentService.addDepartment(dd);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
}
