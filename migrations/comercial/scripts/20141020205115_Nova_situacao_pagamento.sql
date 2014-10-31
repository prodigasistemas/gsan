-- // Nova situacao pagamento
-- Migration SQL that makes the change goes here.

insert into arrecadacao.pagamento_situacao values (15, 'PAGTO CLASS REC CRED', 'CLASSREC', 1, now());

-- //@UNDO
-- SQL to undo the change goes here.

delete from arrecadacao.pagamento_situacao where pgst_id = 15
