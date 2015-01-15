-- // associar grupo ao usuario
-- Migration SQL that makes the change goes here.

insert into seguranca.usuario_grupo(grup_id, usur_id) values (1,1);

-- //@UNDO
-- SQL to undo the change goes here.
delete from seguranca.usuario_grupo;

