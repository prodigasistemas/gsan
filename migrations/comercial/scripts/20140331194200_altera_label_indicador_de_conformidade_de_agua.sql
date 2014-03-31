-- // altera label indicador de conformidade de agua
-- Migration SQL that makes the change goes here.
update operacao.indicador
set indc_nome = 'Índice de Conformidade da Qualidade ÁGUA'
where indc_id = 206;


-- //@UNDO
-- SQL to undo the change goes here.
update operacao.indicador
set indc_nome = 'Índice de Conformidade da Qualidade ÁGUA RMB'
where indc_id = 206;


