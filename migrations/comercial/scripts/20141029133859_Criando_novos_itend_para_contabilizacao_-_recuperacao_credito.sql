-- // Criando novos itend para contabilizacao - recuperacao credito
-- Migration SQL that makes the change goes here.


insert into arrecadacao.recebimento_tipo values (17, 'RECEBIMENTOS CLASS REC CRED', 1, now(), 110);
insert into arrecadacao.recebimento_tipo values (18, 'RECEBIMENTOS CLASS REC CRED MESES ANT', 1, now(), 960);

insert into financeiro.lancamento_tipo values (105, 105, 'RECUPERACAO CREDITO', 'REC CRED', 2, null, now(), 2, 2);

insert into financeiro.lancamento_item values (85, 'RECUPERACAO CREDITO', 'REC CRED', 2, now());

-- //@UNDO
-- SQL to undo the change goes here.


delete from arrecadacao.recebimento_tipo where rctp_id in (17, 18);
delete from financeiro.lancamento_tipo where lctp_id in (105);
delete from financeiro.lancamento_item where lcit_id in (85);


