package gcom.batch;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.registroatendimento.FiltroRaEncerramentoComando;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gcom.atualizacaocadastral.TarefaBatchAtualizacaoCadastral;
import gcom.batch.arrecadacao.TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado;
import gcom.batch.arrecadacao.TarefaBatchCancelarGuiasPagamentoNaoPagas;
import gcom.batch.arrecadacao.TarefaBatchClassificarPagamentosDevolucoes;
import gcom.batch.arrecadacao.TarefaBatchEncerrarArrecadacaoMes;
import gcom.batch.arrecadacao.TarefaBatchGerarDadosDiariosArrecadacao;
import gcom.batch.arrecadacao.TarefaBatchGerarDadosRelatorioBIG;
import gcom.batch.arrecadacao.TarefaBatchGerarHistoricoConta;
import gcom.batch.arrecadacao.TarefaBatchGerarHistoricoParaEncerrarArrecadacaoMes;
import gcom.batch.arrecadacao.TarefaBatchInserirPagamentosFaturasEspeciais;
import gcom.batch.arrecadacao.TarefaBatchProcessarPagamentosComDiferencaDoisReais;
import gcom.batch.atendimentopublico.TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido;
import gcom.batch.atendimentopublico.TarefaBatchEmitirOrdemDeFiscalizacao;
import gcom.batch.atendimentopublico.TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade;
import gcom.batch.atendimentopublico.TarefaBatchExecutarComandoEncerramentoRA;
import gcom.batch.atendimentopublico.TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB;
import gcom.batch.atendimentopublico.TarefaBatchGerarDadosArquivoAcompanhamentoServico;
import gcom.batch.atendimentopublico.TarefaBatchGerarTxtOsInspecaoAnormalidade;
import gcom.batch.atendimentopublico.TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo;
import gcom.batch.atendimentopublico.TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS;
import gcom.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gcom.batch.cadastro.TarefaBatchAtualizarCodigoDebitoAutomatico;
import gcom.batch.cadastro.TarefaBatchEmitirBoletimCadastro;
import gcom.batch.cadastro.TarefaBatchEmitirBoletos;
import gcom.batch.cadastro.TarefaBatchExcluirImoveisDaTarifaSocial;
import gcom.batch.cadastro.TarefaBatchGerarArquivoTextoAtualizacaoCadastral;
import gcom.batch.cadastro.TarefaBatchGerarCartaTarifaSocial;
import gcom.batch.cadastro.TarefaBatchGerarResumoLigacoesEconomias;
import gcom.batch.cadastro.TarefaBatchGerarResumoLigacoesEconomiasPorAno;
import gcom.batch.cadastro.TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral;
import gcom.batch.cadastro.TarefaBatchProcessarComandoGerado;
import gcom.batch.cadastro.TarefaBatchRetirarImovelTarifaSocial;
import gcom.batch.cadastro.TarefaBatchSuspenderImovelEmProgramaEspecial;
import gcom.batch.cobranca.TarefaBatchAtualizarComandoAtividadeAcaoCobranca;
import gcom.batch.cobranca.TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga;
import gcom.batch.cobranca.TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista;
import gcom.batch.cobranca.TarefaBatchEmitirCartasDeFinalDeAno;
import gcom.batch.cobranca.TarefaBatchEmitirDocumentoCobranca;
import gcom.batch.cobranca.TarefaBatchGerarArquivoTextoContasCobrancaEmpresa;
import gcom.batch.cobranca.TarefaBatchGerarAtividadeAcaoCobranca;
import gcom.batch.cobranca.TarefaBatchGerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista;
import gcom.batch.cobranca.TarefaBatchGerarCartasDeFinalDeAno;
import gcom.batch.cobranca.TarefaBatchGerarMovimentoContasCobrancaPorEmpresa;
import gcom.batch.cobranca.TarefaBatchGerarPrescreverDebitosDeImoveis;
import gcom.batch.cobranca.TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa;
import gcom.batch.cobranca.TarefaBatchGerarResumoAcoesCobrancaCronograma;
import gcom.batch.cobranca.TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS;
import gcom.batch.cobranca.TarefaBatchGerarResumoAcoesCobrancaEventual;
import gcom.batch.cobranca.TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga;
import gcom.batch.cobranca.TarefaBatchInserirResumoAcoesCobrancaCronograma;
import gcom.batch.cobranca.TarefaBatchInserirResumoAcoesCobrancaEventual;
import gcom.batch.cobranca.TarefaBatchProcessarEncerramentoOSAcaoCobranca;
import gcom.batch.cobranca.cobrancaporresultado.TarefaBatchAtualizarPagamentosContasCobranca;
import gcom.batch.cobranca.cobrancaporresultado.TarefaBatchEncerrarComandosDeCobrancaPorEmpresa;
import gcom.batch.cobranca.cobrancaporresultado.TarefaBatchGerarArquivoTextoPagamentosContasCobrancaEmpresa;
import gcom.batch.cobranca.cobrancaporresultado.TarefaBatchGerarNegociacaoContasCobrancaEmpresa;
import gcom.batch.cobranca.cobrancaporresultado.TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca;
import gcom.batch.cobranca.spcserasa.TarefaBatchAcompanharPagamentoDoParcelamento;
import gcom.batch.cobranca.spcserasa.TarefaBatchAtualizarNumeroExecucaoResumoNegativacao;
import gcom.batch.cobranca.spcserasa.TarefaBatchDeterminarConfirmacaoDaNegativacao;
import gcom.batch.cobranca.spcserasa.TarefaBatchExecutarComandoNegativacao;
import gcom.batch.cobranca.spcserasa.TarefaBatchGerarMovimentoExclusaoNegativacao;
import gcom.batch.cobranca.spcserasa.TarefaBatchGerarMovimentoRetornoNegativacao;
import gcom.batch.cobranca.spcserasa.TarefaBatchGerarResumoDiarioNegativacao;
import gcom.batch.cobranca.spcserasa.TarefaBatchGerarResumoNegativacao;
import gcom.batch.faturamento.TarefaBatchAlterarInscricaoImovel;
import gcom.batch.faturamento.TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus;
import gcom.batch.faturamento.TarefaBatchAutomatizarPerfisDeGrandesConsumidores;
import gcom.batch.faturamento.TarefaBatchEmitirContas;
import gcom.batch.faturamento.TarefaBatchEmitirContasOrgaoPublico;
import gcom.batch.faturamento.TarefaBatchEmitirExtratoConsumoImovelCondominio;
import gcom.batch.faturamento.TarefaBatchEncerrarFaturamentoMes;
import gcom.batch.faturamento.TarefaBatchEnvioEmailContaParaCliente;
import gcom.batch.faturamento.TarefaBatchFaturarGrupoFaturamento;
import gcom.batch.faturamento.TarefaBatchGerarArquivoTextoContasProjetosEspeciais;
import gcom.batch.faturamento.TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos;
import gcom.batch.faturamento.TarefaBatchGerarBonusTarifaSocial;
import gcom.batch.faturamento.TarefaBatchGerarCreditoSituacaoEspecialFaturamento;
import gcom.batch.faturamento.TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos;
import gcom.batch.faturamento.TarefaBatchGerarDadosReceitasAFaturarResumo;
import gcom.batch.faturamento.TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade;
import gcom.batch.faturamento.TarefaBatchGerarDebitosACobrarDoacao;
import gcom.batch.faturamento.TarefaBatchGerarFaturaClienteResponsavel;
import gcom.batch.faturamento.TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes;
import gcom.batch.faturamento.TarefaBatchGerarResumoFaturamento;
import gcom.batch.faturamento.TarefaBatchGerarResumoFaturamentoAguaEsgoto;
import gcom.batch.faturamento.TarefaBatchGerarResumoFaturamentoPorAno;
import gcom.batch.faturamento.TarefaBatchGerarResumoReFaturamento;
import gcom.batch.faturamento.TarefaBatchGerarResumoReFaturamentoNovo;
import gcom.batch.faturamento.TarefaBatchGerarResumoReFaturamentoOlap;
import gcom.batch.faturamento.TarefaBatchGerarResumoSimulacaoFaturamento;
import gcom.batch.faturamento.TarefaBatchGerarResumoSituacaoEspecialFaturamento;
import gcom.batch.faturamento.TarefaBatchGerarTaxaEntregaContaOutroEndereco;
import gcom.batch.faturamento.TarefaBatchGerarTaxaPercentualTarifaMinimaCortado;
import gcom.batch.faturamento.TarefaBatchGerarTxtImpressaoContasBraille;
import gcom.batch.faturamento.TarefaBatchPrescreverDebitosImoveisPublicosAutomatico;
import gcom.batch.faturamento.TarefaBatchPrescreverDebitosImoveisPublicosManual;
import gcom.batch.faturamento.TarefaBatchReligarImoveisCortadosComConsumoReal;
import gcom.batch.faturamento.TarefaBatchRetificarConjuntoContaConsumos;
import gcom.batch.faturamento.TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10;
import gcom.batch.faturamento.TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado;
import gcom.batch.faturamento.TarefaBatchVerificarFaturamentoImoveisCortados;
import gcom.batch.financeiro.TarefaBatchApagarResumoDevedoresDuvidosos;
import gcom.batch.financeiro.TarefaBatchAtualizarResumoDevedoresDuvidosos;
import gcom.batch.financeiro.TarefaBatchGerarContasAReceberContabil;
import gcom.batch.financeiro.TarefaBatchGerarLancamentosContabeisArrecadacao;
import gcom.batch.financeiro.TarefaBatchGerarLancamentosContabeisAvisosBancarios;
import gcom.batch.financeiro.TarefaBatchGerarLancamentosContabeisDevedoresDuvidosos;
import gcom.batch.financeiro.TarefaBatchGerarLancamentosContabeisFaturamento;
import gcom.batch.financeiro.TarefaBatchGerarResumoDevedoresDuvidosos;
import gcom.batch.financeiro.TarefaBatchGerarResumoDocumentosAReceber;
import gcom.batch.financeiro.TarefaBatchGerarResumoReceita;
import gcom.batch.financeiro.TarefaBatchGerarValorVolumesConsumidosNaoFaturados;
import gcom.batch.financeiro.TarefaBatchRelatorioContasBaixadasContabilmente;
import gcom.batch.gerencial.arrecadacao.TarefaBatchGerarResumoArrecadacao;
import gcom.batch.gerencial.arrecadacao.TarefaBatchGerarResumoArrecadacaoPorAno;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoColetaEsgoto;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoColetaEsgotoPorAno;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoConsumoAgua;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoConsumoAguaPorAno;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoHistogramaAguaEsgoto;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoIndicadoresComercializacao;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoMetas;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoMetasAcumulado;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoParcelamento;
import gcom.batch.gerencial.cadastro.TarefaBatchGerarResumoParcelamentoPorAno;
import gcom.batch.gerencial.cobranca.TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia;
import gcom.batch.gerencial.cobranca.TarefaBatchGerarResumoIndicadoresCobranca;
import gcom.batch.gerencial.cobranca.TarefaBatchGerarResumoPendencia;
import gcom.batch.gerencial.cobranca.TarefaBatchGerarResumoPendenciaPorAno;
import gcom.batch.gerencial.cobranca.TarefaBatchGerarResumoSituacaoEspecialCobranca;
import gcom.batch.gerencial.faturamento.TarefaBatchGerarResumoIndicadoresFaturamento;
import gcom.batch.gerencial.micromedicao.TarefaBatchGerarResumoAnormalidades;
import gcom.batch.gerencial.micromedicao.TarefaBatchGerarResumoIndicadoresMicromedicao;
import gcom.batch.gerencial.micromedicao.TarefaBatchGerarResumoIndicadoresMicromedicaoPorAno;
import gcom.batch.gerencial.micromedicao.TarefaBatchGerarResumoInstalacoesHidrometros;
import gcom.batch.gerencial.micromedicao.TarefaBatchGerarResumoInstalacoesHidrometrosPorAno;
import gcom.batch.gerencial.micromedicao.TarefaBatchGerarResumoLeituraAnormalidade;
import gcom.batch.micromedicao.TarefaBatchConsistirLeiturasCalcularConsumos;
import gcom.batch.micromedicao.TarefaBatchEfetuarRateioConsumo;
import gcom.batch.micromedicao.TarefaBatchGerarArquivoTextoParaLeiturista;
import gcom.batch.micromedicao.TarefaBatchGerarDadosParaLeitura;
import gcom.batch.micromedicao.TarefaBatchGerarMovimentoHidrometro;
import gcom.batch.micromedicao.TarefaBatchGerarRAOSAnormalidadeConsumo;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.empresa.FiltroEmpresaCobranca;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.ControladorLocalidadeLocal;
import gcom.cadastro.localidade.ControladorLocalidadeLocalHome;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroComandoEmpresaCobrancaConta;
import gcom.cobranca.FiltroComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.cobranca.TarefaBatchGerarArquivoTextoOSContasPagasParceladas;
import gcom.cobranca.TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.financeiro.ControladorFinanceiroLocal;
import gcom.financeiro.ControladorFinanceiroLocalHome;
import gcom.gerencial.atendimentopublico.registroatendimento.TarefaBatchGerarResumoRegistroAtendimento;
import gcom.gerencial.atendimentopublico.registroatendimento.TarefaBatchGerarResumoRegistroAtendimentoPorAno;
import gcom.gerencial.faturamento.ControladorGerencialFaturamentoLocal;
import gcom.gerencial.faturamento.ControladorGerencialFaturamentoLocalHome;
import gcom.gerencial.micromedicao.ControladorGerencialMicromedicaoLocal;
import gcom.gerencial.micromedicao.ControladorGerencialMicromedicaoLocalHome;
import gcom.gerencial.micromedicao.TarefaBatchGerarResumoHidrometro;
import gcom.integracao.ControladorIntegracaoLocal;
import gcom.integracao.ControladorIntegracaoLocalHome;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.MovimentoHidrometroHelper;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.TarefaBatchAtualizarConjuntoHidrometro;
import gcom.relatorio.GerenciadorExecucaoTarefaRelatorio;
import gcom.seguranca.FiltroSegurancaParametro;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ControladorBatchSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	SessionContext sessionContext;

	private IRepositorioBatch repositorioBatch = null;

	private IRepositorioMicromedicao repositorioMicromedicao = null;

	public void ejbCreate() throws CreateException {
		repositorioBatch = RepositorioBatchHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
	}

	public void ejbRemove() {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Insere um processo iniciado no sistema e suas funcionalidades iniciadas
	 * 
	 * @param processoIniciado
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer inserirProcessoIniciado(ProcessoIniciado processoIniciado) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();

		Integer anoMesFaturamentoSistemaParametro = sistemaParametros.getAnoMesFaturamento();

		Integer anoMesArrecadacaoSistemaParametro = sistemaParametros.getAnoMesArrecadacao();

		Collection<Integer> colecaoIdsLocalidadesEncerrarArrecadacaoMes = getControladorArrecadacao().pesquisarIdsLocalidadeComPagamentosOuDevolucoes();

		try {
			// Todos os processo serao iniciados com a situacao EM_ESPERA para q sejam executados o mais cedo possivel
			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processoIniciado.getProcesso().getId());
			processoSituacao.setId(processoSituacaoId);
			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade relacionados com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();
			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso().getId()));
			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				try {
					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {
	
						case Funcionalidade.GERAR_RESUMO_DIARIO_NEGATIVACAO:
							TarefaBatchGerarResumoDiarioNegativacao gerarResumoDiarioNegativacao = new TarefaBatchGerarResumoDiarioNegativacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection rotasResumoDiarioNegativacao = getControladorSpcSerasa().consultarRotasParaGerarResumoDiarioNegativacao();
	
							gerarResumoDiarioNegativacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, rotasResumoDiarioNegativacao);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoDiarioNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
							
						case Funcionalidade.EXECUTAR_COMANDO_DE_NEGATIVACAO:
							TarefaBatchExecutarComandoNegativacao executarComandoNegativacao = new TarefaBatchExecutarComandoNegativacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							if (getControladorSpcSerasa().existeOcorrenciaMovimentoExclusaoIncompleto()) {
								throw new ControladorException("atencao.movimento_exclusao_incompleto");
							}
	
							NegativacaoComando negativacaoComando = getControladorSpcSerasa().consultarNegativacaoComandadoParaExecutar();
	
							if (negativacaoComando != null && !negativacaoComando.equals("")) {
	
								NegativacaoCriterio negativacaoCriterio = getControladorSpcSerasa().pesquisarNegativacaoCriterio(negativacaoComando.getId());
	
								Negativador negativador = negativacaoComando.getNegativador();
								NegativadorContrato negativadorContrato = getControladorSpcSerasa().consultarNegativadorContratoVigente(negativador.getId());
	
								Collection colecaoParametroNegativacaoCriterio = (Collection) getControladorSpcSerasa().pesquisarParametroNegativacaoCriterio(
										negativacaoCriterio.getId());
	
								Collection rotas = null;
								Object[] parametroNegCrit = null;
	
								if (negativacaoComando.getComandoSimulacao() != null && !negativacaoComando.getComandoSimulacao().equals("")) {
	
									rotas = getControladorSpcSerasa().pesquisarRotasImoveisComandoSimulacao(negativacaoComando.getComandoSimulacao().getId());
	
								} else if (negativacaoCriterio.getCliente() != null) {
									rotas = getControladorSpcSerasa().pesquisarRotasImoveis();
								} else {
									if (colecaoParametroNegativacaoCriterio != null && !colecaoParametroNegativacaoCriterio.isEmpty()) {
	
										for (Iterator iteratorColecaoParametroNegativacaoCriterio = colecaoParametroNegativacaoCriterio.iterator(); iteratorColecaoParametroNegativacaoCriterio
												.hasNext();) {
	
											parametroNegCrit = (Object[]) iteratorColecaoParametroNegativacaoCriterio.next();
	
											if (parametroNegCrit[0] != null) {
												rotas = getControladorSpcSerasa().pesquisarRotasPorCobrancaGrupoParaNegativacao(negativacaoCriterio);
												break;
											} else if (parametroNegCrit[1] != null) {
												rotas = getControladorSpcSerasa().pesquisarRotasPorGerenciaRegionalParaNegativacao(negativacaoCriterio);
												break;
											} else if (parametroNegCrit[2] != null) {
												rotas = getControladorSpcSerasa().pesquisarRotasPorUnidadeNegocioParaNegativacao(negativacaoCriterio);
												break;
											} else if (parametroNegCrit[3] != null) {
												rotas = getControladorSpcSerasa().pesquisarRotasPorLocalidadeParaNegativacao(negativacaoCriterio);
												break;
											} else if (parametroNegCrit[4] != null && parametroNegCrit[5] != null) {
												rotas = getControladorSpcSerasa().pesquisarRotasPorLocalidadesParaNegativacao(negativacaoCriterio);
												break;
											} else {
												rotas = getControladorSpcSerasa().pesquisarRotasImoveis();
												break;
											}
										}
									} else {
										rotas = getControladorSpcSerasa().pesquisarRotasImoveis();
									}
								}
	
								// Eliminando as rotas duplicadas
								rotas = new HashSet<Integer>(rotas);
	
								executarComandoNegativacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, rotas);
								executarComandoNegativacao.addParametro("nCriterio", negativacaoCriterio);
								executarComandoNegativacao.addParametro("neg", negativador);
								executarComandoNegativacao.addParametro("nComando", negativacaoComando);
								executarComandoNegativacao.addParametro("nContrato", negativadorContrato);
	
								if (negativacaoComando.getIndicadorSimulacao() != NegativacaoComando.SIMULACAO) {
									int numeroSequencialEnvio = negativadorContrato.getNumeroSequencialEnvio() + 1;
									Integer idNegativacaoMovimento = getControladorSpcSerasa().gerarNegativadorMovimento(negativador.getId(),
											numeroSequencialEnvio, negativacaoComando.getId());
	
									getControladorSpcSerasa().gerarRegistroDeInclusaoTipoHeader(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO, 1, negativador,
											negativadorContrato, negativacaoComando, negativacaoCriterio, idNegativacaoMovimento);
								}
							} else {
								throw new ControladorException("atencao.comando.negativacao.vazio.para.executar");
							}
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(executarComandoNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ATUALIZAR_LIGACAO_AGUA_LIGADO_ANALISE_PARA_LIGADO:
							
							TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado atualizarLigacaoAguaLigadoAnaliseParaLigado = new TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection colecaoLocalidade = getControladorArrecadacao().pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise();
	
							atualizarLigacaoAguaLigadoAnaliseParaLigado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoLocalidade);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(atualizarLigacaoAguaLigadoAnaliseParaLigado));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ATUALIZAR_NUMERO_EXECUCAO_RESUMO_NEGATIVACAO:
	
							TarefaBatchAtualizarNumeroExecucaoResumoNegativacao atualizarNumeroExecucaoResumoNegativacao = new TarefaBatchAtualizarNumeroExecucaoResumoNegativacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(atualizarNumeroExecucaoResumoNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO:
	
							TarefaBatchGerarMovimentoExclusaoNegativacao gerarMovimentoExclusaoNegativacao = new TarefaBatchGerarMovimentoExclusaoNegativacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarMovimentoExclusaoNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_MOVIMENTO_RETORNO_NEGATIVACAO:
	
							TarefaBatchGerarMovimentoRetornoNegativacao gerarMovimentoRetornoNegativacao = new TarefaBatchGerarMovimentoRetornoNegativacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarMovimentoRetornoNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO:
	
							TarefaBatchGerarDadosDiariosArrecadacao dadosArrecadacao = new TarefaBatchGerarDadosDiariosArrecadacao(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesGerarDadosDiariosArrecadacao = getControladorArrecadacao()
									.pesquisarIdsLocalidadeComPagamentosOuDevolucoes();
	
							dadosArrecadacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsLocalidadesGerarDadosDiariosArrecadacao);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosArrecadacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_DEBITOS_A_COBRAR_ACRESCIMOS_IMPONTUALIDADE:
						
							TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade impontualidade = new TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							System.out.println("ENCERRAR ARRECADACAO DO MES");
							Collection colecaoTodasRotas = getControladorMicromedicao().pesquisarListaRotasCarregadas();
	
							impontualidade.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoTodasRotas);
							impontualidade.addParametro("indicadorGeracaoMulta", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorGeracaoJuros", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorGeracaoAtualizacao", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorEncerrandoArrecadacao", true);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(impontualidade));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.CLASSIFICAR_PAGAMENTOS_DEVOLUCOES:
	
							TarefaBatchClassificarPagamentosDevolucoes dadosClassificarPagamentosDevolucoes = new TarefaBatchClassificarPagamentosDevolucoes(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesClassificarPagamentosDevolucoes = getControladorArrecadacao()
									.pesquisarIdsLocalidadeComPagamentosOuDevolucoes();
	
							dadosClassificarPagamentosDevolucoes.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesClassificarPagamentosDevolucoes);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosClassificarPagamentosDevolucoes));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ENCERRAR_ARRECADACAO_MES:
	
							TarefaBatchEncerrarArrecadacaoMes dadosEncerrarArrecadacaoMes = new TarefaBatchEncerrarArrecadacaoMes(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							dadosEncerrarArrecadacaoMes.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesEncerrarArrecadacaoMes);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosEncerrarArrecadacaoMes));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES:

				            if (!getControladorArrecadacao().verificarExistenciaResumoArrecadacaoParaAnoMes(
				                    getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao())) {
				              throw new ControladorException("Não existem dados do resumo da arrecadação para o ano/mês de referencia");
				            }

				            TarefaBatchGerarHistoricoParaEncerrarArrecadacaoMes dadosGerarHistoricoEncerrarArrecadacaoMes = new TarefaBatchGerarHistoricoParaEncerrarArrecadacaoMes(
				                processoIniciado.getUsuario(),
				                funcionalidadeIniciada.getId());

				            dadosGerarHistoricoEncerrarArrecadacaoMes.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,colecaoIdsLocalidadesEncerrarArrecadacaoMes);
				            dadosGerarHistoricoEncerrarArrecadacaoMes.addParametro("anoMesReferenciaArrecadacao",anoMesArrecadacaoSistemaParametro);

				            funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarHistoricoEncerrarArrecadacaoMes));

				            getControladorUtil().atualizar(funcionalidadeIniciada);

				            break;
	
						case Funcionalidade.GERAR_HISTORICO_CONTA:
							
							TarefaBatchGerarHistoricoConta dadosGerarHistoricoConta = new TarefaBatchGerarHistoricoConta(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsSetoresEncerrarArrecadacaoMes = getControladorArrecadacao()
									.pesquisarIdsSetoresComPagamentosOuDevolucoes();
	
							dadosGerarHistoricoConta.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsSetoresEncerrarArrecadacaoMes);
							dadosGerarHistoricoConta.addParametro("anoMesReferenciaArrecadacao", anoMesFaturamentoSistemaParametro);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarHistoricoConta));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ENCERRAR_FATURAMENTO_MES:
						
							TarefaBatchEncerrarFaturamentoMes dadosEncerrarFaturamentoMes = new TarefaBatchEncerrarFaturamentoMes(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesEncerrarFaturamentoMes = getControladorFaturamento()
									.pesquisarIdsLocalidadeParaEncerrarFaturamento();
	
							dadosEncerrarFaturamentoMes.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesEncerrarFaturamentoMes);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosEncerrarFaturamentoMes));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES:
							TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes dadosGerarHistoricoParaEncerrarFaturamentoMes = new TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsSetoresEncerrarFaturamentoMes = getControladorFaturamento()
									.pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento();
	
							dadosGerarHistoricoParaEncerrarFaturamentoMes.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsSetoresEncerrarFaturamentoMes);
	
							dadosGerarHistoricoParaEncerrarFaturamentoMes.addParametro("anoMesFaturamentoSistemaParametro", anoMesFaturamentoSistemaParametro);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarHistoricoParaEncerrarFaturamentoMes));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_LIGACOES_ECONOMIAS:
							
							TarefaBatchGerarResumoLigacoesEconomias gerarResumoLigacoesEconomias = new TarefaBatchGerarResumoLigacoesEconomias(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial novoFiltro = new FiltroSetorComercial();
							Collection<SetorComercial> colecaoSetorComercial = getControladorUtil().pesquisar(novoFiltro, SetorComercial.class.getName());
	
							gerarResumoLigacoesEconomias.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoSetorComercial);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoLigacoesEconomias));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_HIDROMETRO:
							
							TarefaBatchGerarResumoHidrometro gerarResumoHidrometro = new TarefaBatchGerarResumoHidrometro(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroHidrometroMarca filtroHidrometro = new FiltroHidrometroMarca();
							Collection colMarca = getControladorUtil().pesquisar(filtroHidrometro, HidrometroMarca.class.getName());
	
							gerarResumoHidrometro.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colMarca);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoHidrometro));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_REGISTRO_ATENDIMENTO:
							
							TarefaBatchGerarResumoRegistroAtendimento gerarResumoRegistroAtendimento = new TarefaBatchGerarResumoRegistroAtendimento(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMes = getControladorLocalidade().pesquisarTodosIdsLocalidade();
	
							gerarResumoRegistroAtendimento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMes);
	
							gerarResumoRegistroAtendimento.addParametro("anoMesFaturamentoSistemaParametro", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoRegistroAtendimento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_CONSUMO_AGUA:
							
							TarefaBatchGerarResumoConsumoAgua gerarResumoConsumoAgua = new TarefaBatchGerarResumoConsumoAgua(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtro = new FiltroSetorComercial();
							Collection<SetorComercial> colSetor = getControladorUtil().pesquisar(filtro, SetorComercial.class.getName());
	
							gerarResumoConsumoAgua.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetor);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoConsumoAgua));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_ARRECADACAO:
	
							TarefaBatchGerarLancamentosContabeisArrecadacao dadosGerarLancamentosContabeisArrecadacao = new TarefaBatchGerarLancamentosContabeisArrecadacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeLancamentoContabeisArrecadacao = new FiltroLocalidade();
	
							Collection<Localidade> colecaoIdsLocalidadesGerarLancamentosContabeisArrecadacao = getControladorUtil().pesquisar(
									filtroLocalidadeLancamentoContabeisArrecadacao, Localidade.class.getName());
	
							dadosGerarLancamentosContabeisArrecadacao.addParametro("anoMesArrecadacao", sistemaParametros.getAnoMesArrecadacao());
							dadosGerarLancamentosContabeisArrecadacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesGerarLancamentosContabeisArrecadacao);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarLancamentosContabeisArrecadacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_AVISO_BANCARIO:
	
							TarefaBatchGerarLancamentosContabeisAvisosBancarios tarefaBatch = new TarefaBatchGerarLancamentosContabeisAvisosBancarios(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							tarefaBatch.addParametro("anoMesArrecadacao", sistemaParametros.getAnoMesArrecadacao());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatch));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_SITUACAO_ESPECIAL_FATURAMENTO:
							
							TarefaBatchGerarResumoSituacaoEspecialFaturamento gerarResumoSituacaoEspecialFaturamento = new TarefaBatchGerarResumoSituacaoEspecialFaturamento(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoLocalidadeFaturamento = getControladorFaturamento().pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();
	
							gerarResumoSituacaoEspecialFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoLocalidadeFaturamento);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoSituacaoEspecialFaturamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO:
	
							TarefaBatchGerarResumoHistogramaAguaEsgoto gerarResumoAguaEsgoto = new TarefaBatchGerarResumoHistogramaAguaEsgoto(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroHistograma = new FiltroSetorComercial();
	
							Collection<SetorComercial> colSetorHistograma = getControladorUtil().pesquisar(filtroHistograma, SetorComercial.class.getName());
	
							gerarResumoAguaEsgoto.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorHistograma);
							gerarResumoAguaEsgoto.addParametro("anoMesFaturamentoSistemaParametro", anoMesFaturamentoSistemaParametro);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoAguaEsgoto));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA:
							
							TarefaBatchGerarResumoAcoesCobrancaCronograma dadosGerarResumoAcoesCobrancaCronograma = new TarefaBatchGerarResumoAcoesCobrancaCronograma(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection colecaoCobrancaGrupoCronogramaMes = getControladorCobranca().pesquisarCobrancaGrupoCronogramaMes();
	
							int POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR = 0;
							int POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR = 1;
							int POSICAO_DATA_COM_ATIV_ENCERRAR = 2;
							int POSICAO_DATA_PREV_ATIV_ENCERRAR = 3;
							int POSICAO_DATA_PREV_ATIV_EMITIR = 4;
							int POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES = 5;
							int POSICAO_ID_COB_ACAO_CRONOG = 6;
							int POSICAO_ID_COB_GRUPO = 7;
							int POSICAO_ID_COB_ACAO = 8;
							int POSICAO_DATA_REA_ATIV_EMITIR = 9;
	
							Collection colecaoDadosCobrancaAcaoAtividadeCronograma = new ArrayList();
	
							if (colecaoCobrancaGrupoCronogramaMes != null) {
								Iterator iteratorColecaoCobrancaGrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMes.iterator();
	
								while (iteratorColecaoCobrancaGrupoCronogramaMes.hasNext()) {
	
									Object[] dadosCobrancaGrupoCronogramaMes = (Object[]) iteratorColecaoCobrancaGrupoCronogramaMes.next();
	
									Integer anoMesReferencia = null;
									Integer idGrupo = null;
	
									Collection colecaoCobrancaAcaoCronograma = null;
	
									int idCobrancaGrupoCronogramaMes = -1;
	
									if (dadosCobrancaGrupoCronogramaMes[0] != null) {
										idCobrancaGrupoCronogramaMes = ((Integer) dadosCobrancaGrupoCronogramaMes[0]).intValue();
									}
	
									if (dadosCobrancaGrupoCronogramaMes[1] != null) {
										anoMesReferencia = (Integer) dadosCobrancaGrupoCronogramaMes[1];
									}
	
									if (dadosCobrancaGrupoCronogramaMes[2] != null) {
										idGrupo = (Integer) dadosCobrancaGrupoCronogramaMes[2];
									}
	
									colecaoCobrancaAcaoCronograma = getControladorCobranca().pesquisarCobrancaAcaoCronograma(idCobrancaGrupoCronogramaMes);
	
									if (colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()) {
	
										Iterator iteratorColecaoCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma.iterator();
	
										int idCobrancaAcaoCronograma = -1;
	
										Object[] dadosCobrancaAcaoCronograma = null;
										Object[] dadosCobrancaAcaoAtividadeCronograma = null;
	
										while (iteratorColecaoCobrancaAcaoCronograma.hasNext()) {
											dadosCobrancaAcaoCronograma = (Object[]) iteratorColecaoCobrancaAcaoCronograma.next();
	
											dadosCobrancaAcaoAtividadeCronograma = new Object[10];
	
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES] = anoMesReferencia;
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_GRUPO] = idGrupo;
	
											if (dadosCobrancaAcaoCronograma[0] != null) {
												idCobrancaAcaoCronograma = ((Integer) dadosCobrancaAcaoCronograma[0]).intValue();
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_CRONOG] = dadosCobrancaAcaoCronograma[0];
											}
	
											if (dadosCobrancaAcaoCronograma[1] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO] = dadosCobrancaAcaoCronograma[1];
											}
	
											boolean primeiraCondicao = true;
											boolean segundaCondicao = true;
	
											Collection colecaoCobrancaAtividadeAcaoCronogramaEmitir = null;
											Collection colecaoCobrancaAtividadeAcaoCronogramaEncerrar = null;
	
											colecaoCobrancaAtividadeAcaoCronogramaEmitir = getControladorCobranca()
													.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(idCobrancaAcaoCronograma, CobrancaAtividade.EMITIR);
	
											if (colecaoCobrancaAtividadeAcaoCronogramaEmitir != null && !colecaoCobrancaAtividadeAcaoCronogramaEmitir.isEmpty()) {
	
												Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEmitir.iterator().next();
	
												if (dadosCobrancaAtividade[0] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR] = dadosCobrancaAtividade[0];
												}
	
												if (dadosCobrancaAtividade[1] == null) {
													primeiraCondicao = false;
												} else {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_REA_ATIV_EMITIR] = dadosCobrancaAtividade[1];
												}
	
												if (dadosCobrancaAtividade[2] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_EMITIR] = dadosCobrancaAtividade[2];
												}
	
											} else {
												primeiraCondicao = false;
											}
	
											colecaoCobrancaAtividadeAcaoCronogramaEncerrar = getControladorCobranca()
													.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(idCobrancaAcaoCronograma, CobrancaAtividade.ENCERRAR);
	
											if (colecaoCobrancaAtividadeAcaoCronogramaEncerrar != null && !colecaoCobrancaAtividadeAcaoCronogramaEncerrar.isEmpty()) {
	
												Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEncerrar.iterator().next();
	
												if (dadosCobrancaAtividade[0] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR] = dadosCobrancaAtividade[0];
												}
	
												if (dadosCobrancaAtividade[1] != null) {
													segundaCondicao = false;
												}
	
												if (dadosCobrancaAtividade[2] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_ENCERRAR] = dadosCobrancaAtividade[2];
												}
	
												if (dadosCobrancaAtividade[3] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_COM_ATIV_ENCERRAR] = dadosCobrancaAtividade[3];
												}
	
												dadosCobrancaAtividade = null;
											} 
	
											if (primeiraCondicao && segundaCondicao) {
												colecaoDadosCobrancaAcaoAtividadeCronograma.add(dadosCobrancaAcaoAtividadeCronograma);
											}
											dadosCobrancaAcaoAtividadeCronograma = null;
										}
									}
	
								}
								dadosGerarResumoAcoesCobrancaCronograma.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoDadosCobrancaAcaoAtividadeCronograma);
	
								funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoAcoesCobrancaCronograma));
	
								getControladorUtil().atualizar(funcionalidadeIniciada);
							} else {
								throw new ControladorException("atencao.nao.existe.dados.tabela.cronograma");
							}
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_ENCERRAR_OS:
							TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS dadosGerarResumoAcoesCobrancaCronogramaEncerrarOS = new TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							colecaoCobrancaGrupoCronogramaMes = getControladorCobranca().pesquisarCobrancaGrupoCronogramaMes();
	
							// posiaaes do array com os dados que serao atualizados
							POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR = 0;
							POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR = 1;
							POSICAO_DATA_COM_ATIV_ENCERRAR = 2;
							POSICAO_DATA_PREV_ATIV_ENCERRAR = 3;
							POSICAO_DATA_PREV_ATIV_EMITIR = 4;
							POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES = 5;
							POSICAO_ID_COB_ACAO_CRONOG = 6;
							POSICAO_ID_COB_GRUPO = 7;
							POSICAO_ID_COB_ACAO = 8;
							POSICAO_DATA_REA_ATIV_EMITIR = 9;
	
							colecaoDadosCobrancaAcaoAtividadeCronograma = new ArrayList();
	
							if (colecaoCobrancaGrupoCronogramaMes != null) {
								Iterator iteratorColecaoCobrancaGrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMes.iterator();
	
								while (iteratorColecaoCobrancaGrupoCronogramaMes.hasNext()) {
	
									Object[] dadosCobrancaGrupoCronogramaMes = (Object[]) iteratorColecaoCobrancaGrupoCronogramaMes.next();
	
									Integer anoMesReferencia = null;
									Integer idGrupo = null;
	
									Collection colecaoCobrancaAcaoCronograma = null;
	
									int idCobrancaGrupoCronogramaMes = -1;
	
									if (dadosCobrancaGrupoCronogramaMes[0] != null) {
										idCobrancaGrupoCronogramaMes = ((Integer) dadosCobrancaGrupoCronogramaMes[0]).intValue();
									}
	
									if (dadosCobrancaGrupoCronogramaMes[1] != null) {
										anoMesReferencia = (Integer) dadosCobrancaGrupoCronogramaMes[1];
									}
	
									if (dadosCobrancaGrupoCronogramaMes[2] != null) {
										idGrupo = (Integer) dadosCobrancaGrupoCronogramaMes[2];
									}
	
									colecaoCobrancaAcaoCronograma = getControladorCobranca().pesquisarCobrancaAcaoCronograma(idCobrancaGrupoCronogramaMes);
	
									if (colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()) {
	
										Iterator iteratorColecaoCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma.iterator();
	
										int idCobrancaAcaoCronograma = -1;
	
										Object[] dadosCobrancaAcaoCronograma = null;
										Object[] dadosCobrancaAcaoAtividadeCronograma = null;
	
										while (iteratorColecaoCobrancaAcaoCronograma.hasNext()) {
											dadosCobrancaAcaoCronograma = (Object[]) iteratorColecaoCobrancaAcaoCronograma.next();
	
											dadosCobrancaAcaoAtividadeCronograma = new Object[10];
	
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES] = anoMesReferencia;
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_GRUPO] = idGrupo;
	
											if (dadosCobrancaAcaoCronograma[0] != null) {
												idCobrancaAcaoCronograma = ((Integer) dadosCobrancaAcaoCronograma[0]).intValue();
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_CRONOG] = dadosCobrancaAcaoCronograma[0];
											}
	
											if (dadosCobrancaAcaoCronograma[1] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO] = dadosCobrancaAcaoCronograma[1];
											}
	
											boolean primeiraCondicao = true;
											boolean segundaCondicao = true;
	
											Collection colecaoCobrancaAtividadeAcaoCronogramaEmitir = null;
											Collection colecaoCobrancaAtividadeAcaoCronogramaEncerrar = null;
	
											colecaoCobrancaAtividadeAcaoCronogramaEmitir = getControladorCobranca()
													.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(idCobrancaAcaoCronograma, CobrancaAtividade.EMITIR);
	
											if (colecaoCobrancaAtividadeAcaoCronogramaEmitir != null && !colecaoCobrancaAtividadeAcaoCronogramaEmitir.isEmpty()) {
	
												Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEmitir.iterator().next();
	
												if (dadosCobrancaAtividade[0] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR] = dadosCobrancaAtividade[0];
												}
	
												if (dadosCobrancaAtividade[1] == null) {
													primeiraCondicao = false;
												} else {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_REA_ATIV_EMITIR] = dadosCobrancaAtividade[1];
												}
	
												if (dadosCobrancaAtividade[2] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_EMITIR] = dadosCobrancaAtividade[2];
												}
	
											} else {
												primeiraCondicao = false;
											}
	
											colecaoCobrancaAtividadeAcaoCronogramaEncerrar = getControladorCobranca()
													.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(idCobrancaAcaoCronograma, CobrancaAtividade.ENCERRAR);
	
											if (colecaoCobrancaAtividadeAcaoCronogramaEncerrar != null && !colecaoCobrancaAtividadeAcaoCronogramaEncerrar.isEmpty()) {
	
												Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEncerrar.iterator().next();
	
												if (dadosCobrancaAtividade[0] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR] = dadosCobrancaAtividade[0];
												}
	
												if (dadosCobrancaAtividade[1] != null) {
													segundaCondicao = false;
												}
	
												if (dadosCobrancaAtividade[2] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_ENCERRAR] = dadosCobrancaAtividade[2];
												}
	
												if (dadosCobrancaAtividade[3] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_COM_ATIV_ENCERRAR] = dadosCobrancaAtividade[3];
												}
												dadosCobrancaAtividade = null;
											} 
	
											if (primeiraCondicao && segundaCondicao) {
												colecaoDadosCobrancaAcaoAtividadeCronograma.add(dadosCobrancaAcaoAtividadeCronograma);
											}
											dadosCobrancaAcaoAtividadeCronograma = null;
										}
									}
								}
								dadosGerarResumoAcoesCobrancaCronogramaEncerrarOS.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoDadosCobrancaAcaoAtividadeCronograma);
	
								funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoAcoesCobrancaCronogramaEncerrarOS));
	
								getControladorUtil().atualizar(funcionalidadeIniciada);
	
							} else {
								throw new ControladorException("atencao.nao.existe.dados.tabela.cronograma");
							}
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_ACOES_COBRANCA_EVENTUAL:
							
							TarefaBatchGerarResumoAcoesCobrancaEventual tarefaBatchGerarResumoAcoesCobrancaEventual = new TarefaBatchGerarResumoAcoesCobrancaEventual(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection colecaoAcaoCobrancaEventual = getControladorCobranca().pesquisarCobrancaAcaoAtividadeComandoSemRealizacao();
	
							if (colecaoAcaoCobrancaEventual != null && !colecaoAcaoCobrancaEventual.isEmpty()) {
								tarefaBatchGerarResumoAcoesCobrancaEventual.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoAcaoCobrancaEventual);
	
								funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoAcoesCobrancaEventual));
	
								getControladorUtil().atualizar(funcionalidadeIniciada);
							} else {
								throw new ControladorException("atencao.nao.existe.dados.tabela.comando");
							}
	
							break;
	
						case Funcionalidade.INSERIR_RESUMO_ACOES_COBRANCA_CRONOGRAMA:
							TarefaBatchInserirResumoAcoesCobrancaCronograma dadosInserirResumoAcoesCobrancaCronograma = new TarefaBatchInserirResumoAcoesCobrancaCronograma(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR = 0;
							POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR = 1;
							POSICAO_DATA_COM_ATIV_ENCERRAR = 2;
							POSICAO_DATA_PREV_ATIV_ENCERRAR = 3;
							POSICAO_DATA_PREV_ATIV_EMITIR = 4;
							POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES = 5;
							POSICAO_ID_COB_ACAO_CRONOG = 6;
							POSICAO_ID_COB_GRUPO = 7;
							POSICAO_ID_COB_ACAO = 8;
							POSICAO_DATA_REA_ATIV_EMITIR = 9;
	
							Collection colecaoCobrancaGrupoCronogramaMesInserir = getControladorCobranca().pesquisarCobrancaGrupoCronogramaMes();
	
							Collection colecaoDadosCobrancaAcaoAtividadeCronogramaParaInserir = new ArrayList();
	
							if (colecaoCobrancaGrupoCronogramaMesInserir != null) {
								Iterator iteratorColecaoCobrancaGrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMesInserir.iterator();
	
								while (iteratorColecaoCobrancaGrupoCronogramaMes.hasNext()) {
	
									Object[] dadosCobrancaGrupoCronogramaMes = (Object[]) iteratorColecaoCobrancaGrupoCronogramaMes.next();
	
									Integer anoMesReferencia = null;
									Integer idGrupo = null;
	
									Collection colecaoCobrancaAcaoCronograma = null;
	
									int idCobrancaGrupoCronogramaMes = -1;
	
									if (dadosCobrancaGrupoCronogramaMes[0] != null) {
										idCobrancaGrupoCronogramaMes = ((Integer) dadosCobrancaGrupoCronogramaMes[0]).intValue();
									}
	
									if (dadosCobrancaGrupoCronogramaMes[1] != null) {
										anoMesReferencia = (Integer) dadosCobrancaGrupoCronogramaMes[1];
									}
	
									if (dadosCobrancaGrupoCronogramaMes[2] != null) {
										idGrupo = (Integer) dadosCobrancaGrupoCronogramaMes[2];
									}
	
									colecaoCobrancaAcaoCronograma = getControladorCobranca().pesquisarCobrancaAcaoCronograma(idCobrancaGrupoCronogramaMes);
	
									if (colecaoCobrancaAcaoCronograma != null && !colecaoCobrancaAcaoCronograma.isEmpty()) {
	
										Iterator iteratorColecaoCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma.iterator();
	
										int idCobrancaAcaoCronograma = -1;
	
										Object[] dadosCobrancaAcaoCronograma = null;
										Object[] dadosCobrancaAcaoAtividadeCronograma = null;
	
										while (iteratorColecaoCobrancaAcaoCronograma.hasNext()) {
											dadosCobrancaAcaoCronograma = (Object[]) iteratorColecaoCobrancaAcaoCronograma.next();
	
											dadosCobrancaAcaoAtividadeCronograma = new Object[10];
	
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES] = anoMesReferencia;
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_GRUPO] = idGrupo;
	
											if (dadosCobrancaAcaoCronograma[0] != null) {
												idCobrancaAcaoCronograma = ((Integer) dadosCobrancaAcaoCronograma[0]).intValue();
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_CRONOG] = dadosCobrancaAcaoCronograma[0];
											}
	
											if (dadosCobrancaAcaoCronograma[1] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO] = dadosCobrancaAcaoCronograma[1];
											}
	
											boolean primeiraCondicao = true;
											boolean segundaCondicao = true;
	
											Collection colecaoCobrancaAtividadeAcaoCronogramaEmitir = null;
											Collection colecaoCobrancaAtividadeAcaoCronogramaEncerrar = null;
	
											colecaoCobrancaAtividadeAcaoCronogramaEmitir = getControladorCobranca()
													.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(idCobrancaAcaoCronograma, CobrancaAtividade.EMITIR);
	
											if (colecaoCobrancaAtividadeAcaoCronogramaEmitir != null && !colecaoCobrancaAtividadeAcaoCronogramaEmitir.isEmpty()) {
	
												Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEmitir.iterator().next();
	
												if (dadosCobrancaAtividade[0] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR] = dadosCobrancaAtividade[0];
												}
	
												if (dadosCobrancaAtividade[1] == null) {
													primeiraCondicao = false;
												} else {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_REA_ATIV_EMITIR] = dadosCobrancaAtividade[1];
												}
	
												if (dadosCobrancaAtividade[2] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_EMITIR] = dadosCobrancaAtividade[2];
												}
	
											} else {
												primeiraCondicao = false;
											}
	
											colecaoCobrancaAtividadeAcaoCronogramaEncerrar = getControladorCobranca()
													.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(idCobrancaAcaoCronograma, CobrancaAtividade.ENCERRAR);
	
											if (colecaoCobrancaAtividadeAcaoCronogramaEncerrar != null && !colecaoCobrancaAtividadeAcaoCronogramaEncerrar.isEmpty()) {
	
												Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEncerrar.iterator().next();
	
												if (dadosCobrancaAtividade[0] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR] = dadosCobrancaAtividade[0];
												}
	
												if (dadosCobrancaAtividade[1] != null) {
													segundaCondicao = false;
												}
	
												if (dadosCobrancaAtividade[2] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_ENCERRAR] = dadosCobrancaAtividade[2];
												}
	
												if (dadosCobrancaAtividade[3] != null) {
													dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_COM_ATIV_ENCERRAR] = dadosCobrancaAtividade[3];
												}
	
												dadosCobrancaAtividade = null;
											} 
	
											if (primeiraCondicao && segundaCondicao) {
												colecaoDadosCobrancaAcaoAtividadeCronogramaParaInserir.add(dadosCobrancaAcaoAtividadeCronograma);
											}
											dadosCobrancaAcaoAtividadeCronograma = null;
										}
									}
	
								}
								dadosInserirResumoAcoesCobrancaCronograma.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoDadosCobrancaAcaoAtividadeCronogramaParaInserir);
	
								funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosInserirResumoAcoesCobrancaCronograma));
	
								getControladorUtil().atualizar(funcionalidadeIniciada);
							} else {
								throw new ControladorException("atencao.nao.existe.dados.tabela.cronograma");
							}
							break;
	
						case Funcionalidade.INSERIR_RESUMO_ACOES_COBRANCA_EVENTUAL:
							
							TarefaBatchInserirResumoAcoesCobrancaEventual tarefaBatchInserirResumoAcoesCobrancaEventual = new TarefaBatchInserirResumoAcoesCobrancaEventual(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection colecaoAcaoCobrancaEventualParaInserir = getControladorCobranca().pesquisarCobrancaAcaoAtividadeComandoSemRealizacao();
	
							if (colecaoAcaoCobrancaEventualParaInserir != null && !colecaoAcaoCobrancaEventualParaInserir.isEmpty()) {
								tarefaBatchInserirResumoAcoesCobrancaEventual.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoAcaoCobrancaEventualParaInserir);
	
								funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchInserirResumoAcoesCobrancaEventual));
	
								getControladorUtil().atualizar(funcionalidadeIniciada);
							} else {
								throw new ControladorException("atencao.nao.existe.dados.tabela.comando");
							}
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_PENDENCIA:
							TarefaBatchGerarResumoPendencia dadosGerarResumoPendencia = new TarefaBatchGerarResumoPendencia(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroSetorComercial novoFiltroSetorComercial = new FiltroSetorComercial();
							Collection<SetorComercial> colecaoSetorPendencia = getControladorUtil().pesquisar(novoFiltroSetorComercial,
									SetorComercial.class.getName());
	
							dadosGerarResumoPendencia.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoSetorPendencia);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoPendencia));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_GUIA_PAGAMENTO_POR_CLIENTE_RESUMO_PENDENCIA:
							
							TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia dados = new TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeResumoPendencia = new FiltroLocalidade();
							Collection<Localidade> colecaoLocalidadePendencia = getControladorUtil().pesquisar(filtroLocalidadeResumoPendencia,
									Localidade.class.getName());
	
							dados.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoLocalidadePendencia);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dados));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_ANORMALIDADES:
							
							TarefaBatchGerarResumoAnormalidades tarefaBatchGerarResumoAnormalidades = new TarefaBatchGerarResumoAnormalidades(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesAnormalidades = getControladorFaturamento()
									.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();
	
							tarefaBatchGerarResumoAnormalidades.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesAnormalidades);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoAnormalidades));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA:
						
							TarefaBatchGerarResumoSituacaoEspecialCobranca tarefaBatchGerarResumoSituacaoEspecialCobranca = new TarefaBatchGerarResumoSituacaoEspecialCobranca(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesCobranca = getControladorFaturamento()
									.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();
	
							tarefaBatchGerarResumoSituacaoEspecialCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesCobranca);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoSituacaoEspecialCobranca));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_FATURA_CLIENTE_RESPONSAVEL:
						
							TarefaBatchGerarFaturaClienteResponsavel dadosGerarFaturaClienteResponsavel = new TarefaBatchGerarFaturaClienteResponsavel(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarFaturaClienteResponsavel));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.EMITIR_CONTAS:
							
							TarefaBatchEmitirContas emitirContas = new TarefaBatchEmitirContas(processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							emitirContas.addParametro("anoMesFaturamentoGrupo", anoMesFaturamentoSistemaParametro);
							emitirContas.addParametro("faturamentoGrupo", null);
	
							Collection colecaoIdsEmpresas = getControladorCadastro().pesquisarIdsEmpresa();
							emitirContas.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsEmpresas);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(emitirContas));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA:
							TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga dadosDesfazerParcelamentoPorEntradaNaoPaga = new TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosDesfazerParcelamentoPorEntradaNaoPaga));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INSTALACOES_HIDROMETROS:
	
							TarefaBatchGerarResumoInstalacoesHidrometros dadosGerarResumoInstalacoesHidrometros = new TarefaBatchGerarResumoInstalacoesHidrometros(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsSetoresComercial = getControladorGerencialMicromedicao()
									.pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(anoMesFaturamentoSistemaParametro);
	
							dadosGerarResumoInstalacoesHidrometros.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsSetoresComercial);
							dadosGerarResumoInstalacoesHidrometros.addParametro("anoMesReferenciaFaturamento", anoMesFaturamentoSistemaParametro);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoInstalacoesHidrometros));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_LEITURA_ANORMALIDADE:
	
							TarefaBatchGerarResumoLeituraAnormalidade dadosGerarResumoLeituraAnormalidade = new TarefaBatchGerarResumoLeituraAnormalidade(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetor = new FiltroSetorComercial();
							Collection<SetorComercial> colSetorComercial = getControladorUtil().pesquisar(filtroSetor, SetorComercial.class.getName());
	
							dadosGerarResumoLeituraAnormalidade.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorComercial);
							dadosGerarResumoLeituraAnormalidade.addParametro("anoMesReferenciaFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoLeituraAnormalidade));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_ARRECADACAO:
	
							TarefaBatchGerarResumoArrecadacao dadosGerarResumoArrecadacao = new TarefaBatchGerarResumoArrecadacao(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeResumoArrecadacao = new FiltroLocalidade(FiltroLocalidade.ID);
							Collection<Localidade> colLocalidadeResumoArrecadacao = getControladorUtil().pesquisar(filtroLocalidadeResumoArrecadacao,
									Localidade.class.getName());
	
							dadosGerarResumoArrecadacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colLocalidadeResumoArrecadacao);
							dadosGerarResumoArrecadacao.addParametro("anoMesReferenciaArrecadacao", sistemaParametros.getAnoMesArrecadacao());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoArrecadacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_PARCELAMENTO:
	
							TarefaBatchGerarResumoParcelamento dadosGerarResumoParcelamento = new TarefaBatchGerarResumoParcelamento(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
							Collection<Integer> colecaoLocalidadesParcelamento = getControladorFaturamento()
									.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();
	
							dadosGerarResumoParcelamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoLocalidadesParcelamento);
							dadosGerarResumoParcelamento.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoParcelamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_FATURAMENTO:
	
							TarefaBatchGerarResumoFaturamento dadosGerarResumoFaturamento = new TarefaBatchGerarResumoFaturamento(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroFaturamento = new FiltroSetorComercial();
	
							Collection<SetorComercial> colSetorFaturamento = getControladorUtil().pesquisar(filtroFaturamento, SetorComercial.class.getName());
	
							dadosGerarResumoFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorFaturamento);
							dadosGerarResumoFaturamento.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoFaturamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_FATURAMENTO_AGUA_ESGOTO:
						
							TarefaBatchGerarResumoFaturamentoAguaEsgoto dadosGerarResumoFaturamentoAguaEsgoto = new TarefaBatchGerarResumoFaturamentoAguaEsgoto(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							filtroFaturamento = new FiltroSetorComercial();
	
							colSetorFaturamento = getControladorUtil().pesquisar(filtroFaturamento, SetorComercial.class.getName());
	
							dadosGerarResumoFaturamentoAguaEsgoto.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorFaturamento);
							dadosGerarResumoFaturamentoAguaEsgoto.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoFaturamentoAguaEsgoto));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_REFATURAMENTO:
							
							TarefaBatchGerarResumoReFaturamento dadosGerarResumoReFaturamento = new TarefaBatchGerarResumoReFaturamento(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroReFaturamento = new FiltroSetorComercial();
	
							Collection<SetorComercial> colSetorReFaturamento = getControladorUtil() // getControladorGerencialFaturamento().pesquisarIdsSetores();
									.pesquisar(filtroReFaturamento, SetorComercial.class.getName());
	
							dadosGerarResumoReFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorReFaturamento);
							dadosGerarResumoReFaturamento.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoReFaturamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_REFATURAMENTO_OLAP:
							
							TarefaBatchGerarResumoReFaturamentoOlap dadosGerarResumoReFaturamentoOlap = new TarefaBatchGerarResumoReFaturamentoOlap(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroReFaturamentoOlap = new FiltroSetorComercial();
	
							Collection<SetorComercial> colSetorReFaturamentoOlap = getControladorUtil().pesquisar(filtroReFaturamentoOlap,
									SetorComercial.class.getName());
	
							dadosGerarResumoReFaturamentoOlap.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorReFaturamentoOlap);
							dadosGerarResumoReFaturamentoOlap.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoReFaturamentoOlap));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_FATURAMENTO:
	
							TarefaBatchGerarLancamentosContabeisFaturamento dadosGerarLancamentosContabeisFaturamento = new TarefaBatchGerarLancamentosContabeisFaturamento(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeLancamentoContabeisFaturamento = new FiltroLocalidade();
	
							Collection<Localidade> colecaoIdsLocalidadesGerarLancamentosContabeisFaturamento = getControladorUtil().pesquisar(
									filtroLocalidadeLancamentoContabeisFaturamento, Localidade.class.getName());
	
							dadosGerarLancamentosContabeisFaturamento.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
							dadosGerarLancamentosContabeisFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesGerarLancamentosContabeisFaturamento);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarLancamentosContabeisFaturamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_DEVEDORES_DUVIDOSOS:
	
							TarefaBatchGerarResumoDevedoresDuvidosos dadosGerarResumoDevedoresDuvidosos = new TarefaBatchGerarResumoDevedoresDuvidosos(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos = getControladorFinanceiro()
									.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(sistemaParametros.getAnoMesFaturamento());
	
							dadosGerarResumoDevedoresDuvidosos.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
							dadosGerarResumoDevedoresDuvidosos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoDevedoresDuvidosos));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_METAS:
	
							TarefaBatchGerarResumoMetas tarefaBatchGerarResumoMetas = new TarefaBatchGerarResumoMetas(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetorC = new FiltroSetorComercial();
							Collection<SetorComercial> colSetorC = getControladorUtil().pesquisar(filtroSetorC, SetorComercial.class.getName());
	
							Collection colecaoResumoMetas = getControladorFaturamento().pesquisarResumoMetas(sistemaParametros.getAnoMesArrecadacao());
	
							if (colecaoResumoMetas != null && !colecaoResumoMetas.isEmpty()) {
								throw new ControladorException("atencao.dados.existente.resumo.metas", null, "" + sistemaParametros.getAnoMesArrecadacao());
							}
	
							tarefaBatchGerarResumoMetas.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorC);
	
							Date dataInicial = Util.gerarDataInicialApartirAnoMesRefencia(sistemaParametros.getAnoMesArrecadacao());
							Date dataFinal = Util.gerarDataApartirAnoMesRefencia(sistemaParametros.getAnoMesArrecadacao());
							tarefaBatchGerarResumoMetas.addParametro("dataInicial", dataInicial);
	
							tarefaBatchGerarResumoMetas.addParametro("dataFinal", dataFinal);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoMetas));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_METAS_ACUMULADO:
							
							TarefaBatchGerarResumoMetasAcumulado tarefaBatchGerarResumoMetasAcumulado = new TarefaBatchGerarResumoMetasAcumulado(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetorCA = new FiltroSetorComercial();
							Collection<SetorComercial> colSetorCA = getControladorUtil().pesquisar(filtroSetorCA, SetorComercial.class.getName());
	
							Collection colecaoResumoMetasA = getControladorFaturamento().pesquisarResumoMetasAcumulado(sistemaParametros.getAnoMesArrecadacao());
	
							if (colecaoResumoMetasA != null && !colecaoResumoMetasA.isEmpty()) {
								throw new ControladorException("atencao.dados.existente.resumo.metas", null, "" + sistemaParametros.getAnoMesArrecadacao());
							}
	
							tarefaBatchGerarResumoMetasAcumulado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorCA);
							tarefaBatchGerarResumoMetasAcumulado.addParametro("anoMesArrecadacao", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoMetasAcumulado));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.EMITIR_CONTAS_ORGAO_PUBLICO:
							
							TarefaBatchEmitirContasOrgaoPublico tarefaBatchEmitirContasOrgaoPublico = new TarefaBatchEmitirContasOrgaoPublico(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Integer anoMesFaturamento = sistemaParametros.getAnoMesFaturamento();
							FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
							faturamentoGrupo.setId(0);
	
							tarefaBatchEmitirContasOrgaoPublico.addParametro("anoMesFaturamento", anoMesFaturamento);
							tarefaBatchEmitirContasOrgaoPublico.addParametro("faturamentoGrupo", faturamentoGrupo);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchEmitirContasOrgaoPublico));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_COLETA_ESGOTO:
							
							TarefaBatchGerarResumoColetaEsgoto gerarResumoColetaEsgoto = new TarefaBatchGerarResumoColetaEsgoto(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetorCom = new FiltroSetorComercial();
							Collection<SetorComercial> colSetorCom = getControladorUtil().pesquisar(filtroSetorCom, SetorComercial.class.getName());
	
							gerarResumoColetaEsgoto.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorCom);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoColetaEsgoto));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_CONTAS_A_RECEBER_CONTABIL:
							
							TarefaBatchGerarContasAReceberContabil gerarContaAReceberContabil = new TarefaBatchGerarContasAReceberContabil(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
							Collection<Localidade> colLocalidade = getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
	
							gerarContaAReceberContabil.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colLocalidade);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarContaAReceberContabil));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ATUALIZA_QUANTIDADE_PARCELA_PAGA_CONSECUTIVA_PARCELA_BONUS:
	
							TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus dadosAtualizarParcela = new TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							dadosAtualizarParcela.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsLocalidadesEncerrarArrecadacaoMes);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosAtualizarParcela));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.EXECUTAR_COMANDO_DE_ENCERRAMENTO_RA:
							
							TarefaBatchExecutarComandoEncerramentoRA executarComandoEncerramentoRA = new TarefaBatchExecutarComandoEncerramentoRA(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesRA = getControladorLocalidade().pesquisarTodosIdsLocalidade();
	
							FiltroRaEncerramentoComando filtroRaEncerramentoComando = new FiltroRaEncerramentoComando();
							filtroRaEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
							filtroRaEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("usuario.unidadeOrganizacional");
							filtroRaEncerramentoComando.adicionarParametro(new ParametroNulo(FiltroRaEncerramentoComando.TEMPO_REALIZACAO));
							Collection<RaEncerramentoComando> colRaEncerramentoComando = getControladorUtil().pesquisar(filtroRaEncerramentoComando,
									RaEncerramentoComando.class.getName());
	
							if (colRaEncerramentoComando != null && !colRaEncerramentoComando.isEmpty()) {
								RaEncerramentoComando raEncerramentoComando = null;
	
								laco: for (RaEncerramentoComando ra : colRaEncerramentoComando) {
									raEncerramentoComando = ra;
									break laco;
								}
	
								executarComandoEncerramentoRA.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsLocalidadesRA);
								executarComandoEncerramentoRA.addParametro("raEncerramentoComando", raEncerramentoComando);
	
							} else {
								throw new ControladorException("atencao.comando.encerramento.ra.vazio.para.executar");
							}
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(executarComandoEncerramentoRA));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_VALOR_VOLUMES_CONSUMIDOS_NAO_FATURADOS:
						
							TarefaBatchGerarValorVolumesConsumidosNaoFaturados gerarValorVolumesConsumidosNaoFaturados = new TarefaBatchGerarValorVolumesConsumidosNaoFaturados(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidades = getControladorLocalidade().pesquisarIdsLocalidadesImoveis();
	
							gerarValorVolumesConsumidosNaoFaturados.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsLocalidades);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarValorVolumesConsumidosNaoFaturados));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INDICADORES_COMERCIALIZACAO:
	
							TarefaBatchGerarResumoIndicadoresComercializacao gerarResumoIndicadoresComercializacao = new TarefaBatchGerarResumoIndicadoresComercializacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoIndicadoresComercializacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INDICADORES_MICROMEDICAO:
	
							TarefaBatchGerarResumoIndicadoresMicromedicao gerarResumoIndicadoresMicromedicao = new TarefaBatchGerarResumoIndicadoresMicromedicao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoIndicadoresMicromedicao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INDICADORES_FATURAMENTO:
	
							TarefaBatchGerarResumoIndicadoresFaturamento gerarResumoIndicadoresFaturamento = new TarefaBatchGerarResumoIndicadoresFaturamento(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoIndicadoresFaturamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INDICADORES_COBRANCA:
	
							TarefaBatchGerarResumoIndicadoresCobranca gerarResumoIndicadoresCobranca = new TarefaBatchGerarResumoIndicadoresCobranca(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoIndicadoresCobranca));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.INCLUIR_DEBITO_A_COBRAR_ENTRADA_PARCELAMENTO_NAO_PAGA:
	
							TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga dadosIncluirDebitoACobrarEntradaParcelamentoNaoPaga = new TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosIncluirDebitoACobrarEntradaParcelamentoNaoPaga));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ATUALIZAR_PAGAMENTOS_CONTAS_COBRANCA:
	
							TarefaBatchAtualizarPagamentosContasCobranca atualizarPagamentosContasCobranca = 
								new TarefaBatchAtualizarPagamentosContasCobranca(processoIniciado.getUsuario(),funcionalidadeIniciada.getId());

					            Collection<Integer> colecaoIdsLocalidadesAtualizarPagamentos = getControladorLocalidade().pesquisarTodosIdsLocalidade();

					            atualizarPagamentosContasCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,colecaoIdsLocalidadesAtualizarPagamentos);
					            atualizarPagamentosContasCobranca.addParametro("anoMesArrecadacaoSistemaParametro", anoMesArrecadacaoSistemaParametro);

					            funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(atualizarPagamentosContasCobranca));

					            getControladorUtil().atualizar(funcionalidadeIniciada);

					            break;
	
						case Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA:
	
							TarefaBatchGerarMovimentoContasCobrancaPorEmpresa gerarMovimentoContasCobrancaPorEmpresa = new TarefaBatchGerarMovimentoContasCobrancaPorEmpresa(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroComandoEmpresaCobrancaConta filtroComandoEmpresaCobrancaConta = new FiltroComandoEmpresaCobrancaConta();
							filtroComandoEmpresaCobrancaConta.adicionarParametro(new ParametroNulo(FiltroComandoEmpresaCobrancaConta.DATA_EXECUCAO));
	
							Collection colecaoComandoEmpresaCobrancaConta = getControladorUtil().pesquisar(filtroComandoEmpresaCobrancaConta,
									ComandoEmpresaCobrancaConta.class.getName());
	
							Collection colecaoComandoEmpresaCobrancaContaParaBatch = new ArrayList();
	
							if (colecaoComandoEmpresaCobrancaConta != null && !colecaoComandoEmpresaCobrancaConta.isEmpty()) {
								Iterator iteratorComandos = colecaoComandoEmpresaCobrancaConta.iterator();
	
								while (iteratorComandos.hasNext()) {
									ComandoEmpresaCobrancaConta comando = (ComandoEmpresaCobrancaConta) iteratorComandos.next();
	
									FiltroEmpresaCobranca filtroEmpresaCobranca = new FiltroEmpresaCobranca();
									filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(FiltroEmpresaCobranca.EMPRESA_ID, comando.getEmpresa().getId()));
									filtroEmpresaCobranca.adicionarParametro(new MaiorQue(FiltroEmpresaCobranca.DATA_FIM_CONTRATO, new Date()));

									Collection colecaoEmpresaCobranca = getControladorUtil().pesquisar(filtroEmpresaCobranca, EmpresaCobranca.class.getName());
	
									if (colecaoEmpresaCobranca != null && !colecaoEmpresaCobranca.isEmpty()) {
	
										EmpresaCobranca empresaCobranca = (EmpresaCobranca) Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);
	
										if ((empresaCobranca.getPercentualContratoCobranca() != null && empresaCobranca.getPercentualContratoCobranca().compareTo(
												BigDecimal.ZERO) > 0)
												|| (comando.getDataEncerramento() == null)) {
	
											colecaoComandoEmpresaCobrancaContaParaBatch.add(comando);
										}
									}
								}
							}
	
							gerarMovimentoContasCobrancaPorEmpresa.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoComandoEmpresaCobrancaContaParaBatch);
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarMovimentoContasCobrancaPorEmpresa));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA:
	
							TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa gerarMovimentoExtensaoContasCobrancaPorEmpresa = new TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroComandoEmpresaCobrancaContaExtensao filtroComandoEmpresaCobrancaContaExtensao = new FiltroComandoEmpresaCobrancaContaExtensao();
							filtroComandoEmpresaCobrancaContaExtensao
									.adicionarParametro(new ParametroNulo(FiltroComandoEmpresaCobrancaContaExtensao.DATA_EXECUCAO));
							filtroComandoEmpresaCobrancaContaExtensao
									.adicionarCaminhoParaCarregamentoEntidade(FiltroComandoEmpresaCobrancaContaExtensao.COMANDO_EMPRESA_COBRANCA_CONTA);
							filtroComandoEmpresaCobrancaContaExtensao.adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta.empresa");
	
							Collection colecaoComandoEmpresaCobrancaContaExtensao = getControladorUtil().pesquisar(filtroComandoEmpresaCobrancaContaExtensao,
									ComandoEmpresaCobrancaContaExtensao.class.getName());
	
							Collection colecaoLocalidades = getControladorCadastro().pesquisarLocalidades();
	
							gerarMovimentoExtensaoContasCobrancaPorEmpresa.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoLocalidades);
	
							gerarMovimentoExtensaoContasCobrancaPorEmpresa.addParametro("colecaoComandoEmpresaCobrancaContaExtensao",
									colecaoComandoEmpresaCobrancaContaExtensao);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarMovimentoExtensaoContasCobrancaPorEmpresa));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ATUALIZAR_AUTOS_INFRACAO_PRAZO_RECURSO_VENCIDO:
						
							TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido = new TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									Collections.singletonList(0));
	
							tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido.addParametro("sistemaParametro", sistemaParametros);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.EXCLUIR_IMOVEIS_DA_TARIFA_SOCIAL:
							
							TarefaBatchExcluirImoveisDaTarifaSocial excluirImoveisDaTarifaSocial = new TarefaBatchExcluirImoveisDaTarifaSocial(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Integer anoMesFaturamento_ExcluirImoveisDaTarifaSocial = sistemaParametros.getAnoMesFaturamento();
	
							FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
							Collection<SetorComercial> colecaoSetor = getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
							excluirImoveisDaTarifaSocial.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoSetor);
							excluirImoveisDaTarifaSocial.addParametro("anoMesReferenciaFaturamento", anoMesFaturamento_ExcluirImoveisDaTarifaSocial);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(excluirImoveisDaTarifaSocial));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_NEGATIVACAO:
							
							TarefaBatchGerarResumoNegativacao gerarResumoNegativacao = new TarefaBatchGerarResumoNegativacao(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							Collection colecaoRotas = getControladorSpcSerasa().consultarRotasParaGerarResumoDiarioNegativacao();
	
							Integer penultimaExecucaoResumo = sistemaParametros.getNumeroExecucaoResumoNegativacao() - 1;
							getControladorSpcSerasa().apagarResumoNegativacao(penultimaExecucaoResumo);
	
							gerarResumoNegativacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoRotas);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ACOMPANHAR_PAGAMENTO_DO_PARCELAMENTO:
	
							TarefaBatchAcompanharPagamentoDoParcelamento acompanharPagamentoDoParcelamento = new TarefaBatchAcompanharPagamentoDoParcelamento(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection rotas = getControladorSpcSerasa().consultarRotasParaGerarResumoDiarioNegativacao();
	
							acompanharPagamentoDoParcelamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, rotas);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(acompanharPagamentoDoParcelamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_DOCUMENTOS_A_RECEBER:
	
							TarefaBatchGerarResumoDocumentosAReceber tarefaBatchGerarResumoDocumentosAReceber = new TarefaBatchGerarResumoDocumentosAReceber(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeGerarResumoDocumentosAReceber = new FiltroLocalidade();
	
							Collection<Localidade> colLocalidadeGerarResumoDocumentosAReceber = getControladorUtil().pesquisar(
									filtroLocalidadeGerarResumoDocumentosAReceber, Localidade.class.getName());
	
							tarefaBatchGerarResumoDocumentosAReceber.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colLocalidadeGerarResumoDocumentosAReceber);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoDocumentosAReceber));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.BATCH_EMITIR_ORDEM_FISCALIZAO:
	
							TarefaBatchEmitirOrdemDeFiscalizacao tarefaBatchEmitirOrdemDeFiscalizacao = new TarefaBatchEmitirOrdemDeFiscalizacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetores = new FiltroSetorComercial();
	
							filtroSetores.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	
							Collection<SetorComercial> colecaoSetores = getControladorUtil().pesquisar(filtroSetores, SetorComercial.class.getName());
	
							tarefaBatchEmitirOrdemDeFiscalizacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoSetores);
							tarefaBatchEmitirOrdemDeFiscalizacao.addParametro("SistemaParametros", sistemaParametros);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchEmitirOrdemDeFiscalizacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
							break;
						case Funcionalidade.BATCH_GERAR_ARQUIVO_ORDEM_FISCALIZAO:
	
							TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB tarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB = new TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
							break;
	
						case Funcionalidade.DETERMINAR_CONFIRMACAO_DA_NEGATIVACAO:
							
							TarefaBatchDeterminarConfirmacaoDaNegativacao determinarConfirmacaoDaNegativacao = new TarefaBatchDeterminarConfirmacaoDaNegativacao(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection colLocalidades = getControladorSpcSerasa().consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao();
	
							determinarConfirmacaoDaNegativacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colLocalidades);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(determinarConfirmacaoDaNegativacao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_PENDENCIA_POR_ANO:
							
							TarefaBatchGerarResumoPendenciaPorAno dadosGerarResumoPendenciaPorAno = new TarefaBatchGerarResumoPendenciaPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroPendenciaPorAno = new FiltroSetorComercial();
							Collection<SetorComercial> colecaoSetorComercialPendenciaPorAno = getControladorUtil().pesquisar(filtroPendenciaPorAno,
									SetorComercial.class.getName());
	
							dadosGerarResumoPendenciaPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoSetorComercialPendenciaPorAno);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoPendenciaPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
						case Funcionalidade.BATCH_ATUALIZAR_CODIGO_DEBITO_AUTOMATICO:
	
							TarefaBatchAtualizarCodigoDebitoAutomatico tarefaBatchAtualizarCodigoDebitoAutomatico = new TarefaBatchAtualizarCodigoDebitoAutomatico(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetoresParaAtualizarCodigoDebitoAutomatico = new FiltroSetorComercial();
	
							Collection<SetorComercial> colecaoSetoresParaAtualizar = getControladorUtil().pesquisar(
									filtroSetoresParaAtualizarCodigoDebitoAutomatico, SetorComercial.class.getName());
	
							tarefaBatchAtualizarCodigoDebitoAutomatico.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoSetoresParaAtualizar);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchAtualizarCodigoDebitoAutomatico));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
							break;
	
						case Funcionalidade.GERAR_RESUMO_LIGACOES_ECONOMIAS_POR_ANO:
							
							TarefaBatchGerarResumoLigacoesEconomiasPorAno gerarResumoLigacoesEconomiasPorAno = new TarefaBatchGerarResumoLigacoesEconomiasPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial fSetorComercial = new FiltroSetorComercial();
							Collection<SetorComercial> setorComercialColecao = getControladorUtil().pesquisar(fSetorComercial, SetorComercial.class.getName());
	
							gerarResumoLigacoesEconomiasPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, setorComercialColecao);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoLigacoesEconomiasPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INDICADORES_MICROMEDICAO_POR_ANO:
	
							TarefaBatchGerarResumoIndicadoresMicromedicaoPorAno gerarResumoIndicadoresMicromedicaoPorAno = new TarefaBatchGerarResumoIndicadoresMicromedicaoPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoIndicadoresMicromedicaoPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_CONSUMO_AGUA_POR_ANO:
							TarefaBatchGerarResumoConsumoAguaPorAno gerarResumoConsumoAguaPorAno = new TarefaBatchGerarResumoConsumoAguaPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetComercial = new FiltroSetorComercial();
							Collection<SetorComercial> colSetComercial = getControladorUtil().pesquisar(filtroSetComercial, SetorComercial.class.getName());
	
							gerarResumoConsumoAguaPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetComercial);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoConsumoAguaPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_RECEITA:
	
							TarefaBatchGerarResumoReceita tarefaBatchGerarResumoReceita = new TarefaBatchGerarResumoReceita(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoReceita));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_FATURAMENTO_POR_ANO:
	
							TarefaBatchGerarResumoFaturamentoPorAno dadosGerarResFaturamento = new TarefaBatchGerarResumoFaturamentoPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filFaturamento = new FiltroSetorComercial();
	
							Collection<SetorComercial> colSetFaturamento = getControladorUtil().pesquisar(filFaturamento, SetorComercial.class.getName());
	
							SetorComercial setComercial = new SetorComercial();
							setComercial.setId(99999);
							colSetFaturamento.add(setComercial);
	
							dadosGerarResFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetFaturamento);
							dadosGerarResFaturamento.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResFaturamento));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_ARRECADACAO_POR_ANO:
	
							TarefaBatchGerarResumoArrecadacaoPorAno dadosGerarResumoArrecadacaoAno = new TarefaBatchGerarResumoArrecadacaoPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeResumoArrecadacaoAno = new FiltroLocalidade();
							Collection<Localidade> colLocalidadeResumoArrecadacaoAno = getControladorUtil().pesquisar(filtroLocalidadeResumoArrecadacaoAno,
									Localidade.class.getName());
	
							dadosGerarResumoArrecadacaoAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colLocalidadeResumoArrecadacaoAno);
							dadosGerarResumoArrecadacaoAno.addParametro("anoMesReferenciaArrecadacao", sistemaParametros.getAnoMesArrecadacao());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoArrecadacaoAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_COLETA_ESGOTO_POR_ANO:
							TarefaBatchGerarResumoColetaEsgotoPorAno gerarResumoColetaEsgotoPorAno = new TarefaBatchGerarResumoColetaEsgotoPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroSetorComPorAno = new FiltroSetorComercial();
							Collection<SetorComercial> colSetorComPorAno = getControladorUtil().pesquisar(filtroSetorComPorAno, SetorComercial.class.getName());
	
							gerarResumoColetaEsgotoPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorComPorAno);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoColetaEsgotoPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_REGISTRO_ATENDIMENTO_POR_ANO:
							TarefaBatchGerarResumoRegistroAtendimentoPorAno gerarResumoRegistroAtendimentoPorAno = new TarefaBatchGerarResumoRegistroAtendimentoPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMesPorAno = getControladorLocalidade()
									.pesquisarTodosIdsLocalidade();
	
							gerarResumoRegistroAtendimentoPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMesPorAno);
	
							gerarResumoRegistroAtendimentoPorAno.addParametro("anoMesFaturamentoSistemaParametro", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoRegistroAtendimentoPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_INSTALACOES_HIDROMETROS_POR_ANO:
	
							TarefaBatchGerarResumoInstalacoesHidrometrosPorAno dadosGerarResumoInstalacoesHidrometrosPorAno = new TarefaBatchGerarResumoInstalacoesHidrometrosPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoIdsSetoresComercialPorAno = getControladorGerencialMicromedicao()
									.pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(anoMesFaturamentoSistemaParametro);
	
							dadosGerarResumoInstalacoesHidrometrosPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsSetoresComercialPorAno);
	
							dadosGerarResumoInstalacoesHidrometrosPorAno.addParametro("anoMesReferenciaFaturamento", anoMesFaturamentoSistemaParametro);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoInstalacoesHidrometrosPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_PARCELAMENTO_POR_ANO:
							TarefaBatchGerarResumoParcelamentoPorAno dadosGerarResumoParcelamentoPorAno = new TarefaBatchGerarResumoParcelamentoPorAno(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoLocalidadesParcelamentoPorAno = getControladorFaturamento()
									.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();
	
							dadosGerarResumoParcelamentoPorAno.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoLocalidadesParcelamentoPorAno);
	
							dadosGerarResumoParcelamentoPorAno.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoParcelamentoPorAno));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ALTERAR_INSCRICOES_IMOVEIS:
	
							TarefaBatchAlterarInscricaoImovel tarefaBatchAlterarInscricaoImovel = new TarefaBatchAlterarInscricaoImovel(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeAlterarInscricoesImoveis = new FiltroLocalidade();
	
							Collection<Localidade> colecaoLocalidadeAlterarInscricoesImoveis = getControladorUtil().pesquisar(
									filtroLocalidadeAlterarInscricoesImoveis, Localidade.class.getName());
	
							tarefaBatchAlterarInscricaoImovel.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoLocalidadeAlterarInscricoesImoveis);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchAlterarInscricaoImovel));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
							break;
	
						case Funcionalidade.GERAR_PRESCREVER_DEBITOS_DE_IMOVEIS:
	
							TarefaBatchGerarPrescreverDebitosDeImoveis dadosGerarPrescreverDebitosDeImoveis = new TarefaBatchGerarPrescreverDebitosDeImoveis(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Integer> colecaoCobrancaSituacao = getControladorCobranca().obterCobrancaSituacaoParaPrescreverDebitos();
	
							dadosGerarPrescreverDebitosDeImoveis.addParametro("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
							dadosGerarPrescreverDebitosDeImoveis.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarPrescreverDebitosDeImoveis));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_REFATURAMENTO_NOVO:
	
							Integer existeResumo = null;
	
							existeResumo = getControladorGerencialFaturamento().verificarExistenciaResumoReFaturamento(sistemaParametros.getAnoMesFaturamento());
	
							if (existeResumo == null || existeResumo > 0) {
	
								throw new ControladorException("atencao.resumo.refaturamento.ja.existe", null, sistemaParametros.getAnoMesFaturamento().toString());
	
							}
	
							TarefaBatchGerarResumoReFaturamentoNovo dadosGerarResumoReFaturamentoNovo = new TarefaBatchGerarResumoReFaturamentoNovo(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroReFaturamentoNovo = new FiltroSetorComercial();
	
							Collection<SetorComercial> colSetorReFaturamentoNovo = getControladorUtil().pesquisar(filtroReFaturamentoNovo,
									SetorComercial.class.getName());
	
							dadosGerarResumoReFaturamentoNovo.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorReFaturamentoNovo);
							dadosGerarResumoReFaturamentoNovo.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoReFaturamentoNovo));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO_SEM_QUADRA:
							
							TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra gerarResumoAguaEsgotoSemQuadra = new TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroSetorComercial filtroHistogramaSemQuadra = new FiltroSetorComercial();
							Collection<SetorComercial> colSetorHistogramaSemQuadra = getControladorUtil().pesquisar(filtroHistogramaSemQuadra,
									SetorComercial.class.getName());
	
							gerarResumoAguaEsgotoSemQuadra.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colSetorHistogramaSemQuadra);
							gerarResumoAguaEsgotoSemQuadra.addParametro("anoMesFaturamentoSistemaParametro", anoMesFaturamentoSistemaParametro);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarResumoAguaEsgotoSemQuadra));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL:
	
							TarefaBatchReligarImoveisCortadosComConsumoReal dadosReligarImoveisCortadosComConsumoReal = new TarefaBatchReligarImoveisCortadosComConsumoReal(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeReligarImoveisCortadosComConsumoReal = new FiltroLocalidade();
	
							Collection<Localidade> colecaoLocalidadesReligarImoveisCortadosComConsumoReal = getControladorUtil().pesquisar(
									filtroLocalidadeReligarImoveisCortadosComConsumoReal, Localidade.class.getName());
	
							dadosReligarImoveisCortadosComConsumoReal.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
							dadosReligarImoveisCortadosComConsumoReal.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoLocalidadesReligarImoveisCortadosComConsumoReal);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosReligarImoveisCortadosComConsumoReal));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS:
						
							TarefaBatchPrescreverDebitosImoveisPublicosAutomatico prescricao = new TarefaBatchPrescreverDebitosImoveisPublicosAutomatico(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection colecaoDadosPrescricaoAutomaticos = getControladorFaturamento().obterDadosPrescricaoDebitosAutomaticos();
	
							if (Util.isVazioOrNulo(colecaoDadosPrescricaoAutomaticos)) {
								colecaoDadosPrescricaoAutomaticos = new ArrayList();
								colecaoDadosPrescricaoAutomaticos.add(EsferaPoder.obterIdsEsferaPoderPublico());
							}
	
							prescricao.addParametro("colecaoDadosPrescricao", colecaoDadosPrescricaoAutomaticos);
							prescricao.addParametro("anoMesFaturamento", sistemaParametros.getAnoMesFaturamento());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(prescricao));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
							break;
	
						case Funcionalidade.SELECIONAR_COMANDO_RETIRAR_IMOVEL_TARIFA_SOCIAL:
	
							TarefaBatchRetirarImovelTarifaSocial tarefaBatchRetirarImovelTarifaSocial = new TarefaBatchRetirarImovelTarifaSocial(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection idsLocalidade = getControladorCadastro().pesquisarLocalidade();
	
							tarefaBatchRetirarImovelTarifaSocial.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, idsLocalidade);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchRetirarImovelTarifaSocial));
							getControladorUtil().atualizar(funcionalidadeIniciada);
							break;
	
						case Funcionalidade.AUTOMATIZAR_PERFIS_DE_GRANDES_CONSUMIDORES:
	
							TarefaBatchAutomatizarPerfisDeGrandesConsumidores dadosAutomatizarPerfisDeGrandesConsumidores = new TarefaBatchAutomatizarPerfisDeGrandesConsumidores(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeAutomatizarPerfis = new FiltroLocalidade();
	
							Collection<Localidade> colecaoIdsLocalidadesAutomatizarPerfisDeGrandesConsumidores = getControladorUtil().pesquisar(
									filtroLocalidadeAutomatizarPerfis, Localidade.class.getName());
	
							dadosAutomatizarPerfisDeGrandesConsumidores.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesAutomatizarPerfisDeGrandesConsumidores);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosAutomatizarPerfisDeGrandesConsumidores));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_TXT_IMPRESSAO_CONTAS_FORMATO_BRAILLE:
	
							Collection colecaoGrupoNaoFaturados = getControladorFaturamento().pesquisarGrupoFaturamentoGrupoNaoFaturados(
									anoMesFaturamentoSistemaParametro);
	
							if (colecaoGrupoNaoFaturados != null && !colecaoGrupoNaoFaturados.isEmpty()) {
								throw new ControladorException("atencao.processo_cancelado_grupo_nao_faturado");
							}
	
							TarefaBatchGerarTxtImpressaoContasBraille gerarTxtContasBraille = new TarefaBatchGerarTxtImpressaoContasBraille(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarTxtContasBraille));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_ARQUIVO_TXT_OS_CONTAS_PAGAS_PARCELADAS:
	
							TarefaBatchGerarArquivoTextoOSContasPagasParceladas gerarArqvTxtOSContasPagasParceladas = new TarefaBatchGerarArquivoTextoOSContasPagasParceladas(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarArqvTxtOSContasPagasParceladas));
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.ENVIO_EMAIL_CONTA_PARA_CLIENTE:
							
							TarefaBatchEnvioEmailContaParaCliente tarefaBatchEnvioEmailContaParaCliente = new TarefaBatchEnvioEmailContaParaCliente(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							FiltroLocalidade filtroLocalidadeEnvioEmail = new FiltroLocalidade();
							filtroLocalidadeEnvioEmail
									.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	
							Collection<Localidade> collectionLocalidades = Fachada.getInstancia().pesquisar(filtroLocalidadeEnvioEmail, Localidade.class.getName());
	
							tarefaBatchEnvioEmailContaParaCliente.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, collectionLocalidades);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchEnvioEmailContaParaCliente));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.PROGRAMACAO_AUTO_ROTEIRO_ACOMPANHAMENTO_OS:
	
							TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS = new TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection collIdsUnidadesOrganizacionais = getControladorOrdemServico().pequisarUnidadesOrganizacionaisdasEquipes();
	
							tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									collIdsUnidadesOrganizacionais);
	
							tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS.addParametro("dataAtual", new Date());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_DADOS_ARQUIVO_ACOMPANHAMENTO_SERVICO:
							
							TarefaBatchGerarDadosArquivoAcompanhamentoServico tarefaBatchGerarDadosArquivoAcompanhamentoServico = new TarefaBatchGerarDadosArquivoAcompanhamentoServico(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection collIdsUnidadesOrganizacionais2 = getControladorOrdemServico().pequisarUnidadesOrganizacionaisdasEquipes();
	
							tarefaBatchGerarDadosArquivoAcompanhamentoServico.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									collIdsUnidadesOrganizacionais2);
	
							tarefaBatchGerarDadosArquivoAcompanhamentoServico.addParametro("dataAtual", new Date());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarDadosArquivoAcompanhamentoServico));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO:
	
							TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo tarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo = new TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.GERAR_DADOS_RELATORIO_BIG: {
							TarefaBatchGerarDadosRelatorioBIG gerarDadosRelatorioBIG = new TarefaBatchGerarDadosRelatorioBIG(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							gerarDadosRelatorioBIG.addParametro("anoMesReferencia", sistemaParametros.getAnoMesArrecadacao());
	
							Collection<Localidade> colecaoLocalidadesGerarDadosRelatorioBIG = obterLocalidadesAtivas();
	
							gerarDadosRelatorioBIG.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoLocalidadesGerarDadosRelatorioBIG);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarDadosRelatorioBIG));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
						}
	
						case Funcionalidade.CANCELAR_GUIAS_PAGAMENTO_NAO_PAGAS:
	
							TarefaBatchCancelarGuiasPagamentoNaoPagas cancelarGuiasPagamentoNaoPagas = new TarefaBatchCancelarGuiasPagamentoNaoPagas(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Date dataReferencia = new Date();
							cancelarGuiasPagamentoNaoPagas.addParametro("dataReferencia", dataReferencia);
	
							Collection<Integer> colecaoIdsLocalidadesComGuiasPagamentoNaoPagas = getControladorArrecadacao()
									.pesquisarIdsLocalidadeComGuiasPagamentoNaoPagas(dataReferencia, null);
	
							cancelarGuiasPagamentoNaoPagas.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoIdsLocalidadesComGuiasPagamentoNaoPagas);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(cancelarGuiasPagamentoNaoPagas));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
	
						case Funcionalidade.PROCESSAR_PAGAMENTOS_COM_DIFERENCA_DE_DOIS_REAIS: {
							
							TarefaBatchProcessarPagamentosComDiferencaDoisReais batch = new TarefaBatchProcessarPagamentosComDiferencaDoisReais(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							batch.addParametro("anoMesReferencia", sistemaParametros.getAnoMesArrecadacao());
	
							Collection<Localidade> localidades = obterLocalidadesAtivas();
	
							batch.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, localidades);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(batch));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
						}
	
						case Funcionalidade.ATUALIZACAO_CADASTRAL: {
							
							TarefaBatchAtualizacaoCadastral batchAtualizacaoCadastral = new TarefaBatchAtualizacaoCadastral(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(batchAtualizacaoCadastral));
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
						}
	
						case Funcionalidade.GERAR_DADOS_RECEITAS_A_FATURAR_RESUMO: {
							
							TarefaBatchGerarDadosReceitasAFaturarResumo gerarDadosReceitasAFaturarResumo = new TarefaBatchGerarDadosReceitasAFaturarResumo(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							gerarDadosReceitasAFaturarResumo.addParametro("anoMesReferencia", sistemaParametros.getAnoMesArrecadacao());
	
							FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
							filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
							filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.SIM));
							Collection<FaturamentoGrupo> colecaoFaturamentoGrupos = getControladorUtil().pesquisar(filtroFaturamentoGrupo,
									FaturamentoGrupo.class.getName());
	
							gerarDadosReceitasAFaturarResumo.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoGrupos);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarDadosReceitasAFaturarResumo));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
						}
	
						case Funcionalidade.GERAR_ARQUIVO_PAGAMENTO_CONTAS_COBRANCA_EMPRESA: {
							
							TarefaBatchGerarArquivoTextoPagamentosContasCobrancaEmpresa batch = new TarefaBatchGerarArquivoTextoPagamentosContasCobrancaEmpresa(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
							
							Collection<Empresa> empresas = obterEmpresasCobranca();
							
							batch.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, empresas);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(batch));

							getControladorUtil().atualizar(funcionalidadeIniciada);

							break;
						}
						
						case Funcionalidade.GERAR_NEGOCIACAO_CONTAS_COBRANCA_EMPRESA: {
							
							TarefaBatchGerarNegociacaoContasCobrancaEmpresa batch = new TarefaBatchGerarNegociacaoContasCobrancaEmpresa(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
	
							Collection<Empresa> empresas = obterEmpresasCobranca();
	
							batch.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, empresas);
	
							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(batch));
	
							getControladorUtil().atualizar(funcionalidadeIniciada);
	
							break;
						}
						default:
					}
				} catch (IOException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		}
		return codigoProcessoIniciadoGerado;
	}

	@SuppressWarnings("unchecked")
	private Collection<Empresa> obterEmpresasCobranca() throws ControladorException {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.ID);
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_EMPRESA_CONTRATADA_COBRANCA, ConstantesSistema.SIM));

		Collection<Empresa> empresas = getControladorUtil().pesquisar(filtroEmpresa, Empresa.class.getName());
		return empresas;
	}

	@SuppressWarnings("unchecked")
	private Collection<Localidade> obterLocalidadesAtivas() throws ControladorException {
		FiltroLocalidade filtroBatch = new FiltroLocalidade(FiltroLocalidade.ID);
		filtroBatch.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroBatch.setCampoOrderBy(FiltroLocalidade.ID);

		Collection<Localidade> localidades = getControladorUtil().pesquisar(filtroBatch, Localidade.class.getName());
		return localidades;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer inserirProcessoIniciadoFaturamentoComandado(Collection<Integer> idsFaturamentoAtividadeCronograma, Usuario usuario)
			throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;
		try {

			Iterator<Integer> iteratorFaturamentoAtividadeCronograma = idsFaturamentoAtividadeCronograma.iterator();

			while (iteratorFaturamentoAtividadeCronograma.hasNext()) {

				Integer codigoFaturamentoAtividadeCronograma = iteratorFaturamentoAtividadeCronograma.next();

				Collection colecaoFaturamentoAtivCronRota = repositorioBatch
						.pesquisarRotasProcessamentoBatchFaturamentoComandado(codigoFaturamentoAtividadeCronograma);

				if (colecaoFaturamentoAtivCronRota == null || colecaoFaturamentoAtivCronRota.isEmpty()) {
					throw new ControladorException("atencao.faturamento_nenhuma_rota");

				}

				FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
				filtroFaturamentoAtividadeCronograma.adicionarParametro(new ParametroSimples(FiltroFaturamentoAtividadeCronograma.ID,
						codigoFaturamentoAtividadeCronograma));

				filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade.processo");
				filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");

				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
						.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtroFaturamentoAtividadeCronograma,
								FaturamentoAtividadeCronograma.class.getName()));

				Processo processo = faturamentoAtividadeCronograma.getFaturamentoAtividade().getProcesso();
				FaturamentoGrupo faturamentoGrupo = faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo();

				if (processo.getId().intValue() == Processo.FATURAR_GRUPO_FATURAMENTO) {
					Collection<Rota> colecaoRota = this.repositorioMicromedicao.pesquisaRotasNaoTransmitidas(faturamentoGrupo.getAnoMesReferencia(),
							faturamentoGrupo.getId());

					if (colecaoRota != null && !colecaoRota.isEmpty()) {
						Iterator iteratorRota = colecaoRota.iterator();
						String mensagemAlerta = null;
						while (iteratorRota.hasNext()) {
							Rota rotaNaoTransmitida = (Rota) iteratorRota.next();
							if (mensagemAlerta == null) {
								mensagemAlerta = rotaNaoTransmitida.getId().toString();
							} else {
								mensagemAlerta += "," + rotaNaoTransmitida.getId().toString();
							}
						}

						throw new ControladorException("atencao.rotas_nao_transmitidas", null, mensagemAlerta);

					}
				}

				ProcessoIniciado processoIniciado = new ProcessoIniciado();
				processoIniciado.setUsuario(usuario);
				processoIniciado.setCodigoGrupoProcesso(faturamentoGrupo.getId());

				ProcessoSituacao processoSituacao = new ProcessoSituacao();
				Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
				processoSituacao.setId(processoSituacaoId);
				processoIniciado.setProcessoSituacao(processoSituacao);
				processoIniciado.setProcesso(processo);
				processoIniciado.setDataHoraAgendamento(new Date());
				processoIniciado.setDataHoraInicio(new Date());
				processoIniciado.setDataHoraComando(faturamentoAtividadeCronograma.getComando());

				codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

				FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();
				filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
						.getId()));
				filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

				Iterator iterator = processosFuncionaliadade.iterator();
				while (iterator.hasNext()) {

					ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();

					FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
					funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);

					FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
					funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
					funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
					funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);

					funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

					SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

					case Funcionalidade.GERAR_DADOS_PARA_LEITURA:

						TarefaBatchGerarDadosParaLeitura dadosParaLeitura = new TarefaBatchGerarDadosParaLeitura(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						dadosParaLeitura.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getAnoMesReferencia());
						dadosParaLeitura.addParametro("idFaturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getId());
						dadosParaLeitura.addParametro("sistemaParametro", sistemaParametro);
						dadosParaLeitura.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosParaLeitura));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA:
						TarefaBatchGerarArquivoTextoParaLeiturista dadosGerarArquivoTextoParaLeiturista = new TarefaBatchGerarArquivoTextoParaLeiturista(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						dadosGerarArquivoTextoParaLeiturista.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						dadosGerarArquivoTextoParaLeiturista.addParametro("colecaoRotas", colecaoFaturamentoAtivCronRota);
						dadosGerarArquivoTextoParaLeiturista.addParametro("anoMesFaturamento", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia());
						dadosGerarArquivoTextoParaLeiturista.addParametro("dataComando", faturamentoAtividadeCronograma.getComando());
						dadosGerarArquivoTextoParaLeiturista.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());
						dadosGerarArquivoTextoParaLeiturista.addParametro("sistemaParametro", sistemaParametro);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarArquivoTextoParaLeiturista));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS:

						TarefaBatchConsistirLeiturasCalcularConsumos calcularConsumos = new TarefaBatchConsistirLeiturasCalcularConsumos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						getControladorMicromedicao().processarLeiturasNaoResgistradas(
								faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						calcularConsumos.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo());
						calcularConsumos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						sistemaParametro.setAnoMesFaturamento(faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getAnoMesReferencia());

						calcularConsumos.addParametro("sistemaParametros", sistemaParametro);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(calcularConsumos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.GERAR_BONUS_TARIFA_SOCIAL:

						TarefaBatchGerarBonusTarifaSocial tarefaBatchGerarBonusTarifaSocial = new TarefaBatchGerarBonusTarifaSocial(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchGerarBonusTarifaSocial.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo());
						tarefaBatchGerarBonusTarifaSocial.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						sistemaParametro.setAnoMesFaturamento(faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getAnoMesReferencia());

						tarefaBatchGerarBonusTarifaSocial.addParametro("sistemaParametros", sistemaParametro);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarBonusTarifaSocial));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.EFETUAR_RATEIO_CONSUMO:
						TarefaBatchEfetuarRateioConsumo rateioConsumo = new TarefaBatchEfetuarRateioConsumo(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						rateioConsumo.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getAnoMesReferencia());
						rateioConsumo.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(rateioConsumo));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.FATURAR_GRUPO_FATURAMENTO:
						TarefaBatchFaturarGrupoFaturamento faturarGrupoFaturamento = new TarefaBatchFaturarGrupoFaturamento(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						faturarGrupoFaturamento.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo());
						faturarGrupoFaturamento.addParametro("atividade", faturamentoAtividadeCronograma.getFaturamentoAtividade().getId());
						faturarGrupoFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(faturarGrupoFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.GERAR_TAXA_ENTREGA_CONTA_OUTRO_ENDERECO:
						TarefaBatchGerarTaxaEntregaContaOutroEndereco taxaEntrega = new TarefaBatchGerarTaxaEntregaContaOutroEndereco(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						taxaEntrega.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getAnoMesReferencia());
						taxaEntrega.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(taxaEntrega));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_DEBITOS_A_COBRAR_ACRESCIMOS_IMPONTUALIDADE:
						TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade impontualidade = new TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						if (processoIniciado.getProcesso().getId().equals(Processo.ENCERRAR_ARRECADACAO_MES)) {

							System.out.println("ENCERRAR ARRECADACAO DO MES");

							Collection colecaoTodasRotas = getControladorMicromedicao().pesquisarListaRotasCarregadas();

							impontualidade.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoTodasRotas);
							impontualidade.addParametro("indicadorGeracaoMulta", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorGeracaoJuros", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorGeracaoAtualizacao", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorEncerrandoArrecadacao", true);

						} else {
							System.out.println("FATURAR GRUPO DE FATURAMENTO");

							impontualidade.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);
							impontualidade.addParametro("indicadorGeracaoMulta", ConstantesSistema.SIM);
							impontualidade.addParametro("indicadorGeracaoJuros", ConstantesSistema.NAO);
							impontualidade.addParametro("indicadorGeracaoAtualizacao", ConstantesSistema.NAO);
							impontualidade.addParametro("indicadorEncerrandoArrecadacao", false);

						}

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(impontualidade));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_CONTAS:
						TarefaBatchEmitirContas emitirContas = new TarefaBatchEmitirContas(processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						emitirContas.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getAnoMesReferencia());

						emitirContas.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo());

						Collection colecaoIdsEmpresas = getControladorCadastro().pesquisarIdsEmpresa();

						emitirContas.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoIdsEmpresas);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(emitirContas));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_DEBITO_COBRAR_DOACAO:
						TarefaBatchGerarDebitosACobrarDoacao gerarDebitoACobrarDoacao = new TarefaBatchGerarDebitosACobrarDoacao(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						gerarDebitoACobrarDoacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarDebitoACobrarDoacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO:

						TarefaBatchEmitirExtratoConsumoImovelCondominio emitirExtratoConsumoImovelCondominio = new TarefaBatchEmitirExtratoConsumoImovelCondominio(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						emitirExtratoConsumoImovelCondominio.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia());

						emitirExtratoConsumoImovelCondominio.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(emitirExtratoConsumoImovelCondominio));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_CREDITO_SITUACAO_ESPECIAL_FATURAMENTO:

						TarefaBatchGerarCreditoSituacaoEspecialFaturamento gerarCreditoSituacaoespecialFaturamento = new TarefaBatchGerarCreditoSituacaoEspecialFaturamento(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						gerarCreditoSituacaoespecialFaturamento.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						gerarCreditoSituacaoespecialFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						gerarCreditoSituacaoespecialFaturamento.addParametro("atividade", faturamentoAtividadeCronograma.getFaturamentoAtividade().getId());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarCreditoSituacaoespecialFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.SUPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL:

						TarefaBatchSuspenderImovelEmProgramaEspecial tarefaBatchSuspenderImovelEmProgramaEspecial = new TarefaBatchSuspenderImovelEmProgramaEspecial(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchSuspenderImovelEmProgramaEspecial.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchSuspenderImovelEmProgramaEspecial));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_SIMULACAO_FATURAMENTO:
						TarefaBatchGerarResumoSimulacaoFaturamento tarefaBatchGerarResumoSimulacaoFaturamento = new TarefaBatchGerarResumoSimulacaoFaturamento(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchGerarResumoSimulacaoFaturamento.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						tarefaBatchGerarResumoSimulacaoFaturamento.addParametro("atividade", faturamentoAtividadeCronograma.getFaturamentoAtividade().getId());

						tarefaBatchGerarResumoSimulacaoFaturamento.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarResumoSimulacaoFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

						TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						SistemaParametro sistemaParametroQuitacao = getControladorUtil().pesquisarParametrosDoSistema();

						tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos.addParametro("SistemaParametros", sistemaParametroQuitacao);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

						TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_TAXA_PERCENTUAL_TARIFA_MINIMA_CORTADO:

						TarefaBatchGerarTaxaPercentualTarifaMinimaCortado tarefaBatchGerarTaxaPercentualTarifaMinimaCortado = new TarefaBatchGerarTaxaPercentualTarifaMinimaCortado(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchGerarTaxaPercentualTarifaMinimaCortado.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						tarefaBatchGerarTaxaPercentualTarifaMinimaCortado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						tarefaBatchGerarTaxaPercentualTarifaMinimaCortado.addParametro("atividade", faturamentoAtividadeCronograma.getFaturamentoAtividade()
								.getId());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarTaxaPercentualTarifaMinimaCortado));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.VERIFICAR_FATURAMENTO_IMOVEIS_CORTADOS:

						TarefaBatchVerificarFaturamentoImoveisCortados tarefaBatchVerificarFaturamentoImoveisCortados = new TarefaBatchVerificarFaturamentoImoveisCortados(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchVerificarFaturamentoImoveisCortados.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						tarefaBatchVerificarFaturamentoImoveisCortados.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						tarefaBatchVerificarFaturamentoImoveisCortados.addParametro("atividade", faturamentoAtividadeCronograma.getFaturamentoAtividade()
								.getId());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchVerificarFaturamentoImoveisCortados));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RA_OS_ANORMALIDADE_CONSUMO:

						TarefaBatchGerarRAOSAnormalidadeConsumo gerarRAOSAnormalidadeConsumo = new TarefaBatchGerarRAOSAnormalidadeConsumo(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						gerarRAOSAnormalidadeConsumo.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo());

						gerarRAOSAnormalidadeConsumo.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoFaturamentoAtivCronRota);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarRAOSAnormalidadeConsumo));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.SUSPENDER_LEITURA_PARA_IMOVEL_COM_HIDROMETRO_RETIRADO:

						TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado = new TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.SUSPENDER_LEITURA_PARA_IMOVEL_COM_CONSUMO_REAL_NAO_SUPERIOR_A_10:

						TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10 tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10 = new TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10.addParametro("faturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoAtivCronRota);

						tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10.addParametro("anoMesFaturamentoGrupo", faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia());

						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					default:
					}
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		} catch (IOException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return codigoProcessoIniciadoGerado;
	}

	public Integer inserirProcessoIniciadoCobrancaComandado(Collection<Integer> idsCronograma, Collection<Integer> idsEventuais, Usuario usuario)
			throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;
		try {

			Iterator<Integer> iteratorCronograma = idsCronograma.iterator();
			codigoProcessoIniciadoGerado = inserirProcessoCobrancaAtividadeCronograma(usuario, codigoProcessoIniciadoGerado, iteratorCronograma);

			Iterator<Integer> iteratorAtividade = idsEventuais.iterator();
			codigoProcessoIniciadoGerado = inserirProcessoCobrancaAtividadeEventual(usuario, codigoProcessoIniciadoGerado, iteratorAtividade);

		} catch (IOException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		} catch (ParseException e) {
		}
		return codigoProcessoIniciadoGerado;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Integer inserirProcessoCobrancaAtividadeCronograma(Usuario usuario, Integer codigoProcessoIniciadoGerado, Iterator<Integer> iteratorCronograma)
			throws ControladorException, ParseException, IOException {

		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		while (iteratorCronograma.hasNext()) {

			Integer codigoCobrancaAcaoAtividadeCronograma = iteratorCronograma.next();

			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = getControladorCobranca().pesquisarCobrancaAcaoAtividadeCronogramaId(
					codigoCobrancaAcaoAtividadeCronograma);

			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuario);

			processoIniciado.setCodigoGrupoProcesso(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes()
					.getCobrancaGrupo().getId());

			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getProcesso().getId());
			processoSituacao.setId(processoSituacaoId); // Ver isso
			processoIniciado.setProcessoSituacao(processoSituacao);

			processoIniciado.setProcesso(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getProcesso());
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(cobrancaAcaoAtividadeCronograma.getComando());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			CobrancaDocumentoControleGeracao cobrancaDocumentoControleGeracao = new CobrancaDocumentoControleGeracao(0, 0, new BigDecimal("0.00"), new Date(),
					processoIniciado, cobrancaAcaoAtividadeCronograma, null);

			Integer idCobrancaDocumentoControleGeracao = (Integer) getControladorUtil().inserir(cobrancaDocumentoControleGeracao);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Date dataAtual = new Date();

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.COBRANCA_GRUPO_ID, cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
						.getCobrancaGrupoCronogramaMes().getCobrancaGrupo().getId()));
				if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getId() != null
						&& cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO)
						&& sistemaParametro.getCodigoEmpresaFebraban() != null
						&& sistemaParametro.getCodigoEmpresaFebraban().equals(Empresa.EMPRESA_FEBRABAN_COMPESA)) {
					filtroRota.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroRota.EMPRESA_COBRANCA_ID, 1));
				}
				Collection<Rota> colecaoRotas = getControladorUtil().pesquisar(filtroRota, Rota.class.getName());

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

				switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

				case Funcionalidade.GERAR_ATIVIDADE_ACAO_COBRANCA:
					TarefaBatchGerarAtividadeAcaoCobranca acaoCobranca = new TarefaBatchGerarAtividadeAcaoCobranca(processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					acaoCobranca.addParametro("grupoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes()
							.getCobrancaGrupo());
					acaoCobranca.addParametro("anoMesReferenciaCicloCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
							.getCobrancaGrupoCronogramaMes().getAnoMesReferencia());
					acaoCobranca.addParametro("comandoAtividadeAcaoCobranca", cobrancaAcaoAtividadeCronograma);
					acaoCobranca.addParametro("colecaoRotas", colecaoRotas);

					if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
						acaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoRotas);
					} else {
						acaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								Collections.singletonList(cobrancaAcaoAtividadeCronograma.getId()));
					}

					acaoCobranca.addParametro("acaoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao());
					acaoCobranca.addParametro("atividadeCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAtividade());
					acaoCobranca.addParametro("indicadorCriterio", new Short("1"));
					acaoCobranca.addParametro("anoMesReferenciaInicial", 000101);
					acaoCobranca.addParametro("anoMesReferenciaFinal",
							(new Integer(Util.subtrairMesDoAnoMes(getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 1))));
					acaoCobranca.addParametro("dataVencimentoInicial", formato.parse("01/01/0001"));
					acaoCobranca.addParametro("dataAtual", dataAtual);
					acaoCobranca
							.addParametro("dataVencimentoFinal", Util.subtrairNumeroDiasDeUmaData(new Date(), getControladorUtil()
									.pesquisarParametrosDoSistema().getNumeroDiasVencimentoCobranca()));
					acaoCobranca.addParametro("idCobrancaDocumentoControleGeracao", idCobrancaDocumentoControleGeracao);

					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(acaoCobranca));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.EMITIR_BOLETIM_CADASTRO:

					TarefaBatchEmitirBoletimCadastro tarefaBatchEmitirBoletimCadastro = new TarefaBatchEmitirBoletimCadastro(processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					tarefaBatchEmitirBoletimCadastro.addParametro("comandoAtividadeAcaoCobranca", cobrancaAcaoAtividadeCronograma);
					tarefaBatchEmitirBoletimCadastro
							.addParametro("acaoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao());

					tarefaBatchEmitirBoletimCadastro.addParametro("atividadeCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAtividade());
					tarefaBatchEmitirBoletimCadastro.addParametro("dataAtual", dataAtual);

					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchEmitirBoletimCadastro));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA:

					TarefaBatchAtualizarComandoAtividadeAcaoCobranca atualizarComandoAtividadeAcaoCobranca = new TarefaBatchAtualizarComandoAtividadeAcaoCobranca(
							processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

					atualizarComandoAtividadeAcaoCobranca.addParametro("grupoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
							.getCobrancaGrupoCronogramaMes().getCobrancaGrupo());
					atualizarComandoAtividadeAcaoCobranca.addParametro("anoMesReferenciaCicloCobranca", cobrancaAcaoAtividadeCronograma
							.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia());
					atualizarComandoAtividadeAcaoCobranca.addParametro("comandoAtividadeAcaoCobranca", cobrancaAcaoAtividadeCronograma);
					atualizarComandoAtividadeAcaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
							Collections.singletonList(cobrancaAcaoAtividadeCronograma.getId()));
					atualizarComandoAtividadeAcaoCobranca.addParametro("acaoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
							.getCobrancaAcao());
					atualizarComandoAtividadeAcaoCobranca.addParametro("indicadorCriterio", new Short("1"));
					atualizarComandoAtividadeAcaoCobranca.addParametro("idCobrancaDocumentoControleGeracao", idCobrancaDocumentoControleGeracao);

					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(atualizarComandoAtividadeAcaoCobranca));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.EMITIR_DOCUMENTO_COBRANCA:

					TarefaBatchEmitirDocumentoCobranca emitirDocumentoCobranca = new TarefaBatchEmitirDocumentoCobranca(processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					emitirDocumentoCobranca.addParametro("grupoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
							.getCobrancaGrupoCronogramaMes().getCobrancaGrupo());
					emitirDocumentoCobranca.addParametro("comandoAtividadeAcaoCobranca", cobrancaAcaoAtividadeCronograma);
					emitirDocumentoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
							Collections.singletonList(cobrancaAcaoAtividadeCronograma.getId()));
					emitirDocumentoCobranca.addParametro("acaoCobranca", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao());
					emitirDocumentoCobranca.addParametro("dataAtual", dataAtual);

					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(emitirDocumentoCobranca));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.ENCERRAR_ORDENS_SERVICO_ACAO_COBRANCA:

					TarefaBatchProcessarEncerramentoOSAcaoCobranca tarefaBatchProcessarEncerramentoOSAcaoCobranca = new TarefaBatchProcessarEncerramentoOSAcaoCobranca(
							processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

					Collection<Integer> colecaoOrdemServicoParaEncerrar = getControladorCobranca().pesquisarOrdemServicoParaEncerrar(
							cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getId());

					if (colecaoOrdemServicoParaEncerrar != null && !colecaoOrdemServicoParaEncerrar.isEmpty()) {

						tarefaBatchProcessarEncerramentoOSAcaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoOrdemServicoParaEncerrar);

						tarefaBatchProcessarEncerramentoOSAcaoCobranca.addParametro("idCobrancaAcaoAtividadeCronograma",
								cobrancaAcaoAtividadeCronograma.getId());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarEncerramentoOSAcaoCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);
					} else {
						throw new ControladorException("atencao.nao.existe.dados.tabela.cronograma");
					}

					break;
				}
			}
		}
		return codigoProcessoIniciadoGerado;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Integer inserirProcessoCobrancaAtividadeEventual(Usuario usuario, Integer codigoProcessoIniciadoGerado, Iterator<Integer> iteratorAtividade)
			throws ControladorException, ParseException, IOException {

		try {

			while (iteratorAtividade.hasNext()) {

				Integer codigoCobrancaAcaoAtividadeComando = iteratorAtividade.next();

				FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
				filtroCobrancaAcaoAtividadeComando.adicionarParametro(new ParametroSimples(FiltroCobrancaAcaoAtividadeComando.ID,
						codigoCobrancaAcaoAtividadeComando));

				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade.processo");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessora");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.documentoTipo");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("superior");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("logradouro");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessoraAlternativa");
				filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessoraAlternativa.documentoTipo");

				CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) Util.retonarObjetoDeColecao(getControladorUtil()
						.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName()));

				ProcessoIniciado processoIniciado = new ProcessoIniciado();
				processoIniciado.setUsuario(usuario);

				if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null && !cobrancaAcaoAtividadeComando.getCobrancaGrupo().equals("")) {
					processoIniciado.setCodigoGrupoProcesso(cobrancaAcaoAtividadeComando.getCobrancaGrupo().getId());
				}
				ProcessoSituacao processoSituacao = new ProcessoSituacao();

				Integer processoSituacaoId = this.verificarAutorizacaoBatch(cobrancaAcaoAtividadeComando.getCobrancaAtividade().getProcesso().getId());
				processoSituacao.setId(processoSituacaoId); // Ver isso
				processoIniciado.setProcessoSituacao(processoSituacao);

				processoIniciado.setProcesso(cobrancaAcaoAtividadeComando.getCobrancaAtividade().getProcesso());
				processoIniciado.setDataHoraAgendamento(new Date());
				processoIniciado.setDataHoraInicio(new Date());
				processoIniciado.setDataHoraComando(cobrancaAcaoAtividadeComando.getComando());

				codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

				processoIniciado.setId(codigoProcessoIniciadoGerado);

				CobrancaDocumentoControleGeracao cobrancaDocumentoControleGeracao = new CobrancaDocumentoControleGeracao(0, 0, new BigDecimal("0.00"),
						new Date(), processoIniciado, null, cobrancaAcaoAtividadeComando);

				Integer idCobrancaDocumentoControleGeracao = (Integer) getControladorUtil().inserir(cobrancaDocumentoControleGeracao);

				FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

				filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
						.getId()));

				filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());
				Date dataAtual = new Date();

				Iterator iterator = processosFuncionaliadade.iterator();
				while (iterator.hasNext()) {
					ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
					FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

					FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
					funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);

					funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
					funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
					funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
					funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

					Collection<Rota> colecaoRotas = new ArrayList();
					if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null) {
						FiltroRota filtroRota = new FiltroRota();
						filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.COBRANCA_GRUPO_ID, cobrancaAcaoAtividadeComando.getCobrancaGrupo()
								.getId()));
						colecaoRotas = getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
					} else {
						try {
							colecaoRotas = repositorioBatch.pesquisarRotasProcessamentoBatchCobrancaGrupoNaoInformado(cobrancaAcaoAtividadeComando.getId());
						} catch (ErroRepositorioException e) {
							e.printStackTrace();
						}
					}

					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {
					case Funcionalidade.GERAR_ATIVIDADE_ACAO_COBRANCA:

						TarefaBatchGerarAtividadeAcaoCobranca acaoCobranca = new TarefaBatchGerarAtividadeAcaoCobranca(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						if (cobrancaAcaoAtividadeComando.getLogradouro() == null) {
							if (cobrancaAcaoAtividadeComando.getSuperior() == null && cobrancaAcaoAtividadeComando.getCliente() == null) {
								if (Util.isVazioOrNulo(colecaoRotas)) {
									throw new ControladorException("atencao.comando.nao.existe.rotas");
								}
							}
						}

						acaoCobranca.addParametro("comandoAtividadeAcaoComando", cobrancaAcaoAtividadeComando);
						acaoCobranca.addParametro("colecaoRotas", colecaoRotas);

						if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
							acaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecaoRotas);
						} else {
							acaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									Collections.singletonList(cobrancaAcaoAtividadeComando.getId()));
						}
						acaoCobranca.addParametro("acaoCobranca", cobrancaAcaoAtividadeComando.getCobrancaAcao());
						acaoCobranca.addParametro("atividadeCobranca", cobrancaAcaoAtividadeComando.getCobrancaAtividade());
						acaoCobranca.addParametro("indicadorCriterio", cobrancaAcaoAtividadeComando.getIndicadorCriterio());
						acaoCobranca.addParametro("criterioCobranca", cobrancaAcaoAtividadeComando.getCobrancaCriterio());
						acaoCobranca.addParametro("cliente", cobrancaAcaoAtividadeComando.getCliente());
						acaoCobranca.addParametro("clienteSuperior", cobrancaAcaoAtividadeComando.getSuperior());
						acaoCobranca.addParametro("clienteRelacaoTipo", cobrancaAcaoAtividadeComando.getClienteRelacaoTipo());
						acaoCobranca.addParametro("anoMesReferenciaInicial", cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial());
						acaoCobranca.addParametro("anoMesReferenciaFinal", cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal());
						acaoCobranca.addParametro("dataVencimentoInicial", cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial());
						acaoCobranca.addParametro("dataVencimentoFinal", cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal());
						acaoCobranca.addParametro("dataAtual", dataAtual);
						acaoCobranca.addParametro("idCobrancaDocumentoControleGeracao", idCobrancaDocumentoControleGeracao);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(acaoCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_BOLETIM_CADASTRO:
						
						TarefaBatchEmitirBoletimCadastro tarefaBatchEmitirBoletimCadastro = new TarefaBatchEmitirBoletimCadastro(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tarefaBatchEmitirBoletimCadastro.addParametro("comandoAtividadeAcaoComando", cobrancaAcaoAtividadeComando);
						tarefaBatchEmitirBoletimCadastro.addParametro("acaoCobranca", cobrancaAcaoAtividadeComando.getCobrancaAcao());
						tarefaBatchEmitirBoletimCadastro.addParametro("atividadeCobranca", cobrancaAcaoAtividadeComando.getCobrancaAtividade());
						tarefaBatchEmitirBoletimCadastro.addParametro("dataAtual", dataAtual);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchEmitirBoletimCadastro));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA:

						TarefaBatchAtualizarComandoAtividadeAcaoCobranca atualizarComandoAtividadeAcaoCobranca = new TarefaBatchAtualizarComandoAtividadeAcaoCobranca(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						atualizarComandoAtividadeAcaoCobranca.addParametro("anoMesReferenciaCicloCobranca", getControladorUtil().pesquisarParametrosDoSistema()
								.getAnoMesFaturamento());
						atualizarComandoAtividadeAcaoCobranca.addParametro("comandoAtividadeAcaoComando", cobrancaAcaoAtividadeComando);
						atualizarComandoAtividadeAcaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								Collections.singletonList(cobrancaAcaoAtividadeComando.getId()));
						atualizarComandoAtividadeAcaoCobranca.addParametro("acaoCobranca", cobrancaAcaoAtividadeComando.getCobrancaAcao());
						atualizarComandoAtividadeAcaoCobranca.addParametro("indicadorCriterio", cobrancaAcaoAtividadeComando.getIndicadorCriterio());
						atualizarComandoAtividadeAcaoCobranca.addParametro("criterioCobranca", cobrancaAcaoAtividadeComando.getCobrancaCriterio());
						atualizarComandoAtividadeAcaoCobranca.addParametro("idCobrancaDocumentoControleGeracao", idCobrancaDocumentoControleGeracao);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(atualizarComandoAtividadeAcaoCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_DOCUMENTO_COBRANCA:

						TarefaBatchEmitirDocumentoCobranca emitirDocumentoCobranca = new TarefaBatchEmitirDocumentoCobranca(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						emitirDocumentoCobranca.addParametro("comandoAtividadeAcaoComando", cobrancaAcaoAtividadeComando);
						emitirDocumentoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								Collections.singletonList(cobrancaAcaoAtividadeComando.getId()));
						emitirDocumentoCobranca.addParametro("acaoCobranca", cobrancaAcaoAtividadeComando.getCobrancaAcao());
						emitirDocumentoCobranca.addParametro("criterioCobranca", cobrancaAcaoAtividadeComando.getCobrancaCriterio());
						emitirDocumentoCobranca.addParametro("dataAtual", dataAtual);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(emitirDocumentoCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					}
				}
			}
			return codigoProcessoIniciadoGerado;

		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		}
	}

	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public void verificarProcessosIniciados() throws ControladorException {
		try {
			Collection<FuncionalidadeIniciada> funcionalidadesParaExecucao = verificarFuncionalidadesIniciadasProntasParaExecucao();
			Iterator<FuncionalidadeIniciada> iterator = funcionalidadesParaExecucao.iterator();

			while (iterator.hasNext()) {
				FuncionalidadeIniciada funcionalidadeIniciada = iterator.next();
				ProcessoIniciado processoIniciado = funcionalidadeIniciada.getProcessoIniciado();
				Integer processoSituacao = processoIniciado.getProcessoSituacao().getId();
				
				if (!processoSituacao.equals(ProcessoSituacao.EM_PROCESSAMENTO)) {
					if (!permiteIniciarProcesso(processoIniciado.getUsuario())) {
						continue;
					}
					
					processoIniciado.setProcessoSituacao(new ProcessoSituacao(ProcessoSituacao.EM_PROCESSAMENTO));
					getControladorUtil().atualizar(processoIniciado);
				}

				funcionalidadeIniciada.setFuncionalidadeSituacao(new FuncionalidadeSituacao(FuncionalidadeSituacao.EM_PROCESSAMENTO));
				funcionalidadeIniciada.setDataHoraInicio(new Date());
				getControladorUtil().atualizar(funcionalidadeIniciada);

				TarefaBatch tarefaBatch = (TarefaBatch) IoUtil.transformarBytesParaObjeto(funcionalidadeIniciada.getTarefaBatch());
				if (tarefaBatch != null) {
					tarefaBatch.agendarTarefaBatch();
				}
				
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void encerrarProcessosIniciados() throws ControladorException {
		try {
			Collection<ProcessoIniciado> colecaoProcessosIniciadosParaEncerramento = this.repositorioBatch.pesquisarProcessosIniciadosProntosParaEncerramento();
			Iterator<ProcessoIniciado> iterator = colecaoProcessosIniciadosParaEncerramento.iterator();

			while (iterator.hasNext()) {
				ProcessoIniciado processoIniciado = iterator.next();
				processoIniciado.setDataHoraTermino(new Date());

				ProcessoSituacao situacao = new ProcessoSituacao();
				situacao.setId(ProcessoSituacao.CONCLUIDO);
				processoIniciado.setProcessoSituacao(situacao);
				getControladorUtil().atualizar(processoIniciado);

			}

			Collection<ProcessoIniciado> colecaoProcessosIniciadosFalha = this.repositorioBatch.pesquisarProcessosIniciadosExecucaoFalha();
			Iterator<ProcessoIniciado> iteratorFalha = colecaoProcessosIniciadosFalha.iterator();

			while (iteratorFalha.hasNext()) {
				ProcessoIniciado processoIniciado = iteratorFalha.next();
				processoIniciado.setDataHoraTermino(new Date());

				ProcessoSituacao situacao = new ProcessoSituacao();
				situacao.setId(ProcessoSituacao.CONCLUIDO_COM_ERRO);
				processoIniciado.setProcessoSituacao(situacao);
				getControladorUtil().atualizar(processoIniciado);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void encerrarFuncionalidadesIniciadas() throws ControladorException {
		try {
			Collection<FuncionalidadeIniciada> colecaoFuncionaldadesIniciadasFalha = this.repositorioBatch.pesquisarFuncionaldadesIniciadasExecucaoFalha();

			if (colecaoFuncionaldadesIniciadasFalha != null && !colecaoFuncionaldadesIniciadasFalha.isEmpty()) {

				Iterator<FuncionalidadeIniciada> iterator = colecaoFuncionaldadesIniciadasFalha.iterator();

				while (iterator.hasNext()) {
					FuncionalidadeIniciada funcionalidadeIniciadaPesquisa = iterator.next();

					FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();

					filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, funcionalidadeIniciadaPesquisa
							.getId()));

					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");
					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoIniciado");
					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade");
					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");

					Collection<FuncionalidadeIniciada> colFuin = getControladorUtil().pesquisar(filtroFuncionalidadeIniciada,
							FuncionalidadeIniciada.class.getName());

					FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colFuin);

					funcionalidadeIniciada.setDataHoraTermino(new Date());

					FuncionalidadeSituacao situacao = new FuncionalidadeSituacao();
					situacao.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
					funcionalidadeIniciada.setFuncionalidadeSituacao(situacao);
					getControladorUtil().atualizar(funcionalidadeIniciada);

				}

			} else {
				Collection<FuncionalidadeIniciada> colecaoFuncionaldadesIniciadasParaEncerramento = new ArrayList();
				colecaoFuncionaldadesIniciadasParaEncerramento = this.repositorioBatch.pesquisarFuncionaldadesIniciadasProntasParaEncerramento();

				if (colecaoFuncionaldadesIniciadasParaEncerramento != null) {
					Iterator iterator = colecaoFuncionaldadesIniciadasParaEncerramento.iterator();

					while (iterator.hasNext()) {
						FuncionalidadeIniciada funcionalidadeIniciadaPesquisa = (FuncionalidadeIniciada) iterator.next();

						FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();

						filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, funcionalidadeIniciadaPesquisa
								.getId()));

						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoIniciado");
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade");
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");

						Collection<FuncionalidadeIniciada> colFuin = getControladorUtil().pesquisar(filtroFuncionalidadeIniciada,
								FuncionalidadeIniciada.class.getName());

						FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colFuin);

						funcionalidadeIniciada.setDataHoraTermino(new Date());

						FuncionalidadeSituacao situacao = new FuncionalidadeSituacao();
						situacao.setId(FuncionalidadeSituacao.CONCLUIDA);
						funcionalidadeIniciada.setFuncionalidadeSituacao(situacao);

						this.atualizarSituacaoFuncionalidadeIniciadaConcluida(funcionalidadeIniciada);

						funcionalidadeIniciada.finalizador();

						getControladorUtil().atualizar(funcionalidadeIniciada);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Inicia a Unidade de Processamento de um processo Batch
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param idUnidadeProcessamento
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int iniciarUnidadeProcessamentoBatch(int idFuncionalidadeIniciada, int idUnidadeProcessamento, int codigoRealUnidadeProcessamento)
			throws ControladorException {

		int retorno = 0;

		if (idFuncionalidadeIniciada > 0) {

			try {

				int funcionalidadesIniciadasComErro = repositorioBatch.pesquisarFuncionaldadesIniciadasConcluidasErro(idFuncionalidadeIniciada);

				if (funcionalidadesIniciadasComErro > 0) {
					throw new ControladorException("Esta Unidade não será executada porque o processo está concluído com erro");
				}
				FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
				filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, idFuncionalidadeIniciada));

				Collection colecaoFuncionalidadesIniciadas = getControladorUtil().pesquisar(filtroFuncionalidadeIniciada,
						FuncionalidadeIniciada.class.getName());

				FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colecaoFuncionalidadesIniciadas);

				try {
					Object tarefa = IoUtil.transformarBytesParaObjeto(funcionalidadeIniciada.getTarefaBatch());
					Collection unidadesJaExecutadas = null;
					if (tarefa instanceof TarefaBatch) {

						TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

						unidadesJaExecutadas = tarefaBatch.getUnidadesJaExecutadas();

						UnidadeIniciada unidadeIniciada = new UnidadeIniciada();

						unidadeIniciada.setFuncionalidadeIniciada(funcionalidadeIniciada);

						UnidadeProcessamento unidadeProcessamento = new UnidadeProcessamento();
						unidadeProcessamento.setId(idUnidadeProcessamento);

						unidadeIniciada.setUnidadeProcessamento(unidadeProcessamento);
						unidadeIniciada.setCodigoRealUnidadeProcessamento(codigoRealUnidadeProcessamento);

						UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
						unidadeSituacao.setId(UnidadeSituacao.EM_PROCESSAMENTO);
						unidadeIniciada.setUnidadeSituacao(unidadeSituacao);

						unidadeIniciada.setDataHoraInicio(new Date());

						retorno = (Integer) getControladorUtil().inserir(unidadeIniciada);

						if (unidadesJaExecutadas != null && !unidadesJaExecutadas.isEmpty()) {

							Iterator iterator = unidadesJaExecutadas.iterator();

							while (iterator.hasNext()) {
								UnidadeIniciada unidadeIniciadaLista = (UnidadeIniciada) iterator.next();

								if ((unidadeIniciadaLista.getCodigoRealUnidadeProcessamento() == codigoRealUnidadeProcessamento)
										&& (unidadeIniciadaLista.getUnidadeSituacao().getId() == UnidadeSituacao.CONCLUIDA)) {

									encerrarUnidadeIniciadaJaExecutada(unidadeIniciadaLista);
								}
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
		return retorno;
	}

	private void encerrarUnidadeIniciadaJaExecutada(UnidadeIniciada unidadeIniciada) throws ControladorException {

		unidadeIniciada.setDataHoraTermino(unidadeIniciada.getDataHoraInicio());

		UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
		unidadeSituacao.setId(UnidadeSituacao.CONCLUIDA);

		unidadeIniciada.setUnidadeSituacao(unidadeSituacao);

		getControladorUtil().atualizar(unidadeIniciada);

		throw new ControladorException("Unidade já executada");

	}

	/**
	 * Encerra a Unidade de Processamento associada a um processamento batch
	 * 
	 * @param excecao
	 * @param idUnidadeIniciada
	 * @param executouComErro
	 */
	@SuppressWarnings("unchecked")
	public void encerrarUnidadeProcessamentoBatch(Throwable excecao, int idUnidadeIniciada, boolean executouComErro) throws ControladorException {

		if (idUnidadeIniciada != 0) {

			FiltroUnidadeIniciada filtroUnidadeIniciada = new FiltroUnidadeIniciada();
			filtroUnidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, idUnidadeIniciada));

			UnidadeIniciada unidadeIniciada = (UnidadeIniciada) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtroUnidadeIniciada,
					UnidadeIniciada.class.getName()));

			unidadeIniciada.setDataHoraTermino(new Date());

			if ((!executouComErro) || (excecao != null && excecao.getMessage() != null && excecao.getMessage().equals("Unidade já executada"))) {
				UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
				unidadeSituacao.setId(UnidadeSituacao.CONCLUIDA);
				unidadeIniciada.setUnidadeSituacao(unidadeSituacao);
			} else {
				UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
				unidadeSituacao.setId(UnidadeSituacao.CONCLUIDA_COM_ERRO);
				unidadeIniciada.setUnidadeSituacao(unidadeSituacao);

				inserirLogExcecaoFuncionalidadeIniciada(unidadeIniciada, excecao);
			}
			getControladorUtil().atualizar(unidadeIniciada);
		} else {
			System.out.println("ATENCAO --- UNIDADE INICIADA POSSUI ID : !" + idUnidadeIniciada + "!");
		}
	}

	/**
	 * Insere uma coleaao de objetos genaricos na base com um flush para cada 50 registros inseridos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void inserirColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos) throws ControladorException {
		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch.inserirColecaoObjetoParaBatch(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Insere uma coleaao de objetos genaricos na base com um flush para cada 50 registros inseridos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	@SuppressWarnings("rawtypes")
	public void inserirColecaoObjetoParaBatchSemTransacao(Collection<? extends Object> colecaoObjetos) throws ControladorException {
		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				Iterator iter = colecaoObjetos.iterator();

				while (iter.hasNext()) {
					Object element = (Object) iter.next();

					repositorioBatch.inserirObjetoParaBatch(element);
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Inseri uma coleção de objetos genéricos na base
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchTransacao(Collection<Object> colecaoObjetos) throws ControladorException {
		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch.inserirColecaoObjetoParaBatchTransacao(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Insere uma coleaao de objetos genaricos na base com um flush para cada 50 registros inseridos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void inserirColecaoObjetoParaBatchGerencial(Collection<? extends Object> colecaoObjetos) throws ControladorException {
		try {
			repositorioBatch.inserirColecaoObjetoParaBatchGerencial(colecaoObjetos);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Atualiza uma coleaao de objetos genaricos na base com um flush para cada 50 registros inseridos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void atualizarColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos) throws ControladorException {
		try {
			repositorioBatch.atualizarColecaoObjetoParaBatch(colecaoObjetos);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Atualiza uma coleaao de objetos genaricos na base com um flush para cada 50 registros inseridos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void atualizarColecaoObjetoParaBatchSemTransacao(Collection<? extends Object> colecaoObjetos) throws ControladorException {
		try {
			repositorioBatch.atualizarColecaoObjetoParaBatch(colecaoObjetos);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Funaao que executa as rotinas de execuaao e fechamento das tarefas batch do sistema
	 */
	public void verificadorProcessosSistema() throws ControladorException {
		verificarProcessosIniciados();
		iniciarRelatoriosAgendados();
		encerrarFuncionalidadesIniciadas();
		encerrarProcessosIniciados();
	}

	/**
	 * Funcao que executa as rotinas de execucao da integração da SAM
	 */
	public void verificadorProcessosIntegracaoUPA() throws ControladorException {
		getControladorIntegracao().receberMovimentoExportacaoFirma();
	}

	/**
	 * Funcao que executa as rotinas de execucao da integração da SAM
	 */
	public void verificadorQueriesDemoradasSistema() throws ControladorException {
		try {
			repositorioBatch.pesquisarQueriesDemoradasSistema();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema");
		}
	}

	public void iniciarRelatoriosAgendados() throws ControladorException {
		try {
			Iterator<byte[]> iterator = repositorioBatch.iniciarRelatoriosAgendados().iterator();

			while (iterator.hasNext()) {
				TarefaRelatorio tarefaRelatorio = (TarefaRelatorio) IoUtil.transformarBytesParaObjeto(iterator.next());
				tarefaRelatorio.agendarTarefaBatch();
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (IOException e) {
			throw new ControladorException("erro.sistema", e);
		} catch (ClassNotFoundException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Retorna todas as Funcionalidades Iniciadas que estao em situacaoo de iniciar o processamento
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<FuncionalidadeIniciada> verificarFuncionalidadesIniciadasProntasParaExecucao() throws ControladorException {
		Collection<FuncionalidadeIniciada> retorno = new ArrayList();

		try {
			Collection<Object[]> funcionalidadesParaExecucao = repositorioBatch.pesquisarFuncionaldadesIniciadasProntasExecucao();

			for (Object[] dados : funcionalidadesParaExecucao) {
				FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) dados[0];

				int quantidadeFuncionalidadesForaOrdem = repositorioBatch.pesquisarQuantidadeFuncionaldadesIniciadasForaOrdemExecucao(
						(int) ((Short) dados[1]), funcionalidadeIniciada.getProcessoIniciado().getId());
				
				if (quantidadeFuncionalidadesForaOrdem == 0 && (!funcionalidadeIniciada.getFuncionalidadeSituacao().getId().equals(FuncionalidadeSituacao.CONCLUIDA))) {
					retorno.add(funcionalidadeIniciada);
				}
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}

	/**
	 * Inicia uma funcionalidade iniciada do relatorio
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void iniciarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada) throws ControladorException {
		try {
			repositorioBatch.iniciarFuncionalidadeIniciadaRelatorio(idFuncionalidadeIniciada);
			repositorioBatch.iniciarProcessoIniciadoRelatorio(idFuncionalidadeIniciada);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Encerra uma funcionalidade iniciada do relatorio
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void encerrarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada, boolean concluiuComErro) throws ControladorException {
		try {
			if (concluiuComErro) {
				repositorioBatch.encerrarFuncionalidadeIniciadaRelatorio(idFuncionalidadeIniciada, FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
				repositorioBatch.encerrarProcessoIniciadoRelatorio(idFuncionalidadeIniciada, ProcessoSituacao.CONCLUIDO_COM_ERRO);
			} else {
				repositorioBatch.encerrarFuncionalidadeIniciadaRelatorio(idFuncionalidadeIniciada, FuncionalidadeSituacao.CONCLUIDA);
				repositorioBatch.encerrarProcessoIniciadoRelatorio(idFuncionalidadeIniciada, ProcessoSituacao.CONCLUIDO);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatarios batch do sistema
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchSistema() throws ControladorException {
		try {
			return repositorioBatch.pesquisarRelatoriosBatchSistema();
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleaao de objetos genaricos na base com um flush para cada 50 registros removidos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos) throws ControladorException {
		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch.removerColecaoObjetoParaBatch(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleaao de objetos genaricos na base com um flush para cada 50 registros removidos.
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removerColecaoObjetoParaBatchSemTransacao(Collection colecaoObjetos) throws ControladorException {
		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch.removerColecaoObjetoParaBatch(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de objetos genéricos na base com um flush para cada 50 registros removidos.
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerObjetoParaBatchSemTransacao(Object objeto) throws ControladorException {
		try {
			repositorioBatch.removerObjetoParaBatch(objeto);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Inicia um processo relacionado com um relatoio que seria processado em batch
	 * 
	 * @throws ControladorException
	 */

	@SuppressWarnings("rawtypes")
	public void iniciarProcessoRelatorio(TarefaRelatorio tarefaRelatorio) throws ControladorException {
		try {
			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(tarefaRelatorio.getUsuario());

			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			processoSituacao.setId(ProcessoSituacao.AGENDADO);
			processoIniciado.setProcessoSituacao(processoSituacao);

			Processo processo = new Processo();
			processo.setId(GerenciadorExecucaoTarefaRelatorio.obterProcessoRelatorio(tarefaRelatorio.getNomeRelatorio()));

			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());

			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {

				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();

				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

				funcionalidadeSituacao.setId(FuncionalidadeSituacao.AGENDADA);
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setDataHoraInicio(new Date());
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				tarefaRelatorio.setIdFuncionalidadeIniciada(funcionalidadeIniciada.getId());

				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaRelatorio));

				getControladorUtil().atualizar(funcionalidadeIniciada);
			}
		} catch (IOException e) {
			throw new SistemaException(e, "Erro Batch Relatario");
		}
	}

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatarios batch do sistema por Usuario
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(int idProcesso) throws ControladorException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoCompletoData = new SimpleDateFormat("dd/MM/yyyy k:mm:ss");

		try {
			Collection<Object[]> retornoMetodo = repositorioBatch.pesquisarRelatoriosBatchPorUsuarioSistema(idProcesso);

			for (Object[] array : retornoMetodo) {

				if (array[3] != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(((Timestamp) array[3]));
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.add(Calendar.DAY_OF_MONTH, 3);

					array[3] = formato.format(calendar.getTime());
				}

				if (array[6] != null) {
					array[6] = formatoCompletoData.format((Date) array[6]);
				}
			}
			return retornoMetodo;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove do sistema todos os relatarios batch que estao na data de expiraaao
	 */
	public void deletarRelatoriosBatchDataExpiracao() throws ControladorException {
		try {
			Calendar dataExpiracao = GregorianCalendar.getInstance();
			dataExpiracao.set(Calendar.SECOND, 59);
			dataExpiracao.set(Calendar.MINUTE, 59);
			dataExpiracao.set(Calendar.HOUR_OF_DAY, 23);
			dataExpiracao.add(Calendar.DAY_OF_MONTH, -3);

			this.repositorioBatch.deletarRelatoriosBatchDataExpiracao(dataExpiracao.getTime());

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCobrancaLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public void marcarProcessosInterrompidos() throws ControladorException {
		FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
		filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.PROCESSO_SITUACAO_ID, ProcessoSituacao.EM_PROCESSAMENTO));

		Collection processosIniciadosParaMarcacao = getControladorUtil().pesquisar(filtroProcessoIniciado, ProcessoIniciado.class.getName());

		Iterator iteratorProcessos = processosIniciadosParaMarcacao.iterator();

		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoSituacao.setId(ProcessoSituacao.EXECUCAO_CANCELADA);
		while (iteratorProcessos.hasNext()) {
			ProcessoIniciado processoIniciado = (ProcessoIniciado) iteratorProcessos.next();

			processoIniciado.setProcessoSituacao(processoSituacao);

			getControladorUtil().atualizar(processoIniciado);

		}

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,
				FuncionalidadeSituacao.EM_PROCESSAMENTO));

		Collection funcionalidadesIniciadasParaMarcacao = getControladorUtil().pesquisar(filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class.getName());

		Iterator iteratorFuncionalidades = funcionalidadesIniciadasParaMarcacao.iterator();

		FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
		funcionalidadeSituacao.setId(FuncionalidadeSituacao.EXECUCAO_CANCELADA);
		while (iteratorFuncionalidades.hasNext()) {
			FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) iteratorFuncionalidades.next();

			funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);

			getControladorUtil().atualizar(funcionalidadeIniciada);

		}

	}

	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorLocalidadeLocal getControladorLocalidade() {
		ControladorLocalidadeLocalHome localHome = null;
		ControladorLocalidadeLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorLocalidadeLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorMicromedicaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorGerencialMicromedicaoLocal getControladorGerencialMicromedicao() {

		ControladorGerencialMicromedicaoLocalHome localHome = null;
		ControladorGerencialMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorGerencialMicromedicaoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_MICROMEDICAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorFinanceiroLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public Object inserirObjetoParaBatchGerencial(Object objeto) throws ControladorException {
		try {
			repositorioBatch.inserirObjetoParaBatchGerencial(objeto);
			return objeto;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoIniciado(ProcessoIniciado processoIniciado, Map<String, Object> dadosProcessamento) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processoIniciado.getProcesso().getId());
			processoSituacao.setId(processoSituacaoId);
			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				try {

					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

					case Funcionalidade.APAGAR_RESUMO_DEVEDORES_DUVIDOSOS:
						TarefaBatchApagarResumoDevedoresDuvidosos dadosApagarResumoDevedoresDuvidosos = new TarefaBatchApagarResumoDevedoresDuvidosos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						Integer anoMesReferenciaContabilApagar = new Integer((String) dadosProcessamento.get("anoMesReferenciaContabil"));

						Collection<Integer> colecaoIdsLocalidadesApagarResumoDevedoresDuvidosos = getControladorFinanceiro()
								.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(anoMesReferenciaContabilApagar);

						dadosApagarResumoDevedoresDuvidosos.addParametro("anoMesReferenciaContabil", anoMesReferenciaContabilApagar);
						dadosApagarResumoDevedoresDuvidosos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoIdsLocalidadesApagarResumoDevedoresDuvidosos);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosApagarResumoDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_DEVEDORES_DUVIDOSOS:
						TarefaBatchGerarResumoDevedoresDuvidosos dadosGerarResumoDevedoresDuvidosos = new TarefaBatchGerarResumoDevedoresDuvidosos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						Integer anoMesReferenciaContabil = new Integer((String) dadosProcessamento.get("anoMesReferenciaContabil"));

						Collection<Integer> colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos = getControladorFinanceiro()
								.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(anoMesReferenciaContabil);

						dadosGerarResumoDevedoresDuvidosos.addParametro("anoMesReferenciaContabil", anoMesReferenciaContabil);
						dadosGerarResumoDevedoresDuvidosos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarResumoDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS:
						TarefaBatchAtualizarResumoDevedoresDuvidosos dadosAtualizarResumoDevedoresDuvidosos = new TarefaBatchAtualizarResumoDevedoresDuvidosos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						Integer anoMesReferenciaContabilAtualiza = new Integer((String) dadosProcessamento.get("anoMesReferenciaContabil"));

						dadosAtualizarResumoDevedoresDuvidosos.addParametro("anoMesReferenciaContabil", anoMesReferenciaContabilAtualiza);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosAtualizarResumoDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_DEVEDORES_DUVIDOSOS:

						TarefaBatchGerarLancamentosContabeisDevedoresDuvidosos dadosGerarLancamentosContabeisDevedoresDuvidosos = new TarefaBatchGerarLancamentosContabeisDevedoresDuvidosos(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						Integer anoMesReferenciaContabilLancamentosContabeis = new Integer((String) dadosProcessamento.get("anoMesReferenciaContabil"));

						Collection<Integer> colecaoIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos = getControladorFinanceiro()
								.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(anoMesReferenciaContabilLancamentosContabeis);

						dadosGerarLancamentosContabeisDevedoresDuvidosos.addParametro("anoMesReferenciaContabil", anoMesReferenciaContabilLancamentosContabeis);
						dadosGerarLancamentosContabeisDevedoresDuvidosos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosGerarLancamentosContabeisDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					default:
					}
				} catch (IOException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return codigoProcessoIniciadoGerado;
	}

	/**
	 * Reinicia uma funcionalidade iniciada
	 * 
	 * @param idsFuncionalidadesIniciadas
	 * @param idProcessoIniciado
	 * @return
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reiniciarFuncionalidadesIniciadas(String[] idsFuncionalidadesIniciadas, Integer idProcessoIniciado) throws ControladorException {

		int i = 0;

		while (i < idsFuncionalidadesIniciadas.length) {
			String idFuncionalidadeIniciada = idsFuncionalidadesIniciadas[i];

			try {
				repositorioBatch.removerUnidadesIniciadas(new Integer(idFuncionalidadeIniciada));
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
			filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, idFuncionalidadeIniciada));
			filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo.processoTipo");

			Collection colecaoFuncionalidadesIniciadas = getControladorUtil().pesquisar(filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class.getName());

			FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colecaoFuncionalidadesIniciadas);
			funcionalidadeIniciada.setDescricaoExcecao(null);
			funcionalidadeIniciada.setDataHoraTermino(null);

			try {
				Object tarefa = IoUtil.transformarBytesParaObjeto(funcionalidadeIniciada.getTarefaBatch());

				if (tarefa instanceof TarefaBatch) {

					TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

					tarefaBatch.setUnidadesJaExecutadas(null);
				}
				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefa));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

			if (funcionalidadeIniciada.getProcessoIniciado().getProcesso().getProcessoTipo().getId().equals(ProcessoTipo.RELATORIO)) {
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.AGENDADA);
			} else {
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
			}

			funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);

			getControladorUtil().atualizar(funcionalidadeIniciada);

			i = i + 1;
		}

		FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
		filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.ID, idProcessoIniciado));
		filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade("processo.processoTipo");

		Collection colecaoProcessosIniciados = getControladorUtil().pesquisar(filtroProcessoIniciado, ProcessoIniciado.class.getName());

		ProcessoIniciado processoIniciado = (ProcessoIniciado) Util.retonarObjetoDeColecao(colecaoProcessosIniciados);

		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		if (processoIniciado.getProcesso().getProcessoTipo().getId().equals(ProcessoTipo.RELATORIO)) {
			processoSituacao.setId(ProcessoSituacao.AGENDADO);
		} else {
			processoSituacao.setId(ProcessoSituacao.EM_ESPERA);
		}

		processoIniciado.setDataHoraTermino(null);
		processoIniciado.setProcessoSituacao(processoSituacao);

		getControladorUtil().atualizar(processoIniciado);
	}

	public void removerColecaoGuiaPagamentoCategoriaParaBatch(Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria) throws ControladorException {
		try {
			if (colecaoGuiaPagamentoCategoria != null && !colecaoGuiaPagamentoCategoria.isEmpty()) {
				repositorioBatch.removerColecaoGuiaPagamentoCategoriaParaBatch(colecaoGuiaPagamentoCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoClienteGuiaPagamentoParaBatch(Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento) throws ControladorException {
		try {
			if (colecaoClienteGuiaPagamento != null && !colecaoClienteGuiaPagamento.isEmpty()) {
				repositorioBatch.removerColecaoClienteGuiaPagamentoParaBatch(colecaoClienteGuiaPagamento);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoGuiaPagamentoParaBatch(Collection<GuiaPagamento> colecaoGuiaPagamento) throws ControladorException {
		try {
			if (colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()) {
				repositorioBatch.removerColecaoGuiaPagamentoParaBatch(colecaoGuiaPagamento);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoDebitoACobrarParaBatch(Collection<DebitoACobrar> colecaoDebitoACobrar) throws ControladorException {
		try {
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
				repositorioBatch.removerColecaoDebitoACobrarParaBatch(colecaoDebitoACobrar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoDebitoACobrarCategoriaParaBatch(Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria) throws ControladorException {
		try {
			if (colecaoDebitoACobrarCategoria != null && !colecaoDebitoACobrarCategoria.isEmpty()) {
				repositorioBatch.removerColecaoDebitoACobrarCategoriaParaBatch(colecaoDebitoACobrarCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de Pagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoPagamento
	 * @throws ControladorException
	 */
	public void removerColecaoPagamentoParaBatch(Collection<Integer> pagamentos) throws ControladorException {
		try {
			repositorioBatch.removerColecaoPagamentoParaBatch(pagamentos);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoDevolucaoParaBatch(Collection<Devolucao> colecaoDevolucao) throws ControladorException {
		try {
			if (colecaoDevolucao != null && !colecaoDevolucao.isEmpty()) {
				repositorioBatch.removerColecaoDevolucaoParaBatch(colecaoDevolucao);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta) throws ControladorException {
		try {
			if (colecaoConta != null && !colecaoConta.isEmpty()) {
				repositorioBatch.removerColecaoContaParaBatch(colecaoConta);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoContaCategoriaParaBatch(Collection<ContaCategoria> colecaoContaCategoria) throws ControladorException {
		try {
			if (colecaoContaCategoria != null && !colecaoContaCategoria.isEmpty()) {
				repositorioBatch.removerColecaoContaCategoriaParaBatch(colecaoContaCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa)
			throws ControladorException {
		try {
			if (colecaoContaCategoriaConsumoFaixa != null && !colecaoContaCategoriaConsumoFaixa.isEmpty()) {
				repositorioBatch.removerColecaoContaCategoriaConsumoFaixaParaBatch(colecaoContaCategoriaConsumoFaixa);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoCreditoRealizadoParaBatch(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ControladorException {
		try {
			if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()) {
				repositorioBatch.removerColecaoCreditoRealizadoParaBatch(colecaoCreditoRealizado);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoDebitoCobradoParaBatch(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ControladorException {
		try {
			if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()) {
				repositorioBatch.removerColecaoDebitoCobradoParaBatch(colecaoDebitoCobrado);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoDebitoCobradoCategoriaParaBatch(Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria) throws ControladorException {

		try {
			if (colecaoDebitoCobradoCategoria != null && !colecaoDebitoCobradoCategoria.isEmpty()) {
				repositorioBatch.removerColecaoDebitoCobradoCategoriaParaBatch(colecaoDebitoCobradoCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoCreditoRealizadoCategoriaParaBatch(Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria)
			throws ControladorException {
		try {
			if (colecaoCreditoRealizadoCategoria != null && !colecaoCreditoRealizadoCategoria.isEmpty()) {
				repositorioBatch.removerColecaoCreditoRealizadoCategoriaParaBatch(colecaoCreditoRealizadoCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoContaImpostosDeduzidosParaBatch(Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos) throws ControladorException {
		try {
			if (colecaoContaImpostosDeduzidos != null && !colecaoContaImpostosDeduzidos.isEmpty()) {
				repositorioBatch.removerColecaoContaImpostosDeduzidosParaBatch(colecaoContaImpostosDeduzidos);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoClienteContaParaBatch(Collection<ClienteConta> colecaoClienteConta) throws ControladorException {
		try {
			if (colecaoClienteConta != null && !colecaoClienteConta.isEmpty()) {
				repositorioBatch.removerColecaoClienteContaParaBatch(colecaoClienteConta);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private ControladorIntegracaoLocal getControladorIntegracao() {
		ControladorIntegracaoLocalHome localHome = null;
		ControladorIntegracaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorIntegracaoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_INTEGRACAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public void removerColecaoCreditoARealizarParaBatch(Collection<CreditoARealizar> colecaoCreditoARealizar) throws ControladorException {
		try {
			if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
				repositorioBatch.removerColecaoCreditoARealizarParaBatch(colecaoCreditoARealizar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void removerColecaoCreditoARealizarCategoriaParaBatch(Collection<Integer> colecaoIdsCreditoARealizar) throws ControladorException {
		try {
			if (colecaoIdsCreditoARealizar != null && !colecaoIdsCreditoARealizar.isEmpty()) {
				repositorioBatch.removerColecaoCreditoARealizarCategoriaParaBatch(colecaoIdsCreditoARealizar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorSpcSerasaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Insere um processo batch ativado por um usuário através de uma funcionalidade comum
	 * 
	 * @param processoIniciado
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public Integer inserirProcessoIniciadoParametrosLivres(Map parametros, int idProcesso, Usuario usuario) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {
			ProcessoIniciado processoIniciado = inserirProcessoIniciadoParametrosLivres(idProcesso, usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(idProcesso);
			processoSituacao.setId(processoSituacaoId);
			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			if (parametros.get("idGrupoFaturamento") != null) {
				String idGrupoFaturamento = (String) parametros.get("idGrupoFaturamento");
				processoIniciado.setCodigoGrupoProcesso(new Integer(idGrupoFaturamento));
			}

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();
			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));
			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);

				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				try {
					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

					case Funcionalidade.GERAR_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE:
						TarefaBatchRelatorioContasBaixadasContabilmente txtContasBaixadasContabilmente = new TarefaBatchRelatorioContasBaixadasContabilmente(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						txtContasBaixadasContabilmente.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						Collection idsSetorComercial = new ArrayList();

						Short tipo = (Short) parametros.get("tipo");
						if (tipo.equals(ConstantesSistema.ANALITICO)) {
							idsSetorComercial = getControladorCadastro().pesquisarIdsCodigosSetorComercial();
						}

						txtContasBaixadasContabilmente.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, idsSetorComercial);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(txtContasBaixadasContabilmente));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA:

						String acao = (String) parametros.get("acao");

						// se gerar cartas
						if (acao.equals("1")) {
							TarefaBatchGerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista cartasGerar = new TarefaBatchGerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

							Collection idsRota = new ArrayList();

							String idGrupoFaturamento = (String) parametros.get("idGrupoFaturamento");
							idsRota = getControladorCobranca().pesquisarRotasPorGrupoFaturamento(new Integer(idGrupoFaturamento));

							cartasGerar.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, idsRota);

							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(cartasGerar));

						} else {
							TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista cartasEmitir = new TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(
									processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

							String idGrupoFaturamento = (String) parametros.get("idGrupoFaturamento");
							cartasEmitir.addParametro("faturamentoGrupo", idGrupoFaturamento);

							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(cartasEmitir));
						}

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_TALELAS_TEMPORARIAS_ATUALIZACAO_CADASTRAL:
						
						TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral tabela = new TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						tabela.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper = (ImovelGeracaoTabelasTemporariasCadastroHelper) parametros
								.get("imovelGeracaoTabelasTemporariasCadastroHelper");

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tabela));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_ARQUIVO_TEXTO_ATUALIZACAO_CADASTRAL:
						
						TarefaBatchGerarArquivoTextoAtualizacaoCadastral arquivoTexto = new TarefaBatchGerarArquivoTextoAtualizacaoCadastral(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						arquivoTexto.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(arquivoTexto));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_BOLETOS:
						
						TarefaBatchEmitirBoletos dadosEmitirBoletos = new TarefaBatchEmitirBoletos(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						String idGrupoFaturamento = (String) parametros.get("idGrupoFaturamento");
						dadosEmitirBoletos.addParametro("faturamentoGrupo", idGrupoFaturamento);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosEmitirBoletos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.RETIFICAR_CONJUNTO_CONTA:

						TarefaBatchRetificarConjuntoContaConsumos dados = new TarefaBatchRetificarConjuntoContaConsumos(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						dados.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dados));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.GERAR_CARTAS_DE_FINAL_DE_ANO:

						String acaoCartasDeFinalDeAno = (String) parametros.get("acao");
						String idGruposFaturamento = (String) parametros.get("idGrupoFaturamento");
						if (acaoCartasDeFinalDeAno.equals("1")) {

							TarefaBatchGerarCartasDeFinalDeAno cartasGerar = new TarefaBatchGerarCartasDeFinalDeAno(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							Collection idsRota = new ArrayList();

							idsRota = getControladorCobranca().pesquisarRotasPorGrupoFaturamento(new Integer(idGruposFaturamento));
							cartasGerar.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, idsRota);

							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(cartasGerar));

						} else {
							TarefaBatchEmitirCartasDeFinalDeAno cartasEmitir = new TarefaBatchEmitirCartasDeFinalDeAno(processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							cartasEmitir.addParametro("faturamentoGrupo", idGruposFaturamento);

							funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(cartasEmitir));
						}

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS:
						TarefaBatchInserirPagamentosFaturasEspeciais faturas = new TarefaBatchInserirPagamentosFaturasEspeciais(processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						faturas.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(faturas));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS:

						TarefaBatchPrescreverDebitosImoveisPublicosManual prescricao = new TarefaBatchPrescreverDebitosImoveisPublicosManual(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						prescricao.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(prescricao));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.PROCESSAR_COMANDO_GERADO:

						TarefaBatchProcessarComandoGerado tarefaBatchProcessarComandoGerado = new TarefaBatchProcessarComandoGerado(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");

						Collection idsLocalidade = null;
						if (tarifaSocialComandoCarta.getLocalidade() != null && tarifaSocialComandoCarta.getLocalidade().getId() != null) {
							idsLocalidade = new ArrayList();
							idsLocalidade.add(tarifaSocialComandoCarta.getLocalidade().getId());

						} else if (tarifaSocialComandoCarta.getGerenciaRegional() != null && tarifaSocialComandoCarta.getGerenciaRegional().getId() != null
								&& tarifaSocialComandoCarta.getUnidadeNegocio() != null && tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null) {

							idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorGerenciaEUnidade(
									tarifaSocialComandoCarta.getGerenciaRegional().getId(), tarifaSocialComandoCarta.getUnidadeNegocio().getId());

						} else if (tarifaSocialComandoCarta.getGerenciaRegional() != null && tarifaSocialComandoCarta.getGerenciaRegional().getId() != null) {
							idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorGerencia(tarifaSocialComandoCarta.getGerenciaRegional().getId());

						} else if (tarifaSocialComandoCarta.getUnidadeNegocio() != null && tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null) {
							idsLocalidade = getControladorCadastro()
									.pesquisarLocalidadesPorUnidadeNegocio(tarifaSocialComandoCarta.getUnidadeNegocio().getId());

						} else {
							idsLocalidade = getControladorCadastro().pesquisarLocalidade();
						}

						if (idsLocalidade == null || idsLocalidade.isEmpty()) {
							throw new ControladorException("atencao.nao_existe_dados_filtro");
						}

						tarefaBatchProcessarComandoGerado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, idsLocalidade);
						tarefaBatchProcessarComandoGerado.addParametro("tarifaSocialComandoCarta", tarifaSocialComandoCarta);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarComandoGerado));
						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.SELECIONAR_COMANDO_RETIRAR_IMOVEL_TARIFA_SOCIAL:

						TarefaBatchRetirarImovelTarifaSocial tarefaBatchRetirarImovelTarifaSocial = new TarefaBatchRetirarImovelTarifaSocial(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						TarifaSocialComandoCarta tsccarta = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");

						tarefaBatchRetirarImovelTarifaSocial.addParametro("tarifaSocialComandoCarta", tsccarta);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchRetirarImovelTarifaSocial));
						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA:
						TarefaBatchEncerrarComandosDeCobrancaPorEmpresa dadosEncerrarComandos = new TarefaBatchEncerrarComandosDeCobrancaPorEmpresa(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						String idEmpresa = (String) parametros.get("idEmpresa");
						Integer idRegistroComando = (Integer) parametros.get("idRegistro");
						Integer idCobrancaSituacao = (Integer) parametros.get("idCobrancaSituacao");

						dadosEncerrarComandos.addParametro("idEmpresa", idEmpresa);
						dadosEncerrarComandos.addParametro("usuario", usuario);
						dadosEncerrarComandos.addParametro("idRegistro", idRegistroComando);
						dadosEncerrarComandos.addParametro("idCobrancaSituacao", idCobrancaSituacao);

						Collection colecao = new ArrayList();
						colecao.add(idRegistroComando);

						dadosEncerrarComandos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colecao);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosEncerrarComandos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.RECEPCIONAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA:
						TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca = new TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						String idEmpresaComando = (String) parametros.get("idEmpresa");
						StringBuilder stringBuilder = (StringBuilder) parametros.get("stringBuilder");
						String nomeArquivo = (String) parametros.get("nomeArquivo");

						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca.addParametro("idEmpresa", idEmpresaComando);
						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca.addParametro("usuario", usuario);
						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca.addParametro("stringBuilder", stringBuilder);
						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca.addParametro("nomeArquivo", nomeArquivo);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ENCERRAR_COMANDO_OS_SELETIVA_INSPECAO_ANORMALIDADE:
						TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade dadosEncerrarComando = new TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						Short idMotivoEncerramento = (Short) parametros.get("idMotivoEncerramento");
						Integer idRegistroComandoOS = (Integer) parametros.get("idRegistro");

						dadosEncerrarComando.addParametro("idMotivoEncerramento", idMotivoEncerramento);
						dadosEncerrarComando.addParametro("usuario", usuario);
						dadosEncerrarComando.addParametro("idRegistro", idRegistroComandoOS);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(dadosEncerrarComando));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
					}
				} catch (IOException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}

		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		}
		return codigoProcessoIniciadoGerado;
	}

	/**
	 * Cria um ProcessoIniciado para os processos batch iniciados através da interface comum
	 * 
	 * @param usuarioUsuário que iniciou o processo
	 * @return
	 */
	private ProcessoIniciado inserirProcessoIniciadoParametrosLivres(int idProcesso, Usuario usuario) {
		ProcessoIniciado processoIniciado = new ProcessoIniciado();

		Processo processo = new Processo();
		processo.setId(idProcesso);
		processoIniciado.setDataHoraAgendamento(new Date());

		ProcessoSituacao processoSituacao = new ProcessoSituacao();

		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);
		processoIniciado.setUsuario(usuario);

		return processoIniciado;
	}

	private void inserirLogExcecaoFuncionalidadeIniciada(UnidadeIniciada unidadeIniciada, Throwable excecao) {
		try {
			repositorioBatch.inserirLogExcecaoFuncionalidadeIniciada(unidadeIniciada, excecao);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}

	public void atualizarSituacaoFuncionalidadeIniciadaConcluida(FuncionalidadeIniciada funcionalidadeIniciada) {
		try {
			repositorioBatch.atualizarSituacaoFuncionalidadeIniciadaConcluida(funcionalidadeIniciada);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Continua o processamento de um batch
	 * 
	 * @param idsFuncionalidadesIniciadas
	 * @param idProcessoIniciado
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void continuarFuncionalidadesIniciadas(String[] idsFuncionalidadesIniciadas, Integer idProcessoIniciado) throws ControladorException {
		String idFuncionalidadeIniciada = idsFuncionalidadesIniciadas[0];

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.ID, idFuncionalidadeIniciada));
		filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo.processoTipo");
		filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");
		filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("unidadesIniciadas");

		Collection colecaoFuncionalidadesIniciadas = getControladorUtil().pesquisar(filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class.getName());

		FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colecaoFuncionalidadesIniciadas);

		int funcionalidadeSituacaoId = funcionalidadeIniciada.getFuncionalidadeSituacao().getId();

		if ((funcionalidadeSituacaoId != (FuncionalidadeSituacao.CONCLUIDA_COM_ERRO))
				&& (funcionalidadeSituacaoId != (FuncionalidadeSituacao.EXECUCAO_CANCELADA))) {
			throw new ControladorException("erro.batch.opcao_invalida");

		}

		funcionalidadeIniciada.setDescricaoExcecao(null);
		funcionalidadeIniciada.setDataHoraTermino(null);
		funcionalidadeIniciada.setDescricaoExcecao(null);

		FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

		if (funcionalidadeIniciada.getProcessoIniciado().getProcesso().getProcessoTipo().getId().equals(ProcessoTipo.RELATORIO)) {
			throw new ControladorException("erro.batch.opcao_invalida");
		} else {
			funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
		}

		funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);

		FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
		filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.ID, idProcessoIniciado));
		filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade("processo.processoTipo");

		Collection colecaoProcessosIniciados = getControladorUtil().pesquisar(filtroProcessoIniciado, ProcessoIniciado.class.getName());

		ProcessoIniciado processoIniciado = (ProcessoIniciado) Util.retonarObjetoDeColecao(colecaoProcessosIniciados);

		ProcessoSituacao processoSituacao = new ProcessoSituacao();

		processoSituacao.setId(ProcessoSituacao.EM_ESPERA);

		processoIniciado.setDataHoraTermino(null);
		processoIniciado.setProcessoSituacao(processoSituacao);

		getControladorUtil().atualizar(processoIniciado);

		try {
			Object tarefa = IoUtil.transformarBytesParaObjeto(funcionalidadeIniciada.getTarefaBatch());

			if (tarefa instanceof TarefaBatch) {

				TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

				tarefaBatch.setUnidadesJaExecutadas(funcionalidadeIniciada.getUnidadesIniciadas());

			} else {
				throw new ControladorException("erro.batch.opcao_invalida");
			}

			funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefa));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			repositorioBatch.removerUnidadesIniciadas(new Integer(idFuncionalidadeIniciada));
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		getControladorUtil().atualizar(funcionalidadeIniciada);

	}

	public boolean verificarProcessoEmExecucao(Integer idProcesso) throws ControladorException {
		try {
			return this.repositorioBatch.verificarProcessoEmExecucao(idProcesso);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Continua o processamento de um batch
	 * 
	 * @param ids
	 * @param idEmpresa
	 * @param idFuncionalidadeIniciada
	 * @param usuario
	 */
	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoIniciadoContasCobranca(Collection ids, Integer idEmpresa, Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		Integer[] idsRegistros = new Integer[ids.size()];
		Iterator colecaoIteratorIDS = ids.iterator();
		while (colecaoIteratorIDS.hasNext()) {
			for (int i = 0; i < ids.size(); i++) {
				Integer id = (Integer) colecaoIteratorIDS.next();
				idsRegistros[i] = id;
			}
		}

		try {
			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuario);
			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			processoSituacao.setId(ProcessoSituacao.EM_ESPERA);
			processoIniciado.setProcessoSituacao(processoSituacao);
			Processo processo = new Processo();
			processo.setId(Processo.GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_EMPRESA);
			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();
			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));
			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				TarefaBatchGerarArquivoTextoContasCobrancaEmpresa tarefaBatchGerarArquivoTextoContasCobrancaEmpresa = new TarefaBatchGerarArquivoTextoContasCobrancaEmpresa(
						processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

				tarefaBatchGerarArquivoTextoContasCobrancaEmpresa.addParametro("idsRegistros", ids);
				tarefaBatchGerarArquivoTextoContasCobrancaEmpresa.addParametro("idEmpresa", idEmpresa);

				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoContasCobrancaEmpresa));

				getControladorUtil().atualizar(funcionalidadeIniciada);

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return codigoProcessoIniciadoGerado;
	}

	/**
	 * Continua o processamento de um batch
	 * 
	 * @param ids
	 * @param idEmpresa
	 * @param idFuncionalidadeIniciada
	 * @param usuario
	 */
	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoIniciadoRelatorioPagamentosContasCobranca(RelatorioPagamentosContasCobrancaEmpresaHelper helper, int opcaoRelatorio,
			Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();

			if (opcaoRelatorio == 1) {
				processo.setId(Processo.GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA_SINTETICO);
			} else {
				processo.setId(Processo.GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA);
			}

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa = new TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa(
						processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

				tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa.addParametro("helper", helper);
				tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa.addParametro("opcaoRelatorio", opcaoRelatorio);

				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa));

				getControladorUtil().atualizar(funcionalidadeIniciada);

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigoProcessoIniciadoGerado;
	}

	@SuppressWarnings("rawtypes")
	public Integer inserirAtualizarMovimentacaoHidrometroIdsBatch(MovimentoHidrometroHelper helper) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(helper.getUsuario());

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();
			processo.setId(Processo.GERAR_MOVIMENTO_HIDROMETRO);

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);

				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				TarefaBatchGerarMovimentoHidrometro tarefaBatchGerarMovimentoHidrometro = new TarefaBatchGerarMovimentoHidrometro(
						processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

				tarefaBatchGerarMovimentoHidrometro.addParametro("helper", helper);
				tarefaBatchGerarMovimentoHidrometro.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
						helper.getColecaoHidrometroSelecionado());

				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarMovimentoHidrometro));

				getControladorUtil().atualizar(funcionalidadeIniciada);

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigoProcessoIniciadoGerado;
	}

	/**
	 * Atualiza hidrometros em batch
	 * 
	 * @author Hugo Amorim
	 * @date 08/06/2009
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer inserirProcessoAtualizarConjuntoHidrometro(String fixo, String inicialFixo, String finalFixo, Hidrometro hidrometroAtualizado,
			Usuario usuarioLogado, Integer totalRegistros) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuarioLogado);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();

			processo.setId(Processo.ATUALIZAR_CONJUNTO_HIDROMETRO);

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) Util.retonarObjetoDeColecao(processosFuncionaliadade);

			int qtdPaginas = Util.dividirArredondarResultadoCima(totalRegistros, 500);

			Collection colIndice = new ArrayList();

			for (int i = 1; i <= qtdPaginas; i++) {
				colIndice.add(new Integer(i));
			}

			FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

			FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
			funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
			funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);

			funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

			funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);

			funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

			TarefaBatchAtualizarConjuntoHidrometro tarefaBatchAtualizarConjuntoHidrometro = new TarefaBatchAtualizarConjuntoHidrometro(
					processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

			tarefaBatchAtualizarConjuntoHidrometro.addParametro("fixo", fixo);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("fixoInicial", inicialFixo);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("fixoFinal", finalFixo);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("hidrometroAtualizado", hidrometroAtualizado);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("usuario", usuarioLogado);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("qtdPaginas", qtdPaginas);

			tarefaBatchAtualizarConjuntoHidrometro.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colIndice);

			funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchAtualizarConjuntoHidrometro));

			getControladorUtil().atualizar(funcionalidadeIniciada);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigoProcessoIniciadoGerado;
	}

	/**
	 * Inicia um processo relacionado com um relatoio que seria processado em
	 * batch dependendo de critérios de autorização
	 * 
	 * @throws ControladorException
	 */

	@SuppressWarnings("rawtypes")
	public void iniciarProcessoRelatorioControleAutorizacao(TarefaRelatorio tarefaRelatorio) throws ControladorException {
		try {
			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(tarefaRelatorio.getUsuario());
			int idProcesso = GerenciadorExecucaoTarefaRelatorio.obterProcessoRelatorio(tarefaRelatorio.getNomeRelatorio());

			if (!repositorioBatch.validarAutorizacaoInserirRelatorioBatch(tarefaRelatorio.getUsuario(), idProcesso)) {
				throw new ControladorException("atencao.numero.registro.controle_autorizacao_relatorio");
			}

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(idProcesso);
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);

			Processo processo = new Processo();
			processo.setId(idProcesso);

			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {

				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();

				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

				Integer funcionalidadeSituacaoId = this.verificarAutorizacaoRelatorioBatch(idProcesso);
				funcionalidadeSituacao.setId(funcionalidadeSituacaoId);
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setDataHoraInicio(new Date());
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				tarefaRelatorio.setIdFuncionalidadeIniciada(funcionalidadeIniciada.getId());

				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaRelatorio));

				getControladorUtil().atualizar(funcionalidadeIniciada);
			}
		} catch (IOException e) {
			throw new SistemaException(e, "Erro Batch Relatorio");
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new SistemaException(e, "Erro Batch Relatorio");
		}
	}

	/**
	 * Autoriza Processo Iniciado
	 * 
	 * @param ProcessoIniciado
	 */

	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado, Integer processoSituacao, Integer funcionalidadeSituacao)
			throws ControladorException {
		try {
			this.repositorioBatch.autorizarProcessoIniciado(processoIniciado, processoSituacao);
			this.repositorioBatch.autorizarFuncionalidadeIniciada(processoIniciado, funcionalidadeSituacao);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

//	@SuppressWarnings({ "rawtypes", "rawtypes" })
//	public Integer inserirProcessoIniciadoPagamentosContasCobranca(Integer idEmpresa, Integer referenciaInicial, Integer referenciaFinal, Usuario usuario)
//			throws ControladorException {
//
//		Integer codigoProcessoIniciadoGerado = null;
//
//		try {
//
//			ProcessoIniciado processoIniciado = new ProcessoIniciado();
//			processoIniciado.setUsuario(usuario);
//
//			ProcessoSituacao processoSituacao = new ProcessoSituacao();
//
//			Processo processo = new Processo();
//
//			processo.setId(Processo.GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA);
//
//			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
//			processoSituacao.setId(processoSituacaoId);
//
//			processoIniciado.setProcessoSituacao(processoSituacao);
//			processoIniciado.setProcesso(processo);
//			processoIniciado.setDataHoraAgendamento(new Date());
//			processoIniciado.setDataHoraInicio(new Date());
//			processoIniciado.setDataHoraComando(new Date());
//
//			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);
//
//			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();
//
//			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
//					.getId()));
//
//			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
//					ConstantesSistema.INDICADOR_USO_ATIVO));
//
//			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());
//
//			Iterator iterator = processosFuncionaliadade.iterator();
//
//			while (iterator.hasNext()) {
//				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
//				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
//
//				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
//				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
//				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
//
//				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
//
//				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
//
//				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));
//
//				TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa = new TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa(
//						processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
//
//				Collection colecaoUnidadeNegocio = getControladorCobranca().obterUnidadeNegocioPagamentosEmpresaCobrancaConta();
//
//				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
//						colecaoUnidadeNegocio);
//
//				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa.addParametro("referenciaInicial", referenciaInicial);
//				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa.addParametro("referenciaFinal", referenciaFinal);
//				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa.addParametro("idEmpresa", idEmpresa);
//
//
//				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa));
//
//				getControladorUtil().atualizar(funcionalidadeIniciada);
//
//				break;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return codigoProcessoIniciadoGerado;
//	}

	/**
	 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
	 */
	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoGerarTxtContasProcessosEspeciais(String anoMes, Integer idCliente, Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();
			processo.setId(Processo.GERAR_ARQUIVO_TEXTO_CONTAS_PROJETOS_ESPECIAIS);

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);

				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				TarefaBatchGerarArquivoTextoContasProjetosEspeciais tarefaBatchGerarArquivoTextoContasProjetosEspeciais = new TarefaBatchGerarArquivoTextoContasProjetosEspeciais(
						processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

				tarefaBatchGerarArquivoTextoContasProjetosEspeciais.addParametro("anoMes", anoMes);
				tarefaBatchGerarArquivoTextoContasProjetosEspeciais.addParametro("idCliente", idCliente);

				funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoContasProjetosEspeciais));

				getControladorUtil().atualizar(funcionalidadeIniciada);

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigoProcessoIniciadoGerado;
	}

	/**
	 * Atualiza um objeto genérico na base
	 * 
	 * @param objetoParaAtualizar
	 * @throws ControladorException
	 */
	public void atualizarObjetoParaBatch(Object objetoParaAtualizar) throws ControladorException {
		try {
			repositorioBatch.atualizarObjetoParaBatch(objetoParaAtualizar);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Insere uma objeto genérico na base
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatch(Object objeto) throws ControladorException {
		try {
			return repositorioBatch.inserirObjetoParaBatch(objeto);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Insere uma objeto genérico na base, sem controle transacional
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatchSemTransacao(Object objeto) throws ControladorException {
		try {
			return repositorioBatch.inserirObjetoParaBatch(objeto);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Metodo utilizado para gerar txt da declaração anual de debitos.
	 * 
	 * @param idFaturamentoGrupo
	 */
	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoGerarTxtDeclaracaoQuitacaoDebitos(Integer idGrupoFaturamento, Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;
		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();
			processo.setId(Processo.GERAR_DECLARACAO_QUITACAO_DEBITOS);

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());
			processoIniciado.setCodigoGrupoProcesso(idGrupoFaturamento);

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {

				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

				case Funcionalidade.GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

					TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos(
							processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

					FiltroRota filtroRota = new FiltroRota();
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.FATURAMENTO_GRUPO_ID, idGrupoFaturamento));
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoRotasParaExecucao = this.getControladorUtil().pesquisar(filtroRota, Rota.class.getName());

					tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
							colecaoRotasParaExecucao);

					SistemaParametro sistemaParametroQuitacao = getControladorUtil().pesquisarParametrosDoSistema();

					tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos.addParametro("SistemaParametros", sistemaParametroQuitacao);

					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;
				case Funcionalidade.GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

					TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
							processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

					Collection<Empresa> colecaoEmpresas = getControladorFaturamento().pesquisarEmpresasParaGeraracaoExtrato(idGrupoFaturamento);

					tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
							colecaoEmpresas);

					tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos.addParametro("idGrupoFaturamento", idGrupoFaturamento);

					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				default:
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigoProcessoIniciadoGerado;
	}

	@SuppressWarnings("rawtypes")
	public Collection retornaProcessoFuncionalidadeEmExecucao() throws ControladorException {

		Collection colecao = null;

		try {
			colecao = this.repositorioBatch.retornaProcessoFuncionalidadeEmExecucao();
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return colecao;
	}

	private ControladorGerencialFaturamentoLocal getControladorGerencialFaturamento() {
		ControladorGerencialFaturamentoLocalHome localHome = null;
		ControladorGerencialFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorGerencialFaturamentoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Insere um processo batch ativado por um usuário através de uma funcionalidade comum
	 * 
	 * @param processoIniciado
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(Map parametros, int idProcesso, Usuario usuario) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = inserirProcessoIniciadoParametrosLivres(idProcesso, usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(idProcesso);
			processoSituacao.setId(processoSituacaoId);

			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			if (parametros.get("idGrupoFaturamento") != null) {
				String idGrupoFaturamento = (String) parametros.get("idGrupoFaturamento");
				processoIniciado.setCodigoGrupoProcesso(new Integer(idGrupoFaturamento));
			}

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil().inserir(processoIniciado);

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO, processoIniciado.getProcesso()
					.getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade, ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.AGENDADA);
			
				funcionalidadeIniciada.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil().inserir(funcionalidadeIniciada));

				try {

					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

					case Funcionalidade.GERAR_CARTA_TARIFA_SOCIAL:

						TarefaBatchGerarCartaTarifaSocial tarefaBatchGerarCartaTarifaSocial = new TarefaBatchGerarCartaTarifaSocial(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						TarifaSocialComandoCarta tscc = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");

						tarefaBatchGerarCartaTarifaSocial.addParametro("tarifaSocialComandoCarta", tscc);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarCartaTarifaSocial));
						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.PROCESSAR_COMANDO_GERADO:

						TarefaBatchProcessarComandoGerado tarefaBatchProcessarComandoGerado = new TarefaBatchProcessarComandoGerado(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");

						Collection idsLocalidade = null;
						if (tarifaSocialComandoCarta.getLocalidade() != null && tarifaSocialComandoCarta.getLocalidade().getId() != null) {
							idsLocalidade = new ArrayList();
							idsLocalidade.add(tarifaSocialComandoCarta.getLocalidade().getId());
						} else if (tarifaSocialComandoCarta.getGerenciaRegional() != null && tarifaSocialComandoCarta.getGerenciaRegional().getId() != null) {
							idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorGerencia(tarifaSocialComandoCarta.getGerenciaRegional().getId());
						} else if (tarifaSocialComandoCarta.getUnidadeNegocio() != null && tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null) {
							idsLocalidade = getControladorCadastro()
									.pesquisarLocalidadesPorUnidadeNegocio(tarifaSocialComandoCarta.getUnidadeNegocio().getId());
						} else {
							idsLocalidade = getControladorCadastro().pesquisarLocalidade();
						}

						tarefaBatchProcessarComandoGerado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, idsLocalidade);
						tarefaBatchProcessarComandoGerado.addParametro("tarifaSocialComandoCarta", tarifaSocialComandoCarta);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarComandoGerado));
						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.EMISSAO_ORDENS_SELETIVAS:
						TarefaBatchGerarTxtOsInspecaoAnormalidade tarefaBatchGerarTxtOsInspecaoAnormalidade = new TarefaBatchGerarTxtOsInspecaoAnormalidade(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						Integer idComandoOrdemSeletiva = (Integer) parametros.get("idComandoOrdemSeletiva");
						Integer qtdAnormalidadesConsecutivas = (Integer) parametros.get("qtdAnormalidadesConsecutivas");

						tarefaBatchGerarTxtOsInspecaoAnormalidade.addParametro("idComandoOrdemSeletiva", idComandoOrdemSeletiva);
						tarefaBatchGerarTxtOsInspecaoAnormalidade.addParametro("qtdAnormalidadesConsecutivas", qtdAnormalidadesConsecutivas);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarTxtOsInspecaoAnormalidade));
						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
					}
				} catch (IOException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		}
		return codigoProcessoIniciadoGerado;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer verificarAutorizacaoBatch(Integer processoId) {

		Integer situacaoBatch = null;

		FiltroProcesso filtroProcesso = new FiltroProcesso();
		filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, processoId));

		Collection colecaoProcesso = Fachada.getInstancia().pesquisar(filtroProcesso, Processo.class.getName());

		Processo processo = (Processo) Util.retonarObjetoDeColecao(colecaoProcesso);

		if (processo.getProcessoTipo().getId().intValue() == ProcessoTipo.RELATORIO) {
			situacaoBatch = ProcessoSituacao.AGENDADO;
		} else {
			situacaoBatch = ProcessoSituacao.EM_ESPERA;
		}

		if (processo != null) {
			if (processo.getIndicadorAutorizacao() == 1) {
				situacaoBatch = ProcessoSituacao.AGUARDANDO_AUTORIZACAO;
			}
		}
		return situacaoBatch;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer verificarAutorizacaoRelatorioBatch(Integer processoId) {

		FiltroProcesso filtroProcesso = new FiltroProcesso();
		filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, processoId));

		Collection colecaoProcesso = Fachada.getInstancia().pesquisar(filtroProcesso, Processo.class.getName());

		Processo processo = (Processo) Util.retonarObjetoDeColecao(colecaoProcesso);

		Integer situacaoBatch = FuncionalidadeSituacao.AGENDADA;

		if (processo != null) {
			if (processo.getIndicadorAutorizacao() == 1) {
				situacaoBatch = FuncionalidadeSituacao.AGUARDANDO_AUTORIZACAO;
			}
		}
		return situacaoBatch;
	}

	public FaturamentoAtividadeCronograma pesquisarProcessoIniciadoParaGrupo(Integer idGrupo, Integer referencia, Integer idAtividadeFaturamento)
			throws ControladorException {
		FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;

		try {
			faturamentoAtividadeCronograma = this.repositorioBatch.pesquisarProcessoIniciadoParaGrupo(idGrupo, referencia, idAtividadeFaturamento);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return faturamentoAtividadeCronograma;
	}

	@SuppressWarnings("rawtypes")
	public String getIpNovoBatch() {
		FiltroSegurancaParametro filtroSegurancaParametro = new FiltroSegurancaParametro();
		filtroSegurancaParametro.adicionarParametro(new ParametroSimples(FiltroSegurancaParametro.NOME,
				SegurancaParametro.NOME_PARAMETRO_SEGURANCA.IP_NOVO_BATCH.toString()));

		Collection parametros = Fachada.getInstancia().pesquisar(filtroSegurancaParametro, SegurancaParametro.class.getName());

		return ((SegurancaParametro) parametros.iterator().next()).getValor();
	}

	public Usuario obterUsuarioQueDisparouProcesso(Integer idFuncionalidadeIniciada) throws ControladorException {
		try {
			return repositorioBatch.obterUsuarioQueDisparouProcesso(idFuncionalidadeIniciada);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao obter usuario que disparou processo", e);
		}
	}
	
	private boolean permiteIniciarProcesso(Usuario usuario) throws ControladorException {
		try {
			Short quantidade = repositorioBatch.pesquisarQuantidadeBatchPorUsuario(usuario.getId());
			Short limite = usuario.getLimiteBatch();
			
			if (limite != null && limite > 0) {
				return quantidade >= limite ? false : true;
			} else {
				return true;
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("Erro ao verificar se permite inserir processo", e);
		}
	}
}
