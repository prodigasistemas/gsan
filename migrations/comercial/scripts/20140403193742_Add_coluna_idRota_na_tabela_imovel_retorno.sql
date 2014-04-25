-- // Add coluna idRota na tabela imovel_retorno
-- Migration SQL that makes the change goes here.
ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN rota_id integer;


-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN rota_id;
