-- // Adicionando flag na conta para controle de deleção no faturamento
-- Migration SQL that makes the change goes here.

alter table faturamento.conta add column cnta_icfaturamentoconcluido smallint default 0;

-- //@UNDO
-- SQL to undo the change goes here.

alter table faturamento.conta drop column cnta_icfaturamentoconcluido;


