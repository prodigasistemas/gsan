-- // popula tabela de tipo de operacao
-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.operacao_tipo VALUES (3, 'ATUALIZAR', 1, '2006-03-23 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (4, 'INSERIR', 1, '2006-03-23 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (2, 'REMOVER', 1, '2006-03-23 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (1, 'PESQUISAR', 2, '2006-03-14 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (5, 'FILTRAR', 1, '2006-03-23 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (6, 'CONSULTAR', 1, '2006-03-23 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (7, 'ADICIONAR', 1, '2006-03-23 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (8, 'CANCELAR', 1, '2006-07-28 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (9, 'MOVIMENTAR', 1, '2006-08-29 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (10, 'ALTERAR', 1, '2006-09-13 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (11, 'IMPORTAR', 1, '2006-09-14 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (12, 'EFETUAR', 1, '2006-09-14 00:00:00');
INSERT INTO seguranca.operacao_tipo VALUES (13, 'RELATORIO', 1, '2006-09-22 16:04:59.159626');
INSERT INTO seguranca.operacao_tipo VALUES (14, 'GERAR', 1, '2007-05-28 10:56:57.655473');


-- //@UNDO
-- SQL to undo the change goes here.


delete from seguranca.operacao_tipo;