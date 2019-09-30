package com.exact.service.ldap.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Response implements Serializable {
	
	public Response() {
		super();
	}
	
	public Response(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public Response(String mensaje, List<Map<String, String>> grupos) {
		super();
		this.mensaje = mensaje;
		this.grupos = grupos;
	}

	private String mensaje;
	private List<Map<String, String>> grupos;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<Map<String, String>> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Map<String, String>> grupos) {
		this.grupos = grupos;
	}
	
	
	private static final long serialVersionUID = 1L;
}
