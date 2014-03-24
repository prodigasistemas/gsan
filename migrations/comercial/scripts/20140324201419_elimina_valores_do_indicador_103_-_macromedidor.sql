-- // elimina valores do indicador 103 - macromedidor
-- Migration SQL that makes the change goes here.

delete from operacao.indicador_mensal where indc_id IN (103);


-- //@UNDO
-- SQL to undo the change goes here.


