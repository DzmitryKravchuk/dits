package devinc.dits.repository;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepository implements DaoRepos<Question> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public List<Answer> getAnswersByQuestion(int questionId) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Answer where question.questionId = :param");
        query.setParameter("param", questionId);
        return query.list();
    }
}
