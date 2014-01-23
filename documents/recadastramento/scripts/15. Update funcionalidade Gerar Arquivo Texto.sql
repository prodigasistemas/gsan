UPDATE batch.processo_funcionalidade
   SET proc_id=177, prfn_nnsequencialexecucao=2
 WHERE fncd_id = 1152;

UPDATE seguranca.funcionalidade
   SET fncd_icpontoentrada=2
 WHERE fncd_id = 1152;

UPDATE batch.processo_funcionalidade
   SET unpr_id=6
 WHERE proc_id = 177 AND fncd_id = 1088;

