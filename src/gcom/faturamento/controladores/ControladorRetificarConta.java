package gcom.faturamento.controladores;

import gcom.arrecadacao.IRepositorioArrecadacao;
import gcom.arrecadacao.RepositorioArrecadacaoHBM;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.IClienteConta;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.FiltroEmpresaCobrancaConta;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.RetificarConjuntoContaConsumosHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.conta.UC0146ManterConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarHistorico;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.jboss.logging.Logger;

public class ControladorRetificarConta extends ControladorComum {

	private static final long serialVersionUID = 4498794060506412760L;

	private static Logger logger = Logger.getLogger(ControladorRetificarConta.class);

	protected IRepositorioFaturamento repositorioFaturamento;
	protected IRepositorioMicromedicao repositorioMicromedicao;
	protected IRepositorioArrecadacao repositorioArrecadacao;
	protected IRepositorioImovel repositorioImovel;
	protected IRepositorioCobranca repositorioCobranca;
	
	public void ejbCreate() throws CreateException {
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
	}

	@SuppressWarnings("rawtypes")
	public Integer retificarConta(Integer mesAnoConta, Conta contaAtual, Imovel imovel, Collection colecaoDebitoCobrado, Collection colecaoCreditoRealizado,
			LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Collection colecaoCategoria, String consumoAgua,
			String consumoEsgoto, String percentualEsgoto, Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap, Usuario usuarioLogado, String consumoTarifa,
			boolean atualizarMediaConsumoHistorico, Integer leituraAnterior, Integer leituraAtual, boolean atualizarLeituraAnteriorEAtualConta,
			String retornoBotaoVoltar, Integer leituraAnteriorPoco, Integer leituraAtualPoco, Integer volumePoco, BigDecimal percentualColeta,
			Integer consumoMedidoProporcional) throws ControladorException {

		try {
			logger.info("Retificacao da conta do imovel: " + imovel.getId());

			validarContaParaRetificacao(contaAtual, imovel, dataVencimentoConta);

			// Detalhe: Essa alteração só irá ser realizada quando chamada da
			// tela de retificar conta.
			// Caso o consumo de agua tenha sido alterado e a situação da
			// ligação de água do imovel
			// ( a nova, caso tenha sido alterada) determine faturamento
			atualizarMediaConsumoHistoricoAoRetificarConta(mesAnoConta, contaAtual, consumoAgua, atualizarMediaConsumoHistorico);

			// Atualização da leitura atual faturada, se o motivo da retificação
			// for ALTERAÇÃO DA LEITURA FATURADA
			if (contaMotivoRetificacao != null && contaMotivoRetificacao.getId().equals(ContaMotivoRetificacao.ALTERACAO_DE_LEITURA_FATURADA)) {

				boolean imovelHidrometrado = repositorioMicromedicao.verificaExistenciaHidrometro(contaAtual.getImovel().getId());

				logger.info("Motivo da retificação: ALTERAÇÃO DA LEITURA FATURADA");
				logger.info("Imóvel: " + imovel.getId());

				if (imovelHidrometrado) {

					if (leituraAtual == null || leituraAnterior == null) {
						throw new ControladorException("atencao.leitura_atual_nao_pode_ser_nula", "exibirRetificarContaAction.do?contaID=" + contaAtual.getId()
								+ "&idImovel=" + imovel.getId(), null);
					} else {
						if (!leituraAtual.equals(contaAtual.getNumeroLeituraAtual())) {

							validarLeiturasRetificarConta(contaAtual, imovel, leituraAtual, consumoMedidoProporcional);

						}
					}

				}
			}

			validarRetificarContasPrescritas(contaAtual, imovel);

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_CONTA_RETIFICAR, contaAtual.getImovel().getId(),
					contaAtual.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(contaAtual);

			DebitoCreditoSituacao debitoCreditoSituacaoAnteriorNaoAlterado = contaAtual.getDebitoCreditoSituacaoAnterior();
			DebitoCreditoSituacao debitoCreditoSituacaoAtualNaoAlterado = contaAtual.getDebitoCreditoSituacaoAtual();
			Integer referenciaContabilNaoAlterado = contaAtual.getReferenciaContabil();
			Usuario usuarioNaoAlterado = contaAtual.getUsuario();

			// [SF002] - Gerar dados da conta
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			Integer idContaGerada = null;

			if (isRetificarContaReferenciaContabilMenor(contaAtual, sistemaParametro)) {

				idContaGerada = retificarContasReferenciaContabilMenor(mesAnoConta, contaAtual, imovel, colecaoDebitoCobrado, colecaoCreditoRealizado,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, colecaoCategoria, consumoAgua, consumoEsgoto, percentualEsgoto, dataVencimentoConta,
						calcularValoresConta, contaMotivoRetificacao, requestMap, usuarioLogado, consumoTarifa, leituraAnterior, leituraAtual,
						atualizarLeituraAnteriorEAtualConta, retornoBotaoVoltar, leituraAnteriorPoco, leituraAtualPoco, volumePoco, percentualColeta,
						consumoMedidoProporcional, registradorOperacao, debitoCreditoSituacaoAnteriorNaoAlterado, debitoCreditoSituacaoAtualNaoAlterado,
						referenciaContabilNaoAlterado, usuarioNaoAlterado, sistemaParametro);

			} else if (isRetificarContaReferenciaContabilMaiorOuIgual(contaAtual, sistemaParametro)) {

				idContaGerada = retificarContasReferenciaContabilMaiorOuIgual(mesAnoConta, contaAtual, imovel, colecaoDebitoCobrado, colecaoCreditoRealizado,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, colecaoCategoria, consumoAgua, consumoEsgoto, percentualEsgoto, dataVencimentoConta,
						calcularValoresConta, contaMotivoRetificacao, requestMap, usuarioLogado, leituraAnterior, leituraAtual,
						atualizarLeituraAnteriorEAtualConta, retornoBotaoVoltar, leituraAnteriorPoco, leituraAtualPoco, volumePoco, percentualColeta,
						consumoMedidoProporcional, debitoCreditoSituacaoAnteriorNaoAlterado, debitoCreditoSituacaoAtualNaoAlterado,
						referenciaContabilNaoAlterado, usuarioNaoAlterado, sistemaParametro);
			} else {

				if (atualizarLeituraAnteriorEAtualConta) {
					getControladorFaturamento().verificarValoresLeituraAnteriorEAtual(leituraAnterior, leituraAtual, contaAtual.getConsumoAgua());
				}
				idContaGerada = contaAtual.getId();
			}

			return idContaGerada;

		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw e;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	private void validarLeiturasRetificarConta(Conta contaAtual, Imovel imovel, Integer leituraAtual, Integer consumoMedido) throws ControladorException, ErroRepositorioException {
		// Essa verificação será válida ate que seja analisado o motivo dessa
		// diferença entre as leituras faturadas e as leituras nas contas
		Object[] leiturasMedicao = getControladorMicromedicao().obterLeituraAnteriorEAtualFaturamentoMedicaoHistorico(imovel.getId(),
				contaAtual.getReferencia());

		Integer leituraAtualMedicao = (Integer) leiturasMedicao[1];

		if ((leituraAtualMedicao != null && leituraAtualMedicao.equals(contaAtual.getNumeroLeituraAtual()) || (leituraAtualMedicao == null && contaAtual
				.getNumeroLeituraAtual() == null))) {

			// Verifica se houve alteração nas leituras, se sim verifica se já
			// tem rota gerada para próxima referência, se tiver não deixa fazer
			// a alteração
			boolean leituraAlterada = false;

			leituraAlterada = getControladorMicromedicao().verificarLeituraAtualFaturadaImovel(leituraAtual, contaAtual.getReferencia(), imovel.getId());

			if (leituraAlterada) {
				FaturamentoGrupo grupo = repositorioImovel.pesquisarGrupoImovel(imovel.getId());

				boolean arquivoProximaReferenciaGerado = getControladorMicromedicao().pesquisaArquivoRotaPorGrupoEReferencia(grupo.getId(),
						contaAtual.getReferencia());

				if (!arquivoProximaReferenciaGerado)
					corrigirLeiturasEConsumos(leituraAtual, contaAtual, consumoMedido);
				else {
					throw new ControladorException("atencao.rota_proxima_referencia_gerada", "exibirRetificarContaAction.do?contaID=" + contaAtual.getId()
							+ "&idImovel=" + imovel.getId(), null);
				}
			}
		} else {
			throw new ControladorException("atencao.diferenca_entre_leituras_faturadas_e_leituras_conta", "exibirRetificarContaAction.do?contaID="
					+ contaAtual.getId() + "&idImovel=" + imovel.getId(), null);
		}
	}

	private void corrigirLeiturasEConsumos(Integer leituraAtual, Conta conta, Integer consumo) throws ControladorException {
		getControladorMicromedicao().atualizarLeituraRetificarConta(leituraAtual, conta.getReferencia(), conta.getImovel().getId());
		this.corrigirConsumos(conta, consumo);
	}
	
	private void corrigirConsumos(Conta conta, Integer consumo) throws ControladorException {
		
		ConsumoHistorico consumoHistorico = obterConsumoHistorico(conta);
		consumoHistorico.setNumeroConsumoFaturadoMes(consumo);
		consumoHistorico.setUltimaAlteracao(new Date());
		
		getControladorUtil().atualizar(consumoHistorico);
	}
	
	@SuppressWarnings("rawtypes")
	private ConsumoHistorico obterConsumoHistorico(Conta conta) throws ControladorException {
		FiltroConsumoHistorico filtro = new FiltroConsumoHistorico();
				
		filtro.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID, conta.getImovel().getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.LIGACAO_TIPO_ID,LigacaoTipo.LIGACAO_AGUA));
		filtro.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.ANO_MES_FATURAMENTO,conta.getAnoMesReferenciaConta()));

		Collection pesquisaConsumo = new ArrayList();
		pesquisaConsumo = getControladorUtil().pesquisar(filtro, ConsumoHistorico.class.getName());

		if (!pesquisaConsumo.isEmpty()) {
			return (ConsumoHistorico) pesquisaConsumo.iterator().next();
		} else {
			return null;
		}
	}
	
	private boolean isRetificarContaReferenciaContabilMaiorOuIgual(Conta contaAtual, SistemaParametro sistemaParametro) {
		return (contaAtual.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.INCLUIDA) || contaAtual.getDebitoCreditoSituacaoAtual().getId()
				.equals(DebitoCreditoSituacao.RETIFICADA))
				&& (Util.compararAnoMesReferencia(contaAtual.getReferenciaContabil(), sistemaParametro.getAnoMesFaturamento(), ">") || Util
						.compararAnoMesReferencia(contaAtual.getReferenciaContabil(), sistemaParametro.getAnoMesFaturamento(), "="));
	}

	private boolean isRetificarContaReferenciaContabilMenor(Conta contaAtual, SistemaParametro sistemaParametro) {
		return (contaAtual.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.NORMAL))
				|| ((contaAtual.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.INCLUIDA) || contaAtual.getDebitoCreditoSituacaoAtual()
						.getId().equals(DebitoCreditoSituacao.RETIFICADA)) && Util.compararAnoMesReferencia(contaAtual.getReferenciaContabil(),
						sistemaParametro.getAnoMesFaturamento(), "<"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Integer retificarContasReferenciaContabilMaiorOuIgual(Integer mesAnoConta, Conta contaAtual, Imovel imovel, Collection colecaoDebitoCobrado,
			Collection colecaoCreditoRealizado, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto, Date dataVencimentoConta,
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta, ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap,
			Usuario usuarioLogado, Integer leituraAnterior, Integer leituraAtual, boolean atualizarLeituraAnteriorEAtualConta, String retornoBotaoVoltar,
			Integer leituraAnteriorPoco, Integer leituraAtualPoco, Integer volumePoco, BigDecimal percentualColeta, Integer consumoMedidoProporcional,
			DebitoCreditoSituacao debitoCreditoSituacaoAnteriorNaoAlterado, DebitoCreditoSituacao debitoCreditoSituacaoAtualNaoAlterado,
			Integer referenciaContabilNaoAlterado, Usuario usuarioNaoAlterado, SistemaParametro sistemaParametro) throws ControladorException {
		Integer idContaGerada;
		contaAtual.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		contaAtual.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

		if (!contaMotivoRetificacao.getId().equals(ContaMotivoRetificacao.DEVOLUCAO_PAGAMENTO_CREDITADO_EM_CONTA)
				&& Util.getAno(dataVencimentoConta) < Util.obterAno(sistemaParametro.getAnoMesFaturamento())) {

			sessionContext.setRollbackOnly();

			contaAtual.setDebitoCreditoSituacaoAnterior(debitoCreditoSituacaoAnteriorNaoAlterado);
			contaAtual.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtualNaoAlterado);
			contaAtual.setReferenciaContabil(referenciaContabilNaoAlterado);
			contaAtual.setUsuario(usuarioNaoAlterado);

			throw new ControladorException("atencao.data_vencimento_conta_inferior_ano_faturamento");
		}

		contaAtual.setDataVencimentoConta(dataVencimentoConta);

		Integer consumoTotalAgua = getControladorFaturamento().calcularConsumoTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_AGUA);

		if (consumoTotalAgua.equals(new Integer("0"))) {
			contaAtual.atribuirConsumoAgua(consumoAgua);
		} else {
			contaAtual.setConsumoAgua(consumoTotalAgua);
		}

		if (atualizarLeituraAnteriorEAtualConta) {
			getControladorFaturamento().verificarValoresLeituraAnteriorEAtual(leituraAnterior, leituraAtual, retornoBotaoVoltar, contaAtual.getId(),
					imovel.getId(), contaAtual.getConsumoAgua(), consumoMedidoProporcional);

			contaAtual.setNumeroLeituraAnterior(leituraAnterior);
			contaAtual.setNumeroLeituraAtual(leituraAtual);
		}

		Integer consumoTotalEsgoto = getControladorFaturamento().calcularConsumoTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_ESGOTO);

		if (consumoTotalEsgoto.equals(new Integer("0"))) {
			contaAtual.atribuirConsumoEsgoto(consumoEsgoto);
		} else {
			contaAtual.setConsumoEsgoto(consumoTotalEsgoto);
		}

		BigDecimal valorTotalAgua = getControladorFaturamento().calcularValorTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_AGUA);
		contaAtual.setValorAgua(valorTotalAgua);

		BigDecimal valorTotalEsgoto = getControladorFaturamento().calcularValorTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_ESGOTO);
		contaAtual.setValorEsgoto(valorTotalEsgoto);

		contaAtual.atribuiValorRateioAgua(contaAtual.getValorRateioAgua());
		contaAtual.atribuiValorRateioEsgoto(contaAtual.getValorRateioEsgoto());
		
		BigDecimal valorTotalDebito = null;
		BigDecimal valorTotalCredito = null;

		if (requestMap != null) {
			valorTotalDebito = getControladorFaturamento().calcularValorTotalDebitoConta(colecaoDebitoCobrado, requestMap);
			valorTotalCredito = getControladorFaturamento().calcularValorTotalCreditoConta(colecaoCreditoRealizado, requestMap);
		} else {
			valorTotalDebito = getControladorFaturamento().calcularValorTotalDebitoConta(colecaoDebitoCobrado);
			valorTotalCredito = getControladorFaturamento().calcularValorTotalCreditoConta(colecaoCreditoRealizado);
		}

		contaAtual.setDebitos(valorTotalDebito);
		contaAtual.setValorCreditos(valorTotalCredito);
		contaAtual.atribuiPercentualEsgoto(percentualEsgoto);

		GerarImpostosDeduzidosContaHelper impostosDeduzidosConta = getControladorFaturamento().gerarImpostosDeduzidosConta(contaAtual.getImovel().getId(),
				mesAnoConta, valorTotalAgua, valorTotalEsgoto, valorTotalDebito, valorTotalCredito, false);

		contaAtual.setValorImposto(impostosDeduzidosConta.getValorTotalImposto());

		Date dataValidadeConta = getControladorFaturamento().retornaDataValidadeConta(dataVencimentoConta);
		contaAtual.setDataValidadeConta(dataValidadeConta);

		Calendar dataRetificacaoConta = new GregorianCalendar();
		contaAtual.setDataRetificacao(dataRetificacaoConta.getTime());
		contaAtual.setContaMotivoRetificacao(contaMotivoRetificacao);
		contaAtual.setUsuario(usuarioLogado);
		contaAtual.setUltimaAlteracao(new Date());
		contaAtual.setNumeroLeituraAnteriorPoco(leituraAnteriorPoco);
		contaAtual.setNumeroLeituraAtualPoco(leituraAtualPoco);
		contaAtual.setNumeroVolumePoco(volumePoco);
		contaAtual.setPercentualColeta(percentualColeta);
		contaAtual.incrementaNumeroRetificacoes();

		try {
			Collection colecaoContaCategoria = getControladorFaturamento().montarColecaoContaCategoria(colecaoCategoria, contaAtual);

			contaAtual.setContaCategorias(new HashSet(colecaoContaCategoria));
			contaAtual.setDebitoCobrados(new HashSet(colecaoDebitoCobrado));
			contaAtual.setCreditoRealizados(new HashSet(colecaoCreditoRealizado));

			getControladorTransacao().registrarTransacao(contaAtual);
			contaAtual.setIdAntigo(contaAtual.getId());

			contaAtual.setNumeroBoleto(getControladorFaturamento().verificarGeracaoBoleto(sistemaParametro, contaAtual));

			repositorioFaturamento.retificarContaAtualizarValores(contaAtual);
			repositorioFaturamento.removerContaCategoria(contaAtual);
			repositorioFaturamento.removerDebitoCobrado(contaAtual);
			repositorioFaturamento.removerCreditoRealizado(contaAtual);
			repositorioFaturamento.removerClientesConta(contaAtual.getId());
			repositorioFaturamento.removerImpostosDeduzidosConta(contaAtual.getId());

			boolean temPermissaoRetificarContaSemRA = getControladorPermissaoEspecial().verificarPermissaoRetificarContaSemRA(usuarioLogado);
			if (!temPermissaoRetificarContaSemRA) {
				RegistroAtendimento raParaRetificacao = getControladorRegistroAtendimento().verificarExistenciaRegistroAtendimento(
						new Integer(imovel.getId()), "atencao.conta_existencia_registro_atendimento", EspecificacaoTipoValidacao.ALTERACAO_CONTA);
				
				repositorioFaturamento.atualizarContaCanceladaOuRetificada(contaAtual, raParaRetificacao);
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			contaAtual.setDebitoCreditoSituacaoAnterior(debitoCreditoSituacaoAnteriorNaoAlterado);
			contaAtual.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtualNaoAlterado);
			contaAtual.setReferenciaContabil(referenciaContabilNaoAlterado);
			contaAtual.setUsuario(usuarioNaoAlterado);

			throw new ControladorException("erro.sistema", ex);
		}

		if (calcularValoresConta != null && !calcularValoresConta.isEmpty()) {
			CalcularValoresAguaEsgotoHelper valorConta = calcularValoresConta.iterator().next();
			valorConta.adicionaRateioAgua(contaAtual.getValorRateioAgua());
			valorConta.adicionaRateioEsgoto(contaAtual.getValorRateioEsgoto());
		}

		getControladorFaturamento().inserirContaCategoria(calcularValoresConta, colecaoCategoria, contaAtual);
		getControladorFaturamento().inserirDebitoCobrado(contaAtual, colecaoDebitoCobrado, imovel, colecaoCategoria);
		getControladorFaturamento().inserirCreditoRealizado(contaAtual, colecaoCreditoRealizado, imovel, colecaoCategoria);
		getControladorFaturamento().inserirClienteImovel(imovel, contaAtual);
		getControladorFaturamento().inserirImpostosDeduzidosConta(impostosDeduzidosConta, contaAtual);

		atualizarFaturaItemContaRetificada(contaAtual.getId(), contaAtual);

		getControladorSpcSerasa().atualizarNegativadorMovimentoRegItemAPartirConta(contaAtual);
		
		return contaAtual.getId();
	}

	public void encerrarRA(Integer idImovel, Usuario usuario) throws ControladorException {
		RegistroAtendimento ra = getControladorRegistroAtendimento().verificarExistenciaRegistroAtendimentoSemLevantarExcecao(
				idImovel, EspecificacaoTipoValidacao.ALTERACAO_CONTA);
		
		if (ra != null) {
			ra.setDataEncerramento(new Date());
			ra.setParecerEncerramento("ENCERRAMENTO AUTOMÁTICO POR RETIFICAÇÃO DE CONTA.");
			ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
			ra.setAtendimentoMotivoEncerramento(new AtendimentoMotivoEncerramento(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO));
			ra.setUltimaAlteracao(new Date());
			
			getControladorUtil().atualizar(ra);
			
			RegistroAtendimentoUnidade raUnidade = new RegistroAtendimentoUnidade(
					new Date(),
					usuario, 
					ra, 
					usuario.getUnidadeOrganizacional(), 
					new AtendimentoRelacaoTipo(AtendimentoRelacaoTipo.ENCERRAR));
			
			getControladorUtil().inserir(raUnidade);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Integer retificarContasReferenciaContabilMenor(Integer mesAnoConta, Conta contaAtual, Imovel imovel, Collection colecaoDebitoCobrado,
			Collection colecaoCreditoRealizado, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto, Date dataVencimentoConta,
			Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta, ContaMotivoRetificacao contaMotivoRetificacao, Map<String, String[]> requestMap,
			Usuario usuarioLogado, String consumoTarifa, Integer leituraAnterior, Integer leituraAtual, boolean atualizarLeituraAnteriorEAtualConta,
			String retornoBotaoVoltar, Integer leituraAnteriorPoco, Integer leituraAtualPoco, Integer volumePoco, BigDecimal percentualColeta,
			Integer consumoMedidoProporcional, RegistradorOperacao registradorOperacao, DebitoCreditoSituacao debitoCreditoSituacaoAnteriorNaoAlterado,
			DebitoCreditoSituacao debitoCreditoSituacaoAtualNaoAlterado, Integer referenciaContabilNaoAlterado, Usuario usuarioNaoAlterado,
			SistemaParametro sistemaParametro) throws ControladorException {
		Integer idContaGerada;
		if (contaAtual.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.NORMAL)
				&& (Util.compararAnoMesReferencia(contaAtual.getReferencia(), sistemaParametro.getAnoMesFaturamento(), "=") || (Util.compararAnoMesReferencia(
						contaAtual.getReferencia(), sistemaParametro.getAnoMesFaturamento(), ">")))) {

			contaAtual.setDebitoCreditoSituacaoAnterior(contaAtual.getDebitoCreditoSituacaoAtual());
		} else {
			contaAtual.setDebitoCreditoSituacaoAnterior(null);
		}

		DebitoCreditoSituacao debitoCreditoSituacaoAtualAtualizacao = new DebitoCreditoSituacao(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO);
		contaAtual.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtualAtualizacao);

		Integer referenciaContabil = getControladorFaturamento().obterReferenciaContabilConta(sistemaParametro, contaAtual.getReferencia());
		contaAtual.setReferenciaContabil(referenciaContabil);

		contaAtual.setUsuario(usuarioLogado);
		contaAtual.setUltimaAlteracao(new Date());

		try {
			boolean temPermissaoRetificarContaSemRA = getControladorPermissaoEspecial().verificarPermissaoRetificarContaSemRA(usuarioLogado);
			if (!temPermissaoRetificarContaSemRA) {
				RegistroAtendimento raParaRetificacao = getControladorRegistroAtendimento().verificarExistenciaRegistroAtendimento(
						new Integer(imovel.getId()), "atencao.conta_existencia_registro_atendimento", EspecificacaoTipoValidacao.ALTERACAO_CONTA);
				
				repositorioFaturamento.atualizarContaCanceladaOuRetificada(contaAtual, raParaRetificacao);
			}

			logger.info("Antes da Atualização no banco ");
			logger.info("Imóvel: " + imovel.getId());
			logger.info("Referência da conta: " + contaAtual.getReferencia());
			logger.info("Situação anterior da conta : "
					+ (contaAtual.getDebitoCreditoSituacaoAnterior() != null ? contaAtual.getDebitoCreditoSituacaoAnterior().getId() : ""));
			logger.info("Situação atual da conta : "
					+ (contaAtual.getDebitoCreditoSituacaoAtual() != null ? contaAtual.getDebitoCreditoSituacaoAtual().getId() : ""));

			if (contaAtual.getDebitoCreditoSituacaoAnterior() == null) {
				repositorioFaturamento.retificarContaAtualizarSituacao(contaAtual, null);
			} else {
				repositorioFaturamento.retificarContaAtualizarSituacao(contaAtual, contaAtual.getDebitoCreditoSituacaoAnterior().getId());
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();

			contaAtual.setDebitoCreditoSituacaoAnterior(debitoCreditoSituacaoAnteriorNaoAlterado);
			contaAtual.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtualNaoAlterado);
			contaAtual.setReferenciaContabil(referenciaContabilNaoAlterado);
			contaAtual.setUsuario(usuarioNaoAlterado);

			throw new ControladorException("erro.sistema", ex);
		}

		ContaGeral contaGeralInserir = new ContaGeral();

		Short indicadorHistorico = 2;

		contaGeralInserir.setIndicadorHistorico(indicadorHistorico);
		contaGeralInserir.setUltimaAlteracao(new Date());

		Integer idGerado = (Integer) this.getControladorUtil().inserir(contaGeralInserir);

		contaGeralInserir.setId(idGerado);

		Conta contaInserir = new Conta();

		contaInserir.setIdAntigo(contaAtual.getId());
		registradorOperacao.registrarOperacao(contaInserir);

		contaInserir.setImovel(contaAtual.getImovel());
		contaInserir.setContaGeral(contaGeralInserir);
		contaInserir.setId(idGerado);
		contaInserir.setReferencia(mesAnoConta.intValue());
		contaInserir.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.RETIFICADA));
		contaInserir.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		contaInserir.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
		contaInserir.setLocalidade(contaAtual.getLocalidade());
		contaInserir.setQuadraConta(contaAtual.getQuadraConta());
		contaInserir.setLote(contaAtual.getLote());
		contaInserir.setSubLote(contaAtual.getSubLote());
		contaInserir.setCodigoSetorComercial(contaAtual.getQuadraConta().getSetorComercial().getCodigo());
		contaInserir.setQuadra(new Integer(contaAtual.getQuadraConta().getNumeroQuadra()));
		contaInserir.setDigitoVerificadorConta(new Short(String.valueOf(Util.calculoRepresentacaoNumericaCodigoBarrasModulo10(mesAnoConta))));
		contaInserir.setIndicadorCobrancaMulta(contaAtual.getIndicadorCobrancaMulta());
		contaInserir.setDataVencimentoConta(dataVencimentoConta);
		contaInserir.setDataVencimentoOriginal(contaAtual.getDataVencimentoOriginal());
		contaInserir.setIndicadorAlteracaoVencimento(new Short("2"));

		Integer consumoTotalAgua = getControladorFaturamento().calcularConsumoTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_AGUA);

		if (consumoTotalAgua.equals(new Integer("0"))) {
			contaInserir.atribuirConsumoAgua(consumoAgua);
		} else {
			contaInserir.setConsumoAgua(consumoTotalAgua);
		}

		contaInserir.atribuirConsumoRateioAgua(contaAtual.getConsumoRateioAgua());

		contaInserir.atribuirConsumoRateioEsgoto(contaAtual.getConsumoRateioEsgoto());

		if (atualizarLeituraAnteriorEAtualConta) {
			getControladorFaturamento().verificarValoresLeituraAnteriorEAtual(leituraAnterior, leituraAtual, retornoBotaoVoltar, contaAtual.getId(),
					imovel.getId(), contaInserir.getConsumoAgua(), consumoMedidoProporcional);

			contaInserir.setNumeroLeituraAnterior(leituraAnterior);
			contaInserir.setNumeroLeituraAtual(leituraAtual);

		} else {
			contaInserir.setNumeroLeituraAnterior(contaAtual.getNumeroLeituraAnterior());
			contaInserir.setNumeroLeituraAtual(contaAtual.getNumeroLeituraAtual());
		}

		Integer consumoTotalEsgoto = getControladorFaturamento().calcularConsumoTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_ESGOTO);

		if (consumoTotalEsgoto.equals(new Integer("0"))) {
			contaInserir.atribuirConsumoEsgoto(consumoEsgoto);
		} else {
			contaInserir.setConsumoEsgoto(consumoTotalEsgoto);
		}

		BigDecimal valorTotalAgua = getControladorFaturamento().calcularValorTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_AGUA);
		contaInserir.setValorAgua(valorTotalAgua);

		BigDecimal valorTotalEsgoto = getControladorFaturamento().calcularValorTotalAguaOuEsgotoPorCategoria(calcularValoresConta,
				ConstantesSistema.CALCULAR_ESGOTO);
		contaInserir.setValorEsgoto(valorTotalEsgoto);

		contaInserir.atribuiValorRateioAgua(contaAtual.getValorRateioAgua());

		contaInserir.atribuiValorRateioEsgoto(contaAtual.getValorRateioEsgoto());

		BigDecimal valorTotalDebito = null;
		BigDecimal valorTotalCredito = null;

		if (requestMap != null) {
			valorTotalDebito = getControladorFaturamento().calcularValorTotalDebitoConta(colecaoDebitoCobrado, requestMap);
			valorTotalCredito = getControladorFaturamento().calcularValorTotalCreditoConta(colecaoCreditoRealizado, requestMap);
		} else {
			valorTotalDebito = getControladorFaturamento().calcularValorTotalDebitoConta(colecaoDebitoCobrado);
			valorTotalCredito = getControladorFaturamento().calcularValorTotalCreditoConta(colecaoCreditoRealizado);
		}

		contaInserir.setDebitos(valorTotalDebito);
		contaInserir.setValorCreditos(valorTotalCredito);
		contaInserir.atribuiPercentualEsgoto(percentualEsgoto);
		GerarImpostosDeduzidosContaHelper impostosDeduzidosConta = getControladorFaturamento().gerarImpostosDeduzidosConta(contaAtual.getImovel().getId(),
				mesAnoConta, valorTotalAgua, valorTotalEsgoto, valorTotalDebito, valorTotalCredito, false);

		contaInserir.setValorImposto(impostosDeduzidosConta.getValorTotalImposto());
		contaInserir.setDataValidadeConta(getControladorFaturamento().retornaDataValidadeConta(dataVencimentoConta));
		contaInserir.setDataRetificacao((new GregorianCalendar()).getTime());
		contaInserir.setDataEmissao((new GregorianCalendar()).getTime());
		contaInserir.setReferenciaContabil(referenciaContabil);
		contaInserir.setContaMotivoRetificacao(contaMotivoRetificacao);
		contaInserir.setConsumoTarifa(new ConsumoTarifa(Integer.parseInt(consumoTarifa)));
		contaInserir.setImovelPerfil(contaAtual.getImovelPerfil());
		contaInserir.setIndicadorDebitoConta(contaAtual.getIndicadorDebitoConta());

		FiltroEmpresaCobrancaConta filtroEmpresaCobrancaConta = new FiltroEmpresaCobrancaConta();
		filtroEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaConta.CONTA_ID, contaAtual.getId()));

		Collection colecaoEmpresaConta = getControladorUtil().pesquisar(filtroEmpresaCobrancaConta, EmpresaCobrancaConta.class.getName());

		if (colecaoEmpresaConta != null && !colecaoEmpresaConta.isEmpty()) {
			EmpresaCobrancaConta empresaCobrancaConta = (EmpresaCobrancaConta) Util.retonarObjetoDeColecao(colecaoEmpresaConta);
			empresaCobrancaConta.setContaGeral(contaGeralInserir);

			getControladorUtil().atualizar(empresaCobrancaConta);
		}

		contaInserir.setUsuario(usuarioLogado);

		contaInserir.incrementaNumeroRetificacoes();
		contaInserir.setFaturamentoGrupo(contaAtual.getFaturamentoGrupo());
		contaInserir.setRota(contaAtual.getRota());
		contaInserir.setNumeroFatura(null);
		contaInserir.setAnoMesReferenciaBaixaSocial(null);
		contaInserir.setNumeroAlteracoesVencimento(new Integer(0));
		contaInserir.setUltimaAlteracao(new Date());
		contaInserir.setNumeroLeituraAnteriorPoco(leituraAnteriorPoco);
		contaInserir.setNumeroLeituraAtualPoco(leituraAtualPoco);
		contaInserir.setNumeroVolumePoco(volumePoco);
		contaInserir.setPercentualColeta(percentualColeta);

		Collection colecaoContaCategoria = getControladorFaturamento().montarColecaoContaCategoria(colecaoCategoria, contaInserir);

		contaInserir.setContaCategorias(new HashSet(colecaoContaCategoria));
		contaInserir.setDebitoCobrados(new HashSet(colecaoDebitoCobrado));
		contaInserir.setCreditoRealizados(new HashSet(colecaoCreditoRealizado));
		contaInserir.setNumeroBoleto(getControladorFaturamento().verificarGeracaoBoleto(sistemaParametro, contaInserir));

		Integer idContaGerado = (Integer) this.getControladorUtil().inserir(contaInserir);

		idContaGerada = idContaGerado;

		this.inserirClienteContaRetificacao(contaInserir, contaAtual.getId());

		if (calcularValoresConta != null && !calcularValoresConta.isEmpty()) {
			CalcularValoresAguaEsgotoHelper valorConta = calcularValoresConta.iterator().next();
			valorConta.adicionaRateioAgua(contaAtual.getValorRateioAgua());
			valorConta.adicionaRateioEsgoto(contaAtual.getValorRateioEsgoto());
		}

		getControladorFaturamento().inserirContaCategoria(calcularValoresConta, colecaoCategoria, contaInserir);
		getControladorFaturamento().inserirDebitoCobrado(contaInserir, colecaoDebitoCobrado, imovel, colecaoCategoria);
		getControladorFaturamento().inserirCreditoRealizado(contaInserir, colecaoCreditoRealizado, imovel, colecaoCategoria);
		getControladorFaturamento().inserirImpostosDeduzidosConta(impostosDeduzidosConta, contaInserir);

		this.atualizarPagamentoContaRetificada(contaAtual.getId(), idContaGerado);
		this.atualizarDebitoAutomaticoMovimentoContaRetificada(contaAtual.getId(), idContaGerado, imovel);
		this.atualizarFaturaItemContaRetificada(contaAtual.getId(), contaInserir);

		getControladorSpcSerasa().atualizarNegativadorMovimentoRegItemAPartirConta(contaInserir);
		
		return idContaGerada;
	}

	private void validarRetificarContasPrescritas(Conta contaAtual, Imovel imovel) throws ControladorException {
		if (contaAtual.getDebitoCreditoSituacaoAtual().equals(DebitoCreditoSituacao.DEBITO_PRESCRITO)
				|| contaAtual.getDebitoCreditoSituacaoAtual().equals(DebitoCreditoSituacao.DEBITO_PRESCRITO_CONTAS_INCLUIDAS)) {
			throw new ControladorException("atencao.contas_prescritas_nao_podem_ser_retificadas", "exibirRetificarContaAction.do?contaID=" + contaAtual.getId()
					+ "&idImovel=" + imovel.getId(), null);
		}
	}

	@SuppressWarnings("rawtypes")
	private void atualizarMediaConsumoHistoricoAoRetificarConta(Integer mesAnoConta, Conta contaAtual, String consumoAgua,
			boolean atualizarMediaConsumoHistorico) throws ControladorException {

		if (atualizarMediaConsumoHistorico) {

			Collection colConsumos = getControladorMicromedicao().pesquisaConsumoHistoricoSubstituirConsumo(contaAtual.getImovel().getId());

			if (colConsumos != null) {
				Iterator iteConsumos = colConsumos.iterator();

				while (iteConsumos.hasNext()) {
					ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iteConsumos.next();

					if (imovelMicromedicao.getConsumoHistorico().getReferenciaFaturamento() == contaAtual.getReferencia()) {
						imovelMicromedicao.getConsumoHistorico().setNumeroConsumoCalculoMedia(Integer.parseInt(consumoAgua));
						getControladorMicromedicao().atualizarConsumosAnteriores(imovelMicromedicao.getConsumoHistorico());

						SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
						sistemaParametro.setAnoMesFaturamento(imovelMicromedicao.getAnoMesGrupoFaturamento());

						MedicaoTipo medicaoTipo = new MedicaoTipo(MedicaoTipo.LIGACAO_AGUA);

						boolean houveIntslacaoHidrometro = this.getControladorMicromedicao().verificarInstalacaoSubstituicaoHidrometro(
								contaAtual.getImovel().getId(), medicaoTipo);

						int[] consumoMedioHidrometroAgua = getControladorMicromedicao().obterVolumeMedioAguaEsgoto(contaAtual.getImovel().getId(),
								sistemaParametro.getAnoMesFaturamento(), medicaoTipo.getId(), houveIntslacaoHidrometro);

						medicaoTipo.setId(MedicaoTipo.POCO);
						int[] consumoMedioHidrometroEsgoto = getControladorMicromedicao().obterVolumeMedioAguaEsgoto(contaAtual.getImovel().getId(),
								sistemaParametro.getAnoMesFaturamento(), medicaoTipo.getId(), houveIntslacaoHidrometro);

						getControladorMicromedicao().atualizarConsumosMedio(contaAtual.getImovel().getId(), mesAnoConta, consumoMedioHidrometroAgua[0],
								consumoMedioHidrometroEsgoto[0]);

						break;
					}
				}
			}
		}
	}

	private void validarContaParaRetificacao(Conta contaAtual, Imovel imovel, Date dataVencimentoConta) throws ControladorException {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String dtVencimentoConta = df.format(dataVencimentoConta);
		String dtAtual = df.format(new Date());
		if (dataVencimentoConta != null && !dataVencimentoConta.equals(contaAtual.getDataVencimentoConta()) && !dtVencimentoConta.equals(dtAtual)
				&& dataVencimentoConta.before(new Date())) {
			throw new ControladorException("atencao.data_menor_atual", "exibirRetificarContaAction.do?contaID=" + contaAtual.getId() + "&idImovel="
					+ imovel.getId(), null);
		}

		validarRetificarContasPrescritas(contaAtual, imovel);

		ContaMotivoRevisao contaMotivoRevisaoAtual = null;

		try {
			contaMotivoRevisaoAtual = this.repositorioFaturamento.pesquisarContaMotivoRevisao(contaAtual.getId());
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (contaMotivoRevisaoAtual != null && contaMotivoRevisaoAtual.getId().equals(ContaMotivoRevisao.REVISAO_POR_ANTIGUIDADE)) {
			throw new ControladorException("atencao.conta_revisao_antiguidade");
		}

		if (!this.verificarContaParaRetificacao(contaAtual)) {
			throw new ControladorException("atencao.conta_impressao_simultanea");
		}

		validarRetificarContasPrescritas(contaAtual, imovel);
	}

	public void atualizarFaturaItemContaRetificada(Integer idContaRetificada, Conta contaInserida) throws ControladorException {

		Object[] faturaItem = null;

		try {
			faturaItem = repositorioFaturamento.pesquisarFaturaItemDeConta(idContaRetificada);

			if (faturaItem != null) {

				Integer idFaturaItem = (Integer) faturaItem[0];
				BigDecimal valorContaFaturaItem = (BigDecimal) faturaItem[1];

				Integer consumoFatura = null;

				if (contaInserida.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
					consumoFatura = contaInserida.getConsumoAgua();
				} else if (contaInserida.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)) {
					consumoFatura = contaInserida.getConsumoEsgoto();
				}

				repositorioFaturamento.atualizarContaEmFaturaItem(idFaturaItem, contaInserida, consumoFatura);

				Integer idFatura = (Integer) faturaItem[3];
				BigDecimal valorDebitoFaturaLiquido = (BigDecimal) faturaItem[4];

				BigDecimal valorDebitoFaturaRetificado = valorDebitoFaturaLiquido.subtract(valorContaFaturaItem);
				valorDebitoFaturaRetificado = valorDebitoFaturaRetificado.add(contaInserida.getValorTotal());

				repositorioFaturamento.atualizarValorDebitoFatura(idFatura, valorDebitoFaturaRetificado);

			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", e);
		}

	}

	private void inserirClienteContaRetificacao(Conta contaRetificada, Integer idContaCanceladaPorRetificacao) throws ControladorException {
		Collection<IClienteConta> listaClienteConta = null;
		try {
			listaClienteConta = repositorioFaturamento.pesquisarClienteConta(idContaCanceladaPorRetificacao);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		for (IClienteConta clienteConta : listaClienteConta) {
			ClienteConta clienteContaRetificada = new ClienteConta();
			clienteContaRetificada.setCliente(clienteConta.getCliente());
			clienteContaRetificada.setClienteRelacaoTipo(clienteConta.getClienteRelacaoTipo());
			clienteContaRetificada.setConta(contaRetificada);
			clienteContaRetificada.setIndicadorNomeConta(clienteConta.getIndicadorNomeConta());
			clienteContaRetificada.setUltimaAlteracao(new Date());

			getControladorUtil().inserir(clienteContaRetificada);
		}
	}

	private void atualizarPagamentoContaRetificada(Integer idContaRetificada, Integer idContaInserida) throws ControladorException {

		// Atualizar, caso existam, os pagamentos referentes a
		// conta que foi retificada com o id da nova conta gerada

		Pagamento pagamento = null;

		pagamento = getControladorArrecadacao().pesquisarPagamentoDeConta(idContaRetificada);

		if (pagamento != null) {
			try {
				repositorioArrecadacao.atualizarContaEmPagamento(pagamento.getId(), idContaInserida);
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void atualizarDebitoAutomaticoMovimentoContaRetificada(Integer idContaRetificada, Integer idContaInserida, Imovel imovel)
			throws ControladorException {

		/*
		 * Atualizar, caso o imóvel seja optante de débito automatico
		 * (IMOV_ICDEBITOCONTA = 1 da TABELA IMOVEL)
		 */
		if (imovel != null && imovel.getIndicadorDebitoConta().equals(ConstantesSistema.SIM)) {

			/*
			 * Caso a conta tenha sido gerada como movimento de débito
			 * automático e o movimento não tenha sido enviado para o banco
			 * (CNTA_ID da conta a ser retificada existente na tabela
			 * DEBITO_AUTOMATICO_MOVIMENTO com DAMV_NNNSAENVIO igual a nulo)
			 */
			try {

				Collection colecaoDebitoAutomaticoMovimentoParaAtualizacao = this.repositorioFaturamento
						.pesquisarDebitoAutomaticoMovimentoContaRetificada(idContaRetificada);

				if (colecaoDebitoAutomaticoMovimentoParaAtualizacao != null && !colecaoDebitoAutomaticoMovimentoParaAtualizacao.isEmpty()) {

					this.repositorioFaturamento.atualizarDebitoAutomaticoMovimentoContaRetificada(colecaoDebitoAutomaticoMovimentoParaAtualizacao,
							idContaInserida);
				}

			} catch (ErroRepositorioException ex) {

				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
	}

	public boolean verificarContaParaRetificacao(Conta conta) throws ControladorException {

		boolean contaDisponivel = true;

		try {

			Rota rotaRetificacao = repositorioFaturamento.pesquisarRotaParaRetificacao(conta.getId());

			if (rotaRetificacao != null && conta.getReferencia() == rotaRetificacao.getFaturamentoGrupo().getAnoMesReferencia().intValue()) {

				/*
				 * Caso o arquivo texto da rota do imóvel não esteja finalizado e seja de impressão simultânea (SITL_ID =1, 2, 3 ou 5 e
				 * STCE_ID=2, onde ROTA_ID da tabela ARQUIVO_TEXTO_ROT_EMP seja igual a ROTA_ID da tabela CONTA e CNTA_AMREFERENCIACONTA da
				 * tabela conta seja igual a TXRE_AMREFERENCIA da tabela ARQUIVO_TEXTO_ROT_EMP) não habilitar esta conta para retificação.
				 */
				Integer idArquivoTextoRoteiroEmpresa = repositorioFaturamento.pesquisarArquivoTextoRoteiroEmpresaNaoFinalizado(conta, rotaRetificacao);

				if (idArquivoTextoRoteiroEmpresa != null) {

					contaDisponivel = false;
				}
			}
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		return contaDisponivel;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void retificarConjuntoConta(Collection colecaoImovel, Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao, Collection debitosTipoRetirar,
			Usuario usuarioLogado, Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, String indicadorContaPaga)
			throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();
		List colecaoAuxiliar = new ArrayList();

		colecaoAuxiliar.addAll(colecaoImovel);

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		int i = 0;
		int cont = 500;

		Collection colecao = new ArrayList();
		while (i <= colecaoImovel.size()) {

			if (colecaoImovel.size() - i >= cont) {
				colecao = colecaoAuxiliar.subList(i, i + cont);
			} else {
				colecao = colecaoAuxiliar.subList(i, colecaoImovel.size());
			}
			i = i + cont;
			try {
				colecaoContasManutencao = repositorioFaturamento.obterContasImoveis(anoMes, colecao, dataVencimentoContaInicio, dataVencimentoContaFim,
						anoMesFim, indicadorContaPaga);

				/**
				 * [UC0407] Filtrar Imóveis para Inserir ou Manter Conta 3. Caso o indicador de bloqueio de contas vinculadas a contrato de
				 * parcelamento no manter contas esteja ativo retirar da lista de contas selecionadas as contas vinculadas a algum contrato
				 * de parcelamento ativo
				 * */
				if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
						&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
					colecaoContasManutencao = this.obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(colecaoContasManutencao);
				}

				if (colecaoContasManutencao != null && !colecaoContasManutencao.isEmpty()) {

					Iterator colecaoContasManutencaoIterator = colecaoContasManutencao.iterator();

					while (colecaoContasManutencaoIterator.hasNext()) {

						Object[] contaArray = (Object[]) colecaoContasManutencaoIterator.next();

						Conta conta = (Conta) contaArray[0];

						conta.setUltimaAlteracao(new Date());

						Imovel imovel = (Imovel) contaArray[1];

						Collection colecaoCategoriaOUSubcategoria = null;

						if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)) {

							colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);

						} else {

							colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);

						}

						Collection colecaoCreditoRealizado = getControladorFaturamento().obterCreditosRealizadosConta(conta);

						Collection colecaoDebitoCobrado = getControladorFaturamento().obterDebitosCobradosConta(conta);

						Collection<CalcularValoresAguaEsgotoHelper> valoresConta = getControladorFaturamento().calcularValoresConta(Util.formatarAnoMesParaMesAno(conta.getReferencia()),
								imovel.getId().toString(), conta.getLigacaoAguaSituacao().getId(), conta.getLigacaoEsgotoSituacao().getId(),
								colecaoCategoriaOUSubcategoria, conta.getConsumoAgua().toString(), conta.getConsumoEsgoto().toString(), conta
										.getPercentualEsgoto().toString(), conta.getConsumoTarifa().getId(), usuarioLogado);

						boolean achouDebitoRetirar = false;
						if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()) {
							Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado.iterator();
							while (colecaoDebitoCobradoIterator.hasNext()) {
								DebitoCobrado debitoCobrado = (DebitoCobrado) colecaoDebitoCobradoIterator.next();
								DebitoTipo debitoTipo = debitoCobrado.getDebitoTipo();
								if (debitosTipoRetirar.contains(debitoTipo)) {
									achouDebitoRetirar = true;
									colecaoDebitoCobradoIterator.remove();
								}
							}
							if (achouDebitoRetirar) {
								getControladorRetificarConta().retificarConta(new Integer(conta.getReferencia()), conta, imovel, colecaoDebitoCobrado,
										colecaoCreditoRealizado, conta.getLigacaoAguaSituacao(), conta.getLigacaoEsgotoSituacao(),
										colecaoCategoriaOUSubcategoria, conta.getConsumoAgua().toString(), conta.getConsumoEsgoto().toString(),
										conta.getPercentualEsgoto().toString(), conta.getDataVencimentoConta(), valoresConta, contaMotivoRetificacao, null,
										usuarioLogado, conta.getConsumoTarifa().getId() + "", false, null, null, false, null, null, null, null, null, null);
							}

						}
					}
				}
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				new ControladorException("erro.sistema", ex);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void retificarConjuntoContaCliente(Integer codigoCliente, Short relacaoTipo, Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado, Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();

		try {
			colecaoContasManutencao = repositorioFaturamento.obterContasCliente(codigoCliente, relacaoTipo, anoMes, dataVencimentoContaInicio,
					dataVencimentoContaFim, anoMesFim);

			if (colecaoContasManutencao != null && !colecaoContasManutencao.isEmpty()) {

				Iterator colecaoContasManutencaoIterator = colecaoContasManutencao.iterator();

				while (colecaoContasManutencaoIterator.hasNext()) {

					Object[] contaArray = (Object[]) colecaoContasManutencaoIterator.next();

					Conta conta = (Conta) contaArray[0];

					conta.setUltimaAlteracao(new Date());

					Imovel imovel = (Imovel) contaArray[1];

					Collection colecaoCategoria = getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);

					Collection colecaoCreditoRealizado = getControladorFaturamento().obterCreditosRealizadosConta(conta);

					Collection colecaoDebitoCobrado = getControladorFaturamento().obterDebitosCobradosConta(conta);

					Collection<CalcularValoresAguaEsgotoHelper> valoresConta = getControladorFaturamento().calcularValoresConta(Util.formatarAnoMesParaMesAno(conta.getReferencia()),
							imovel.getId().toString(), conta.getLigacaoAguaSituacao().getId(), conta.getLigacaoEsgotoSituacao().getId(), colecaoCategoria,
							conta.getConsumoAgua().toString(), conta.getConsumoEsgoto().toString(), conta.getPercentualEsgoto().toString(), conta
									.getConsumoTarifa().getId(), usuarioLogado);

					boolean achouDebitoRetirar = false;
					if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()) {
						Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado.iterator();
						while (colecaoDebitoCobradoIterator.hasNext()) {
							DebitoCobrado debitoCobrado = (DebitoCobrado) colecaoDebitoCobradoIterator.next();
							DebitoTipo debitoTipo = debitoCobrado.getDebitoTipo();
							if (debitosTipoRetirar.contains(debitoTipo)) {
								achouDebitoRetirar = true;
								colecaoDebitoCobradoIterator.remove();
							}
						}
						if (achouDebitoRetirar) {
							getControladorRetificarConta().retificarConta(new Integer(conta.getReferencia()), conta, imovel, colecaoDebitoCobrado,
									colecaoCreditoRealizado, conta.getLigacaoAguaSituacao(), conta.getLigacaoEsgotoSituacao(), colecaoCategoria,
									conta.getConsumoAgua().toString(), conta.getConsumoEsgoto().toString(), conta.getPercentualEsgoto().toString(),
									conta.getDataVencimentoConta(), valoresConta, contaMotivoRetificacao, null, usuarioLogado,
									conta.getConsumoTarifa().getId() + "", false, null, null, false, null, null, null, null, null, null);
						}

					}
				}
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void retificarConjuntoConta(Integer idGrupoFaturamento, Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado, Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim)
			throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		try {

			colecaoContasManutencao = repositorioFaturamento.obterContasGrupoFaturamento(anoMes, idGrupoFaturamento, dataVencimentoContaInicio,
					dataVencimentoContaFim, anoMesFim);

			if (colecaoContasManutencao != null && !colecaoContasManutencao.isEmpty()) {

				/**
				 * [UC0407] Filtrar Imóveis para Inserir ou Manter Conta 3. Caso o indicador de bloqueio de contas vinculadas a contrato de
				 * parcelamento no manter contas esteja ativo retirar da lista de contas selecionadas as contas vinculadas a algum contrato
				 * de parcelamento ativo
				 * */
				if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
						&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
					colecaoContasManutencao = this.obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(colecaoContasManutencao);
				}

				Iterator colecaoContasManutencaoIterator = colecaoContasManutencao.iterator();

				while (colecaoContasManutencaoIterator.hasNext()) {

					Object[] contaArray = (Object[]) colecaoContasManutencaoIterator.next();

					Conta conta = (Conta) contaArray[0];

					conta.setUltimaAlteracao(new Date());

					Imovel imovel = (Imovel) contaArray[1];

					Collection colecaoCategoria = getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);

					Collection colecaoCreditoRealizado = getControladorFaturamento().obterCreditosRealizadosConta(conta);

					Collection colecaoDebitoCobrado = getControladorFaturamento().obterDebitosCobradosConta(conta);

					Collection<CalcularValoresAguaEsgotoHelper> valoresConta = getControladorFaturamento().calcularValoresConta(Util.formatarAnoMesParaMesAno(conta.getReferencia()),
							imovel.getId().toString(), conta.getLigacaoAguaSituacao().getId(), conta.getLigacaoEsgotoSituacao().getId(), colecaoCategoria,
							conta.getConsumoAgua().toString(), conta.getConsumoEsgoto().toString(), conta.getPercentualEsgoto().toString(), conta
									.getConsumoTarifa().getId(), usuarioLogado);

					boolean achouDebitoRetirar = false;
					if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()) {
						Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado.iterator();
						while (colecaoDebitoCobradoIterator.hasNext()) {
							DebitoCobrado debitoCobrado = (DebitoCobrado) colecaoDebitoCobradoIterator.next();
							DebitoTipo debitoTipo = debitoCobrado.getDebitoTipo();
							if (debitosTipoRetirar.contains(debitoTipo)) {
								achouDebitoRetirar = true;
								colecaoDebitoCobradoIterator.remove();
							}
						}
						if (achouDebitoRetirar) {
							getControladorRetificarConta().retificarConta(new Integer(conta.getReferencia()), conta, imovel, colecaoDebitoCobrado,
									colecaoCreditoRealizado, conta.getLigacaoAguaSituacao(), conta.getLigacaoEsgotoSituacao(), colecaoCategoria,
									conta.getConsumoAgua().toString(), conta.getConsumoEsgoto().toString(), conta.getPercentualEsgoto().toString(),
									conta.getDataVencimentoConta(), valoresConta, contaMotivoRetificacao, null, usuarioLogado,
									conta.getConsumoTarifa().getId() + "", false, null, null, false, null, null, null, null, null, null);
						}

					}
				}
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void retificarConjuntoConta(Collection<Conta> colecaoContas, String identificadores, LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, Date dataVencimento, ContaMotivoRetificacao contaMotivoRetificacao,
			Short indicadorCategoriaEconomiaConta, Usuario usuarioLogado) throws ControladorException {

		UC0146ManterConta manterConta = UC0146ManterConta.getInstancia(repositorioFaturamento, sessionContext);

		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

		Collection colecaoContaSelecionadas = manterConta.gerarColecaoContaSelecaoParaRetificacao(colecaoContas, identificadores, sistemaParametro,
				usuarioLogado);

		manterConta.retificarConjuntoConta(colecaoContaSelecionadas, ligacaoAguaSituacao, consumoAgua, ligacaoEsgotoSituacao, consumoEsgoto, dataVencimento,
				contaMotivoRetificacao, indicadorCategoriaEconomiaConta, sistemaParametro, usuarioLogado);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void retificarConjuntoContaConsumos(Integer idFuncionalidadeIniciada, Map parametros) throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

			System.out.println("***************************************");
			System.out.println("RETIFICAR CONJUNTO CONTA CONSUMOS");
			System.out.println("***************************************");

			RetificarConjuntoContaConsumosHelper helper = (RetificarConjuntoContaConsumosHelper) parametros.get("helper");

			boolean usuarioPodeRetificarContasApenasVolumeEsgoto = this.getControladorPermissaoEspecial().verificarPermissaoEspecial(
					PermissaoEspecial.RETIFICAR_CONTA_APENAS_VOLUME_ESGOTO, helper.getUsuarioLogado());

			if (helper.getConsumoAgua().intValue() == 0 && helper.getVolumeEsgoto().intValue() > 0 && !usuarioPodeRetificarContasApenasVolumeEsgoto) {

				throw new ControladorException("atencao.necessario_permissao_especial_para_retificar_apenas_volume_esgoto");
			}

			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();

			Collection colecaoContasManutencao = new ArrayList();
			List colecaoAuxiliar = new ArrayList();

			colecaoAuxiliar.addAll(helper.getColecaoImovel());

			int i = 0;
			int cont = 500;

			Collection colecao = new ArrayList();
			while (i <= helper.getColecaoImovel().size()) {

				// PAGINAÇÃO
				if (helper.getColecaoImovel().size() - i >= cont) {
					colecao = colecaoAuxiliar.subList(i, i + cont);
				} else {
					colecao = colecaoAuxiliar.subList(i, helper.getColecaoImovel().size());
				}

				i = i + cont;

				try {

					colecaoContasManutencao = repositorioFaturamento.obterContasImoveis(helper.getAnoMes(), colecao, helper.getDataVencimentoContaInicio(),
							helper.getDataVencimentoContaFim(), helper.getAnoMesFim(), helper.getIndicadorContaPaga());

					/**
					 * [UC0407] Filtrar Imóveis para Inserir ou Manter Conta 3. Caso o indicador de bloqueio de contas vinculadas a
					 * contrato de parcelamento no manter contas esteja ativo retirar da lista de contas selecionadas as contas
					 * vinculadas a algum contrato de parcelamento ativo
					 */
					if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
							&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
						colecaoContasManutencao = getControladorRetificarConta().obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(
								colecaoContasManutencao);
					}

					if (colecaoContasManutencao != null && !colecaoContasManutencao.isEmpty()) {

						Iterator colecaoContasManutencaoIterator = colecaoContasManutencao.iterator();

						while (colecaoContasManutencaoIterator.hasNext()) {

							Object[] contaArray = (Object[]) colecaoContasManutencaoIterator.next();

							Conta conta = (Conta) contaArray[0];

							conta.setUltimaAlteracao(new Date());

							Imovel imovel = (Imovel) contaArray[1];

							Collection colecaoCategoriaOUSubcategoria = null;

							if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)) {

								if (helper.getIndicadorCategoriaEconomiaConta().equals(ConstantesSistema.SIM)) {
									colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);
								} else {
									colecaoCategoriaOUSubcategoria = this.getControladorImovel()
											.obterQuantidadeEconomiasSubCategoria(conta.getImovel().getId());
								}
							} else {

								if (helper.getIndicadorCategoriaEconomiaConta().equals(ConstantesSistema.SIM)) {
									colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);
								} else {
									colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(conta.getImovel());
								}
							}

							Collection colecaoCreditoRealizado = getControladorFaturamento().obterCreditosRealizadosConta(conta);
							Collection colecaoDebitoCobrado = getControladorFaturamento().obterDebitosCobradosConta(conta);

							/*
							 * Caso na conta a ser retificada o consumo de água seja igual ao volume de esgoto da conta os dois
							 * campos devem ser alterados (mesmo que o volume não tenha sido informado).
							 * 
							 * Caso contrário na conta a ser retificada o consumo de água seja diferente do volume de
							 * esgoto da conta só deve ser alterado o consumo de água.
							 */
							Integer volumeEsgoto = helper.getVolumeEsgoto();

							if (conta.getConsumoAgua() != null && conta.getConsumoEsgoto() != null && conta.getConsumoAgua().equals(conta.getConsumoEsgoto())
									&& helper.getVolumeEsgoto().intValue() == 0) {

								volumeEsgoto = conta.getConsumoAgua();
							}

							Collection<CalcularValoresAguaEsgotoHelper> valoresConta = getControladorFaturamento().calcularValoresConta(
									Util.formatarAnoMesParaMesAno(conta.getReferencia()), imovel.getId().toString(),
									helper.getLigacaoAguaSituacao() != null ? helper.getLigacaoAguaSituacao().getId() : conta.getLigacaoAguaSituacao().getId(),
									helper.getLigacaoEsgotoSituacao() != null ? helper.getLigacaoEsgotoSituacao().getId() : conta.getLigacaoEsgotoSituacao()
											.getId(), colecaoCategoriaOUSubcategoria, helper.getConsumoAgua().toString(), volumeEsgoto.toString(), conta
											.getPercentualEsgoto().toString(), conta.getConsumoTarifa().getId(), helper.getUsuarioLogado());

							getControladorRetificarConta().retificarConta(new Integer(conta.getReferencia()), conta, imovel, colecaoDebitoCobrado,
									colecaoCreditoRealizado,
									helper.getLigacaoAguaSituacao() != null ? helper.getLigacaoAguaSituacao() : conta.getLigacaoAguaSituacao(),
									helper.getLigacaoEsgotoSituacao() != null ? helper.getLigacaoEsgotoSituacao() : conta.getLigacaoEsgotoSituacao(),
									colecaoCategoriaOUSubcategoria, helper.getConsumoAgua().toString(), volumeEsgoto.toString(),
									conta.getPercentualEsgoto().toString(), helper.getDataVencimentoContaRetificacao(), valoresConta,
									helper.getContaMotivoRetificacao(), null, helper.getUsuarioLogado(), conta.getConsumoTarifa().getId().toString(), false,
									null, null, false, null, null, null, null, null, null);
						}
					}

				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					new ControladorException("erro.sistema", ex);
				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			System.out.println("******* FIM RETIFICAR CONJUNTO CONTA CONSUMOS **********");
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}

	@SuppressWarnings("rawtypes")
	public Collection<Object[]> obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(Collection<Object[]> colecaoContasManutencao)
			throws ControladorException {
		Collection<Object[]> retorno = new ArrayList<Object[]>();

		try {

			if (colecaoContasManutencao != null
					&& !colecaoContasManutencao.isEmpty()) {
				Iterator iterator = colecaoContasManutencao.iterator();

				while (iterator.hasNext()) {
					Object[] contaArray = (Object[]) iterator.next();

					if (!repositorioCobranca
							.verificaContaVinculadaAContratoParcelAtivo(((Conta) contaArray[0])
									.getId())) {
						retorno.add(contaArray);
					}
				}
			}

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection<Object[]> obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContasIds(
			Collection<Object[]> colecaoContasManutencao)
			throws ControladorException {
		Collection<Object[]> retorno = new ArrayList<Object[]>();

		try {

			if (colecaoContasManutencao != null && !colecaoContasManutencao.isEmpty()) {
				Iterator iterator = colecaoContasManutencao.iterator();

				while (iterator.hasNext()) {
					Object[] contaArray = (Object[]) iterator.next();

					if (!repositorioCobranca.verificaContaVinculadaAContratoParcelAtivo((Integer) contaArray[0])) {
						retorno.add(contaArray);
					}
				}
			}

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection retificarContasPagasSemDebitoCredito(Collection colecaoContasRetificar, Usuario usuarioLogado) throws ControladorException {

		Collection qtdContasRetificadas = new ArrayList();
		try {

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
			contaMotivoRetificacao.setId(ContaMotivoRetificacao.VALOR_SERVICO_ERRADO);

			Iterator ite = colecaoContasRetificar.iterator();
			while (ite.hasNext()) {
				Object[] dadosConta = (Object[]) ite.next();
				Integer idConta = null;
				BigDecimal valorConta = null;
				BigDecimal valorpagamento = null;
				Conta conta = new Conta();
				if (dadosConta != null) {
					if (dadosConta[0] != null) {
						idConta = (Integer) dadosConta[0];
						conta.setId(idConta);
					}
					if (dadosConta[1] != null) {
						valorpagamento = (BigDecimal) dadosConta[1];
					}

					if (dadosConta[2] != null) {
						valorConta = (BigDecimal) dadosConta[2];
					}

					Collection colecaoDebitosCobrados = repositorioFaturamento.pesquisarValorPrestacaoDebitoCobradoSemreferencia(idConta);
					Collection dadosDebitosACobrar = new ArrayList();
					Collection debitosCobradosDescartados = new ArrayList();

					boolean retificarConta = false;
					if (colecaoDebitosCobrados != null && !colecaoDebitosCobrados.isEmpty()) {
						Iterator itDebitosCobrados = colecaoDebitosCobrados.iterator();
						BigDecimal valorDebitosCobrados = new BigDecimal("0.00");
						while (itDebitosCobrados.hasNext()) {
							Object[] dadosDebitosCobrados = (Object[]) itDebitosCobrados.next();
							if (dadosDebitosCobrados != null) {
								BigDecimal valorDebitoCobrado = new BigDecimal("0.00");
								if (dadosConta[0] != null) {
									DebitoCobrado debitoCobrado = (DebitoCobrado) dadosDebitosCobrados[0];
									valorDebitoCobrado = debitoCobrado.getValorPrestacao();
									valorDebitosCobrados = valorDebitosCobrados.add(valorDebitoCobrado);
									debitosCobradosDescartados.add(debitoCobrado);
								}
								if (dadosConta[1] != null) {
									Integer idDebitoAcobrar = (Integer) dadosDebitosCobrados[1];
									Object[] dadosDebitoACobrar = new Object[2];
									dadosDebitoACobrar[0] = idDebitoAcobrar;
									dadosDebitoACobrar[1] = valorDebitoCobrado;
									dadosDebitosACobrar.add(dadosDebitoACobrar);
								}
							}
							BigDecimal valorContaMaisDebitoCobrado = valorpagamento.add(valorDebitosCobrados);

							if (valorContaMaisDebitoCobrado.compareTo(valorConta) == 0) {
								retificarConta = true;
								qtdContasRetificadas.add(idConta);
								break;
							}

						}
					}

					// retificar Conta
					if (retificarConta) {

						Collection colecaoCategoriaOUSubcategoria = null;

						if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)) {
							// [UC0108] - Quantidade de economias por categoria
							colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);

						} else {
							// [UC0108] - Quantidade de economias por categoria
							colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);

						}

						Collection colecaoCreditoRealizado = getControladorFaturamento().obterCreditosRealizadosConta(conta);

						Collection colecaoDebitoCobrado = getControladorFaturamento().obterDebitosCobradosConta(conta);
						colecaoDebitoCobrado.removeAll(debitosCobradosDescartados);

						// [UC0150] - Retificar Conta
						Conta contaParaRetificacao = this.pesquisarContaRetificacao(conta.getId());

						Collection<CalcularValoresAguaEsgotoHelper> valoresConta = getControladorFaturamento().calcularValoresConta(

						Util.formatarAnoMesParaMesAno(contaParaRetificacao.getReferencia()), contaParaRetificacao.getImovel().getId().toString(),
								contaParaRetificacao.getLigacaoAguaSituacao().getId(), contaParaRetificacao.getLigacaoEsgotoSituacao().getId(),
								colecaoCategoriaOUSubcategoria, contaParaRetificacao.getConsumoAgua().toString(),
								contaParaRetificacao.getConsumoEsgoto().toString(), contaParaRetificacao.getPercentualEsgoto().toString(),
								contaParaRetificacao.getConsumoTarifa().getId(), usuarioLogado);

						getControladorRetificarConta().retificarConta(contaParaRetificacao.getReferencia(), contaParaRetificacao,
								contaParaRetificacao.getImovel(), colecaoDebitoCobrado, colecaoCreditoRealizado, contaParaRetificacao.getLigacaoAguaSituacao(),
								contaParaRetificacao.getLigacaoEsgotoSituacao(), colecaoCategoriaOUSubcategoria,
								contaParaRetificacao.getConsumoAgua().toString(), contaParaRetificacao.getConsumoEsgoto().toString(),
								contaParaRetificacao.getPercentualEsgoto().toString(), contaParaRetificacao.getDataVencimentoConta(), valoresConta,
								contaMotivoRetificacao, null, usuarioLogado, contaParaRetificacao.getConsumoTarifa().getId().toString(), false, null, null,
								false, null, null, null, null, null, null);

						// Inseri o débito a Cobrar e o Débito a Cobrar
						// Categoria
						if (dadosDebitosACobrar != null && !dadosDebitosACobrar.isEmpty()) {
							Iterator itDebitoACobrar = dadosDebitosACobrar.iterator();
							while (itDebitoACobrar.hasNext()) {
								Object[] dadosDebitoACobrar = (Object[]) itDebitoACobrar.next();

								Integer idDebitoACobrar = (Integer) dadosDebitoACobrar[0];
								BigDecimal valorDebitoACobrar = (BigDecimal) dadosDebitoACobrar[1];

								// Pesquisa os débitos a cobrar
								FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
								filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID, idDebitoACobrar));
								filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarCategorias");
								filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
								filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
								filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
								Collection debitosACobrar = getControladorUtil().pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());
								DebitoACobrar debitoACobrar = (DebitoACobrar) Util.retonarObjetoDeColecao(debitosACobrar);

								if (debitoACobrar != null && !debitoACobrar.equals("")) {
									DebitoACobrar debitoACobrarInserir = new DebitoACobrar(new Date(), contaParaRetificacao.getReferencia(),
											debitoACobrar.getAnoMesCobrancaDebito(), valorDebitoACobrar, new Short("1"), new Short("0"),
											debitoACobrar.getCodigoSetorComercial(), debitoACobrar.getNumeroQuadra(), debitoACobrar.getNumeroLote(),
											debitoACobrar.getNumeroSubLote(), new Date(), Util.formataAnoMes(new Date()),
											debitoACobrar.getPercentualTaxaJurosFinanciamento(), debitoACobrar.getImovel(), debitoACobrar.getDocumentoTipo(),
											debitoACobrar.getParcelamento(), debitoACobrar.getFinanciamentoTipo(), debitoACobrar.getOrdemServico(),
											debitoACobrar.getQuadra(), debitoACobrar.getLocalidade(), debitoACobrar.getDebitoTipo(),
											debitoACobrar.getRegistroAtendimento(), debitoACobrar.getLancamentoItemContabil(),
											debitoACobrar.getDebitoCreditoSituacaoAnterior(), debitoACobrar.getDebitoCreditoSituacaoAtual(),
											debitoACobrar.getParcelamentoGrupo(), debitoACobrar.getCobrancaForma(), usuarioLogado,
											debitoACobrar.getDebitoACobrarCategorias());

									getControladorFaturamento().inserirDebitoACobrar(1, debitoACobrarInserir, valorDebitoACobrar,
											contaParaRetificacao.getImovel(), null, null, usuarioLogado, true);
								} else {
									// Caso não tenha débito a cobrar então
									// procura o débito a cobrar histórico
									// Pesquisa os débitos a cobrar
									FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();
									filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID, idDebitoACobrar));
									filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
									filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
									filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
									Collection debitosACobrarHistorico = getControladorUtil().pesquisar(filtroDebitoACobrarHistorico,
											DebitoACobrarHistorico.class.getName());
									DebitoACobrarHistorico debitoACobrarHistorico = (DebitoACobrarHistorico) Util
											.retonarObjetoDeColecao(debitosACobrarHistorico);

									if (debitoACobrarHistorico != null && !debitoACobrarHistorico.equals("")) {
										DebitoACobrar debitoACobrarInserir = new DebitoACobrar(new Date(), contaParaRetificacao.getReferencia(),
												debitoACobrarHistorico.getAnoMesCobrancaDebito(), valorDebitoACobrar, new Short("1"), new Short("0"),
												debitoACobrarHistorico.getCodigoSetorComercial(), debitoACobrarHistorico.getNumeroQuadra(),
												debitoACobrarHistorico.getLote(), debitoACobrarHistorico.getSublote(), new Date(),
												Util.formataAnoMes(new Date()), debitoACobrarHistorico.getPercentualTaxaJurosFinanciamento(),
												debitoACobrarHistorico.getImovel(), debitoACobrarHistorico.getDocumentoTipo(),
												debitoACobrarHistorico.getParcelamento(), debitoACobrarHistorico.getFinanciamentoTipo(),
												debitoACobrarHistorico.getOrdemServico(), debitoACobrarHistorico.getQuadra(),
												debitoACobrarHistorico.getLocalidade(), debitoACobrarHistorico.getDebitoTipo(),
												debitoACobrarHistorico.getRegistroAtendimento(), debitoACobrarHistorico.getLancamentoItemContabil(),
												debitoACobrarHistorico.getDebitoCreditoSituacaoAnterior(),
												debitoACobrarHistorico.getDebitoCreditoSituacaoAtual(), debitoACobrarHistorico.getParcelamentoGrupo(),
												debitoACobrarHistorico.getCobrancaForma(), usuarioLogado, null);

										getControladorFaturamento().inserirDebitoACobrar(1, debitoACobrarInserir, valorDebitoACobrar,
												contaParaRetificacao.getImovel(), null, null, usuarioLogado, true);
									}
								}

							}
						}
					}

				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return qtdContasRetificadas;
	}
	
	public Conta pesquisarContaRetificacao(Integer idConta) throws ControladorException {

		Conta retorno = null;
		Object[] arrayConta = null;

		try {

			arrayConta = repositorioFaturamento.pesquisarContaRetificacao(idConta);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		if (arrayConta != null) {

			retorno = new Conta();

			retorno.setId((Integer) arrayConta[0]);
			retorno.setDataVencimentoConta((Date) arrayConta[1]);
			retorno.setReferencia((Integer) arrayConta[2]);

			if (arrayConta[3] != null) {
				retorno.setReferenciaContabil((Integer) arrayConta[3]);
			}

			if (arrayConta[4] != null) {
				retorno.setConsumoAgua((Integer) arrayConta[4]);
			}

			if (arrayConta[5] != null) {
				retorno.setConsumoEsgoto((Integer) arrayConta[5]);
			}

			retorno.setValorAgua((BigDecimal) arrayConta[6]);
			retorno.setValorEsgoto((BigDecimal) arrayConta[7]);
			retorno.setDebitos((BigDecimal) arrayConta[8]);
			retorno.setValorCreditos((BigDecimal) arrayConta[9]);

			if (arrayConta[10] != null) {
				retorno.setValorImposto((BigDecimal) arrayConta[10]);
			}

			if (arrayConta[11] != null) {
				retorno.setDataValidadeConta((Date) arrayConta[11]);
			}

			if (arrayConta[12] != null) {
				retorno.setLote((Short) arrayConta[12]);
			}

			if (arrayConta[13] != null) {
				retorno.setSubLote((Short) arrayConta[13]);
			}

			retorno.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao((Integer) arrayConta[14]));

			retorno.setLocalidade(new Localidade((Integer) arrayConta[15]));
			
			SetorComercial setorComercialConta = new SetorComercial((Integer) arrayConta[18]);
			setorComercialConta.setCodigo((Integer) arrayConta[19]);

			Quadra quadraConta = new Quadra((Integer) arrayConta[16]);
			quadraConta.setNumeroQuadra((Integer) arrayConta[17]);
			quadraConta.setSetorComercial(setorComercialConta);

			retorno.setQuadraConta(quadraConta);

			retorno.setIndicadorCobrancaMulta((Short) arrayConta[20]);
			retorno.setConsumoTarifa(new ConsumoTarifa((Integer) arrayConta[21]));

			if (arrayConta[22] != null) {
				ImovelPerfil imovelPerfilConta = new ImovelPerfil((Integer) arrayConta[22]);
				retorno.setImovelPerfil(imovelPerfilConta);
			}

			LigacaoAguaSituacao ligacaoAguaSituacaoConta = new LigacaoAguaSituacao((Integer) arrayConta[23]);
			ligacaoAguaSituacaoConta.setIndicadorFaturamentoSituacao((Short) arrayConta[49]);
			retorno.setLigacaoAguaSituacao(ligacaoAguaSituacaoConta);

			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoConta = new LigacaoEsgotoSituacao((Integer) arrayConta[24]);
			ligacaoEsgotoSituacaoConta.setIndicadorFaturamentoSituacao((Short) arrayConta[50]);
			retorno.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacaoConta);

			Imovel imovelConta = new Imovel((Integer) arrayConta[25]);

			imovelConta.setImovelPerfil(new ImovelPerfil((Integer) arrayConta[26]));

			if (arrayConta[38] != null) {
				retorno.setPercentualEsgoto((BigDecimal) arrayConta[38]);
			}

			if (arrayConta[39] != null) {
				imovelConta.setIndicadorDebitoConta((Short) arrayConta[39]);
			}

			if (arrayConta[40] != null) {
				imovelConta.setImovelCondominio(new Imovel((Integer) arrayConta[40]));
			}

			if (arrayConta[41] != null) {
				retorno.setNumeroRetificacoes((Integer) arrayConta[41]);
			}

			if (arrayConta[42] != null) {
				retorno.setIndicadorDebitoConta((Short) arrayConta[42]);
			}

			if (arrayConta[43] != null) {
				retorno.setFaturamentoGrupo(new FaturamentoGrupo((Integer) arrayConta[43]));
			}

			if (arrayConta[44] != null) {
				retorno.setRota(new Rota((Integer) arrayConta[44]));
			}

			imovelConta.setLocalidade(new Localidade((Integer) arrayConta[27]));

			SetorComercial setorComercialImovel = new SetorComercial((Integer) arrayConta[30]);
			setorComercialImovel.setCodigo((Integer) arrayConta[31]);
			imovelConta.setSetorComercial(setorComercialImovel);

			Quadra quadraImovel = new Quadra((Integer) arrayConta[28]);
			quadraImovel.setNumeroQuadra((Integer) arrayConta[29]);
			imovelConta.setQuadra(quadraImovel);

			LigacaoAguaSituacao ligacaoAguaSituacaoImovel = new LigacaoAguaSituacao((Integer) arrayConta[32]);
			ligacaoAguaSituacaoImovel.setDescricao((String) arrayConta[33]);
			imovelConta.setLigacaoAguaSituacao(ligacaoAguaSituacaoImovel);

			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoImovel = new LigacaoEsgotoSituacao((Integer) arrayConta[34]);
			ligacaoEsgotoSituacaoImovel.setDescricao((String) arrayConta[35]);
			imovelConta.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacaoImovel);

			imovelConta.setConsumoTarifa(new ConsumoTarifa((Integer) arrayConta[36]));

			retorno.setDataVencimentoOriginal((Date) arrayConta[37]);

			BigDecimal percentualLigacaoEsgoto = null;

			try {
				percentualLigacaoEsgoto = repositorioFaturamento.obterPercentualLigacaoEsgotoImovel(imovelConta.getId());
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				new ControladorException("erro.sistema", ex);
			}

			if (percentualLigacaoEsgoto != null) {
				LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
				ligacaoEsgoto.setPercentual(percentualLigacaoEsgoto);
				imovelConta.setLigacaoEsgoto(ligacaoEsgoto);
			}

			retorno.setImovel(imovelConta);

			if (arrayConta[45] != null) {
				retorno.setNumeroLeituraAnterior((Integer) arrayConta[45]);
			}
			if (arrayConta[46] != null) {
				retorno.setNumeroLeituraAtual((Integer) arrayConta[46]);
			}
			if (arrayConta[47] != null) {
				retorno.setPercentualColeta((BigDecimal) (arrayConta[47]));
			}
			if (arrayConta[48] != null) {
				retorno.setNumeroVolumePoco((Integer) (arrayConta[48]));
			}
			if (arrayConta[51] != null) {
				retorno.setValorRateioAgua((BigDecimal) (arrayConta[51]));
			}
			if (arrayConta[52] != null) {
				retorno.setConsumoRateioAgua((Integer) (arrayConta[52]));
			}
			if (arrayConta[53] != null) {
				retorno.setValorRateioEsgoto((BigDecimal) (arrayConta[53]));
			}
			if (arrayConta[54] != null) {
				retorno.setConsumoRateioEsgoto((Integer) (arrayConta[54]));
			}
		}

		return retorno;
	}
}
