package com.standard.domain;

import java.io.Serializable;

public class BreadCrumb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String link;
	private String texto;
	private String last;

	public BreadCrumb() {
	}

	public BreadCrumb(String link, String texto, String last) {
		this.link = link;
		this.texto = texto;
		this.setLast(last);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getLast() {
		return last;
	}

	private void setLast(String last) {
		this.last = last;
	}

 

}
