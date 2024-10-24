package ru.job4j.todo.store.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.store.CrudRepository;
import ru.job4j.todo.store.priority.PriorityStore;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class SimplePriorityStore implements PriorityStore {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Priority> findAll() {
        return crudRepository
                .query("FROM Priority", Priority.class);
    }
}