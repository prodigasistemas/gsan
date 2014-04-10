INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES ((select max(oper_id) + 1 from seguranca.operacao),1194,
            'Retornar Zip Arquivo Texto Atualizacao Cadastral','RetZipTxAC',
            'retornarZipArquivoTxtAtualizacaoCadastralAction.do',now(),null,null,
            6,null,2);


INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, (select max(oper_id) from seguranca.operacao), 1194, now());

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (3, (select max(oper_id) from seguranca.operacao), 1194, now());
