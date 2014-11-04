package gcom.relatorio;

import gcom.util.ControladorException;
import gcom.util.SistemaException;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface que contém os caminhos dos arquivos compilados dos relatórios
 * 
 * @author Sávio Luiz
 */
public class ConstantesRelatorios {

	public static final String RELATORIO_OPERACAO_MANTER = "/relatorioManterOperacao.jasper";

	public static final String RELATORIO_GRUPO_MANTER = "/relatorioManterGrupo.jasper";

	public static final String RELATORIO_MUNICIPIO_MANTER = "/relatorioManterMunicipio.jasper";

	public static final String RELATORIO_SITUACAO_COBRANCA_MANTER = "/relatorioManterCobrancaSituacao.jasper";

	public static final String RELATORIO_UNIDADE_ORGANIZACIONAL_MANTER = "/relatorioManterUnidadeOrganizacional.jasper";

	public static final String RELATORIO_USUARIO_TIPO_MANTER = "/relatorioManterUsuarioTipo.jasper";

	public static final String RELATORIO_LIGACAO_ESGOTO_ESGOTAMENTO_MANTER = "/relatorioManterLigacaoEsgotoEsgotamento.jasper";

	public static final String RELATORIO_FATURAMENTO_SITUACAO_TIPO_MANTER = "/relatorioManterFaturamentoSituacaoTipo.jasper";

	public static final String RELATORIO_DIVISAO_ESGOTO_MANTER = "/relatorioManterDivisaoEsgoto.jasper";

	public static final String RELATORIO_CARGO_FUNCIONARIO_MANTER = "/relatorioManterCargoFuncionario.jasper";

	public static final String RELATORIO_FONTE_ABASTECIMENTO_MANTER = "/relatorioManterFonteAbastecimento.jasper";

	public static final String RELATORIO_LOCAL_ARMAZENAGEM_HIDROMETRO_MANTER = "/relatorioManterLocalArmazenagemHidrometro.jasper";

	public static final String RELATORIO_EMPRESA_MANTER = "/relatorioManterEmpresa.jasper";

	public static final String RELATORIO_BACIA_MANTER = "/relatorioManterBacia.jasper";

	public static final String RELATORIO_SITUACAO_PAGAMENTO_MANTER = "/relatorioManterSituacaoPagamento.jasper";

	public static final String RELATORIO_BAIRRO_MANTER = "/relatorioManterBairro.jasper";

	public static final String RELATORIO_LOGRADOURO_MANTER = "/relatorioManterLogradouro.jasper";

	public static final String RELATORIO_LOCALIDADE_MANTER = "/relatorioManterLocalidade.jasper";

	public static final String RELATORIO_SETOR_COMERCIAL_MANTER = "/relatorioManterSetorComercial.jasper";

	public static final String RELATORIO_QUADRA_MANTER = "/relatorioManterQuadra1.jasper";

	public static final String RELATORIO_TARIFA_SOCIAL_MANTER = "/relatorioManterTipoCartaoTarifaSocial.jasper";

	public static final String RELATORIO_HIDROMETRO_MANTER = "/relatorioManterHidrometro.jasper";

	public static final String RELATORIO_CLIENTE_MANTER = "/relatorioManterCliente.jasper";

	public static final String RELATORIO_CLIENTE_OUTROS_CRITERIOS_MANTER = "/relatorioManterClienteOutrosCriterios.jasper";

	public static final String RELATORIO_IMOVEL_MANTER = "/relatorioManterImovel.jasper";

	public static final String RELATORIO_IMOVEL_ENDERECO = "/relatorioImovelEndereco.jasper";

	public static final String RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO = "/relatorioCadastroConsumidoresInscricao.jasper";

	public static final String RELATORIO_IMOVEL_OUTROS_CRITERIOS_MANTER = "/relatorioManterImovelOutrosCriterios.jasper";

	public static final String RELATORIO_DADOS_ECONOMIA_IMOVEL = "/relatorioManterDadosEconomiasImovel.jasper";

	public static final String RELATORIO_DADOS_TARIFA_SOCIAL = "/relatorioManterDadosTarifaSocial.jasper";

	public static final String RELATORIO_OPERACAO = "/relatorioOperacao.jasper";

	public static final String RELATORIO_OPERACAO_CONSULTAR = "/relatorioConsultarOperacao.jasper";

	public static final String RELATORIO_RESUMO_FATURAMENTO = "/relatorioResumoFaturamento.jasper";

	public static final String RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES = "/relatorioRegistrarLeiturasAnormalidades.jasper";

	public static final String RELATORIO_MOVIMENTO_DEBITO_AUTOMATICO_BANCO = "/relatorioMovimentoDebitoAutomaticoBanco.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_FATURAMENTO = "/relatorioAcompanhamentoFaturamento.jasper";

	public static final String RELATORIO_DEVOLUCAO = "/relatorioDevolucoes.jasper";

	public static final String RELATORIO_RESUMO_ARRECADACAO = "/relatorioResumoArrecadacao.jasper";

	public static final String RELATORIO_GERAR_RELACAO_DEBITOS = "/relatorioGerarRelacaoDebitosResumido.jasper";

	public static final String RELATORIO_RESUMO_ANORMALIDADE_LEITURA_ESTADO = "/relatorioResumoAnormalidadeLeituraEstado.jasper";

	public static final String RELATORIO_RESUMO_ANORMALIDADE_LEITURA_GERENCIA = "/relatorioResumoAnormalidadeLeituraGerenciaRegional.jasper";

	public static final String RELATORIO_RESUMO_ANORMALIDADE_LEITURA_ELO = "/relatorioResumoAnormalidadeLeituraElo.jasper";

	public static final String RELATORIO_RESUMO_ANORMALIDADE_LEITURA_LOCALIDADE = "/relatorioResumoAnormalidadeLeituraLocalidade.jasper";

	public static final String RELATORIO_RESUMO_ANORMALIDADE_LEITURA_SETOR = "/relatorioResumoAnormalidadeLeituraSetorComercial.jasper";

	public static final String RELATORIO_RESUMO_ANORMALIDADE_LEITURA_QUADRA = "/relatorioResumoAnormalidadeLeituraQuadra.jasper";

	public static final String RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL = "/relatorioResumoFaturamentoSituacaoEspecial.jasper";

	public static final String RELATORIO_RESUMO_COBRANCA_SITUACAO_ESPECIAL = "/relatorioResumoCobrancaSituacaoEspecial.jasper";

	public static final String RELATORIO_ANALISE_FATURAMENTO = "/relatorioAnaliseFaturamento.jasper";

	public static final String RELATORIO_PAGAMENTO = "/relatorioPagamentos.jasper";

	public static final String RELATORIO_PAGAMENTO_CONSULTAR_IMOVEL = "/relatorioPagamentosConsultarImovel.jasper";

