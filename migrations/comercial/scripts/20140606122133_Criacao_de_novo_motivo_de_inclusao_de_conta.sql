-- // Criacao de novo motivo de inclusao de conta
-- Migration SQL that makes the change goes here.

insert into faturamento.conta_motivo_inclusao values (46, 'RECUPERACAO DE CREDITO', 2, now());

-- //@UNDO
-- SQL to undo the change goes here.

delete from faturamento.conta_motivo_inclusao where cmic_dsmotivoinclusaoconta like 'RECUPERACAO DE CREDITO';

