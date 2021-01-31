package devinc.dits.repository;

import devinc.dits.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class QuestionRepository implements DaoRepos<Question> {
    public static final String SQL_SELECT_QUESTION_LITERATURE_BY_ID = "SELECT q.questionId, q.description as qdescription, testId, lit.literatureId, lit.description as litdescription, link.linkId, link FROM question q, literature lit, link link WHERE q.questionId=? and q.questionId=lit.questionId and lit.literatureId=link.literatureId";
    public static final String SQL_SELECT_QUESTION_STATISTIC_BY_ID = "SELECT stat.statisticId, correct, q.questionId, q.description FROM question q, statistic stat WHERE q.questionId=? and q.questionId=stat.questionId";
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
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Answer where question.questionId = :param");
        query.setParameter("param", questionId);
        return query.list();
    }

    public Question getQuestionByDescription(String questionDescription) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Question where description = :param");
        query.setParameter("param", questionDescription);
        return (Question) query.getSingleResult();
    }

    public Question getFullInfoById(int id) {
        Question question = new Question();
        question.setQuestionId(id);
        Set<Literature> literatureSet = new HashSet<>();
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_QUESTION_LITERATURE_BY_ID);
        q.setParameter(1, id);
        List<Object[]> qResultList = q.getResultList();
        for (Object[] a : qResultList) {
            question.setDescription((String) a[1]);

            Test test = new Test();
            test.setTestId((int) a[2]);
            question.setTest(test);

            Literature lit = new Literature();
            Link link = new Link();
            lit.setLink(link);
            lit.setQuestion(question);  //recursive
            lit.setLiteratureId((int) a[3]);
            lit.setDescription((String) a[4]);
            link.setLiterature(lit);  //recursive
            link.setLinkId((int) a[5]);
            link.setLink((String) a[6]);

            literatureSet.add(lit);
        }
        question.setLiteratureSet(literatureSet);

        return question;
    }

    public Integer getTotalAnsweredQuestionCount(int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_QUESTION_STATISTIC_BY_ID);
        q.setParameter(1, questionId);
        List<Object[]> qResultList = q.getResultList();

        return new Integer(qResultList.size());
    }

    public Double getCorrectAnswersByQuestionRate(int questionId) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_QUESTION_STATISTIC_BY_ID);
        q.setParameter(1, questionId);
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

    public List<Question> getQuestionsByTestId(int testId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Question where test.testId = :param");
        query.setParameter("param", testId);
        return query.list();
    }
}
