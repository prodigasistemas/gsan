package gcom.gui;

public interface Funcionalidade {
	// *** Caminhos das funcionalidades de sistema ***//
	// TABELAAUXILIAR
	String TABELA_AUXILIAR_INSERIR = "exibirInserirTabelaAuxiliarAction.do";

	String TABELA_AUXILIAR_MANTER = "exibirManterTabelaAuxiliarAction.do";
	
	String TABELA_AUXILIAR_FILTRAR = "exibirFiltrarTabelaAuxiliarAction.do";

	// TABELAAUXILIARABREVIADA
	String TABELA_AUXILIAR_ABREVIADA_INSERIR = "exibirInserirTabelaAuxiliarAbreviadaAction.do";

	String TABELA_AUXILIAR_ABREVIADA_MANTER = "exibirManterTabelaAuxiliarAbreviadaAction.do";
	
	String TABELA_AUXILIAR_ABREVIADA_FILTRAR = "exibirFiltrarTabelaAuxiliarAbreviadaAction.do";
	
	

	// TABELAAUXILIARFAIXA
	String TABELA_AUXILIAR_FAIXA_INSERIR = "exibirInserirTabelaAuxiliarFaixaAction.do";

	String TABELA_AUXILIAR_FAIXA_FILTRAR = "exibirFiltrarTabelaAuxiliarAction.do";
	
	String TABELA_AUXILIAR_FAIXA_MANTER = "exibirManterTabelaAuxiliarFaixaAction.do";
	
	//TABELAAULIARFAIXAREAL
	
	String TABELA_AUXILIAR_FAIXA_REAL_INSERIR = "exibirInserirTabelaAuxiliarFaixaRealAction.do";

	String TABELA_AUXILIAR_FAIXA_REAL_MANTER = "exibirManterTabelaAuxiliarFaixaRealAction.do";
	
	String TABELA_AUXILIAR_FAIXA_REAL_FILTRAR = "exibirFiltrarTabelaAuxiliarFaixaRealAction.do";
	
	
	//TABELAAULIARINDICADOR
	
	String TABELA_AUXILIAR_INDICADOR_INSERIR = "exibirInserirTabelaAuxiliarIndicadorAction.do";

	String TABELA_AUXILIAR_INDICADOR_MANTER = "exibirManterTabelaAuxiliarIndicadorAction.do";
	
	String TABELA_AUXILIAR_INDICADOR_FILTRAR = "exibirFiltrarTabelaAuxiliarIndicadorAction.do";
	

	// TABELAAUXILIARTIPO
	String TABELA_AUXILIAR_TIPO_INSERIR = "exibirInserirTabelaAuxiliarTipoAction.do";

	String TABELA_AUXILIAR_TIPO_MANTER = "exibirManterTabelaAuxiliarTipoAction.do";
	
	String TABELA_AUXILIAR_TIPO_FILTRAR = "exibirFiltrarTabelaAuxiliarTipoAction.do";
	
	//TABELAAULIARFAIXAREAL
	
	String TABELA_AUXILIAR_FAIXA_INTEIRO_INSERIR = "exibirInserirTabelaAuxiliarFaixaInteiroAction.do";

	String TABELA_AUXILIAR_FAIXA_INTEIRO_MANTER = "exibirManterTabelaAuxiliarFaixaInteiroAction.do";
	
	String TABELA_AUXILIAR_FAIXA_INTEIRO_FILTRAR = "exibirFiltrarTabelaAuxiliarFaixaInteiroAction.do";
	

	// *** Parametros passados no get das telas (objetos) do sistema ***//
	// TABELAAUXILIAR
	
	String TELA_OPERACAO = "?tela=operacao";
	
	String TELA_TIPO_PAVIMENTO_CALCADA = "?tela=tipoPavimentoCalcada";

	String TELA_TIPO_PAVIMENTO_RUA = "?tela=tipoPavimentoRua";

	String TELA_LOCALIDADE_CLASSE = "?tela=localidadeClasse";

	String TELA_LOCALIDADE_PORTE = "?tela=localidadePorte";

	String TELA_FONTE_DADO_CENSITARIO = "?tela=fonteDadoCensitario";

	String TELA_SETOR_CENSITARIO = "?tela=setorCensitario";

	String TELA_CEP_TIPO = "?tela=cepTipo";

