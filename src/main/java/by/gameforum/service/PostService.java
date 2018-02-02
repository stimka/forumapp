
package by.gameforum.service;

import by.gameforum.model.Topic;
import by.gameforum.model.User;
import by.gameforum.model.Post;

import java.util.List;
import java.util.Set;


public interface PostService {

    Post findById(int id);

    List<Post> findAll();

    Set<Post> findRecent();

    Set<Post> findAllByOrderByCreationDateDesc();

    Set<Post> findByUser(User user);

    Set<Post> findByTopic(int idTopic);

    Set<Post> findByTopic(Topic topic);

    void save(Post post);

    void delete(int id);

    void delete(Post post);

    void save(String content,
              String username,
              int idTopic);

}
