package devinc.dits.repository;

import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TopicRepository implements DaoRepos<Topic> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public List<Test> getTestsByTopic(int topicId) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Test where topic.topicId = :param");
        query.setParameter("param", topicId);
        return query.list();

    }

    public Topic getByTopicName(String topicName) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Topic where name = :param");
        query.setParameter("param", topicName);
        return (Topic) query.getSingleResult();
    }
}
