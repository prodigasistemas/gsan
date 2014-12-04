-- // Add data inclusao em situacao especial faturamento
-- Migration SQL that makes the change goes here.

alter table faturamento.fatur_situacao_hist add column ftsh_tminclusao timestamp;


-- //@UNDO
-- SQL to undo the change goes here.

alter table faturamento.fatur_situacao_hist drop column ftsh_tminclusao;
