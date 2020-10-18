create table m_users2
(
    id         bigserial                                                  not null
        constraint m_users2_pk
            primary key,
    username   varchar(200)  default 'DEFAULT_NAME'::character varying    not null,
    surname    varchar(1000) default 'DEFAULT_SURNAME'::character varying not null,
    birth_date date,
    country    varchar(50)   default 'NOT_SELECTED'::character varying    not null,
    login      varchar(100)                                               not null,
    password   varchar(1000) default '123'::character varying             not null,
    created    timestamp(6),
    changed    timestamp(6),
    is_blocked boolean       default false                                not null,
    weight     double precision,
    gender     varchar(50)   default 'NOT_SELECTED'::character varying    not null
);

alter table m_users2
    owner to test;

create unique index m_users2_login_uindex
    on m_users2 (login);

create index m_users2_username_index
    on m_users2 (username);

create index m_users2_surname_index
    on m_users2 (surname);

create index m_users2_country_index
    on m_users2 (country);

create table m_roles
(
    id        bigserial   not null
        constraint m_roles_pk
            primary key,
    role_name varchar(50) not null,
    user_id   bigint      not null
        constraint m_roles_m_users2_id_fk
            references m_users2
            on update cascade on delete cascade
);

alter table m_roles
    owner to test;

create index m_roles_role_name_index
    on m_roles (role_name);

create index m_roles_user_id_index
    on m_roles (user_id desc);

create unique index m_roles_role_name_user_id_uindex
    on m_roles (role_name, user_id);

create table m_auto_dealer
(
    id                 bigserial                                             not null
        constraint m_auto_dealer_pk
            primary key,
    name               varchar(100)                                          not null,
    country            varchar(50) default 'NOT_SELECTED'::character varying not null,
    address            varchar(1000)                                         not null,
    capacity           integer     default 3                                 not null,
    created            timestamp(6)                                          not null,
    changed            timestamp(6)                                          not null,
    year_of_foundation date                                                  not null
);

alter table m_auto_dealer
    owner to test;

create index m_auto_dealer_address_index
    on m_auto_dealer (address);

create index m_auto_dealer_name_index
    on m_auto_dealer (name);

create index m_auto_dealer_country_index
    on m_auto_dealer (country);

create table m_cars2
(
    id                        bigserial                                             not null
        constraint m_cars2_pk
            primary key,
    model                     varchar(100)                                          not null,
    year_of_release           integer                                               not null,
    country                   varchar(50) default 'NOT_SELECTED'::character varying not null,
    guarantee_expiration_date timestamp(6)                                          not null,
    price                     double precision                                      not null,
    dealer_id                 bigint                                                not null
        constraint m_cars2_m_auto_dealer_id_fk
            references m_auto_dealer
            on update cascade on delete cascade,
    user_id                   bigint
        constraint m_cars2_m_users2_id_fk
            references m_users2
            on update cascade on delete set null
);

alter table m_cars2
    owner to test;

create index m_cars2_model_index
    on m_cars2 (model);

create index m_cars2_year_of_release_index
    on m_cars2 (year_of_release);

create index m_cars2_country_index
    on m_cars2 (country);

create index m_cars2_price_index
    on m_cars2 (price);

create table m_body
(
    id      bigserial                                       not null
        constraint m_body_pk
            primary key,
    color   varchar(100) default 'BLACK'::character varying not null,
    vin     varchar(200)                                    not null,
    type    varchar(100)                                    not null,
    created timestamp(6)                                    not null,
    changed timestamp(6)                                    not null,
    car_id  bigint                                          not null
        constraint m_body_m_cars2_id_fk
            references m_cars2
            on update cascade on delete cascade
);

alter table m_body
    owner to test;

create unique index m_body_vin_uindex
    on m_body (vin);

