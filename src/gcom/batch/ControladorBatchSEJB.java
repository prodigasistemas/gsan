package gcom.batch;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.Pagamento;
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
import gcom.batch.cobranca.TarefaBatchAtualizarPagamentosContasCobranca;
import gcom.batch.cobranca.TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga;
import gcom.batch.cobranca.TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista;
import gcom.batch.cobranca.TarefaBatchEmitirCartasDeFinalDeAno;
import gcom.batch.cobranca.TarefaBatchEmitirDocumentoCobranca;
import gcom.batch.cobranca.TarefaBatchGerarArquivoTextoContasCobrancaEmpresa;
import gcom.batch.cobranca.TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa;
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
import gcom.batch.cobranca.cobrancaporresultado.TarefaBatchEncerrarComandosDeCobrancaPorEmpresa;
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

/**
 * Controlador que possui os metodos de negocio de toda a parte que da suporte
 * ao batch
 * 
 * @author Rodrigo Silveira
 * @date 28/07/2006
 */
public class ControladorBatchSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	SessionContext sessionContext;

	private IRepositorioBatch repositorioBatch = null;

	private IRepositorioMicromedicao repositorioMicromedicao = null;

	public void ejbCreate() throws CreateException {
		repositorioBatch = RepositorioBatchHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
	}

	/**
	 * < <Descricao do metodo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descricao do metodo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descricao do metodo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Insere um processo iniciado no sistema e suas funcionalidades iniciadas
	 * 
	 * @author Rodrigo Silveira
	 * @date 28/07/2006
	 * 
	 * @param processoIniciado
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	public Integer inserirProcessoIniciado(ProcessoIniciado processoIniciado)
			throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		SistemaParametro sistemaParametros = getControladorUtil()
				.pesquisarParametrosDoSistema();

		Integer anoMesFaturamentoSistemaParametro = sistemaParametros
				.getAnoMesFaturamento();

		/** Colecao de ids de localidades para encerrar a arrecadacacao do mes */
		Collection<Integer> colecaoIdsLocalidadesEncerrarArrecadacaoMes = getControladorArrecadacao()
				.pesquisarIdsLocalidadeComPagamentosOuDevolucoes();

		try {

			// Todos os processo serao iniciados com a situacao EM_ESPERA para q
			// sejam executados o mais cedo possivel
			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processoIniciado.getProcesso().getId());
			processoSituacao.setId(processoSituacaoId);
			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				// Seta os parametros da funcionalidadeIniciada
				// ----------------------------------------------------------
				// Lista dos possiveis processos eventuais ou mensais
				// ----------------------------------------------------------
				try {
					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {

					case Funcionalidade.GERAR_RESUMO_DIARIO_NEGATIVACAO:
						TarefaBatchGerarResumoDiarioNegativacao gerarResumoDiarioNegativacao = new TarefaBatchGerarResumoDiarioNegativacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

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

							NegativacaoCriterio negativacaoCriterio = getControladorSpcSerasa().pesquisarNegativacaoCriterio(
									negativacaoComando.getId());

							Negativador negativador = negativacaoComando.getNegativador();
							NegativadorContrato negativadorContrato = getControladorSpcSerasa().consultarNegativadorContratoVigente(negativador.getId());

							Collection colecaoParametroNegativacaoCriterio = (Collection) getControladorSpcSerasa()
									.pesquisarParametroNegativacaoCriterio(negativacaoCriterio.getId());

							Collection rotas = null;
							Object[] parametroNegCrit = null;

							if (negativacaoComando.getComandoSimulacao() != null
									&& !negativacaoComando.getComandoSimulacao().equals("")) {
								
								rotas = getControladorSpcSerasa().pesquisarRotasImoveisComandoSimulacao(
										negativacaoComando.getComandoSimulacao().getId());
								
							} else if (negativacaoCriterio.getCliente() != null) {
								rotas = getControladorSpcSerasa().pesquisarRotasImoveis();
							} else {
								if (colecaoParametroNegativacaoCriterio != null && !colecaoParametroNegativacaoCriterio.isEmpty()) {

									for (Iterator iteratorColecaoParametroNegativacaoCriterio = colecaoParametroNegativacaoCriterio.iterator(); iteratorColecaoParametroNegativacaoCriterio.hasNext();) {

										parametroNegCrit = (Object[]) iteratorColecaoParametroNegativacaoCriterio.next();

										if (parametroNegCrit[0] != null) {
											// Condição 1
											rotas = getControladorSpcSerasa().pesquisarRotasPorCobrancaGrupoParaNegativacao(negativacaoCriterio);
											break;
										} else if (parametroNegCrit[1] != null) {
											// Condição 2
											rotas = getControladorSpcSerasa().pesquisarRotasPorGerenciaRegionalParaNegativacao(negativacaoCriterio);
											break;
										} else if (parametroNegCrit[2] != null) {
											// Condição 3
											rotas = getControladorSpcSerasa().pesquisarRotasPorUnidadeNegocioParaNegativacao(negativacaoCriterio);
											break;
										} else if (parametroNegCrit[3] != null) {
											// Condição 4
											rotas = getControladorSpcSerasa().pesquisarRotasPorLocalidadeParaNegativacao(negativacaoCriterio);
											break;
										} else if (parametroNegCrit[4] != null && parametroNegCrit[5] != null) {
											// Condição 5 ou 6
											rotas = getControladorSpcSerasa().pesquisarRotasPorLocalidadesParaNegativacao(negativacaoCriterio);
											break;
										} else {
											// default
											rotas = getControladorSpcSerasa().pesquisarRotasImoveis();
											break;
										}
									}
								} else {
									// default
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
								// gerando o movimento
								Integer idNegativacaoMovimento = getControladorSpcSerasa().gerarNegativadorMovimento(
										negativador.getId(), numeroSequencialEnvio, negativacaoComando.getId());

								// [SB0008] - Gerar Registro do tipo Hearder
								getControladorSpcSerasa().gerarRegistroDeInclusaoTipoHeader(ConstantesSistema.TIPO_COMANDO_POR_CRITERIO, 1,
										negativador, negativadorContrato, negativacaoComando, negativacaoCriterio, idNegativacaoMovimento);
							}
						} else {
							throw new ControladorException("atencao.comando.negativacao.vazio.para.executar");
						}

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(executarComandoNegativacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_LIGACAO_AGUA_LIGADO_ANALISE_PARA_LIGADO:
						TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado atualizarLigacaoAguaLigadoAnaliseParaLigado = new TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colecaoLocalidade = getControladorArrecadacao()
								.pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise();

						atualizarLigacaoAguaLigadoAnaliseParaLigado
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidade);

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(atualizarLigacaoAguaLigadoAnaliseParaLigado));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_NUMERO_EXECUCAO_RESUMO_NEGATIVACAO:

						TarefaBatchAtualizarNumeroExecucaoResumoNegativacao atualizarNumeroExecucaoResumoNegativacao = new TarefaBatchAtualizarNumeroExecucaoResumoNegativacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta o objeto para ser serializado no banco,
						// onde depois seria executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(atualizarNumeroExecucaoResumoNegativacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO:

						TarefaBatchGerarMovimentoExclusaoNegativacao gerarMovimentoExclusaoNegativacao = new TarefaBatchGerarMovimentoExclusaoNegativacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarMovimentoExclusaoNegativacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_MOVIMENTO_RETORNO_NEGATIVACAO:

						TarefaBatchGerarMovimentoRetornoNegativacao gerarMovimentoRetornoNegativacao = new TarefaBatchGerarMovimentoRetornoNegativacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta o objeto para ser serializado no banco,
						// onde depois seria executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarMovimentoRetornoNegativacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO:
						TarefaBatchGerarDadosDiariosArrecadacao dadosArrecadacao = new TarefaBatchGerarDadosDiariosArrecadacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesGerarDadosDiariosArrecadacao = getControladorArrecadacao()
								.pesquisarIdsLocalidadeComPagamentosOuDevolucoes();

						// Seta os parametros para rodar a funcionalidade
						dadosArrecadacao
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarDadosDiariosArrecadacao);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(dadosArrecadacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					/** Pedro Alexandre */
					case Funcionalidade.GERAR_DEBITOS_A_COBRAR_ACRESCIMOS_IMPONTUALIDADE:
						TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade impontualidade = new TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						System.out.println("ENCERRAR ARRECADACAO DO MES");
						// ENCERRAR ARRECADACAO DO MES
						Collection colecaoTodasRotas = getControladorMicromedicao()
								.pesquisarListaRotasCarregadas();

						impontualidade
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoTodasRotas);

						impontualidade.addParametro("indicadorGeracaoMulta",
								ConstantesSistema.SIM);

						impontualidade.addParametro("indicadorGeracaoJuros",
								ConstantesSistema.SIM);

						impontualidade.addParametro(
								"indicadorGeracaoAtualizacao",
								ConstantesSistema.SIM);

						impontualidade.addParametro(
								"indicadorEncerrandoArrecadacao", true);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(impontualidade));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.CLASSIFICAR_PAGAMENTOS_DEVOLUCOES:
						TarefaBatchClassificarPagamentosDevolucoes dadosClassificarPagamentosDevolucoes = new TarefaBatchClassificarPagamentosDevolucoes(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesClassificarPagamentosDevolucoes = getControladorArrecadacao()
								.pesquisarIdsLocalidadeComPagamentosOuDevolucoes();

						// Seta os parametros para rodar a funcionalidade
						dadosClassificarPagamentosDevolucoes
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesClassificarPagamentosDevolucoes);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosClassificarPagamentosDevolucoes));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** ****** INICIO ENCERRAR ARRECADACAO DO MaS ** */
					/** Pedro Alexandre */
					case Funcionalidade.ENCERRAR_ARRECADACAO_MES:
						TarefaBatchEncerrarArrecadacaoMes dadosEncerrarArrecadacaoMes = new TarefaBatchEncerrarArrecadacaoMes(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						dadosEncerrarArrecadacaoMes
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesEncerrarArrecadacaoMes);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosEncerrarArrecadacaoMes));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES:

						// Verificamos se o resumo da arrecadação foi gerado
						// para esse o ano mes de referencia
						if (!getControladorArrecadacao()
								.verificarExistenciaResumoArrecadacaoParaAnoMes(
										getControladorUtil()
												.pesquisarParametrosDoSistema()
												.getAnoMesArrecadacao())) {
							throw new ControladorException(
									"Não existem dados do resumo da arrecadação para o ano/mês de referencia");
						}

						TarefaBatchGerarHistoricoParaEncerrarArrecadacaoMes dadosGerarHistoricoEncerrarArrecadacaoMes = new TarefaBatchGerarHistoricoParaEncerrarArrecadacaoMes(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						/**
						 * Coleaao de ids de localidades para encerrar a
						 * ARRECADACAO do mes
						 */
						/*
						 * Collection<Integer>
						 * colecaoIdsSetoresEncerrarArrecadacaoMes =
						 * getControladorArrecadacao().pesquisarIdsSetoresComPagamentosOuDevolucoes();
						 */

						// Seta os parametros para rodar a funcionalidade
						dadosGerarHistoricoEncerrarArrecadacaoMes
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesEncerrarArrecadacaoMes);

						dadosGerarHistoricoEncerrarArrecadacaoMes.addParametro(
								"anoMesReferenciaArrecadacao",
								anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarHistoricoEncerrarArrecadacaoMes));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_HISTORICO_CONTA:
						TarefaBatchGerarHistoricoConta dadosGerarHistoricoConta = new TarefaBatchGerarHistoricoConta(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						/**
						 * Coleaao de ids de localidades para encerrar a
						 * ARRECADACAO do mes
						 */
						Collection<Integer> colecaoIdsSetoresEncerrarArrecadacaoMes = getControladorArrecadacao()
								.pesquisarIdsSetoresComPagamentosOuDevolucoes();

						// Seta os parametros para rodar a funcionalidade
						dadosGerarHistoricoConta
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsSetoresEncerrarArrecadacaoMes);

						dadosGerarHistoricoConta.addParametro(
								"anoMesReferenciaArrecadacao",
								anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarHistoricoConta));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					/** ****** FIM ENCERRAR ARRECADACAO DO MaS ***** */

					/** ****** INICIO ENCERRAR FATURAMENTO DO MaS ***** */
					/** Pedro Alexandre */
					case Funcionalidade.ENCERRAR_FATURAMENTO_MES:
						TarefaBatchEncerrarFaturamentoMes dadosEncerrarFaturamentoMes = new TarefaBatchEncerrarFaturamentoMes(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesEncerrarFaturamentoMes = getControladorFaturamento()
								.pesquisarIdsLocalidadeParaEncerrarFaturamento();

						// Seta os parametros para rodar a funcionalidade
						dadosEncerrarFaturamentoMes
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesEncerrarFaturamentoMes);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosEncerrarFaturamentoMes));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES:
						TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes dadosGerarHistoricoParaEncerrarFaturamentoMes = new TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsSetoresEncerrarFaturamentoMes = getControladorFaturamento()
								.pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento();

						dadosGerarHistoricoParaEncerrarFaturamentoMes
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsSetoresEncerrarFaturamentoMes);

						dadosGerarHistoricoParaEncerrarFaturamentoMes
								.addParametro(
										"anoMesFaturamentoSistemaParametro",
										anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarHistoricoParaEncerrarFaturamentoMes));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_LIGACOES_ECONOMIAS:
						TarefaBatchGerarResumoLigacoesEconomias gerarResumoLigacoesEconomias = new TarefaBatchGerarResumoLigacoesEconomias(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial novoFiltro = new FiltroSetorComercial();
						Collection<SetorComercial> colecaoSetorComercial = getControladorUtil()
								.pesquisar(novoFiltro,
										SetorComercial.class.getName());

						gerarResumoLigacoesEconomias
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoSetorComercial);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoLigacoesEconomias));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_HIDROMETRO:
						TarefaBatchGerarResumoHidrometro gerarResumoHidrometro = new TarefaBatchGerarResumoHidrometro(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroHidrometroMarca filtroHidrometro = new FiltroHidrometroMarca();
						Collection colMarca = getControladorUtil().pesquisar(
								filtroHidrometro,
								HidrometroMarca.class.getName());

						gerarResumoHidrometro
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colMarca);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoHidrometro));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_REGISTRO_ATENDIMENTO:
						TarefaBatchGerarResumoRegistroAtendimento gerarResumoRegistroAtendimento = new TarefaBatchGerarResumoRegistroAtendimento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMes = getControladorLocalidade()
								.pesquisarTodosIdsLocalidade();

						gerarResumoRegistroAtendimento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMes);

						gerarResumoRegistroAtendimento.addParametro(
								"anoMesFaturamentoSistemaParametro",
								sistemaParametros.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoRegistroAtendimento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_CONSUMO_AGUA:
						TarefaBatchGerarResumoConsumoAgua gerarResumoConsumoAgua = new TarefaBatchGerarResumoConsumoAgua(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtro = new FiltroSetorComercial();
						Collection<SetorComercial> colSetor = getControladorUtil()
								.pesquisar(filtro,
										SetorComercial.class.getName());

						gerarResumoConsumoAgua
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetor);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoConsumoAgua));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_ARRECADACAO:

						TarefaBatchGerarLancamentosContabeisArrecadacao dadosGerarLancamentosContabeisArrecadacao = new TarefaBatchGerarLancamentosContabeisArrecadacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeLancamentoContabeisArrecadacao = new FiltroLocalidade();

						Collection<Localidade> colecaoIdsLocalidadesGerarLancamentosContabeisArrecadacao = getControladorUtil()
								.pesquisar(
										filtroLocalidadeLancamentoContabeisArrecadacao,
										Localidade.class.getName());

						dadosGerarLancamentosContabeisArrecadacao.addParametro(
								"anoMesArrecadacao", sistemaParametros
										.getAnoMesArrecadacao());

						// Seta os parametros para rodar a funcionalidade
						dadosGerarLancamentosContabeisArrecadacao
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarLancamentosContabeisArrecadacao);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarLancamentosContabeisArrecadacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** ****** FIM ENCERRAR FATURAMENTO DO MES ***** */

					/** Raphael Rossiter */
					case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_AVISO_BANCARIO:

						TarefaBatchGerarLancamentosContabeisAvisosBancarios tarefaBatch = new TarefaBatchGerarLancamentosContabeisAvisosBancarios(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tarefaBatch.addParametro("anoMesArrecadacao",
								sistemaParametros.getAnoMesArrecadacao());

						/*
						 * Seta o objeto para ser serializado no banco, onde
						 * depois sera executado por uma thread
						 */
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatch));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_SITUACAO_ESPECIAL_FATURAMENTO:
						TarefaBatchGerarResumoSituacaoEspecialFaturamento gerarResumoSituacaoEspecialFaturamento = new TarefaBatchGerarResumoSituacaoEspecialFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoLocalidadeFaturamento = getControladorFaturamento()
								.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();

						gerarResumoSituacaoEspecialFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidadeFaturamento);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoSituacaoEspecialFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO:
						TarefaBatchGerarResumoHistogramaAguaEsgoto gerarResumoAguaEsgoto = new TarefaBatchGerarResumoHistogramaAguaEsgoto(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Collection<SetorComercial> colSetorHistograma = new
						// ArrayList();
						//						
						// SetorComercial stcm = new SetorComercial();
						// stcm.setId( 1 );
						// stcm.setCodigo( 1 );
						// colSetorHistograma.add( stcm );

						FiltroSetorComercial filtroHistograma = new FiltroSetorComercial();
						//						
						// //filtroHistograma.adicionarParametro( new
						// ParametroSimples( FiltroSetorComercial.LOCALIDADE,
						// "411" ) );
						// filtroHistograma.adicionarParametro( new
						// ParametroSimples( FiltroSetorComercial.LOCALIDADE,
						// "590", ParametroSimples.CONECTOR_OR ) );
						// filtroHistograma.adicionarParametro( new
						// ParametroSimples( FiltroSetorComercial.LOCALIDADE,
						// "589" ) );

						Collection<SetorComercial> colSetorHistograma = getControladorUtil()
								.pesquisar(filtroHistograma,
										SetorComercial.class.getName());

						gerarResumoAguaEsgoto
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorHistograma);

						gerarResumoAguaEsgoto.addParametro(
								"anoMesFaturamentoSistemaParametro",
								anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoAguaEsgoto));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA:
						TarefaBatchGerarResumoAcoesCobrancaCronograma dadosGerarResumoAcoesCobrancaCronograma = new TarefaBatchGerarResumoAcoesCobrancaCronograma(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colecaoCobrancaGrupoCronogramaMes = getControladorCobranca()
								.pesquisarCobrancaGrupoCronogramaMes();
						// Collection colecaoCobrancaGrupoCronogramaMes = new
						// ArrayList();
						// Object[] parms = new Object[3];
						// parms[0] = 687;
						// parms[1] = 200702;
						// parms[2] = 39;
						// colecaoCobrancaGrupoCronogramaMes.add(parms);

						// posiaaes do array com os dados que serao atualizados
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
							Iterator iteratorColecaoCobrancaGrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMes
									.iterator();

							while (iteratorColecaoCobrancaGrupoCronogramaMes
									.hasNext()) {

								Object[] dadosCobrancaGrupoCronogramaMes = (Object[]) iteratorColecaoCobrancaGrupoCronogramaMes
										.next();

								Integer anoMesReferencia = null;
								Integer idGrupo = null;

								// coleaao de aaaes de cobranaa do cronograma
								Collection colecaoCobrancaAcaoCronograma = null;

								// id do cobranca grupo conograma mes
								int idCobrancaGrupoCronogramaMes = -1;

								// id co cobranca grupo cronograma mes
								if (dadosCobrancaGrupoCronogramaMes[0] != null) {
									idCobrancaGrupoCronogramaMes = ((Integer) dadosCobrancaGrupoCronogramaMes[0])
											.intValue();
								}

								// ano mes referencia
								if (dadosCobrancaGrupoCronogramaMes[1] != null) {
									anoMesReferencia = (Integer) dadosCobrancaGrupoCronogramaMes[1];

								}

								// id do cobranca grupo
								if (dadosCobrancaGrupoCronogramaMes[2] != null) {
									idGrupo = (Integer) dadosCobrancaGrupoCronogramaMes[2];

								}

								// Item 2
								// a partir da tabela COBRANCA_ACAO_CRONOGRAMA
								// com CBCM_ID
								// da tabela COBRANCA_GRUPO_CRONOGRAMA_MES
								colecaoCobrancaAcaoCronograma = getControladorCobranca()
										.pesquisarCobrancaAcaoCronograma(
												idCobrancaGrupoCronogramaMes);

								// para cada aaao de cobranaa do conograma
								// verifica:
								if (colecaoCobrancaAcaoCronograma != null
										&& !colecaoCobrancaAcaoCronograma
												.isEmpty()) {

									Iterator iteratorColecaoCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma
											.iterator();

									// id do cobranca acao cronograma
									int idCobrancaAcaoCronograma = -1;

									Object[] dadosCobrancaAcaoCronograma = null;
									Object[] dadosCobrancaAcaoAtividadeCronograma = null;

									while (iteratorColecaoCobrancaAcaoCronograma
											.hasNext()) {
										dadosCobrancaAcaoCronograma = (Object[]) iteratorColecaoCobrancaAcaoCronograma
												.next();

										dadosCobrancaAcaoAtividadeCronograma = new Object[10];

										dadosCobrancaAcaoAtividadeCronograma[POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES] = anoMesReferencia;
										dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_GRUPO] = idGrupo;

										// id do cobranca acao cronograma
										if (dadosCobrancaAcaoCronograma[0] != null) {
											idCobrancaAcaoCronograma = ((Integer) dadosCobrancaAcaoCronograma[0])
													.intValue();
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_CRONOG] = dadosCobrancaAcaoCronograma[0];
										}

										// id de Cobranca Acao do Cobranca Acao
										// Cronograma(sera
										// usada para pesquisar o cobranca acao)
										if (dadosCobrancaAcaoCronograma[1] != null) {
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO] = dadosCobrancaAcaoCronograma[1];
										}

										boolean primeiraCondicao = true;
										boolean segundaCondicao = true;

										Collection colecaoCobrancaAtividadeAcaoCronogramaEmitir = null;
										Collection colecaoCobrancaAtividadeAcaoCronogramaEncerrar = null;

										// Item 3.1
										// O sistema seleciona a atividade da
										// aaao de
										// cobranaa correspondete a EMITIR(
										// apatir da tabela
										// COBRANCA_ATIVIDADE_ACAO_CONOGRAMA
										// com CBCR_ID da tabela
										// COBRANCA_ACAO_CRONOGRAMA e
										// CBAT_ID com o valor correspondente a
										// EMITIR da
										// tabela
										// COBRANCA_ATIVIDADE
										colecaoCobrancaAtividadeAcaoCronogramaEmitir = getControladorCobranca()
												.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
														idCobrancaAcaoCronograma,
														CobrancaAtividade.EMITIR);

										// se existir cobranca atividade acao
										// cronograma,
										// EMITIR
										if (colecaoCobrancaAtividadeAcaoCronogramaEmitir != null
												&& !colecaoCobrancaAtividadeAcaoCronogramaEmitir
														.isEmpty()) {

											Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEmitir
													.iterator().next();

											// id cobranca atividade acao
											// cronograma
											if (dadosCobrancaAtividade[0] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR] = dadosCobrancaAtividade[0];
											}

											// data realizacao
											// [FS0004] - Verificar Realizaaao
											// da Atividade
											// Emitir da Aaao de Cobranaa
											if (dadosCobrancaAtividade[1] == null) {
												primeiraCondicao = false;
											} else {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_REA_ATIV_EMITIR] = dadosCobrancaAtividade[1];
											}

											// data prevista
											if (dadosCobrancaAtividade[2] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_EMITIR] = dadosCobrancaAtividade[2];
											}

										} else {
											// [FS0003] - Verificar Existancia
											// da Atividade
											// Emitir da Aaao de Cobranaa
											primeiraCondicao = false;
										}

										// Item 3.2
										// o sistema seleciona a atividade da
										// aaao de
										// cobrana correspondente a ENCERRAR( a
										// partir da
										// tabela
										// COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
										// com CBCR_ID da tabela
										// COBRANCA_ACAO_CONOGRAMA e
										// CBAT_ID com o valor correspondente a
										// ENCERRAR da
										// tebal COBRANA_ATIVIDADE
										colecaoCobrancaAtividadeAcaoCronogramaEncerrar = getControladorCobranca()
												.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
														idCobrancaAcaoCronograma,
														CobrancaAtividade.ENCERRAR);

										// se existir acobranca atividade acao
										// cronograma,
										// ENCERRAR
										if (colecaoCobrancaAtividadeAcaoCronogramaEncerrar != null
												&& !colecaoCobrancaAtividadeAcaoCronogramaEncerrar
														.isEmpty()) {

											Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEncerrar
													.iterator().next();

											// id cobranca atividade acao
											// cronograma
											if (dadosCobrancaAtividade[0] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR] = dadosCobrancaAtividade[0];
											}

											// data realizacao
											// [FS0006] - Verificar Realizaaao
											// da Atividade
											// Encerrar da Aaao de Cobranaa
											if (dadosCobrancaAtividade[1] != null) {
												segundaCondicao = false;

											}

											// data prevista
											if (dadosCobrancaAtividade[2] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_ENCERRAR] = dadosCobrancaAtividade[2];
											}

											// data comando
											if (dadosCobrancaAtividade[3] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_COM_ATIV_ENCERRAR] = dadosCobrancaAtividade[3];
											}

											// seta null para o GC liberar
											dadosCobrancaAtividade = null;

										} else {
											// [FS0005] - Verificar Existancia
											// da Atividade
											// Encerrar da Aaao de Cobranaa
											// segundaCondicao = false;
										}

										if (primeiraCondicao && segundaCondicao) {
											colecaoDadosCobrancaAcaoAtividadeCronograma
													.add(dadosCobrancaAcaoAtividadeCronograma);
										}
										dadosCobrancaAcaoAtividadeCronograma = null;
									}
								}

							}
							dadosGerarResumoAcoesCobrancaCronograma
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoDadosCobrancaAcaoAtividadeCronograma);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(dadosGerarResumoAcoesCobrancaCronograma));

							getControladorUtil().atualizar(
									funcionalidadeIniciada);
						} else {
							throw new ControladorException(
									"atencao.nao.existe.dados.tabela.cronograma");
						}

						break;

					case Funcionalidade.GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_ENCERRAR_OS:
						TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS dadosGerarResumoAcoesCobrancaCronogramaEncerrarOS = new TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						colecaoCobrancaGrupoCronogramaMes = getControladorCobranca()
								.pesquisarCobrancaGrupoCronogramaMes();
						// Collection colecaoCobrancaGrupoCronogramaMes = new
						// ArrayList();
						// Object[] parms = new Object[3];
						// parms[0] = 687;
						// parms[1] = 200702;
						// parms[2] = 39;
						// colecaoCobrancaGrupoCronogramaMes.add(parms);

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
							Iterator iteratorColecaoCobrancaGrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMes
									.iterator();

							while (iteratorColecaoCobrancaGrupoCronogramaMes
									.hasNext()) {

								Object[] dadosCobrancaGrupoCronogramaMes = (Object[]) iteratorColecaoCobrancaGrupoCronogramaMes
										.next();

								Integer anoMesReferencia = null;
								Integer idGrupo = null;

								// coleaao de aaaes de cobranaa do cronograma
								Collection colecaoCobrancaAcaoCronograma = null;

								// id do cobranca grupo conograma mes
								int idCobrancaGrupoCronogramaMes = -1;

								// id co cobranca grupo cronograma mes
								if (dadosCobrancaGrupoCronogramaMes[0] != null) {
									idCobrancaGrupoCronogramaMes = ((Integer) dadosCobrancaGrupoCronogramaMes[0])
											.intValue();
								}

								// ano mes referencia
								if (dadosCobrancaGrupoCronogramaMes[1] != null) {
									anoMesReferencia = (Integer) dadosCobrancaGrupoCronogramaMes[1];

								}

								// id do cobranca grupo
								if (dadosCobrancaGrupoCronogramaMes[2] != null) {
									idGrupo = (Integer) dadosCobrancaGrupoCronogramaMes[2];

								}

								// Item 2
								// a partir da tabela COBRANCA_ACAO_CRONOGRAMA
								// com CBCM_ID
								// da tabela COBRANCA_GRUPO_CRONOGRAMA_MES
								colecaoCobrancaAcaoCronograma = getControladorCobranca()
										.pesquisarCobrancaAcaoCronograma(
												idCobrancaGrupoCronogramaMes);

								// para cada aaao de cobranaa do conograma
								// verifica:
								if (colecaoCobrancaAcaoCronograma != null
										&& !colecaoCobrancaAcaoCronograma
												.isEmpty()) {

									Iterator iteratorColecaoCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma
											.iterator();

									// id do cobranca acao cronograma
									int idCobrancaAcaoCronograma = -1;

									Object[] dadosCobrancaAcaoCronograma = null;
									Object[] dadosCobrancaAcaoAtividadeCronograma = null;

									while (iteratorColecaoCobrancaAcaoCronograma
											.hasNext()) {
										dadosCobrancaAcaoCronograma = (Object[]) iteratorColecaoCobrancaAcaoCronograma
												.next();

										dadosCobrancaAcaoAtividadeCronograma = new Object[10];

										dadosCobrancaAcaoAtividadeCronograma[POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES] = anoMesReferencia;
										dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_GRUPO] = idGrupo;

										// id do cobranca acao cronograma
										if (dadosCobrancaAcaoCronograma[0] != null) {
											idCobrancaAcaoCronograma = ((Integer) dadosCobrancaAcaoCronograma[0])
													.intValue();
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_CRONOG] = dadosCobrancaAcaoCronograma[0];
										}

										// id de Cobranca Acao do Cobranca Acao
										// Cronograma(sera
										// usada para pesquisar o cobranca acao)
										if (dadosCobrancaAcaoCronograma[1] != null) {
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO] = dadosCobrancaAcaoCronograma[1];
										}

										boolean primeiraCondicao = true;
										boolean segundaCondicao = true;

										Collection colecaoCobrancaAtividadeAcaoCronogramaEmitir = null;
										Collection colecaoCobrancaAtividadeAcaoCronogramaEncerrar = null;

										// Item 3.1
										// O sistema seleciona a atividade da
										// aaao de
										// cobranaa correspondete a EMITIR(
										// apatir da tabela
										// COBRANCA_ATIVIDADE_ACAO_CONOGRAMA
										// com CBCR_ID da tabela
										// COBRANCA_ACAO_CRONOGRAMA e
										// CBAT_ID com o valor correspondente a
										// EMITIR da
										// tabela
										// COBRANCA_ATIVIDADE
										colecaoCobrancaAtividadeAcaoCronogramaEmitir = getControladorCobranca()
												.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
														idCobrancaAcaoCronograma,
														CobrancaAtividade.EMITIR);

										// se existir cobranca atividade acao
										// cronograma,
										// EMITIR
										if (colecaoCobrancaAtividadeAcaoCronogramaEmitir != null
												&& !colecaoCobrancaAtividadeAcaoCronogramaEmitir
														.isEmpty()) {

											Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEmitir
													.iterator().next();

											// id cobranca atividade acao
											// cronograma
											if (dadosCobrancaAtividade[0] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR] = dadosCobrancaAtividade[0];
											}

											// data realizacao
											// [FS0004] - Verificar Realizaaao
											// da Atividade
											// Emitir da Aaao de Cobranaa
											if (dadosCobrancaAtividade[1] == null) {
												primeiraCondicao = false;
											} else {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_REA_ATIV_EMITIR] = dadosCobrancaAtividade[1];
											}

											// data prevista
											if (dadosCobrancaAtividade[2] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_EMITIR] = dadosCobrancaAtividade[2];
											}

										} else {
											// [FS0003] - Verificar Existancia
											// da Atividade
											// Emitir da Aaao de Cobranaa
											primeiraCondicao = false;
										}

										// Item 3.2
										// o sistema seleciona a atividade da
										// aaao de
										// cobrana correspondente a ENCERRAR( a
										// partir da
										// tabela
										// COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
										// com CBCR_ID da tabela
										// COBRANCA_ACAO_CONOGRAMA e
										// CBAT_ID com o valor correspondente a
										// ENCERRAR da
										// tebal COBRANA_ATIVIDADE
										colecaoCobrancaAtividadeAcaoCronogramaEncerrar = getControladorCobranca()
												.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
														idCobrancaAcaoCronograma,
														CobrancaAtividade.ENCERRAR);

										// se existir acobranca atividade acao
										// cronograma,
										// ENCERRAR
										if (colecaoCobrancaAtividadeAcaoCronogramaEncerrar != null
												&& !colecaoCobrancaAtividadeAcaoCronogramaEncerrar
														.isEmpty()) {

											Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEncerrar
													.iterator().next();

											// id cobranca atividade acao
											// cronograma
											if (dadosCobrancaAtividade[0] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR] = dadosCobrancaAtividade[0];
											}

											// data realizacao
											// [FS0006] - Verificar Realizaaao
											// da Atividade
											// Encerrar da Aaao de Cobranaa
											if (dadosCobrancaAtividade[1] != null) {
												segundaCondicao = false;

											}

											// data prevista
											if (dadosCobrancaAtividade[2] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_ENCERRAR] = dadosCobrancaAtividade[2];
											}

											// data comando
											if (dadosCobrancaAtividade[3] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_COM_ATIV_ENCERRAR] = dadosCobrancaAtividade[3];
											}

											// seta null para o GC liberar
											dadosCobrancaAtividade = null;

										} else {
											// [FS0005] - Verificar Existancia
											// da Atividade
											// Encerrar da Aaao de Cobranaa
											// segundaCondicao = false;
										}

										if (primeiraCondicao && segundaCondicao) {
											colecaoDadosCobrancaAcaoAtividadeCronograma
													.add(dadosCobrancaAcaoAtividadeCronograma);
										}
										dadosCobrancaAcaoAtividadeCronograma = null;
									}
								}

							}
							dadosGerarResumoAcoesCobrancaCronogramaEncerrarOS
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoDadosCobrancaAcaoAtividadeCronograma);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(dadosGerarResumoAcoesCobrancaCronogramaEncerrarOS));

							getControladorUtil().atualizar(
									funcionalidadeIniciada);

						} else {
							throw new ControladorException(
									"atencao.nao.existe.dados.tabela.cronograma");
						}

						break;

					case Funcionalidade.GERAR_RESUMO_ACOES_COBRANCA_EVENTUAL:
						TarefaBatchGerarResumoAcoesCobrancaEventual tarefaBatchGerarResumoAcoesCobrancaEventual = new TarefaBatchGerarResumoAcoesCobrancaEventual(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colecaoAcaoCobrancaEventual = getControladorCobranca()
								.pesquisarCobrancaAcaoAtividadeComandoSemRealizacao();

						if (colecaoAcaoCobrancaEventual != null
								&& !colecaoAcaoCobrancaEventual.isEmpty()) {
							tarefaBatchGerarResumoAcoesCobrancaEventual
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoAcaoCobrancaEventual);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(tarefaBatchGerarResumoAcoesCobrancaEventual));

							getControladorUtil().atualizar(
									funcionalidadeIniciada);
						} else {
							throw new ControladorException(
									"atencao.nao.existe.dados.tabela.comando");
						}

						break;

					case Funcionalidade.INSERIR_RESUMO_ACOES_COBRANCA_CRONOGRAMA:
						TarefaBatchInserirResumoAcoesCobrancaCronograma dadosInserirResumoAcoesCobrancaCronograma = new TarefaBatchInserirResumoAcoesCobrancaCronograma(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

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

						Collection colecaoCobrancaGrupoCronogramaMesInserir = getControladorCobranca()
								.pesquisarCobrancaGrupoCronogramaMes();
						// Collection colecaoCobrancaGrupoCronogramaMesInserir =
						// new ArrayList();
						// parms = new Object[3];
						// parms[0] = 687;
						// parms[1] = 200702;
						// parms[2] = 39;
						// colecaoCobrancaGrupoCronogramaMesInserir.add(parms);

						Collection colecaoDadosCobrancaAcaoAtividadeCronogramaParaInserir = new ArrayList();

						if (colecaoCobrancaGrupoCronogramaMesInserir != null) {
							Iterator iteratorColecaoCobrancaGrupoCronogramaMes = colecaoCobrancaGrupoCronogramaMesInserir
									.iterator();

							while (iteratorColecaoCobrancaGrupoCronogramaMes
									.hasNext()) {

								Object[] dadosCobrancaGrupoCronogramaMes = (Object[]) iteratorColecaoCobrancaGrupoCronogramaMes
										.next();

								Integer anoMesReferencia = null;
								Integer idGrupo = null;

								// coleaao de aaaes de cobranaa do cronograma
								Collection colecaoCobrancaAcaoCronograma = null;

								// id do cobranca grupo conograma mes
								int idCobrancaGrupoCronogramaMes = -1;

								// id co cobranca grupo cronograma mes
								if (dadosCobrancaGrupoCronogramaMes[0] != null) {
									idCobrancaGrupoCronogramaMes = ((Integer) dadosCobrancaGrupoCronogramaMes[0])
											.intValue();
								}

								// ano mes referencia
								if (dadosCobrancaGrupoCronogramaMes[1] != null) {
									anoMesReferencia = (Integer) dadosCobrancaGrupoCronogramaMes[1];

								}

								// id do cobranca grupo
								if (dadosCobrancaGrupoCronogramaMes[2] != null) {
									idGrupo = (Integer) dadosCobrancaGrupoCronogramaMes[2];

								}

								// Item 2
								// a partir da tabela COBRANCA_ACAO_CRONOGRAMA
								// com CBCM_ID
								// da tabela COBRANCA_GRUPO_CRONOGRAMA_MES
								colecaoCobrancaAcaoCronograma = getControladorCobranca()
										.pesquisarCobrancaAcaoCronograma(
												idCobrancaGrupoCronogramaMes);

								// para cada aaao de cobranaa do conograma
								// verifica:
								if (colecaoCobrancaAcaoCronograma != null
										&& !colecaoCobrancaAcaoCronograma
												.isEmpty()) {

									Iterator iteratorColecaoCobrancaAcaoCronograma = colecaoCobrancaAcaoCronograma
											.iterator();

									// id do cobranca acao cronograma
									int idCobrancaAcaoCronograma = -1;

									Object[] dadosCobrancaAcaoCronograma = null;
									Object[] dadosCobrancaAcaoAtividadeCronograma = null;

									while (iteratorColecaoCobrancaAcaoCronograma
											.hasNext()) {
										dadosCobrancaAcaoCronograma = (Object[]) iteratorColecaoCobrancaAcaoCronograma
												.next();

										dadosCobrancaAcaoAtividadeCronograma = new Object[10];

										dadosCobrancaAcaoAtividadeCronograma[POSICAO_ANO_MES_REF_COB_GRUP_CRON_MES] = anoMesReferencia;
										dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_GRUPO] = idGrupo;

										// id do cobranca acao cronograma
										if (dadosCobrancaAcaoCronograma[0] != null) {
											idCobrancaAcaoCronograma = ((Integer) dadosCobrancaAcaoCronograma[0])
													.intValue();
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_CRONOG] = dadosCobrancaAcaoCronograma[0];
										}

										// id de Cobranca Acao do Cobranca Acao
										// Cronograma(sera
										// usada para pesquisar o cobranca acao)
										if (dadosCobrancaAcaoCronograma[1] != null) {
											dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO] = dadosCobrancaAcaoCronograma[1];
										}

										boolean primeiraCondicao = true;
										boolean segundaCondicao = true;

										Collection colecaoCobrancaAtividadeAcaoCronogramaEmitir = null;
										Collection colecaoCobrancaAtividadeAcaoCronogramaEncerrar = null;

										// Item 3.1
										// O sistema seleciona a atividade da
										// aaao de
										// cobranaa correspondete a EMITIR(
										// apatir da tabela
										// COBRANCA_ATIVIDADE_ACAO_CONOGRAMA
										// com CBCR_ID da tabela
										// COBRANCA_ACAO_CRONOGRAMA e
										// CBAT_ID com o valor correspondente a
										// EMITIR da
										// tabela
										// COBRANCA_ATIVIDADE
										colecaoCobrancaAtividadeAcaoCronogramaEmitir = getControladorCobranca()
												.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
														idCobrancaAcaoCronograma,
														CobrancaAtividade.EMITIR);

										// se existir cobranca atividade acao
										// cronograma,
										// EMITIR
										if (colecaoCobrancaAtividadeAcaoCronogramaEmitir != null
												&& !colecaoCobrancaAtividadeAcaoCronogramaEmitir
														.isEmpty()) {

											Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEmitir
													.iterator().next();

											// id cobranca atividade acao
											// cronograma
											if (dadosCobrancaAtividade[0] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_EMITIR] = dadosCobrancaAtividade[0];
											}

											// data realizacao
											// [FS0004] - Verificar Realizaaao
											// da Atividade
											// Emitir da Aaao de Cobranaa
											if (dadosCobrancaAtividade[1] == null) {
												primeiraCondicao = false;
											} else {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_REA_ATIV_EMITIR] = dadosCobrancaAtividade[1];
											}

											// data prevista
											if (dadosCobrancaAtividade[2] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_EMITIR] = dadosCobrancaAtividade[2];
											}

										} else {
											// [FS0003] - Verificar Existancia
											// da Atividade
											// Emitir da Aaao de Cobranaa
											primeiraCondicao = false;
										}

										// Item 3.2
										// o sistema seleciona a atividade da
										// aaao de
										// cobrana correspondente a ENCERRAR( a
										// partir da
										// tabela
										// COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
										// com CBCR_ID da tabela
										// COBRANCA_ACAO_CONOGRAMA e
										// CBAT_ID com o valor correspondente a
										// ENCERRAR da
										// tebal COBRANA_ATIVIDADE
										colecaoCobrancaAtividadeAcaoCronogramaEncerrar = getControladorCobranca()
												.pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
														idCobrancaAcaoCronograma,
														CobrancaAtividade.ENCERRAR);

										// se existir acobranca atividade acao
										// cronograma,
										// ENCERRAR
										if (colecaoCobrancaAtividadeAcaoCronogramaEncerrar != null
												&& !colecaoCobrancaAtividadeAcaoCronogramaEncerrar
														.isEmpty()) {

											Object[] dadosCobrancaAtividade = (Object[]) colecaoCobrancaAtividadeAcaoCronogramaEncerrar
													.iterator().next();

											// id cobranca atividade acao
											// cronograma
											if (dadosCobrancaAtividade[0] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_ID_COB_ACAO_ATIV_CRON_ENCERRAR] = dadosCobrancaAtividade[0];
											}

											// data realizacao
											// [FS0006] - Verificar Realizaaao
											// da Atividade
											// Encerrar da Aaao de Cobranaa
											if (dadosCobrancaAtividade[1] != null) {
												segundaCondicao = false;
											}

											// data prevista
											if (dadosCobrancaAtividade[2] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_PREV_ATIV_ENCERRAR] = dadosCobrancaAtividade[2];
											}

											// data comando
											if (dadosCobrancaAtividade[3] != null) {
												dadosCobrancaAcaoAtividadeCronograma[POSICAO_DATA_COM_ATIV_ENCERRAR] = dadosCobrancaAtividade[3];
											}

											// seta null para o GC liberar
											dadosCobrancaAtividade = null;

										} else {
											// [FS0005] - Verificar Existancia
											// da Atividade
											// Encerrar da Aaao de Cobranaa
											// segundaCondicao = false;
										}

										if (primeiraCondicao && segundaCondicao) {
											colecaoDadosCobrancaAcaoAtividadeCronogramaParaInserir
													.add(dadosCobrancaAcaoAtividadeCronograma);
										}
										dadosCobrancaAcaoAtividadeCronograma = null;
									}
								}

							}
							dadosInserirResumoAcoesCobrancaCronograma
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoDadosCobrancaAcaoAtividadeCronogramaParaInserir);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(dadosInserirResumoAcoesCobrancaCronograma));

							getControladorUtil().atualizar(
									funcionalidadeIniciada);
						} else {
							throw new ControladorException(
									"atencao.nao.existe.dados.tabela.cronograma");
						}
						break;

					case Funcionalidade.INSERIR_RESUMO_ACOES_COBRANCA_EVENTUAL:
						TarefaBatchInserirResumoAcoesCobrancaEventual tarefaBatchInserirResumoAcoesCobrancaEventual = new TarefaBatchInserirResumoAcoesCobrancaEventual(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colecaoAcaoCobrancaEventualParaInserir = getControladorCobranca()
								.pesquisarCobrancaAcaoAtividadeComandoSemRealizacao();

						if (colecaoAcaoCobrancaEventualParaInserir != null
								&& !colecaoAcaoCobrancaEventualParaInserir
										.isEmpty()) {
							tarefaBatchInserirResumoAcoesCobrancaEventual
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoAcaoCobrancaEventualParaInserir);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(tarefaBatchInserirResumoAcoesCobrancaEventual));

							getControladorUtil().atualizar(
									funcionalidadeIniciada);
						} else {
							throw new ControladorException(
									"atencao.nao.existe.dados.tabela.comando");
						}

						break;

					case Funcionalidade.GERAR_RESUMO_PENDENCIA:
						TarefaBatchGerarResumoPendencia dadosGerarResumoPendencia = new TarefaBatchGerarResumoPendencia(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial novoFiltroSetorComercial = new FiltroSetorComercial();
						Collection<SetorComercial> colecaoSetorPendencia = getControladorUtil()
								.pesquisar(novoFiltroSetorComercial,
										SetorComercial.class.getName());

						// Seta os parametros para rodar a funcionalidade
						dadosGerarResumoPendencia
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoSetorPendencia );

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoPendencia));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_GUIA_PAGAMENTO_POR_CLIENTE_RESUMO_PENDENCIA:
						TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia dados = new TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeResumoPendencia = new FiltroLocalidade();
						Collection<Localidade> colecaoLocalidadePendencia = getControladorUtil()
								.pesquisar(filtroLocalidadeResumoPendencia,
										Localidade.class.getName());

						// Seta os parametros para rodar a funcionalidade
						dados
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidadePendencia);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(dados));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_ANORMALIDADES:
						TarefaBatchGerarResumoAnormalidades tarefaBatchGerarResumoAnormalidades = new TarefaBatchGerarResumoAnormalidades(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesAnormalidades = getControladorFaturamento()
								.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();

						// Seta os parametros para rodar a funcionalidade
						tarefaBatchGerarResumoAnormalidades
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesAnormalidades);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoAnormalidades));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA:
						TarefaBatchGerarResumoSituacaoEspecialCobranca tarefaBatchGerarResumoSituacaoEspecialCobranca = new TarefaBatchGerarResumoSituacaoEspecialCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesCobranca = getControladorFaturamento()
								.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();

						// Seta os parametros para rodar a funcionalidade
						tarefaBatchGerarResumoSituacaoEspecialCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesCobranca);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoSituacaoEspecialCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** processo de gerar fatura de cliente responsavel */
					/** Pedro Alexandre */
					case Funcionalidade.GERAR_FATURA_CLIENTE_RESPONSAVEL:
						TarefaBatchGerarFaturaClienteResponsavel dadosGerarFaturaClienteResponsavel = new TarefaBatchGerarFaturaClienteResponsavel(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarFaturaClienteResponsavel));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_CONTAS:
						TarefaBatchEmitirContas emitirContas = new TarefaBatchEmitirContas(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						emitirContas.addParametro("anoMesFaturamentoGrupo",
								anoMesFaturamentoSistemaParametro);

						emitirContas.addParametro("faturamentoGrupo", null);

						Collection colecaoIdsEmpresas = getControladorCadastro()
								.pesquisarIdsEmpresa();

						emitirContas
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsEmpresas);

						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(emitirContas));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** fim do processo de gerar fatura de cliente responsavel */

					case Funcionalidade.DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA:
						TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga dadosDesfazerParcelamentoPorEntradaNaoPaga = new TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosDesfazerParcelamentoPorEntradaNaoPaga));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_RESUMO_INSTALACOES_HIDROMETROS:
						TarefaBatchGerarResumoInstalacoesHidrometros dadosGerarResumoInstalacoesHidrometros = new TarefaBatchGerarResumoInstalacoesHidrometros(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						/**
						 * Coleaao de todos ids de setor comercial para os
						 * imaveis que tem hidrometro instalado no historico
						 * para o ano/mes de referancia do faturamento.
						 */
						Collection<Integer> colecaoIdsSetoresComercial = getControladorGerencialMicromedicao()
								.pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
										anoMesFaturamentoSistemaParametro);
						// Collection<Integer> colecaoIdsSetoresComercial =
						// getControladorCadastro().pesquisarTodosIdsSetorComercial();

						// Seta os parametros para rodar a funcionalidade
						dadosGerarResumoInstalacoesHidrometros
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsSetoresComercial);

						dadosGerarResumoInstalacoesHidrometros.addParametro(
								"anoMesReferenciaFaturamento",
								anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoInstalacoesHidrometros));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_LEITURA_ANORMALIDADE:
						TarefaBatchGerarResumoLeituraAnormalidade dadosGerarResumoLeituraAnormalidade = new TarefaBatchGerarResumoLeituraAnormalidade(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						/*
						 * Collection<Integer> colecaoLocalidades =
						 * getControladorFaturamento()
						 * .pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();
						 */
						FiltroSetorComercial filtroSetor = new FiltroSetorComercial();
						Collection<SetorComercial> colSetorComercial = getControladorUtil()
								.pesquisar(filtroSetor,
										SetorComercial.class.getName());

						dadosGerarResumoLeituraAnormalidade
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorComercial);

						dadosGerarResumoLeituraAnormalidade.addParametro(
								"anoMesReferenciaFaturamento",
								sistemaParametros.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoLeituraAnormalidade));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_ARRECADACAO:
						TarefaBatchGerarResumoArrecadacao dadosGerarResumoArrecadacao = new TarefaBatchGerarResumoArrecadacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FiltroSetorComercial gra_filtroSetor = new
						// FiltroSetorComercial();
						// Collection<SetorComercial> gra_colSetorComercial =
						// getControladorUtil()
						// .pesquisar(gra_filtroSetor,
						// SetorComercial.class.getName());

						FiltroLocalidade filtroLocalidadeResumoArrecadacao = new FiltroLocalidade();
						Collection<Localidade> colLocalidadeResumoArrecadacao = getControladorUtil()
								.pesquisar(filtroLocalidadeResumoArrecadacao,
										Localidade.class.getName());

						dadosGerarResumoArrecadacao
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colLocalidadeResumoArrecadacao);

						dadosGerarResumoArrecadacao.addParametro(
								"anoMesReferenciaArrecadacao",
								sistemaParametros.getAnoMesArrecadacao());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoArrecadacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_PARCELAMENTO:
						TarefaBatchGerarResumoParcelamento dadosGerarResumoParcelamento = new TarefaBatchGerarResumoParcelamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());
						// 2222
						Collection<Integer> colecaoLocalidadesParcelamento = getControladorFaturamento()
								.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();

						dadosGerarResumoParcelamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidadesParcelamento);

						dadosGerarResumoParcelamento.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoParcelamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_FATURAMENTO:
						TarefaBatchGerarResumoFaturamento dadosGerarResumoFaturamento = new TarefaBatchGerarResumoFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroFaturamento = new FiltroSetorComercial();

						Collection<SetorComercial> colSetorFaturamento = getControladorUtil()
								.pesquisar(filtroFaturamento,
										SetorComercial.class.getName());

						// Adicionamos um setor comercial ficticio que será
						// utilizado
						// para selecionarmos as guias de pagamento sem imovel.
						SetorComercial setorComercial = new SetorComercial();
						setorComercial.setId(99999);
						colSetorFaturamento.add(setorComercial);

						dadosGerarResumoFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorFaturamento);

						dadosGerarResumoFaturamento.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_FATURAMENTO_AGUA_ESGOTO:
						TarefaBatchGerarResumoFaturamentoAguaEsgoto dadosGerarResumoFaturamentoAguaEsgoto = new TarefaBatchGerarResumoFaturamentoAguaEsgoto(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						filtroFaturamento = new FiltroSetorComercial();

						colSetorFaturamento = getControladorUtil().pesquisar(
								filtroFaturamento,
								SetorComercial.class.getName());

						dadosGerarResumoFaturamentoAguaEsgoto
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorFaturamento);

						dadosGerarResumoFaturamentoAguaEsgoto.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoFaturamentoAguaEsgoto));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_REFATURAMENTO:
						TarefaBatchGerarResumoReFaturamento dadosGerarResumoReFaturamento = new TarefaBatchGerarResumoReFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroReFaturamento = new FiltroSetorComercial();

						Collection<SetorComercial> colSetorReFaturamento = getControladorUtil() // getControladorGerencialFaturamento().pesquisarIdsSetores();
								.pesquisar(filtroReFaturamento,
										SetorComercial.class.getName());

						dadosGerarResumoReFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorReFaturamento);

						dadosGerarResumoReFaturamento.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoReFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					// Bruno Barros
					case Funcionalidade.GERAR_RESUMO_REFATURAMENTO_OLAP:
						TarefaBatchGerarResumoReFaturamentoOlap dadosGerarResumoReFaturamentoOlap = new TarefaBatchGerarResumoReFaturamentoOlap(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroReFaturamentoOlap = new FiltroSetorComercial();

						Collection<SetorComercial> colSetorReFaturamentoOlap = getControladorUtil()
								.pesquisar(filtroReFaturamentoOlap,
										SetorComercial.class.getName());

						dadosGerarResumoReFaturamentoOlap
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorReFaturamentoOlap);

						dadosGerarResumoReFaturamentoOlap.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoReFaturamentoOlap));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_FATURAMENTO:

						TarefaBatchGerarLancamentosContabeisFaturamento dadosGerarLancamentosContabeisFaturamento = new TarefaBatchGerarLancamentosContabeisFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeLancamentoContabeisFaturamento = new FiltroLocalidade();

						Collection<Localidade> colecaoIdsLocalidadesGerarLancamentosContabeisFaturamento = getControladorUtil()
								.pesquisar(
										filtroLocalidadeLancamentoContabeisFaturamento,
										Localidade.class.getName());

						dadosGerarLancamentosContabeisFaturamento.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta os parametros para rodar a funcionalidade
						dadosGerarLancamentosContabeisFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarLancamentosContabeisFaturamento);

						// Seta o objeto para ser serializado no banco, onde
						// depois serao executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarLancamentosContabeisFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_RESUMO_DEVEDORES_DUVIDOSOS:
						TarefaBatchGerarResumoDevedoresDuvidosos dadosGerarResumoDevedoresDuvidosos = new TarefaBatchGerarResumoDevedoresDuvidosos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos = getControladorFinanceiro()
								.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(
										sistemaParametros
												.getAnoMesFaturamento());

						dadosGerarResumoDevedoresDuvidosos.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta os parametros para rodar a funcionalidade
						dadosGerarResumoDevedoresDuvidosos
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_METAS:
						TarefaBatchGerarResumoMetas tarefaBatchGerarResumoMetas = new TarefaBatchGerarResumoMetas(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetorC = new FiltroSetorComercial();
						Collection<SetorComercial> colSetorC = getControladorUtil()
								.pesquisar(filtroSetorC,
										SetorComercial.class.getName());

						Collection colecaoResumoMetas = getControladorFaturamento()
								.pesquisarResumoMetas(
										sistemaParametros
												.getAnoMesArrecadacao());

						if (colecaoResumoMetas != null
								&& !colecaoResumoMetas.isEmpty()) {
							throw new ControladorException(
									"atencao.dados.existente.resumo.metas",
									null, ""
											+ sistemaParametros
													.getAnoMesArrecadacao());
						}

						tarefaBatchGerarResumoMetas
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorC);

						Date dataInicial = Util
								.gerarDataInicialApartirAnoMesRefencia(sistemaParametros
										.getAnoMesArrecadacao());
						Date dataFinal = Util
								.gerarDataApartirAnoMesRefencia(sistemaParametros
										.getAnoMesArrecadacao());
						tarefaBatchGerarResumoMetas.addParametro("dataInicial",
								dataInicial);

						tarefaBatchGerarResumoMetas.addParametro("dataFinal",
								dataFinal);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoMetas));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_METAS_ACUMULADO:
						TarefaBatchGerarResumoMetasAcumulado tarefaBatchGerarResumoMetasAcumulado = new TarefaBatchGerarResumoMetasAcumulado(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetorCA = new FiltroSetorComercial();
						Collection<SetorComercial> colSetorCA = getControladorUtil()
								.pesquisar(filtroSetorCA,
										SetorComercial.class.getName());

						Collection colecaoResumoMetasA = getControladorFaturamento()
								.pesquisarResumoMetasAcumulado(
										sistemaParametros
												.getAnoMesArrecadacao());

						if (colecaoResumoMetasA != null
								&& !colecaoResumoMetasA.isEmpty()) {
							throw new ControladorException(
									"atencao.dados.existente.resumo.metas",
									null, ""
											+ sistemaParametros
													.getAnoMesArrecadacao());
						}

						tarefaBatchGerarResumoMetasAcumulado
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorCA);

						tarefaBatchGerarResumoMetasAcumulado.addParametro(
								"anoMesArrecadacao", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoMetasAcumulado));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_CONTAS_ORGAO_PUBLICO:
						TarefaBatchEmitirContasOrgaoPublico tarefaBatchEmitirContasOrgaoPublico = new TarefaBatchEmitirContasOrgaoPublico(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Integer anoMesFaturamento = sistemaParametros
								.getAnoMesFaturamento();
						FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
						faturamentoGrupo.setId(0);

						tarefaBatchEmitirContasOrgaoPublico.addParametro(
								"anoMesFaturamento", anoMesFaturamento);
						tarefaBatchEmitirContasOrgaoPublico.addParametro(
								"faturamentoGrupo", faturamentoGrupo);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchEmitirContasOrgaoPublico));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_COLETA_ESGOTO:
						TarefaBatchGerarResumoColetaEsgoto gerarResumoColetaEsgoto = new TarefaBatchGerarResumoColetaEsgoto(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetorCom = new FiltroSetorComercial();
						Collection<SetorComercial> colSetorCom = getControladorUtil()
								.pesquisar(filtroSetorCom,
										SetorComercial.class.getName());

						gerarResumoColetaEsgoto
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorCom);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread marcio
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoColetaEsgoto));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_CONTAS_A_RECEBER_CONTABIL:
						TarefaBatchGerarContasAReceberContabil gerarContaAReceberContabil = new TarefaBatchGerarContasAReceberContabil(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

						Collection<Localidade> colLocalidade = getControladorUtil()
								.pesquisar(filtroLocalidade,
										Localidade.class.getName());

						gerarContaAReceberContabil
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colLocalidade);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread marcio
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarContaAReceberContabil));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Vivianne Sousa */
					case Funcionalidade.ATUALIZA_QUANTIDADE_PARCELA_PAGA_CONSECUTIVA_PARCELA_BONUS:
						TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus dadosAtualizarParcela = new TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						dadosAtualizarParcela
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesEncerrarArrecadacaoMes);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosAtualizarParcela));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EXECUTAR_COMANDO_DE_ENCERRAMENTO_RA:
						TarefaBatchExecutarComandoEncerramentoRA executarComandoEncerramentoRA = new TarefaBatchExecutarComandoEncerramentoRA(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesRA = getControladorLocalidade()
								.pesquisarTodosIdsLocalidade();

						FiltroRaEncerramentoComando filtroRaEncerramentoComando = new FiltroRaEncerramentoComando();
						filtroRaEncerramentoComando
								.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
						filtroRaEncerramentoComando
								.adicionarCaminhoParaCarregamentoEntidade("usuario.unidadeOrganizacional");
						filtroRaEncerramentoComando
								.adicionarParametro(new ParametroNulo(
										FiltroRaEncerramentoComando.TEMPO_REALIZACAO));
						Collection<RaEncerramentoComando> colRaEncerramentoComando = getControladorUtil()
								.pesquisar(filtroRaEncerramentoComando,
										RaEncerramentoComando.class.getName());

						if (colRaEncerramentoComando != null
								&& !colRaEncerramentoComando.isEmpty()) {

							RaEncerramentoComando raEncerramentoComando = null;
							// recupera o promeiro comando da seleção para
							// executar
							laco: for (RaEncerramentoComando ra : colRaEncerramentoComando) {
								raEncerramentoComando = ra;
								break laco;
							}

							executarComandoEncerramentoRA
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoIdsLocalidadesRA);
							executarComandoEncerramentoRA.addParametro(
									"raEncerramentoComando",
									raEncerramentoComando);

						} else {
							throw new ControladorException(
									"atencao.comando.encerramento.ra.vazio.para.executar");
						}

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(executarComandoEncerramentoRA));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_VALOR_VOLUMES_CONSUMIDOS_NAO_FATURADOS:
						TarefaBatchGerarValorVolumesConsumidosNaoFaturados gerarValorVolumesConsumidosNaoFaturados = new TarefaBatchGerarValorVolumesConsumidosNaoFaturados(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidades = getControladorLocalidade()
								.pesquisarIdsLocalidadesImoveis();

						gerarValorVolumesConsumidosNaoFaturados
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidades);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread marcio
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarValorVolumesConsumidosNaoFaturados));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_INDICADORES_COMERCIALIZACAO:

						TarefaBatchGerarResumoIndicadoresComercializacao gerarResumoIndicadoresComercializacao = new TarefaBatchGerarResumoIndicadoresComercializacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoIndicadoresComercializacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_INDICADORES_MICROMEDICAO:

						TarefaBatchGerarResumoIndicadoresMicromedicao gerarResumoIndicadoresMicromedicao = new TarefaBatchGerarResumoIndicadoresMicromedicao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoIndicadoresMicromedicao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_INDICADORES_FATURAMENTO:

						TarefaBatchGerarResumoIndicadoresFaturamento gerarResumoIndicadoresFaturamento = new TarefaBatchGerarResumoIndicadoresFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoIndicadoresFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_INDICADORES_COBRANCA:

						TarefaBatchGerarResumoIndicadoresCobranca gerarResumoIndicadoresCobranca = new TarefaBatchGerarResumoIndicadoresCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoIndicadoresCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.INCLUIR_DEBITO_A_COBRAR_ENTRADA_PARCELAMENTO_NAO_PAGA:

						TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga dadosIncluirDebitoACobrarEntradaParcelamentoNaoPaga = new TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosIncluirDebitoACobrarEntradaParcelamentoNaoPaga));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_PAGAMENTOS_CONTAS_COBRANCA:

						TarefaBatchAtualizarPagamentosContasCobranca atualizarPagamentosContasCobranca = new TarefaBatchAtualizarPagamentosContasCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesAtualizarPagamentos = getControladorLocalidade()
								.pesquisarTodosIdsLocalidade();

						atualizarPagamentosContasCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesAtualizarPagamentos);

						atualizarPagamentosContasCobranca.addParametro(
								"anoMesArrecadacaoSistemaParametro",
								sistemaParametros.getAnoMesArrecadacao());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(atualizarPagamentosContasCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA:

						TarefaBatchGerarMovimentoContasCobrancaPorEmpresa gerarMovimentoContasCobrancaPorEmpresa = new TarefaBatchGerarMovimentoContasCobrancaPorEmpresa(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroComandoEmpresaCobrancaConta filtroComandoEmpresaCobrancaConta = new FiltroComandoEmpresaCobrancaConta();
						filtroComandoEmpresaCobrancaConta
								.adicionarParametro(new ParametroNulo(
										FiltroComandoEmpresaCobrancaConta.DATA_EXECUCAO));

						Collection colecaoComandoEmpresaCobrancaConta = getControladorUtil()
								.pesquisar(
										filtroComandoEmpresaCobrancaConta,
										ComandoEmpresaCobrancaConta.class
												.getName());

						Collection colecaoComandoEmpresaCobrancaContaParaBatch = new ArrayList();
						
						if (colecaoComandoEmpresaCobrancaConta != null 
								&& !colecaoComandoEmpresaCobrancaConta.isEmpty()) {
							Iterator iteratorComandos = colecaoComandoEmpresaCobrancaConta.iterator();
							
							while(iteratorComandos.hasNext()) {
								ComandoEmpresaCobrancaConta comando = (ComandoEmpresaCobrancaConta) 
									iteratorComandos.next();
								
								FiltroEmpresaCobranca filtroEmpresaCobranca = new FiltroEmpresaCobranca();
								filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
										FiltroEmpresaCobranca.EMPRESA_ID, comando.getEmpresa().getId()));
								filtroEmpresaCobranca.adicionarParametro(new ParametroNulo(
										FiltroEmpresaCobranca.DATA_FIM_CONTRATO));
								Collection colecaoEmpresaCobranca = getControladorUtil().pesquisar(
										filtroEmpresaCobranca, EmpresaCobranca.class.getName());
								
								if (colecaoEmpresaCobranca != null && !colecaoEmpresaCobranca.isEmpty()) {
									
									EmpresaCobranca empresaCobranca = (EmpresaCobranca) 
										Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);
									
									if ((empresaCobranca.getPercentualContratoCobranca() != null
											&& empresaCobranca.getPercentualContratoCobranca()
												.compareTo(BigDecimal.ZERO) > 0)
										|| (comando.getDataEncerramento() == null)){
										
										colecaoComandoEmpresaCobrancaContaParaBatch.add(comando);
										
									}
									
								}
								
							}
						}
						
						gerarMovimentoContasCobrancaPorEmpresa
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoComandoEmpresaCobrancaContaParaBatch);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarMovimentoContasCobrancaPorEmpresa));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA:

						TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa gerarMovimentoExtensaoContasCobrancaPorEmpresa = new TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroComandoEmpresaCobrancaContaExtensao filtroComandoEmpresaCobrancaContaExtensao = new FiltroComandoEmpresaCobrancaContaExtensao();
						filtroComandoEmpresaCobrancaContaExtensao
								.adicionarParametro(new ParametroNulo(
										FiltroComandoEmpresaCobrancaContaExtensao.DATA_EXECUCAO));
						filtroComandoEmpresaCobrancaContaExtensao
								.adicionarCaminhoParaCarregamentoEntidade(FiltroComandoEmpresaCobrancaContaExtensao.COMANDO_EMPRESA_COBRANCA_CONTA);
						filtroComandoEmpresaCobrancaContaExtensao
								.adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta.empresa");

						Collection colecaoComandoEmpresaCobrancaContaExtensao = getControladorUtil()
								.pesquisar(
										filtroComandoEmpresaCobrancaContaExtensao,
										ComandoEmpresaCobrancaContaExtensao.class
												.getName());

						Collection colecaoLocalidades = getControladorCadastro()
								.pesquisarLocalidades();

						gerarMovimentoExtensaoContasCobrancaPorEmpresa
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidades);

						gerarMovimentoExtensaoContasCobrancaPorEmpresa
								.addParametro(
										"colecaoComandoEmpresaCobrancaContaExtensao",
										colecaoComandoEmpresaCobrancaContaExtensao);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarMovimentoExtensaoContasCobrancaPorEmpresa));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_AUTOS_INFRACAO_PRAZO_RECURSO_VENCIDO:
						TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido = new TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										Collections.singletonList(0));

						tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido
								.addParametro("sistemaParametro",
										sistemaParametros);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					// case Funcionalidade.MOVIMENTO_HIDROMETRO:
					// TarefaBatchGerarMovimentoHidrometro
					// tarefaBatchGerarMovimentoHidrometro = new
					// TarefaBatchGerarMovimentoHidrometro(
					// processoIniciado.getUsuario(),
					// funcionalidadeIniciada.getId());
					//
					//						
					// tarefaBatchGerarMovimentoHidrometro
					// .addParametro();
					//
					//						
					//						
					// // Seta o objeto para ser serializado no banco, onde
					// // depois sera executado por uma thread
					// funcionalidadeIniciada
					// .setTarefaBatch(IoUtil
					// .transformarObjetoParaBytes(tarefaBatchGerarMovimentoHidrometro));
					//
					// getControladorUtil().atualizar(funcionalidadeIniciada);
					//
					// break;

					case Funcionalidade.EXCLUIR_IMOVEIS_DA_TARIFA_SOCIAL:
						TarefaBatchExcluirImoveisDaTarifaSocial excluirImoveisDaTarifaSocial = new TarefaBatchExcluirImoveisDaTarifaSocial(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Integer anoMesFaturamento_ExcluirImoveisDaTarifaSocial = sistemaParametros
								.getAnoMesFaturamento();

						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
						Collection<SetorComercial> colecaoSetor = getControladorUtil()
								.pesquisar(filtroSetorComercial,
										SetorComercial.class.getName());

						excluirImoveisDaTarifaSocial
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoSetor);
						excluirImoveisDaTarifaSocial.addParametro(
								"anoMesReferenciaFaturamento",
								anoMesFaturamento_ExcluirImoveisDaTarifaSocial);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(excluirImoveisDaTarifaSocial));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_NEGATIVACAO:
						TarefaBatchGerarResumoNegativacao gerarResumoNegativacao = new TarefaBatchGerarResumoNegativacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colecaoRotas = getControladorSpcSerasa()
								.consultarRotasParaGerarResumoDiarioNegativacao();

						// 2.0 O sistema exclui o resumo diário das negativações
						// da penúltima execução
						// (exclui as linhas da tabela RESUMO_NEGATIVACAO com
						// RNEG_NNEXECUCAORESUMONEGATIVACAO=PARM_NNEXECUCAORESUMONEGATIVACAO
						// da tabela SISTEMA_PARAMETROS menos um.
						Integer penultimaExecucaoResumo = sistemaParametros
								.getNumeroExecucaoResumoNegativacao() - 1;
						getControladorSpcSerasa().apagarResumoNegativacao(
								penultimaExecucaoResumo);

						gerarResumoNegativacao
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoRotas);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoNegativacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ACOMPANHAR_PAGAMENTO_DO_PARCELAMENTO:

						TarefaBatchAcompanharPagamentoDoParcelamento acompanharPagamentoDoParcelamento = new TarefaBatchAcompanharPagamentoDoParcelamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection rotas = getControladorSpcSerasa()
								.consultarRotasParaGerarResumoDiarioNegativacao();

						acompanharPagamentoDoParcelamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										rotas);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(acompanharPagamentoDoParcelamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_DOCUMENTOS_A_RECEBER:

						TarefaBatchGerarResumoDocumentosAReceber tarefaBatchGerarResumoDocumentosAReceber = new TarefaBatchGerarResumoDocumentosAReceber(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeGerarResumoDocumentosAReceber = new FiltroLocalidade();

						Collection<Localidade> colLocalidadeGerarResumoDocumentosAReceber = getControladorUtil()
								.pesquisar(
										filtroLocalidadeGerarResumoDocumentosAReceber,
										Localidade.class.getName());

						tarefaBatchGerarResumoDocumentosAReceber
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colLocalidadeGerarResumoDocumentosAReceber);

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoDocumentosAReceber));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.BATCH_EMITIR_ORDEM_FISCALIZAO:

						TarefaBatchEmitirOrdemDeFiscalizacao tarefaBatchEmitirOrdemDeFiscalizacao = new TarefaBatchEmitirOrdemDeFiscalizacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetores = new FiltroSetorComercial();

						filtroSetores.adicionarParametro(new ParametroSimples(
								FiltroSetorComercial.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

						Collection<SetorComercial> colecaoSetores = getControladorUtil()
								.pesquisar(filtroSetores,
										SetorComercial.class.getName());

						tarefaBatchEmitirOrdemDeFiscalizacao
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoSetores);

						tarefaBatchEmitirOrdemDeFiscalizacao.addParametro(
								"SistemaParametros", sistemaParametros);

						// Seta o objeto para ser serializado no banco, onde
						// depois será executado por uma thread.
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchEmitirOrdemDeFiscalizacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
					case Funcionalidade.BATCH_GERAR_ARQUIVO_ORDEM_FISCALIZAO:

						TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB tarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB = new TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());
						// Seta o objeto para ser serializado no banco, onde
						// depois será executado por uma thread.
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.DETERMINAR_CONFIRMACAO_DA_NEGATIVACAO:
						TarefaBatchDeterminarConfirmacaoDaNegativacao determinarConfirmacaoDaNegativacao = new TarefaBatchDeterminarConfirmacaoDaNegativacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colLocalidades = getControladorSpcSerasa().consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao();

						determinarConfirmacaoDaNegativacao.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, colLocalidades);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(determinarConfirmacaoDaNegativacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_PENDENCIA_POR_ANO:
						TarefaBatchGerarResumoPendenciaPorAno dadosGerarResumoPendenciaPorAno = new TarefaBatchGerarResumoPendenciaPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroPendenciaPorAno = new FiltroSetorComercial();
						Collection<SetorComercial> colecaoSetorComercialPendenciaPorAno = getControladorUtil()
								.pesquisar(filtroPendenciaPorAno,
										SetorComercial.class.getName());

						// Seta os parametros para rodar a funcionalidade
						dadosGerarResumoPendenciaPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoSetorComercialPendenciaPorAno);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoPendenciaPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.BATCH_ATUALIZAR_CODIGO_DEBITO_AUTOMATICO:

						TarefaBatchAtualizarCodigoDebitoAutomatico tarefaBatchAtualizarCodigoDebitoAutomatico = new TarefaBatchAtualizarCodigoDebitoAutomatico(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetoresParaAtualizarCodigoDebitoAutomatico = new FiltroSetorComercial();

						Collection<SetorComercial> colecaoSetoresParaAtualizar = getControladorUtil()
								.pesquisar(
										filtroSetoresParaAtualizarCodigoDebitoAutomatico,
										SetorComercial.class.getName());

						tarefaBatchAtualizarCodigoDebitoAutomatico
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoSetoresParaAtualizar);

						// Seta o objeto para ser serializado no banco, onde
						// depois será executado por uma thread.
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchAtualizarCodigoDebitoAutomatico));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.GERAR_RESUMO_LIGACOES_ECONOMIAS_POR_ANO:
						TarefaBatchGerarResumoLigacoesEconomiasPorAno gerarResumoLigacoesEconomiasPorAno = new TarefaBatchGerarResumoLigacoesEconomiasPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial fSetorComercial = new FiltroSetorComercial();
						Collection<SetorComercial> setorComercialColecao = getControladorUtil()
								.pesquisar(fSetorComercial,
										SetorComercial.class.getName());

						gerarResumoLigacoesEconomiasPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										setorComercialColecao);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoLigacoesEconomiasPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_INDICADORES_MICROMEDICAO_POR_ANO:

						TarefaBatchGerarResumoIndicadoresMicromedicaoPorAno gerarResumoIndicadoresMicromedicaoPorAno = new TarefaBatchGerarResumoIndicadoresMicromedicaoPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoIndicadoresMicromedicaoPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_CONSUMO_AGUA_POR_ANO:
						TarefaBatchGerarResumoConsumoAguaPorAno gerarResumoConsumoAguaPorAno = new TarefaBatchGerarResumoConsumoAguaPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetComercial = new FiltroSetorComercial();
						Collection<SetorComercial> colSetComercial = getControladorUtil()
								.pesquisar(filtroSetComercial,
										SetorComercial.class.getName());

						gerarResumoConsumoAguaPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetComercial);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoConsumoAguaPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_RECEITA:

						TarefaBatchGerarResumoReceita tarefaBatchGerarResumoReceita = new TarefaBatchGerarResumoReceita(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoReceita));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_FATURAMENTO_POR_ANO:

						TarefaBatchGerarResumoFaturamentoPorAno dadosGerarResFaturamento = new TarefaBatchGerarResumoFaturamentoPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filFaturamento = new FiltroSetorComercial();

						Collection<SetorComercial> colSetFaturamento = getControladorUtil()
								.pesquisar(filFaturamento,
										SetorComercial.class.getName());

						// Adicionamos um setor comercial ficticio que será
						// utilizado
						// para selecionarmos as guias de pagamento sem imovel.
						SetorComercial setComercial = new SetorComercial();
						setComercial.setId(99999);
						colSetFaturamento.add(setComercial);

						dadosGerarResFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetFaturamento);

						dadosGerarResFaturamento.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_ARRECADACAO_POR_ANO:
						TarefaBatchGerarResumoArrecadacaoPorAno dadosGerarResumoArrecadacaoAno = new TarefaBatchGerarResumoArrecadacaoPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FiltroSetorComercial gra_filtroSetor = new
						// FiltroSetorComercial();
						// Collection<SetorComercial> gra_colSetorComercial =
						// getControladorUtil()
						// .pesquisar(gra_filtroSetor,
						// SetorComercial.class.getName());

						FiltroLocalidade filtroLocalidadeResumoArrecadacaoAno = new FiltroLocalidade();
						Collection<Localidade> colLocalidadeResumoArrecadacaoAno = getControladorUtil()
								.pesquisar(
										filtroLocalidadeResumoArrecadacaoAno,
										Localidade.class.getName());

						dadosGerarResumoArrecadacaoAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colLocalidadeResumoArrecadacaoAno);

						dadosGerarResumoArrecadacaoAno.addParametro(
								"anoMesReferenciaArrecadacao",
								sistemaParametros.getAnoMesArrecadacao());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoArrecadacaoAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_COLETA_ESGOTO_POR_ANO:
						TarefaBatchGerarResumoColetaEsgotoPorAno gerarResumoColetaEsgotoPorAno = new TarefaBatchGerarResumoColetaEsgotoPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroSetorComPorAno = new FiltroSetorComercial();
						Collection<SetorComercial> colSetorComPorAno = getControladorUtil()
								.pesquisar(filtroSetorComPorAno,
										SetorComercial.class.getName());

						gerarResumoColetaEsgotoPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorComPorAno);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread marcio
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoColetaEsgotoPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_REGISTRO_ATENDIMENTO_POR_ANO:
						TarefaBatchGerarResumoRegistroAtendimentoPorAno gerarResumoRegistroAtendimentoPorAno = new TarefaBatchGerarResumoRegistroAtendimentoPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMesPorAno = getControladorLocalidade()
								.pesquisarTodosIdsLocalidade();

						gerarResumoRegistroAtendimentoPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarResumoRegistroAtendimentoMesPorAno);

						gerarResumoRegistroAtendimentoPorAno.addParametro(
								"anoMesFaturamentoSistemaParametro",
								sistemaParametros.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoRegistroAtendimentoPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre */
					case Funcionalidade.GERAR_RESUMO_INSTALACOES_HIDROMETROS_POR_ANO:
						TarefaBatchGerarResumoInstalacoesHidrometrosPorAno dadosGerarResumoInstalacoesHidrometrosPorAno = new TarefaBatchGerarResumoInstalacoesHidrometrosPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						/**
						 * Coleaao de todos ids de setor comercial para os
						 * imaveis que tem hidrometro instalado no historico
						 * para o ano/mes de referancia do faturamento.
						 */
						Collection<Integer> colecaoIdsSetoresComercialPorAno = getControladorGerencialMicromedicao()
								.pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
										anoMesFaturamentoSistemaParametro);

						// Seta os parametros para rodar a funcionalidade
						dadosGerarResumoInstalacoesHidrometrosPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsSetoresComercialPorAno);

						dadosGerarResumoInstalacoesHidrometrosPorAno
								.addParametro("anoMesReferenciaFaturamento",
										anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoInstalacoesHidrometrosPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_PARCELAMENTO_POR_ANO:
						TarefaBatchGerarResumoParcelamentoPorAno dadosGerarResumoParcelamentoPorAno = new TarefaBatchGerarResumoParcelamentoPorAno(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());
						// 2222
						Collection<Integer> colecaoLocalidadesParcelamentoPorAno = getControladorFaturamento()
								.pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias();

						dadosGerarResumoParcelamentoPorAno
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidadesParcelamentoPorAno);

						dadosGerarResumoParcelamentoPorAno.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoParcelamentoPorAno));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ALTERAR_INSCRICOES_IMOVEIS:

						TarefaBatchAlterarInscricaoImovel tarefaBatchAlterarInscricaoImovel = new TarefaBatchAlterarInscricaoImovel(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeAlterarInscricoesImoveis = new FiltroLocalidade();

						Collection<Localidade> colecaoLocalidadeAlterarInscricoesImoveis = getControladorUtil()
								.pesquisar(
										filtroLocalidadeAlterarInscricoesImoveis,
										Localidade.class.getName());

						// Seta os parametros para rodar a funcionalidade
						tarefaBatchAlterarInscricaoImovel
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidadeAlterarInscricoesImoveis);

						// Seta o objeto para ser serializado no banco, onde
						// depois será executado por uma thread.
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchAlterarInscricaoImovel));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					/*
					 * Autor: Hugo Leonardo Data: 07/07/2010 CRC: 2111 Analista:
					 * Adriana Ribeiro.
					 */
					case Funcionalidade.GERAR_PRESCREVER_DEBITOS_DE_IMOVEIS:
						TarefaBatchGerarPrescreverDebitosDeImoveis dadosGerarPrescreverDebitosDeImoveis = 
							new TarefaBatchGerarPrescreverDebitosDeImoveis(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection<Integer> colecaoCobrancaSituacao = getControladorCobranca()
								.obterCobrancaSituacaoParaPrescreverDebitos();

						dadosGerarPrescreverDebitosDeImoveis.addParametro(
								"colecaoCobrancaSituacao",
								colecaoCobrancaSituacao);

						dadosGerarPrescreverDebitosDeImoveis.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarPrescreverDebitosDeImoveis));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_REFATURAMENTO_NOVO:

						// [FS0001 ? Verificar existência de resumo de
						// refaturamento para o ano/mês de referência]
						Integer existeResumo = null;

						existeResumo = getControladorGerencialFaturamento()
								.verificarExistenciaResumoReFaturamento(
										sistemaParametros
												.getAnoMesFaturamento());

						if (existeResumo == null || existeResumo > 0) {

							throw new ControladorException(
									"atencao.resumo.refaturamento.ja.existe",
									null, sistemaParametros
											.getAnoMesFaturamento().toString());

						}

						TarefaBatchGerarResumoReFaturamentoNovo dadosGerarResumoReFaturamentoNovo = new TarefaBatchGerarResumoReFaturamentoNovo(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroReFaturamentoNovo = new FiltroSetorComercial();

						Collection<SetorComercial> colSetorReFaturamentoNovo = getControladorUtil()
								.pesquisar(filtroReFaturamentoNovo,
										SetorComercial.class.getName());

						dadosGerarResumoReFaturamentoNovo
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorReFaturamentoNovo);

						dadosGerarResumoReFaturamentoNovo.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoReFaturamentoNovo));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO_SEM_QUADRA:
						TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra gerarResumoAguaEsgotoSemQuadra = new TarefaBatchGerarResumoHistogramaAguaEsgotoSemQuadra(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroSetorComercial filtroHistogramaSemQuadra = new FiltroSetorComercial();
						Collection<SetorComercial> colSetorHistogramaSemQuadra = getControladorUtil()
								.pesquisar(filtroHistogramaSemQuadra,
										SetorComercial.class.getName());

						gerarResumoAguaEsgotoSemQuadra
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colSetorHistogramaSemQuadra);

						gerarResumoAguaEsgotoSemQuadra.addParametro(
								"anoMesFaturamentoSistemaParametro",
								anoMesFaturamentoSistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarResumoAguaEsgotoSemQuadra));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL:

						TarefaBatchReligarImoveisCortadosComConsumoReal dadosReligarImoveisCortadosComConsumoReal = new TarefaBatchReligarImoveisCortadosComConsumoReal(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeReligarImoveisCortadosComConsumoReal = new FiltroLocalidade();

						Collection<Localidade> colecaoLocalidadesReligarImoveisCortadosComConsumoReal = getControladorUtil()
								.pesquisar(
										filtroLocalidadeReligarImoveisCortadosComConsumoReal,
										Localidade.class.getName());

						dadosReligarImoveisCortadosComConsumoReal.addParametro(
								"anoMesFaturamento", sistemaParametros
										.getAnoMesFaturamento());

						// Seta os parametros para rodar a funcionalidade
						dadosReligarImoveisCortadosComConsumoReal
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoLocalidadesReligarImoveisCortadosComConsumoReal);

						// Seta o objeto para ser serializado no banco, onde
						// depois serao executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosReligarImoveisCortadosComConsumoReal));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/*
					 * Autor: Hugo Leonardo. Data: 19/10/2010
					 */
					case Funcionalidade.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS:
						TarefaBatchPrescreverDebitosImoveisPublicosAutomatico prescricao = new TarefaBatchPrescreverDebitosImoveisPublicosAutomatico(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Collection colecaoDadosPrescricaoAutomaticos = getControladorFaturamento()
								.obterDadosPrescricaoDebitosAutomaticos();

						//Adicionar o conjunto de parametros informados pelo
						//usuário através da interface do sistema
						
						if (Util.isVazioOrNulo(colecaoDadosPrescricaoAutomaticos)){

							colecaoDadosPrescricaoAutomaticos = new ArrayList();

							Object[] idEsferapoder = new Object[2];
							idEsferapoder[0] = EsferaPoder.ESTADUAL;
							idEsferapoder[1] = EsferaPoder.FEDERAL;

							colecaoDadosPrescricaoAutomaticos.add(idEsferapoder);
						}

						
						prescricao.addParametro("colecaoDadosPrescricao",
								colecaoDadosPrescricaoAutomaticos);

						prescricao.addParametro("anoMesFaturamento",
								sistemaParametros.getAnoMesFaturamento());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(prescricao));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
						
					case Funcionalidade.SELECIONAR_COMANDO_RETIRAR_IMOVEL_TARIFA_SOCIAL:
						
						TarefaBatchRetirarImovelTarifaSocial tarefaBatchRetirarImovelTarifaSocial = new TarefaBatchRetirarImovelTarifaSocial(
								processoIniciado.getUsuario(),funcionalidadeIniciada.getId());
						
						Collection idsLocalidade = getControladorCadastro().pesquisarLocalidade();
						
						tarefaBatchRetirarImovelTarifaSocial.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,idsLocalidade);
						
						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchRetirarImovelTarifaSocial));
						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
						
						/*
						 * [UC1122] Automatizar Perfis de Grandes Consumidores
						 * 
						 * Mariana Victor - 07/02/2011 
						 * 
						 * */
					case Funcionalidade.AUTOMATIZAR_PERFIS_DE_GRANDES_CONSUMIDORES:

						TarefaBatchAutomatizarPerfisDeGrandesConsumidores dadosAutomatizarPerfisDeGrandesConsumidores = new TarefaBatchAutomatizarPerfisDeGrandesConsumidores(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						FiltroLocalidade filtroLocalidadeAutomatizarPerfis = new FiltroLocalidade();

						Collection<Localidade> colecaoIdsLocalidadesAutomatizarPerfisDeGrandesConsumidores = getControladorUtil()
								.pesquisar(
										filtroLocalidadeAutomatizarPerfis,
										Localidade.class.getName());

						// Seta os parametros para rodar a funcionalidade
						dadosAutomatizarPerfisDeGrandesConsumidores
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesAutomatizarPerfisDeGrandesConsumidores);

						// Seta o objeto para ser serializado no banco, onde
						// depois serao executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosAutomatizarPerfisDeGrandesConsumidores));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					case Funcionalidade.GERAR_TXT_IMPRESSAO_CONTAS_FORMATO_BRAILLE:
						
						Collection colecaoGrupoNaoFaturados = getControladorFaturamento().pesquisarGrupoFaturamentoGrupoNaoFaturados
							(anoMesFaturamentoSistemaParametro);
						//Caso haja algum grupo de faturamento que não tenha sido faturado
						if(colecaoGrupoNaoFaturados != null && !colecaoGrupoNaoFaturados.isEmpty()){
							throw new ControladorException("atencao.processo_cancelado_grupo_nao_faturado");
						}

						TarefaBatchGerarTxtImpressaoContasBraille gerarTxtContasBraille = new TarefaBatchGerarTxtImpressaoContasBraille(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarTxtContasBraille));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					//[UC1183] Gerar Arquivo TXT OS Contas Pagas/Parceladas
					case Funcionalidade.GERAR_ARQUIVO_TXT_OS_CONTAS_PAGAS_PARCELADAS:

						TarefaBatchGerarArquivoTextoOSContasPagasParceladas gerarArqvTxtOSContasPagasParceladas = new TarefaBatchGerarArquivoTextoOSContasPagasParceladas(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());
						
							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(gerarArqvTxtOSContasPagasParceladas));
							getControladorUtil().atualizar(funcionalidadeIniciada);

							break;	
						
					case Funcionalidade.ENVIO_EMAIL_CONTA_PARA_CLIENTE:
						TarefaBatchEnvioEmailContaParaCliente tarefaBatchEnvioEmailContaParaCliente = new TarefaBatchEnvioEmailContaParaCliente(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						FiltroLocalidade filtroLocalidadeEnvioEmail = new FiltroLocalidade();
						filtroLocalidadeEnvioEmail.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
						
						Collection<Localidade> collectionLocalidades = Fachada.getInstancia().pesquisar(filtroLocalidadeEnvioEmail, Localidade.class.getName());
						
						tarefaBatchEnvioEmailContaParaCliente
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										collectionLocalidades);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchEnvioEmailContaParaCliente));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					case Funcionalidade.PROGRAMACAO_AUTO_ROTEIRO_ACOMPANHAMENTO_OS:
						TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS = new TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// ******************************************************
						// Seta os parametros para rodar a funcionalidade
						
						Collection collIdsUnidadesOrganizacionais = getControladorOrdemServico().pequisarUnidadesOrganizacionaisdasEquipes();


						tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										collIdsUnidadesOrganizacionais);


						tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS.addParametro("dataAtual",
								new Date());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					case Funcionalidade.GERAR_DADOS_ARQUIVO_ACOMPANHAMENTO_SERVICO:
						TarefaBatchGerarDadosArquivoAcompanhamentoServico tarefaBatchGerarDadosArquivoAcompanhamentoServico = 
							new TarefaBatchGerarDadosArquivoAcompanhamentoServico(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// ******************************************************
						// Seta os parametros para rodar a funcionalidade
						
						Collection collIdsUnidadesOrganizacionais2 = getControladorOrdemServico().pequisarUnidadesOrganizacionaisdasEquipes();


						tarefaBatchGerarDadosArquivoAcompanhamentoServico
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										collIdsUnidadesOrganizacionais2);


						tarefaBatchGerarDadosArquivoAcompanhamentoServico.addParametro("dataAtual", new Date());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarDadosArquivoAcompanhamentoServico));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
						
					case Funcionalidade.PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO:
						
						TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo tarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo = new TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());
						
						funcionalidadeIniciada
						.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo));

						getControladorUtil().atualizar(
								funcionalidadeIniciada);
						
						break;

					
					case Funcionalidade.GERAR_DADOS_RELATORIO_BIG:{
						TarefaBatchGerarDadosRelatorioBIG gerarDadosRelatorioBIG = new TarefaBatchGerarDadosRelatorioBIG(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						gerarDadosRelatorioBIG.addParametro("anoMesReferencia", sistemaParametros.getAnoMesArrecadacao());

						FiltroLocalidade filtroLocalidadeGerarDadosRelatorioBIG = new FiltroLocalidade(FiltroLocalidade.ID);
						filtroLocalidadeGerarDadosRelatorioBIG.adicionarParametro(new ParametroSimples(
								FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
						filtroLocalidadeGerarDadosRelatorioBIG.setCampoOrderBy(FiltroLocalidade.ID);

						Collection<Localidade> colecaoLocalidadesGerarDadosRelatorioBIG = getControladorUtil()
								.pesquisar(filtroLocalidadeGerarDadosRelatorioBIG, Localidade.class.getName());

						gerarDadosRelatorioBIG.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoLocalidadesGerarDadosRelatorioBIG);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarDadosRelatorioBIG));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					}
						
					case Funcionalidade.CANCELAR_GUIAS_PAGAMENTO_NAO_PAGAS:

						TarefaBatchCancelarGuiasPagamentoNaoPagas cancelarGuiasPagamentoNaoPagas = new TarefaBatchCancelarGuiasPagamentoNaoPagas(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Date dataReferencia = new Date();
						cancelarGuiasPagamentoNaoPagas.addParametro("dataReferencia", dataReferencia);

						Collection<Integer> colecaoIdsLocalidadesComGuiasPagamentoNaoPagas = getControladorArrecadacao()
								.pesquisarIdsLocalidadeComGuiasPagamentoNaoPagas(dataReferencia, null);
						
						cancelarGuiasPagamentoNaoPagas.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoIdsLocalidadesComGuiasPagamentoNaoPagas);


						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(cancelarGuiasPagamentoNaoPagas));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					case Funcionalidade.PROCESSAR_PAGAMENTOS_COM_DIFERENCA_DE_DOIS_REAIS:{
						TarefaBatchProcessarPagamentosComDiferencaDoisReais batch = new TarefaBatchProcessarPagamentosComDiferencaDoisReais(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						batch.addParametro("anoMesReferencia", sistemaParametros.getAnoMesArrecadacao());

						FiltroLocalidade filtroBatch = new FiltroLocalidade(FiltroLocalidade.ID);
						filtroBatch.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
						filtroBatch.setCampoOrderBy(FiltroLocalidade.ID);

						Collection<Localidade> localidades = getControladorUtil().pesquisar(filtroBatch, Localidade.class.getName());

						batch.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, localidades);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(batch));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;		
					}
					
					case Funcionalidade.ATUALIZACAO_CADASTRAL: {
						TarefaBatchAtualizacaoCadastral batchAtualizacaoCadastral = new TarefaBatchAtualizacaoCadastral(
								processoIniciado.getUsuario(), funcionalidadeIniciada.getId());
						
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
						filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.SIM));
						Collection<FaturamentoGrupo> colecaoFaturamentoGrupos = getControladorUtil().pesquisar(
								filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

						gerarDadosReceitasAFaturarResumo.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoFaturamentoGrupos);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(gerarDadosReceitasAFaturarResumo));

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

	public Integer inserirProcessoIniciadoFaturamentoComandado(
			Collection<Integer> idsFaturamentoAtividadeCronograma,
			Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;
		try {

			Iterator<Integer> iteratorFaturamentoAtividadeCronograma = idsFaturamentoAtividadeCronograma
					.iterator();

			// Este trecho insere um processoIniciado e as
			// funcionalidadesIniciadas para cada FaturamentoAtividadeCronograma
			// informado pelo usuario
			while (iteratorFaturamentoAtividadeCronograma.hasNext()) {

				Integer codigoFaturamentoAtividadeCronograma = iteratorFaturamentoAtividadeCronograma
						.next();

				// Pesquisa as rotas associadas ao
				// FaturamentoAtividadeCronograma
				Collection colecaoFaturamentoAtivCronRota = repositorioBatch
						.pesquisarRotasProcessamentoBatchFaturamentoComandado(codigoFaturamentoAtividadeCronograma);

				// [FS0001] - Verificar existencia de dados
				if (colecaoFaturamentoAtivCronRota == null
						|| colecaoFaturamentoAtivCronRota.isEmpty()) {
					throw new ControladorException(
							"atencao.faturamento_nenhuma_rota");

				}

				// Busca o FaturamentoAtividade para obter os dados para montar
				// o processoIniciado
				FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
				filtroFaturamentoAtividadeCronograma
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoAtividadeCronograma.ID,
								codigoFaturamentoAtividadeCronograma));

				filtroFaturamentoAtividadeCronograma
						.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade.processo");
				filtroFaturamentoAtividadeCronograma
						.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");

				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
						.retonarObjetoDeColecao(getControladorUtil().pesquisar(
								filtroFaturamentoAtividadeCronograma,
								FaturamentoAtividadeCronograma.class.getName()));

				// Alteração: 01/03/2010
				// Autor: Rafael Pinto
				// Caso o processo seja de FATURAR_GRUPO , e existem rotas que
				// ainda nao foram transmitidas
				// para impressao simultanea nao sera possivel executar o
				// processo.
				Processo processo = faturamentoAtividadeCronograma
						.getFaturamentoAtividade().getProcesso();
				FaturamentoGrupo faturamentoGrupo = faturamentoAtividadeCronograma
						.getFaturamentoGrupoCronogramaMensal()
						.getFaturamentoGrupo();

				if (processo.getId().intValue() == Processo.FATURAR_GRUPO_FATURAMENTO) {
					Collection<Rota> colecaoRota = this.repositorioMicromedicao
							.pesquisaRotasNaoTransmitidas(faturamentoGrupo.getAnoMesReferencia(), faturamentoGrupo.getId());

					if (colecaoRota != null && !colecaoRota.isEmpty()) {
						Iterator iteratorRota = colecaoRota.iterator();
						String mensagemAlerta = null;
						while (iteratorRota.hasNext()) {
							Rota rotaNaoTransmitida = (Rota) iteratorRota.next();
							if (mensagemAlerta == null) {
								mensagemAlerta = rotaNaoTransmitida.getId().toString();
							} else {
								mensagemAlerta += ","+ rotaNaoTransmitida.getId().toString();
							}
						}

						throw new ControladorException("atencao.rotas_nao_transmitidas", null,mensagemAlerta);

					}
				}

				// Construi um processoIniciado para cada FaturamentoAtividadeCronograma
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

				// Este trecho pesquisa todos do processoFuncionalidade relacionados com o processo do objeto a ser inserido
				FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();
				filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.ID_PROCESSO,processoIniciado.getProcesso().getId()));
				filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(FiltroProcessoFuncionalidade.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection processosFuncionaliadade = getControladorUtil().pesquisar(filtroProcessoFuncionalidade,ProcessoFuncionalidade.class.getName());

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

						TarefaBatchGerarDadosParaLeitura dadosParaLeitura = new TarefaBatchGerarDadosParaLeitura(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						dadosParaLeitura.addParametro("anoMesFaturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());
						dadosParaLeitura.addParametro("idFaturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo().getId());

						dadosParaLeitura.addParametro("sistemaParametro",
								sistemaParametro);

						dadosParaLeitura
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(dadosParaLeitura));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					// pedro alexandre dia 13/09/2007
					case Funcionalidade.GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA:
						TarefaBatchGerarArquivoTextoParaLeiturista dadosGerarArquivoTextoParaLeiturista = new TarefaBatchGerarArquivoTextoParaLeiturista(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						dadosGerarArquivoTextoParaLeiturista
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Alterado por Raphael Rossiter em 16/04/2008 -
						// Analista: Aryed Lins
						dadosGerarArquivoTextoParaLeiturista.addParametro(
								"colecaoRotas", colecaoFaturamentoAtivCronRota);

						dadosGerarArquivoTextoParaLeiturista.addParametro(
								"anoMesFaturamento",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());
						dadosGerarArquivoTextoParaLeiturista.addParametro(
								"dataComando", faturamentoAtividadeCronograma
										.getComando());
						dadosGerarArquivoTextoParaLeiturista.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());
						dadosGerarArquivoTextoParaLeiturista.addParametro(
								"sistemaParametro", sistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarArquivoTextoParaLeiturista));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS:

						TarefaBatchConsistirLeiturasCalcularConsumos calcularConsumos = new TarefaBatchConsistirLeiturasCalcularConsumos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// --------------------------------------------------------------
						// @Autor: Yara T. Souza
						// @Data:09/01/09
						// Processar Leituras Não Registradas
						// --------------------------------------------------------------
						getControladorMicromedicao()
								.processarLeiturasNaoResgistradas(
										faturamentoAtividadeCronograma
												.getFaturamentoGrupoCronogramaMensal()
												.getFaturamentoGrupo());
						// --------------------------------------------------------------

						// Seta os parametros para rodar a funcionalidade
						calcularConsumos.addParametro("faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());
						calcularConsumos
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						sistemaParametro
								.setAnoMesFaturamento(faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getAnoMesReferencia());

						calcularConsumos.addParametro("sistemaParametros",
								sistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(calcularConsumos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.GERAR_BONUS_TARIFA_SOCIAL:

						TarefaBatchGerarBonusTarifaSocial tarefaBatchGerarBonusTarifaSocial = new TarefaBatchGerarBonusTarifaSocial(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						tarefaBatchGerarBonusTarifaSocial.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());
						tarefaBatchGerarBonusTarifaSocial
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						sistemaParametro
								.setAnoMesFaturamento(faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getAnoMesReferencia());

						tarefaBatchGerarBonusTarifaSocial.addParametro(
								"sistemaParametros", sistemaParametro);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarBonusTarifaSocial));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.EFETUAR_RATEIO_CONSUMO:
						TarefaBatchEfetuarRateioConsumo rateioConsumo = new TarefaBatchEfetuarRateioConsumo(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						rateioConsumo.addParametro("anoMesFaturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());
						rateioConsumo
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(rateioConsumo));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.FATURAR_GRUPO_FATURAMENTO:
						TarefaBatchFaturarGrupoFaturamento faturarGrupoFaturamento = new TarefaBatchFaturarGrupoFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						faturarGrupoFaturamento.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());

						faturarGrupoFaturamento.addParametro("atividade",
								faturamentoAtividadeCronograma
										.getFaturamentoAtividade().getId());

						faturarGrupoFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(faturarGrupoFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.GERAR_TAXA_ENTREGA_CONTA_OUTRO_ENDERECO:
						TarefaBatchGerarTaxaEntregaContaOutroEndereco taxaEntrega = new TarefaBatchGerarTaxaEntregaContaOutroEndereco(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						taxaEntrega.addParametro("anoMesFaturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());
						taxaEntrega
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(taxaEntrega));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_DEBITOS_A_COBRAR_ACRESCIMOS_IMPONTUALIDADE:
						TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade impontualidade = new TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						/** Caso for o processo de encerrar ARRECADACAO do mes */
						if (processoIniciado.getProcesso().getId().equals(
								Processo.ENCERRAR_ARRECADACAO_MES)) {

							System.out.println("ENCERRAR ARRECADACAO DO MES");
							// ENCERRAR ARRECADACAO DO MES
							Collection colecaoTodasRotas = getControladorMicromedicao()
									.pesquisarListaRotasCarregadas();

							impontualidade
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoTodasRotas);

							impontualidade.addParametro(
									"indicadorGeracaoMulta",
									ConstantesSistema.SIM);

							impontualidade.addParametro(
									"indicadorGeracaoJuros",
									ConstantesSistema.SIM);

							impontualidade.addParametro(
									"indicadorGeracaoAtualizacao",
									ConstantesSistema.SIM);

							impontualidade.addParametro(
									"indicadorEncerrandoArrecadacao", true);

						} else {
							System.out.println("FATURAR GRUPO DE FATURAMENTO");
							// FATURAR GRUPO DE FATURAMENTO
							impontualidade
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoFaturamentoAtivCronRota);

							impontualidade.addParametro(
									"indicadorGeracaoMulta",
									ConstantesSistema.SIM);

							impontualidade.addParametro(
									"indicadorGeracaoJuros",
									ConstantesSistema.NAO);

							impontualidade.addParametro(
									"indicadorGeracaoAtualizacao",
									ConstantesSistema.NAO);

							impontualidade.addParametro(
									"indicadorEncerrandoArrecadacao", false);

						}

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(impontualidade));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_CONTAS:
						TarefaBatchEmitirContas emitirContas = new TarefaBatchEmitirContas(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						emitirContas.addParametro("anoMesFaturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());

						emitirContas.addParametro("faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());

						Collection colecaoIdsEmpresas = getControladorCadastro()
								.pesquisarIdsEmpresa();

						emitirContas
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsEmpresas);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(emitirContas));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_DEBITO_COBRAR_DOACAO:
						TarefaBatchGerarDebitosACobrarDoacao gerarDebitoACobrarDoacao = new TarefaBatchGerarDebitosACobrarDoacao(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						gerarDebitoACobrarDoacao
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarDebitoACobrarDoacao));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO:
						TarefaBatchEmitirExtratoConsumoImovelCondominio emitirExtratoConsumoImovelCondominio = new TarefaBatchEmitirExtratoConsumoImovelCondominio(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						emitirExtratoConsumoImovelCondominio.addParametro(
								"anoMesFaturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());

						emitirExtratoConsumoImovelCondominio.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(emitirExtratoConsumoImovelCondominio));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_CREDITO_SITUACAO_ESPECIAL_FATURAMENTO:

						TarefaBatchGerarCreditoSituacaoEspecialFaturamento gerarCreditoSituacaoespecialFaturamento = new TarefaBatchGerarCreditoSituacaoEspecialFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FATURAMENTO_GRUPO
						gerarCreditoSituacaoespecialFaturamento.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());

						// ROTAS SELECIONADAS
						gerarCreditoSituacaoespecialFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						gerarCreditoSituacaoespecialFaturamento.addParametro(
								"atividade", faturamentoAtividadeCronograma
										.getFaturamentoAtividade().getId());

						/*
						 * Seta o objeto para ser serializado no banco, onde
						 * depois será executado por uma thread.
						 */
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarCreditoSituacaoespecialFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.SUPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL:

						TarefaBatchSuspenderImovelEmProgramaEspecial tarefaBatchSuspenderImovelEmProgramaEspecial = new TarefaBatchSuspenderImovelEmProgramaEspecial(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tarefaBatchSuspenderImovelEmProgramaEspecial
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois será executado por uma thread.
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchSuspenderImovelEmProgramaEspecial));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_RESUMO_SIMULACAO_FATURAMENTO:
						TarefaBatchGerarResumoSimulacaoFaturamento tarefaBatchGerarResumoSimulacaoFaturamento = new TarefaBatchGerarResumoSimulacaoFaturamento(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						tarefaBatchGerarResumoSimulacaoFaturamento
								.addParametro(
										"faturamentoGrupo",
										faturamentoAtividadeCronograma
												.getFaturamentoGrupoCronogramaMensal()
												.getFaturamentoGrupo());

						tarefaBatchGerarResumoSimulacaoFaturamento
								.addParametro("atividade",
										faturamentoAtividadeCronograma
												.getFaturamentoAtividade()
												.getId());

						tarefaBatchGerarResumoSimulacaoFaturamento
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarResumoSimulacaoFaturamento));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					// *************************************************************
					// Autor: Ivan Sergio
					// Data: 30/07/2009
					// Solicitado por Leonardo Vieira
					// AINDA NÃO FOI APROVADO
					// *************************************************************
					/*
					 * case Funcionalidade.PROCESSAR_LEITURAS_NAO_REGISTRADAS:
					 * TarefaBatchProcessarLeiturasNaoResgistradas procLeituras =
					 * new TarefaBatchProcessarLeiturasNaoResgistradas(
					 * processoIniciado.getUsuario(),
					 * funcionalidadeIniciada.getId()); // Seta os parametros
					 * para rodar a funcionalidade
					 * procLeituras.addParametro("faturamentoGrupo",
					 * faturamentoAtividadeCronograma
					 * .getFaturamentoGrupoCronogramaMensal()
					 * .getFaturamentoGrupo());
					 * 
					 * sistemaParametro.setAnoMesFaturamento(faturamentoAtividadeCronograma
					 * .getFaturamentoGrupoCronogramaMensal()
					 * .getAnoMesReferencia());
					 * 
					 * procLeituras.addParametro("sistemaParametros",
					 * sistemaParametro); // Seta o objeto para ser serializado
					 * no banco, onde // depois sera executado por uma thread
					 * funcionalidadeIniciada.setTarefaBatch(
					 * IoUtil.transformarObjetoParaBytes(procLeituras));
					 * 
					 * getControladorUtil().atualizar(funcionalidadeIniciada);
					 * break;
					 */

					/*
					 * [UC0994]
					 * Passado para EVENTUAL
					 *
					case Funcionalidade.ENVIO_EMAIL_CONTA_PARA_CLIENTE:
						TarefaBatchEnvioEmailContaParaCliente tarefaBatchEnvioEmailContaParaCliente = new TarefaBatchEnvioEmailContaParaCliente(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						tarefaBatchEnvioEmailContaParaCliente.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());

						tarefaBatchEnvioEmailContaParaCliente.addParametro(
								"atividade", faturamentoAtividadeCronograma
										.getFaturamentoAtividade().getId());

						tarefaBatchEnvioEmailContaParaCliente
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchEnvioEmailContaParaCliente));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						*/
					case Funcionalidade.GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

						TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						SistemaParametro sistemaParametroQuitacao = getControladorUtil()
								.pesquisarParametrosDoSistema();

						tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos
								.addParametro("SistemaParametros",
										sistemaParametroQuitacao);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					case Funcionalidade.GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

						TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos
								.addParametro(
										"faturamentoGrupo",
										faturamentoAtividadeCronograma
												.getFaturamentoGrupoCronogramaMensal()
												.getFaturamentoGrupo());

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_TAXA_PERCENTUAL_TARIFA_MINIMA_CORTADO:

						TarefaBatchGerarTaxaPercentualTarifaMinimaCortado tarefaBatchGerarTaxaPercentualTarifaMinimaCortado = new TarefaBatchGerarTaxaPercentualTarifaMinimaCortado(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FATURAMENTO_GRUPO
						tarefaBatchGerarTaxaPercentualTarifaMinimaCortado
								.addParametro(
										"faturamentoGrupo",
										faturamentoAtividadeCronograma
												.getFaturamentoGrupoCronogramaMensal()
												.getFaturamentoGrupo());

						// ROTAS SELECIONADAS
						tarefaBatchGerarTaxaPercentualTarifaMinimaCortado
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						tarefaBatchGerarTaxaPercentualTarifaMinimaCortado
								.addParametro("atividade",
										faturamentoAtividadeCronograma
												.getFaturamentoAtividade()
												.getId());

						/*
						 * Seta o objeto para ser serializado no banco, onde
						 * depois será executado por uma thread.
						 */
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchGerarTaxaPercentualTarifaMinimaCortado));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.VERIFICAR_FATURAMENTO_IMOVEIS_CORTADOS:

						TarefaBatchVerificarFaturamentoImoveisCortados tarefaBatchVerificarFaturamentoImoveisCortados = new TarefaBatchVerificarFaturamentoImoveisCortados(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FATURAMENTO_GRUPO
						tarefaBatchVerificarFaturamentoImoveisCortados
								.addParametro(
										"faturamentoGrupo",
										faturamentoAtividadeCronograma
												.getFaturamentoGrupoCronogramaMensal()
												.getFaturamentoGrupo());

						// ROTAS SELECIONADAS
						tarefaBatchVerificarFaturamentoImoveisCortados
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						tarefaBatchVerificarFaturamentoImoveisCortados
								.addParametro("atividade",
										faturamentoAtividadeCronograma
												.getFaturamentoAtividade()
												.getId());

						/*
						 * Seta o objeto para ser serializado no banco, onde
						 * depois será executado por uma thread.
						 */
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchVerificarFaturamentoImoveisCortados));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					case Funcionalidade.GERAR_RA_OS_ANORMALIDADE_CONSUMO:

						TarefaBatchGerarRAOSAnormalidadeConsumo gerarRAOSAnormalidadeConsumo = new TarefaBatchGerarRAOSAnormalidadeConsumo(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade
						gerarRAOSAnormalidadeConsumo.addParametro(
								"faturamentoGrupo",
								faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo());

						gerarRAOSAnormalidadeConsumo
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoFaturamentoAtivCronRota);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(gerarRAOSAnormalidadeConsumo));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
						
					case Funcionalidade.SUSPENDER_LEITURA_PARA_IMOVEL_COM_HIDROMETRO_RETIRADO:
						/**
						 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado
						 * @author Vivianne Sousa
						 * @date 23/08/2011
						 */
						TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado = new TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FATURAMENTO_GRUPO
						tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado.addParametro("faturamentoGrupo",
						faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						// ROTAS SELECIONADAS
						tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado.addParametro(
						ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,colecaoFaturamentoAtivCronRota);

						tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado.addParametro("anoMesFaturamentoGrupo",
						faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia());
						
						funcionalidadeIniciada.setTarefaBatch(IoUtil
							.transformarObjetoParaBytes(tarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
					case Funcionalidade.SUSPENDER_LEITURA_PARA_IMOVEL_COM_CONSUMO_REAL_NAO_SUPERIOR_A_10:
						/**
						 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado
						 * @author Vivianne Sousa
						 * @date 23/08/2011
						 */
						TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10 tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10 = new TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// FATURAMENTO_GRUPO
						tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10.addParametro("faturamentoGrupo",
						faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo());

						// ROTAS SELECIONADAS
						tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10.addParametro(
						ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,colecaoFaturamentoAtivCronRota);

						tarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10.addParametro("anoMesFaturamentoGrupo",
						faturamentoAtividadeCronograma.getFaturamentoGrupoCronogramaMensal().getFaturamentoGrupo().getAnoMesReferencia());
						
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

	public Integer inserirProcessoIniciadoCobrancaComandado(
			Collection<Integer> idsCronograma,
			Collection<Integer> idsEventuais, Usuario usuario)
			throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;
		try {

			Iterator<Integer> iteratorCronograma = idsCronograma.iterator();

			Iterator<Integer> iteratorAtividade = idsEventuais.iterator();

			codigoProcessoIniciadoGerado = inserirProcessoCobrancaAtividadeCronograma(
					usuario, codigoProcessoIniciadoGerado, iteratorCronograma);

			codigoProcessoIniciadoGerado = inserirProcessoCobrancaAtividadeEventual(
					usuario, codigoProcessoIniciadoGerado, iteratorAtividade);

		} catch (IOException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		} catch (ParseException e) {
		}

		return codigoProcessoIniciadoGerado;

	}

	private Integer inserirProcessoCobrancaAtividadeCronograma(Usuario usuario,
			Integer codigoProcessoIniciadoGerado,
			Iterator<Integer> iteratorCronograma) throws ControladorException,
			ParseException, IOException {

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();
		// Este trecho insere um processoIniciado e as
		// funcionalidadesIniciadas para cada
		// CobrancaAcaoAtividadeCronograma
		// informado pelo usuario
		while (iteratorCronograma.hasNext()) {

			Integer codigoCobrancaAcaoAtividadeCronograma = iteratorCronograma
					.next();

			// [FS0001] - Verificar existancia de dados
			// if (resultado == null || resultado.isEmpty()) {
			// throw new ControladorException("");
			//
			// }

			// Busca o CobrancaAcaoAtividadeCronograma para obter os dados para
			// montar o processoIniciado
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = getControladorCobranca()
					.pesquisarCobrancaAcaoAtividadeCronogramaId(
							codigoCobrancaAcaoAtividadeCronograma);

			// Constrai um processoIniciado para cada
			// FaturamentoAtividadeCronograma
			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(usuario);

			processoIniciado
					.setCodigoGrupoProcesso(cobrancaAcaoAtividadeCronograma
							.getCobrancaAcaoCronograma()
							.getCobrancaGrupoCronogramaMes().getCobrancaGrupo()
							.getId());

			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(cobrancaAcaoAtividadeCronograma
					.getCobrancaAtividade().getProcesso().getId());
			processoSituacao.setId(processoSituacaoId); // Ver isso
			processoIniciado.setProcessoSituacao(processoSituacao);

			processoIniciado.setProcesso(cobrancaAcaoAtividadeCronograma
					.getCobrancaAtividade().getProcesso());
			processoIniciado.setDataHoraAgendamento(new Date());
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(cobrancaAcaoAtividadeCronograma
					.getComando());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// adicionado por Vivianne Sousa - 08/04/2010
			// Inserir CobrancaDocumentoControleGeracao
			CobrancaDocumentoControleGeracao cobrancaDocumentoControleGeracao = new CobrancaDocumentoControleGeracao(
					0, 0, new BigDecimal("0.00"), new Date(), processoIniciado,
					cobrancaAcaoAtividadeCronograma, null);

			Integer idCobrancaDocumentoControleGeracao = (Integer) getControladorUtil()
					.inserir(cobrancaDocumentoControleGeracao);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Date dataAtual = new Date();

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				// Seta os parametros da funcionalidadeIniciada

				// Seleciona as rotas
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.COBRANCA_GRUPO_ID,
						cobrancaAcaoAtividadeCronograma
								.getCobrancaAcaoCronograma()
								.getCobrancaGrupoCronogramaMes()
								.getCobrancaGrupo().getId()));
				if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
						.getCobrancaAcao().getId() != null
						&& cobrancaAcaoAtividadeCronograma
								.getCobrancaAcaoCronograma().getCobrancaAcao()
								.getId().equals(
										CobrancaAcao.CORTE_ADMINISTRATIVO)
						&& sistemaParametro.getCodigoEmpresaFebraban() != null
						&& sistemaParametro.getCodigoEmpresaFebraban().equals(
								Empresa.EMPRESA_FEBRABAN_COMPESA)) {
					filtroRota
							.adicionarParametro(new ParametroSimplesDiferenteDe(
									FiltroRota.EMPRESA_COBRANCA_ID, 1));
				}
				Collection<Rota> colecaoRotas = getControladorUtil().pesquisar(
						filtroRota, Rota.class.getName());

				// cria o formato da data
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

				switch (funcionalidadeIniciada.getProcessoFuncionalidade()
						.getFuncionalidade().getId()) {

				case Funcionalidade.GERAR_ATIVIDADE_ACAO_COBRANCA:
					TarefaBatchGerarAtividadeAcaoCobranca acaoCobranca = new TarefaBatchGerarAtividadeAcaoCobranca(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					// Seta os parametros para rodar a funcionalidade
					acaoCobranca.addParametro("grupoCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaGrupoCronogramaMes()
									.getCobrancaGrupo());

					acaoCobranca.addParametro("anoMesReferenciaCicloCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaGrupoCronogramaMes()
									.getAnoMesReferencia());

					acaoCobranca.addParametro("comandoAtividadeAcaoCobranca",
							cobrancaAcaoAtividadeCronograma);

					acaoCobranca.addParametro("colecaoRotas", colecaoRotas);

					if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
						acaoCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoRotas);
					} else {
						acaoCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										Collections
												.singletonList(cobrancaAcaoAtividadeCronograma
														.getId()));
					}

					acaoCobranca.addParametro("acaoCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao());

					acaoCobranca.addParametro("atividadeCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAtividade());

					acaoCobranca.addParametro("indicadorCriterio", new Short(
							"1"));

					acaoCobranca
							.addParametro("anoMesReferenciaInicial", 000101);

					acaoCobranca.addParametro("anoMesReferenciaFinal",
							(new Integer(Util.subtrairMesDoAnoMes(
									getControladorUtil()
											.pesquisarParametrosDoSistema()
											.getAnoMesFaturamento(), 1))));

					acaoCobranca.addParametro("dataVencimentoInicial", formato
							.parse("01/01/0001"));
					acaoCobranca.addParametro("dataAtual", dataAtual);

					acaoCobranca
							.addParametro(
									"dataVencimentoFinal",
									Util
											.subtrairNumeroDiasDeUmaData(
													new Date(),
													getControladorUtil()
															.pesquisarParametrosDoSistema()
															.getNumeroDiasVencimentoCobranca()));

					acaoCobranca.addParametro(
							"idCobrancaDocumentoControleGeracao",
							idCobrancaDocumentoControleGeracao);

					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada.setTarefaBatch(IoUtil
							.transformarObjetoParaBytes(acaoCobranca));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.EMITIR_BOLETIM_CADASTRO:
					TarefaBatchEmitirBoletimCadastro tarefaBatchEmitirBoletimCadastro = new TarefaBatchEmitirBoletimCadastro(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					// Seta os parametros para rodar a funcionalidade
					tarefaBatchEmitirBoletimCadastro.addParametro(
							"comandoAtividadeAcaoCobranca",
							cobrancaAcaoAtividadeCronograma);

					tarefaBatchEmitirBoletimCadastro.addParametro(
							"acaoCobranca", cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao());

					tarefaBatchEmitirBoletimCadastro.addParametro(
							"atividadeCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAtividade());

					tarefaBatchEmitirBoletimCadastro.addParametro("dataAtual",
							dataAtual);

					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada
							.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(tarefaBatchEmitirBoletimCadastro));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA:
					TarefaBatchAtualizarComandoAtividadeAcaoCobranca atualizarComandoAtividadeAcaoCobranca = new TarefaBatchAtualizarComandoAtividadeAcaoCobranca(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					// Seta os parametros para rodar a funcionalidade
					atualizarComandoAtividadeAcaoCobranca.addParametro(
							"grupoCobranca", cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaGrupoCronogramaMes()
									.getCobrancaGrupo());

					atualizarComandoAtividadeAcaoCobranca.addParametro(
							"anoMesReferenciaCicloCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaGrupoCronogramaMes()
									.getAnoMesReferencia());

					atualizarComandoAtividadeAcaoCobranca.addParametro(
							"comandoAtividadeAcaoCobranca",
							cobrancaAcaoAtividadeCronograma);

					atualizarComandoAtividadeAcaoCobranca
							.addParametro(
									ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									Collections
											.singletonList(cobrancaAcaoAtividadeCronograma
													.getId()));

					atualizarComandoAtividadeAcaoCobranca.addParametro(
							"acaoCobranca", cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao());

					atualizarComandoAtividadeAcaoCobranca.addParametro(
							"indicadorCriterio", new Short("1"));

					atualizarComandoAtividadeAcaoCobranca.addParametro(
							"idCobrancaDocumentoControleGeracao",
							idCobrancaDocumentoControleGeracao);

					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada
							.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(atualizarComandoAtividadeAcaoCobranca));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;

				case Funcionalidade.EMITIR_DOCUMENTO_COBRANCA:
					TarefaBatchEmitirDocumentoCobranca emitirDocumentoCobranca = new TarefaBatchEmitirDocumentoCobranca(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					// Seta os parametros para rodar a funcionalidade
					emitirDocumentoCobranca.addParametro("grupoCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaGrupoCronogramaMes()
									.getCobrancaGrupo());

					emitirDocumentoCobranca.addParametro(
							"comandoAtividadeAcaoCobranca",
							cobrancaAcaoAtividadeCronograma);

					emitirDocumentoCobranca
							.addParametro(
									ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									Collections
											.singletonList(cobrancaAcaoAtividadeCronograma
													.getId()));

					emitirDocumentoCobranca.addParametro("acaoCobranca",
							cobrancaAcaoAtividadeCronograma
									.getCobrancaAcaoCronograma()
									.getCobrancaAcao());

					emitirDocumentoCobranca
							.addParametro("dataAtual", dataAtual);

					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada
							.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(emitirDocumentoCobranca));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;
					
					/*
					 * Autor: Mariana Victor. Data: 02/12/2010
					 */
				case Funcionalidade.ENCERRAR_ORDENS_SERVICO_ACAO_COBRANCA:
					
					TarefaBatchProcessarEncerramentoOSAcaoCobranca tarefaBatchProcessarEncerramentoOSAcaoCobranca = new TarefaBatchProcessarEncerramentoOSAcaoCobranca(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					Collection<Integer> colecaoOrdemServicoParaEncerrar =  getControladorCobranca()
									.pesquisarOrdemServicoParaEncerrar(cobrancaAcaoAtividadeCronograma
											.getCobrancaAcaoCronograma().getId());

					if (colecaoOrdemServicoParaEncerrar != null
							&& !colecaoOrdemServicoParaEncerrar
									.isEmpty()) {
							
						tarefaBatchProcessarEncerramentoOSAcaoCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoOrdemServicoParaEncerrar);

						tarefaBatchProcessarEncerramentoOSAcaoCobranca.addParametro("idCobrancaAcaoAtividadeCronograma", cobrancaAcaoAtividadeCronograma.getId());
						
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchProcessarEncerramentoOSAcaoCobranca));

						getControladorUtil().atualizar(
								funcionalidadeIniciada);
					} else {
						throw new ControladorException(
								"atencao.nao.existe.dados.tabela.cronograma");
					}

					break;
					
					
				}
			}
		}
		return codigoProcessoIniciadoGerado;
	}

	private Integer inserirProcessoCobrancaAtividadeEventual(Usuario usuario,
			Integer codigoProcessoIniciadoGerado,
			Iterator<Integer> iteratorAtividade) throws ControladorException,
			ParseException, IOException {

		try {

			// Este trecho insere um processoIniciado e as
			// funcionalidadesIniciadas para cada
			// CobrancaAcaoAtividadeComando
			// informado pelo usuario
			while (iteratorAtividade.hasNext()) {

				Integer codigoCobrancaAcaoAtividadeComando = iteratorAtividade
						.next();

				// [FS0001] - Verificar existancia de dados
				// if (resultado == null || resultado.isEmpty()) {
				// throw new ControladorException("");
				//
				// }

				// Busca o CobrancaAcaoAtividadeComando para obter os dados
				// para montar o processoIniciado
				FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
				filtroCobrancaAcaoAtividadeComando
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaAcaoAtividadeComando.ID,
								codigoCobrancaAcaoAtividadeComando));

				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade.processo");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessora");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.documentoTipo");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("superior");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
				filtroCobrancaAcaoAtividadeComando
						.adicionarCaminhoParaCarregamentoEntidade("logradouro");
				filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessoraAlternativa");
				filtroCobrancaAcaoAtividadeComando
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao.cobrancaAcaoPredecessoraAlternativa.documentoTipo");


				CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) Util
						.retonarObjetoDeColecao(getControladorUtil().pesquisar(
								filtroCobrancaAcaoAtividadeComando,
								CobrancaAcaoAtividadeComando.class.getName()));

				// Constrai um processoIniciado para cada
				// FaturamentoAtividadeCronograma
				ProcessoIniciado processoIniciado = new ProcessoIniciado();
				processoIniciado.setUsuario(usuario);

				if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null
						&& !cobrancaAcaoAtividadeComando.getCobrancaGrupo()
								.equals("")) {
					processoIniciado
							.setCodigoGrupoProcesso(cobrancaAcaoAtividadeComando
									.getCobrancaGrupo().getId());
				}
				ProcessoSituacao processoSituacao = new ProcessoSituacao();
				
				Integer processoSituacaoId = this.verificarAutorizacaoBatch(cobrancaAcaoAtividadeComando
						.getCobrancaAtividade().getProcesso().getId());
				processoSituacao.setId(processoSituacaoId); // Ver isso
				processoIniciado.setProcessoSituacao(processoSituacao);

				processoIniciado.setProcesso(cobrancaAcaoAtividadeComando
						.getCobrancaAtividade().getProcesso());
				processoIniciado.setDataHoraAgendamento(new Date());
				processoIniciado.setDataHoraInicio(new Date());
				processoIniciado
						.setDataHoraComando(cobrancaAcaoAtividadeComando
								.getComando());

				codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
						.inserir(processoIniciado);

				processoIniciado.setId(codigoProcessoIniciadoGerado);

				// adicionado por Vivianne Sousa - 08/04/2010
				// Inserir CobrancaDocumentoControleGeracao
				CobrancaDocumentoControleGeracao cobrancaDocumentoControleGeracao = new CobrancaDocumentoControleGeracao(
						0, 0, new BigDecimal("0.00"), new Date(),
						processoIniciado, null, cobrancaAcaoAtividadeComando);

				Integer idCobrancaDocumentoControleGeracao = (Integer) getControladorUtil()
						.inserir(cobrancaDocumentoControleGeracao);

				// Este trecho pesquisa todos do processoFuncionalidade
				// relacionados com o processo do objeto a ser inserido
				FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

				filtroProcessoFuncionalidade
						.adicionarParametro(new ParametroSimples(
								FiltroProcessoFuncionalidade.ID_PROCESSO,
								processoIniciado.getProcesso().getId()));

				filtroProcessoFuncionalidade
						.adicionarParametro(new ParametroSimples(
								FiltroProcessoFuncionalidade.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection processosFuncionaliadade = getControladorUtil()
						.pesquisar(filtroProcessoFuncionalidade,
								ProcessoFuncionalidade.class.getName());
				Date dataAtual = new Date();

				Iterator iterator = processosFuncionaliadade.iterator();
				while (iterator.hasNext()) {
					ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
							.next();
					FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

					FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
					funcionalidadeSituacao
							.setId(FuncionalidadeSituacao.EM_ESPERA);
					funcionalidadeIniciada
							.setFuncionalidadeSituacao(funcionalidadeSituacao);

					funcionalidadeIniciada
							.setProcessoIniciado(processoIniciado);

					funcionalidadeIniciada
							.setProcessoFuncionalidade(processoFuncionalidade);

					funcionalidadeIniciada.setId((Integer) getControladorUtil()
							.inserir(funcionalidadeIniciada));

					// Seta os parametros da funcionalidadeIniciada

					// Seleciona as rotas

					Collection<Rota> colecaoRotas = new ArrayList();
					if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null) {

						FiltroRota filtroRota = new FiltroRota();
						filtroRota.adicionarParametro(new ParametroSimples(
								FiltroRota.COBRANCA_GRUPO_ID,
								cobrancaAcaoAtividadeComando.getCobrancaGrupo()
										.getId()));
						colecaoRotas = getControladorUtil().pesquisar(
								filtroRota, Rota.class.getName());

					} else {

						try {
							colecaoRotas = repositorioBatch
									.pesquisarRotasProcessamentoBatchCobrancaGrupoNaoInformado(cobrancaAcaoAtividadeComando
											.getId());
						} catch (ErroRepositorioException e) {
							e.printStackTrace();
						}

					}

					switch (funcionalidadeIniciada.getProcessoFuncionalidade()
							.getFuncionalidade().getId()) {
					case Funcionalidade.GERAR_ATIVIDADE_ACAO_COBRANCA:
						TarefaBatchGerarAtividadeAcaoCobranca acaoCobranca = new TarefaBatchGerarAtividadeAcaoCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// ******************************************************
						// Autor: Ivan Sergio
						// Data: 01/09/2009
						// CRC2658
						// Caso o Cliente Superior seja informado nao deve
						// verificar as rotas.
						// Caso o Logradouro seja informado nao deve
						// verificar as rotas.
						// ******************************************************
						if (cobrancaAcaoAtividadeComando.getLogradouro() == null) {
							if (cobrancaAcaoAtividadeComando.getSuperior() == null
									&& cobrancaAcaoAtividadeComando
											.getCliente() == null) {
								if (Util.isVazioOrNulo(colecaoRotas)) {
									throw new ControladorException(
											"atencao.comando.nao.existe.rotas");
								}
							}
						}

						acaoCobranca.addParametro("comandoAtividadeAcaoComando", cobrancaAcaoAtividadeComando);
						acaoCobranca.addParametro("colecaoRotas", colecaoRotas);

						if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
							acaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,colecaoRotas);
						} else {
							acaoCobranca.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH, Collections.singletonList(cobrancaAcaoAtividadeComando.getId()));
						}
						acaoCobranca.addParametro("acaoCobranca",cobrancaAcaoAtividadeComando.getCobrancaAcao());
						acaoCobranca.addParametro("atividadeCobranca",cobrancaAcaoAtividadeComando.getCobrancaAtividade());
						acaoCobranca.addParametro("indicadorCriterio",cobrancaAcaoAtividadeComando.getIndicadorCriterio());
						acaoCobranca.addParametro("criterioCobranca",cobrancaAcaoAtividadeComando .getCobrancaCriterio());
						acaoCobranca.addParametro("cliente", cobrancaAcaoAtividadeComando.getCliente());
						acaoCobranca.addParametro("clienteSuperior", cobrancaAcaoAtividadeComando.getSuperior());
						acaoCobranca.addParametro("clienteRelacaoTipo",cobrancaAcaoAtividadeComando .getClienteRelacaoTipo());
						acaoCobranca.addParametro("anoMesReferenciaInicial",cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial());
						acaoCobranca.addParametro("anoMesReferenciaFinal", cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal());
						acaoCobranca.addParametro("dataVencimentoInicial", cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial());
						acaoCobranca.addParametro("dataVencimentoFinal",cobrancaAcaoAtividadeComando .getDataVencimentoContaFinal());
						acaoCobranca.addParametro("dataAtual", dataAtual);
						acaoCobranca.addParametro("idCobrancaDocumentoControleGeracao", idCobrancaDocumentoControleGeracao);

						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(acaoCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_BOLETIM_CADASTRO:
						TarefaBatchEmitirBoletimCadastro tarefaBatchEmitirBoletimCadastro = new TarefaBatchEmitirBoletimCadastro(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Seta os parametros para rodar a funcionalidade

						tarefaBatchEmitirBoletimCadastro.addParametro(
								"comandoAtividadeAcaoComando",
								cobrancaAcaoAtividadeComando);

						tarefaBatchEmitirBoletimCadastro.addParametro(
								"acaoCobranca", cobrancaAcaoAtividadeComando
										.getCobrancaAcao());

						tarefaBatchEmitirBoletimCadastro.addParametro(
								"atividadeCobranca",
								cobrancaAcaoAtividadeComando
										.getCobrancaAtividade());

						tarefaBatchEmitirBoletimCadastro.addParametro(
								"dataAtual", dataAtual);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchEmitirBoletimCadastro));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA:
						TarefaBatchAtualizarComandoAtividadeAcaoCobranca atualizarComandoAtividadeAcaoCobranca = new TarefaBatchAtualizarComandoAtividadeAcaoCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// ******************************************************
						// Seta os parametros para rodar a funcionalidade

						atualizarComandoAtividadeAcaoCobranca.addParametro(
								"anoMesReferenciaCicloCobranca",
								getControladorUtil()
										.pesquisarParametrosDoSistema()
										.getAnoMesFaturamento());

						atualizarComandoAtividadeAcaoCobranca.addParametro(
								"comandoAtividadeAcaoComando",
								cobrancaAcaoAtividadeComando);

						atualizarComandoAtividadeAcaoCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										Collections
												.singletonList(cobrancaAcaoAtividadeComando
														.getId()));

						atualizarComandoAtividadeAcaoCobranca.addParametro(
								"acaoCobranca", cobrancaAcaoAtividadeComando
										.getCobrancaAcao());

						atualizarComandoAtividadeAcaoCobranca.addParametro(
								"indicadorCriterio",
								cobrancaAcaoAtividadeComando
										.getIndicadorCriterio());

						atualizarComandoAtividadeAcaoCobranca.addParametro(
								"criterioCobranca",
								cobrancaAcaoAtividadeComando
										.getCobrancaCriterio());

						atualizarComandoAtividadeAcaoCobranca.addParametro(
								"idCobrancaDocumentoControleGeracao",
								idCobrancaDocumentoControleGeracao);
						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(atualizarComandoAtividadeAcaoCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.EMITIR_DOCUMENTO_COBRANCA:
						TarefaBatchEmitirDocumentoCobranca emitirDocumentoCobranca = new TarefaBatchEmitirDocumentoCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// ******************************************************
						// Seta os parametros para rodar a funcionalidade

						emitirDocumentoCobranca.addParametro(
								"comandoAtividadeAcaoComando",
								cobrancaAcaoAtividadeComando);

						emitirDocumentoCobranca
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										Collections
												.singletonList(cobrancaAcaoAtividadeComando
														.getId()));

						emitirDocumentoCobranca.addParametro("acaoCobranca",
								cobrancaAcaoAtividadeComando.getCobrancaAcao());

						emitirDocumentoCobranca.addParametro(
								"criterioCobranca",
								cobrancaAcaoAtividadeComando
										.getCobrancaCriterio());

						emitirDocumentoCobranca.addParametro("dataAtual",
								dataAtual);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(emitirDocumentoCobranca));

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

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas a
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Verifica no sistema a presenca de ProcessosIniciados nao agendados para
	 * iniciar a execucao
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public void verificarProcessosIniciados() throws ControladorException {

		try {

			// Procurar as funcionalidadesIniciadas de processosIniciados em
			// Situacao de Inicio - Agendado ou em espera
			// ---------------------------------------------------------------
			// Sa vai procurar as funcionalidades Iniciadas para iniciar
			// execuaao em que o processo Iniciado nao tem precedente ou que o
			// precedente tenha sido finalizado

			Collection<FuncionalidadeIniciada> colecaoFuncionalidadesIniciadasParaExecucao = verificarFuncionalidadesIniciadasProntasParaExecucao();

			Iterator<FuncionalidadeIniciada> iterator = colecaoFuncionalidadesIniciadasParaExecucao
					.iterator();

			while (iterator.hasNext()) {
				// Recuperar a tarefa batch armazenada
				FuncionalidadeIniciada funcionalidadeIniciada = iterator.next();
				TarefaBatch tarefaBatch = (TarefaBatch) IoUtil
						.transformarBytesParaObjeto(funcionalidadeIniciada
								.getTarefaBatch());

				// Atualizar a FuncionalidadeSituacao para EM_PROCESSAMENTO
				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao
						.setId(FuncionalidadeSituacao.EM_PROCESSAMENTO);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setDataHoraInicio(new Date());

				// Atualiza o Processo Iniciado para EM_PROCESSAMENTO
				ProcessoIniciado processoIniciado = funcionalidadeIniciada
						.getProcessoIniciado();
				if (!processoIniciado.getProcessoSituacao().getId().equals(
						ProcessoSituacao.EM_PROCESSAMENTO)) {
					ProcessoSituacao processoSituacao = new ProcessoSituacao();
					processoSituacao.setId(ProcessoSituacao.EM_PROCESSAMENTO);
					processoIniciado.setProcessoSituacao(processoSituacao);
					getControladorUtil().atualizar(processoIniciado);
				}

				getControladorUtil().atualizar(funcionalidadeIniciada);

				// Agendar a tarefa Batch
				if (tarefaBatch != null) {
					tarefaBatch.agendarTarefaBatch();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();

			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}

	}

	/**
	 * Encerra os Processos Iniciados no sistema quando todas as funcionalidades
	 * do mesmo finalizarem a execuaao
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public void encerrarProcessosIniciados() throws ControladorException {
		try {

			// Busca todos os Processos Iniciados que podem ser concluados -
			// eles possuem apenas FuncionalidadesIniciadas no estado CONCLUIDO
			Collection<ProcessoIniciado> colecaoProcessosIniciadosParaEncerramento = this.repositorioBatch
					.pesquisarProcessosIniciadosProntosParaEncerramento();
			Iterator<ProcessoIniciado> iterator = colecaoProcessosIniciadosParaEncerramento
					.iterator();

			while (iterator.hasNext()) {
				ProcessoIniciado processoIniciado = iterator.next();
				processoIniciado.setDataHoraTermino(new Date());

				ProcessoSituacao situacao = new ProcessoSituacao();
				situacao.setId(ProcessoSituacao.CONCLUIDO);
				processoIniciado.setProcessoSituacao(situacao);
				getControladorUtil().atualizar(processoIniciado);

			}

			// Busca todos os Processos Iniciados que podem ser concluados
			// indicando erro -
			// eles possuem apenas FuncionalidadesIniciadas no estado CONCLUIDO
			// e pelo menos uma no estado CONCLUIDO_COM_ERRO
			Collection<ProcessoIniciado> colecaoProcessosIniciadosFalha = this.repositorioBatch
					.pesquisarProcessosIniciadosExecucaoFalha();
			Iterator<ProcessoIniciado> iteratorFalha = colecaoProcessosIniciadosFalha
					.iterator();

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

	/**
	 * Encerra as Funcionalidades Iniciadas no sistema quando todas as unidades
	 * de processamento do mesmo finalizarem a execuaao
	 * 
	 * @author Rodrigo Silveira, Tiago Moreno
	 * @date 22/08/2006, 13/08/2010
	 * 
	 */
	public void encerrarFuncionalidadesIniciadas() throws ControladorException {
		try {

			// Pesquisa no sistema para ver se existe alguma unidadeIniciada que
			// foi concluida com falha para
			// marcar a funcionalidadeIniciada como 'concluida com erro'
			Collection<FuncionalidadeIniciada> colecaoFuncionaldadesIniciadasFalha = this.repositorioBatch
					.pesquisarFuncionaldadesIniciadasExecucaoFalha();

			if (colecaoFuncionaldadesIniciadasFalha != null
					&& !colecaoFuncionaldadesIniciadasFalha.isEmpty()) {

				Iterator<FuncionalidadeIniciada> iterator = colecaoFuncionaldadesIniciadasFalha
						.iterator();

				while (iterator.hasNext()) {
					FuncionalidadeIniciada funcionalidadeIniciadaPesquisa = iterator.next();
					
					FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
					
					filtroFuncionalidadeIniciada.adicionarParametro(
							new ParametroSimples(FiltroFuncionalidadeIniciada.ID, funcionalidadeIniciadaPesquisa.getId()));

					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");
					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoIniciado");
					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade");
					filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");
					
					Collection<FuncionalidadeIniciada> colFuin = 
						getControladorUtil().pesquisar(filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class.getName());
					
					FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colFuin);
					
					funcionalidadeIniciada.setDataHoraTermino(new Date());

					FuncionalidadeSituacao situacao = new FuncionalidadeSituacao();
					situacao.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
					funcionalidadeIniciada.setFuncionalidadeSituacao(situacao);
					getControladorUtil().atualizar(funcionalidadeIniciada);

				}

			} else {
				Collection<FuncionalidadeIniciada> colecaoFuncionaldadesIniciadasParaEncerramento = new ArrayList();
				colecaoFuncionaldadesIniciadasParaEncerramento = this.repositorioBatch
						.pesquisarFuncionaldadesIniciadasProntasParaEncerramento();
				
				if (colecaoFuncionaldadesIniciadasParaEncerramento != null){
					Iterator iterator = colecaoFuncionaldadesIniciadasParaEncerramento
							.iterator();
					
					while (iterator.hasNext()) {
						FuncionalidadeIniciada funcionalidadeIniciadaPesquisa = (FuncionalidadeIniciada) iterator.next();
						
						FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
						
						filtroFuncionalidadeIniciada.adicionarParametro(
								new ParametroSimples(FiltroFuncionalidadeIniciada.ID, funcionalidadeIniciadaPesquisa.getId()));
						
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoIniciado");
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade");
						filtroFuncionalidadeIniciada.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");
						
						Collection<FuncionalidadeIniciada> colFuin = 
							getControladorUtil().pesquisar(filtroFuncionalidadeIniciada, FuncionalidadeIniciada.class.getName());
						
						FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util.retonarObjetoDeColecao(colFuin);
						
						funcionalidadeIniciada.setDataHoraTermino(new Date());
	
						FuncionalidadeSituacao situacao = new FuncionalidadeSituacao();
						situacao.setId(FuncionalidadeSituacao.CONCLUIDA);
						funcionalidadeIniciada.setFuncionalidadeSituacao(situacao);
	
						// Chama o metodo para atualizar a situacao sem transacao
						// para nao
						// ocorrer problemas ao chamar o finalizador caso a
						// finalizacao demore
						// mais de um minuto
						this
								.atualizarSituacaoFuncionalidadeIniciadaConcluida(funcionalidadeIniciada);
	
						// Finaliza com alguma lagica de negacio depois que todas as
						// UnidadesIniciadas terminarem de executar
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
	public int iniciarUnidadeProcessamentoBatch(int idFuncionalidadeIniciada,
			int idUnidadeProcessamento, int codigoRealUnidadeProcessamento)
			throws ControladorException {

		int retorno = 0;

		// Verifica se a funcionalidadeIniciada existe para ser registrada
		// Caso não exista, pode ser usada como teste do processo batch
		if (idFuncionalidadeIniciada > 0) {

			try {

				int funcionalidadesIniciadasComErro = repositorioBatch
						.pesquisarFuncionaldadesIniciadasConcluidasErro(idFuncionalidadeIniciada);

				if (funcionalidadesIniciadasComErro > 0) {
					throw new ControladorException(
							"Esta Unidade não será executada porque o processo está concluído com erro");
				}
				FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
				filtroFuncionalidadeIniciada
						.adicionarParametro(new ParametroSimples(
								FiltroFuncionalidadeIniciada.ID,
								idFuncionalidadeIniciada));

				Collection colecaoFuncionalidadesIniciadas = getControladorUtil()
						.pesquisar(filtroFuncionalidadeIniciada,
								FuncionalidadeIniciada.class.getName());

				FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadesIniciadas);

				try {
					Object tarefa = IoUtil
							.transformarBytesParaObjeto(funcionalidadeIniciada
									.getTarefaBatch());
					Collection unidadesJaExecutadas = null;
					if (tarefa instanceof TarefaBatch) {

						TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

						unidadesJaExecutadas = tarefaBatch
								.getUnidadesJaExecutadas();

						UnidadeIniciada unidadeIniciada = new UnidadeIniciada();

						unidadeIniciada
								.setFuncionalidadeIniciada(funcionalidadeIniciada);

						UnidadeProcessamento unidadeProcessamento = new UnidadeProcessamento();
						unidadeProcessamento.setId(idUnidadeProcessamento);

						unidadeIniciada
								.setUnidadeProcessamento(unidadeProcessamento);
						unidadeIniciada
								.setCodigoRealUnidadeProcessamento(codigoRealUnidadeProcessamento);

						UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
						unidadeSituacao.setId(UnidadeSituacao.EM_PROCESSAMENTO);
						unidadeIniciada.setUnidadeSituacao(unidadeSituacao);

						unidadeIniciada.setDataHoraInicio(new Date());

						retorno = (Integer) getControladorUtil().inserir(
								unidadeIniciada);

						// Só vai processar as unidades que não foram
						// processadas, as que deram algum erro e as que ficaram
						// incompletas
						if (unidadesJaExecutadas != null
								&& !unidadesJaExecutadas.isEmpty()) {

							Iterator iterator = unidadesJaExecutadas.iterator();

							while (iterator.hasNext()) {
								UnidadeIniciada unidadeIniciadaLista = (UnidadeIniciada) iterator
										.next();

								// Verificar se
								if ((unidadeIniciadaLista
										.getCodigoRealUnidadeProcessamento() == codigoRealUnidadeProcessamento)
										&& (unidadeIniciadaLista
												.getUnidadeSituacao().getId() == UnidadeSituacao.CONCLUIDA)) {

									// Caso a unidade já tenha sido previamente
									// executada, ela será registrada como
									// concluída e com data de
									// início e fim iguais
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

	private void encerrarUnidadeIniciadaJaExecutada(
			UnidadeIniciada unidadeIniciada) throws ControladorException {

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
	 * @author Rodrigo Silveira
	 * @date 24/08/2006
	 * 
	 * @param excecao
	 * @param idUnidadeIniciada
	 * @param executouComErro
	 */

	public void encerrarUnidadeProcessamentoBatch(Throwable excecao,
			int idUnidadeIniciada, boolean executouComErro)
			throws ControladorException {

		if (idUnidadeIniciada != 0) {

			FiltroUnidadeIniciada filtroUnidadeIniciada = new FiltroUnidadeIniciada();
			filtroUnidadeIniciada.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidadeIniciada.ID, idUnidadeIniciada));

			UnidadeIniciada unidadeIniciada = (UnidadeIniciada) Util
					.retonarObjetoDeColecao(getControladorUtil().pesquisar(
							filtroUnidadeIniciada,
							UnidadeIniciada.class.getName()));

			unidadeIniciada.setDataHoraTermino(new Date());

			// verifica se a unidade encerrou com sucesso ou se nao foi
			// executada por causa de uma continuação no processo
			if ((!executouComErro)
					|| (excecao != null && excecao.getMessage() != null && excecao
							.getMessage().equals("Unidade já executada"))) {

				UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
				unidadeSituacao.setId(UnidadeSituacao.CONCLUIDA);
				unidadeIniciada.setUnidadeSituacao(unidadeSituacao);

			} else {

				UnidadeSituacao unidadeSituacao = new UnidadeSituacao();
				unidadeSituacao.setId(UnidadeSituacao.CONCLUIDA_COM_ERRO);
				unidadeIniciada.setUnidadeSituacao(unidadeSituacao);

				inserirLogExcecaoFuncionalidadeIniciada(unidadeIniciada,
						excecao);

			}

			getControladorUtil().atualizar(unidadeIniciada);

		} else {

			System.out.println("ATENCAO --- UNIDADE INICIADA POSSUI ID : !"
					+ idUnidadeIniciada + "!");

		}

	}

	/**
	 * Insere uma coleaao de objetos genaricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void inserirColecaoObjetoParaBatch(
			Collection<? extends Object> colecaoObjetos)
			throws ControladorException {

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
	 * Insere uma coleaao de objetos genaricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Sávio Luiz
	 * @date 31/03/2010
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void inserirColecaoObjetoParaBatchSemTransacao(
			Collection<? extends Object> colecaoObjetos)
			throws ControladorException {

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
	 * @author Rafael Pinto
	 * @date 20/05/2008
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchTransacao(
			Collection<Object> colecaoObjetos) throws ControladorException {

		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch
						.inserirColecaoObjetoParaBatchTransacao(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Insere uma coleaao de objetos genaricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void inserirColecaoObjetoParaBatchGerencial(
			Collection<? extends Object> colecaoObjetos)
			throws ControladorException {

		try {
			repositorioBatch
					.inserirColecaoObjetoParaBatchGerencial(colecaoObjetos);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Atualiza uma coleaao de objetos genaricos na base com um flush para cada
	 * 50 registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void atualizarColecaoObjetoParaBatch(
			Collection<? extends Object> colecaoObjetos)
			throws ControladorException {

		try {
			repositorioBatch.atualizarColecaoObjetoParaBatch(colecaoObjetos);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Atualiza uma coleaao de objetos genaricos na base com um flush para cada
	 * 50 registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void atualizarColecaoObjetoParaBatchSemTransacao(
			Collection<? extends Object> colecaoObjetos)
			throws ControladorException {

		try {
			repositorioBatch.atualizarColecaoObjetoParaBatch(colecaoObjetos);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Funaao que executa as rotinas de execuaao e fechamento das tarefas batch
	 * do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 11/09/2006
	 * 
	 */
	public void verificadorProcessosSistema() throws ControladorException {
		verificarProcessosIniciados();
		iniciarRelatoriosAgendados();
		encerrarFuncionalidadesIniciadas();
		encerrarProcessosIniciados();

	}

	/**
	 * Funcao que executa as rotinas de execucao da integração da SAM
	 * 
	 * @author Rodrigo Silveira
	 * @date 27/02/2008
	 * 
	 */
	public void verificadorProcessosIntegracaoUPA() throws ControladorException {

		// getControladorIntegracao().enviarMovimentoExportacaoFirma();
		getControladorIntegracao().receberMovimentoExportacaoFirma();
	}

	/**
	 * Funcao que executa as rotinas de execucao da integração da SAM
	 * 
	 * @author Rodrigo Silveira
	 * @date 27/02/2008
	 * 
	 */
	public void verificadorQueriesDemoradasSistema()
			throws ControladorException {
		try {
			repositorioBatch.pesquisarQueriesDemoradasSistema();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema");
		}

	}

	/**
	 * 
	 * 
	 * @author Rodrigo Silveira
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarRelatoriosAgendados() throws ControladorException {
		// Consulta todas as configuraaaes de relatario gravadas na base dentro
		// das funcionalidades iniciadas com status de AGENDADA

		try {
			Iterator<byte[]> iterator = repositorioBatch
					.iniciarRelatoriosAgendados().iterator();

			while (iterator.hasNext()) {
				TarefaRelatorio tarefaRelatorio = (TarefaRelatorio) IoUtil
						.transformarBytesParaObjeto(iterator.next());
				tarefaRelatorio.agendarTarefaBatch();

			}
		} catch (ErroRepositorioException e) {
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		} catch (IOException e) {
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		} catch (ClassNotFoundException e) {
			// sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}

	}

	/**
	 * Retorna todas as Funcionalidades Iniciadas que estao em situacaoo de
	 * iniciar o processamento
	 * 
	 * @author Rodrigo Silveira
	 * @throws ControladorException
	 * @date 11/09/2006
	 * 
	 */
	public Collection<FuncionalidadeIniciada> verificarFuncionalidadesIniciadasProntasParaExecucao()
			throws ControladorException {

		Collection<FuncionalidadeIniciada> retorno = new ArrayList();

		try {
			// Pesquisa todas as funcionalidades Iniciadas prontas para execuaao
			// independente do sequencialExecucao

			Collection<Object[]> funcionalidadesIniciadasExecucao = repositorioBatch
					.pesquisarFuncionaldadesIniciadasProntasExecucao();

			// Para cada Funcionalidade Iniciada, verificar se todas as
			// funcionalidades iniciadas com sequencialExecucao anterior do
			// mesmo processo estao
			// concluadas
			for (Object[] objects : funcionalidadesIniciadasExecucao) {
				FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) objects[0];

				int quantidadeFuncionalidadesForaOrdem = repositorioBatch
						.pesquisarQuantidadeFuncionaldadesIniciadasForaOrdemExecucao(
								(int) ((Short) objects[1]),
								funcionalidadeIniciada.getProcessoIniciado()
										.getId());
				if (quantidadeFuncionalidadesForaOrdem == 0
						&& (!funcionalidadeIniciada.getFuncionalidadeSituacao()
								.getId().equals(
										FuncionalidadeSituacao.CONCLUIDA))) {
					// Se todas estiverem concluadas significa que a a vez de
					// executar esta funcionalidade iniciada
					retorno.add(funcionalidadeIniciada);
					// A praxima funcionalidade Iniciada vai ficar para a
					// praxima verificaaao
					break;

				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}
		return retorno;
	}

	/**
	 * Inicia uma funcionalidade iniciada do relatorio
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void iniciarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada) throws ControladorException {

		try {

			repositorioBatch
					.iniciarFuncionalidadeIniciadaRelatorio(idFuncionalidadeIniciada);
			repositorioBatch
					.iniciarProcessoIniciadoRelatorio(idFuncionalidadeIniciada);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);

		}
	}

	/**
	 * Encerra uma funcionalidade iniciada do relatorio
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void encerrarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada, boolean concluiuComErro)
			throws ControladorException {

		try {
			if (concluiuComErro) {

				repositorioBatch.encerrarFuncionalidadeIniciadaRelatorio(
						idFuncionalidadeIniciada,
						FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
				repositorioBatch.encerrarProcessoIniciadoRelatorio(
						idFuncionalidadeIniciada,
						ProcessoSituacao.CONCLUIDO_COM_ERRO);
			} else {

				repositorioBatch.encerrarFuncionalidadeIniciadaRelatorio(
						idFuncionalidadeIniciada,
						FuncionalidadeSituacao.CONCLUIDA);
				repositorioBatch.encerrarProcessoIniciadoRelatorio(
						idFuncionalidadeIniciada, ProcessoSituacao.CONCLUIDO);

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatarios
	 * batch do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 09/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchSistema()
			throws ControladorException {

		try {
			return repositorioBatch.pesquisarRelatoriosBatchSistema();
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Remove uma coleaao de objetos genaricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos)
			throws ControladorException {

		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch.removerColecaoObjetoParaBatch(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleaao de objetos genaricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ControladorException
	 */
	public void removerColecaoObjetoParaBatchSemTransacao(
			Collection colecaoObjetos) throws ControladorException {

		try {
			if (colecaoObjetos != null && !colecaoObjetos.isEmpty()) {
				repositorioBatch.removerColecaoObjetoParaBatch(colecaoObjetos);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Sávio Luiz
	 * @date 31/03/2010
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerObjetoParaBatchSemTransacao(Object objeto)
			throws ControladorException {

		try {

			repositorioBatch.removerObjetoParaBatch(objeto);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Inicia um processo relacionado com um relatoio que seria processado em
	 * batch
	 * 
	 * @author Rodrigo Silveira
	 * @date 23/10/2006
	 * 
	 * @throws ControladorException
	 */

	public void iniciarProcessoRelatorio(TarefaRelatorio tarefaRelatorio)
			throws ControladorException {
		try {

			// Constroi um processoIniciado para o Relatorio
			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(tarefaRelatorio.getUsuario());

			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			processoSituacao.setId(ProcessoSituacao.AGENDADO);
			processoIniciado.setProcessoSituacao(processoSituacao);

			Processo processo = new Processo();
			processo
					.setId(GerenciadorExecucaoTarefaRelatorio
							.obterProcessoRelatorio(tarefaRelatorio
									.getNomeRelatorio()));

			processoIniciado.setProcesso(processo);
			processoIniciado.setDataHoraAgendamento(new Date());

			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			getControladorUtil().inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {

				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();

				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

				// AGENDADA NAO ENTRA EM EXECUCAO
				// SOH EXECUTA QUANDO ENTRA EM_ESPERA
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.AGENDADA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setDataHoraInicio(new Date());
				// funcionalidadeIniciada.setDataHoraTermino(new Date());

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				// Seta os parametros da funcionalidadeIniciada
				tarefaRelatorio
						.setIdFuncionalidadeIniciada(funcionalidadeIniciada
								.getId());

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread
				funcionalidadeIniciada.setTarefaBatch(IoUtil
						.transformarObjetoParaBytes(tarefaRelatorio));

				getControladorUtil().atualizar(funcionalidadeIniciada);

			}
		} catch (IOException e) {
			throw new SistemaException(e, "Erro Batch Relatario");
		}

	}

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatarios
	 * batch do sistema por Usuario
	 * 
	 * @author Rodrigo Silveira
	 * @date 25/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(
			int idProcesso) throws ControladorException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoCompletoData = new SimpleDateFormat(
				"dd/MM/yyyy k:mm:ss");
		try {

			Collection<Object[]> retornoMetodo = repositorioBatch
					.pesquisarRelatoriosBatchPorUsuarioSistema(idProcesso);

			for (Object[] array : retornoMetodo) {

				// Adiciona 3 dias na data do relatorio para indicar o dia
				// maximo de disponibilidade
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
	 * Remove do sistema todos os relatarios batch que estao na data de
	 * expiraaao
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/10/2006
	 * 
	 */
	public void deletarRelatoriosBatchDataExpiracao()
			throws ControladorException {

		try {
			Calendar dataExpiracao = GregorianCalendar.getInstance();
			dataExpiracao.set(Calendar.SECOND, 59);
			dataExpiracao.set(Calendar.MINUTE, 59);
			dataExpiracao.set(Calendar.HOUR_OF_DAY, 23);
			dataExpiracao.add(Calendar.DAY_OF_MONTH, -3);

			this.repositorioBatch
					.deletarRelatoriosBatchDataExpiracao(dataExpiracao
							.getTime());

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas a
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas a
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas a
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * Retorna o controladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	


	/**
	 * Pesquisa no sistema todos os processos que pararam na metade devido a uma
	 * falha no servidor e marca com 'EXECUaaO INTERROMPIDA'
	 * 
	 * @author Rodrigo Silveira
	 * @date 27/01/2007
	 * 
	 */
	public void marcarProcessosInterrompidos() throws ControladorException {
		FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
		filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
				FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
				ProcessoSituacao.EM_PROCESSAMENTO));

		Collection processosIniciadosParaMarcacao = getControladorUtil()
				.pesquisar(filtroProcessoIniciado,
						ProcessoIniciado.class.getName());

		Iterator iteratorProcessos = processosIniciadosParaMarcacao.iterator();

		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoSituacao.setId(ProcessoSituacao.EXECUCAO_CANCELADA);
		while (iteratorProcessos.hasNext()) {
			ProcessoIniciado processoIniciado = (ProcessoIniciado) iteratorProcessos
					.next();

			processoIniciado.setProcessoSituacao(processoSituacao);

			getControladorUtil().atualizar(processoIniciado);

		}

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,
				FuncionalidadeSituacao.EM_PROCESSAMENTO));

		Collection funcionalidadesIniciadasParaMarcacao = getControladorUtil()
				.pesquisar(filtroFuncionalidadeIniciada,
						FuncionalidadeIniciada.class.getName());

		Iterator iteratorFuncionalidades = funcionalidadesIniciadasParaMarcacao
				.iterator();

		FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
		funcionalidadeSituacao.setId(FuncionalidadeSituacao.EXECUCAO_CANCELADA);
		while (iteratorFuncionalidades.hasNext()) {
			FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) iteratorFuncionalidades
					.next();

			funcionalidadeIniciada
					.setFuncionalidadeSituacao(funcionalidadeSituacao);

			getControladorUtil().atualizar(funcionalidadeIniciada);

		}

	}

	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna a interface remota de ControladorParametro
	 * 
	 * @return A interface remota do controlador de parametro
	 */
	private ControladorLocalidadeLocal getControladorLocalidade() {
		ControladorLocalidadeLocalHome localHome = null;
		ControladorLocalidadeLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorLocalidadeLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorGerencialMicromedicaoLocal getControladorGerencialMicromedicao() {

		ControladorGerencialMicromedicaoLocalHome localHome = null;
		ControladorGerencialMicromedicaoLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialMicromedicaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Retorna o valor de controladorFinanceiro
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFinanceiroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Insere objeto genarico na base
	 * 
	 * @author Marcio Roberto
	 * @date 18/05/2007
	 * 
	 * @param Objeto
	 * @throws ControladorException
	 */
	public Object inserirObjetoParaBatchGerencial(Object objeto)
			throws ControladorException {

		try {
			repositorioBatch.inserirObjetoParaBatchGerencial(objeto);
			return objeto;
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * <Breve descriaao sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 18/06/2007
	 * 
	 * @param processoIniciado
	 * @param dadosProcessamento
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirProcessoIniciado(ProcessoIniciado processoIniciado,
			Map<String, Object> dadosProcessamento) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {

			// Todos os processo serao iniciados com a situaaao EM_ESPERA para q
			// sejam executados o mais cedo possavel
			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processoIniciado.getProcesso().getId());
			processoSituacao.setId(processoSituacaoId);
			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);
				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				// Seta os parametros da funcionalidadeIniciada

				// ----------------------------------------------------------
				// Lista dos possaveis processos eventuais ou mensais
				// ----------------------------------------------------------
				try {

					switch (funcionalidadeIniciada.getProcessoFuncionalidade()
							.getFuncionalidade().getId()) {
					
					/** Arthur Carvalho 26-10-2010 */
					case Funcionalidade.APAGAR_RESUMO_DEVEDORES_DUVIDOSOS:
							TarefaBatchApagarResumoDevedoresDuvidosos dadosApagarResumoDevedoresDuvidosos = new TarefaBatchApagarResumoDevedoresDuvidosos(
									processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							Integer anoMesReferenciaContabilApagar = new Integer(
									(String) dadosProcessamento
											.get("anoMesReferenciaContabil"));

							Collection<Integer> colecaoIdsLocalidadesApagarResumoDevedoresDuvidosos = getControladorFinanceiro()
									.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(
											anoMesReferenciaContabilApagar);

							dadosApagarResumoDevedoresDuvidosos.addParametro(
									"anoMesReferenciaContabil",
									anoMesReferenciaContabilApagar);

							// Seta os parametros para rodar a funcionalidade
							dadosApagarResumoDevedoresDuvidosos
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											colecaoIdsLocalidadesApagarResumoDevedoresDuvidosos);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(dadosApagarResumoDevedoresDuvidosos));

							getControladorUtil().atualizar(funcionalidadeIniciada);

							break;

					/** Pedro Alexandre 18-06-2007 */
					case Funcionalidade.GERAR_RESUMO_DEVEDORES_DUVIDOSOS:
						TarefaBatchGerarResumoDevedoresDuvidosos dadosGerarResumoDevedoresDuvidosos = new TarefaBatchGerarResumoDevedoresDuvidosos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Integer anoMesReferenciaContabil = new Integer(
								(String) dadosProcessamento
										.get("anoMesReferenciaContabil"));

						Collection<Integer> colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos = getControladorFinanceiro()
								.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(
										anoMesReferenciaContabil);

						dadosGerarResumoDevedoresDuvidosos.addParametro(
								"anoMesReferenciaContabil",
								anoMesReferenciaContabil);

						// Seta os parametros para rodar a funcionalidade
						dadosGerarResumoDevedoresDuvidosos
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarResumoDevedoresDuvidosos);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarResumoDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
						
						/** Arthur Carvalho 26-10-2010 */
					case Funcionalidade.ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS:
							TarefaBatchAtualizarResumoDevedoresDuvidosos dadosAtualizarResumoDevedoresDuvidosos = new TarefaBatchAtualizarResumoDevedoresDuvidosos(
									processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							Integer anoMesReferenciaContabilAtualiza = new Integer(
									(String) dadosProcessamento
											.get("anoMesReferenciaContabil"));

//							Collection<Integer> colecaoIdsLocalidadesAtualizarResumoDevedoresDuvidosos = getControladorFinanceiro()
//									.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(
//											anoMesReferenciaContabilAtualiza);

							dadosAtualizarResumoDevedoresDuvidosos.addParametro(
									"anoMesReferenciaContabil",
									anoMesReferenciaContabilAtualiza);

							// Seta os parametros para rodar a funcionalidade
//							dadosAtualizarResumoDevedoresDuvidosos
//									.addParametro(
//											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
//											colecaoIdsLocalidadesAtualizarResumoDevedoresDuvidosos);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada
									.setTarefaBatch(IoUtil
											.transformarObjetoParaBytes(dadosAtualizarResumoDevedoresDuvidosos));

							getControladorUtil().atualizar(funcionalidadeIniciada);

							break;
							
					/** Pedro Alexandre 25-06-2007 */
					case Funcionalidade.GERAR_LANCAMENTOS_CONTABEIS_DEVEDORES_DUVIDOSOS:
						TarefaBatchGerarLancamentosContabeisDevedoresDuvidosos dadosGerarLancamentosContabeisDevedoresDuvidosos = new TarefaBatchGerarLancamentosContabeisDevedoresDuvidosos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						Integer anoMesReferenciaContabilLancamentosContabeis = new Integer(
								(String) dadosProcessamento
										.get("anoMesReferenciaContabil"));

						Collection<Integer> colecaoIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos = getControladorFinanceiro()
								.pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(
										anoMesReferenciaContabilLancamentosContabeis);

						dadosGerarLancamentosContabeisDevedoresDuvidosos
								.addParametro("anoMesReferenciaContabil",
										anoMesReferenciaContabilLancamentosContabeis);

						// Seta os parametros para rodar a funcionalidade
						dadosGerarLancamentosContabeisDevedoresDuvidosos
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										colecaoIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosGerarLancamentosContabeisDevedoresDuvidosos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Pedro Alexandre 18-06-2007 */
					// case Funcionalidade.ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS:
					//						
					// TarefaBatchAtualizarResumoDevedoresDuvidosos
					// dadosAtualizarResumoDevedoresDuvidosos = new
					// TarefaBatchAtualizarResumoDevedoresDuvidosos(
					// processoIniciado.getUsuario(),
					// funcionalidadeIniciada.getId());
					//
					// Integer anoMesReferenciaCont = new Integer(
					// (String) dadosProcessamento
					// .get("anoMesReferenciaContabil"));
					//
					// Collection<Integer>
					// colecaoIdsQuadrasAtualizarResumoDevedoresDuvidosos =
					// getControladorFinanceiro()
					// .pesquisarIdsQuadrasGerarResumoDevedoresDuvidosos();
					//
					// dadosAtualizarResumoDevedoresDuvidosos.addParametro(
					// "anoMesReferenciaContabil",
					// anoMesReferenciaCont);
					//
					// // Seta os parametros para rodar a funcionalidade
					// dadosAtualizarResumoDevedoresDuvidosos
					// .addParametro(
					// ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
					// colecaoIdsQuadrasAtualizarResumoDevedoresDuvidosos);
					//
					// // Seta o objeto para ser serializado no banco, onde
					// // depois sera executado por uma thread
					// funcionalidadeIniciada
					// .setTarefaBatch(IoUtil
					// .transformarObjetoParaBytes(dadosAtualizarResumoDevedoresDuvidosos));
					//
					// getControladorUtil().atualizar(funcionalidadeIniciada);
					//
					// break;
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
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Rafael Corraa
	 * @date 06/10/2007
	 * 
	 * @param idsFuncionalidadesIniciadas
	 * @param idProcessoIniciado
	 * @return
	 * @throws ControladorException
	 */
	public void reiniciarFuncionalidadesIniciadas(
			String[] idsFuncionalidadesIniciadas, Integer idProcessoIniciado)
			throws ControladorException {

		int i = 0;

		while (i < idsFuncionalidadesIniciadas.length) {
			String idFuncionalidadeIniciada = idsFuncionalidadesIniciadas[i];

			try {
				repositorioBatch.removerUnidadesIniciadas(new Integer(
						idFuncionalidadeIniciada));
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
			filtroFuncionalidadeIniciada
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeIniciada.ID,
							idFuncionalidadeIniciada));
			filtroFuncionalidadeIniciada
					.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo.processoTipo");

			Collection colecaoFuncionalidadesIniciadas = getControladorUtil()
					.pesquisar(filtroFuncionalidadeIniciada,
							FuncionalidadeIniciada.class.getName());

			FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util
					.retonarObjetoDeColecao(colecaoFuncionalidadesIniciadas);
			funcionalidadeIniciada.setDescricaoExcecao(null);
			funcionalidadeIniciada.setDataHoraTermino(null);

			// Limpa a coleção das funcionalidades já executadas da continuação
			try {
				Object tarefa = IoUtil
						.transformarBytesParaObjeto(funcionalidadeIniciada
								.getTarefaBatch());

				if (tarefa instanceof TarefaBatch) {

					TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

					tarefaBatch.setUnidadesJaExecutadas(null);

				}

				funcionalidadeIniciada.setTarefaBatch(IoUtil
						.transformarObjetoParaBytes(tarefa));

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

			if (funcionalidadeIniciada.getProcessoIniciado().getProcesso()
					.getProcessoTipo().getId().equals(ProcessoTipo.RELATORIO)) {
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.AGENDADA);
			} else {
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
			}

			funcionalidadeIniciada
					.setFuncionalidadeSituacao(funcionalidadeSituacao);

			getControladorUtil().atualizar(funcionalidadeIniciada);

			i = i + 1;

		}

		FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
		filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
				FiltroProcessoIniciado.ID, idProcessoIniciado));
		filtroProcessoIniciado
				.adicionarCaminhoParaCarregamentoEntidade("processo.processoTipo");

		Collection colecaoProcessosIniciados = getControladorUtil().pesquisar(
				filtroProcessoIniciado, ProcessoIniciado.class.getName());

		ProcessoIniciado processoIniciado = (ProcessoIniciado) Util
				.retonarObjetoDeColecao(colecaoProcessosIniciados);

		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		if (processoIniciado.getProcesso().getProcessoTipo().getId().equals(
				ProcessoTipo.RELATORIO)) {
			processoSituacao.setId(ProcessoSituacao.AGENDADO);
		} else {
			processoSituacao.setId(ProcessoSituacao.EM_ESPERA);
		}

		processoIniciado.setDataHoraTermino(null);
		processoIniciado.setProcessoSituacao(processoSituacao);

		getControladorUtil().atualizar(processoIniciado);

	}

	/**
	 * Remove uma coleção de GuiaPagamentoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamentoCategoria
	 * @throws ControladorException
	 */
	public void removerColecaoGuiaPagamentoCategoriaParaBatch(
			Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria)
			throws ControladorException {

		try {
			if (colecaoGuiaPagamentoCategoria != null
					&& !colecaoGuiaPagamentoCategoria.isEmpty()) {
				repositorioBatch
						.removerColecaoGuiaPagamentoCategoriaParaBatch(colecaoGuiaPagamentoCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de ClienteGuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteGuiaPagamento
	 * @throws ControladorException
	 */
	public void removerColecaoClienteGuiaPagamentoParaBatch(
			Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento)
			throws ControladorException {

		try {
			if (colecaoClienteGuiaPagamento != null
					&& !colecaoClienteGuiaPagamento.isEmpty()) {
				repositorioBatch
						.removerColecaoClienteGuiaPagamentoParaBatch(colecaoClienteGuiaPagamento);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de GuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamento
	 * @throws ControladorException
	 */
	public void removerColecaoGuiaPagamentoParaBatch(
			Collection<GuiaPagamento> colecaoGuiaPagamento)
			throws ControladorException {

		try {
			if (colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()) {
				repositorioBatch
						.removerColecaoGuiaPagamentoParaBatch(colecaoGuiaPagamento);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de DebitoACobrar
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrar
	 * @throws ControladorException
	 */
	public void removerColecaoDebitoACobrarParaBatch(
			Collection<DebitoACobrar> colecaoDebitoACobrar)
			throws ControladorException {

		try {
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
				repositorioBatch
						.removerColecaoDebitoACobrarParaBatch(colecaoDebitoACobrar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de DebitoACobrarCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrarCategoria
	 * @throws ControladorException
	 */
	public void removerColecaoDebitoACobrarCategoriaParaBatch(
			Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria)
			throws ControladorException {

		try {
			if (colecaoDebitoACobrarCategoria != null
					&& !colecaoDebitoACobrarCategoria.isEmpty()) {
				repositorioBatch
						.removerColecaoDebitoACobrarCategoriaParaBatch(colecaoDebitoACobrarCategoria);
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
	public void removerColecaoPagamentoParaBatch(
			Collection<Pagamento> colecaoPagamento) throws ControladorException {

		try {
			if (colecaoPagamento != null && !colecaoPagamento.isEmpty()) {
				repositorioBatch
						.removerColecaoPagamentoParaBatch(colecaoPagamento);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de Devolução
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDevolucao
	 * @throws ControladorException
	 */
	public void removerColecaoDevolucaoParaBatch(
			Collection<Devolucao> colecaoDevolucao) throws ControladorException {

		try {
			if (colecaoDevolucao != null && !colecaoDevolucao.isEmpty()) {
				repositorioBatch
						.removerColecaoDevolucaoParaBatch(colecaoDevolucao);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de Conta
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta)
			throws ControladorException {

		try {
			if (colecaoConta != null && !colecaoConta.isEmpty()) {
				repositorioBatch.removerColecaoContaParaBatch(colecaoConta);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de ContaCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ControladorException
	 */
	public void removerColecaoContaCategoriaParaBatch(
			Collection<ContaCategoria> colecaoContaCategoria)
			throws ControladorException {

		try {
			if (colecaoContaCategoria != null
					&& !colecaoContaCategoria.isEmpty()) {
				repositorioBatch
						.removerColecaoContaCategoriaParaBatch(colecaoContaCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de ContaCategoriaConsumoFaixa
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoriaConsumoFaixa
	 * @throws ControladorException
	 */
	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(
			Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa)
			throws ControladorException {

		try {
			if (colecaoContaCategoriaConsumoFaixa != null
					&& !colecaoContaCategoriaConsumoFaixa.isEmpty()) {
				repositorioBatch
						.removerColecaoContaCategoriaConsumoFaixaParaBatch(colecaoContaCategoriaConsumoFaixa);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de CreditoRealizado
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizado
	 * @throws ControladorException
	 */
	public void removerColecaoCreditoRealizadoParaBatch(
			Collection<CreditoRealizado> colecaoCreditoRealizado)
			throws ControladorException {

		try {
			if (colecaoCreditoRealizado != null
					&& !colecaoCreditoRealizado.isEmpty()) {
				repositorioBatch
						.removerColecaoCreditoRealizadoParaBatch(colecaoCreditoRealizado);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de DebitoCobrado
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobrado
	 * @throws ControladorException
	 */
	public void removerColecaoDebitoCobradoParaBatch(
			Collection<DebitoCobrado> colecaoDebitoCobrado)
			throws ControladorException {

		try {
			if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()) {
				repositorioBatch
						.removerColecaoDebitoCobradoParaBatch(colecaoDebitoCobrado);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de DebitoCobradoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobradoCategoria
	 * @throws ControladorException
	 */
	public void removerColecaoDebitoCobradoCategoriaParaBatch(
			Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria)
			throws ControladorException {

		try {
			if (colecaoDebitoCobradoCategoria != null
					&& !colecaoDebitoCobradoCategoria.isEmpty()) {
				repositorioBatch
						.removerColecaoDebitoCobradoCategoriaParaBatch(colecaoDebitoCobradoCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de CreditoRealizadoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizadoCategoria
	 * @throws ControladorException
	 */
	public void removerColecaoCreditoRealizadoCategoriaParaBatch(
			Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria)
			throws ControladorException {

		try {
			if (colecaoCreditoRealizadoCategoria != null
					&& !colecaoCreditoRealizadoCategoria.isEmpty()) {
				repositorioBatch
						.removerColecaoCreditoRealizadoCategoriaParaBatch(colecaoCreditoRealizadoCategoria);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de ContaImpostosDeduzidos
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaImpostosDeduzidos
	 * @throws ControladorException
	 */
	public void removerColecaoContaImpostosDeduzidosParaBatch(
			Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos)
			throws ControladorException {

		try {
			if (colecaoContaImpostosDeduzidos != null
					&& !colecaoContaImpostosDeduzidos.isEmpty()) {
				repositorioBatch
						.removerColecaoContaImpostosDeduzidosParaBatch(colecaoContaImpostosDeduzidos);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de ClienteConta
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteConta
	 * @throws ControladorException
	 */
	public void removerColecaoClienteContaParaBatch(
			Collection<ClienteConta> colecaoClienteConta)
			throws ControladorException {

		try {
			if (colecaoClienteConta != null && !colecaoClienteConta.isEmpty()) {
				repositorioBatch
						.removerColecaoClienteContaParaBatch(colecaoClienteConta);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	private ControladorIntegracaoLocal getControladorIntegracao() {
		ControladorIntegracaoLocalHome localHome = null;
		ControladorIntegracaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorIntegracaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_INTEGRACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Remove uma coleção de CreditoARealizar
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoARealizar
	 * @throws ControladorException
	 */
	public void removerColecaoCreditoARealizarParaBatch(
			Collection<CreditoARealizar> colecaoCreditoARealizar)
			throws ControladorException {

		try {
			if (colecaoCreditoARealizar != null
					&& !colecaoCreditoARealizar.isEmpty()) {
				repositorioBatch
						.removerColecaoCreditoARealizarParaBatch(colecaoCreditoARealizar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Remove uma coleção de CreditoARealizarCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 09/04/2008
	 * 
	 * @param colecaoIdsCreditoARealizar
	 * @throws ControladorException
	 */
	public void removerColecaoCreditoARealizarCategoriaParaBatch(
			Collection<Integer> colecaoIdsCreditoARealizar)
			throws ControladorException {

		try {
			if (colecaoIdsCreditoARealizar != null
					&& !colecaoIdsCreditoARealizar.isEmpty()) {
				repositorioBatch
						.removerColecaoCreditoARealizarCategoriaParaBatch(colecaoIdsCreditoARealizar);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Retorna a interface remota de ControladorSpcSerasa
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorSpcSerasaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Insere um processo batch ativado por um usuário através de uma
	 * funcionalidade comum
	 * 
	 * @author Rodrigo Silveira
	 * @date 02/05/2008
	 * 
	 * @param processoIniciado
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	public Integer inserirProcessoIniciadoParametrosLivres(Map parametros,
			int idProcesso, Usuario usuario) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = inserirProcessoIniciadoParametrosLivres(
					idProcesso, usuario);

			// Todos os processo serao iniciados com a situaaao EM_ESPERA para q
			// sejam executados o mais cedo possavel
			ProcessoSituacao processoSituacao = new ProcessoSituacao();
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(idProcesso);
			processoSituacao.setId(processoSituacaoId);
			processoIniciado.setProcessoSituacao(processoSituacao);
			processoIniciado.setDataHoraInicio(new Date());
			processoIniciado.setDataHoraComando(new Date());

			if (parametros.get("idGrupoFaturamento") != null) {
				String idGrupoFaturamento = (String) parametros
						.get("idGrupoFaturamento");
				processoIniciado.setCodigoGrupoProcesso(new Integer(
						idGrupoFaturamento));
			}

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				// Seta os parametros da funcionalidadeIniciada

				try {

					switch (funcionalidadeIniciada.getProcessoFuncionalidade()
							.getFuncionalidade().getId()) {

					case Funcionalidade.GERAR_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE:
						TarefaBatchRelatorioContasBaixadasContabilmente txtContasBaixadasContabilmente = new TarefaBatchRelatorioContasBaixadasContabilmente(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Adicionar o conjunto de parametros informados pelo
						// usuário através da interface do sistema
						txtContasBaixadasContabilmente.addParametro(
								ConstantesSistema.PARAMETROS_BATCH, parametros);

						// ----------------------------------------------
						// FAZER CONSULTA DOS SETORES
						Collection idsSetorComercial = new ArrayList();

						Short tipo = (Short) parametros.get("tipo");
						if (tipo.equals(ConstantesSistema.ANALITICO)) {
							idsSetorComercial = getControladorCadastro()
									.pesquisarIdsCodigosSetorComercial();
						}

						// //----------------------------------------------
						txtContasBaixadasContabilmente
								.addParametro(
										ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
										idsSetorComercial);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(txtContasBaixadasContabilmente));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA:

						String acao = (String) parametros.get("acao");

						if (acao.equals("1")) {
							// se gerar cartas
							TarefaBatchGerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista cartasGerar = new TarefaBatchGerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(
									processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							// Adicionar o conjunto de parametros informados
							// pelo usuário através da interface do sistema
							// cartasGerar.addParametro(ConstantesSistema.PARAMETROS_BATCH,
							// parametros);

							// ----------------------------------------------
							// FAZER CONSULTA DAS ROTAS
							Collection idsRota = new ArrayList();

							String idGrupoFaturamento = (String) parametros
									.get("idGrupoFaturamento");
							idsRota = getControladorCobranca()
									.pesquisarRotasPorGrupoFaturamento(
											new Integer(idGrupoFaturamento));

							// ----------------------------------------------
							cartasGerar
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											idsRota);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(cartasGerar));

						} else {
							// se emitir cartas
							TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista cartasEmitir = new TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(
									processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							// Adicionar o conjunto de parametros informados
							// pelo usuário através da interface do sistema
							// cartasEmitir.addParametro(ConstantesSistema.PARAMETROS_BATCH,
							// parametros);

							// ----------------------------------------------
							String idGrupoFaturamento = (String) parametros
									.get("idGrupoFaturamento");
							cartasEmitir.addParametro("faturamentoGrupo",
									idGrupoFaturamento);

							// FAZER CONSULTA DAS ROTAS
							// Collection idsRota = new ArrayList();
							// idsRota =
							// getControladorCobranca().pesquisarRotasPorGrupoFaturamento(new
							// Integer(idGrupoFaturamento));
							//	                            
							// //----------------------------------------------
							// cartasEmitir.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
							// idsRota);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(cartasEmitir));
						}

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					case Funcionalidade.GERAR_TALELAS_TEMPORARIAS_ATUALIZACAO_CADASTRAL:
						TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral tabela = new TarefaBatchGerarTabelasTemporariasAtualizacaoCadastral(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						tabela.addParametro(ConstantesSistema.PARAMETROS_BATCH, parametros);

						ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper = 
								(ImovelGeracaoTabelasTemporariasCadastroHelper) parametros.get("imovelGeracaoTabelasTemporariasCadastroHelper");

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

					/** Vivianne Sousa 12/07/2009 */
					case Funcionalidade.EMITIR_BOLETOS:
						TarefaBatchEmitirBoletos dadosEmitirBoletos = new TarefaBatchEmitirBoletos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						String idGrupoFaturamento = (String) parametros
								.get("idGrupoFaturamento");
						dadosEmitirBoletos.addParametro("faturamentoGrupo",
								idGrupoFaturamento);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosEmitirBoletos));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Vivianne Sousa 04/08/2009 */
					case Funcionalidade.RETIFICAR_CONJUNTO_CONTA:
						TarefaBatchRetificarConjuntoContaConsumos dados = new TarefaBatchRetificarConjuntoContaConsumos(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Adicionar o conjunto de parametros informados pelo
						// usuário através da interface do sistema
						dados.addParametro(ConstantesSistema.PARAMETROS_BATCH,
								parametros);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(dados));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					case Funcionalidade.GERAR_CARTAS_DE_FINAL_DE_ANO:

						String acaoCartasDeFinalDeAno = (String) parametros
								.get("acao");
						String idGruposFaturamento = (String) parametros
								.get("idGrupoFaturamento");
						if (acaoCartasDeFinalDeAno.equals("1")) {
							// se gerar cartas
							TarefaBatchGerarCartasDeFinalDeAno cartasGerar = new TarefaBatchGerarCartasDeFinalDeAno(
									processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							// ----------------------------------------------
							// FAZER CONSULTA DAS ROTAS
							Collection idsRota = new ArrayList();

							idsRota = getControladorCobranca()
									.pesquisarRotasPorGrupoFaturamento(
											new Integer(idGruposFaturamento));
							cartasGerar
									.addParametro(
											ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
											idsRota);
							// ----------------------------------------------

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(cartasGerar));

						} else {
							// se emitir cartas
							TarefaBatchEmitirCartasDeFinalDeAno cartasEmitir = new TarefaBatchEmitirCartasDeFinalDeAno(
									processoIniciado.getUsuario(),
									funcionalidadeIniciada.getId());

							// ----------------------------------------------
							cartasEmitir.addParametro("faturamentoGrupo",
									idGruposFaturamento);

							// Seta o objeto para ser serializado no banco, onde
							// depois sera executado por uma thread
							funcionalidadeIniciada.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(cartasEmitir));
						}

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;

					/** Vivianne Sousa 23/12/2009 */
					case Funcionalidade.INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS:
						TarefaBatchInserirPagamentosFaturasEspeciais faturas = new TarefaBatchInserirPagamentosFaturasEspeciais(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Adicionar o conjunto de parametros informados pelo
						// usuário através da interface do sistema
						faturas.addParametro(
								ConstantesSistema.PARAMETROS_BATCH, parametros);

						// Seta o objeto para ser serializado no banco, onde
						// depois será executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(faturas));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;

					/** Hugo Leonardo 18/10/2010 */
					case Funcionalidade.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS:
						TarefaBatchPrescreverDebitosImoveisPublicosManual prescricao = new TarefaBatchPrescreverDebitosImoveisPublicosManual(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						// Adicionar o conjunto de parametros informados pelo
						// usuário através da interface do sistema
						prescricao.addParametro(
								ConstantesSistema.PARAMETROS_BATCH, parametros);

						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(prescricao));

						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
					
					
				case Funcionalidade.PROCESSAR_COMANDO_GERADO:
					
					TarefaBatchProcessarComandoGerado tarefaBatchProcessarComandoGerado = new TarefaBatchProcessarComandoGerado(
							processoIniciado.getUsuario(),funcionalidadeIniciada.getId());
					
					TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");
					
					Collection idsLocalidade = null;
					if(tarifaSocialComandoCarta.getLocalidade() != null && tarifaSocialComandoCarta.getLocalidade().getId() != null){
						idsLocalidade = new ArrayList();
						idsLocalidade.add(tarifaSocialComandoCarta.getLocalidade().getId());
						
					}else if(tarifaSocialComandoCarta.getGerenciaRegional() != null 
							&& tarifaSocialComandoCarta.getGerenciaRegional().getId() != null &&
							tarifaSocialComandoCarta.getUnidadeNegocio() != null
							&& tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null){
						
						idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorGerenciaEUnidade(
								tarifaSocialComandoCarta.getGerenciaRegional().getId(),tarifaSocialComandoCarta.getUnidadeNegocio().getId());
						
					}else if(tarifaSocialComandoCarta.getGerenciaRegional() != null 
							&& tarifaSocialComandoCarta.getGerenciaRegional().getId() != null){
							idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorGerencia(tarifaSocialComandoCarta.getGerenciaRegional().getId());
							
					}else if(tarifaSocialComandoCarta.getUnidadeNegocio() != null
							&& tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null){
						idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorUnidadeNegocio(tarifaSocialComandoCarta.getUnidadeNegocio().getId());
						
					}else{
						idsLocalidade = getControladorCadastro().pesquisarLocalidade();
					}
					
					if(idsLocalidade == null || idsLocalidade.isEmpty()){
						throw new ControladorException("atencao.nao_existe_dados_filtro");
					}

					tarefaBatchProcessarComandoGerado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,idsLocalidade);
					tarefaBatchProcessarComandoGerado.addParametro("tarifaSocialComandoCarta",tarifaSocialComandoCarta);
					
					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarComandoGerado));
					getControladorUtil().atualizar(funcionalidadeIniciada);
					break;
				
				case Funcionalidade.SELECIONAR_COMANDO_RETIRAR_IMOVEL_TARIFA_SOCIAL:
					
					TarefaBatchRetirarImovelTarifaSocial tarefaBatchRetirarImovelTarifaSocial = new TarefaBatchRetirarImovelTarifaSocial(
							processoIniciado.getUsuario(),funcionalidadeIniciada.getId());
					
					TarifaSocialComandoCarta tsccarta = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");
					
//					tarefaBatchRetirarImovelTarifaSocial(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,idsLocalidade);
					tarefaBatchRetirarImovelTarifaSocial.addParametro("tarifaSocialComandoCarta",tsccarta);
					
					funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchRetirarImovelTarifaSocial));
					getControladorUtil().atualizar(funcionalidadeIniciada);
					break;
					

				/** [UC1168] Encerrar Comandos de Cobrança por Empresa
				 *  Mariana Victor 09/05/2011
				 **/
				case Funcionalidade.ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA:
					TarefaBatchEncerrarComandosDeCobrancaPorEmpresa dadosEncerrarComandos = new TarefaBatchEncerrarComandosDeCobrancaPorEmpresa(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					String idEmpresa = (String) parametros
							.get("idEmpresa");
					Integer idRegistroComando = (Integer) parametros
							.get("idRegistro");
					Integer idCobrancaSituacao = (Integer) parametros
						.get("idCobrancaSituacao");
					
					dadosEncerrarComandos.addParametro("idEmpresa",
							idEmpresa);

					dadosEncerrarComandos.addParametro("usuario",
							usuario);
					
					dadosEncerrarComandos.addParametro("idRegistro",
							idRegistroComando);
					
					dadosEncerrarComandos.addParametro("idCobrancaSituacao",
							idCobrancaSituacao);
					
					Collection colecao = new ArrayList();
					colecao.add(idRegistroComando);
					
					dadosEncerrarComandos.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
							colecao);
					
					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada
							.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(dadosEncerrarComandos));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;
					
					
					/** [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
					 *  Mariana Victor 21/06/2011
					 **/
					case Funcionalidade.RECEPCIONAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA:
						TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca = new TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca(
								processoIniciado.getUsuario(),
								funcionalidadeIniciada.getId());

						String idEmpresaComando = (String) parametros
								.get("idEmpresa");
						StringBuilder stringBuilder = (StringBuilder) parametros
								.get("stringBuilder");
						String nomeArquivo = (String) parametros
								.get("nomeArquivo");
						
						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca
							.addParametro("idEmpresa", idEmpresaComando);

						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca
							.addParametro("usuario", usuario);
						
						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca
							.addParametro("stringBuilder", stringBuilder);
						
						tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca
							.addParametro("nomeArquivo", nomeArquivo);
						
						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada
								.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(tarefaBatchProcessarArquivoTxtEncerramentoOSCobranca));

						getControladorUtil().atualizar(funcionalidadeIniciada);

						break;
					/**
					 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
					 * @author Vivianne Sousa
					 * @created 21/07/2011
					 */
					case Funcionalidade.ENCERRAR_COMANDO_OS_SELETIVA_INSPECAO_ANORMALIDADE:
						TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade dadosEncerrarComando = new TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade(
								processoIniciado.getUsuario(),	funcionalidadeIniciada.getId());

						Short idMotivoEncerramento = (Short) parametros.get("idMotivoEncerramento");
						Integer idRegistroComandoOS = (Integer) parametros.get("idRegistro");
						
						dadosEncerrarComando.addParametro("idMotivoEncerramento",idMotivoEncerramento);

						dadosEncerrarComando.addParametro("usuario",usuario);
						
						dadosEncerrarComando.addParametro("idRegistro",idRegistroComandoOS);
						
						// Seta o objeto para ser serializado no banco, onde
						// depois sera executado por uma thread
						funcionalidadeIniciada.setTarefaBatch(IoUtil
										.transformarObjetoParaBytes(dadosEncerrarComando));

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
	 * Cria um ProcessoIniciado para os processos batch iniciados através da
	 * interface comum
	 * 
	 * @param usuario
	 *            Usuário que iniciou o processo
	 * @return
	 */
	private ProcessoIniciado inserirProcessoIniciadoParametrosLivres(
			int idProcesso, Usuario usuario) {

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

	private void inserirLogExcecaoFuncionalidadeIniciada(
			UnidadeIniciada unidadeIniciada, Throwable excecao) {
		// Verificar se a unidade já possui o registro do log da exceção
		// Só o primeiro log deverá ser gravado

		try {
			repositorioBatch.inserirLogExcecaoFuncionalidadeIniciada(
					unidadeIniciada, excecao);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

	}

	public void atualizarSituacaoFuncionalidadeIniciadaConcluida(
			FuncionalidadeIniciada funcionalidadeIniciada) {

		try {
			repositorioBatch
					.atualizarSituacaoFuncionalidadeIniciadaConcluida(funcionalidadeIniciada);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Continua o processamento de um batch
	 * 
	 * @author Rodrigo Silveira
	 * @date 13/08/2008
	 * 
	 * @param idsFuncionalidadesIniciadas
	 * @param idProcessoIniciado
	 */
	public void continuarFuncionalidadesIniciadas(
			String[] idsFuncionalidadesIniciadas, Integer idProcessoIniciado)
			throws ControladorException {

		String idFuncionalidadeIniciada = idsFuncionalidadesIniciadas[0];

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.ID, idFuncionalidadeIniciada));
		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoIniciado.processo.processoTipo");
		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeSituacao");
		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("unidadesIniciadas");

		Collection colecaoFuncionalidadesIniciadas = getControladorUtil()
				.pesquisar(filtroFuncionalidadeIniciada,
						FuncionalidadeIniciada.class.getName());

		FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util
				.retonarObjetoDeColecao(colecaoFuncionalidadesIniciadas);

		int funcionalidadeSituacaoId = funcionalidadeIniciada
				.getFuncionalidadeSituacao().getId();

		if ((funcionalidadeSituacaoId != (FuncionalidadeSituacao.CONCLUIDA_COM_ERRO))
				&& (funcionalidadeSituacaoId != (FuncionalidadeSituacao.EXECUCAO_CANCELADA))) {
			throw new ControladorException("erro.batch.opcao_invalida");

		}

		funcionalidadeIniciada.setDescricaoExcecao(null);
		funcionalidadeIniciada.setDataHoraTermino(null);
		funcionalidadeIniciada.setDescricaoExcecao(null);

		FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

		if (funcionalidadeIniciada.getProcessoIniciado().getProcesso()
				.getProcessoTipo().getId().equals(ProcessoTipo.RELATORIO)) {
			throw new ControladorException("erro.batch.opcao_invalida");
		} else {
			funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
		}

		funcionalidadeIniciada
				.setFuncionalidadeSituacao(funcionalidadeSituacao);

		FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
		filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
				FiltroProcessoIniciado.ID, idProcessoIniciado));
		filtroProcessoIniciado
				.adicionarCaminhoParaCarregamentoEntidade("processo.processoTipo");

		Collection colecaoProcessosIniciados = getControladorUtil().pesquisar(
				filtroProcessoIniciado, ProcessoIniciado.class.getName());

		ProcessoIniciado processoIniciado = (ProcessoIniciado) Util
				.retonarObjetoDeColecao(colecaoProcessosIniciados);

		ProcessoSituacao processoSituacao = new ProcessoSituacao();

		processoSituacao.setId(ProcessoSituacao.EM_ESPERA);

		processoIniciado.setDataHoraTermino(null);
		processoIniciado.setProcessoSituacao(processoSituacao);

		getControladorUtil().atualizar(processoIniciado);

		try {
			Object tarefa = IoUtil
					.transformarBytesParaObjeto(funcionalidadeIniciada
							.getTarefaBatch());

			if (tarefa instanceof TarefaBatch) {

				TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

				tarefaBatch.setUnidadesJaExecutadas(funcionalidadeIniciada
						.getUnidadesIniciadas());

			} else {
				throw new ControladorException("erro.batch.opcao_invalida");

			}

			funcionalidadeIniciada.setTarefaBatch(IoUtil
					.transformarObjetoParaBytes(tarefa));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			repositorioBatch.removerUnidadesIniciadas(new Integer(
					idFuncionalidadeIniciada));
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		getControladorUtil().atualizar(funcionalidadeIniciada);

	}

	/**
	 * Verifica se o processo está em execução
	 * 
	 * @author Ana Maria
	 * @date 18/12/2008
	 * 
	 */
	public boolean verificarProcessoEmExecucao(Integer idProcesso)
			throws ControladorException {
		try {
			return this.repositorioBatch
					.verificarProcessoEmExecucao(idProcesso);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Continua o processamento de um batch
	 * 
	 * @author Rômulo Aurélio
	 * @date 03/12/2008
	 * 
	 * @param ids
	 * @param idEmpresa
	 * @param idFuncionalidadeIniciada
	 * @param usuario
	 */

	public Integer inserirProcessoIniciadoContasCobranca(Collection ids,
			Integer idEmpresa, Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		// Converte a Colecao de id em Integer[]
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

			processo
					.setId(Processo.GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_EMPRESA);

			processoIniciado.setProcesso(processo);

			processoIniciado.setDataHoraAgendamento(new Date());

			processoIniciado.setDataHoraInicio(new Date());

			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				TarefaBatchGerarArquivoTextoContasCobrancaEmpresa tarefaBatchGerarArquivoTextoContasCobrancaEmpresa = new TarefaBatchGerarArquivoTextoContasCobrancaEmpresa(
						processoIniciado.getUsuario(), funcionalidadeIniciada
								.getId());

				// Seta os parametros para rodar a funcionalidade
				tarefaBatchGerarArquivoTextoContasCobrancaEmpresa.addParametro(
						"idsRegistros", ids);
				tarefaBatchGerarArquivoTextoContasCobrancaEmpresa.addParametro(
						"idEmpresa", idEmpresa);

				Collection colecaoUnidadeNegocio = getControladorCobranca()
						.obterUnidadeNegocioEmpresaCobrancaConta(idsRegistros);

				tarefaBatchGerarArquivoTextoContasCobrancaEmpresa.addParametro(
						ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
						colecaoUnidadeNegocio);

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread

				funcionalidadeIniciada
						.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoContasCobrancaEmpresa));

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
	 * @author Rômulo Aurélio
	 * @date 12/01/2009
	 * 
	 * @param ids
	 * @param idEmpresa
	 * @param idFuncionalidadeIniciada
	 * @param usuario
	 */

	public Integer inserirProcessoIniciadoRelatorioPagamentosContasCobranca(
			RelatorioPagamentosContasCobrancaEmpresaHelper helper,
			int opcaoRelatorio, Usuario usuario) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();

			processoIniciado.setUsuario(usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();

			// Sintetico
			if (opcaoRelatorio == 1) {

				processo
						.setId(Processo.GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA_SINTETICO);
			} else {
				processo
						.setId(Processo.GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA);
			}
			
			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);
			
			processoIniciado.setProcessoSituacao(processoSituacao);

			processoIniciado.setProcesso(processo);

			processoIniciado.setDataHoraAgendamento(new Date());

			processoIniciado.setDataHoraInicio(new Date());

			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa = new TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa(
						processoIniciado.getUsuario(), funcionalidadeIniciada
								.getId());

				// Seta os parametros para rodar a funcionalidade
				tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa
						.addParametro("helper", helper);

				tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa
						.addParametro("opcaoRelatorio", opcaoRelatorio);

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread

				funcionalidadeIniciada
						.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa));

				getControladorUtil().atualizar(funcionalidadeIniciada);

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return codigoProcessoIniciadoGerado;

	}

	/**
	 * Inserir e Atualizar Movimentações de Hidrometros
	 * 
	 * @author Arthur Carvalho
	 * @date 17/06/2009
	 * 
	 */
	public Integer inserirAtualizarMovimentacaoHidrometroIdsBatch(
			MovimentoHidrometroHelper helper) throws ControladorException {

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

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				TarefaBatchGerarMovimentoHidrometro tarefaBatchGerarMovimentoHidrometro = new TarefaBatchGerarMovimentoHidrometro(
						processoIniciado.getUsuario(), funcionalidadeIniciada
								.getId());

				// Seta os parametros para rodar a funcionalidade
				tarefaBatchGerarMovimentoHidrometro.addParametro("helper",
						helper);

				tarefaBatchGerarMovimentoHidrometro.addParametro(
						ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
						helper.getColecaoHidrometroSelecionado());

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread

				funcionalidadeIniciada
						.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchGerarMovimentoHidrometro));

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
	 * 
	 */
	public Integer inserirProcessoAtualizarConjuntoHidrometro(String fixo,
			String inicialFixo, String finalFixo,
			Hidrometro hidrometroAtualizado, Usuario usuarioLogado,
			Integer totalRegistros) throws ControladorException {

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

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) Util
					.retonarObjetoDeColecao(processosFuncionaliadade);

			int qtdPaginas = Util.dividirArredondarResultadoCima(
					totalRegistros, 500);

			Collection colIndice = new ArrayList();

			for (int i = 1; i <= qtdPaginas; i++) {
				colIndice.add(new Integer(i));
			}

			FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

			FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
			funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
			funcionalidadeIniciada
					.setFuncionalidadeSituacao(funcionalidadeSituacao);

			funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

			funcionalidadeIniciada
					.setProcessoFuncionalidade(processoFuncionalidade);

			funcionalidadeIniciada.setId((Integer) getControladorUtil()
					.inserir(funcionalidadeIniciada));

			TarefaBatchAtualizarConjuntoHidrometro tarefaBatchAtualizarConjuntoHidrometro = new TarefaBatchAtualizarConjuntoHidrometro(
					processoIniciado.getUsuario(), funcionalidadeIniciada
							.getId());

			// Seta os parametros para rodar a funcionalidade
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("fixo", fixo);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("fixoInicial",
					inicialFixo);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("fixoFinal",
					finalFixo);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro(
					"hidrometroAtualizado", hidrometroAtualizado);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("usuario",
					usuarioLogado);
			tarefaBatchAtualizarConjuntoHidrometro.addParametro("qtdPaginas",
					qtdPaginas);

			tarefaBatchAtualizarConjuntoHidrometro.addParametro(
					ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
					colIndice);

			// Seta o objeto para ser serializado no banco, onde
			// depois sera executado por uma thread
			funcionalidadeIniciada
					.setTarefaBatch(IoUtil
							.transformarObjetoParaBytes(tarefaBatchAtualizarConjuntoHidrometro));

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
	 * @author Rodrigo Silveira
	 * @date 08/06/2009
	 * 
	 * @throws ControladorException
	 */

	public void iniciarProcessoRelatorioControleAutorizacao(
			TarefaRelatorio tarefaRelatorio) throws ControladorException {
		try {

			// Constroi um processoIniciado para o Relatorio
			ProcessoIniciado processoIniciado = new ProcessoIniciado();
			processoIniciado.setUsuario(tarefaRelatorio.getUsuario());
			int idProcesso = GerenciadorExecucaoTarefaRelatorio
					.obterProcessoRelatorio(tarefaRelatorio.getNomeRelatorio());

			// O usuário não pode agendar relatórios caso exista mais de dois
			// documentos em situação de 'Aguardando Autorização'

			if (!repositorioBatch.validarAutorizacaoInserirRelatorioBatch(
					tarefaRelatorio.getUsuario(), idProcesso)) {
				throw new ControladorException(
						"atencao.numero.registro.controle_autorizacao_relatorio");

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

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();
			while (iterator.hasNext()) {

				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();

				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();

				// AGENDADA NAO ENTRA EM EXECUCAO
				// SOH EXECUTA QUANDO ENTRA EM_ESPERA
				Integer funcionalidadeSituacaoId = this.verificarAutorizacaoRelatorioBatch(idProcesso);
				funcionalidadeSituacao
						.setId(funcionalidadeSituacaoId);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);
				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);
				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setDataHoraInicio(new Date());
				// funcionalidadeIniciada.setDataHoraTermino(new Date());

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				// Seta os parametros da funcionalidadeIniciada
				tarefaRelatorio
						.setIdFuncionalidadeIniciada(funcionalidadeIniciada
								.getId());

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread
				funcionalidadeIniciada.setTarefaBatch(IoUtil
						.transformarObjetoParaBytes(tarefaRelatorio));

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
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */

	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado,Integer processoSituacao,Integer funcionalidadeSituacao)
			throws ControladorException {
		try {
			this.repositorioBatch.autorizarProcessoIniciado(processoIniciado,processoSituacao);
			this.repositorioBatch.autorizarFuncionalidadeIniciada(processoIniciado,funcionalidadeSituacao);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 06/10/2009
	 * 
	 */
	public Integer inserirProcessoIniciadoPagamentosContasCobranca(
			Integer idEmpresa, Integer referenciaInicial,
			Integer referenciaFinal, Usuario usuario)
			throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();

			processoIniciado.setUsuario(usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();

			processo
					.setId(Processo.GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA);

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);
			
			processoIniciado.setProcessoSituacao(processoSituacao);
			
			processoIniciado.setProcesso(processo);

			processoIniciado.setDataHoraAgendamento(new Date());

			processoIniciado.setDataHoraInicio(new Date());

			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa = new TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa(
						processoIniciado.getUsuario(), funcionalidadeIniciada
								.getId());

				Collection colecaoUnidadeNegocio = getControladorCobranca()
						.obterUnidadeNegocioPagamentosEmpresaCobrancaConta();

				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa
						.addParametro(
								ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
								colecaoUnidadeNegocio);

				// Seta os parametros para rodar a funcionalidade
				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa
						.addParametro("referenciaInicial", referenciaInicial);
				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa
						.addParametro("referenciaFinal", referenciaFinal);
				tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa
						.addParametro("idEmpresa", idEmpresa);

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread

				funcionalidadeIniciada
						.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa));

				getControladorUtil().atualizar(funcionalidadeIniciada);

				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return codigoProcessoIniciadoGerado;

	}

	/**
	 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @date 14/12/2009
	 * 
	 */
	public Integer inserirProcessoGerarTxtContasProcessosEspeciais(
			String anoMes, Integer idCliente, Usuario usuario)
			throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = new ProcessoIniciado();

			processoIniciado.setUsuario(usuario);

			ProcessoSituacao processoSituacao = new ProcessoSituacao();

			Processo processo = new Processo();

			processo
					.setId(Processo.GERAR_ARQUIVO_TEXTO_CONTAS_PROJETOS_ESPECIAIS);

			Integer processoSituacaoId = this.verificarAutorizacaoBatch(processo.getId());
			processoSituacao.setId(processoSituacaoId);
			
			processoIniciado.setProcessoSituacao(processoSituacao);
			
			processoIniciado.setProcesso(processo);

			processoIniciado.setDataHoraAgendamento(new Date());

			processoIniciado.setDataHoraInicio(new Date());

			processoIniciado.setDataHoraComando(new Date());

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {
				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				TarefaBatchGerarArquivoTextoContasProjetosEspeciais tarefaBatchGerarArquivoTextoContasProjetosEspeciais = new TarefaBatchGerarArquivoTextoContasProjetosEspeciais(
						processoIniciado.getUsuario(), funcionalidadeIniciada
								.getId());

				// Seta os parametros para rodar a funcionalidade
				tarefaBatchGerarArquivoTextoContasProjetosEspeciais
						.addParametro("anoMes", anoMes);
				tarefaBatchGerarArquivoTextoContasProjetosEspeciais
						.addParametro("idCliente", idCliente);

				// Seta o objeto para ser serializado no banco, onde
				// depois sera executado por uma thread

				funcionalidadeIniciada
						.setTarefaBatch(IoUtil
								.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoContasProjetosEspeciais));

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
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param objetoParaAtualizar
	 * @throws ControladorException
	 */
	public void atualizarObjetoParaBatch(Object objetoParaAtualizar)
			throws ControladorException {

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
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatch(Object objeto)
			throws ControladorException {

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
	 * @author Bruno Barros
	 * @date 23/09/2010
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatchSemTransacao(Object objeto)
			throws ControladorException {

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
	 * 
	 * @author Hugo Amorim
	 * @date 12/05/2010
	 * @param idFaturamentoGrupo
	 * 
	 */
	public Integer inserirProcessoGerarTxtDeclaracaoQuitacaoDebitos(
			Integer idGrupoFaturamento, Usuario usuario)
			throws ControladorException {

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

			codigoProcessoIniciadoGerado = (Integer) getControladorUtil()
					.inserir(processoIniciado);

			// Este trecho pesquisa todos do processoFuncionalidade
			// relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

			Iterator iterator = processosFuncionaliadade.iterator();

			while (iterator.hasNext()) {

				ProcessoFuncionalidade processoFuncionalidade = (ProcessoFuncionalidade) iterator
						.next();
				FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();

				FuncionalidadeSituacao funcionalidadeSituacao = new FuncionalidadeSituacao();
				funcionalidadeSituacao.setId(FuncionalidadeSituacao.EM_ESPERA);
				funcionalidadeIniciada
						.setFuncionalidadeSituacao(funcionalidadeSituacao);

				funcionalidadeIniciada.setProcessoIniciado(processoIniciado);

				funcionalidadeIniciada
						.setProcessoFuncionalidade(processoFuncionalidade);

				funcionalidadeIniciada.setId((Integer) getControladorUtil()
						.inserir(funcionalidadeIniciada));

				switch (funcionalidadeIniciada.getProcessoFuncionalidade()
						.getFuncionalidade().getId()) {

				case Funcionalidade.GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

					TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					FiltroRota filtroRota = new FiltroRota();

					filtroRota
							.adicionarParametro(new ParametroSimples(
									FiltroRota.FATURAMENTO_GRUPO_ID,
									idGrupoFaturamento));

					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoRotasParaExecucao = this
							.getControladorUtil().pesquisar(filtroRota,
									Rota.class.getName());

					tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos
							.addParametro(
									ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoRotasParaExecucao);

					SistemaParametro sistemaParametroQuitacao = getControladorUtil()
							.pesquisarParametrosDoSistema();

					tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos
							.addParametro("SistemaParametros",
									sistemaParametroQuitacao);

					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada
							.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(tarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos));

					getControladorUtil().atualizar(funcionalidadeIniciada);

					break;
				case Funcionalidade.GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS:

					TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos = new TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
							processoIniciado.getUsuario(),
							funcionalidadeIniciada.getId());

					Collection<Empresa> colecaoEmpresas = getControladorFaturamento()
							.pesquisarEmpresasParaGeraracaoExtrato(
									idGrupoFaturamento);

					tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos
							.addParametro(
									ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,
									colecaoEmpresas);

					tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos
							.addParametro("idGrupoFaturamento",
									idGrupoFaturamento);

					// Seta o objeto para ser serializado no banco, onde
					// depois sera executado por uma thread
					funcionalidadeIniciada
							.setTarefaBatch(IoUtil
									.transformarObjetoParaBytes(tarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos));

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

	/**
	 * Retorna o(s) processo(s) que está em execução
	 * 
	 * @author Arthur Carvalho
	 * @date 04/06/2010
	 * 
	 */
	public Collection retornaProcessoFuncionalidadeEmExecucao()
			throws ControladorException {

		Collection colecao = null;

		try {

			colecao = this.repositorioBatch
					.retornaProcessoFuncionalidadeEmExecucao();

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return colecao;
	}

	/**
	 * Retorna a interface remota de ControladorParametro
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorGerencialFaturamentoLocal getControladorGerencialFaturamento() {
		ControladorGerencialFaturamentoLocalHome localHome = null;
		ControladorGerencialFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialFaturamentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * Insere um processo batch ativado por um usuário através de uma
	 * funcionalidade comum
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @param processoIniciado
	 * @throws ControladorException
	 * @throws ControladorException
	 */
	public Integer inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(Map parametros,
			int idProcesso, Usuario usuario) throws ControladorException {
		Integer codigoProcessoIniciadoGerado = null;

		try {

			ProcessoIniciado processoIniciado = inserirProcessoIniciadoParametrosLivres(
					idProcesso, usuario);

			// Todos os processo serao iniciados com a situaaao EM_ESPERA para q
			// sejam executados o mais cedo possavel
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

			// Este trecho pesquisa todos do processoFuncionalidade relacionados
			// com o processo do objeto a ser inserido
			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							processoIniciado.getProcesso().getId()));

			filtroProcessoFuncionalidade.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection processosFuncionaliadade = getControladorUtil()
					.pesquisar(filtroProcessoFuncionalidade,
							ProcessoFuncionalidade.class.getName());

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

				// Seta os parametros da funcionalidadeIniciada

				try {

					switch (funcionalidadeIniciada.getProcessoFuncionalidade().getFuncionalidade().getId()) {


					case Funcionalidade.GERAR_CARTA_TARIFA_SOCIAL:

						TarefaBatchGerarCartaTarifaSocial tarefaBatchGerarCartaTarifaSocial = new TarefaBatchGerarCartaTarifaSocial(
								processoIniciado.getUsuario(),funcionalidadeIniciada.getId());
						
						TarifaSocialComandoCarta tscc = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");
						
						tarefaBatchGerarCartaTarifaSocial.addParametro("tarifaSocialComandoCarta",tscc);
						
						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchGerarCartaTarifaSocial));
						getControladorUtil().atualizar(funcionalidadeIniciada);
	
						break;

					case Funcionalidade.PROCESSAR_COMANDO_GERADO:
						
						TarefaBatchProcessarComandoGerado tarefaBatchProcessarComandoGerado = new TarefaBatchProcessarComandoGerado(
								processoIniciado.getUsuario(),funcionalidadeIniciada.getId());
						
						TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta) parametros.get("tarifaSocialComandoCarta");
						
						Collection idsLocalidade = null;
						if(tarifaSocialComandoCarta.getLocalidade() != null && tarifaSocialComandoCarta.getLocalidade().getId() != null){
							idsLocalidade = new ArrayList();
							idsLocalidade.add(tarifaSocialComandoCarta.getLocalidade().getId());
						}else if(tarifaSocialComandoCarta.getGerenciaRegional() != null && tarifaSocialComandoCarta.getGerenciaRegional().getId() != null){
							idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorGerencia(tarifaSocialComandoCarta.getGerenciaRegional().getId());
						}else if(tarifaSocialComandoCarta.getUnidadeNegocio() != null && tarifaSocialComandoCarta.getUnidadeNegocio().getId() != null){
							idsLocalidade = getControladorCadastro().pesquisarLocalidadesPorUnidadeNegocio(tarifaSocialComandoCarta.getUnidadeNegocio().getId());
						}else{
							idsLocalidade = getControladorCadastro().pesquisarLocalidade();
						}

						tarefaBatchProcessarComandoGerado.addParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH,idsLocalidade);
						tarefaBatchProcessarComandoGerado.addParametro("tarifaSocialComandoCarta",tarifaSocialComandoCarta);
						
						funcionalidadeIniciada.setTarefaBatch(IoUtil.transformarObjetoParaBytes(tarefaBatchProcessarComandoGerado));
						getControladorUtil().atualizar(funcionalidadeIniciada);
						break;
						
					case Funcionalidade.EMISSAO_ORDENS_SELETIVAS:
						//[UC0713] Emitir Ordem de Serviço Seletiva
						//[SB0002]-Gerar TXT 
						TarefaBatchGerarTxtOsInspecaoAnormalidade tarefaBatchGerarTxtOsInspecaoAnormalidade = new TarefaBatchGerarTxtOsInspecaoAnormalidade(
								processoIniciado.getUsuario(),funcionalidadeIniciada.getId());
						
						Integer idComandoOrdemSeletiva = (Integer) parametros.get("idComandoOrdemSeletiva");
						Integer qtdAnormalidadesConsecutivas = (Integer) parametros.get("qtdAnormalidadesConsecutivas");
						
						tarefaBatchGerarTxtOsInspecaoAnormalidade.addParametro("idComandoOrdemSeletiva",idComandoOrdemSeletiva);
						tarefaBatchGerarTxtOsInspecaoAnormalidade.addParametro("qtdAnormalidadesConsecutivas",qtdAnormalidadesConsecutivas);
						
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
	
	
	/**
	 * @author Rodrigo Cabral
	 * @date 22/08/2011
	 * 
	 * Verifica se o Batch ja foi autorizado
	 */
	public Integer verificarAutorizacaoBatch(Integer processoId){
		
		Integer situacaoBatch = null;
		
		FiltroProcesso filtroProcesso = new FiltroProcesso();
		filtroProcesso.adicionarParametro(new ParametroSimples(
				FiltroProcesso.ID, processoId));
		
		Collection colecaoProcesso = Fachada.getInstancia().pesquisar(filtroProcesso, Processo.class.getName());
		
		Processo processo = (Processo) Util.retonarObjetoDeColecao(colecaoProcesso);
		
		if (processo.getProcessoTipo().getId().intValue() == ProcessoTipo.RELATORIO){
			
			situacaoBatch = ProcessoSituacao.AGENDADO;
		}else {
			
			situacaoBatch = ProcessoSituacao.EM_ESPERA;
		}
		
		if (processo != null){
		
			if (processo.getIndicadorAutorizacao() == 1){
				
				situacaoBatch = ProcessoSituacao.AGUARDANDO_AUTORIZACAO;
			} 
		
		}
		
		return situacaoBatch;
	}
	
	/**
	 * @author Rodrigo Cabral
	 * @date 22/08/2011
	 * 
	 * Verifica se o Batch ja foi autorizado
	 */
	public Integer verificarAutorizacaoRelatorioBatch(Integer processoId){
		
		
		
		FiltroProcesso filtroProcesso = new FiltroProcesso();
		filtroProcesso.adicionarParametro(new ParametroSimples(
				FiltroProcesso.ID, processoId));
		
		Collection colecaoProcesso = Fachada.getInstancia().pesquisar(filtroProcesso, Processo.class.getName());
		
		Processo processo = (Processo) Util.retonarObjetoDeColecao(colecaoProcesso);
		
		Integer	situacaoBatch = FuncionalidadeSituacao.AGENDADA;
		
		
		if (processo != null){
		
			if (processo.getIndicadorAutorizacao() == 1){
				
				situacaoBatch = FuncionalidadeSituacao.AGUARDANDO_AUTORIZACAO;
			} 
		
		}
		
		return situacaoBatch;
	}
	
	public FaturamentoAtividadeCronograma pesquisarProcessoIniciadoParaGrupo(Integer idGrupo, Integer referencia, Integer idAtividadeFaturamento) throws ControladorException {
		FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
	
		try {
	
			faturamentoAtividadeCronograma = this.repositorioBatch.pesquisarProcessoIniciadoParaGrupo(idGrupo, referencia, idAtividadeFaturamento);
	
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	
		return faturamentoAtividadeCronograma;
	}
	
	public String getIpNovoBatch() {
		StringBuilder caminhoCompleto = new StringBuilder();
		
		FiltroSegurancaParametro filtroSegurancaParametro = new FiltroSegurancaParametro();
		filtroSegurancaParametro.adicionarParametro(new ParametroSimples(FiltroSegurancaParametro.NOME, SegurancaParametro.NOME_PARAMETRO_SEGURANCA.IP_NOVO_BATCH.toString()));

		Collection parametros = Fachada.getInstancia().pesquisar(filtroSegurancaParametro, SegurancaParametro.class.getName());

		 return ((SegurancaParametro) parametros.iterator().next()).getValor();
	}

}
