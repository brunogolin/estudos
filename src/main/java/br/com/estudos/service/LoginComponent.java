package br.com.estudos.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.estudos.model.ToLogin;
import br.com.estudos.model.Document.LoginDocument;
import br.com.estudos.service.connection.MongoDB;

@Component("LOGIN")
public class LoginComponent {

	@Autowired
	private MongoDB db;
	
	@Autowired
	private LoginDocument loginDocument;
	
	public Object efetuarLogin(ToLogin toLogin) {

		MongoDatabase database = db.openConnection();
		MongoCollection<Document> collection = db.toChargeCollection(database, "LOGIN");
		Document documentLogin = loginDocument.convertToLogin(toLogin);
		db.insert(collection, documentLogin, "login", toLogin.getUser());
		
		return null;

	}

}
