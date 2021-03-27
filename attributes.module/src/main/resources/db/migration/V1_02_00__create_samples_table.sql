create table samples
(
    id uuid not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    extractor_type varchar(100),
    label varchar(255),
    original_file_name varchar(255),
    constraint samples_pkey
        primary key (id)
);

alter table samples owner to postgres;

