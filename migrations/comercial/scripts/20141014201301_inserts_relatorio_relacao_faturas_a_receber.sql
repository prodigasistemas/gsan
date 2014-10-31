-- // inserts relatorio relacao faturas a receber
-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.funcionalidade(
            fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
            fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
            fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
    VALUES ((select max(fncd_id)+1 from seguranca.funcionalidade), 9, 'Relatorio de Receitas a Faturar', now(), 
            'Gsan/Relatorios/Faturamento', 'exibirGerarRelatorioReceitasAFaturar.do', 1, 'GeRelRecFa', 
            70040030001, 2, 2, 104);

INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES ((select max(oper_id)+1 from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade),
	    'Relatorio de Receitas a Faturar', null, 'gerarRelatorioReceitasAFaturarAction.do', now(), 
            null, null, 13, null, 2);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (16, (select max(oper_id) from seguranca.operacao), (select max(fncd_id) from seguranca.funcionalidade), now());

-- //@UNDO
-- SQL to undo the change goes here.


