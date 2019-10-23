package com.magicloud.controller.business.power;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magicloud.common.CodeUtil;
import com.magicloud.common.JsonUtil;
import com.magicloud.entity.Power;
import com.magicloud.entity.Role;
import com.magicloud.entity.SoupeModel;
import com.magicloud.service.MenuService;
import com.magicloud.service.PowerService;
import com.magicloud.service.RoleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
@RequestMapping(value="/power")
public class PowerBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MenuService menuService;
	@Autowired
	PowerService powerService;
	@Autowired
	RoleService roleService;
	
	@ResponseBody
	@ApiOperation(value="角色分配", notes="")
	@ApiImplicitParam(paramType = "query", name = "roleID", value = "角色ID", required = true, dataType = "String")
	@RequestMapping(value="/assignpower",method = RequestMethod.GET)
	public String assignpower(@RequestParam String roleID, HttpServletRequest request) {
		logger.info(this.getClass()+"   角色分配");
		SoupeModel<Power> spm = new SoupeModel<Power>();
		if(roleID==null || "".equals(roleID)) {
			spm.setStatus("1000");
			spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
			return JsonUtil.toJson(spm);
		}
		List<Power> powerlist = powerService.getPowerList(roleID);
		spm.setStatus("success");
		spm.setList(powerlist);
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value="权限编辑", notes="", consumes="application/json")
	@RequestMapping(value="/savepower",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String savepower(@RequestBody JSONObject jsonParam, HttpServletRequest request) {
		SoupeModel<Power> spm = new SoupeModel<Power>();
		System.out.println(jsonParam);
		JSONArray jsonArray = jsonParam.getJSONArray("data");//获取数组
		int rolelevel = 0;
		try {
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject tempjson = JSONObject.fromObject(jsonArray.get(i));
				String checked = tempjson.getString("checked");
				String powerid = tempjson.getString("powerId");
				if(checked==null || powerid==null) {
					spm.setStatus("1000");
					spm.setMessage(CodeUtil.MSG_PARAMETER_NULL);
					return JsonUtil.toJson(spm);
				}
				if(rolelevel==0) {
					// 获取当前设定角色的可见等级
					try {
						Role role = roleService.getRole(tempjson.getString("roleId"));
						rolelevel = role.getLevel();
					}catch (Exception e) {
						// TODO: handle exception
						spm.setStatus("1000");
						spm.setMessage(CodeUtil.MSG_LOGIN_UNAUTHORIZED);
						return JsonUtil.toJson(spm);
					}
				}
				
				if("1".equals(checked) && "0".equals(powerid)) {
					// insert 
					Power power = new Power();
					power.setPowerId(UUID.randomUUID().toString());
					power.setMenuId(tempjson.getString("menuId"));
					power.setRoleId(tempjson.getString("roleId"));
					power.setPowerAdd(tempjson.getString("powerAdd"));
					power.setPowerDel(tempjson.getString("powerDel"));
					power.setPowerUpd(tempjson.getString("powerUpd"));
					power.setPowerSel(tempjson.getString("powerSel"));
					power.setPowerFile(tempjson.getString("powerFile"));
					power.setState("1");
					power.setLevel(rolelevel);
					powerService.addPower(power);
				}
				if("1".equals(checked) && !"0".equals(powerid)) {
					// update all
					Power power = new Power();
					power.setPowerId(tempjson.getString("powerId"));
					power.setMenuId(tempjson.getString("menuId"));
					power.setRoleId(tempjson.getString("roleId"));
					power.setPowerAdd(tempjson.getString("powerAdd"));
					power.setPowerDel(tempjson.getString("powerDel"));
					power.setPowerUpd(tempjson.getString("powerUpd"));
					power.setPowerSel(tempjson.getString("powerSel"));
					power.setPowerFile(tempjson.getString("powerFile"));
					power.setState("1");
					power.setLevel(rolelevel);
					powerService.updatePower(power);
				}
				if("0".equals(checked) && !"0".equals(powerid)) {
					// update state to 0
					Power power = new Power();
					power.setPowerId(tempjson.getString("powerId"));
					power.setMenuId(tempjson.getString("menuId"));
					power.setRoleId(tempjson.getString("roleId"));
					power.setPowerAdd("0");
					power.setPowerDel("0");
					power.setPowerUpd("0");
					power.setPowerSel("0");
					power.setPowerFile("0");
					power.setState("0");
					power.setLevel(rolelevel);
					powerService.updatePower(power);
				}
			}
			spm.setMessage("操作成功！");
			spm.setStatus("success");
			return JsonUtil.toJson(spm);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			spm.setStatus("err");
			spm.setMessage("系统异常");
			return JsonUtil.toJson(spm);
		}
	}
}
