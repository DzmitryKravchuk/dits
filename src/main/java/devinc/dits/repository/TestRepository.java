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
    public static final String SQL_SELECT_TEST_STATISTIC_BY_ID="SELECT stat.statisticId, correct, test.testId, test.name, test.description, q.questionId FROM test test, statistic stat, question q WHERE test.testId=? and test.testId=q.testId and q.questionId=stat.questionId";

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

    public Integer getTotalTestPassedCount(int testId) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_TEST_STATISTIC_BY_ID);
        q.setParameter(1, testId);
        List<Object[]> qResultList = q.getResultList();

        return new Integer(qResultList.size());
    }

    public Double getCorrectAnswersByTestRate(int testId) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_TEST_STATISTIC_BY_ID);
        q.setParameter(1, testId);
        List<Object[]> qResultList = q.getResultList();
        Integer totalAnswersCount = new Integer(qResultList.size());
        Integer correctAnswersCount = null;
        int c = 0;
        for (Object[] a : qResultList) {
            if ((boolean) a[1] == true) {
                c++;
            }
        }
        correctAnswersCount = new Integer(c);
        double d =correctAnswersCount.doubleValue()/totalAnswersCount.doubleValue()*100;
        return new Double(d);
    }
}
