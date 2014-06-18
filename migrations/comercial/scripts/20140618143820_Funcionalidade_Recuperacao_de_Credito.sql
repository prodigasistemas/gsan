-- // Funcionalidade Recuperacao de Credito
-- Migration SQL that makes the change goes here.

insert into seguranca.funcionalidade  (fncd_id,modu_id,fncd_dsfuncionalidade,fncd_tmultimaalteracao,fncd_dscaminhomenu,fncd_dscaminhourl,fncd_icpontoentrada,fncd_dsabreviado,fncd_nnordemmenu,fncd_icnovajanela,fncd_icolap,fncg_id) 
values (nextval('seguranca.seq_funcionalidade'),2,'Recuperar Credito',now(),'/Gsan','exibirFiltrarPagamentosAClassificarAction.do',1,'RecCre',1,2,2,13	);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Recuperar Credito','RecCre','exibirFiltrarPagamentosAClassificarAction.do',now(),null,null,12,null,2);

----------------------

insert into seguranca.funcionalidade  (fncd_id,modu_id,fncd_dsfuncionalidade,fncd_tmultimaalteracao,fncd_dscaminhomenu,fncd_dscaminhourl,fncd_icpontoentrada,fncd_dsabreviado,fncd_nnordemmenu,fncd_icnovajanela,fncd_icolap,fncg_id) 
values (nextval('seguranca.seq_funcionalidade'),2,'Filtrar Pagamentos Recuperacao de Credito',now(),'/Gsan','filtrarPagamentosRecuperacaoDeCreditoAction.do',2,'FilRecCre',1,2,2,13	);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Filtrar Recuperar Credito','RecCre','filtrarPagamentosRecuperacaoDeCreditoAction.do',now(),null,null,12,null,2);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Devolver Credito','DevCre','refaturarPagamentosNaoClassificadosAction.do',now(),null,null,12,null,2);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Devolver Credito' ,'DevCr','classificarPagamentosAction.do',now(),null,null,12,null,2);

----------------

insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (	
1,
(select oper_id from seguranca.operacao where oper_dsoperacao like 'Recuperar Credito' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Recuperar Credito')
,now());

insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (	
1,
(select oper_id from seguranca.operacao where oper_dsoperacao like 'Filtrar Recuperar Credito' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito')
,now());

insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (
1,
(select oper_id from seguranca.operacao where oper_dscaminhourl like 'classificarPagamentosAction.do' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito'),
now());

insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (
1,
(select oper_id from seguranca.operacao where oper_dscaminhourl like 'refaturarPagamentosNaoClassificadosAction.do' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito'),
now());


insert into faturamento.credito_origem values (7, 'VALORES COBRADOS INDEVIDAMENTE', 'VALCOBIN', 1, now()); 
insert into faturamento.conta_motivo_inclusao values (46, 'RECUPERACAO DE CREDITO', 1, now());

-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.usuario_favoritos where fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Recuperar Credito');

delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dsoperacao like 'Recuperar Credito' )
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Recuperar Credito');


delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dscaminhourl like 'filtrarPagamentosRecuperacaoDeCreditoAction.do' )
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito');


delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dscaminhourl like 'classificarPagamentosAction.do' )
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito');

delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dscaminhourl like 'refaturarPagamentosNaoClassificadosAction.do' )
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito');


delete from seguranca.operacao where oper_dscaminhourl like 'exibirFiltrarPagamentosAClassificar.do';
delete from seguranca.operacao where oper_dscaminhourl like 'filtrarPagamentosRecuperacaoDeCreditoAction.do';
delete from seguranca.operacao where oper_dscaminhourl like 'classificarPagamentosAction.do';
delete from seguranca.operacao where oper_dscaminhourl like 'refaturarPagamentosNaoClassificadosAction.do';

delete from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Recuperar Credito';
delete from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Pagamentos Recuperacao de Credito';

delete from faturamento.credito_origem where crog_id = 7;
delete from faturamento.conta_motivo_inclusao where cmic_id = 46;
