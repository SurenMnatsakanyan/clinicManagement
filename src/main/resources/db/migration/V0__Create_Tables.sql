-- auto-generated definition
create table app_user
(
    id         integer not null
        primary key,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    role       varchar(255)
);

alter table app_user
    owner to smnatsakanyan;

-- auto-generated definition
create sequence app_user_seq
    increment by 50;

alter sequence app_user_seq owner to smnatsakanyan;
-- auto-generated definition
create table doctor
(
    id                  bigserial primary key,
    date                date    not null,
    description         text,
    first_name          varchar(255),
    last_name           varchar(255),
    years_of_experience integer not null
        constraint doctor_years_of_experience_check
            check ((years_of_experience >= 0) AND (years_of_experience <= 80))
);

alter table doctor
    owner to smnatsakanyan;

-- auto-generated definition
create table specification
(
    id   bigserial
        primary key,
    name varchar(255) not null
        constraint unique_name
            unique
);

alter table specification
    owner to smnatsakanyan;

-- auto-generated definition
create table doctor_specification
(
    doctor_id        bigint not null
        constraint referencing_doctor
            references doctor,
    specification_id bigint not null
        constraint referencing_specification
            references specification,
    primary key (doctor_id, specification_id)
);

alter table doctor_specification
    owner to smnatsakanyan;

