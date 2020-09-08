package br.com.gamification.model;

import java.util.HashMap;
import java.util.Map;

public class Comment {
	private String user;
	private String titulo;
	private Map<String,Boolean> likes = new HashMap<String,Boolean>();
	
	public Comment(String user, String titulo) {
		this.user = user;
		this.titulo = titulo;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void addLike(String user) {
		likes.put(user,Boolean.TRUE);
	}
}
