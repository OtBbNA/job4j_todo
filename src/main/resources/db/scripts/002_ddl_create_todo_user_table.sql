create table todo_user
(
    id        serial primary key,
    name varchar        not null,
    login     varchar unique not null,
    password  varchar        not null
);