package devinc.dits.service.impl;

import devinc.dits.entity.Test;
import devinc.dits.repository.TestRepository;
import devinc.dits.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
