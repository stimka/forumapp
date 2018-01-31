/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package by.zagursky.gameforum.service;

import by.zagursky.gameforum.model.Role;

import java.util.List;


public interface RoleService {
    
    Role findById(int id);
    
    Role findByName(String name);
    
    List<Role> findAll();
    
    void save(Role role);
    
    void save(String name);
    
    void delete(Role role);
    
    void delete(String name);
    
    void delete(int id);
    
}
