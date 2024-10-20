package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class SimpleTaskStore implements TaskStore {

    @NonNull
    private final CrudRepository crudRepository;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleTaskStore.class);

    @Override
    public Task save(Task task) {
        Task rsl = null;
        try {
            crudRepository.run(session -> session.persist(task));
            rsl = task;
        } catch (Exception e) {
            LOG.error("Ошибка при сохранении задачи: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public boolean update(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "UPDATE Task SET title = :fTitle, description = :fDescription, user_id = :fUserId WHERE id = :fId",
                    Map.of("fId", task.getId(),
                            "fTitle", task.getTitle(),
                            "fDescription", task.getDescription(),
                            "fUserId", task.getUser().getId())
            );
            rsl = true;
        } catch (Exception e) {
            LOG.error("Ошибка при обновлении задачи: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public boolean updateDoneToTrue(int id) {
        boolean rsl = false;
        try {
            crudRepository.run("UPDATE Task SET done = :fDone WHERE id = :fId",
                    Map.of("fId", id, "fDone", true));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Ошибка при обновлении статуса задачи: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public Optional<Task> findById(int id) {
        Optional<Task> rsl = Optional.empty();
        try {
             rsl = crudRepository.optional("FROM Task AS i WHERE i.id = :fId", Task.class, Map.of("fId", id));
        } catch (Exception e) {
            LOG.error("Произошла ошибка во время поиска: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public Collection<Task> findByUser(User user) {
        List<Task> rsl = new ArrayList<>();
        try {
            rsl = crudRepository.query("FROM Task AS i WHERE i.user = :fUser", Task.class,
                    Map.of("fUser", user));
        } catch (Exception e) {
            LOG.error("Произошла ошибка во время поиска: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public Collection<Task> findByDoneAndUser(boolean done, User user) {
        List<Task> rsl = new ArrayList<>();
        try {
            rsl = crudRepository.query("FROM Task AS i WHERE i.done = :fDone AND i.user = :fUser", Task.class,
                    Map.of("fDone", done, "fUser", user));
        } catch (Exception e) {
            LOG.error("Произошла ошибка во время поиска: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        try {
            crudRepository.run("DELETE Task WHERE id = :fId", Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Произошла ошибка при удалении: " + e.getMessage());
        }
        return rsl;
    }
}