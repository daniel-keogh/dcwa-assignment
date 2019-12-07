package com.shops.dao;

import com.shops.Office;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	
	public MongoDAO() {
		String mongoDB = "storeHeadOfficeDB";
		String mongoCollection = "storeHeadOffice";
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}

	public ArrayList<Office> loadOffices() {
		ArrayList<Office> officeList = new ArrayList<>();
		
		FindIterable<Document> offices = collection.find();
		
		// Iterate over results of find()
		for (Document d : offices) {
			Office o = new Office();
			
			o.setSid(d.getDouble("_id").intValue());
			o.setLocation(d.getString("location"));
			
			officeList.add(o);
		}
		
		return officeList;
	}

	public void addOffice(Office o) {
		Document doc = new Document();
		
		doc.append("_id", Double.valueOf(o.getSid()));
		doc.append("location", o.getLocation());
		
		collection.insertOne(doc);
	}

	public void deleteOffice(Office o) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", Double.valueOf(o.getSid()));
		
		collection.deleteOne(query);
	}
}
