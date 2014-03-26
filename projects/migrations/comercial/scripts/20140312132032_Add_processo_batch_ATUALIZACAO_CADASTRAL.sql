-- // Add processo batch ATUALIZACAO CADASTRAL
-- Migration SQL that makes the change goes here.

insert into batch.processo values (507, 'ATUALIZACAO CADASTRAL', 'ATUALCAD' , 1, now() , 4, null, 2)  ;
insert into seguranca.funcionalidade values (16006, 8, 'Atualizacao Cadastral', now(), 'Gsan/Batch', 'null', 2, 'AtualCad', 888, 2, 2, 65);
insert into batch.processo_funcionalidade values (nextval('batch.seq_processo_funcionalidade'), 507, now(), 1, 5, 16006, 1, null);


-- //@UNDO
-- SQL to undo the change goes here.

delete from batch.processo_funcionalidade where proc_id = 507 and fncd_id = 16006;
delete from seguranca.funcionalidade where fncd_id = 16006;
delete from batch.processo where proc_id = 507;

