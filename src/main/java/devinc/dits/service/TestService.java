package devinc.dits.service;

import devinc.dits.entity.Question;
import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;

import java.util.List;

public interface TestService {
    List<Test> findAll();

    void update(Test t);

    void delete(Test t);

    void save(Test t);

    Test getById(int id);

    List<Question> getQuestionsByTest(String testName);

    Test createTestByName (String testName, Topic topic);
}
