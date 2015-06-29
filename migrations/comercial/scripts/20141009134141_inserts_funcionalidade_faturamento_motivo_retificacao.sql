-- Migration SQL that makes the change goes here.
-- // INSERTS FUNCIONALIDADE MOTIVO RETIFICA플O
INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 7, 'Inserir Motivo Retificacao', now(), 
'Gsan/Faturamento/Conta', 'exibirInserirMotivoRetificacaoAction.do', 1, 'InMoReT', 
0, 2, 2, 33);


INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Inserir Motivo Retificacao', 'InMoReT', 'exibirInserirMotivoRetificacaoAction.do', now(), 
null, null, 4, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());


-- // INSERTS ADICIONAR FUNCIONALIDADE MOTIVO RETIFICA플O
INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 7, 'Adicionar Motivo Retificacao', now(), 
'Gsan/Faturamento/Conta', 'inserirMotivoRetificacaoAction.do', 0, 'AdMoReT', 
0, 2, 2, 33);

INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Adicionar Motivo Retificacao', 'AdMoReT', 'inserirMotivoRetificacaoAction.do', now(), 
null, null, 7, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());


-- // INSERTS MANTEM FUNCIONALIDADE MOTIVO RETIFICA플O
INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 7, 'Manter Motivo Retificacao', now(), 
'Gsan/Faturamento/Conta', 'exibirFiltrarMotivoRetificacaoAction.do', 1, 'ExFilMoReT', 
0, 2, 2, 33);

INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Manter Motivo Retificacao', 'ExFilMoReT', 'exibirFiltrarMotivoRetificacaoAction.do', now(), 
null, null, 10, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());


-- //INSERTS FILTRAR FUNCIONALIDADE MOTIVO RETIFICA플O
INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 7, 'Alterar Motivo Retificacao', now(), 
'Gsan/Faturamento/Conta', 'filtrarMotivoRetificacaoAction.do', 0, 'AlMoReT', 
0, 2, 2, 33);

INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Alterar Motivo Retificacao', 'AlMoReT', 'filtrarMotivoRetificacaoAction.do', now(), 
null, null, 5, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());


-- EXIBIR ATUALIZAR FUNCIONALIDADE MOTIVO RETIFICA플O

BEGIN;
INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 7, 'Exibir Atualizar Motivo Retificacao', now(), 
'Gsan/Faturamento/Conta', 'exibirAtualizarMotivoRetificacaoAction.do', 0, 'ExAtuMoReT', 
0, 2, 2, 33);

INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Exibir Atualizar Motivo Retificacao', 'ExAtuMoReT', 'exibirAtualizarMotivoRetificacaoAction.do', now(), 
null, null, 3, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());

INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 7, 'Atualizar Motivo Retificacao', now(), 
'Gsan/Faturamento/Conta', 'atualizarMotivoRetificacaoAction.do', 0, 'AtuMoReT', 
0, 2, 2, 33);

INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Atualizar Motivo Retificacao', 'AtuMoReT', 'atualizarMotivoRetificacaoAction.do', now(), 
null, null, 3, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());


-- //@UNDO
-- SQL to undo the change goes here.


