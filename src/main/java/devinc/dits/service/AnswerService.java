package devinc.dits.service;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Role;

import java.util.List;

public interface AnswerService {
    List<Answer> findAll();

    void update(Answer t);

    void delete(Answer t);

    void save(Answer t);

    Answer getById(int id);
}
