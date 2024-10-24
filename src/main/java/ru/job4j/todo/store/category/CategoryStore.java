package ru.job4j.todo.store.category;

import ru.job4j.todo.model.Category;

import java.util.Collection;

public interface CategoryStore {

    Collection<Category> findAll();

    Collection<Category> findAllById(Collection<Integer> category);
}
