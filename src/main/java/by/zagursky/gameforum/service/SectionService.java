package by.zagursky.gameforum.service;

import by.zagursky.gameforum.model.Section;

import java.util.List;


public interface SectionService {
    
    List<Section> findAll();
    
    Section findById(int id);
    
    Section findByName(String name);
    
    Section save(Section section);
    
    void delete(int id);
    
    void delete(Section section);
    
}
