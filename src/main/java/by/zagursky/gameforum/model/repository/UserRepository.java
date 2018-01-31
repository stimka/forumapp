/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package by.zagursky.gameforum.model.repository;

import by.zagursky.gameforum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
}
