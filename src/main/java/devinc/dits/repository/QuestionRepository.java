package devinc.dits.repository;

import devinc.dits.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class QuestionRepository implements DaoRepos<Question> {
    public static final String SQL_SELECT_QUESTION_LITERATURE_BY_ID = "SELECT q.questionId, q.description as qdescription, testId, lit.literatureId, lit.description as litdescription, link.linkId, link FROM question q, literature lit, link link WHERE q.questionId=? and q.questionId=lit.questionId and lit.literatureId=link.literatureId";
    public static final String SQL_SELECT_QUESTION_LITERATURE_ANSWER_BY_ID = "SELECT question.questionId, question.description as qdesc, question.testId, literature.literatureId, literature.description as ldesc, l.linkId, l.link, a.answerId, a.description as adesc, a.correct FROM question LEFT JOIN literature ON question.questionId = literature.questionId LEFT JOIN answer a ON question.questionId = a.questionId LEFT JOIN link l ON literature.literatureId = l.literatureId WHERE question.questionId=?";
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
        Set<Answer> answerSet = new HashSet<>();
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_QUESTION_LITERATURE_ANSWER_BY_ID);
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
            link.setLiterature(lit);  //recursive

            if (a[3] != null) {
                lit.setLiteratureId((int) a[3]);
                lit.setDescription((String) a[4]);
            }

            if (a[5] != null) {
                link.setLinkId((int) a[5]);
                link.setLink((String) a[6]);
                literatureSet.add(lit);
            }



            Answer answer = new Answer();
            answer.setQuestion(question);   //recursive

            if (a[7] != null) {
                answer.setAnswerId((int) a[7]);
                answer.setDescription((String) a[8]);
                answer.setCorrect((boolean) a[9]);
                answerSet.add(answer);
            }

        }

        question.setLiteratureSet(literatureSet);
        question.setAnswerSet(answerSet);
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
        double d = correctAnswersCount.doubleValue() / totalAnswersCount.doubleValue() * 100;
        return new Double(round(d,1));
    }

    public List<Question> getQuestionsByTestId(int testId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Question where test.testId = :param");
        query.setParameter("param", testId);
        return query.list();
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
