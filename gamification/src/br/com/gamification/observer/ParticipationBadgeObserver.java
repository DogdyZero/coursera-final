package br.com.gamification.observer;

import br.com.gamification.model.Achievement;
import br.com.gamification.model.AchievementComposite;

public class ParticipationBadgeObserver implements AchievementObserver {

	private AchievementComposite composite;
	
	public ParticipationBadgeObserver(AchievementComposite composite) {
		this.composite = composite;
		
	}
	@Override
	public void achievementUpdate(String user, Achievement a) {
		AchievementComposite newAchiComposite = (AchievementComposite)a;
		if(newAchiComposite.getPoints()>100) {
			newAchiComposite.setBadge("PART OF THE COMMUNITY");
		}
	}

}
