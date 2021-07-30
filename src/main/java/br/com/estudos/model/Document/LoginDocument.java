package br.com.estudos.model.Document;

import org.bson.Document;

import br.com.estudos.model.ToLogin;

public class LoginDocument {

	public Document convertToLogin(ToLogin toLogin) {

		Document document = new Document();

		document.append("", toLogin.getUser());
		document.append("", toLogin.getPassword());

		return document;

	}

}
