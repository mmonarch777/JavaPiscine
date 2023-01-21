drop table if exists messages cascade;
drop table if exists chatrooms cascade;
drop table if exists users cascade;

create table users (id serial primary key,
login varchar(30) not null,
password varchar(30) not null);

create table chatrooms(id serial primary key,
name_room varchar(30) not null,
owner integer references users(id) not null);

create table messages(id serial primary key,
author integer references users(id) not null,
room integer references chatrooms(id) not null,
text text not null,
time_date timestamp);


