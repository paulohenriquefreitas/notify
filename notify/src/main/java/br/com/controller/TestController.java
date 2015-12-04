package br.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Notify;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@RestController
public class TestController {
	
	 @RequestMapping("/list")
	    Notify home() {
		 try{
			// Connect to mongodb server on localhost
			List<String> list = new ArrayList<String>();
			MongoClient mongoClient = new MongoClient("localhost",27017);
			DB db = mongoClient.getDB( "sampledb" );
			DBCollection coll = db.getCollection("users");
			
			Gson gson = new Gson();
			DBObject doc = new BasicDBObject("price", 44);
			DBObject obj = coll.findOne(doc);
			Notify notify = gson.fromJson(obj.toString(), Notify.class);
			
			 /*DBCursor cursor = coll.find();
			 try {
			 while(cursor.hasNext()) {
				 DBObject object = cursor.next();
			
			DBObject query = new BasicDBObject("price", 44);
			DBCursor cursor = coll.find(query);
			try {
				while(cursor.hasNext()) {
					 //list.add(object.toString());
					return cursor.next().get("url").toString();
				 }
			 }*/
			
		return notify;
		}
		catch(Exception e){
        
	    }
		return null;
	 }
}
