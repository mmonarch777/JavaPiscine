drop table if exists users cascade;
create table users (
    id serial primary key,
    username varchar not null,
    password varchar not null
);
insert into users(username, password) values ('1', '111');
insert into users(username, password) values ('2', '222');
insert into users(username, password) values ('3', '333');