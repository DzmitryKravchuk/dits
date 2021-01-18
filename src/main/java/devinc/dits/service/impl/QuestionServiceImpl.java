package devinc.dits.service.impl;

import devinc.dits.entity.Question;
import devinc.dits.repository.QuestionRepository;
import devinc.dits.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    QuestionRepository repository;

    @Autowired
    public void setRepository(QuestionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Question> findAll() {
        return repository.findAll(Question.class);
    }

    @Transactional
    @Override
    public void update(Question t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Question t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Question t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Question getById(int id) {
        return repository.getById(Question.class, id);
    }
}
