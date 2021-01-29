package devinc.dits.repository;

import devinc.dits.entity.Statistic;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticRepository implements DaoRepos<Statistic> {
    private static final String SQL_FULL_STAT = "SELECT stat.statisticId, date, correct, user.userId, user.firstName, lastName, login, q.questionId, q.description, test.testId, test.name, test.description, topic.topicId, topic.name, topic.description FROM statistic stat, user user, question q, test test, topic topic WHERE statisticId =? and stat.questionId=q.questionId and user.userId=stat.userId and q.questionId=test.testId and test.topicId=topic.topicId";
    private static final String SQL_LITERATURE = "SELECT lit.literatureId, description, link.linkId, link FROM literature lit, link link WHERE questionId=? and lit.literatureId=link.literatureId";
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }
}
