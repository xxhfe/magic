package com.magicloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.dao.CommonDao;
import com.magicloud.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	CommonDao commonDao;
	
	@Override
	public int getTotal(String parameter) {
		// TODO Auto-generated method stub
		return commonDao.getTotal(parameter);
	}
}
