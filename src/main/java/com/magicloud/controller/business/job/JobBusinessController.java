package com.magicloud.controller.business.job;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magicloud.common.CodeUtil;
import com.magicloud.common.JsonUtil;
import com.magicloud.common.StringUtil;
import com.magicloud.entity.Employee;
import com.magicloud.entity.Job;
import com.magicloud.entity.SoupeModel;
import com.magicloud.service.CommonService;
import com.magicloud.service.EmployeeService;
import com.magicloud.service.JobService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/job")
public class JobBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	JobService jobService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	CommonService commonService;
	
	@ResponseBody
	@ApiOperation(value="添加职位", notes="")
	@ApiImplicitParam(paramType="query", name = "jobName", value = "部门名称", required = true, dataType = "String")
	@RequestMapping(value="/addjob",method = RequestMethod.POST)
	public String addjob(@RequestParam("jobName")String jobName,HttpServletRequest request) {
		SoupeModel spm = new SoupeModel();
		try {
			
			if(StringUtil.isNULL(jobName)) {
				spm.setStatus(CodeUtil.PARAMETER_NULL);
				spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
				return JsonUtil.toJson(spm);
			}
			Job dd = new Job();
			dd.setJobId(UUID.randomUUID().toString());
			dd.setJobName(jobName);
			dd.setState("1");
			jobService.addJob(dd);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	
	@ResponseBody
	@ApiOperation(value="获取职位信息", notes="")
	@ApiImplicitParam(paramType="query", name = "jobId", value = "职位ID", required = true, dataType = "String")
	@RequestMapping(value="/getjob",method = RequestMethod.GET)
	public String getjob(@RequestParam("jobId")String jobId,HttpServletRequest request) {
		SoupeModel<Job> spm = new SoupeModel<Job>();
		try {
			if(StringUtil.isNULL(jobId)) {
				spm.setStatus(CodeUtil.PARAMETER_NULL);
				spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
				return JsonUtil.toJson(spm);
			}
			StringBuffer sql = new StringBuffer();
			sql.append("where s_job_id = '");
			sql.append(jobId);
			sql.append("' and s_state <> '0'");
			Job job = jobService.getJob(sql.toString());
			spm.setEntity(job);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value="修改职位信息", notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name = "jobId", value = "职位ID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType="query", name = "jobName", value = "职位名称", required = true, dataType = "String")}
	)
	@RequestMapping(value="/editjob",method = RequestMethod.POST)
	public String editjob(@ModelAttribute("job") Job job,HttpServletRequest request) {
		SoupeModel<Employee> spm = new SoupeModel<Employee>();
		try {
			if(StringUtil.isNULL(job.getJobId()) || StringUtil.isNULL(job.getJobName())) {
				spm.setStatus(CodeUtil.PARAMETER_NULL);
				spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
				return JsonUtil.toJson(spm);
			}
			job.setState(null);
			jobService.updateJob(job);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value="返回当前职位是否存在员工", notes="")
	@ApiImplicitParam(paramType="query", name = "jobId", value = "职位ID", required = true, dataType = "String")
	@RequestMapping(value="/isemp",method = RequestMethod.GET)
	public String isemp(@RequestParam("jobId")String jobId,HttpServletRequest request) {
		SoupeModel<Employee> spm = new SoupeModel<Employee>();
		try {
			if(StringUtil.isNULL(jobId)) {
				spm.setStatus(CodeUtil.PARAMETER_NULL);
				spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
				return JsonUtil.toJson(spm);
			}
			StringBuffer sql = new StringBuffer();
			sql.append("where job_id = '");
			sql.append(jobId);
			sql.append("' and state_id <> '100'");
			int size = commonService.getTotal("employee "+sql.toString());
			if(size>0) {
				spm.setStatus(CodeUtil.SIZE_OVERFOLOW);
				spm.setMessage(CodeUtil.MSG_SIZE_OVERFOLOW);
			}else {
				spm.setStatus(CodeUtil.SUCCESS);
				spm.setMessage(CodeUtil.MSG_SUCCESS);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	
	@ResponseBody
	@ApiOperation(value="删除职位", notes="")
	@ApiImplicitParam(paramType="query", name = "jobId", value = "职位ID", required = true, dataType = "String")
	@RequestMapping(value="/deljob",method = RequestMethod.POST)
	public String deljob(@RequestParam("jobId")String jobId,HttpServletRequest request) {
		SoupeModel<Employee> spm = new SoupeModel<Employee>();
		try {
			if(StringUtil.isNULL(jobId)) {
				spm.setStatus(CodeUtil.PARAMETER_NULL);
				spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
				return JsonUtil.toJson(spm);
			}
			Job job = new Job();
			job.setJobId(jobId);
			job.setState("0");
			jobService.updateJob(job);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
}
