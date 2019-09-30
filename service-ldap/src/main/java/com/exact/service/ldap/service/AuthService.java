package com.exact.service.ldap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

	public Boolean autenticar(String url, String baseDn, String managerUsername, String managerPassword,
			String filter, String password) {

		LdapContextSource ldapContextSource = definirContextoLdap(url, baseDn, managerUsername, managerPassword);

		LdapTemplate tmpl = new LdapTemplate(ldapContextSource);

		boolean autenticado = tmpl.authenticate("", filter, password);

		return autenticado;

	}
	
	LdapContextSource definirContextoLdap(String url, String baseDn, String managerUsername, String managerPassword) {
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl(url);
		ldapContextSource.setBase(baseDn);
		ldapContextSource.setUserDn(managerUsername);
		ldapContextSource.setPassword(managerPassword);
		ldapContextSource.afterPropertiesSet();
		return ldapContextSource;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> listarGrupos(String url, String baseDn, String managerUsername, String managerPassword,
			String filter, String password) {
		
		LdapContextSource ldapContextSource = definirContextoLdap(url, baseDn, managerUsername, managerPassword);

		LdapTemplate tmpl = new LdapTemplate(ldapContextSource);

		boolean autenticado = tmpl.authenticate("", filter, password);
		
		if (!autenticado) {
			return null;
		}
		
		List valores = tmpl.search("", filter, new AttributesMapper() {
			
			@Override
			public Object mapFromAttributes(Attributes attributes) throws NamingException {
				Attribute lista = attributes.get("mail");
				try {
					NamingEnumeration<?> valores = lista.getAll();
					List<String> valoresString = new ArrayList<>();
					while(valores.hasMore()) {
						valoresString.add(valores.next().toString());
					}
					return valoresString;
				} catch(NullPointerException e) {
					return null;
				} catch(Exception e) {
					e.printStackTrace();
					return null;
				}
								
			}			
		});		
		
		List<Map<String, String>> gruposMap = new ArrayList<>();
		
		valores.forEach(grupos ->{
			((List<String>) grupos).forEach(grupo ->{
				Map<String, String> grupoMap = new HashMap<>();
				grupoMap.put("grupo", grupo.toString());
				gruposMap.add(grupoMap);
			});			
		});
		
		return gruposMap;
	}
}
