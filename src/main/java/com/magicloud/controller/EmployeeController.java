package com.magicloud.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magicloud.common.EmployeeStateUtil;
import com.magicloud.common.Page;
import com.magicloud.common.SessionUtil;
import com.magicloud.common.StringUtil;
import com.magicloud.entity.Department;
import com.magicloud.entity.Employee;
import com.magicloud.entity.Job;
import com.magicloud.entity.Power;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.CommonService;
import com.magicloud.service.DepartmentService;
import com.magicloud.service.EmployeeService;
import com.magicloud.service.JobService;

@Controller
public class EmployeeController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	JobService jobService;
	@Autowired
	CommonService commonService;
	
	@RequestMapping(value = "/employeelist", method = RequestMethod.GET)
	public String employeelist(@ModelAttribute("employee")Employee employee,@ModelAttribute("page")Page page,Model model, HttpServletRequest request,HttpServletResponse response) {
		StringBuffer sql = new StringBuffer();
		sql.append("where state_id <> 100 ");
		if(!StringUtil.isNULL(employee.getEmployeeName())) {
			sql.append("and s_employee_name like '%"+employee.getEmployeeName().trim()+"%' ");
		}
		if(!StringUtil.isNULL(employee.getEmployeePhone())) {
			sql.append("and s_employee_phone like '%"+employee.getEmployeePhone().trim()+"%' ");
		}
		if(!StringUtil.isNULL(employee.getDepartmentId())) {
			sql.append("and department_id ='"+employee.getDepartmentId().trim()+"' ");
		}
		if(!StringUtil.isNULL(employee.getJobId())) {
			sql.append("and job_id ='"+employee.getJobId().trim()+"' ");
		}
		// 分页
		int recordTotal = commonService.getTotal("employee "+sql.toString());
		page = Page.forInstance(recordTotal,page.getCurrentPageNo(),page.getMaxResults(),"/employeelist");
		sql.append(" limit "+(page.getCurrentPageNo()-1)*page.getMaxResults()+","+page.getMaxResults());
		
		Power power = MyInterceptor.getLocalPower();
		User user = MyInterceptor.getLocalUser();
		String userID = SessionUtil.getCookieID(user);
		List<Employee> employeelist = employeeService.getEmployeeList(sql.toString());
		List<Job> joblist = jobService.getJobList(" where s_state <> '0'");
		Map<String,String> jobMap = joblist.stream().collect(Collectors.toMap(Job::getJobId,Job::getJobName));
		List<Department> departmentlist = departmentService.getAllDepartment();
		Map<String,String> departmentMap = departmentlist.stream().collect(Collectors.toMap(Department::getDepartmentId,Department::getDepartmentName));
		
		
		model.addAttribute("userid",userID);
		model.addAttribute("page",page);
		model.addAttribute("employeelist",employeelist);
		model.addAttribute("joblist",joblist);
		model.addAttribute("jobMap",jobMap);
		model.addAttribute("departmentlist",departmentlist);
		model.addAttribute("departmentMap",departmentMap);
		model.addAttribute("havepower",MyInterceptor.getLocalPower());
		model.addAttribute("employeeStateMap",EmployeeStateUtil.EmployeeStateMap());
		model.addAttribute("pageTitle", power.getMenuName());
		
		return "view/employee/employeelist";
	}
}
