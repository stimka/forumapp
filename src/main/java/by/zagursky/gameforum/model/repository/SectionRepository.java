/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package by.zagursky.gameforum.model.repository;

import by.zagursky.gameforum.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SectionRepository extends JpaRepository<Section, Integer> {
    
    Section findByName(String name);
    
}
