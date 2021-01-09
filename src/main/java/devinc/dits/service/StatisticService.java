package devinc.dits.service;

import devinc.dits.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> findAll();

    void update(Statistic t);

    void delete(Statistic t);

    void save(Statistic t);

    Statistic getById(int id);
}
