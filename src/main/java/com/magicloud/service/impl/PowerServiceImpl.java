package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.dao.PowerDao;
import com.magicloud.entity.Power;
import com.magicloud.service.PowerService;

@Service
public class PowerServiceImpl implements PowerService{

	@Autowired
	PowerDao powerDao;
	@Override
	public void addPower(Power power) {
		// TODO Auto-generated method stub
		powerDao.addPower(power);
	}
	@Override
	public List<Power> getPowerList(String roleID) {
		// TODO Auto-generated method stub
		return powerDao.getPowerList(roleID);
	}
	@Override
	public List<Power> getRolePower(String roleID) {
		// TODO Auto-generated method stub
		return powerDao.getRolePower(roleID);
	}
	@Override
	public void updatePower(Power power) {
		// TODO Auto-generated method stub
		powerDao.updatePower(power);
	}
	@Override
	public Power getPowerByMenu(String roleID, String menuID) {
		// TODO Auto-generated method stub
		return powerDao.getPowerByMenu(roleID, menuID);
	}
}
