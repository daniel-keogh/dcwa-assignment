package com.shops.controllers;

import com.shops.Product;
import com.shops.dao.DAO;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.naming.NamingException;

@ManagedBean
@SessionScoped
public class ProductController {
	private DAO dao;
	private ArrayList<Product> products;

	public ProductController() {
		try {
			dao = new DAO();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void loadProducts() {
		try {
			products = dao.loadProducts();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Unable to connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void deleteProduct(Product p) {
		try {
			dao.deleteProduct(p);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Failed to delete that product from DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
