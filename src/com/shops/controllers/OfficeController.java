package com.shops.controllers;

import com.shops.Office;
import com.shops.dao.DAO;
import com.shops.dao.MongoDAO;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mongodb.MongoTimeoutException;
import com.mongodb.MongoWriteException;

@ManagedBean
@SessionScoped
public class OfficeController {
	private MongoDAO mongoDao;
	private ArrayList<Office> offices;
	
	public OfficeController() {
		mongoDao = new MongoDAO();
	}
	
	public ArrayList<Office> getOffices() {
		return offices;
	}
	
	public void loadOffices() {
		try {
			offices = mongoDao.loadOffices();
		} 
		// A MongoTimeoutException will *eventually* be thrown if there is an issue connecting.
		catch (MongoTimeoutException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public String addOffice(Office o) {
		try {
			// Make sure that the entered office ID exists in the SQL database.
			if (!new DAO().storeExists(o.getSid())) {
				throw new StoreNotExistException();
			}
			
			mongoDao.addOffice(o);	
			return "list_offices.xhtml";
		} catch (StoreNotExistException e) {
			FacesMessage message = new FacesMessage(String.format("Error: Store ID: %d does not exist", o.getSid()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch(MongoWriteException e) {
			FacesMessage message = new FacesMessage(String.format("Error: Store ID: %d already has location", o.getSid()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return null;
	}
	
	public void deleteOffice(Office o) {
		try {
			mongoDao.deleteOffice(o);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
