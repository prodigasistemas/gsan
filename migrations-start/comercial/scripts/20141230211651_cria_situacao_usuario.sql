-- // cria situacao usuario
-- Migration SQL that makes the change goes here.

insert into seguranca.usuario_situacao
(usst_id
, usst_dsusuariosituacao
, usst_dsabreviado
, usst_icuso
, usst_icusosistema)
values
(1
, 'ATIVO'
, 'ATIVO'
, 1
, 1);


-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.usuario_situacao;