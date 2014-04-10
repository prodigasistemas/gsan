-- // insere coluna data criacao macromedidor
-- Migration SQL that makes the change goes here.
alter table operacao.macro_medidor
add column mmed_datacadastro date NOT NULL default '2009-10-01';


-- //@UNDO
-- SQL to undo the change goes here.
alter table operacao.macro_medidor
drop column mmed_datacadastro;


