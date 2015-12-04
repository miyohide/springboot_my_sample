create table user (
    id          varchar(32)       not null primary key,
    password    varchar(128)      not null,
    enabled     tinyint           not null default 1
);
    