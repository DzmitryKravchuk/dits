package devinc.dits.service.impl;

import devinc.dits.entity.Question;
import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import devinc.dits.entity.models.TestStatistic;
import devinc.dits.repository.TestRepository;
import devinc.dits.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    TestRepository repository;

    @Autowired
    public void setRepository(TestRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Test> findAll() {
        return repository.findAll(Test.class);
    }

    @Transactional
    @Override
    public void update(Test t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Test t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Test t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Test getById(int id) {
        return repository.getById(Test.class, id);
    }

    @Transactional
    @Override
    public List<Question> getQuestionsByTest(String testName) {
        Test test = repository.getByTestName(testName);
        return repository.getQuestionsByTest(test.getTestId());
    }

    @Transactional
    @Override
    public Test createTestByName(String testName, Topic topic) {
        Test newTest = new Test();
        List <Test> l = findAll();
        for (Test t : l){
            if (testName.equals(t.getName())){
                newTest.setTestId(t.getTestId());
                newTest.setName(t.getName());
                newTest.setDescription(t.getDescription());
                return newTest;
            }
        }
        newTest.setName(testName);
        newTest.setDescription(testName);
        newTest.setTopic(topic);
        save(newTest);

        return newTest;
    }

    @Transactional
    @Override
    public List<TestStatistic> getTestStatisticList() {
        List<TestStatistic> testStatisticList = new ArrayList<>();
        List<Test> testList = repository.findAll(Test.class);

        testList.forEach(t -> testStatisticList.add(new TestStatistic(
                t.getTestId(),
                t.getName(),
                repository.getTotalTestPassedCount(t.getTestId()),
                repository.getCorrectAnswersByTestRate(t.getTestId())
        )));

        return testStatisticList;
    }

    @Transactional
    @Override
    public Test getByName(String testName) {
        return repository.getByTestName(testName);
    }
}
