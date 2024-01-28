drop table if exists categories cascade;

create table categories
(
    id                      serial8,
    category_name           varchar(40) not null,
    primary key (id),
    unique (category_name)
);

drop table if exists products cascade;

create table products
(
    id                      serial8,
    category_id             int4 not null,
    product_name            varchar(40) not null,
    price                   int4 not null,
    primary key (id),
    foreign key (category_id) references categories (id),
    unique (product_name)
);

drop table if exists specifications cascade;

create table specifications
(
    id                      serial8,
    category_id             int4 not null,
    specification_name      varchar(40) not null,
    primary key (id),
    foreign key (category_id) references categories (id),
    unique (category_id, specification_name)
);

drop table if exists values;

create table values
(
    id                      serial8,
    specification_id        int4 not null,
    product_id              int4 not null,
    value_name              varchar(40) not null,
    primary key (id),
    foreign key (specification_id) references specifications (id),
    foreign key (product_id) references products (id)
);


insert into categories (category_name)
values ('Processors'),
       ('Monitors');

insert into products (category_id, product_name, price)
values (1, 'Intel Core I9 9900', 300000),
       (1, 'AMD Ryzen R7 7700', 250000),
       (2, 'Samsung SU556270', 200000),
       (2, 'AOC Z215S659', 150000);

insert into specifications (category_id, specification_name)
values (1, 'Manufacturer'),
       (2, 'Manufacturer'),
       (1, 'Number of cores'),
       (1, 'Socket'),
       (2, 'Diagonal'),
       (2, 'Matrix'),
       (2, 'Resolution'),
       (2, 'Stand material');

insert into values (specification_id, product_id, value_name)
values (1, 1, 'Intel'),
       (1, 2, 'AMD'),
       (2, 3, 'Samsung'),
       (2, 4, 'AOC'),
       (3, 1, '8'),
       (3, 2, '12'),
       (4, 1, '1250'),
       (4, 2, 'AM4'),
       (5, 3, '27'),
       (5, 4, '21.5'),
       (6, 3, 'TN'),
       (6, 4, 'AH-IPS'),
       (7, 3, '2560*1440'),
       (7, 4, '1920*1080');


select p.id,
       c.category_name,
       p.product_name,
       s.specification_name,
       v.value_name
from products p
         join values v on p.id = v.product_id
         join specifications s on s.id = v.specification_id
         join categories c on c.id = p.category_id
order by p.id, specification_id;


select c.category_name,
       s.specification_name
from categories c
         join specifications s on c.id = s.category_id
where category_name = 'Monitors';


select s.specification_name
from products p
         join specifications s on p.category_id = s.category_id
where p.id = 5;


select s.specification_name,
       v.value_name
from products p
         join specifications s on p.category_id = s.category_id
         join values v on p.id = v.product_id and s.id = v.specification_id
where p.id = 10;


select s.specification_name,
       v.value_name
from products p
         join specifications s on p.category_id = s.category_id
         left join values v on p.id = v.product_id and s.id = v.specification_id
where p.id = 3;

select *
from specifications