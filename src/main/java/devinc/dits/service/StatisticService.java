package devinc.dits.service;

import devinc.dits.entity.Statistic;
import devinc.dits.entity.models.UserStatistic;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    List<Statistic> findAll();

    void update(Statistic t);

    void delete(Statistic t);

    void save(Statistic t);

    Statistic getById(int id);

    List<Statistic> getByUserId (int userId);

    List<UserStatistic> getAllUserStatistic();
}
