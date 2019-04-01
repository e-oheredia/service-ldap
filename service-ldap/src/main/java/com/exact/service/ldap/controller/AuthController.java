package com.exact.service.ldap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exact.service.ldap.service.AuthService;

@RestController
@RequestMapping("ldap")
public class AuthController {
	
	@Autowired
	AuthService authService;

	@PostMapping(params = { "url", "baseDn", "managerUsername", "managerPassword", "userDnPattern", "filter",
			"password" })
	public Boolean autenticar(@RequestParam String url, @RequestParam String baseDn,
			@RequestParam String managerUsername, @RequestParam String managerPassword,
			@RequestParam String userDnPattern, @RequestParam String filter, @RequestParam String password) {
		
		return authService.autenticar(url, baseDn, managerUsername, managerPassword, userDnPattern, filter, password);

	}

}
