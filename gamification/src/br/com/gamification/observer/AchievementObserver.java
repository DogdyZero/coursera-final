package br.com.gamification.observer;

import br.com.gamification.model.Achievement;

public interface AchievementObserver {
    void achievementUpdate(String user, Achievement a);
}
