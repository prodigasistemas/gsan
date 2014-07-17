-- // Tela Faturamento Seletivo
-- Migration SQL that makes the change goes here.

-- FILTRAR FATURAMETO SELETIVO
insert into seguranca.funcionalidade  (fncd_id,modu_id,fncd_dsfuncionalidade,fncd_tmultimaalteracao,fncd_dscaminhomenu,fncd_dscaminhourl,fncd_icpontoentrada,fncd_dsabreviado,fncd_nnordemmenu,fncd_icnovajanela,fncd_icolap,fncg_id) 
values (nextval('seguranca.seq_funcionalidade'),7,'Filtrar Faturamento Seletivo',now(),'/Gsan','filtrarFaturamentoSeletivo.do',1,'FilFatSel',1,2,2,32);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Filtrar Faturamento Seletivo','FilFatSel','filtrarFaturamentoSeletivo.do',now(),null,null,1,null,2);

insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (	
1,
(select oper_id from seguranca.operacao where oper_dsoperacao like 'Filtrar Faturamento Seletivo' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Faturamento Seletivo')
,now());


-- EXIBIR FATURAMENTO SELETIVO
insert into seguranca.funcionalidade  (fncd_id,modu_id,fncd_dsfuncionalidade,fncd_tmultimaalteracao,fncd_dscaminhomenu,fncd_dscaminhourl,fncd_icpontoentrada,fncd_dsabreviado,fncd_nnordemmenu,fncd_icnovajanela,fncd_icolap,fncg_id) 
values (nextval('seguranca.seq_funcionalidade'),7,'Exibir Faturamento Seletivo',now(),'/Gsan','exibirFaturamentoSeletivo.do',2,'ExibFatSel',1,2,2,32);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Exibir Faturamento Seletivo','ExibFatSel','exibirFaturamentoSeletivo.do',now(),null,null,1,null,2);

insert into seguranca.operacao (oper_id,fncd_id,oper_dsoperacao,oper_dsabreviado,oper_dscaminhourl,oper_tmultimaalteracao,oper_idoperacaopesquisa,tbco_id,optp_id,tbco_idargumento,oper_icregistratransacao) 
values (nextval('seguranca.seq_operacao'),currval('seguranca.seq_funcionalidade'),'Faturamento Seletivo','FatSel','FaturamentoSeletivo.do',now(),null,null,1,null,2);


insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (	
1,
(select oper_id from seguranca.operacao where oper_dsoperacao like 'Exibir Faturamento Seletivo' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Exibir Faturamento Seletivo')
,now());


insert into seguranca.grupo_func_operacao (grup_id,oper_id,fncd_id,gfop_tmultimaalteracao) values (	
1,
(select oper_id from seguranca.operacao where oper_dsoperacao like 'Faturamento Seletivo' ),
(select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Exibir Faturamento Seletivo')
,now());
-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.usuario_favoritos where fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Faturamento Seletivo');
delete from seguranca.usuario_favoritos where fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Exibir Faturamento Seletivo');

delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dscaminhourl like 'filtrarFaturamentoSeletivo.do' )
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Faturamento Seletivo');

delete from seguranca.operacao where oper_dscaminhourl like 'filtrarFaturamentoSeletivo.do';
delete from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Filtrar Faturamento Seletivo';


delete from seguranca.grupo_func_operacao
where oper_id = (select oper_id from seguranca.operacao where oper_dscaminhourl like 'exibirFaturamentoSeletivo.do' )
and fncd_id = (select fncd_id from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Exibir Faturamento Seletivo');

delete from seguranca.operacao where oper_dscaminhourl like 'exibirFaturamentoSeletivo.do';
delete from seguranca.funcionalidade where fncd_dsfuncionalidade like 'Exibir Faturamento Seletivo';


