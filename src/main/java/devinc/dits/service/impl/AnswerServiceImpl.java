package devinc.dits.service.impl;

import devinc.dits.entity.Answer;
import devinc.dits.repository.AnswerRepository;
import devinc.dits.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    AnswerRepository repository;

    @Autowired
    public void setRepository(AnswerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Answer> findAll() {
        return repository.findAll(Answer.class);
    }

    @Transactional
    @Override
    public void update(Answer t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Answer t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Answer t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Answer getById(int id) {
        return repository.getById(Answer.class,id);
    }

    @Transactional
    @Override
    public Answer getByDescription(String chosenAnswer) {
        return repository.getByDescription(chosenAnswer);
    }
}
