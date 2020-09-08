package br.com.gamification.observer;

import br.com.gamification.model.Achievement;
import br.com.gamification.model.AchievementComposite;

public class CreationBadgeObserver implements AchievementObserver{

	private AchievementComposite composite;

	public CreationBadgeObserver(AchievementComposite composite) {
		this.composite = composite;
	}

	@Override
	public void achievementUpdate(String user, Achievement a) {
		AchievementComposite newAchiComposite = (AchievementComposite)a;
		if(newAchiComposite.getPoints()>100) {
			newAchiComposite.setBadge("INVENTOR");
		}
	}

}
