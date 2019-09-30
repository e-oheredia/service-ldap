	package com.exact.service.ldap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exact.service.ldap.model.Response;
import com.exact.service.ldap.service.AuthService;

@RestController
@RequestMapping("ldap")
@Transactional
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping(params = { "url", "baseDn", "managerUsername", "managerPassword", "filter",
	"password" })
	public ResponseEntity<Response> listarGrupos(@RequestParam String url, @RequestParam String baseDn,
		@RequestParam String managerUsername, @RequestParam String managerPassword,
		@RequestParam String filter, @RequestParam String password) {
		Response response =  new Response();
		List<Map<String, String>> lista = authService.listarGrupos(url, baseDn, managerUsername, managerPassword, filter, password);
		
		if (lista == null) {
			response.setMensaje("Usuario no encontrado");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
		}
		
		if (lista.get(0) != null) {
			response.setMensaje("Usuario encontrado");
			response.setGrupos(lista);
			return new ResponseEntity<Response>(response, HttpStatus.OK);	
		}
		
		response.setMensaje("El usuario no está dentro de ningún grupo");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);	
	}
	
	

}

 