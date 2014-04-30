-- // Add coluna cadastro ocorrencia na tabela imovel controle
-- Migration SQL that makes the change goes here.

ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad ADD COLUMN cocr_id integer;


-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad DROP COLUMN cocr_id integer;

