UPDATE seguranca.funcionalidade
 SET modu_id=1, fncd_dsfuncionalidade='Consultar Movimento de Atualizacoes Cadastrais', fncd_tmultimaalteracao=now(), 
       fncd_dscaminhomenu='Gsan/Cadastro/Atualizacao Cadastral Dispositivo Movel', fncd_dscaminhourl='exibirFiltrarAlteracaoAtualizacaoCadastralAction.do', fncd_icpontoentrada=1, 
       fncd_dsabreviado='ExConAtCad', fncd_nnordemmenu=0, fncd_icnovajanela=2, 
       fncd_icolap=2, fncg_id=121
 WHERE fncd_id = 1211;


UPDATE seguranca.operacao
 SET oper_dsoperacao='Consultar Movimento de Atualizacoes Cadastrais', oper_dsabreviado='ConsAtCad', 
       oper_dscaminhourl='consultarMovimentoAtualizacaoCadastral.do', oper_tmultimaalteracao=now(), oper_idoperacaopesquisa=null, 
       tbco_id=null, optp_id=6, tbco_idargumento=null
 WHERE oper_id=1507;


UPDATE seguranca.operacao
 SET oper_dsoperacao='Exibir Atualizar Dados Imovel Atualizacao Cadastral', oper_dsabreviado='ExAtDACPop', 
       oper_dscaminhourl='exibirAtualizarDadosImovelAtualizacaoCadastralPopupAction.do', oper_tmultimaalteracao=now(), oper_idoperacaopesquisa=null, 
       tbco_id=null, optp_id=6, tbco_idargumento=null
 WHERE oper_id=1508;

UPDATE seguranca.operacao
 SET oper_dsoperacao='Atualizar Dados Imovel Atualizacao Cadastral', oper_dsabreviado='AtuDadImAC', 
       oper_dscaminhourl='atualizarDadosImovelAtualizacaoCadastralAction.do', oper_tmultimaalteracao=now(), oper_idoperacaopesquisa=null, 
       tbco_id=null, optp_id=3, tbco_idargumento=null
 WHERE oper_id=1509;

UPDATE seguranca.operacao
 SET oper_dsoperacao='Atualizar Dados Cadastrais via Movimento', oper_dsabreviado='AtDaCaVMov', 
       oper_dscaminhourl='atualizarDadosCadastraisViaMovimentoAction.do', oper_tmultimaalteracao=now(), oper_idoperacaopesquisa=null, 
       tbco_id=null, optp_id=3, tbco_idargumento=null
 WHERE oper_id=1515;

UPDATE batch.processo_funcionalidade
   SET unpr_id=6
 WHERE fncd_id=1152;



