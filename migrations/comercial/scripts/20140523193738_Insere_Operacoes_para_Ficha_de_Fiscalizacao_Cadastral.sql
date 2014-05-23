-- // Insere Operacoes para Ficha de Fiscalizacao Cadastral
-- Migration SQL that makes the change goes here.
INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES (15027, 1211,'Fiscalizar Imovel Atualizacao Cadastral','FisImAC',
	    'fiscalizarImovelAtualizacaoCadastralAction.do',now(),null,null,3,null,2);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, 15027, 1211, now());

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (3, 15027, 1211, now());

INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES (15028, 1211,'Imprimir Ficha de Fiscalizacao Cadastral',null,
	    'imprimirFichaFiscalizacaoCadastralAction.do',now(),null,null,3,null,2);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, 15028, 1211, now());

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (3, 15028, 1211, now());

INSERT INTO cadastro.situacao_atlz_cadastral(
            siac_id, siac_dssituacao, siac_icuso, siac_tmultimaalteracao)
    VALUES (5, 'EM FISCALIZACAO', 1, now());


-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM seguranca.grupo_func_operacao
WHERE oper_id IN (15028, 15029);

DELETE FROM seguranca.operacao
WHERE oper_id IN (15028, 15029);

