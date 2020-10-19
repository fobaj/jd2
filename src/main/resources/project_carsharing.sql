create table c_cars
(
    "Id"                 bigserial        not null
        constraint c_cars_pkey
            primary key,
    "Brand"              varchar(50)      not null,
    "Model"              varchar(50)      not null,
    "Year_of_issue"      bigint           not null,
    "Color"              varchar(20)      not null,
    "VIN"                varchar(20)      not null,
    "Registration_plate" varchar(10)      not null,
    "Number_of_seats"    bigint           not null,
    "Rate"               double precision not null
);

alter table c_cars
    owner to test;

create index c_cars_brand_index
    on c_cars ("Brand");

create index c_cars_color_index
    on c_cars ("Color");

create index c_cars_model_index
    on c_cars ("Model");

create index c_cars_number_of_seats_index
    on c_cars ("Number_of_seats");

create index c_cars_rate_index
    on c_cars ("Rate");

create index c_cars_year_of_issue_index
    on c_cars ("Year_of_issue");

create unique index c_cars_registration_plate_uindex
    on c_cars ("Registration_plate");

create unique index c_cars_vin_uindex
    on c_cars ("VIN");

create table c_clients
(
    "Id"        bigserial    not null
        constraint c_clients_pkey
            primary key,
    "Name"      varchar(50)  not null,
    "Surname"   varchar(50)  not null,
    "Gender"    varchar(10)  not null,
    "Passport"  varchar(20)  not null,
    "Address"   varchar(100) not null,
    "Telephone" varchar(15)  not null,
    "E-mail"    varchar(50)  not null,
    "Login"     varchar(20)  not null,
    "Password"  varchar(30)  not null
);

alter table c_clients
    owner to test;

create index c_clients_gender_index
    on c_clients ("Gender");

create index c_clients_name_index
    on c_clients ("Name");

create index c_clients_surname_index
    on c_clients ("Surname");

create unique index c_clients_address_uindex
    on c_clients ("Address");

create unique index "c_clients_e-mail_uindex"
    on c_clients ("E-mail");

create unique index c_clients_login_uindex
    on c_clients ("Login");

create unique index c_clients_passport_uindex
    on c_clients ("Passport");

create unique index c_clients_telephone_uindex
    on c_clients ("Telephone");

create table c_request
(
    "Id"                  bigserial    not null
        constraint c_request_pk
            primary key,
    "Id_car"              bigint       not null
        constraint c_request_c_cars_id_fk
            references c_cars
            on update cascade on delete cascade,
    "Id_client"           bigint       not null
        constraint c_request_c_clients_id_fk
            references c_clients
            on update cascade on delete cascade,
    "Start_date_and_time" timestamp(6) not null,
    "End_date_and_time"   timestamp(6) not null
);

alter table c_request
    owner to test;

create index c_request_end_date_and_time_index
    on c_request ("End_date_and_time");

create index c_request_start_date_and_time_index
    on c_request ("Start_date_and_time");

create index c_request_id_car_index
    on c_request ("Id_car");

create index c_request_id_client_index
    on c_request ("Id_client");

create table c_car_rent
(
    "Id"             bigserial        not null
        constraint c_car_rent_pk
            primary key,
    "Id_car_request" bigint           not null
        constraint c_car_rent_c_request_id_fk
            references c_request
            on update cascade on delete cascade,
    "Cost"           double precision not null
);

alter table c_car_rent
    owner to test;

create unique index c_car_rent_id_car_request_uindex
    on c_car_rent ("Id_car_request");

create index c_car_rent_cost_index
    on c_car_rent ("Cost");

create table c_car_transfer
(
    "Id"                     bigserial        not null
        constraint c_car_transfer_pk
            primary key,
    "Id_car_rent"            bigint           not null
        constraint c_car_transfer_c_car_rent_id_fk
            references c_car_rent
            on update cascade on delete cascade,
    "Start_place"            varchar(50)      not null,
    "End_place"              varchar(50)      not null,
    "Start_odometer_reading" double precision not null,
    "End_odometer_reading"   double precision not null,
    "Start_amount_of_fuel"   double precision not null,
    "End_amount_of_fuel"     double precision not null,
    "Start_notes"            varchar(200),
    "End_notes"              varchar(200)
);

alter table c_car_transfer
    owner to test;

create unique index c_car_transfer_id_car_rent_uindex
    on c_car_transfer ("Id_car_rent");

create index c_car_transfer_end_amount_of_fuel_index
    on c_car_transfer ("End_amount_of_fuel");

create index c_car_transfer_end_odometer_reading_index
    on c_car_transfer ("End_odometer_reading");

create index c_car_transfer_end_place_index
    on c_car_transfer ("End_place");

create index c_car_transfer_start_amount_of_fuel_index
    on c_car_transfer ("Start_amount_of_fuel");

create index c_car_transfer_start_odometer_reading_index
    on c_car_transfer ("Start_odometer_reading");

create index c_car_transfer_start_place_index
    on c_car_transfer ("Start_place");

create table c_car_repair
(
    "Id"              bigserial not null
        constraint c_car_repair_pk
            primary key,
    "Id_car_transfer" bigint    not null
        constraint c_car_repair_c_car_transfer_id_fk
            references c_car_transfer,
    "Damage"          varchar(100),
    "Cost"            double precision
);

alter table c_car_repair
    owner to test;

create unique index c_car_repair_id_car_transfer_uindex
    on c_car_repair ("Id_car_transfer");

create index c_car_repair_cost_index
    on c_car_repair ("Cost");