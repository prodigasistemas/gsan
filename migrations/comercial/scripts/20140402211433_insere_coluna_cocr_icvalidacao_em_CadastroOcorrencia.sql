-- // insere coluna cocr_icvalidacao em CadastroOcorrencia
-- Migration SQL that makes the change goes here.
ALTER TABLE cadastro.cadastro_ocorrencia ADD COLUMN cocr_icvalidacao smallint default 2;

UPDATE cadastro.cadastro_ocorrencia
SET cocr_icvalidacao = 1
WHERE cocr_id IN (1,32,33,34,35);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE cadastro.cadastro_ocorrencia DROP COLUMN cocr_icvalidacao;

