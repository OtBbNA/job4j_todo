package ru.job4j.todo.model;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String title;

    private String description;

    private LocalDateTime created = LocalDateTime.now(ZoneId.of("UTC")).withNano(0);

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tasks_categories",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "categories_id") }
    )
    private List<Category> categories = new ArrayList<>();
}
