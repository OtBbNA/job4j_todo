package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class CrudRepository {
    private final SessionFactory sf;

    public void run(Consumer<Session> command) {
        tx(session -> {
            command.accept(session);
            return null;
        });
    }

    public void run(String query, Map<String, Object> args) {
        run(session -> {
            var sq = session.createQuery(query);
            setParameters(sq, args);
            sq.executeUpdate();
        });
    }

    public <T> Optional<T> optional(String query, Class<T> cl, Map<String, Object> args) {
        return tx(session -> {
            var sq = session.createQuery(query, cl);
            setParameters(sq, args);
            return sq.uniqueResultOptional();
        });
    }

    public <T> List<T> query(String query, Class<T> cl) {
        return tx(session -> session.createQuery(query, cl).list());
    }

    public <T> List<T> query(String query, Class<T> cl, Map<String, Object> args) {
        return tx(session -> {
            var sq = session.createQuery(query, cl);
            setParameters(sq, args);
            return sq.list();
        });
    }

    public <T> T tx(Function<Session, T> command) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                T result = command.apply(session);
                transaction.commit();
                return result;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    private void setParameters(Query<?> query, Map<String, Object> args) {
        if (args != null) {
            args.forEach(query::setParameter);
        }
    }
}