	public static final String RELATORIO_PAGAMENTO_CONSULTAR_AVISO_BANCARIO = "/relatorioPagamentosConsultarAvisoBancario.jasper";

	public static final String RELATORIO_SUBCATEGORIA_MANTER = "/relatorioManterSubCategoria.jasper";

	public static final String RELATORIO_ROTA_MANTER = "/relatorioManterRota.jasper";

	public static final String RELATORIO_CATEGORIA_MANTER = "/relatorioManterCategoria.jasper";

	public static final String RELATORIO_CRONOGRAMA_FATURAMENTO_MANTER = "/relatorioManterCronogramaFaturamento.jasper";

	public static final String RELATORIO_MENSAGEM_CONTA_MANTER = "/relatorioManterMensagemConta.jasper";

	public static final String RELATORIO_CRONOGRAMA_COBRANCA_MANTER = "/relatorioManterCronogramaCobranca.jasper";

	// Relatorio de leitura da consulta de arquivos textos
	public static final String RELATORIO_LEITURA_CONSULTAR_ARQUIVO_TEXTOS = "/relatorioLeituraConsultarArquivosTextos.jasper";

	public static final String RELATORIO_RESOLUCAO_DIRETORIA_MANTER = "/relatorioManterResolucaoDiretoria.jasper";

	public static final String RELATORIO_CRITERIO_COBRANCA_MANTER = "/relatorioManterCriterioCobranca.jasper";

	public static final String RELATORIO_PERFIL_PARCELAMENTO_MANTER = "/relatorioManterPerfilParcelamento.jasper";

	public static final String RELATORIO_AVISO_BANCARIO_MANTER = "/relatorioManterAvisoBancario.jasper";

	public static final String RELATORIO_MOVIMENTO_ARRECADADOR_MANTER = "/relatorioManterMovimentoArrecadadores.jasper";

	public static final String RELATORIO_GUIA_DEVOLUCAO_MANTER = "/relatorioManterGuiaDevolucao.jasper";

	public static final String RELATORIO_EXTRATO_DEBITO = "/relatorioExtratoDebito.jasper";
	
	/**
	 * @author Adriana Muniz
	 * @date 02/12/2011
	 * 
	 * Criação da constante de relatório para a geração do relatório de debito
	 * */
	public static final String RELATORIO_DEBITO = "/relatorioDebito.jasper";
	
	public static final String RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO = "/relatorioConsultarRegistroAtendimento.jasper";

	public static final String RELATORIO_2_VIA_CONTA = "/relatorio2ViaConta.jasper";

	public static final String RELATORIO_2_VIA_CONTA_CAERN = "/relatorio2ViaContaCaern.jasper";

	public static final String RELATORIO_PARCELAMENTO = "/relatorioParcelamento.jasper";

	public static final String RELATORIO_PARCELAMENTO_CAER = "/relatorioParcelamentoDebitoCAER.jasper";

	public static final String RELATORIO_PARCELAMENTO_CAERN = "/relatorioParcelamentoCAERN.jasper";

	public static final String RELATORIO_PARCELAMENTO_CAEMA = "/relatorioParcelamentoCAEMA.jasper";

	public static final String RELATORIO_PARCELAMENTO_JUAZEIRO = "/relatorioParcelamentoJUAZEIRO.jasper";

	public static final String RELATORIO_PARCELAMENTO_COSANPA = "/relatorioParcelamentoCosanpa.jasper";

	public static final String RELATORIO_GUIA_PAGAMENTO_EMITIR = "/relatorioEmitirGuiaPagamento.jasper";

	public static final String RELATORIO_GUIA_DEVOLUCAO = "/relatorioGuiaDevolucao.jasper";

	public static final String RELATORIO_ROTEIRO_PROGRAMACAO = "/relatorioRoteiroProgramacao.jasper";

	public static final String RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE = "/relatorioConsultarRegistroAtendimentoViaCliente.jasper";

	public static final String RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE_COSANPA = "/relatorioConsultarRegistroAtendimentoViaClienteCosanpa.jasper";

	public static final String RELATORIO_ORDEM_SERVICO = "/relatorioOrdemServico.jasper";

	public static final String RELATORIO_ORDEM_SERVICO_MODELO_2 = "/relatorioOrdemServicoModelo2.jasper";

	public static final String RELATORIO_ORDEM_SERVICO_CAERN = "/relatorioOrdemServicoCAERN.jasper";

	public static final String RELATORIO_NUMERACAO_RA_MANUAL = "/relatorioGerarNumeracaoRAManual.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS = "/relatorioAcompanhamentoExecucaoOS.jasper";

	public static final String RELATORIO_MOVIMENTO_ARRECADADOR = "/relatorioRegistrarMovimentoArrecadadores.jasper";

	public static final String RELATORIO_FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA = "/relatorioFaturamentoLigacoesMedicaoIndividualizada.jasper";

	public static final String RELATORIO_CONTAS_EMITIDAS = "/relatorioContasEmitidas.jasper";

	public static final String RELATORIO_MAPA_CONTROLE_CONTA = "/relatorioMapaControleConta.jasper";

	public static final String RELATORIO_MEDICAO_CONSUMO_LIGACAO_AGUA = "/relatorioMedicaoConsumoLigacaoAgua.jasper";

	public static final String RELATORIO_RESUMO_CONTA_LOCALIDADE = "/relatorioResumoContaLocalidade.jasper";

	public static final String RELATORIO_RESUMO_IMOVEL_MICROMEDICAO = "/relatorioResumoImovelMicromedicao.jasper";

	public static final String RELATORIO_2_VIA_CONTA_TIPO_2 = "/relatorio2ViaContaTipo2.jasper";

	public static final String RELATORIO_CONSULTAR_DEBITOS = "/relatorioConsultarDebitos.jasper";

	public static final String RELATORIO_CONSULTAR_DEBITOS_RESUMIDO = "/relatorioConsultarDebitosResumido.jasper";

	public static final String RELATORIO_CONSULTAR_DEBITOS_CLIENTE = "/relatorioConsultarDebitosCliente.jasper";

	public static final String RELATORIO_CONSULTAR_DEBITOS_CLIENTE_ENDERECO = "/relatorioConsultarDebitosClienteEndereco.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES = "/relatorioAcompanhamentoMovimentoArrecadadores.jasper";

	public static final String RELATORIO_COMPARATIVOS_LEITURAS_E_ANORMALIDADES = "/relatorioComparativoLeiturasEAnormalidades.jasper";

	public static final String RELATORIO_EXTRATO_DEBITO_CLIENTE = "/relatorioExtratoDebitoCliente.jasper";

	public static final String RELATORIO_CONTRATO_PRESTACAO_SERVICO = "/relatorioContratoPrestacaoServico.jasper";

	public static final String RELATORIO_CONTRATO_PRESTACAO_SERVICO_JURIDICO = "/relatorioContratoPrestacaoServicoJuridico.jasper";

