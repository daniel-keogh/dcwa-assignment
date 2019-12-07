package com.shops.controllers;

public class StoreNotExistException extends Exception {
	public StoreNotExistException() {
		super();
	}
	
	public StoreNotExistException(String message) {
		super(message);
	}
}
