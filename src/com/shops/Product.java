package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	private int sid;
	private int pid;
	private String name;
	private double price;
	
	public Product() { }
	
	public Product(int sid, int pid, String name, double price) {
		this.sid = sid;
		this.pid = pid;
		this.name = name;
		this.price = price;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [sid=" + sid + ", pid=" + pid + ", name=" + name + ", price=" + price + "]";
	}
}
