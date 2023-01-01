create database if not exists todolist_api;
use todolist_api;


create table if not exists todolist_api.person
(
    person_id         int auto_increment
        primary key,
    person_first_name varchar(64) null,
    person_last_name  varchar(64) null,
    person_age        smallint    null,
    person_birthday   date        null
);

create table if not exists todolist_api.users
(
    user_id     int auto_increment
        primary key,
    user_person int         null,
    user_name   varchar(64) null,
    user_pass   varchar(64) null,
    user_email  varchar(64) null,
    user_phone  varchar(24) null,
    constraint user_id
        unique (user_id),
    constraint user_person
        unique (user_person),
    constraint fk_person_id
        foreign key (user_person) references todolist_api.person (person_id)
);

create table if not exists todolist_api.tasks
(
    task_id                bigint auto_increment
        primary key,
    task_user              int          null,
    task_name              varchar(255) null,
    task_description       text         null,
    task_datetime_created  datetime     null,
    task_state             tinyint(1)   null,
    task_datetime_finished datetime     null,
    constraint fk_task_user_id
        foreign key (task_user) references todolist_api.users (user_id)
            on delete cascade
);

