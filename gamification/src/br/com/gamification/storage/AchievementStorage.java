package br.com.gamification.storage;

import java.util.List;

import br.com.gamification.model.Achievement;

public interface AchievementStorage {
	void addAchievement(String user, Achievement a);
    List<Achievement> getAchievements(String user);
    Achievement getAchievement(String user, String achievementName);
}
