-- auto-generated definition
create table if not exists EMPLOYEES
(
    ID            INTEGER auto_increment
        primary key,
    FIRST_NAME    VARCHAR(255) not null,
    LAST_NAME     VARCHAR(255) not null,
    EMAIL_ADDRESS VARCHAR(255) not null
);

