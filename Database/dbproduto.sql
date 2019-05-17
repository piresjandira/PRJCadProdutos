create database dbprodutos;
use dbprodutos;
create table tbprodutos(
id int auto_increment primary key,
nome varchar(50) not null,
descricao varchar(50) not null,
fabricante varchar(50) not null,
quantidade int(4) not null,
preco decimal(10,2) not null
);
insert into tbprodutos(nome, descricao, fabricante, quantidade, preco)
values('Mouse', 'Mouse Gamer', 'Logika', '1', '80' );
select * from tbprodutos;
