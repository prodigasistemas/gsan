
<table border="0" class="layerMenu">
<tr>
    <td>
	  <bean:write name="menuGCOM" scope="session" filter="false"/>
    </td>
  </tr>
  <%-- 
	<tr>
		<td>
		<div class="dtree"><script><!-- -
          d = new dTree('d');
          d.add(0,-1,'Menu GSAN');
          d.add(1,0,'GSAN','#');
          d.add(2,1,'Cadastro','#');
          d.add(3,2,'Cliente','#');
          d.add(4,2,'Imóvel','#');
          d.add(5,3,'Inserir Cliente','exibirInserirClienteAction.do?menu=sim');
          d.add(6,3,'Manter Cliente','exibirManterClienteAction.do?menu=sim');
          d.add(7,4,'Inserir Imóvel','exibirInserirImovelAction.do?menu=sim');
          d.add(8,4,'Informar Economia','exibirInformarEconomiaAction.do?menu=sim');
          d.add(9,1,'Micromedição','#');
          d.add(10,2,'Localidade','#');
          d.add(11,10,'Inserir Localidade','exibirInserirLocalidadeAction.do?menu=sim');
          d.add(12,10,'Manter Localidade','exibirFiltrarLocalidadeAction.do?menu=sim');
          d.add(13,10,'Inserir Setor Comercial','exibirInserirSetorComercialAction.do?menu=sim');
          d.add(14,10,'Manter Setor Comercial','exibirFiltrarSetorComercialAction.do?menu=sim');
          d.add(15,10,'Inserir Quadra','exibirInserirQuadraAction.do?menu=sim');
          d.add(16,9,'Consistir Leituras e Calcular Consumos','exibirConsistirLeiturasCalcularConsumosAction.do?menu=sim'  '');
          d.add(17,4,'Alterar Inscrição Imóvel','exibirAlterarImovelInscricaoAction.do?menu=sim');
          d.add(18,2,'Geográfico','#');
          d.add(19,22,'Inserir Bairro','exibirInserirBairroAction.do?menu=sim');
          d.add(20,22,'Manter Bairro','exibirManterBairroAction.do?menu=sim');
          d.add(21,10,'Manter Quadra','exibirFiltrarQuadraAction.do?menu=sim');
          d.add(22,2,'Endereço','#');
          d.add(23,22,'Inserir Logradouro','exibirInserirLogradouroAction.do?menu=sim');
          d.add(24,22,'Manter Logradouro','exibirManterLogradouroAction.do?menu=sim');
		  d.add(25,4,'Manter Imóvel','exibirFiltrarImovelAction.do?menu=sim');
		  d.add(26,2,'Tarifa Social','#');
		  d.add(27,26,'Inserir Tipo de Cartão da Tarifa Social','exibirInserirTarifaSocialCartaoTipoAction.do?menu=sim');
          d.add(28,26,'Manter Tipo de Cartão da Tarifa Social','filtrarTarifaSocialCartaoTipoAction.do?menu=sim');
          d.add(29,9,'Hidrômetro','#')
          d.add(30,29,'Inserir Hidrômetro','exibirInserirHidrometroAction.do?menu=sim');
          d.add(31,29,'Manter Hidrômetro','exibirManterHidrometroAction.do?menu=sim');
          d.add(32,29,'Movimentar Hidrômetro','exibirFiltrarHidrometroAction.do?menu=sim&tela=movimentarHidrometro');
		  d.add(33,35,'Inserir Comando de Atividade de Faturamento','exibirInserirComandoAtividadeFaturamentoAction.do?menu=sim');
		  d.add(34,35,'Manter Comando de Atividade de Faturamento','filtrarComandoAtividadeFaturamentoAction.do?menu=sim');		  
		  d.add(35,1,'Faturamento','#');
		  d.add(36,37,'Inserir Conta','exibirInserirContaAction.do?menu=sim');
		  d.add(37,35,'Conta','#');
		  d.add(38,29,'Consultar Movimentação de Hidrômetro','exibirFiltrarMovimentacaoHidrometroAction.do?menu=sim&tela=consultarMovimentacaoHidrometro');
		  d.add(39,26,'Inserir Dados Tarifa Social','exibirInserirTarifaSocialAction.do?menu=sim');		  
		  d.add(40,26,'Manter Dados Tarifa Social','exibirFiltrarImovelAction.do?menu=sim&acao=exibir&redirecionar=ManterDadosTarifaSocial');
		  d.add(41,37,'Informar Vencimento Alternativo','exibirInformarVencimentoAlternativoAction.do?menu=sim');
		  d.add(42,37,'Manter Conta','exibirManterContaAction.do?menu=sim');
 		  d.add(43,37,'Simular Cálculo da Conta','exibirSimularCalculoContaAction.do?menu=sim');
		  d.add(43,22,'Importar Cep Correios','exibirImportarCepAction.do?menu=sim');
		  d.add(44,111,'Inserir Débito a Cobrar','exibirInserirDebitoACobrarAction.do?menu=sim');
		  d.add(45,111,'Cancelar Débito a Cobrar','exibirManterDebitoACobrarAction.do?menu=sim');
          d.add(46,109,'Inserir Categoria','exibirInserirCategoriaAction.do?menu=sim'); 
          d.add(47,35, 'Tarifa de Consumo', '#');
          d.add(48,47, 'Inserir Tarifa de Consumo', 'exibirInserirConsumoTarifaAction.do?menu=sim');
          d.add(49,109, 'Manter Categoria', 'exibirManterCategoriaAction.do?menu=sim');
          d.add(50,109, 'Inserir Subcategoria', 'exibirInserirSubcategoriaAction.do?menu=sim');
          d.add(51,109, 'Manter Subcategoria', 'exibirManterSubcategoriaAction.do?menu=sim');
          d.add(52,35,'Consultar Histórico de Faturamento','exibirConsultarHistoricoFaturamentoAction.do?menu=sim');
		  d.add(53,1,'Relatório','#');
		  d.add(54,53,'Cadastro','#');
		  d.add(55,54,'Relatório de Imóveis','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=RelatorioImoveis&limpar=S');
		  d.add(56,54,'Relatório dos Dados das Economias dos Imóveis','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=RelatorioEconomia&limpar=S');
		  d.add(57,54,'Relatório Dados Tarifa Social','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=RelatorioTarifaSocial&limpar=S');
		  d.add(58,35,'Inserir Cronograma Faturamento','exibirInserirFaturamentoCronogramaAction.do?menu=sim');
		  d.add(59,35,'Manter Cronograma Faturamento','exibirFiltrarFaturamentoCronogramaAction.do?menu=sim');
		  d.add(60,54,'Gerar Relatório de Clientes','exibirFiltrarClienteOutrosCriteriosAction.do?menu=sim');
		  d.add(61,35,'Alterar Dados para Faturamento','exibirDadosFaturamentoAction.do?menu=sim');
		  d.add(62,1,'Cobrança','#');
		  d.add(63,62,'Consultar Débitos','exibirConsultarDebitoAction.do?menu=sim');
		  d.add(64,9,'Manter Vínculos de Imóveis para Rateio de Consumo','exibirFiltrarImovelAction.do?menu=sim&redirecionar=ManterVinculoImoveisRateioConsumo');		  
		  d.add(65,35,'Crédito','#');
		  d.add(66,65,'Inserir Crédito a Realizar','exibirInserirCreditoARealizarAction.do?menu=sim');		  
		  d.add(67,47, 'Manter Tarifa de Consumo', 'exibirFiltrarConsumoTarifaAction.do?menu=sim');
		  d.add(68,65,'Cancelar Crédito a Realizar','exibirManterCreditoARealizarAction.do?menu=sim');
		  d.add(69,35,'Inserir Guia de Pagamento', 'exibirInserirGuiaPagamentoAction.do?menu=sim');
		  d.add(70,35,'Cancelar Guia de Pagamento', 'exibirManterGuiaPagamentoAction.do?menu=sim');
  		  d.add(71,9,'Consultar Histórico de Medição Individualizada', 'exibirConsultarHistoricoMedicaoIndividualizadaAction.do?menu=sim');
  		  d.add(72,9,'Analisar Exceções de Leituras e Consumos','exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim');
		  d.add(73,35, 'Informar Situação Especial de Faturamento', 'exibirSituacaoEspecialFaturamentoInformarAction.do?menu=sim' );	
		  d.add(74,29,'Consultar Histórico de Instalação','exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do?menu=sim');
		  d.add(75,9,'Registrar Leituras e Anormalidades','exibirRegistrarLeiturasAnormalidadesAction.do?menu=sim');
		  d.add(76,200,'Inserir Comando de Ação de Cobrança','exibirInserirComandoAcaoCobrancaAction.do?menu=sim');		  
		  d.add(77,53,'Faturamento','#');		  
		  d.add(78,77,'Relatório Resumo Faturamento','exibirGerarRelatorioResumoFaturamentoAction.do?menu=sim');		  		  
		  d.add(79,200,'Executar Atividade de Ação de Cobrança','exibirExecutarAtividadeAcaoCobrancaAction.do?menu=sim');
		  d.add(80,202,'Efetuar Parcelamento de Débitos','exibirEfetuarParcelamentoDebitosAction.do?menu=sim');
		  d.add(81,202,'Consultar Parcelamento de Débitos','exibirConsultarListaParcelamentoDebitoAction.do?menu=sim');
		  d.add(82,1,'Arrecadação','#');
		  d.add(83,82,'Aviso Bancário','#');
		  d.add(84,82,'Pagamento','#');		  		  		  
		  d.add(85,84,'Inserir Pagamentos','exibirInserirPagamentosAction.do?menu=sim');
		  d.add(86,1,'Segurança','#');
		  d.add(87,86,'Transação','#');
		  d.add(88,87,'Consultar Operação','ExibirFiltrarOperacaoEfetuadaAction.do?acao=consulta&menu=sim');
		  d.add(89,137,'Inserir Devoluções','exibirInserirDevolucoesAction.do?menu=sim');
		  d.add(90,137,'Manter Devoluções','exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&menu=sim');
		  d.add(91,53,'Transação','#');		  
		  d.add(92,91,'Relatório Tranções Efetuada','ExibirFiltrarOperacaoAction.do?acao=relatorio&menu=sim');
		  d.add(93,9,'Consultar Imóveis Medição Individualizada','exibirConsultarImoveisMedicaoIndividualizadaAction.do?menu=sim');
		  d.add(94,82,'Registrar Movimento Arrecadadores','exibirRegistrarMovimentoArredadadoresAction.do?menu=sim');
		  d.add(95,83,'Inserir Aviso Bancário','exibirInserirAvisoBancarioAction.do?menu=sim');	          
          d.add(96,62, 'Informar Situação Especial de Cobrança', 'exibirSituacaoEspecialCobrancaInformarAction.do?menu=sim' );
          d.add(97,84,'Manter Pagamentos','exibirFiltrarPagamentoAction.do?tela=manterPagamento&menu=sim');
          d.add(98,82,'Efetuar Análise do Movimento dos Arrecadadores','exibirFiltrarMovimentoArrecadadoresAction.do?menu=sim');
          d.add(99,83,'Manter Aviso Bancario','exibirFiltrarAvisoBancarioAction.do?menu=sim');
          d.add(100,200,'Manter Comando de Ação de Cobrança','exibirManterComandoAcaoCobrancaAction.do?menu=sim');
          d.add(101,26,'Consultar Imóveis Excluídos da Tarifa Social','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=consultarTarifaExcluida&limpar=S');
          d.add(102,83,'Efetuar Análise Aviso Bancário','exibirFiltrarAvisoBancarioAction.do?menu=sim&acao=efetuar');
          d.add(103,137,'Consultar Devoluções','exibirFiltrarDevolucaoAction.do?menu=sim');
          d.add(104,35,'Consultar Posição Faturamento','PesquisarPosicaoFaturamentoAction.do?menu=sim');
          d.add(105,4,'Relação Cliente e Imóvel','ExibirConsultarRelacaoClienteImovelAction.do?menu=sim');
          d.add(106,35,'Executar Atividade do Faturamento','exibirExecutarAtividadeFaturamentoAction.do?menu=sim');
          d.add(107,62,'Inserir Resolução de Diretoria','exibirInserirResolucaoDiretoriaAction.do?menu=sim');
          d.add(108,62,'Consultar Documentos de Cobrança','exibirFiltrarDocumentosCobrancaAction.do?menu=sim');
          d.add(109,2,'Categoria','#');
          d.add(110,84,'Consultar Pagamentos','exibirFiltrarPagamentoAction.do?menu=sim');		  
          d.add(111,35,'Débito','#');
          d.add(112,62,'Manter Resolução de Diretoria','exibirFiltrarResolucaoDiretoriaAction.do?menu=sim');	  
          d.add(113,9,'Rota','#');
          d.add(114,113,'Inserir Rota','exibirInserirRotaAction.do?menu=sim');
          d.add(115,113,'Manter Rota','exibirFiltrarRotaAction.do?menu=sim');
          d.add(116,4,'Inserir Situação do Imóvel ','exibirInserirImovelSituacaoAction.do?menu=sim');
          d.add(117,4,'Consultar Situação do Imóvel ','exibirFiltrarImovelSituacaoAction.do?menu=sim');
          d.add(118,82,'Gerar Movimento do Débito Automático para os Bancos','exibirGerarMovimentoDebitoAutomaticoBancoAction.do?menu=sim');
          d.add(119,62,'Inserir Cronograma de Cobrança ','exibirInserirCronogramaCobrancaAction.do?menu=sim');
          d.add(120,62,'Inserir Critério de Cobrança ','exibirInserirCriterioCobrancaAction.do?menu=sim');
          d.add(121,137,'Inserir Guia de Devolução ','exibirInserirGuiaDevolucaoAction.do?menu=sim');
		  d.add(122,37,'Desfazer Cancelamento e/ou Retificação','exibirManterDesfazerCancelamentoRetificacaoContaAction.do?menu=sim');
          d.add(123,86,'Acesso','#');
          d.add(124,123,'Inserir Grupo','exibirInserirGrupoAction.do?menu=sim');
          d.add(125,123,'Manter Grupo','exibirManterGrupoAction.do?menu=sim');
          d.add(126,37,'Inserir Mensagem da Conta','exibirInserirMensagemContaAction.do?menu=sim');
          d.add(127,37,'Manter Mensagem da Conta','exibirFiltrarMensagemContaAction.do?menu=sim');
          d.add(128,62,'Manter Critério de Cobrança ','exibirFiltrarCriterioCobrancaAction.do?menu=sim');
          d.add(129,77,'Relatório de Acompanhamento de Faturamento','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=GerarRelatorioAcompanhamentoFaturamento&limpar=S');		  		  
          d.add(130,200,'Consultar Comandos de Ação de Cobrança','exibirConsultarComandosAcaoCobrancaAction.do?menu=sim');		  		  
          d.add(131,123,'Inserir Funcionalidade','exibirInserirFuncionalidadeAction.do?menu=sim');
          d.add(132,123,'Usuário','#');
          d.add(133,132,'Inserir Usuário','exibirInserirUsuarioAction.do?menu=sim');
          d.add(134,132,'Manter Usuário','exibirFiltrarUsuarioAction.do?menu=sim');
          d.add(135,202,'Inserir Perfil de Parcelamento','exibirInserirPerfilParcelamentoAction.do?menu=sim');
          d.add(136,137,'Manter Guia de Devolução','exibirFiltrarGuiaDevolucaoAction.do?menu=sim');
          d.add(137,82,'Devolução','#');
          d.add(138,123,'Inserir Operação','exibirInserirOperacaoAction.do?menu=sim');
          d.add(139,202,'Manter Perfil de Parcelamento','exibirFiltrarPerfilParcelamentoAction.do?menu=sim');
		  d.add(140,123,'Manter Funcionalidade','exibirFiltrarFuncionalidadeAction.do?menu=sim');	
	  	  d.add(141,62,'Manter Cronograma de Cobrança','exibirFiltrarCobrancaCronogramaAction.do?menu=sim');
	  	  d.add(142,82,'Consultar Dados Diários da Arrecadação','exibirFiltrarDadosDiariosArrecadacaoAction.do?menu=sim');
	  	  d.add(143,171,'Consultar Resumo das Anormalidades','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=ANORMALIDADE');
          d.add(144,53,'Cobrança','#');
	  	  d.add(145,144,'Gerar Relação de Débitos','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=GerarRelacaoDebito&limpar=S');	  	  
	  	  d.add(146,148,'Consultar Resumo de Pendência','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=PENDENCIA');
	  	  d.add(147,1,'Gerencial','#');
	  	  d.add(148,147,'Cobranca','#');
	  	  d.add(149,147,'Arrecadação','#');
	  	  d.add(151,147,'Faturamento');
	  	  d.add(152,151,'Consultar Resumo das Situações Especiais de Faturamento','exibirInformarResumoSituacaoEspecialFaturamentoAction.do?menu=sim');
          d.add(153,147,'Cadastro');
	  	  d.add(154,153,'Consultar Resumo das Ligacoes / Economias','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=LIGACAO_EC0NOMIA');
	  	  d.add(155,1,'Execução Batch','#');
	  	  d.add(156,155,'UC0302 - Gerar Débitos a Cobrar de Acréscimos por Impontualidade', 'execucaoBatchAction.do?casoUso=UC0302'  '');
	  	  d.add(157,155,'UC0275 - Gerar Resumo das Ligações/Economias', 'execucaoBatchAction.do?casoUso=UC0275'  '');
	  	  d.add(158,155,'UC0209 - Gerar Taxa de Entrega em Outro Endereço',execucaoBatchAction.do?casoUso=UC0209&idRotas=3,984&anoMes=20045' '');
	  	  d.add(159,155,'UC0341 - Gerar Resumo da Situação Especial de Faturamento','execucaoBatchAction.do?casoUso=UC0341' '');
	  	  d.add(160,155,'UC0346 - Gerar Resumo da Situação Especial de Cobrança','execucaoBatchAction.do?casoUso=UC0346' '');
	  	  d.add(161,155,'UC0335 - Gerar Resumo da Pendência','execucaoBatchAction.do?casoUso=UC0335' '');
	  	  d.add(162,155,'UC0276 - Encerrar a Arrecadação do Mês','execucaoBatchAction.do?casoUso=UC0276' '');
	  	  d.add(163,155,'UC0348 - Gerar Lançamentos Contábeis da Arrecadação','execucaoBatchAction.do?casoUso=UC0348&anoMes=200601' '');
	  	  d.add(164,155,'UC0300 - Classificar Pagamentos e Devoluções','execucaoBatchAction.do?casoUso=UC0300' '');
	  	  d.add(165,155,'UC0301 - Gerar Dados Diários da Arrecadação','execucaoBatchAction.do?casoUso=UC0301' '');
	  	  d.add(166,155,'UC0343 - Gerar Resumo das Anormalidades','execucaoBatchAction.do?casoUso=UC0343' '');
	  	  d.add(167,155,'UC0352 - Emitir Contas','execucaoBatchAction.do?casoUso=UC0352&idContas=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15' '');
	  	  d.add(168,155,'UC0349 - Emitir Documento de Cobrança','execucaoBatchAction.do?casoUso=UC0349' '');
	  	  d.add(169,155,'UC0320 - Gerar Fatura de Cliente Responsável','execucaoBatchAction.do?casoUso=UC0320' '');
	  	  d.add(170,155,'UC0321 - Emitir Fatura de Cliente Responsável','execucaoBatchAction.do?casoUso=UC0321' '');
	  	  d.add(171,147,'Micromedição');
	  	  d.add(173,155,'UC0343 - Gerar Resumo de Anormalidades','execucaoBatchAction.do?casoUso=UC0343' '');
          d.add(174,148,'Consultar Resumo das Situações Especiais de Cobrança','exibirInformarResumoSituacaoEspecialCobrancaAction.do?menu=sim');
          d.add(175,151,'Consultar Resumo da Análise do Faturamento','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&analiseFaturamento=ok&tipoResumo=ANALISE');
          d.add(176,155,'UC0343 - Gerar Resumo Anormalidade Consumo','execucaoBatchAction.do?casoUso=UC0343C' '');
          d.add(177,53,'Arrecadação','#');
          d.add(178,177,'Relatório Resumo Arrecadação','exibirGerarRelatorioResumoArrecadacaoAction.do?menu=sim');
          d.add(179,132,'Inserir Situação do Usuário','exibirInserirSituacaoUsuarioAction.do?menu=sim');
          d.add(180,132,'Manter Situação do Usuário','exibirManterSituacaoUsuarioAction.do?menu=sim');
          d.add(181,132,'Inserir Abrangência do Usuário','exibirInserirAbrangenciaUsuarioAction.do?menu=sim');
          d.add(182,132,'Manter Abrangência do Usuário','exibirManterAbrangenciaUsuarioAction.do?menu=sim');
          d.add(183,149,'Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=COMPARATIVORESUMOS');
          d.add(184,1,'Atendimento ao Público','#');
          d.add(185,184,'Ligação Água','#');
          d.add(186,185,'Efetuar Ligação de Água','exibirEfetuarLigacaoAguaAction.do?menu=sim');
          d.add(187,185,'Efetuar Corte de Ligação de Água','exibirEfetuarCorteLigacaoAguaAction.do?menu=sim');
          d.add(188,184,'Ligação Esgoto','#');
          d.add(189,188,'Efetuar Ligação de Esgoto','exibirEfetuarLigacaoEsgotoAction.do?menu=sim');
          d.add(190,155,'UC0213 - Desfazer Parcelamentos Por Entrada Não Paga','execucaoBatchAction.do?casoUso=UC0213');
          d.add(191,132,'Bloquear Desbloquear Acesso','exibirBloquearDesbloquearAcessoUsuarioAction.do?menu=sim');
          d.add(192,184,'Hidrômetro','#');
          d.add(193,192,'Efetuar Instalação de Hidrômetro','exibirEfetuarInstalacaoHidrometroAction.do?menu=sim');
	      d.add(194,192,'Efetuar Retirada de Hidrômetro','exibirEfetuarRetiradaHidrometroAction.do?menu=sim');
	      d.add(195,192,'Efetuar Remanejamento de Hidrômetro','exibirEfetuarRemanejamentoHidrometroAction.do?menu=sim');
	      d.add(196,185,'Efetuar Religação de Água','exibirEfetuarReligacaoAguaAction.do?menu=sim');
	      d.add(197,185,'Efetuar Restabelecimento da Ligação de Água','exibirEfetuarRestabelecimentoLigacaoAguaAction.do?menu=sim');
	      d.add(198,188,'Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto','exibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction.do?menu=sim');		
	      d.add(199,188,'Atualizar Ligação de Esgoto','exibirAtualizarLigacaoEsgotoAction.do?menu=sim');			 
	      d.add(200,62,'Comando de Ação de Cobrança','#');	
   	      d.add(201,9,'Substituir Consumos Anteriores','exibirSubstituirConsumoAnteriorAction.do?menu=sim&peloMenu=true');
   	      d.add(202,62,'Parcelamento','#');	
   	      d.add(203,4,'Consultar Imóvel','exibirConsultarImovelAction.do?menu=sim');
   	      d.add(204,184,'Ordem de Serviço', '#');
		  d.add(205,204,'Inserir Equipe', 'exibirInserirEquipeAction.do?menu=sim');
	 	  d.add(206,184,'Registro Atendimento', '#');
		  d.add(207,206,'Manter Registro de Atendimento','exibirFiltrarRegistroAtendimentoAction.do?menu=sim');
		  d.add(208,185,'Atualizar Consumo Mínimo da Ligação de Água','exibirAtualizarConsumoMinimoLigacaoAguaAction.do?menu=sim');
		  d.add(209,204,'Manter Ordem de Serviço','exibirFiltrarOrdemServicoAction.do?menu=sim');
		  d.add(210,192,'Atualizar Instalação do Hidrômetro','exibirAtualizarInstalacaoHidrometroAction.do?menu=sim');
		  d.add(211,204,'Inserir Tipo Serviço','exibirInserirServicoTipoAction.do?menu=sim');	 	  		  
		  d.add(212,204,'Inserir Tipo de Retorno da Ordem de Serviço Referida','exibirInserirTipoRetornoOrdemServicoReferidaAction.do?menu=sim');
		  d.add(213,206,'Inserir Registro de Atendimento','exibirInserirRegistroAtendimentoAction.do?menu=sim');
		  d.add(214,204,'Inserir Tipo Perfil Serviço','exibirInserirTipoPerfilServicoAction.do?menu=sim');
		  d.add(215,206,'Inserir Tipo Solocitação Especificação','exibirInserirTipoSolicitacaoEspecificacaoAction.do?menu=sim');
		  d.add(216,192,'Efetuar Substituição de Hidrômetro','exibirEfetuarSubstituicaoHidrometroAction.do?menu=sim');		  
		  d.add(217,188,'Atualizar Volume Mínimo da Ligação de Esgoto','exibirAtualizarVolumeMinimoLigacaoEsgotoAction.do?menu=sim');		  
		  d.add(218,204,'Inserir Tipo de Serviço de Referência','exibirInserirTipoServicoReferenciaAction.do?menu=sim');
		  d.add(219,204,'Inserir Prioridade do Tipo de Serviço','exibirInserirPrioridadeTipoServicoAction.do?menu=sim'); 
		  d.add(220,185,'Efetuar Supressão da Ligação de Água', 'exibirEfetuarSupressaoLigacaoAguaAction.do?menu=sim');
		  d.add(221,204,'Inserir Material','exibirInserirMaterialAction.do?menu=sim');
		  d.add(222,185,'Efetuar Corte Administrativo da Ligação de Água', 'exibirEfetuarCorteAdministrativoLigacaoAguaAction.do?menu=sim');
		  d.add(223,204,'Inserir Valor de Cobrança de Serviço', 'exibirEfetuarValorCobrancaServicoAction.do?menu=sim');
		  d.add(224,185,'Atualizar Ligação de Água', 'exibirAtualizarLigacaoAguaAction.do?menu=sim');
  		  d.add(225,206,'Inserir Especificação Situação Imóvel', 'exibirInserirEspecificacaoSituacaoImovelAction.do?menu=sim');

		  d.draw();

     //-->     </script></div>
		</td>
	</tr>--%>
</table>



<%--
<menu:ddmenu x="10" y="63" name="menu" isVertical="false" selBckColor="#90c7fc" fntSize="2" fntface="Arial" selFntColor="#000000">

   <menu:ddmenu text="Tab. Auxiliares " selBckColor="#cbe5fe" colWidth="120">

      <menu:ddmenu text="Tipo Pavimento ?" rowHeight="25" colWidth="140">
        <menu:ddmenu text="Calçada" rowHeight="25">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarAction.do?tela=tipoPavimentoCalcada"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarAction.do?tela=tipoPavimentoCalcada"/>
        </menu:ddmenu>

        <menu:ddmenu text="Rua" rowHeight="25">
            <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarAction.do?tela=tipoPavimentoRua"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarAction.do?tela=tipoPavimentoRua"/>
        </menu:ddmenu>
      </menu:ddmenu>

      <menu:ddmenu text="Categoria ?" rowHeight="25" colWidth="140">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarAbreviadaAction.do?tela=categoria"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarAbreviadaAction.do?tela=categoria"/>
      </menu:ddmenu>

    <menu:ddmenu text="Área Construída ?" rowHeight="25" colWidth="140">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarFaixaAction.do?tela=areaConstruida"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarFaixaAction.do?tela=areaConstruida"/>
    </menu:ddmenu>

    <menu:ddmenu text="Bacia ?" rowHeight="25" colWidth="140">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarTipoAction.do?tela=bacia"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarTipoAction.do?tela=bacia"/>
    </menu:ddmenu>


   </menu:ddmenu>

   <menu:ddmenu text="Consulta" selBckColor="#cbe5fe">
      <menu:ddmenu text="Presença dos Técnicos" rowHeight="35" link="exibirTelaPresencaTecnicosAction.do"/>
   </menu:ddmenu>

   <menu:ddmenu text="Relatórios" selBckColor="#cbe5fe">
     <menu:ddmenu text="Controle de Horas" rowHeight="35" selBckColor="#cbe5fe" link="exibirFiltroRelatorioControleFrequenciaHoraTrabalhadaAction.do"/>
     <menu:ddmenu text="Horas Autorizadas" rowHeight="35" selBckColor="#cbe5fe" link="exibirFiltroRelatorioControleFrequenciaHoraAutorizadaAction.do"/>
   </menu:ddmenu>

   <menu:ddmenu text="Sair" selBckColor="#cbe5fe" link="sairAction.do" />

   <%--
   <menu:ddmenu text="Item2"  link="link2.htm" selBckColor="#cbe5fe">
      <menu:ddmenu text="Item21"  link="link21.htm" />
      <menu:ddmenu text="Item22"  link="link22.htm" />
   </menu:ddmenu>
   <menu:ddmenu text="Item2"  link="link2.htm" />

</menu:ddmenu>
--%>
