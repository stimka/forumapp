/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package by.gameforum.model.repository;


import by.gameforum.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByName(String name);
    
}
