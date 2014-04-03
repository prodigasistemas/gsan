-- // Add coluna imre_cdsetorcomercial na tabela atualizacaocadastral.imovel_retorno
-- Migration SQL that makes the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno
ADD COLUMN imre_cdsetorcomercial integer;


-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE atualizacaocadastral.imovel_retorno
DROP COLUMN imre_cdsetorcomercial;

