-- // update para desativar gerar dados para leitura antigo
-- Migration SQL that makes the change goes here.

UPDATE faturamento.faturamento_atividade SET ftat_icuso = 2, ftat_tmultimaalteracao = now() WHERE ftat_id = 1;

-- //@UNDO
-- SQL to undo the change goes here.

UPDATE faturamento.faturamento_atividade SET ftat_icuso = 1, ftat_tmultimaalteracao = now() WHERE ftat_id = 1;
