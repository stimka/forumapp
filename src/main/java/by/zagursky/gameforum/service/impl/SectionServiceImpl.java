package by.zagursky.gameforum.service.impl;

import by.zagursky.gameforum.model.Section;
import by.zagursky.gameforum.model.repository.SectionRepository;
import by.zagursky.gameforum.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section findById(int id) {
        return sectionRepository.findOne(id);
    }

    @Override
    public Section findByName(String name) {
        return sectionRepository.findByName(name);
    }

    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void delete(int id) {
        delete(findById(id));
    }

    @Override
    public void delete(Section section) {
        sectionRepository.delete(section);
    }

}
