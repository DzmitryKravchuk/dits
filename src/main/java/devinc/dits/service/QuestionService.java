package devinc.dits.service;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Question;
import devinc.dits.entity.Test;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();

    void update(Question t);

    void delete(Question t);

    void save(Question t);

    Question getById(int id);

    List<Answer> getAnswersByQuestion(int questionId);

    Question createQuestionByDescription(String questionName, Test test);

    Question getQuestionByDescription(String questionDescription);

    Question getFullInfoById(int id);

}
