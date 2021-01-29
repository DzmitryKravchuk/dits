package devinc.dits.repository;

import devinc.dits.entity.Literature;
import devinc.dits.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class LiteratureRepository implements DaoRepos<Literature> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public Set<Literature> getByQuestionId(int questionID) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Literature where question.questionId = :param");
        query.setParameter("param", questionID);
        return (Set<Literature>) query.list();
    }
}
