create table experiments
(
    id          uuid      not null,
    created_at  timestamp not null,
    updated_at  timestamp not null,
    description varchar(65535),
    name        varchar(255),
    constraint experiments_pkey
        primary key (id),
    constraint experiments_pk
        unique (name)
);

alter table experiments
    owner to postgres;

