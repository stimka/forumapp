package by.gameforum.service;

import by.gameforum.model.UserProfile;

public interface UserProfileService {

    public UserProfile findById(int userId);

    public UserProfile findByUsername(String username);

}
