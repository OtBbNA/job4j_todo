package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {

    private final TaskStore taskStore;

    @Override
    public Task save(Task task) {
        return taskStore.save(task);
    }

    @Override
    public boolean update(Task task) {
        return taskStore.update(task);
    }

    @Override
    public boolean updateDoneToTrue(int id) {
        return taskStore.updateDoneToTrue(id);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskStore.findAll().stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Task> findByDone(boolean done) {
        return taskStore.findByDone(done).stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(int id) {
        return taskStore.deleteById(id);
    }
}
