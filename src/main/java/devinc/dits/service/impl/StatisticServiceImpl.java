package devinc.dits.service.impl;

import devinc.dits.entity.Statistic;
import devinc.dits.entity.User;
import devinc.dits.repository.QuestionRepository;
import devinc.dits.repository.StatisticRepository;
import devinc.dits.repository.UserRepository;
import devinc.dits.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {

    StatisticRepository repository;

    UserRepository userRepository;

    QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    public void setRepository(StatisticRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<Statistic> findAll() {
        return repository.findAll(Statistic.class);
    }

    @Transactional
    @Override
    public void update(Statistic t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Statistic t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Statistic t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Statistic getById(int id) {
        return repository.getById(Statistic.class, id);
    }

    @Transactional
    @Override
    public List<Statistic> getByUserId(int userId) {
        return repository.getByUserId(userId);
    }

}