	public static final String RELATORIO_CONSUMO_TARIFA_MANTER = "/relatorioManterConsumoTarifa.jasper";

	public static final String RELATORIO_ANALITICO_FATURAMENTO = "/relatorioAnaliticoFaturamento.jasper";

	public static final String RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA = "/relatorioEmitirProtocoloDocumentoCobranca.jasper";

	public static final String RELATORIO_CLIENTES_ESPECIAIS = "/relatorioClientesEspeciais.jasper";

	public static final String RELATORIO_RELACAO_PARCELAMENTO = "/relatorioRelacaoParcelamento.jasper";

	public static final String RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO = "/relatorioHistogramaAguaLigacao.jasper";
	
	public static final String RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO_SUBCATEGORIA = "/relatorioHistogramaAguaLigacaoSubcategoria.jasper";

	public static final String RELATORIO_FAIXAS_FALSAS_LEITURA = "/relatorioFaixasFalsasLeitura.jasper";

	public static final String RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA = "/relatorioHistogramaAguaEconomia.jasper";

	public static final String RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA_SUBCATEGORIA = "/relatorioHistogramaAguaEconomiaSubcategoria.jasper";
	
	public static final String RELATORIO_ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA = "/relatorioAcompanhamentoMovimentoArrecadadoresPorNSA.jasper";

	public static final String RELATORIO_CONTA_TIPO_2 = "/relatorioContaTipo2.jasper";

	public static final String RELATORIO_RESUMO_DEVEDORES_DUVIDOSOS = "/relatorioResumoDevedoresDuvidosos.jasper";

	public static final String REAVISO_DE_DEBITO = "/relatorioReavisoDeDebito.jasper";

	public static final String RELATORIO_ORDEM_CORTE = "/relatorioOrdemCorte.jasper";

	public static final String RELATORIO_FATURA_CLIENTE_RESPONSAVEL = "/relatorioFaturaClienteResponsavel.jasper";

	public static final String RELATORIO_RELACAO_ANALITICA_FATURAS = "/relatorioRelacaoAnaliticaFaturas.jasper";

	public static final String RELATORIO_GERAR_DADOS_PARA_LEITURA = "/relatorioGerarDadosParaLeitura.jasper";

	public static final String BOLETIM_CADASTRO = "boletimCadastro";

	public static final String RELATORIO_VOLUMES_FATURADOS = "/relatorioVolumesFaturados.jasper";

	public static final String RELATORIO_VOLUMES_FATURADOS_RESUMIDO = "/relatorioVolumesFaturadosResumido.jasper";

	public static final String RELATORIO_CONTAS_EM_REVISAO = "/relatorioContasRevisao.jasper";

	public static final String RELATORIO_CONTAS_EM_REVISAO_RESUMIDO = "/relatorioContasRevisaoResumido.jasper";

	public static final String RELATORIO_ANORMALIDADE_CONSUMO = "/relatorioAnormalidadeConsumoELeitura.jasper";

	public static final String RELATORIO_PADRAO_BATCH = "/relatorioPadraoBatch.jasper";

	public static final String RELATORIO_GERAR_INDICE_ACRESCIMOS_IMPONTUALIDADE = "/relatorioGerarIndicesAcrescimosImpontualidade.jasper";

	public static final String RELATORIO_GERAR_CURVA_ABC_DEBITOS = "/relatorioGerarCurvaABCDebitos.jasper";

	public static final String RELATORIO_GERAR_QUALIDADE_AGUA = "/relatorioQualidadeAgua.jasper";

	// Adicionado para atender necessidade da CAERN
	public static final String RELATORIO_GERAR_QUALIDADE_AGUA_CAERN = "/relatorioQualidadeAguaCAERN.jasper";

	public static final String RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_LIGACAO = "/relatorioHistogramaEsgotoLigacao.jasper";

	public static final String RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_ECONOMIA = "/relatorioHistogramaEsgotoEconomia.jasper";

	public static final String RELATORIO_ANALISE_CONSUMO = "/relatorioAnaliseConsumo.jasper";

	public static final String RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA = "/relatorioEmitirOrdemServicoSeletiva.jasper";

	public static final String RELATORIO_RELACAO_SINTETICA_FATURAS = "/relatorioRelacaoSinteticaFaturas.jasper";

	public static final String RELATORIO_ORCAMENTO_SINP = "/relatorioOrcamentoSINP.jasper";

	public static final String RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA = "/relatorioImoveisSituacaoLigacaoAgua.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_LOCALIZACAO = "/relatorioImoveisFaturasAtrasoAgrupadasLocalizacao.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE = "/relatorioImoveisFaturasAtrasoAgrupadasCliente.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO = "/relatorioImoveisFaturasAtrasoDescritasLocalizacao.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_CLIENTE = "/relatorioImoveisFaturasAtrasoDescritasCliente.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_ATRASO = "/relatorioImoveisFaturasAtraso.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITA = "/relatorioImoveisFaturasAtrasoDescritas.jasper";

	public static final String RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_PENDENTES = "/relatorioRelacaoOrdensServicoEncerradasPendentes.jasper";

	public static final String RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_MOTIVO_ENCERRAMENTO = "relatorioRelacaoOrdensServicoEncerradasPorMotivoEncerramento.jasper";
	
	public static final String RELATORIO_IMOVEIS_CONSUMO_MEDIO = "/relatorioImoveisConsumoMedio.jasper";

	public static final String RELATORIO_IMOVEIS_ULTIMOS_CONSUMOS_AGUA = "/relatorioImoveisUltimosConsumosAgua.jasper";

	public static final String RELATORIO_ORDEM_FISCALIZACAO = "/relatorioOrdemFiscalizacao.jasper";

	public static final String RELATORIO_EVOLUCAO_CONTAS_A_RECEBER_CONTABIL = "/relatorioEvolucaoContasAReceberContabil.jasper";

	public static final String RELATORIO_QUADRO_METAS_ACUMULADO = "/relatorioQuadroMetasAcumulado.jasper";

	public static final String RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO = "/relatorioEmitirOrdemServicoSeletivaSugestao.jasper";

	public static final String RELATORIO_RESUMO_ORDENS_SERVICO_ENCERRADAS_PENDENTES = "/relatorioResumoOrdensServicoEncerradasPendentes.jasper";

	public static final String RELATORIO_IMOVEIS_ATIVOS_NAO_MEDIDOS = "/relatorioImoveisAtivosNaoMedidos.jasper";

	public static final String RELATORIO_IMOVEIS_TIPO_CONSUMO = "/relatorioImoveisTipoConsumo.jasper";

	public static final String RELATORIO_IMOVEIS_FATURAS_RECENTES_DIA_FATURAS_ANTIGAS_ATRASO = "/relatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso.jasper";

	public static final String RELATORIO_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO = "/relatorioComparativoFaturamentoArrecadacaoExpurgo.jasper";

