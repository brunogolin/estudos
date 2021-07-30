package br.com.estudos.service.connection;

import java.util.Properties;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	@Autowired
	@Qualifier("systemProperties")
	private Properties systemProperties;
	
	public MongoDatabase openConnection() {

		String password = systemProperties.getProperty("mongoDB.password");
		String cluster = systemProperties.getProperty("mongoDB.cluster");
		String project = systemProperties.getProperty("mongoDB.project");
		
		ConnectionString connectionString = new ConnectionString("mongodb+srv://sa:" + password + "@" + cluster + ".qp4nn.mongodb.net/" + project + "?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase(cluster);

		return database;

	}

	public MongoCollection<Document> toChargeCollection(MongoDatabase database, String name) {

		MongoCollection<Document> collection = database.getCollection(name);
		if (collection == null) {
			database.createCollection(name);
		}

		return database.getCollection(name);
	}

	public boolean insert(MongoCollection<Document> collection, Document document, String key, String value) {

		Document documentPesquisa = searchKey(collection, key, value);

		if (documentPesquisa == null) {
			collection.insertOne(document);
		}

		return true;
	}

	public void mostrar(MongoCollection<Document> collection) {

		FindIterable<Document> documents = collection.find();

		for (Document document : documents) {
			System.out.println(document.get("name"));
		}

	}

	public Document searchKey(MongoCollection<Document> collection, String key, String value) {

		Document filterDoc = new Document();
		filterDoc.append(key, value);

		Document document = collection.find(filterDoc).first();

		return document;
	}

}
