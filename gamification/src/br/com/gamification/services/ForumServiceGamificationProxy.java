package br.com.gamification.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.gamification.model.AchievementComposite;
import br.com.gamification.model.Badge;
import br.com.gamification.model.Comment;
import br.com.gamification.model.Points;
import br.com.gamification.model.Topic;
import br.com.gamification.observer.AchievementObserver;
import br.com.gamification.observer.CreationBadgeObserver;
import br.com.gamification.observer.CreationPointsObserver;
import br.com.gamification.observer.ParticipationBadgeObserver;
import br.com.gamification.observer.ParticipationPointsObserver;
import br.com.gamification.storage.MemoryAchievementStorage;

public class ForumServiceGamificationProxy implements ForumService {

	private List<Topic> topics = new ArrayList<Topic>();
	
	@Override
	public void addTopic(String user, String topic) {
		topics.add(new Topic(user, topic));
		
		AchievementComposite composite = 
				new AchievementComposite("CREATION", new Badge("I CAN TALK"), new Points(5));

		createCreationObservers(composite);
		MemoryAchievementStorage.getInstance().addAchievement(user, composite);
	}

	@Override
	public void addComment(String user, String topic, String comment) {
		Optional<Topic> optional = findTopic(topic);
		if(optional.isPresent()) {
			optional.get().addComment(new Comment(user, comment));
			AchievementComposite composite = 
					new AchievementComposite("PARTICIPATION", new Badge("LET ME ADD"), new Points(3));
			
			createParticipationObservers(composite);			
			MemoryAchievementStorage.getInstance().addAchievement(user, composite);
		}
	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		Optional<Topic> optional = findTopic(topic);
		if(optional.isPresent()) {
			optional.get().addLike(topicUser);
			
			AchievementComposite composite = 
					new AchievementComposite("CREATION", new Badge("I CAN TALK"), new Points(1));

			createCreationObservers(composite);
			MemoryAchievementStorage.getInstance().addAchievement(user, composite);
		}
	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		Optional<Topic> optional = findTopic(topic);
		if(optional.isPresent()) {
			optional.get().addLikeComment(comment, commentUser);
			
			AchievementComposite composite = 
					new AchievementComposite("PARTICIPATION", new Badge("LET ME ADD"), new Points(1));
			
			createParticipationObservers(composite);
			MemoryAchievementStorage.getInstance().addAchievement(user, composite);

		}
	}
	
	private Optional<Topic> findTopic(String name) {
		return topics.stream().filter(t -> t.getTitulo().equalsIgnoreCase(name)).findFirst();
	}
	
	public void imprimir() {
		topics.forEach(t -> t.getComments().forEach(c->System.out.println(c.getTitulo())));
	}
	
	private void createCreationObservers(AchievementComposite composite){
		List<AchievementObserver> observers =  Arrays.asList(
				new CreationPointsObserver(composite),
				new CreationBadgeObserver(composite));
		MemoryAchievementStorage.getInstance().setObserver(observers);	
	}
	private void createParticipationObservers(AchievementComposite composite){
		List<AchievementObserver> observers = Arrays.asList(
				new ParticipationPointsObserver(composite),
				new ParticipationBadgeObserver(composite));
		MemoryAchievementStorage.getInstance().setObserver(observers);	
	}

}