	String TELA_COBRANCA_SITUACAO = "?tela=cobrancaSituacao";

	String TELA_IMOVEL_PERFIL = "?tela=imovelPerfil";

	String TELA_LIGACAO_ESGOTO_SITUACAO = "?tela=ligacaoEsgotoSituacao";

	String TELA_LIGACAO_AGUA_SITUACAO = "?tela=ligacaoAguaSituacao";

	String TELA_NOME_CONTA = "?tela=nomeConta";

	String TELA_LOGRADOURO_TITULO = "?tela=logradouroTitulo";

	String TELA_ENDERECO_TIPO = "?tela=enderecoTipo";

	String TELA_FONE_TIPO = "?tela=foneTipo";

	String TELA_PESSOA_SEXO = "?tela=pessoaSexo";

	String TELA_RENDA_TIPO = "?tela=rendaTipo";

	String TELA_TARIFA_SOCIAL_EXCLUSAO_MOTIVO = "?tela=tarifaSocialExclusaoMotivo";

	String TELA_CLIENTE_IMOVEL_TIPO = "?tela=clienteImovelTipo";

	String TELA_USUARIO_ACAO = "?tela=usuarioAcao";

	String TELA_USUARIO_TIPO = "?tela=usuarioTipo";

	String TELA_LIGACAO_ESGOTO_PERFIL = "?tela=ligacaoEsgotoPerfil";

	String TELA_LIGACAO_ESGOTO_MATERIAL = "?tela=ligacaoEsgotoMaterial";

	String TELA_LIGACAO_ESGOTO_DIAMETRO = "?tela=ligacaoEsgotoDiametro";

	String TELA_LIGACAO_AGUA_PERFIL = "?tela=ligacaoAguaPerfil";

	String TELA_LIGACAO_AGUA_DIAMETRO = "?tela=ligacaoAguaDiametro";

	String TELA_LIGACAO_AGUA_MATERIAL = "?tela=ligacaoAguaMaterial";

	String TELA_EMISSAO_ORDEM_COBRANCA_TIPO = "?tela=emissaoOrdemCobrancaTipo";

	String TELA_CONSUMO_TIPO = "?tela=consumoTipo";

	String TELA_CORTE_TIPO = "?tela=corteTipo";

	String TELA_SUPRESSAO_TIPO = "?tela=supressaoTipo";

	String TELA_SUPRESSAO_PARCIAL_TIPO = "?tela=supressaoParcialTipo";

	String TELA_COBRANCA_PARALISACAO_TIPO = "?tela=cobrancaParalisacaoTipo";

	String TELA_COBRANCA_PARALISACAO_MOTIVO = "?tela=cobrancaParalisacaoMotivo";

	String TELA_FATURAMENTO_PARALISACAO_MOTIVO = "?tela=faturamentoParalisacaoMotivo";

	String TELA_POCO = "?tela=poco";

	String TELA_ELO_ANORMALIDADE = "?tela=eloAnormalidade";

	String TELA_RAMO_ATIVIDADE = "?tela=ramoAtividade";

	String TELA_PROFISSAO = "?tela=profissao";

	String TELA_REGIAO = "?tela=regiao";

	String TELA_REGIAO_DESENVOLVIMENTO = "?tela=regiaoDesenvolvimento";

	String TELA_LEITURA_TIPO = "?tela=leituraTipo";

	String TELA_CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO = "?tela=clienteImovelFimRelacaoMotivo";
	
	String TELA_IBGE_SETOR_CENSITARIO= "?tela=ibgeSetorCensitario";
	
	String TELA_TIPO_HABITACAO = "?tela=imovelTipoHabitacao";
	
	String TELA_TIPO_PROPRIEDADE = "?tela=imovelTipoPropriedade";
	
	String TELA_TIPO_CONSTRUCAO = "?tela=imovelTipoConstrucao";
	
	String TELA_TIPO_COBERTURA = "?tela=imovelTipoCobertura";
	
	String TELA_HIDROMETRO_RELOJOARIA = "?tela=hidrometroRelojoaria";
	
	String TELA_LIGACAO_ESGOTO_DESTINO_DEJETOS = "?tela=ligacaoEsgotoDestinoDejetos";
	
