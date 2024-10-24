package ru.job4j.todo.store.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.CrudRepository;

import java.util.Collection;
import java.util.Map;

@Repository
@AllArgsConstructor
public class SimpleCategoryStore implements CategoryStore {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Category> findAll() {
        return crudRepository
                .query("FROM Category", Category.class);
    }

    @Override
    public Collection<Category> findAllById(Collection<Integer> category) {
        return crudRepository.query("FROM Category f WHERE f.id IN :fIds", Category.class,
                Map.of("fIds", category));
    }
}