	public static final String RELATORIO_SALDO_CONTAS_A_RECEBER_CONTABIL = "/relatorioSaldoContasAReceberContabil.jasper";

	public static final String RELATORIO_BOLETIM_CADASTRO = "/relatorioBoletimCadastro.jasper";

	public static final String RELATORIO_ATUALIZAR_LEITURAS_ANORMALIDADES_CELULAR = "/relatorioAtualizarLeiturasAnormalidadesCelular.jasper";

	public static final String RELATORIO_RESUMO_PROBLEMAS_LEITURAS_ANORMALIDADES_CELULAR = "/relatorioResumoLeiturasAnormalidadesProblemas.jasper";

	public static final String RELATORIO_CERTIDAO_NEGATIVA = "/relatorioCertidaoNegativa.jasper";

	public static final String RELATORIO_CERTIDAO_NEGATIVA_CAEMA = "/relatorioCertidaoNegativaCaema.jasper";

	public static final String RELATORIO_CERTIDAO_NEGATIVA_COM_EFEITO_POSITIVO = "/relatorioCertidaoNegativaComEfeitoPositivo.jasper";

	public static final String RELATORIO_CERTIDAO_NEGATIVA_CLIENTE = "/relatorioCertidaoNegativaCliente.jasper";

	public static final String RELATORIO_MANTER_NEGATIVADOR = "/relatorioManterNegativador.jasper";

	public static final String RELATORIO_MANTER_NEGATIVADOR_REGISTRO_TIPO = "/relatorioManterNegativadorRegistroTipo.jasper";

	public static final String RELATORIO_MANTER_NEGATIVADOR_RETORNO_MOTIVO = "/relatorioManterNegativadorRetornoMotivo.jasper";

	public static final String RELATORIO_MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO = "/relatorioManterNegativadorExclusaoMotivo.jasper";

	public static final String RELATORIO_MANTER_NEGATIVADOR_CONTRATO = "/relatorioManterNegativadorContrato.jasper";

	public static final String RELATORIO_QUADRO_METAS_EXERCICIO = "/relatorioQuadroMetasExercicio.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS = "/relatorioAcompanhamentoClientesNegativados.jasper";

	public static final String RELATORIO_NEGATIVACOES_EXCLUIDAS = "/relatorioNegativacoesExcluidas.jasper";

	public static final String RELATORIO_RELACAO_ORDENS_SERVICO_CONCLUIDAS = "/relatorioRelacaoOrdensServicoConcluidas.jasper";

	public static final String RELATORIO_USUARIO_MANTER = "/relatorioManterUsuario.jasper";

	public static final String RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE = "/relatorioContasBaixadasContabilmente.jasper";

	public static final String RELATORIO_CONTAS_CANCELADAS = "/relatorioContasCanceladas.jasper";

	public static final String RELATORIO_CONTAS_RETIFICADAS = "/relatorioContasRetificadas.jasper";

	public static final String RELATORIO_CONTAS_CANCELADAS_SINTETICO = "/relatorioContasCanceladasSintetico.jasper";

	public static final String RELATORIO_CONTAS_RETIFICADAS_SINTETICO = "/relatorioContasRetificadasSintetico.jasper";

	public static final String RELATORIO_AVISO_ANORMALIDADE = "/relatorioAvisoAnormalidade.jasper";

	public static final String RELATORIO_GERAR_DADOS_LEITURA = "/relatorioGerarDadosLeituraRetrato.jasper";

	public static final String RELATORIO_NEGATIVADOR_RESULTADO_SIMULACAO = "/relatorioNegativadorResultadoSimulacao.jasper";

	public static final String RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO = "/relatorioRelacaoServicosAcompanhamentoRepavimentacao.jasper";

	public static final String RELATORIO_BOLETIM_ORDENS_SERVICO_CONCLUIDAS = "/relatorioBoletimOrdensServicoConcluidas.jasper";

	public static final String RELATORIO_MANTER_MOTIVO_CORTE = "/relatorioManterMotivoCorte.jasper";

	public static final String RELATORIO_MANTER_ARRECADACAO_FORMA = "/relatorioManterArrecadacaoForma.jasper";

	public static final String RELATORIO_MANTER_ATIVIDADE = "/relatorioManterAtividade.jasper";

	public static final String RELATORIO_MANTER_LIGACAO_AGUA_SITUACAO = "/relatorioManterLigacaoAguaSituacao.jasper";

	public static final String RELATORIO_MANTER_HIDROMETRO_DIAMETRO = "/relatorioManterHidrometroDiametro.jasper";

	public static final String RELATORIO_MANTER_ZONA_PRESSAO = "/relatorioManterZonaPressao.jasper";

	public static final String RELATORIO_MANTER_FATURAMENTO_GRUPO = "/relatorioManterFaturamentoGrupo.jasper";

	public static final String RELATORIO_MANTER_ANORMALIDADE_CONSUMO = "/relatorioManterAnormalidadeConsumo.jasper";

	public static final String RELATORIO_MANTER_PRODUCAO_AGUA = "/relatorioManterProducaoAgua.jasper";

	public static final String RELATORIO_MANTER_ALTERACAO_TIPO = "/relatorioManterAlteracaoTipo.jasper";

	public static final String RELATORIO_FATURAS_AGRUPADAS = "/relatorioFaturasAgrupadas.jasper";

	public static final String RELATORIO_FATURAS_AGRUPADAS_SINTETICO = "/relatorioFaturasAgrupadasSintetico.jasper";

	public static final String RELATORIO_PROTOCOLO_ENTREGA_FATURA = "/relatorioProtocoloEntregaFatura.jasper";

	public static final String RELATORIO_MANTER_SISTEMA_ESGOTO_TRATAMENTO_TIPO = "/relatorioManterSistemaEsgotoTratamentoTipo.jasper";

	public static final String RELATORIO_GESTAO_SERVICOS_UPA = "/relatorioGestaoServicosUPA.jasper";

	public static final String RELATORIO_RESUMO_SOLICITACOES_RA_POR_UNIDADE = "/relatorioResumoSolicitacoesRAPorUnidade.jasper";

	public static final String RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS = "/relatorioVolumesConsumidosNaoFaturados.jasper";

	public static final String RELATORIO_ANALISE_ARRECADACAO = "/relatorioAnaliseArrecadacao.jasper";

	public static final String RELATORIO_PARAMETROS_CONTABEIS_FATURAMENTO = "/relatorioParametrosContabeisFaturamento.jasper";

	public static final String RELATORIO_PARAMETROS_CONTABEIS_ARRECADACAO = "/relatorioParametrosContabeisArrecadacao.jasper";

	public static final String RELATORIO_ANALISE_AVISOS_BANCARIOS = "/relatorioAnaliseAvisosBancarios.jasper";

