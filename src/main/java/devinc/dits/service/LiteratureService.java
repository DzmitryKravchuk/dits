package devinc.dits.service;

import devinc.dits.entity.Literature;

import java.util.List;

public interface LiteratureService {
    List<Literature> findAll();

    void update(Literature t);

    void delete(Literature t);

    void save(Literature t);

    Literature getById(int id);
}
