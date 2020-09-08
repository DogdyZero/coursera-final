package br.com.gamification.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Topic {
	private String creator;
	private String titulo;
	private List<Comment> comments = new ArrayList<Comment>();
	private Map<String,Boolean> likes = new HashMap<String,Boolean>();
	
	public Topic(String creator, String titulo ) {
		this.creator = creator;
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void addLike(String user) {
		likes.put(user,Boolean.TRUE);
	}
	
	public void addLikeComment(String comment, String user) {
		Optional<Comment> optional = comments.stream()
				.filter(c -> c.getTitulo().equalsIgnoreCase(comment)).findFirst();
		if(optional.isPresent()) {
			optional.get().addLike(user);
		}
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}
}
