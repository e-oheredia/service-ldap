package com.exact.service.ldap.service;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public Boolean autenticar(String url, String baseDn, String managerUsername, String managerPassword,
			String userDnPattern, String filter, String password) {

		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl(url);
		ldapContextSource.setBase(baseDn);
		ldapContextSource.setUserDn(managerUsername);
		ldapContextSource.setPassword(managerPassword);
		ldapContextSource.afterPropertiesSet();

		LdapTemplate tmpl = new LdapTemplate(ldapContextSource);

		boolean autenticado = tmpl.authenticate("", filter, password);

		return autenticado;

	}
}
