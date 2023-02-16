create table person (
id integer auto_increment,
username varchar(20) not null,
year_of_birth integer not null,
password varchar(20) not null,
primary key (id)
);

insert into person (username, password, year_of_birth) values
('robert', 'dumbPassword', 1998), ('rustam', 'kek', 1997);

select * from person;
