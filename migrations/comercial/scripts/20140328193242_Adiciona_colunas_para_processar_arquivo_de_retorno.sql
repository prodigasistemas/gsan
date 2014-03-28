-- // Adiciona colunas para processar arquivo de retorno
-- Migration SQL that makes the change goes here.
ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN loca_id integer;
ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN imre_nnquadra integer;
ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN imre_cdsetorcomercial integer;
ALTER TABLE atualizacaocadastral.cliente_retorno ADD COLUMN clir_tipooperacao integer;


-- //@UNDO
-- SQL to undo the change goes here.


ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN loca_id;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN imre_nnquadra;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN imre_cdsetorcomercial;
ALTER TABLE atualizacaocadastral.cliente_retorno DROP COLUMN clir_tipooperacao;