	public static final String RELATORIO_TRANSFERENCIAS_CONSULTAR = "/relatorioConsultarTransferencias.jasper";

	public static final String RELATORIO_POSICAO_FATURAMENTO = "/relatorioPosicaoFaturamento.jasper";

	public static final String RELATORIO_AUTO_INFRACAO = "/relatorioAutoInfracao.jasper";

	public static final String RELATORIO_AVISO_BANCARIO_POR_CONTA_CORRENTE = "/relatorioAvisoBancarioPorContaCorrente.jasper";

	public static final String RELATORIO_CONSULTAR_MOVIMENTACAO_HIDROMETRO = "/relatorioConsultarMovimentacaoHidrometro.jasper";

	public static final String RELATORIO_GESTAO_SOLICITACOES_RA_POR_CHEFIA = "/relatorioGestaoSolicitacoesRAPorChefia.jasper";

	public static final String RELATORIO_FILTRAR_REGISTRO_ATENDIMENTO = "/relatorioFiltrarRegistroAtendimento.jasper";

	public static final String RELATORIO_FILTRAR_ORDEM_SERVICO = "/relatorioFiltrarOrdemServico.jasper";

	public static final String RELATORIO_PERFIL_LIGACAO_ESGOTO_MANTER = "/relatorioManterPerfilLigacaoEsgoto.jasper";

	public static final String RELATORIO_ROTAS_ONLINE_POR_EMPRESA = "/relatorioRotasOnlinePorEmpresa.jasper";

	public static final String RELATORIO_TIPO_DEBITO_MANTER = "/relatorioManterTipoDebito.jasper";

	public static final String RELATORIO_TIPO_PERFIL_SERVICO_MANTER = "/relatorioManterTipoPerfilServico.jasper";

	public static final String RELATORIO_MANTER_MOVIMENTO_ARRECADADORES_ITENS = "/relatorioManterMovimentoArrecadadoresItens.jasper";

	public static final String RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA = "/relatorioPagamentosContasCobrancaEmpresa.jasper";

	public static final String RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA_SINTETICO = "/relatorioPagamentosContasCobrancaEmpresaSintetico.jasper";

	public static final String RELATORIO_ANALISE_IMOVEL_CORPORATIVO_GRANDE = "/relatorioAnaliseImovelCorporativoGrande.jasper";

	public static final String RELATORIO_GUIA_PAGAMENTO_EM_ATRASO = "/relatorioGuiaPagamentoEmAtraso.jasper";

	public static final String RELATORIO_RELACAO_PARCELAMENTO_ANALITICO = "/relatorioRelacaoParcelamentoAnalitico.jasper";

	public static final String RELATORIO_MANTER_SERVICO_TIPO = "/relatorioManterServicoTipo.jasper";

	public static final String RELATORIO_IMOVEIS_COM_ACORDO = "/relatorioImoveisComAcordo.jasper";

	public static final String RELATORIO_RESUMO_DISTRITO_OPERACIONAL = "/relatorioResumoDistritoOperacional.jasper";

	public static final String RELATORIO_RESUMO_ZONA_ABASTECIMENTO = "/relatorioResumoZonaAbastecimento.jasper";

	public static final String RELATORIO_AUTOS_INFRACAO_MANTER = "/relatorioManterAutosInfracao.jasper";

	public static final String RELATORIO_UNIDADE_NEGOCIO_MANTER = "/relatorioManterUnidadeNegocio.jasper";

	public static final String RELATORIO_BOLETIM_CUSTO_ATUALIZACAO_CADASTRAL = "/relatorioBoletimCustoAtualizacaoCadastral.jasper";

	public static final String RELATORIO_FILTRAR_REGISTRO_ATENDIMENTO_COORDENADAS_SEM_LOGRADOURO = "/relatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro.jasper";

	public static final String RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL = "/relatorioRelacaoImpostosPorClienteResponsavel.jasper";

	public static final String RELATORIO_ERROS_MOVIMENTOS_CONTA_PRE_FATURADAS = "relatorioErrosMovimentosContaPreFaturadas.jasper";

	public static final String RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA = "relatorioResumoLeiturasAnormalidadesRegistradas.jasper";

	public static final String RELATORIO_MANTER_DEBITO_AUTOMATICO = "/relatorioManterDebitoAutomatico.jasper";

	public static final String RELATORIO_MANTER_CONTRATO_ARRECADADOR = "/relatorioManterContratoArrecadador.jasper";

	public static final String RELATORIO_MANTER_AGENCIA_BANCARIA = "/relatorioManterAgenciaBancaria.jasper";

	public static final String RELATORIO_MANTER_ARRECADADOR = "/relatorioManterArrecadador.jasper";

	public static final String RELATORIO_CONTAS = "/relatorioConta2.jasper";

	public static final String RELATORIO_NOTIFICACAO_DEBITO = "/relatorioNotificacaoDebito.jasper";

	public static final String RELATORIO_NOTIFICACAO_DEBITO_COSANPA = "/relatorioNotificacaoDebitoCosanpa.jasper";

	public static final String RELATORIO_ANALISAR_METAS_CICLO = "/relatorioAnalisarMetasCiclo.jasper";

	public static final String RELATORIO_COBRANCA_GRUPO_MANTER = "/relatorioManterCobrancaGrupo.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_ACOES_COBRANCA = "/relatorioAcompanhamentoAcoesCobranca.jasper";

	public static final String RELATORIO_FILTRAR_DOCUMENTO_COBRANCA = "/relatorioFiltrarDocumentoCobranca.jasper";

	public static final String RELATORIO_SUPRESSOES_RELIGACOES_REESTABELECIMENTOS = "/relatorioSupressoesReligacoesReestabelecimentos.jasper";

	public static final String RELATORIO_IMOVEIS_RELACIONADOS_CLIENTE = "/relatorioImoveisRelacionadosCliente.jasper";

	public static final String RELATORIO_CLIENTES_RELACIONADOS_IMOVEL = "/relatorioClientesRelacionadosImovel.jasper";

	public static final String RELATORIO_PROJETO_MANTER = "/relatorioProjetoManter.jasper";

	public static final String RELATORIO_DADOS_CADASTRAIS_IMOVEL = "/relatorioDadosCadastraisImovel.jasper";

	public static final String RELATORIO_DADOS_COMPLEMENTARES_IMOVEL = "/relatorioDadosComplementaresImovel.jasper";

	public static final String RELATORIO_DADOS_ANALISE_MEDICAO_CONSUMO_IMOVEL = "/relatorioDadosAnaliseMedicaoConsumoImovel.jasper";

	public static final String RELATORIO_HISTORICO_FATURAMENTO_IMOVEL = "/relatorioHistoricoFaturamentoImovel.jasper";

	public static final String RELATORIO_COMANDO_DOCUMENTO_COBRANCA_COSANPA = "/relatorioComandoDocumentoCobrancaCosanpa.jasper";

