package br.com.gamification;
import org.junit.Test;

import br.com.gamification.model.Achievement;
import br.com.gamification.model.AchievementComposite;
import br.com.gamification.services.ForumService;
import br.com.gamification.services.ForumServiceGamificationProxy;
import br.com.gamification.storage.MemoryAchievementStorage;
import junit.framework.Assert;

public class ForumServiceGamificationProxyTest {

	@Test
	public void testAddNewTopic() {
		ForumService services = new ForumServiceGamificationProxy();
		services.addTopic("Douglas", "Primeiro Tópico");
		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","CREATION");
		Assert.assertEquals("CREATION", res.getName());
	}
	
	@Test
	public void testAddNewComment() {
		ForumService services = new ForumServiceGamificationProxy();
		services.addTopic("Douglas", "Primeiro Tópico");
		services.addComment("Douglas", "Primeiro Tópico", "Meu primeiro comentário");

		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","PARTICIPATION");
		Assert.assertEquals("PARTICIPATION", res.getName());
	}
	
	@Test
	public void testLikeTopic() {
		ForumService services = new ForumServiceGamificationProxy();
		services.addTopic("Douglas", "Primeiro Tópico");
		services.likeTopic("Douglas", "Primeiro Tópico", "Douglas");

		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","CREATION");
		Assert.assertEquals("CREATION", res.getName());
	}
	
	@Test
	public void testLikeComment() {
		ForumService services = new ForumServiceGamificationProxy();
		services.addTopic("Douglas", "Primeiro Tópico");
		services.addComment("Douglas", "Primeiro Tópico", "Meu primeiro comentário");

		services.likeComment("Douglas", "Primeiro Tópico", "Meu primeiro comentário", "Douglas");

		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","PARTICIPATION");
		Assert.assertEquals("PARTICIPATION", res.getName());
	}
	
	@Test
	public void testAddTwiceTopic() {
		ForumService services = new ForumServiceGamificationProxy();
		services.addTopic("Douglas", "Primeiro Tópico");
		services.addTopic("Douglas", "Segundo Tópico");


		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","CREATION");
		AchievementComposite composite = (AchievementComposite)res;
		Assert.assertEquals(10, composite.getPoints());
	}

	@Test
	public void testLotTopicCommentAddAndLike() {
		ForumService services = new ForumServiceGamificationProxy();
		services.addTopic("Douglas", "Primeiro Tópico");
		services.likeTopic("Douglas", "Primeiro Tópico", "Douglas");

		services.addTopic("Douglas", "Segundo Tópico");
		services.likeTopic("Douglas", "Segundo Tópico", "Douglas");

		services.addTopic("Douglas", "Terceiro Tópico");
		services.addTopic("Douglas", "Quarto Tópico");

		services.likeTopic("Douglas", "Terceiro Tópico", "Douglas");

		

		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","CREATION");
		AchievementComposite composite = (AchievementComposite)res;
		Assert.assertEquals(23, composite.getPoints());
	}
	
	@Test
	public void testGetBadgeInventor() {
		ForumService services = new ForumServiceGamificationProxy();
		
		for(int i = 0 ; i<=20;i++) {
			services.addTopic("Douglas", i+ "Tópico");
		}
		
		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","CREATION");
		AchievementComposite composite = (AchievementComposite)res;
		Assert.assertEquals("INVENTOR", composite.getBadge());
	}

	@Test
	public void testGetBadgePartOfTheCommunity() {
		ForumService services = new ForumServiceGamificationProxy();
		
		services.addTopic("Douglas", "Primeiro Tópico");
		for(int i = 0 ; i<=100;i++) {
			services.addComment("Douglas", "Primeiro Tópico", i+" Comentario");
		}
		
		Achievement res = MemoryAchievementStorage.getInstance().getAchievement("Douglas","PARTICIPATION");
		AchievementComposite composite = (AchievementComposite)res;
		System.out.println(composite.getPoints());
		Assert.assertEquals("PART OF THE COMMUNITY", composite.getBadge());
	}

}
