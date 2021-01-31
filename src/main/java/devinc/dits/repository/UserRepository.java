package devinc.dits.repository;

import devinc.dits.entity.Role;
import devinc.dits.entity.Test;
import devinc.dits.entity.Topic;
import devinc.dits.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements DaoRepos<User> {

    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public User getByLogin(String currentPrincipalName) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login = :param");
        query.setParameter("param", currentPrincipalName);
        return (User) query.getSingleResult();
    }
}
