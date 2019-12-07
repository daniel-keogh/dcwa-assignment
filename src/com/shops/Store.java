package com.shops;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {
	private int id;
	private String name;
	private Date founded;
	
	public Store() { }
	
	public Store(int id, String name, Date founded) {
		this.id = id;
		this.name = name;
		this.founded = founded;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getFounded() {
		return founded;
	}
	public void setFounded(Date founded) {
		this.founded = founded;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", founded=" + founded + "]";
	}
}
