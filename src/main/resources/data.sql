drop table user;

create table user
(
    id          uuid            not null DEFAULT random_uuid(),
    login       varchar(100)    not null,
    pswd        varchar(100),
    email       varchar(100),
    name        varchar(100),
    deleted  bool            not null default false
);

insert into user (login, email, name) values ('user1', 'user1@email', 'username');
insert into user (login, email, name) values ('user2', 'user2@email', 'username');
insert into user (login, email, name) values ('user3', 'user3@email', 'username');
insert into user (login, email, name) values ('user4', 'user4@email', 'username');
insert into user (login, email, name, deleted) values ('user5', 'user4@email', 'username', true);