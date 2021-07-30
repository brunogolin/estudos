package br.com.estudos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.estudos.model.ToLogin;
import br.com.estudos.service.LoginComponent;

@Controller
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginController {

	public class TesteController {

		@Autowired
		private LoginComponent loginComponent;

		@RequestMapping(method = RequestMethod.POST, value = "/teste")
		@ResponseBody
		public ResponseEntity<Object> login(@RequestBody ToLogin toLogin) {

			loginComponent.efetuarLogin(toLogin);

			return new ResponseEntity<Object>(null, HttpStatus.CREATED);

		}

	}

}
