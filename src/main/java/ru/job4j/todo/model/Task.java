package ru.job4j.todo.model;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private LocalDateTime created = LocalDateTime.now();

    boolean done;
}
