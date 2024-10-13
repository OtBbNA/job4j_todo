# job4j_TODO

## О проекте
TODO приложение - это веб приложение для создания и управления списком заданий. 
Пользователь имеет следующие возможности:
- отслеживать состояние всех заданий,
- отслеживать только выполненные или только новые задания
- добавлять новые задания,
- редактировать задания, 
- удалять задания,
- изменять состояние задания c нового на выполненное

## Используемые инструменты:
- Java 17,
- CheckStyle 3.1.2,
- Spring Boot 2.7.6,
- Thymeleaf,
- Hibernate 5.6.11,
- Lombok 1.18.22,
- PostgreSql 42.5.9,
- Liquibase 4.15.0,
- css 3,
- js 1.8.5.

## Используемое ПО:
- IntelliJ IDEA 2021.3.0.0
- pgAdmin 4

## Запуск проекта:
- Скачасть проект
- С помощью pgAdmin 4 создать базу данных todo
- Измените файлы liquibase.properties* и hibernate.cfg.xml** в соответствии со своей конфигурцаией логин/пароль
- Через инструмент Maven пройдите по пути Job4j Todo/Plugins/liquibase и выберете функцию liquibase:update
- Пройдите по пути src/main/java/ru/job4j/todo, найдите и запустите класс Main.java
- Перейдите на страницу http://localhost:8080/ в браузере

###### **src/main/resources/db/liquibase.properties*
###### ***src/main/resources/hibernate.cfg.xml*

## Скриншоты основных страниц сайта:

Главная страница
![Главная страница](https://github.com/OtBbNA/job4j_todo/blob/master/imageReadme/homeAll.png)

Страница выполенных задач
![Страница выполенных задач](https://github.com/OtBbNA/job4j_todo/blob/master/imageReadme/homeCompleted.png)

Страница новых задач
![Страница новых задач](https://github.com/OtBbNA/job4j_todo/blob/master/imageReadme/homeNew.png)

Страница просмотра одной задачи
![Страница просмотра одной задачи](https://github.com/OtBbNA/job4j_todo/blob/master/imageReadme/one.png)

Страница редактирования задачи
![Страница редактирования задачи](https://github.com/OtBbNA/job4j_todo/blob/master/imageReadme/update.png)

Страница создания задачи
![Страница создания задачи](https://github.com/OtBbNA/job4j_todo/blob/master/imageReadme/create.png)