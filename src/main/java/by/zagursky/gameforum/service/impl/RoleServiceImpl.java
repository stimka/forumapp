package by.zagursky.gameforum.service.impl;

import by.zagursky.gameforum.model.Role;
import by.zagursky.gameforum.model.repository.RoleRepository;
import by.zagursky.gameforum.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Evgeny Yushkevich on 08.05.2017.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(int id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void save(String name) {
        save(new Role(name));
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void delete(String name) {
        delete(findByName(name));
    }

    @Override
    public void delete(int id) {
        delete(findById(id));
    }

}
