package br.com.gamification.model;

public class Points extends Achievement {
	private int number;
	
	public Points() {
	}
	
	public Points(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
}
