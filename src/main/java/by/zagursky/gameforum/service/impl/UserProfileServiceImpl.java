package by.zagursky.gameforum.service.impl;

import by.zagursky.gameforum.model.User;
import by.zagursky.gameforum.model.UserProfile;
import by.zagursky.gameforum.service.PostService;
import by.zagursky.gameforum.service.TopicService;
import by.zagursky.gameforum.service.UserProfileService;
import by.zagursky.gameforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Evgeny Yushkevich on 08.05.2017.
 */

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @Override
    public UserProfile findById(int userId) {
        UserProfile userProfile = new UserProfile();
        User user = userService.findById(userId);
        userProfile.setUser(user);
        userProfile.setPosts(postService.findByUser(user));
        userProfile.setTopics(topicService.findByUser(user));
        return userProfile;
    }

    @Override
    public UserProfile findByUsername(String username) {
        return findById(userService.findByUsername(username).getId());
    }

}
