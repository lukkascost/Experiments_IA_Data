create table rounds
(
    id uuid not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    experiment_id uuid not null,
    dataset_id uuid not null,
    sum_confusion_matrix varchar(65535),
    normalization_limits varchar(65535),

    constraint rounds_pkey
        primary key (id),
    constraint qiUWEKRGHFIQWKHFBALMDNAJKSDN
        foreign key (dataset_id) references datasets,
    constraint JHGHEJGSFKHGWSFUKGJWGHFEYWGE
        foreign key (experiment_id) references experiments
);

alter table rounds owner to postgres;