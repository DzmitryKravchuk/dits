package devinc.dits.repository;

import devinc.dits.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticRepository implements DaoRepos<Statistic> {
    public static final String SQL_SELECT_STATISTIC_QUESTION_TEST_BY_USER_ID = "SELECT stat.statisticId, date, correct, user.userId, user.firstName, lastName, login, q.questionId, q.description as questionDesc, test.testId, test.name, test.description as testDesc, test.topicId FROM statistic stat, user user, question q, test test WHERE stat.userId =? and stat.questionId=q.questionId and user.userId=stat.userId and q.testId=test.testId order by test.testId";

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public List<Statistic> getByUserId(int userId) {
        List<Statistic> statList = new ArrayList<>();
        User user = new User();
        user.setUserId(userId);
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery(SQL_SELECT_STATISTIC_QUESTION_TEST_BY_USER_ID);
        q.setParameter(1, userId);
        List<Object[]> qResultList = q.getResultList();
        for (Object[] a : qResultList) {
            Statistic stat = new Statistic();
            stat.setUser(user);
            Question question = new Question();
            stat.setQuestion(question);
            Topic topic = new Topic();
            Test test = new Test();
            test.setTopic(topic);
            question.setTest(test);

            stat.setStatisticId((int) a[0]);
            java.sql.Date sqlDate = (Date) a[1];
            stat.setDate(sqlDate.toLocalDate());
            stat.setCorrect((boolean) a[2]);

            user.setFirstName((String) a[4]);
            user.setLastName((String) a[5]);
            user.setLogin((String) a[6]);

            question.setQuestionId((int) a[7]);
            question.setDescription((String) a[8]);

            test.setTestId((int) a[9]);
            test.setName((String) a[10]);
            test.setDescription((String) a[11]);

            topic.setTopicId((int) a[12]);

            statList.add(stat);
        }

        return statList;
    }

}
