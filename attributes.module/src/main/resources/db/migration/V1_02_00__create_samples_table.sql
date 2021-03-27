create table samples
(
    id uuid not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    extractor_type varchar(100),
    label varchar(255),
    original_file_name varchar(255),
    dataset_id uuid not null,
    constraint samples_pkey
        primary key (id),
    constraint fknlgccqh3r9gxiiu18nwtdluor
        foreign key (dataset_id) references datasets
);

alter table samples owner to postgres;

