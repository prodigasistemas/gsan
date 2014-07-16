-- // Adicionando novos lancamentos para recuperacao de credito
-- Migration SQL that makes the change goes here.

insert into financeiro.lancamento_tipo values (99, 99, 'DEVOLUCAO_VALORES_RECUPERACAO_CREDITO', 'DEVARECCRE', 1, null, now(), 2, 2);
insert into financeiro.lancamento_tipo values (100, 100, 'OUTROS_CREDITOS_CANCELADOS_POR_RECUPERACAO_CREDITO', 'OCRCANRECR', 1, null, now(), 2, 2);
insert into financeiro.lancamento_tipo values (101, 101, 'OUTROS_CREDITOS_CONCEDIDOS_POR_RECUPERACAO_CREDITO', 'OCRCONRECR', 1, null, now(), 2, 2);
insert into financeiro.lancamento_tipo values (102, 102, 'OUTR_CRED_A_REAL_POR_RECUP_CRED_DEB_PRESCRITO', 'OCRARERECR', 1, null, now(), 2, 2);
insert into financeiro.lancamento_tipo values (103, 103, 'OUTR_CRED_A_REAL_POR_RECUP_CRED_INCLUIDOS', 'OCRARERECR', 1, null, now(), 2, 2);
insert into financeiro.lancamento_tipo values (104, 104, 'OUTR_CRED_A_REAL_POR_RECUP_CRED_CANCELADOS', 'OCRARERECR', 1, null, now(), 2, 2);

insert into financeiro.lancamento_item values (83, 'RECUPERACAO CREDITO CONTA CANCELADA', 'RECCREDCAN', 2, now());
insert into financeiro.lancamento_item values (84, 'RECUPERACAO CREDITO CONTA PARCELADA', 'RECCREDPAR', 2, now());

-- //@UNDO
-- SQL to undo the change goes here.

DELETE FROM financeiro.lancamento_tipo
WHERE lctp_id in (99, 100, 101, 102, 103, 104); 

DELETE FROM financeiro.lancamento_item
WHERE lcit_id in (83);