package devinc.dits.service.impl;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Question;
import devinc.dits.entity.Test;
import devinc.dits.repository.QuestionRepository;
import devinc.dits.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    QuestionRepository repository;

    @Autowired
    public void setRepository(QuestionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Question> findAll() {
        return repository.findAll(Question.class);
    }

    @Transactional
    @Override
    public void update(Question t) {
        repository.update(t);
    }

    @Transactional
    @Override
    public void delete(Question t) {
        repository.delete(t);
    }

    @Transactional
    @Override
    public void save(Question t) {
        repository.save(t);
    }

    @Transactional
    @Override
    public Question getById(int id) {
        return repository.getById(Question.class, id);
    }

    @Transactional
    @Override
    public List<Answer> getAnswersByQuestion(int questionId) {
        return repository.getAnswersByQuestion(questionId);
    }

    @Transactional
    @Override
    public Question createQuestionByDescription(String questionName, Test test) {
        Question newQuestion = new Question();
        List<Question> l = findAll();
        for (Question t : l) {
            if (questionName.equals(t.getDescription())) {
                newQuestion.setQuestionId(t.getQuestionId());
                newQuestion.setDescription(t.getDescription());
                return newQuestion;
            }
        }
        newQuestion.setDescription(questionName);
        newQuestion.setTest(test);
        save(newQuestion);

        return newQuestion;
    }

    @Transactional
    @Override
    public Question getQuestionByDescription(String questionDescription) {
        return repository.getQuestionByDescription(questionDescription);
    }

    @Transactional
    @Override
    public Question getFullInfoById(int id) {
        return repository.getFullInfoById(id);
    }
}
