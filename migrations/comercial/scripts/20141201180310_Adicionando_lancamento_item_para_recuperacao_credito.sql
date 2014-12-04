-- // Adicionando lancamento item para recuperacao credito
-- Migration SQL that makes the change goes here.

update financeiro.lancamento_item set lcit_dsitemlancamento = 'RECUPERACAO CREDITO CANCELAMENTO', lcit_dsabreviado = 'RECCREDCAN' where lcit_id = 85;

insert into financeiro.lancamento_item values (86, 'RECUPERACAO CREDITO DUPLUCIDADE', 'RECCREDDUP', 2, now());

-- //@UNDO
-- SQL to undo the change goes here.

update financeiro.lancamento_item set lcit_dsitemlancamento = 'RECUPERACAO CREDITO', lcit_dsabreviado = 'REC CRED' where lcit_id = 85;

delete from financeiro.lancamento_item where lcit_id = 86;



