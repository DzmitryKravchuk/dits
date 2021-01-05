package devinc.dits.repository;

import devinc.dits.entity.Test;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
