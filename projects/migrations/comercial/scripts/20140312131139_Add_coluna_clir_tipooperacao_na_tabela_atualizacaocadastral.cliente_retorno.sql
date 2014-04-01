-- // Add coluna clir_tipooperacao na tabela atualizacaocadastral.cliente_retorno
-- Migration SQL that makes the change goes here.

alter table atualizacaocadastral.cliente_retorno
	add column clir_tipooperacao integer;


-- //@UNDO
-- SQL to undo the change goes here.

alter table atualizacaocadastral.cliente_retorno
	drop column clir_tipooperacao;
