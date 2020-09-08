package br.com.gamification.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gamification.model.Achievement;
import br.com.gamification.observer.AchievementObserver;

public class MemoryAchievementStorage implements AchievementStorage {

	private static MemoryAchievementStorage instance;
	private Map<String, List<Achievement>> storage = new HashMap<String, List<Achievement>>();
	private List<AchievementObserver> observer = new ArrayList<AchievementObserver>();

	private MemoryAchievementStorage() {
	}

	public static MemoryAchievementStorage getInstance() {
		if (instance == null) {
			instance = new MemoryAchievementStorage();
		}
		return instance;
	}

	public void addAchievement(String user, Achievement a) {
		List<Achievement> repository = storage.get(user);
		if (repository == null) {
			newInstance(user,a);
		} else {
			boolean isNew = true;
			for (Achievement achievement : repository) {
				if(a.getName().equals(achievement.getName())) {
					for (AchievementObserver achievementObserver : observer) {
						achievementObserver.achievementUpdate(user, achievement);
					}
					isNew = false;
				}
			}
			if(isNew)
				newInstance(user, a);
			
		}

	}
	private void newInstance(String user, Achievement a) {
		List<Achievement> newList = new ArrayList<Achievement>(); 
		newList.add(a);
		storage.put(user, newList);
	}

	public List<Achievement> getAchievements(String user) {
		return storage.get(user);
	}

	public Achievement getAchievement(String user, String achievementName) {
		return storage.get(user).stream().filter(p ->p.getName().equals(achievementName)).findFirst().get();
	}
	public void setObserver(List<AchievementObserver> observers) {
		observer.clear();
		observer.addAll(observers);
	}

}
