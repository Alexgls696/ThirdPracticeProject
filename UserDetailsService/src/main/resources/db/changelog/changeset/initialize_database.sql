--liquibase formatted sql
--changeset Alexandr:create users_database_tables
--comment first migration

create table users (
    id integer primary key generated always as identity,
    name varchar(64) not null default 'Неизвестно',
    surname varchar(128) not null default 'Неизвестно',
    patronymic varchar(128),
    gender varchar(8),
    email varchar(128),
    passport varchar(32) not null default 'Неизвестно',
    INN varchar(16) not null default 'Неизвестно',
    drivers_license varchar(16)
)