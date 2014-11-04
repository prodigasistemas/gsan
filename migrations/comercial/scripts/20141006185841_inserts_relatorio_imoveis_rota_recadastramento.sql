-- // inserts relatorio imoveis rota recadastramento
-- Migration SQL that makes the change goes here.
INSERT INTO seguranca.funcionalidade(
fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 1, 'Relatorio de Imoveis por Rota', now(), 
'Gsan/Cadastro/Atualizacao Cadastral', 'exibirGerarRelatorioRelacaoImoveisRota.do', 1, '', 
13000006, 2, 2, 121);

INSERT INTO seguranca.operacao(
oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
tbco_idargumento, oper_icregistratransacao)
VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
'Relatorio Relacao de Imoveis por Rota', '', 'gerarRelatorioRelacaoImoveisRotaAction.do', now(), 
null, null, 13, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());

INSERT INTO seguranca.grupo_func_operacao(
grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (71, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());


-- //@UNDO
-- SQL to undo the change goes here.


