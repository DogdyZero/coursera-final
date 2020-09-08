package br.com.gamification.storage;

public class AchievementStorageFactory{

    private static AchievementStorage achievementStorage;
    private AchievementStorageFactory(){}

    public static AchievementStorage getAchievementStorage(){
        return achievementStorage;
    }

    public static void setAchievementStorage(AchievementStorage a){
        achievementStorage = a;
    }

    static{
        setAchievementStorage(MemoryAchievementStorage.getInstance());
    }
}
