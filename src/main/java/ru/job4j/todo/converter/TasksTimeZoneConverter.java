package ru.job4j.todo.converter;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;
import java.util.Collection;

public class TasksTimeZoneConverter  {

    public static Collection<Task> convertTime(Collection<Task> tasks, User user) {
        for (Task task : tasks) {
            task.getCreated()
                    .atZone(ZoneId.of("UTC"))
                    .withZoneSameLocal(ZoneId.of(user.getTimezone()))
                    .toLocalDateTime();
        }
        return tasks;
    }
}