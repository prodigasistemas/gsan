-- // altera tipo de colunas de rede instalada
-- Migration SQL that makes the change goes here.
alter table operacao.rede_instalada alter column rdin_cadastrada type decimal;
alter table operacao.rede_instalada alter column rdin_existente type decimal;


-- //@UNDO
-- SQL to undo the change goes here.
alter table operacao.rede_instalada alter column rdin_cadastrada type integer;
alter table operacao.rede_instalada alter column rdin_existente type integer;

