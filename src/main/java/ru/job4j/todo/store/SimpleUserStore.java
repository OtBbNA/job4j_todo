package ru.job4j.todo.store;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleUserStore implements UserStore {

    @NonNull
    private final SessionFactory sf;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserStore.class);

    @Override
    public Optional<User> save(User user) {
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            LOG.error("Пользователь с такой почтой уже зарегистрирован");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional rsl = Optional.empty();
        Session session = sf.getCurrentSession();
        try {
            session.getTransaction().begin();
            Query<User> query = session.createQuery("FROM User AS i WHERE  login = :fLogin AND password = :fPassword", User.class)
                    .setParameter("fLogin", email)
                    .setParameter("fPassword", password);
            rsl = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rsl;
    }

    @Override
    public boolean deleteByEmail(String email) {
        boolean rsl = false;
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            int updated = session.createQuery(
                            "DELETE User WHERE login = :fLogin")
                    .setParameter("fLogin", email)
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = updated > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rsl;
    }

    @Override
    public Collection<User> findAll() {
        List<User> rsl = new ArrayList<>();
        Session session = sf.getCurrentSession();
        try  {
            session.getTransaction().begin();
            Query<User> query =  session.createQuery("FROM USER", User.class);
            rsl = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return rsl;
    }
}
