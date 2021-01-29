package devinc.dits.repository;

import devinc.dits.entity.Answer;
import devinc.dits.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepository implements DaoRepos<Answer> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public Answer getByDescription(String chosenAnswer) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Answer where description = :param");
        query.setParameter("param", chosenAnswer);
        return (Answer) query.getSingleResult();
    }
}
