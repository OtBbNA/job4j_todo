package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;
import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    Task save(Task task);

    boolean update(Task task);

    boolean updateDoneToTrue(int id);

    Optional<Task> findById(int id);

    Collection<Task> findAll();

    Collection<Task> findByDone(boolean done);

    boolean deleteById(int id);
}
