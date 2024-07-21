drop table if exists account CASCADE;
drop table if exists balance CASCADE;
drop table if exists category CASCADE;
drop table if exists merchant CASCADE;
drop table if exists classification CASCADE;
drop table if exists transaction CASCADE;

drop sequence if exists acc_seq;
drop sequence if exists bal_seq;
drop sequence if exists cat_seq;
drop sequence if exists cla_seq;
drop sequence if exists mer_seq;
drop sequence if exists tra_seq;

create sequence acc_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;
create sequence bal_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;
create sequence cat_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;
create sequence cla_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;
create sequence mer_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;
create sequence tra_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;

create table if not exists account (
    id bigserial not null,
    name text not null,
    primary key (id)
);

create table if not exists category (
    id bigserial not null,
    name text not null,
    primary key (id)
);

create table if not exists balance (
    id bigserial not null,
    version serial not null,
    amount integer not null check (amount >= 0),
    account_id bigserial not null,
    category_id bigserial not null,
    primary key (id),
    foreign key (account_id) references account(id),
    foreign key (category_id) references category(id)
);

create table if not exists classification (
    id bigserial not null,
    code text not null,
    category_id bigserial not null,
    primary key (id),
    foreign key (category_id) references category(id)
);

create index idx_classification_code on classification (code);

create table if not exists merchant (
    id bigserial not null,
    name text not null,
    category_id bigserial not null,
    primary key (id),
    foreign key (category_id) references category(id)
);

create index idx_merchant_name on merchant(name);

create table if not exists transaction (
    id bigserial not null,
    account_id bigserial not null,
    total_amount integer not null,
    mcc text not null,
    merchant_name text not null,
    primary key (id)
);
