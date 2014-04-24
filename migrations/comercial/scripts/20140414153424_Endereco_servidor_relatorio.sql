-- // Endereco servidor relatorio
-- Migration SQL that makes the change goes here.
SELECT setval('operacao.sequence_parametro', 9);

INSERT INTO OPERACAO.PARAMETRO
VALUES
(nextval('operacao.sequence_parametro'), 'URL_RELATORIO', now(), 0, 'http://192.168.0.16:3000/produtos_quimicos');


-- //@UNDO
-- SQL to undo the change goes here.

DELETE FROM OPERACAO.PARAMETRO
WHERE parm_nmparametro = 'URL_RELATORIO';

