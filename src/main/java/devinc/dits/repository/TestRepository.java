package devinc.dits.repository;

import devinc.dits.entity.Question;
import devinc.dits.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository implements DaoRepos<Test> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public List<Question> getQuestionsByTest(int testId) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Question where test.testId = :param");
        query.setParameter("param", testId);
        return query.list();
    }

    public Test getByTestName(String testName) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Test where name = :param");
        query.setParameter("param", testName);
        return (Test) query.getSingleResult();
    }
}
