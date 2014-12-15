-- // Separando situacoes pagamento recuperacao credito
-- Migration SQL that makes the change goes here.

update arrecadacao.pagamento_situacao 
set pgst_dspagamentosituacao = 'PG CLASS REC CRED CA', pgst_dsabreviado = 'CLASSREC'
where pgst_id = 15;

insert into arrecadacao.pagamento_situacao values (16, 'PG CLASS REC CRED DU', 'RECCREDU', 1, now());

-- //@UNDO
-- SQL to undo the change goes here.

delete from arrecadacao.pagamento_situacao where pgst_id = 16;

