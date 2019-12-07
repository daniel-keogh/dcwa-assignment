package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Office {
	private int sid;
	private String location;
	
	public Office() { }
	
	public Office(int sid, String location) {
		this.sid = sid;
		this.location = location;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Office [sid=" + sid + ", location=" + location + "]";
	}
}