	public static final String RELATORIO_HISTORICO_MEDICAO_POCO = "/relatorioHistoricoMedicaoPoco.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_LEITURISTA = "/relatorioAcompanhamentoLeiturista.jasper";

	public static final String RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS = "/relatorioJurosMultasDebitosCancelados.jasper";

	public static final String RELATORIO_DEBITO_COBRADO_CONTA = "/relatorioValorDebitoConsultarImovel.jasper";

	public static final String RELATORIO_COMANDO_DOCUMENTO_COBRANCA = "/relatorioComandoDocumentoCobranca.jasper";

	public static final String RELATORIO_ACRESCIMOS_POR_IMPONTUALIDADE = "/relatorioAcrescimoPorImpontualidade.jasper";

	public static final String RELATORIO_SISTEMA_ABASTECIMENTO_MANTER = "/relatorioManterSistemaAbastecimento.jasper";

	public static final String RELATORIO_ANORMALIDADE_LEITURA_PERIODO = "/relatorioAnormalidadeLeituraPeriodo.jasper";

	public static final String RELATORIO_COMANDOS_ACAO_COBRANCA_CRONOGRAMA = "/relatorioComandosAcaoCobrancaCronograma.jasper";

	public static final String RELATORIO_COMANDOS_ACAO_COBRANCA_EVENTUAL = "/relatorioComandosAcaoCobrancaEventual.jasper";

	public static final String RELATORIO_FATURAS_AGRUPADAS_COSANPA = "/relatorioFaturasAgrupadasCosanpa.jasper";

	public static final String RELATORIO_OPERACAO_EFETUADA = "/relatorioOperacaoEfetuada.jasper";

	public static final String RELATORIO_RAMO_ATIVIDADE_MANTER = "/relatorioManterRamoAtividade.jasper";

	public static final String RELATORIO_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA_SINTETICO = "/relatorioMotivoNaoGeracaoDocumentoCobrancaSintetico.jasper";

	public static final String RELATORIO_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA_ANALITICO = "/relatorioMotivoNaoGeracaoDocumentoCobrancaAnalitico.jasper";

	public static final String RELATORIO_IMOVEL_PROGRAMA_ESPECIAL = "/relatorioManterImovelProgramaEspecial.jasper";

	public static final String RELATORIO_MANTER_LEITURISTA = "/relatorioManterLeiturista.jasper";

	public static final String RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO = "/relatorioPagamentoEntidadesBeneficentesAnalitico.jasper";

	public static final String RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_SINTETICO = "/relatorioPagamentoEntidadesBeneficentesSintetico.jasper";

	public static final String RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO = "/relatorioImoveisProgrmasEspeciaisAnalitico.jasper";

	public static final String RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_SINTETICO = "/relatorioImoveisProgrmasEspeciaisSintetico.jasper";

	public static final String RELATORIO_RESUMO_RECEITA_SINTETICO = "/relatorioResumoReceitaSintetico.jasper";

	public static final String RELATORIO_RESUMO_RECEITA_ANALITICO = "/relatorioResumoReceitaAnalitico.jasper";

	public static final String RELATORIO_ORDEM_FISCALIZACAO_ONLINE = "/relatorioOrdemFiscalizacaoOnline.jasper";

	public static final String RELATORIO_DECLARACAO_TRANSFERENCIA_DEBITO_CREDITO = "/relatorioEmitirDeclaracaoTransferenciaDebitoCredito.jasper";

	public static final String RELATORIO_DOCUMENTOS_A_RECEBER = "/relatorioDocumentosAReceber.jasper";

	public static final String RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_ANALITICO = "/relatorioEmitirOrdemServicoSeletivaAnalitico.jasper";

	public static final String RELATORIO_COLETA_MEDIDOR_ENERGIA = "/relatorioColetaMedidorEnergia.jasper";

	public static final String RELATORIO_EMITIR_BOLETIM_CADASTRO_INDIVIDUAL = "/relatorioEmitirBoletimCadastroIndividual.jasper";

	public static final String RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO = "/relatorioResumoLigacoesCapacidadeHidrometro.jasper";

	public static final String RELATORIO_RESUMO_ACOES_COBRANCA_EVENTUAIS = "/relatorioResumoAcoesCobrancaEventuais.jasper";

	public static final String RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS = "/relatorioDeclaracaoQuitacaoAnualDebitos.jasper";

	public static final String RELATORIO_DEMONSTRATIVO_SINTETICO_LIGACOES = "/relatorioDemonstrativoSinteticoLigacoes.jasper";

	public static final String RELATORIO_RESUMO_DADOS_CAS = "/relatorioResumoDadosCas.jasper";

	public static final String RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE = "/relatorioRelacaoOrdemRepavimentacaoProcessoAceite.jasper";

	public static final String RELATORIO_NOTIFICACAO_DEBITOS_IMPRESSAO_SIMULTANEA = "/relatorioNotificacaoDebitosImpressaoSimultanea.jasper";

	public static final String RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS_CAERN = "/relatorioDeclaracaoQuitacaoAnualDebitosCAERN.jasper";

	public static final String RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS_CAEMA = "/relatorioDeclaracaoQuitacaoAnualDebitosCAEMA.jasper";

	public static final String RELATORIO_CERTIDAO_NEGATIVA_CLIENTE_CAEMA = "/relatorioCertidaoNegativaClienteCaema.jasper";

	public static final String RELATORIO_RELACAO_PARCELAMENTO_CARTAO_CREDITO = "/relatorioRelacaoParcelamentoCartaoCredito.jasper";

	public static final String RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO = "/relatorioFuncionalidadeOperacaoPorGrupo.jasper";

	public static final String RELATORIO_ACESSOS_POR_USUARIO = "/relatorioAcessosPorUsuario.jasper";

	public static final String RELATORIO_ANALISE_PAGAMENTO_CARTAO_DEBITO = "/relatorioAnalisePagamentoCartaoDebito.jasper";

	public static final String RELATORIO_MANTER_ITEM_SERVICO = "/relatorioManterItemServico.jasper";

	public static final String RELATORIO_ORDEM_CORTE_ONLINE = "/relatorioOrdemCorteOnline.jasper";

	public static final String RELATORIO_MANTER_RETORNO_CONTROLE_HIDROMETRO = "/relatorioManterRetornoControleHidrometro.jasper";

	public static final String RELATORIO_BOLETIM_MEDICAO = "/relatorioBoletimMedicao.jasper";

	public static final String RELATORIO_VAZIO = "/relatorioVazio.jasper";

	public static final String RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM = "/relatorioAnormalidadeConsumoELeituraPorAmostragem.jasper";

	public static final String RELATORIO_NOTIFICACAO_DEBITO_COSANPA_2_PAGINAS = "/relatorioNotificacaoDebitoCosanpa2Paginas.jasper";

