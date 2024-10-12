package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;
import java.util.Collection;

public interface TaskService {

    Task save(Task task);

    boolean update(Task task);

    Task findById(int id);

    Collection<Task> findAll();

    Collection<Task> findByDone(boolean done);

    void deleteById(int id);
}
