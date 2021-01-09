package devinc.dits.service;

import devinc.dits.entity.Test;

import java.util.List;

public interface TestService {
    List<Test> findAll();

    void update(Test t);

    void delete(Test t);

    void save(Test t);

    Test getById(int id);
}
