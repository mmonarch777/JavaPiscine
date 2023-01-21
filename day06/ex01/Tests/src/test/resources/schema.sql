drop table if exists Product;
create table Product (
    id integer identity primary key,
    name varchar(30) not null unique,
    price integer not null
);