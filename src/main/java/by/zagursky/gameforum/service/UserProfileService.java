package by.zagursky.gameforum.service;

import by.zagursky.gameforum.model.UserProfile;

public interface UserProfileService {

    public UserProfile findById(int userId);

    public UserProfile findByUsername(String username);

}
