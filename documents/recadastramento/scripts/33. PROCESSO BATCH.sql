
insert into batch.processo
values (507, 'ATUALIZACAO CADASTRAL', 'ATUALCAD' , 1, now() , 4, null, 2)  

	
insert into seguranca.funcionalidade 
values 
(16005, 8, 'Atualizacao Cadastral', now(), 'Gsan/Batch', 'null', 2, 'AtualCad', 888, 2, 2, 65)

insert into batch.processo_funcionalidade values (nextval('batch.seq_processo_funcionalidade'), 507, now(), 1, 5, 16005, 1, null)
