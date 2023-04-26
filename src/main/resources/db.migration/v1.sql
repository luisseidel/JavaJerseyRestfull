create database testeServlet;

create table if not exists ebooks(
    id bigserial primary key not null,
    nome varchar(200) not null,
    editora varchar(200) null
);

insert into ebooks values (1, 'Teste 1', 'Teste 1');
insert into ebooks values (2, 'Teste 2', 'Teste 2');
insert into ebooks values (3, 'Teste 3', 'Teste 3');
insert into ebooks values (4, 'Teste 4', 'Teste 4');
