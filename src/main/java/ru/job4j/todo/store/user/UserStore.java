package ru.job4j.todo.store.user;

import ru.job4j.todo.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserStore {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean deleteByEmail(String email);

    Collection<User> findAll();
}
