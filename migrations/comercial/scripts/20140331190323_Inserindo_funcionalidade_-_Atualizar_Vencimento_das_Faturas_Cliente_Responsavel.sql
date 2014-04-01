-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.funcionalidade(
        fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
        fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
        fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
VALUES (16007,7,'Atualizar Vencimento de Fatura de Cliente Responsavel',now(),
'Gsan/Faturamento/','exibirAtualizarVencimentoFaturaClienteResponsavel.do',1,'AtVeFaCl',0,2,2,32);

INSERT INTO seguranca.operacao(
        oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
        oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
        tbco_idargumento, oper_icregistratransacao)
VALUES (15020, 16007, 'Atualizar Vencimento de Fatura de Cliente Responsavel', 'AtVeFaCl', 'atualizarVencimentoFaturaClienteResponsavelAction.do',now(), null, null, 3, 
        null, 2);

INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, 15020, 16007, now());

-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.grupo_func_operacao
where grup_id = 1, oper_id = 15020, fncd_id = 16007;

delete from seguranca.operacao
where oper_id = 15020;

delete from seguranca.funcionalidade
where fncd_id = 16007;


