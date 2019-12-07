package com.shops;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ProductDetails {
	private int sid;
	private String storeName;
	private Date founded;
	private int pid;
	private String prodName;
	private double price;
	
	public ProductDetails() { }
	
	public ProductDetails(int sid, String storeName, Date founded, int pid, String prodName, double price) {
		this.sid = sid;
		this.storeName = storeName;
		this.founded = founded;
		this.pid = pid;
		this.prodName = prodName;
		this.price = price;
	}

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getFounded() {
		return founded;
	}
	public void setFounded(Date founded) {
		this.founded = founded;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDetails [sid=" + sid + ", storeName=" + storeName + ", founded=" + founded + ", pid=" + pid
				+ ", prodName=" + prodName + ", price=" + price + "]";
	}
}
