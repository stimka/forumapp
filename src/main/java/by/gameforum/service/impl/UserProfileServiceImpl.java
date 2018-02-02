package by.gameforum.service.impl;

import by.gameforum.model.User;
import by.gameforum.service.PostService;
import by.gameforum.service.UserService;
import by.gameforum.model.UserProfile;
import by.gameforum.service.TopicService;
import by.gameforum.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
