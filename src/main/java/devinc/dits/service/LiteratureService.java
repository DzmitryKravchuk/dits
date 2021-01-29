package devinc.dits.service;

import devinc.dits.entity.Literature;

import java.util.List;
import java.util.Set;

public interface LiteratureService {
    List<Literature> findAll();

    void update(Literature t);

    void delete(Literature t);

    void save(Literature t);

    Literature getById(int id);

    Set<Literature> getByQuestionId(int questionID);
}
