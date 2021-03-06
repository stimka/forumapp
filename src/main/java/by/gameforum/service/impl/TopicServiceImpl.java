package by.gameforum.service.impl;

import by.gameforum.model.Topic;
import by.gameforum.model.Section;
import by.gameforum.model.User;
import by.gameforum.model.repository.TopicRepository;
import by.gameforum.service.SectionService;
import by.gameforum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SectionService sectionService;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(int id) {
        return topicRepository.findOne(id);
    }

    @Override
    public Set<Topic> findRecent() {
        return topicRepository.findTop5ByOrderByCreationDateDesc();
    }

    @Override
    public Set<Topic> findAllByOrderByCreationDateDesc() {
        return topicRepository.findAllByOrderByCreationDateDesc();
    }

    @Override
    public Set<Topic> findBySection(Section section) {
        return topicRepository.findBySection(section);
    }

    @Override
    public Set<Topic> findBySection(String sectionName) {
        return findBySection(sectionService.findByName(sectionName));
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Set<Topic> findBySection(int id) {
        return findBySection(sectionService.findById(id));
    }

    @Override
    public Set<Topic> findByUser(User user) {
        return topicRepository.findByUser(user);
    }

    @Override
    public void delete(int id) {
        delete(findById(id));
    }

    @Override
    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }

}
