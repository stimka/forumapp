/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package by.zagursky.gameforum.model.repository;

import by.zagursky.gameforum.model.Section;
import by.zagursky.gameforum.model.Topic;
import by.zagursky.gameforum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface TopicRepository extends JpaRepository<Topic, Integer> {
    
    Set<Topic> findBySection(Section section);
    
    Set<Topic> findByUser(User user);
    
    Set<Topic> findAllByOrderByCreationDateDesc();
    
    Set<Topic> findTop5ByOrderByCreationDateDesc();
    
    
}
