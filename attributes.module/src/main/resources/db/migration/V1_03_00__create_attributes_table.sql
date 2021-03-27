create table attributes
(
    id          uuid      not null
        constraint attributes_pkey
            primary key,
    created_at  timestamp not null,
    updated_at  timestamp not null,
    name        varchar(255),
    order_field integer,
    value       varchar(255),
    sample_id   uuid      not null
        constraint fk62qpdn09ooplxxnjmov2hm6br
            references samples
);

alter table attributes
    owner to postgres;