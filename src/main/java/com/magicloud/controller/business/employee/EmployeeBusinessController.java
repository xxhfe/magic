package com.magicloud.controller.business.employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.magicloud.common.CodeUtil;
import com.magicloud.common.JsonUtil;
import com.magicloud.common.StringUtil;
import com.magicloud.entity.Department;
import com.magicloud.entity.Employee;
import com.magicloud.entity.Menu;
import com.magicloud.entity.SoupeModel;
import com.magicloud.service.EmployeeService;
import com.magicloud.service.JobService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/employee")
public class EmployeeBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${face.tempdir}")
	private String tempdir;// 临时存放路径
	@Value("${face.dir}")
	private String dir;     // 永久保存路径
	
	@Autowired
	EmployeeService employeeService;
	
	@ResponseBody
	@ApiOperation(value="根据ID取得员工信息", notes="")
	@ApiImplicitParam(paramType = "query", name = "employeeID", value = "员工ID", required = true, dataType = "String")
	@RequestMapping(value="/getinfo",method = RequestMethod.GET)
	public String getinfo(@RequestParam("employeeID") String employeeID) {
		
		SoupeModel<Employee> spm = new SoupeModel<Employee>();
		if(StringUtil.isNULL(employeeID)) {
			spm.setStatus(CodeUtil.PARAMETER_NULL);
			spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
			return JsonUtil.toJson(spm);
		}
		try {
			Employee employee = employeeService.getEmployeeByID(employeeID);
			spm.setEntity(employee);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value="添加员工", notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "employeeName", value = "姓名", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeSex", value = "性别", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeePhone", value = "联系电话", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeEmergencyName", value = "紧急联系人", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeEmergencyPhone", value = "紧急联系人电话", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeNowAddress", value = "现居住地址", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeIDAddress", value = "身份证居住地址", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeIDNo", value = "身份证号码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeBirthday", value = "生日", required = true, dataType = "Date"),
		@ApiImplicitParam(paramType = "query", name = "employeeEntryDate", value = "入职日期", required = true, dataType = "Date"),
		@ApiImplicitParam(paramType = "query", name = "jobId", value = "职位ID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "departmentId", value = "部门ID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeface", value = "人脸照片", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "orgId", value = "机构编号", required = true, dataType = "String") })
	@RequestMapping(value="/addemployee",method = RequestMethod.POST)
	public String addemployee(@ModelAttribute("employee") Employee employee) {
		SoupeModel spm = new SoupeModel();
		String uuid = "";
		String face = employee.getEmployeeface();
		try {
			if(!StringUtil.isNULL(face)) {
				String rootDir = System.getProperty("user.dir");/// 项目同级目录
				String filename = face.substring(face.lastIndexOf("/"));
				uuid = filename.substring(1,filename.indexOf("."));
				File oldfile = new File(rootDir + tempdir + new SimpleDateFormat("d").format(new Date()) +"/"+ filename);
				File newfile = new File(rootDir + dir + filename);
				if (newfile.getParentFile() != null || !newfile.getParentFile().isDirectory()) {
				    // 创建父文件夹
					newfile.getParentFile().mkdirs();
				}
				oldfile.renameTo(newfile);
				employee.setEmployeeface("/image/upload"+filename);
			}else {
				uuid = UUID.randomUUID().toString();
			}
			employee.setStateId("0");
			employee.setEmployeeId(uuid);
			employeeService.addEmployee(employee);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value="修改员工信息", notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "employeeId", value = "UUID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeName", value = "姓名", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeSex", value = "性别", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeePhone", value = "联系电话", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeEmergencyName", value = "紧急联系人", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeEmergencyPhone", value = "紧急联系人电话", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeNowAddress", value = "现居住地址", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeIDAddress", value = "身份证居住地址", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeIDNo", value = "身份证号码", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeBirthday", value = "生日", required = true, dataType = "Date"),
		@ApiImplicitParam(paramType = "query", name = "employeeEntryDate", value = "入职日期", required = true, dataType = "Date"),
		@ApiImplicitParam(paramType = "query", name = "jobId", value = "职位ID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "departmentId", value = "部门ID", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeface", value = "人脸照片", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "employeeDepartureDate", value = "离职日期", required = false, dataType = "Date"),
		@ApiImplicitParam(paramType = "query", name = "stateId", value = "状态", required = false, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "orgId", value = "机构编号", required = true, dataType = "String") })
	@RequestMapping(value="/editemployee",method = RequestMethod.POST)
	public String editemployee(@ModelAttribute("employee") Employee employee) {
		SoupeModel spm = new SoupeModel();
		String facePath = employee.getEmployeeface();
		try {
			// 判断路径 temp OR dir
			if(facePath.indexOf("temp")>0) {
				String rootDir = System.getProperty("user.dir");/// 项目同级目录
				String filename = facePath.substring(facePath.lastIndexOf("/"));
				File oldfile = new File(rootDir + tempdir + new SimpleDateFormat("d").format(new Date()) +"/"+ filename);
				String suffix = filename.substring(filename.indexOf("."));
				File newfile = new File(rootDir + dir + employee.getEmployeeId() +suffix);
				if (newfile.getParentFile() != null || !newfile.getParentFile().isDirectory()) {
				    // 创建父文件夹
					newfile.getParentFile().mkdirs();
				}
				oldfile.renameTo(newfile);
				employee.setEmployeeface("/image/upload/"+employee.getEmployeeId() +suffix);
			}
			if(StringUtil.isNULL(employee.getStateId())) {
				employee.setStateId(null);
			}
			employeeService.updateEmployee(employee);
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value="离职员工", notes="")
	@ApiImplicitParam(paramType="query", name = "employeeId", value = "员工ID", required = true, dataType = "String")
	@RequestMapping(value="/quitemployee",method = RequestMethod.POST)
	public String quit(@RequestParam("employeeId")String employeeId) {
		SoupeModel spm = new SoupeModel();
		try {
			employeeService.updateEmployeeState(employeeId, "99");
			spm.setStatus(CodeUtil.SUCCESS);
			spm.setMessage(CodeUtil.MSG_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}
		
		return JsonUtil.toJson(spm);
	}
	
	
	
	@ResponseBody
	@ApiOperation(value="人脸验证图片上传", notes="")
	@RequestMapping(value="/uploadFace",method = RequestMethod.POST,consumes = "multipart/*",headers = "content-type=multipart/form-data")
	public String uploadFace(@ApiParam(value="人脸图片",required = true) MultipartFile facefile,HttpServletRequest request) {
		
		SoupeModel spm = new SoupeModel();
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		String rootDir = System.getProperty("user.dir");/// 项目同级目录
		try {
			if(facefile!=null) {
				String fileName = facefile.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					String suffix = fileName.substring(fileName.indexOf("."));
					String uuid = UUID.randomUUID().toString();
					String finalPath = rootDir + tempdir + new SimpleDateFormat("d").format(new Date()) +"/"+ uuid + suffix;
					File outFile = new File(finalPath); 
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
					    // 创建父文件夹
			             outFile.getParentFile().mkdirs();
					}
					fileOutputStream = new FileOutputStream(outFile);
					inputStream = facefile.getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
					spm.setStatus(CodeUtil.SUCCESS);
					spm.setMessage("/image/temp/"+uuid+suffix);
					return JsonUtil.toJson(spm);
				}
				spm.setStatus(CodeUtil.UPLOAD_NULL);
				spm.setMessage(CodeUtil.ERR_UPLOAD_NULL);
			}else {
				spm.setStatus(CodeUtil.UPLOAD_NULL);
				spm.setMessage(CodeUtil.ERR_UPLOAD_NULL);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			// TODO: handle exception
			spm.setStatus(CodeUtil.ERROR);
			spm.setMessage(CodeUtil.MSG_ERROR);
		}finally {
			if (fileOutputStream != null) {
		        try {
					fileOutputStream.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		return JsonUtil.toJson(spm);
	}
}
