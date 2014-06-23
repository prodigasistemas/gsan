-- // insert operacao imagens recadastramento
-- Migration SQL that makes the change goes here.
INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES ((select max(oper_id) + 1 from seguranca.operacao),241,'Exibir Imagem Imovel','EFOC','exibirImovelImagemAction.do',now(),null,null,4,null,2);

INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (1, (select max(oper_id) from seguranca.operacao), 241, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (3, (select max(oper_id) from seguranca.operacao), 241, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (9, (select max(oper_id) from seguranca.operacao), 241, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (11, (select max(oper_id) from seguranca.operacao), 241, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (13, (select max(oper_id) from seguranca.operacao), 241, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (16, (select max(oper_id) from seguranca.operacao), 241, now());



INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES ((select max(oper_id) + 1 from seguranca.operacao),1211,'Exibir Imagem Retorno Atualizacao Cadastral','ExImgRet',
	    'exibirImagemRetornoAtualizacaoCadastralAction.do',now(),null,null,4,null,2);

INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (1, (select max(oper_id) from seguranca.operacao), 1211, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (3, (select max(oper_id) from seguranca.operacao), 1211, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (9, (select max(oper_id) from seguranca.operacao), 1211, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (11, (select max(oper_id) from seguranca.operacao), 1211, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (13, (select max(oper_id) from seguranca.operacao), 1211, now());
INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao) VALUES (16, (select max(oper_id) from seguranca.operacao), 1211, now());


-- //@UNDO
-- SQL to undo the change goes here.


