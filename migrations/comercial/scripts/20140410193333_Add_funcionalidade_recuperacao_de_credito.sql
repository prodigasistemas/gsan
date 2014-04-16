-- // Add operacao - Aprovar imoveis em lote
-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.funcionalidade(
        fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
        fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
        fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap)
VALUES (nextval('seguranca.seq_funcionalidade'),2,'Recuperar Credito',now(),
'Gsan/','exibirFiltrarPagamentosAClassificar.do',1,'ExRecCre',1,2,2);

insert into seguranca.operacao
(oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl,  optp_id)
values (nextval('seguranca.seq_operacao'), currval('seguranca.seq_funcionalidade'), 'Recuperar Credito', 'RecCre', '' ,12);

insert into seguranca.grupo_func_operacao values (1, currval('seguranca.seq_operacao'), currval('seguranca.seq_funcionalidade'))

-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dsoperacao like 'Recuperar Credito')
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dscaminhourl like 'exibirFiltrarPagamentosAClassificar.do');

delete from seguranca.operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dsoperacao like 'Recuperar Credito')
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dscaminhourl like 'exibirFiltrarPagamentosAClassificar.do');

delete from seguranca.funcionalidade
where fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dscaminhourl like 'exibirFiltrarPagamentosAClassificar.do');

