package ru.job4j.todo.store;


import lombok.AllArgsConstructor;
import ru.job4j.todo.model.Priority;

import java.util.Collection;

@AllArgsConstructor
public class SimplePriorityStore implements PriorityStore {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Priority> findAll() {
        return crudRepository
                .query("FROM Priority", Priority.class);
    }
}