	public static final String RELATORIO_NOTIFICACAO_DEBITO_COSANPA_MINI = "/relatorioNotificacaoDebitoCosanpaMini.jasper";

	public static final String RELATORIO_LOGRADOURO_POR_MUNICIPIO = "/relatorioLogradouroPorMunicipio.jasper";

	public static final String RELATORIO_EXTRATO_DEBITO_BOLETO_BANCARIO = "/relatorioExtratoDebitoBoletoBancario.jasper";

	public static final String RELATORIO_EXTRATO_DEBITO_CLIENTE_BOLETO_BANCARIO = "/relatorioExtratoDebitoClienteBoletoBancario.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_ANALITICO = "/relatorioFiltrarAcompanhamentoRegistroAtendimentoAnalitico.jasper";

	public static final String RELATORIO_ALTERACOES_SISTEMA_COLUNA_USUARIO = "/relatorioAlteracoesSistemaColunaPorUsuario.jasper";

	public static final String RELATORIO_ALTERACOES_SISTEMA_COLUNA_LOCALIDADE = "/relatorioAlteracoesSistemaColunaPorLocalidade.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ENCERRADO = "/relatorioFiltrarAcompanhamentoRegistroAtendimentoSinteticoEncerrado.jasper";

	public static final String RELATORIO_MONITORAR_LEITURA_MOBILE = "/relatorioMonitorarLeituraMobile.jasper";

	public static final String RELATORIO_LEITURAS_TELEMETRIA = "/relatorioLeiturasTelemetria.jasper";

	public static final String RELATORIO_ATUALIZACAO_CADASTRAL_VIA_INTERNET = "/relatorioAtualizacaoCadastralViaInternet.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ABERTO = "/relatorioFiltrarAcompanhamentoRegistroAtendimentoSinteticoAberto.jasper";

	public static final String RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET = "/relatorioResumoAtualizacaoCadastralViaInternet.jasper";

	public static final String RELATORIO_MANTER_CONSUMO_ANORMALIDADE_ACAO = "/relatorioManterConsumoAnormalidadeAcao.jasper";

	public static final String RELATORIO_MANTER_IMOVEL_PERFIL = "/relatorioManterImovelPerfil.jasper";
	
	public static final String RELATORIO_REGISTRO_ATENDIMENTO_POR_UNIDADE_POR_USUARIO = "/relatorioResumoRAPorUnidadePorUsuario.jasper";

	public static final String RELATORIO_CONSULTA_CADASTRO_CDL = "/relatorioResultadoPesquisaConsultaCadastroCdl.jasper";

	public static final String RELATORIO_MANTER_SOLICITACAO_ACESSO_SITUACAO = "/relatorioManterSolicitacaoAcessoSituacao.jasper";
	

	public static final String RELATORIO_SOLICITACAO_ACESSO = "/relatorioSolicitacaoAcesso.jasper";
	
	public static final String RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO_NOVO = "/relatorioRelacaoServicosAcompanhamentoRepavimentacaoNovo.jasper";
	
	public static final String RELATORIO_BOLETIM_CUSTO_PAVIMENTO = "/relatorioBoletimCustoPavimento.jasper";
	
	public static final String RELATORIO_CUSTO_PAVIMENTO_MANTER = "/relatorioCustoPavimentoManter.jasper";
	
	public static final String RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH = "/relatorioImoveisAlteracaoInscricaoViaBatch.jasper";
	
	public static final String RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO = "/relatorioAcompanhamentoClientesNegativadosSintetico.jasper";
	
	public static final String RELATORIO_VISITA_COBRANCA = "/relatorioVisitaCobranca.jasper";
	
	public static final String RELATORIO_MANTER_CONTRATO_PARCELAMENTO_RD = "/relatorioManterContratoParcelamentoRD.jasper";
	
	public static final String RELATORIO_DOCUMENTO_COBRANCA_ORDEM_CORTE = "/relatorioDocumentoCobrancaOrdemCorte.jasper";
	
	public static final String RELATORIO_DOCUMENTO_COBRANCA_ORDEM_FISCALIZACAO = "/relatorioDocumentoCobrancaOrdemFiscalizacao.jasper";
	
	public static final String RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_LIGACAO_SUBCATEGORA = "/relatorioHistogramaEsgotoLigacaoSubcategoria.jasper";
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_GERENCIA = "/relatorioDadosDiariosArrecadacaoGerencia.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_ARRECADADOR = "/relatorioDadosDiariosArrecadacaoArrecadador.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_CATEGORIA = "/relatorioDadosDiariosArrecadacaoCategoria.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_PERFIL = "/relatorioDadosDiariosArrecadacaoPerfil.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_AGREGADOR = "/relatorioDadosDiariosArrecadacaoDocumentoAgregador.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_PARAMETROS = "/relatorioDadosDiariosArrecadacaoParametros.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_UNIDADE_NEGOCIO = "/relatorioDadosDiariosUnidadeNegocio.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_ELO = "/relatorioDadosDiariosElo.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_LOCALIDADE = "/relatorioDadosDiariosLocalidade.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_VALORES_DIARIOS = "/relatorioDadosDiariosValoresDiarios.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_AGENTE = "/relatorioDadosDiariosAgente.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_ARRECADACAO_FORMA = "/relatorioDadosDiariosArrecadacaoForma.jasper";
	
	public static final String RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_AGREGADOR_POPUP = "/relatorioDadosDiariosDocumentoAgregador.jasper";
	
	public static final String RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES = "/relatorioReligacaoClientesInadiplentes.jasper";
	
	public static final String RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_2 = "/relatorioReligacaoClientesInadiplentes2.jasper";

	public static final String RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_3 = "/relatorioReligacaoClientesInadiplentes3.jasper";
	
	public static final String RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_4 = "/relatorioReligacaoClientesInadiplentes4.jasper";
	
	public static final String RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_5 = "/relatorioReligacaoClientesInadiplentes5.jasper";
	
	public static final String RELATORIO_ALTERACOES_CPF_CNPJ_USUARIO = "/relatorioAlteracoesCpfCnpjUsuario.jasper";
	
	public static final String RELATORIO_ALTERACOES_CPF_CNPJ_LOCALIDADE = "/relatorioAlteracoesCpfCnpjLocalidade.jasper";
	
	public static final String RELATORIO_ALTERACOES_CPF_CNPJ_MEIO = "/relatorioAlteracoesCpfCnpjMeio.jasper";

	public static final String RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT = "RelatorioBoletimMedicaoArquivoTxt";
	
	public static final String RELATORIO_DEVOLUCAO_PAGAMENTO_DUPLICIDADE = "/relatorioDevolucaoPagamentosDuplicidade.jasper";

	public static final String RELATORIO_BOLETIM_MEDICAO_COBRANCA = "/relatorioBoletimMedicaoCobranca.jasper";
	
