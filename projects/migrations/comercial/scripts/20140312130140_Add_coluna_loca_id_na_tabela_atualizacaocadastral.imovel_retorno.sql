-- // Add coluna loca_id na tabela atualizacaocadastral.imovel_retorno
-- Migration SQL that makes the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno
ADD COLUMN loca_id integer;


-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno
DROP COLUMN loca_id;

