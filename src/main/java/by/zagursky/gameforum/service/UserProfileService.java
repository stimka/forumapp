package by.zagursky.gameforum.service;

import by.zagursky.gameforum.model.UserProfile;

/**
 * Created by Evgeny Yushkevich on 08.05.2017.
 */
public interface UserProfileService {

    public UserProfile findById(int userId);

    public UserProfile findByUsername(String username);

}
