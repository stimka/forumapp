package by.gameforum.service;

import by.gameforum.model.Section;
import by.gameforum.model.Topic;
import by.gameforum.model.User;

import java.util.List;
import java.util.Set;


public interface TopicService {
    
    List<Topic> findAll();
    
    Topic findById(int id);
    
    Set<Topic> findRecent();
    
    Set<Topic> findAllByOrderByCreationDateDesc();
    
    Set<Topic> findBySection(Section section);
    
    Set<Topic> findBySection(String sectionName);
    
    Topic save(Topic topic);
    
    Set<Topic> findBySection(int id);
    
    Set<Topic> findByUser(User user);
    
    void delete(int id);
    
    void delete(Topic topic);
    
}
