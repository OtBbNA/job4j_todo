package ru.job4j.todo.store.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.CrudRepository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class SimpleUserStore implements UserStore {

    @NonNull
    private final CrudRepository crudRepository;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserStore.class);

    @Override
    public Optional<User> save(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            LOG.error("Пользователь с такой почтой уже зарегистрирован: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try {
            return crudRepository.optional("FROM User AS i WHERE  login = :fLogin AND password = :fPassword", User.class,
                    Map.of("fLogin", email, "fPassword", password));
        } catch (Exception e) {
            LOG.error("Пользователь не найден: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteByEmail(String email) {
        boolean rsl = false;
        try {
            crudRepository.run("DELETE User WHERE login = :fLogin", Map.of("fLogin", email));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Произошла ошибка при удалении: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public Collection<User> findAll() {
        List<User> rsl = new ArrayList<>();
        try  {
            rsl = crudRepository.query("FROM USER", User.class);
        } catch (Exception e) {
            LOG.error("Произошла ошибка во время поиска: " + e.getMessage());
        }
        return rsl;
    }
}
