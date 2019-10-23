package com.magicloud.entity;

import java.util.Date;

public class Item {

	private Date lf;
	private String ptid;
	private String ptname;
	private String cp;
	private String gs;
	private String txm;
	public Date getLf() {
		return lf;
	}
	public void setLf(Date lf) {
		this.lf = lf;
	}
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	public String getPtname() {
		return ptname;
	}
	public void setPtname(String ptname) {
		this.ptname = ptname;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getGs() {
		return gs;
	}
	public void setGs(String gs) {
		this.gs = gs;
	}
	public String getTxm() {
		return txm;
	}
	public void setTxm(String txm) {
		this.txm = txm;
	}
}
