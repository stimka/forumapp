/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package by.gameforum.model.repository;

import by.gameforum.model.Topic;
import by.gameforum.model.User;
import by.gameforum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface PostRepository extends JpaRepository<Post, Integer> {
    
    Set<Post> findByUser(User user);
    
    Set<Post> findByTopic(Topic topic);
    
    Set<Post> findAllByOrderByCreationDateDesc();
    
    Set<Post> findTop5ByOrderByCreationDateDesc();
}
