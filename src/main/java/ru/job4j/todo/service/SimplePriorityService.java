package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.store.PriorityStore;

import java.util.Collection;

@AllArgsConstructor
public class SimplePriorityService implements PriorityService {

    private final PriorityStore priorityStore;

    @Override
    public Collection<Priority> findAll() {
        return priorityStore.findAll();
    }
}
