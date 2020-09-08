package br.com.gamification.observer;

import br.com.gamification.model.Achievement;
import br.com.gamification.model.AchievementComposite;

public class CreationPointsObserver implements AchievementObserver {

	private AchievementComposite composite;

	public CreationPointsObserver(AchievementComposite composite) {
		this.composite = composite;
	}

	@Override
	public void achievementUpdate(String user, Achievement a) {
		AchievementComposite p = (AchievementComposite) a;
		int number = p.getPoints() + composite.getPoints();
		p.setPoints(number);
		composite.setPoints(number);
	}

}
