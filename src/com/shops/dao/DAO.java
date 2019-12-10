package com.shops.dao;

import com.shops.Product;
import com.shops.ProductDetails;
import com.shops.Store;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class DAO {
	private DataSource sqlDS;

	public DAO() throws NamingException {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		sqlDS = (DataSource) context.lookup(jndiName);
	}

	public ArrayList<Store> loadStores() throws SQLException {
		Connection conn = sqlDS.getConnection();
		
		String sql = "SELECT * FROM store";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		ArrayList<Store> stores = new ArrayList<>();
		while (rs.next()) {
			Store s = new Store();
			
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setFounded(rs.getDate("founded"));
			
			stores.add(s);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return stores;
	}

	public void addStore(Store s) throws SQLException {	    
		Connection conn = sqlDS.getConnection();
		
		String sql = "INSERT INTO store(name, founded) VALUES(?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, s.getName());
		
		// Convert founded from Date to a String.
		String founded = new SimpleDateFormat("yyyy-MM-dd").format(s.getFounded());
		stmt.setString(2, founded);
		stmt.execute();
		
		stmt.close();
		conn.close();
	}

	public void deleteStore(Store s) throws SQLException {
		Connection conn = sqlDS.getConnection();
		
		String sql = "DELETE FROM store WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, s.getId());
		
		stmt.execute();
		
		stmt.close();
		conn.close();
	}
	
	public ArrayList<Product> loadProducts() throws SQLException {
		Connection conn = sqlDS.getConnection();
		
		String sql = "SELECT * FROM product";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		ArrayList<Product> products = new ArrayList<>();
		while (rs.next()) {
			Product p = new Product();
			
			p.setPid(rs.getInt("pid"));
			p.setSid(rs.getInt("sid"));
			p.setName(rs.getString("prodName"));
			p.setPrice(rs.getDouble("price"));
			
			products.add(p);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return products;
	}
	
	public void deleteProduct(Product p) throws SQLException {
		Connection conn = sqlDS.getConnection();

		String sql = "DELETE FROM product WHERE pid = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, p.getPid());

		stmt.execute();	

		stmt.close();
		conn.close();
	}
	
	public void addProduct(Product p) throws SQLException {
		Connection conn = sqlDS.getConnection();
		
		String sql = "INSERT INTO product(sid, prodName, price) VALUES(?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, p.getSid());
		stmt.setString(2, p.getName());
		stmt.setDouble(3, p.getPrice());
		
		stmt.execute();
		
		stmt.close();
		conn.close();
	}
	
	public ArrayList<ProductDetails> loadProductDetails(Store s) throws SQLException {
		Connection conn = sqlDS.getConnection();
		
		String sql = "SELECT * FROM store s JOIN product p ON s.id = p.sid WHERE s.id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, s.getId());
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<ProductDetails> productDetails = new ArrayList<>();
		while (rs.next()) {			
			ProductDetails pd = new ProductDetails();
			
			pd.setSid(rs.getInt("sid"));
			pd.setStoreName(rs.getString("name"));
			pd.setFounded(rs.getDate("founded"));
			pd.setPid(rs.getInt("pid"));
			pd.setProdName(rs.getString("prodName"));
			pd.setPrice(rs.getDouble("price"));
			
			productDetails.add(pd);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return productDetails;
	}

	public boolean storeExists(int sid) throws SQLException {
		Connection conn = sqlDS.getConnection();
		
		String sql = "SELECT * FROM store WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, sid);
		
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next(); // False if an empty set was returned, true otherwise.
		
		rs.close();
		stmt.close();
		conn.close();
		
		return exists;
	}
}
