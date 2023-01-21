drop table if exists users;
create table users (
  id serial primary key,
  email varchar not null
);

insert into users(email) values ('1@mail.ru');
insert into users(email) values ('2@mail.ru');
insert into users(email) values ('3@mail.ru');
insert into users(email) values ('4@mail.ru');
insert into users(email) values ('5@mail.ru');