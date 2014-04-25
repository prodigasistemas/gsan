-- // Add coluna imre_nnquadra na tabela atualizacaocadastral.imovel_retorno
-- Migration SQL that makes the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno
ADD COLUMN imre_nnquadra integer;


-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno
DROP COLUMN imre_nnquadra;

