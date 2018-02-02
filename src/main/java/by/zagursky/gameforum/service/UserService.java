package by.zagursky.gameforum.service;

import by.zagursky.gameforum.controller.form.UserEditForm;
import by.zagursky.gameforum.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void create(User user);

    void remove(int id);

    void remove(User user);

    void remove(User user,
                String password);

    void save(User user,
              UserEditForm userEditForm);

}
