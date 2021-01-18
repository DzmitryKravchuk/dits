package devinc.dits.service;

import devinc.dits.entity.Question;
import devinc.dits.entity.Role;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();

    void update(Question t);

    void delete(Question t);

    void save(Question t);

    Question getById(int id);
}
