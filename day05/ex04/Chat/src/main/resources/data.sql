insert into users(login, password) values ('first', '111');
insert into users(login, password) values ('second', '222');
insert into users(login, password) values ('third', '333');
insert into users(login, password) values ('fourth', '444');
insert into users(login, password) values ('fifth', '555');

insert into chatrooms(name_room, owner) values ('room1', 1);
insert into chatrooms(name_room, owner) values ('room2', 2);
insert into chatrooms(name_room, owner) values ('room3', 3);
insert into chatrooms(name_room, owner) values ('room4', 4);
insert into chatrooms(name_room, owner) values ('room5', 5);

insert into messages(author, room, text, time_date) values (1, 5, 'Moscow', current_timestamp);
insert into messages(author, room, text, time_date) values (2, 4, 'Ufa', current_timestamp);
insert into messages(author, room, text, time_date) values (3, 3, 'Krasnoyarsk', current_timestamp);
insert into messages(author, room, text, time_date) values (4, 2, 'Piter', current_timestamp);
insert into messages(author, room, text, time_date) values (5, 1, 'Sochi', current_timestamp);