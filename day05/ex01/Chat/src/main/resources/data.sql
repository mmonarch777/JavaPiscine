insert into users (login, password) values ('first', '1111');
insert into users (login, password) values ('second', '2222');
insert into users (login, password) values ('third', '3333');
insert into users (login, password) values ('fourth', '4444');
insert into users (login, password) values ('fifth', '5555');

insert into chatrooms (name, owner) values ('firstRoom', 1);
insert into chatrooms (name, owner) values ('secondRoom', 2);
insert into chatrooms (name, owner) values ('thirdRoom', 3);
insert into chatrooms (name, owner) values ('fourthRoom', 4);
insert into chatrooms (name, owner) values ('fifthRoom', 5);

insert into messages (author, room, text, date_time) values (1, 5, 'hello', current_timestamp);
insert into messages (author, room, text, date_time) values (2, 4, 'whatsup', current_timestamp);
insert into messages (author, room, text, date_time) values (3, 3, 'im busy', current_timestamp);
insert into messages (author, room, text, date_time) values (4, 2, 'im beautiful', current_timestamp);
insert into messages (author, room, text, date_time) values (5, 1, 'im creezzy', current_timestamp);