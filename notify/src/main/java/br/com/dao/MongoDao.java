package br.com.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import br.com.model.Notify;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Component
public class MongoDao {	
	
	private static final String COLLECTION = "urlinfo";
	private static final String DATABASE_NAME = "notifyDB";
	private static final int PORT = 27017;

	public List<Notify> findNotify(String field){
		MongoClient mongoClient = null;
		List<Notify> notifyList = new ArrayList<>();
		try {
			mongoClient = new MongoClient("localhost",PORT);
			MongoOperations mongoOps = new MongoTemplate(mongoClient,DATABASE_NAME);
			notifyList = mongoOps.find(new Query(Criteria.where("field").is(field)), Notify.class, COLLECTION);
			/*DB db = mongoClient.getDB( DATABASE_NAME );
			DBCollection coll = db.getCollection(COLLECTION);
			Gson gson = new Gson();
			DBObject doc = new BasicDBObject("field", field);
			DBCursor cursor = coll.find(doc);
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				notifyList.add(gson.fromJson(obj.toString(), Notify.class));
			}				*/
			
		} catch (UnknownHostException e) {			
			e.printStackTrace();			
		}finally{
			mongoClient.close();
		}
		return notifyList;
	}
}
