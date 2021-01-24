package devinc.dits.repository;

import devinc.dits.entity.Role;
import devinc.dits.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository implements DaoRepos<Role> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public Role getRoleByName(String roleName) {
        Session session= sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Role where name = :param");
        query.setParameter("param", roleName);
        return (Role) query.getSingleResult();
    }
}
