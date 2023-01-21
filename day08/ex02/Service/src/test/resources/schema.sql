drop table if exists users cascade;
create table users (
    id integer identity primary key,
    email varchar(30) not null,
    password varchar(50) not null
);