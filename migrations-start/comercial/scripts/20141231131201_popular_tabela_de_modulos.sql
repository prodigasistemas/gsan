-- // popular tabela de modulos
-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.modulo VALUES (6, 'ATENDIMENTO AO PUBLICO', 'ATPUB', '2006-07-13 15:19:00', 1);
INSERT INTO seguranca.modulo VALUES (1, 'CADASTRO', 'CADASTRO', '2006-02-15 00:00:00', 2);
INSERT INTO seguranca.modulo VALUES (5, 'MICROMEDICAO', 'MICRO', '2006-02-15 00:00:00', 3);
INSERT INTO seguranca.modulo VALUES (7, 'FATURAMENTO', 'FATUR', '2006-07-18 08:48:00', 4);
INSERT INTO seguranca.modulo VALUES (3, 'COBRANCA', 'COBRANCA', '2006-02-15 00:00:00', 5);
INSERT INTO seguranca.modulo VALUES (2, 'ARRECADACAO', 'ARREC', '2006-02-15 00:00:00', 6);
INSERT INTO seguranca.modulo VALUES (9, 'RELATORIOS', 'RELATORIOS', '2006-09-29 17:46:06.421483', 7);
INSERT INTO seguranca.modulo VALUES (11, 'GERENCIAL', 'GEREN', '2007-05-02 09:58:37.23067', 8);
INSERT INTO seguranca.modulo VALUES (4, 'SEGURANCA', 'SEGUR', '2006-02-15 00:00:00', 9);
INSERT INTO seguranca.modulo VALUES (8, 'BATCH', 'BATCH', '2006-09-28 00:00:00', 10);
INSERT INTO seguranca.modulo VALUES (12, 'FINANCEIRO', 'FINAN', '2007-05-23 10:05:04.136165', 11);
INSERT INTO seguranca.modulo VALUES (10, 'OPERACIONAL', 'OPERAC', '2007-02-13 10:14:02.933979', 12);


-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.modulo;