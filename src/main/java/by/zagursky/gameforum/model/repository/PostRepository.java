/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package by.zagursky.gameforum.model.repository;

import by.zagursky.gameforum.model.Post;
import by.zagursky.gameforum.model.Topic;
import by.zagursky.gameforum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface PostRepository extends JpaRepository<Post, Integer> {
    
    Set<Post> findByUser(User user);
    
    Set<Post> findByTopic(Topic topic);
    
    Set<Post> findAllByOrderByCreationDateDesc();
    
    Set<Post> findTop5ByOrderByCreationDateDesc();
}
