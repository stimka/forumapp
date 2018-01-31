/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package by.zagursky.gameforum.service;

import by.zagursky.gameforum.model.Post;
import by.zagursky.gameforum.model.Topic;
import by.zagursky.gameforum.model.User;

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
