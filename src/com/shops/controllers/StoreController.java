package com.shops.controllers;

import com.shops.ProductDetails;
import com.shops.Store;
import com.shops.dao.DAO;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.naming.NamingException;

@ManagedBean
@SessionScoped
public class StoreController {
	private DAO dao;
	private ArrayList<Store> stores;
	private ArrayList<ProductDetails> productDetails;
	
	public StoreController() {
		try {
			dao = new DAO();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Store> getStores() {
		return stores;
	}
	
	public ArrayList<ProductDetails> getProductDetails() {
		return productDetails;
	}
	
	public void loadStores() {
		try {
			stores = dao.loadStores();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Unable to connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public String addStore(Store s) {
		try {
			dao.addStore(s);
			return "list_stores.xhtml";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(String.format("Error: Store %s already exists", s.getName()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return null;
	}
	
	public void deleteStore(Store s) {
		try {
			dao.deleteStore(s);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(String.format("Error: Store %s has not been deleted from MySQL DB, it contains products", s.getName()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Unable to delete that store");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public String showProductDetails(Store s) {
		try {
			productDetails = dao.loadProductDetails(s);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(String.format("Error: Failed to get details about %s's products", s.getName()));
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
		// Go to the Store/Product Details page
		return "store_product_details.xhtml";
	}
}
