-- // insert usuario em documento de cobranca
-- Migration SQL that makes the change goes here.
ALTER TABLE cobranca.cobranca_documento ADD COLUMN usur_id INTEGER 
CONSTRAINT fk23_cobranca_documento REFERENCES seguranca.usuario (usur_id)
MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE cobranca.cobranca_documento DROP COLUMN usur_id;

