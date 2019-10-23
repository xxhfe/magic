package com.magicloud.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SoupeModel<T> implements Serializable{

	private String message;
	private Map<String,Object> maps;
	private String status;
	private String userid;
	private List<T> list;
	private T entity;
	
	public SoupeModel(){}
	public SoupeModel(String message,Map<String,Object> maps,String status,String userid,List<T> list,T entity){
		this.message=message;
		this.maps=maps;
		this.status=status;
		this.userid=userid;
		this.list=list;
		this.entity=entity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
}
