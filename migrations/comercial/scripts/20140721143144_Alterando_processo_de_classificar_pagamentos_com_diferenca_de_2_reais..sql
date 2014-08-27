-- // Alterando processo de classificar pagamentos com diferenca de 2 reais.
-- Migration SQL that makes the change goes here.


update batch.processo_funcionalidade 
set proc_id = 49, prfn_nnsequencialexecucao = 2
where fncd_id = 16004



-- //@UNDO
-- SQL to undo the change goes here.


update batch.processo_funcionalidade 
set proc_id = 50, prfn_nnsequencialexecucao = 1
where fncd_id = 16004

