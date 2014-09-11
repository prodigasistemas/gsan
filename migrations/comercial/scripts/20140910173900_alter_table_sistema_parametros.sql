-- // alter table cadastro.sistema_parametros
-- Migration SQL that makes the change goes here.
ALTER TABLE cadastro.sistema_parametros ADD COLUMN parm_dddfone CHARACTER VARYING(2);

ALTER TABLE cadastro.sistema_parametros ADD COLUMN clie_idresponsavelnegativacao INTEGER
	CONSTRAINT fk23_sistema_parametros REFERENCES cadastro.cliente (clie_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE cadastro.sistema_parametros DROP COLUMN parm_dddfone;

ALTER TABLE cadastro.sistema_parametros DROP CONSTRAINT fk23_sistema_parametros;
ALTER TABLE cadastro.sistema_parametros DROP COLUMN clie_idresponsavelnegativacao;