	String TELA_LIGACAO_ESGOTO_ESGOTAMENTO = "?tela=ligacaoEsgotoEsgotamento";

	String TELA_LIGACAO_ESGOTO_CAIXA_INSPECAO = "?tela=ligacaoEsgotoCaixaInspecao";

	String TELA_LIGACAO_ESGOTO_DESTINO_AGUAS_PLUVIAIS = "?tela=ligacaoEsgotoDestinoAguasPluviais";
	
	String TELA_ESGOTO_TRATAMENTO_TIPO = "?tela=sistemaEsgotoTratamentoTipo";
	
	String TELA_REGIAO_INTEGRACAO = "?tela=regiaoIntegracao";
	
	// TABELAAUXILIARABREVIADA
	String TELA_CATEGORIA = "?tela=categoria";

	String TELA_ZEIS = "?tela=zeis";

	String TELA_FATURAMENTO_GRUPO = "?tela=faturamentoGrupo";

	String TELA_SISTEMA_ABASTECIMENTO = "?tela=sistemaAbastecimento";

	String TELA_ORGAO_EXPEDITOR_RG = "?tela=orgaoExpeditorRg";

	String TELA_UNIDADE_FEDERACAO = "?tela=unidadeFederacao";

	String TELA_DESPEJO = "?tela=despejo";

	String TELA_EMPRESA = "?tela=empresa";

	String TELA_ENDERECO_IMOVEL_REFERENCIA = "?tela=enderecoImovelReferencia";

	String TELA_EQUIPAMENTOS_ESPECIAIS = "?tela=equipamentosEspeciais&menu=sim";
	
	String TELA_TABELA = "?tela=tabela";
	
	String TELA_BANCO = "?tela=banco";
	
	String TELA_ALTERACAO_TIPO = "?tela=alteracaoTipo";
	
	String TELA_GRUPO = "?tela=grupo";
	
	String TELA_LIGACAO_ORIGEM = "?tela=ligacaoOrigem";
	
	String TELA_HIDROMETRO_LOCAL_ARMAZENAGEM = "?tela=hidrometroLocalArmazenagem";
	
	String TELA_LOGRADOURO_TIPO = "?tela=logradouroTipo";
	
	// TABELAAUXILIARAREACONSTRUIDA
	String TELA_AREA_CONSTRUIDA = "?tela=areaConstruida";

	// TABELAAUXILIABACIA
	String TELA_BACIA = "?tela=bacia";
	
	//TABELAAUXILIARUNIDADE
	String TELA_MATERIAL = "?tela=material";
	
	// TABELAAUXILIARFAIXAREAL
	
	String TELA_PISCINA_VOLUME_FAIXA = "?tela=piscinaVolumeFaixa";
	
	String TELA_RESERVATORIO_VOLUME_FAIXA = "?tela=reservatorioVolumeFaixa";
	
	// TABELAAUXILIARINDICADOR
	
	String TELA_QUADRA_PERFIL = "?tela=quadraPerfil";

	//TABELA AUXILIAR FAIXA INTEIRO
	
	String TELA_AREA_CONSTRUIDA_FAIXA = "?tela=areaConstruidaFaixa";
	
	String TELA_FUNCIONALIDADE = "?tela=funcionalidade";

	// TABELA AUXILIAR ABREVIADA TIPO
	
	String TELA_SETOR_ABASTECIMENTO = "?tela=setorAbastecimento";
	
	String TELA_ZONA_ABASTECIMENTO = "?tela=zonaAbastecimento";
	
	String TELA_FONTE_CAPTACAO = "?tela=fonteCaptacao";
	
	String TABELA_AUXILIAR_ABREVIADA_TIPO_MANTER = "exibirManterTabelaAuxiliarAbreviadaTipoAction.do";
	
	String TABELA_AUXILIAR_ABREVIADA_TIPO_INSERIR = "exibirInserirTabelaAuxiliarAbreviadaTipoAction.do";

	String TABELA_AUXILIAR_ABREVIADA_TIPO_FILTRAR = "exibirFiltrarTabelaAuxiliarAbreviadaTipoAction.do";
	
	String TELA_TIPO_CAPTACAO = "?tela=tipoCaptacao";
	
	String TELA_CONTA_MOTIVO_RETIFICACAO = "?tela=contaMotivoRetificacao";
	
}
