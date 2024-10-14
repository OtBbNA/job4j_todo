package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleTaskStore implements TaskStore {

    private final SessionFactory sf;

    @Override
    public Task save(Task task) {
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return task;
    }

    @Override
    public boolean update(Task task) {
        boolean rsl = false;
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            int updated = session.createQuery(
                            "UPDATE Task SET title = :fTitle, description = :fDescription WHERE id = :fId")
                    .setParameter("fId", task.getId())
                    .setParameter("fTitle", task.getTitle())
                    .setParameter("fDescription", task.getDescription())
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
    public boolean updateDoneToTrue(int id) {
        boolean rsl = false;
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            int updated = session.createQuery(
                            "UPDATE Task SET done = :fDone WHERE id = :fId")
                    .setParameter("fId", id)
                    .setParameter("fDone", true)
                    .executeUpdate();
            rsl = updated > 0;
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
    public Optional<Task> findById(int id) {
        Optional rsl = Optional.empty();
        Session session = sf.getCurrentSession();
        try {
            session.getTransaction().begin();
            Query<Task> query = session.createQuery("FROM Task AS i WHERE i.id = :fId", Task.class).setParameter("fId", id);
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
    public Collection<Task> findAll() {
        List<Task> rsl = new ArrayList<>();
        Session session = sf.getCurrentSession();
        try {
            session.getTransaction().begin();
            Query<Task> query = session.createQuery("FROM Task", Task.class);
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

    @Override
    public Collection<Task> findByDone(boolean done) {
        List<Task> rsl = new ArrayList<>();
        Session session = sf.getCurrentSession();
        try {
            session.getTransaction().begin();
            Query<Task> query = session.createQuery("FROM Task AS i WHERE i.done = :fDone", Task.class).setParameter("fDone", done);
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

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            int updated = session.createQuery(
                            "DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
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
}