	public static final String RELATORIO_RELEITURA_IMOVEIS = "/relatorioReleituraImoveis.jasper";
	
	public static final String RELATORIO_ANALISE_PERDAS_CREDITOS = "/relatorioAnalisePerdasCreditos.jasper";
	
	public static final String RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL = "/relatorioResumoQtdeImoveisExcluidosTarifaSocial.jasper";

	public static final String RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL_TIPO_2 = "/relatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2.jasper";

	public static final String RELATORIO_GERENCIA_REGIONAL_MANTER = "/relatorioManterGerenciaRegional.jasper";
	
	public static final String RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO = "/relatorioOSExecutadasPrestadoraServico.jasper";
	
	public static final String RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO_SINTETICO = "/relatorioOSExecutadasPrestadoraServicoSintetico.jasper";
	
	public static final String RELATORIO_ACESSO_SPC = "/relatorioAcessoSPC.jasper";

	public static final String RELATORIO_EMITIR_COMPROVANTE_CONTRATO_PARCELAMENTO = "/relatorioEmitirComprovantePagamentoContratoParcelamento.jasper";
	
	public static final String RELATORIO_IMOVEIS_DOACOES_IMOVEL = "/relatorioImoveisDoacoesImovel.jasper";
	
	public static final String RELATORIO_IMOVEIS_DOACOES_ENTIDADE = "/relatorioImoveisDoacoesEntidade.jasper";
	
	public static final String RELATORIO_DOCUMENTO_VISITA_COBRANCA = "/relatorioDocumentoVisitaCobranca.jasper";
	
	public static final String RELATORIO_OS_FISCALIZACAO = "/relatorioOSFiscalizacao.jasper";
	
	public static final String RELATORIO_IMOVEIS_COM_LEITURAS_QUANTITATIVOS = "/relatorioImoveisComLeiturasQuantitativos.jasper";
	
	public static final String RELATORIO_IMOVEIS_COM_LEITURAS_RELACAO = "/relatorioImoveisComLeiturasRelacao.jasper";
	
	public static final String RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_7 = "/relatorioImoveisComLeiturasTipo7.jasper";

	public static final String RELATORIO_EMITIR_CONTRATO_PARCELAMENTO_POR_CLIENTE = "/relatorioEmitirContratoParcelamentoPorCliente.jasper";

	public static final String RELATORIO_MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE = "/relatorioManterContratoParcelamentoPorCliente.jasper";

	public static final String RELATORIO_OS_SITUACAO_ANALITICO = "/relatorioOSSituacao.jasper";

	public static final String RELATORIO_OS_SITUACAO_SINTETICO = "/relatorioOSSituacaoSintetico.jasper";

	public static final String RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO = "/relatorioAcompanhamentoBoletimMedicao.jasper";
	
	public static final String RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO = "/relatorioGerarRelatorioOSAcompanhamentoCobrancaResultado.jasper";
	
	public static final String RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL = "/relatorioDocumentosParcelamentoLojaVirtual.jasper";
	
    /**
	 * 
	 * Pamela Gatinho - 08/09/2011
	 */
	public static final String RELATORIO_LEITURAS_REALIZADAS = "/relatorioLeiturasRealizadas.jasper";

	public static final String RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL = "/relatorioEstruturaTarifariaLojaVirtual.jasper";

	public static final String RELATORIO_MULTAS_AUTOS_INFRACAO_PENTENTES = "/relatorioMultasAutosInfracaoPendentes.jasper";

    /**
	 * 
	 * Pamela Gatinho - 15/09/2011
	 */
	public static final String RELATORIO_CONTAS_RETIDAS = "/relatorioContasRetidas.jasper";

	/**
	 * 
	 * Pamela Gatinho - 22/09/2011
	 */
	public static final String RELATORIO_MEDICAO_FATURAMENTO = "/relatorioMedicaoFaturamento.jasper";

	public static final String RELATORIO_EMITIR_EXTRATO_CONTRATO_PARCELAMENTO_CLIENTE = "/relatorioEmitirExtratoContratoParcelamentoCliente.jasper";

	public static final String RELATORIO_DOCUMENTOS_NAO_ACEITOS = "/relatorioDocumentosNaoAceitos.jasper";

	public static final String RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO = "/relatorioOrdensServicoFiscalizacaoAnalitico.jasper";
	
	public static final String RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO = "/relatorioOrdensServicoFiscalizacaoSintetico.jasper";
	
	public static final String RELATORIO_TRANSFERENCIA_PAGAMENTO = "/relatorioTransferenciaPagamento.jasper";
	
	public static final String RELATORIO_BIG = "/relatorioBIG.jasper";

	public static final String RELATORIO_CONSULTA_ATUALIZACAO_CADASTRAL = "/relatorioConsultaAtualizacaoCadastral.jasper";
	
	public static final String RELATORIO_IMOVEIS_SITUACAO_PERIODO = "/relatorioImoveisSituacaoPeriodo.jasper";

	public static final String RELATORIO_INCONSISTENCIAS_RETORNO_ATUALIZACAO_CADASTRAL = "/relatorioInconsistenciasRetornoAtualizacaoCadastral.jasper";
	
	public static final String RELATORIO_FICHA_FISCALIZACAO_CADASTRAL = "/relatorioFichaFiscalizacaoCadastral.jasper";
	
	public static final String RELATORIO_RECEITAS_A_FATURAR_SINTETICO = "/relatorioReceitasAFaturarSintetico.jasper";
	
	public static final String RELATORIO_RECEITAS_A_FATURAR_ANALITICO = "/relatorioReceitasAFaturarAnalitico.jasper";
	
	public static final String RELATORIO_RELACAO_IMOVEIS_ROTA = "/relatorioRelacaoImoveisRota.jasper";

	private static Map<String, URL> relatorios = new HashMap<String, URL>();
	
	static {

		Field[] campos = ConstantesRelatorios.class.getFields();

		for (int i = 0; i < campos.length; i++) {
			Field campo = campos[i];
			try {
				if (!campo.getName().equals("relatorios")) {
					inicializarPropriedades((String) campo.get(null));
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new SistemaException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new SistemaException();
			}

		}

	}

	/**
	 * Inicializa as propriedades da classes ConstantesJNDI
	 */

	public static void inicializarPropriedades(String nomeArquivo) {

		URL url;

		try {

			ClassLoader classLoader = ClassLoader.getSystemClassLoader();

			url = classLoader.getResource(nomeArquivo);

			// if system class loader not found try the this class classLoader

			if (url == null) {
				url = ConstantesRelatorios.class.getClassLoader().getResource(
						nomeArquivo);
			}

			if (url == null) {
				url = ConstantesRelatorios.class.getResource(nomeArquivo);
			}

			relatorios.put(nomeArquivo, url);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public static URL getURLRelatorio(String nomeRelatorio) {
		return relatorios.get(nomeRelatorio);

	}
}
