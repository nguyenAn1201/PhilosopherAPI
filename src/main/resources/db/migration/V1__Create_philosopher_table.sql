CREATE TABLE philosophers (
    id bigint primary key,
    name varchar(256) not null,
    title varchar(256),
    birth_month varchar(256),
    birth_day integer,
    birth_year integer,
    birth_country varchar(256),
    birth_city varchar(256),
    death_month varchar(256),
    death_day integer,
    death_year integer,
    death_country varchar(256),
    death_city varchar(256),
    description varchar(256) not null
)