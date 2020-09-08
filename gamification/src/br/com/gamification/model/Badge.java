package br.com.gamification.model;

public class Badge extends Achievement {

	private String title;
	public Badge() {
		
	}
	
	public Badge(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String badge) {
		this.title = badge;
	}
}
