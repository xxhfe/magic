package com.magicloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magicloud.common.CodeUtil;
import com.magicloud.common.EmployeeStateUtil;
import com.magicloud.common.JsonUtil;
import com.magicloud.common.Page;
import com.magicloud.common.SessionUtil;
import com.magicloud.common.StateUtil;
import com.magicloud.entity.Department;
import com.magicloud.entity.Employee;
import com.magicloud.entity.Job;
import com.magicloud.entity.Power;
import com.magicloud.entity.SoupeModel;
import com.magicloud.entity.User;
import com.magicloud.interceptor.MyInterceptor;
import com.magicloud.service.CommonService;
import com.magicloud.service.DepartmentService;
import com.magicloud.service.EmployeeService;
import com.magicloud.service.JobService;

@Controller
public class JobController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	JobService jobService;
	@Autowired
	CommonService commonService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/joblist", method = RequestMethod.GET)
	public String joblist(Model model,@ModelAttribute("page")Page page, HttpServletRequest request) {
		User user = MyInterceptor.getLocalUser();
		String userID = SessionUtil.getCookieID(user);
		Power power = MyInterceptor.getLocalPower();
		StringBuffer sql = new StringBuffer();
		sql.append(" where s_state <> '0' ");
		
		int recordTotal = commonService.getTotal(" job "+sql.toString());
		page = Page.forInstance(recordTotal,page.getCurrentPageNo(),page.getMaxResults(),"/joblist");
		sql.append(" limit "+(page.getCurrentPageNo()-1)*page.getMaxResults()+","+page.getMaxResults());
		
		List<Job> list = jobService.getJobList(sql.toString());
		List<Department> departmentlist = departmentService.getAllDepartment();
		Map<String,String> departmentMap = departmentlist.stream().collect(Collectors.toMap(Department::getDepartmentId,Department::getDepartmentName));
		
		model.addAttribute("userid",userID);
		model.addAttribute("joblist",list);
		model.addAttribute("departmentMap",departmentMap);
		model.addAttribute("employeeStateMap",EmployeeStateUtil.EmployeeStateMap());
		model.addAttribute("stateMap",StateUtil.StateMap());
		model.addAttribute("havepower",MyInterceptor.getLocalPower());
		model.addAttribute("pageTitle", power.getMenuName());
		model.addAttribute("page", page);
		
		return "view/job/joblist";
	}
	@RequestMapping(value = "/emplist", method = RequestMethod.GET)
	public String emplist(Model model,@ModelAttribute("page")Page page, HttpServletRequest request) {
		String jobId = request.getParameter("jobID");
		try {
			User user = MyInterceptor.getLocalUser();
			String userID = SessionUtil.getCookieID(user);
			StringBuffer sql = new StringBuffer();
			sql.append("where job_id = '");
			sql.append(jobId);
			sql.append("' and state_id <> '100'");
			int recordTotal = commonService.getTotal("employee "+sql.toString());
			page = Page.forInstance(recordTotal,page.getCurrentPageNo(),page.getMaxResults(),"/emplist");
			sql.append(" limit "+(page.getCurrentPageNo()-1)*page.getMaxResults()+","+page.getMaxResults());
			List<Employee> employeeList = employeeService.getEmployeeList(sql.toString());
			List<Department> departmentlist = departmentService.getAllDepartment();
			Map<String,String> departmentMap = departmentlist.stream().collect(Collectors.toMap(Department::getDepartmentId,Department::getDepartmentName));
			
			model.addAttribute("userid",userID);
			model.addAttribute("employeeList",employeeList);
			model.addAttribute("departmentlist",departmentlist);
			model.addAttribute("departmentMap",departmentMap);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
		}
		return "view/job/job_emplist";
	}
	@RequestMapping(value = "/empotherlist", method = RequestMethod.GET)
	public String empotherlist(Model model,@ModelAttribute("page")Page page, HttpServletRequest request) {
		String jobId = request.getParameter("jobID");
		try {
			User user = MyInterceptor.getLocalUser();
			String userID = SessionUtil.getCookieID(user);
			StringBuffer sql = new StringBuffer();
			sql.append("where job_id <> '");
			sql.append(jobId);
			sql.append("' and state_id <> '100'");
			int recordTotal = commonService.getTotal("employee "+sql.toString());
			page = Page.forInstance(recordTotal,page.getCurrentPageNo(),page.getMaxResults(),"/empotherlist");
			sql.append(" limit "+(page.getCurrentPageNo()-1)*page.getMaxResults()+","+page.getMaxResults());
			List<Employee> employeeList = employeeService.getEmployeeList(sql.toString());
			List<Department> departmentlist = departmentService.getAllDepartment();
			Map<String,String> departmentMap = departmentlist.stream().collect(Collectors.toMap(Department::getDepartmentId,Department::getDepartmentName));
			List<Job> joblist = jobService.getJobList(" where s_state <> '0'");
			Map<String,String> jobMap = joblist.stream().collect(Collectors.toMap(Job::getJobId,Job::getJobName));
			
			model.addAttribute("userid",userID);
			model.addAttribute("employeeList",employeeList);
			model.addAttribute("departmentlist",departmentlist);
			model.addAttribute("departmentMap",departmentMap);
			model.addAttribute("jobMap",jobMap);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
		}
		return "view/job/job_empotherlist";
	}
}
