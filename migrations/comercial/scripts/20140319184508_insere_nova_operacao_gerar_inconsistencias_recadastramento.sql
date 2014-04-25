-- // insere nova operacao gerar inconsistencias recadastramento
-- Migration SQL that makes the change goes here.
INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao,
            oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa,
            tbco_id, optp_id, tbco_idargumento,
	    oper_icregistratransacao)
    VALUES (15019, 1207, 'Gerar Relatorio Inconsistencias Retorno', 'GeReInRe',
	    'gerarRelatorioInconsistenciasRetornoAtualizacaoCadastralAction.do', now(), null, null,
	    14, null, 2);


INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, 15019, 1207, now());

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (69, 15019, 1207, now());


-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM seguranca.grupo_func_operacao WHERE oper_id = 15019;
DELETE FROM seguranca.operacao WHERE oper_id = 15019;


