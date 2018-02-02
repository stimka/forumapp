package by.zagursky.gameforum.service.impl;

import by.zagursky.gameforum.model.Post;
import by.zagursky.gameforum.model.Topic;
import by.zagursky.gameforum.model.User;
import by.zagursky.gameforum.model.repository.PostRepository;
import by.zagursky.gameforum.service.PostService;
import by.zagursky.gameforum.service.TopicService;
import by.zagursky.gameforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Override
    public Post findById(int id) {
        return postRepository.findOne(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Set<Post> findRecent() {
        return postRepository.findTop5ByOrderByCreationDateDesc();
    }

    @Override
    public Set<Post> findAllByOrderByCreationDateDesc() {
        return postRepository.findAllByOrderByCreationDateDesc();
    }

    @Override
    public Set<Post> findByUser(User user) {
        return postRepository.findByUser(user);
    }

    @Override
    public Set<Post> findByTopic(int idTopic) {
        return findByTopic(topicService.findById(idTopic));
    }

    @Override
    public Set<Post> findByTopic(Topic topic) {
        return postRepository.findByTopic(topic);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(int id) {
        delete(findById(id));
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void save(String content,
                     String username,
                     int idTopic) {
        Post post = new Post();
        post.setTopic(topicService.findById(idTopic));
        post.setUser(userService.findByUsername(username));
        post.setContent(content);
        save(post);
    }
}
