create database testeServlet;

create table if not exists empresas(
    id bigserial primary key not null,
    nome varchar(200) not null,
    endereco varchar(200) null
);

insert into empresas values (1, 'Empresa 1', 'Endereco 1');
insert into empresas values (2, 'Empresa 2', 'Endereco 2');
insert into empresas values (3, 'Empresa 3', 'Endereco 3');
insert into empresas values (4, 'Empresa 4', 'Endereco 4');
