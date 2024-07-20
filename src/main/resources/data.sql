insert into account (name) values ('CONTA 1');
insert into account (name) values ('CONTA 2');
insert into account (name) values ('CONTA 3');
insert into account (name) values ('CONTA 4');

insert into category (name) values ('FOOD');
insert into category (name) values ('MEAL');
insert into category (name) values ('CASH');

insert into classification (code, category_id) values ('5411', 1);
insert into classification (code, category_id) values ('5412', 1);
insert into classification (code, category_id) values ('5811', 2);
insert into classification (code, category_id) values ('5812', 2);

insert into merchant (name, category_id) values ('PADARIA DO ZE               SAO PAULO BR', 1);
insert into merchant (name, category_id) values ('UBER EATS                   SAO PAULO BR', 2);
insert into merchant (name, category_id) values ('UBER TRIP                   SAO PAULO BR', 3);


insert into balance (version, account_id, category_id, amount) values (1, 1, 1, 10000);
insert into balance (version, account_id, category_id, amount) values (1, 1, 2, 10000);
insert into balance (version, account_id, category_id, amount) values (1, 1, 3, 10000);
insert into balance (version, account_id, category_id, amount) values (1, 2, 1, 0);
insert into balance (version, account_id, category_id, amount) values (1, 2, 2, 10000);
insert into balance (version, account_id, category_id, amount) values (1, 2, 3, 10000);
insert into balance (version, account_id, category_id, amount) values (1, 3, 1, 0);
insert into balance (version, account_id, category_id, amount) values (1, 3, 2, 0);
insert into balance (version, account_id, category_id, amount) values (1, 3, 3, 10000);
insert into balance (version, account_id, category_id, amount) values (1, 4, 1, 0);
insert into balance (version, account_id, category_id, amount) values (1, 4, 2, 0);
insert into balance (version, account_id, category_id, amount) values (1, 4, 3, 0);
