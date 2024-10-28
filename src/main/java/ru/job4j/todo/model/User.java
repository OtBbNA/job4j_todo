package ru.job4j.todo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "todo_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String login;

    @NonNull
    private String password;

    @NonNull
    private String timezone;
}
