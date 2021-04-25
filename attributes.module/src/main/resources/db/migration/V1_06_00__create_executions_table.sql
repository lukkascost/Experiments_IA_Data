create table executions
(
    id uuid not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    model_id uuid null,
    round_id uuid not null,
    split_dataset varchar,
    confusion_matrix varchar,
    constraint executions_pkey
        primary key (id),
    constraint ahajgdalkyhdqiuowjyqheruo
        foreign key (round_id) references rounds

);

alter table executions owner to postgres;