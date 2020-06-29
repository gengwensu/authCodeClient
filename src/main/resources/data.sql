CREATE SCHEMA if not exists testdb;
CREATE SCHEMA if not exists clientdb;
CREATE USER if not exists authclient PASSWORD '123' admin;
GRANT ALL TO authclient;

use clientdb;
create table client_user(
id bigint auto_increment primary key,
username varchar(100),
password varchar(50),
access_token varchar(100) NULL,
access_token_validity datetime NULL,
refresh_token varchar(100) NULL
);
insert into client_user (username, password) values ('aeloy', '{noop}abc');