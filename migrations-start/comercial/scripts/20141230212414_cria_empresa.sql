-- // cria empresa
-- Migration SQL that makes the change goes here.
insert into cadastro.empresa
(empr_id
, empr_nmempresa
, empr_icempresaprincipal)
values(
1
, 'NOME EMPRESA'
, 1
);


-- //@UNDO
-- SQL to undo the change goes here.

delete from cadastro.empresa;

