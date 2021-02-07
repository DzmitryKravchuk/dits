package devinc.dits.repository;

import devinc.dits.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class StatisticRepository implements DaoRepos<Statistic> {
    private static final String SQL_SELECT_STATISTIC_QUESTION_TEST_BY_USER_ID = "SELECT stat.statisticId, date, correct, user.userId, user.firstName, lastName, login, q.questionId, q.description as questionDesc, test.testId, test.name, test.description as testDesc, test.topicId FROM statistic stat, user user, question q, test test WHERE stat.userId =? and stat.questionId=q.questionId and user.userId=stat.userId and q.testId=test.testId order by test.testId";

    private SessionFactory sessionFactory;

    @Autowired
    private QuestionRepository questionRepository;

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

    public Set<Test> getTestsPassedByUserId(int userId) {
        List<Statistic> statList = getByUserId(userId);
        Set<Test> testSet = new HashSet<>();
        for (Statistic st : statList) {
            testSet.add(st.getQuestion().getTest());
        }
        return testSet;
    }

    public Integer getTotalUserPassedTestCount(int userId, int testId) {
        List<Statistic> statList = getByUserId(userId);
        List<Statistic> statList4Test = statList.stream().filter(st -> st.getQuestion().getTest().getTestId() == testId).collect(Collectors.toList());
        int testQuestionCount = questionRepository.getQuestionsByTestId(testId).size();
        int totalPassedTestCount = statList4Test.size() / testQuestionCount;

        return new Integer(totalPassedTestCount);
    }

    public Double getCorrectAnswersUserPassedTestRate(int userId, int testId) {
        List<Statistic> statList = getByUserId(userId);
        Predicate<Statistic> isCorrect = stat -> stat.isCorrect() == true;
        Predicate<Statistic> hasTestId = stat -> stat.getQuestion().getTest().getTestId() == testId;
        int totalPassedQuestionCount = statList.stream().filter(hasTestId).collect(Collectors.toList())
                .size();
        int correctPassedQuestionCount = statList.stream().filter(hasTestId.and(isCorrect)).collect(Collectors.toList())
                .size();
        double correctAnswersUserPassedTestRate =(double) correctPassedQuestionCount/ (double) totalPassedQuestionCount*100;
        return new Double(round(correctAnswersUserPassedTestRate,1));
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
