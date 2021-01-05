package devinc.dits.service.impl;

import devinc.dits.entity.Topic;
import devinc.dits.repository.TopicRepository;
import devinc.dits.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    TopicRepository topicRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll(Topic.class);
    }

    @Transactional
    @Override
    public void update(Topic t) {
        topicRepository.update(t);
    }

    @Transactional
    @Override
    public void delete(Topic t) {
        topicRepository.delete(t);
    }

    @Transactional
    @Override
    public void save(Topic t) {
        topicRepository.save(t);
    }

    @Transactional
    @Override
    public Topic getById(int id) {
        return topicRepository.getById(Topic.class, id);
    }
}
