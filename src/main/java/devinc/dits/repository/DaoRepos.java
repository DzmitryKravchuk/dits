package devinc.dits.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoRepos<T> {

    SessionFactory getBeanToBeAutowired();

    //   default void create(T t) {
    //       getBeanToBeAutowired().getCurrentSession().saveOrUpdate(t);
    //   }

    default void update(T t) {
        getBeanToBeAutowired().getCurrentSession().update(t);
    }

    default void delete(T t) {
        getBeanToBeAutowired().getCurrentSession().delete(t);
    }

    //default void deleteAll(Class T) {
    //    Query query = getBeanToBeAutowired().getCurrentSession().createQuery("delete from " + T.getSimpleName());
    //    query.executeUpdate();
    // }

    default void save(T t) {
        getBeanToBeAutowired().getCurrentSession().save(t);
    }

    @SuppressWarnings("unchecked")
    default List<T> findAll(Class T) {
        List<T> tList = getBeanToBeAutowired().getCurrentSession().createQuery("from " + T.getSimpleName()).list();
        return tList;
    }

    default T getById(Class T, int id) {
        Session session = getBeanToBeAutowired().getCurrentSession();
        return (T) session.get(T, id);
    }

}