create table m_transmission
(
    id      bigserial        not null
        constraint m_transmission_pk
            primary key,
    type    varchar(100)     not null,
    gears   integer          not null,
    weight  double precision not null,
    created timestamp(6)     not null,
    changed timestamp(6)     not null,
    car_id  bigint           not null
        constraint m_transmission_m_cars2_id_fk
            references m_cars2
            on update cascade on delete cascade
);

alter table m_transmission
    owner to test;

create index m_transmission_changed_index
    on m_transmission (changed desc);

create index m_transmission_created_index
    on m_transmission (created desc);

create index m_transmission_type_index
    on m_transmission (type);

create index m_transmission_weight_index
    on m_transmission (weight desc);

create table m_engine
(
    id            bigserial                                        not null
        constraint m_engine_pk
            primary key,
    volume        double precision                                 not null,
    cylinder      integer                                          not null,
    batch         varchar(100)                                     not null,
    fuel_type     varchar(100) default 'PETROL'::character varying not null,
    car_id        bigint                                           not null
        constraint m_engine_m_cars2_id_fk
            references m_cars2
            on update cascade on delete cascade,
    is_deprecated boolean      default false                       not null
);

alter table m_engine
    owner to test;

create index m_engine_type_index
    on m_engine (fuel_type);

create index m_engine_volume_index
    on m_engine (volume desc);

create table m_good
(
    id        bigserial    not null
        constraint m_good_pk
            primary key,
    good_name varchar(100) not null
);

alter table m_good
    owner to test;

create table l_user_goods
(
    id      bigserial    not null
        constraint l_user_goods_pk
            primary key,
    user_id integer      not null
        constraint l_user_goods_m_users2_id_fk
            references m_users2
            on update cascade on delete cascade,
    good_id integer      not null
        constraint l_user_goods_m_good_id_fk
            references m_good,
    count   integer      not null,
    created timestamp(6) not null,
    changed timestamp(6) not null
);

alter table l_user_goods
    owner to test;

create index l_user_goods_count_index
    on l_user_goods (count desc);

create index l_user_goods_created_index
    on l_user_goods (created desc);



/*Tasks*/

/*Task 1. Display all the unique names of user roles.*/
select distinct (role_name)
from m_roles;

/*Task 2. Count the number of machines for each user.
Output in the format User full name (username + space + user surname) |
The number of machines the user has.*/
select u.username, u.surname, count(c.user_id)
from m_users2 u
join m_cars2 c on u.id = c.user_id
group by user_id, username, surname
order by user_id;

/*Task 3. Calculate for each dealer the number of cars older than 2018 production with
a red body.*/
select u.name, count(c.dealer_id)
from m_auto_dealer u
join m_cars2 c on u.id = c.dealer_id
join m_body mb on c.id = mb.car_id
where year_of_release < 2018 and
      color = 'RED'
group by name
order by name;

/*Task 4. Find users not from Belarus and Russia who have a car built in 2010-2015 from
Germany and purchased at a dealer not in Germany with an engine capacity of more than 3
liters.*/
select u.id, username, u.surname
from m_users2 u
join m_cars2 c on u.id = c.user_id
join m_auto_dealer mad on c.dealer_id = mad.id
join m_engine me on c.id = me.car_id
where u.country not in ('BELARUS', 'RUSSIA') and
      year_of_release between 2009 and 2016 and
      c.country = 'GERMANY' and
      mad.country != 'GERMANY' and
      volume > 3;

/*Task 5. Determine the logins of users with more than 3 machines.*/
select u.login, count(m.user_id)
from m_users2 u
join m_cars2 m on u.id = m.user_id
group by login
having count(m.user_id) > 3
order by login;

/*Task 6. Display unique dealers with the calculated amount of machine costs associated
with them.*/
select distinct (u.name), sum(m.price)
from m_auto_dealer u
join m_cars2 m on u.id = m.dealer_id
group by name
order by name;

/*Task 7. Count the number of unique users who own at least one machine that costs more
than the average cost of all machines.*/
select count(distinct (m.user_id))
from m_users2 u
join m_cars2 m on u.id = m.user_id
where price > (select avg(price) from m_cars2);
