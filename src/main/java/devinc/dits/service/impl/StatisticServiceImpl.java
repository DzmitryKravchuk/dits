package devinc.dits.service.impl;

import devinc.dits.entity.Statistic;
import devinc.dits.repository.StatisticRepository;
import devinc.dits.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    StatisticRepository repository;

    @Autowired
    public void setRepository(StatisticRepository repository) {
        this.repository = repository;
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
}
