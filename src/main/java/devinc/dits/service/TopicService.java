package devinc.dits.service;

import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopicService {
    List<Topic> findAll();

    void update(Topic t);

    void delete(Topic t);

    void save(Topic t);

    Topic getById(int id);

    List<Test> getTestsByTopic(int topicId);

    Topic getByTopicName(String topic);
}
