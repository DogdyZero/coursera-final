package br.com.gamification.model;

public class AchievementComposite extends Achievement{

	private Achievement badge;
	private Achievement points;

	public AchievementComposite(String name,Badge badge, Points points) {
		setName(name);
		this.badge = badge;
		this.points = points;
	}
	
	public int getPoints() {
		return ((Points)points).getNumber();
	}
	
	public String getBadge() {
		return ((Badge) badge).getTitle();
	}
	
	public void setPoints(int number) {
		((Points)points).setNumber(number);
	}
	
	public void setBadge(String title) {
		((Badge) badge).setTitle(title);
	}
	
	
}
