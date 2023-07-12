package taskmaster.todo.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import taskmaster.todo.dao.UserDao;
import taskmaster.todo.exeption.DataException;
import taskmaster.todo.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Autowired
    protected UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from User u left join fetch u.tasks "
                                    + "where u.login = :login", User.class)
                    .setParameter("login", login)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataException("Couldn't get user by login: "
                    + login, e);
        }
    }
}
