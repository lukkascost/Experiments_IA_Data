create table datasets
(
    id uuid not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    description varchar(255),
    "name" varchar(255),
    constraint datasets_pkey
        primary key (id),
    constraint datasets_pk
        unique (name)
);

alter table datasets owner to postgres;