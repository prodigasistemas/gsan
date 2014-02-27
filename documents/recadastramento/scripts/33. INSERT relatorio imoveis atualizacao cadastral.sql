INSERT INTO batch.relatorio(
            rela_id, rela_dsrelatorio, rela_dsabreviada, rela_icuso, rela_tmultimaalteracao)
    VALUES (nextval('batch.seq_relatorio'), 'Relatorio de Imoveis por Sit e Periodo', 'ReImSiPe', 1, now());


INSERT INTO seguranca.funcionalidade(
            fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
            fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
            fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
    VALUES (16005, 1, 'Relatorio de Imoveis Por Situacao e Período', now(), 
            'Gsan/Cadastro/Atualizacao Cadastral' , 'exibirGerarRelatorioImoveisSituacaoPeriodo.do', 1, 'GeReSiPe', 
            13000005, 2, 2, 121);

            INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES (15018, 16005, 'Gerar Relatorio de Imoveis Por Situacao e Periodo', 'GeReISiPe',
     'gerarRelatorioImoveisSituacaoPeriodoAction', now(), null, null, 13, null, 2);

     INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, 15018,16005, now());