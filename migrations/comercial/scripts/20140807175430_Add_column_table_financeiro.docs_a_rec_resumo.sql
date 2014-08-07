-- // Add column table financeiro.docs_a_rec_resumo
-- Migration SQL that makes the change goes here.

ALTER TABLE financeiro.docs_a_rec_resumo add column drrs_vldocumentossemparcelaatual numeric(13,2)

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE financeiro.docs_a_rec_resumo drop column drrs_vldocumentossemparcelaatual