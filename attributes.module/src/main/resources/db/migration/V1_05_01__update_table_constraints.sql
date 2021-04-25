alter table rounds
    add name varchar(1000);

alter table rounds
    add constraint rounds_pk_2
        unique (experiment_id, dataset_id);
