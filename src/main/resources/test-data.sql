DELETE  FROM  cliente;
DELETE  FROM  endereco;


insert into endereco (id, cep, logradouro, complemento, bairro,  localidade, uf,  ibge, gia , ddd, siafi)
values (1, '01310-100', 'Avenida Paulista', 'de 612 a 1510 - lado par', 'Bela Vista', 'São Paulo', 'SP', '3550308', '1004', '11', '7107');

insert into cliente (id, nome, cpf, endereco_id ) values ('99', 'Conceicao Silva', '99999999999', 1);


