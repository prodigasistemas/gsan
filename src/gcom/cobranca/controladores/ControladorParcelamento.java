package gcom.cobranca.controladores;

import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.repositorios.IRepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.RepositorioParcelamentoHBM;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

public class ControladorParcelamento extends ControladorComum {

	private static final long serialVersionUID = -2063305788601928963L;
	
	private SistemaParametro sistemaParametro;

	protected IRepositorioParcelamentoHBM repositorio;

	public void ejbCreate() throws CreateException {
		repositorio = RepositorioParcelamentoHBM.getInstancia();
	}
	
	public CancelarParcelamentoHelper pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ControladorException {
		try {
			return repositorio.pesquisarParcelamentoParaCancelar(idParcelamento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void cancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {
			List<CancelarParcelamentoHelper> parcelamentos = repositorio.pesquisarParcelamentosParaCancelar();

			for (CancelarParcelamentoHelper helper : parcelamentos) {
				cancelarParcelamento(helper, usuario);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new EJBException(ex);
		}
	}

	public void cancelarParcelamento(CancelarParcelamentoHelper helper, Usuario usuario) throws ControladorException {
		sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		
		cancelarDebitoACobrar(helper.getParcelamento().getId());
		cancelarCreditoARealizar(helper.getParcelamento().getId());
		
		Parcelamento parcelamento = helper.getParcelamento();
		parcelamento.setParcelamentoSituacao(new ParcelamentoSituacao(ParcelamentoSituacao.CANCELADO));
		parcelamento.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(parcelamento);
		
		gerarContaIncluida(helper, usuario);
	}

	@SuppressWarnings("unchecked")
	private void cancelarDebitoACobrar(Integer idParcelamento) {
		try {
			Filtro filtro = new FiltroDebitoACobrar();
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, idParcelamento));
			Collection<DebitoACobrar> colecao = super.getControladorUtil().pesquisar(filtro, DebitoACobrar.class.getName());

			for (DebitoACobrar debito : colecao) {
				debito.setDebitoCreditoSituacaoAnterior(debito.getDebitoCreditoSituacaoAtual());
				debito.setDebitoCreditoSituacaoAtual(getSituacaoCancelada());
				debito.setUltimaAlteracao(new Date());

				getControladorUtil().atualizar(debito);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cancelarCreditoARealizar(Integer idParcelamento) {
		try {
			Filtro filtro = new FiltroCreditoARealizar();
			filtro.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.PARCELAMENTO_ID, idParcelamento));
			Collection<CreditoARealizar> colecao = super.getControladorUtil().pesquisar(filtro, CreditoARealizar.class.getName());

			for (CreditoARealizar credito : colecao) {
				credito.setDebitoCreditoSituacaoAnterior(credito.getDebitoCreditoSituacaoAtual());
				credito.setDebitoCreditoSituacaoAtual(getSituacaoCancelada());
				credito.setUltimaAlteracao(new Date());

				getControladorUtil().atualizar(credito);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

	private void gerarContaIncluida(CancelarParcelamentoHelper helper, Usuario usuario) throws ControladorException {
		Integer referencia = getControladorFaturamento().pesquisarAnoMesReferenciaFaturamentoGrupo(helper.getImovel().getId());
		Conta conta = new Conta(gerarContaGeral(),
				referencia,
				getControladorFaturamento().obterReferenciaContabilConta(sistemaParametro, referencia),
				ConstantesSistema.NAO,
				new DebitoCreditoSituacao(DebitoCreditoSituacao.INCLUIDA),
				helper.getImovel(),
				Util.calculoRepresentacaoNumericaCodigoBarrasModulo10(referencia).shortValue(),
				ConstantesSistema.NAO,
				new Date(),
				new Date(),
				getControladorFaturamento().retornaDataValidadeConta(new Date()),
				new Date(),
				new Date(),
				new ContaMotivoInclusao(ContaMotivoInclusao.CANCELAMENTO_DE_PARCELAMENTO),
				ConstantesSistema.NAO,
				0,
				getControladorImovel().pesquisarGrupoImovel(helper.getImovel().getId()),
				0,
				usuario,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				helper.getSaldoDevedorTotal(),
				BigDecimal.ZERO);
		
		GerarImpostosDeduzidosContaHelper impostosDeduzidos = getControladorFaturamento().gerarImpostosDeduzidosConta(conta, false);
		conta.setValorImposto(impostosDeduzidos.getValorTotalImposto());
		conta.setNumeroBoleto(getControladorFaturamento().verificarGeracaoBoleto(sistemaParametro, conta));

		getControladorUtil().inserir(conta);
		getControladorFaturamento().inserirClienteConta(conta, helper.getImovel());
		getControladorFaturamento().inserirImpostosDeduzidosConta(impostosDeduzidos, conta);
		
		gerarDebitos(helper, conta, usuario);
	}
	
	private ContaGeral gerarContaGeral() throws ControladorException {
		ContaGeral contaGeral = new ContaGeral();
		contaGeral.setIndicadorHistorico(ConstantesSistema.NAO);
		contaGeral.setUltimaAlteracao(new Date());
		
		Integer id = (Integer) getControladorUtil().inserir(contaGeral);
		contaGeral.setId(id);
		
		return contaGeral;
	}
	
	private Date obterDataProximaConta(Imovel imovel) throws ControladorException {
		Integer proximaReferencia = obterReferenciaProximaConta(imovel.getId());
		
		if (imovel.getDiaVencimento() != null) {
			return Util.criarData(imovel.getDiaVencimento(), Util.obterMes(proximaReferencia), Util.obterAno(proximaReferencia));
		} else {
			FaturamentoGrupo grupo = getControladorImovel().pesquisarGrupoImovel(imovel.getId());
			return Util.criarData(grupo.getDiaVencimento(), Util.obterMes(proximaReferencia), Util.obterAno(proximaReferencia));
		}
	}
	
	private Integer obterReferenciaProximaConta(Integer idImovel) throws ControladorException {
		Conta conta = getControladorFaturamento().obterContaImovel(idImovel, sistemaParametro.getAnoMesFaturamento());
		if (conta == null) {
			return sistemaParametro.getAnoMesFaturamento();
		} else {
			return Util.somaMesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento(), 1);
		}
	}

	private void inserirDebitoACobrar(BigDecimal valor, CancelarParcelamentoHelper helper, Conta conta, Integer idDebitoTipo, Usuario usuario) throws ControladorException {
		if (valor == null || valor == BigDecimal.ZERO) return;
		
		DebitoACobrar debitoACobrar = new DebitoACobrar(
				gerarDebitoACobrarGeral(),
				helper.getImovel(),
				valor,
				new Short("1"),
				new Short("0"),
				new Date(),
				sistemaParametro.getAnoMesFaturamento(),
				sistemaParametro.getAnoMesArrecadacao(),
				getControladorFaturamento().obterReferenciaContabilConta(sistemaParametro),
				BigDecimal.ZERO,
				idDebitoTipo,
				FinanciamentoTipo.CANCELAMENTO_PARCELAMENTO,
				LancamentoItemContabil.CANCELAMENTO_PARCELAMENTO,
				DebitoCreditoSituacao.NORMAL,
				null,
				CobrancaForma.COBRANCA_EM_CONTA,
				usuario);
		try {
			getControladorUtil().inserir(debitoACobrar);
			inserirDebitoCobrado(conta, helper.getImovel(), debitoACobrar, idDebitoTipo);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("Erro ao inserir novo Debito a Cobrar", e);
		}
	}

	private void inserirDebitoCobrado(Conta conta, Imovel imovel, DebitoACobrar debitoACobrar, Integer idDebitoTipo) throws ControladorException {
		DebitoCobrado debitoCobrado = new DebitoCobrado(
				new Date(),
				imovel,
				idDebitoTipo,
				conta,
				LancamentoItemContabil.CANCELAMENTO_PARCELAMENTO,
				debitoACobrar,
				new Short("1"),
				new Short("0"),
				FinanciamentoTipo.CANCELAMENTO_PARCELAMENTO);

		Integer id = (Integer) this.getControladorUtil().inserir(debitoCobrado);
		debitoCobrado.setId(id);

		Collection<?> colecaoCategoria = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imovel);
		getControladorFaturamento().inserirDebitoCobradoCategoria(debitoCobrado, colecaoCategoria);
	}
	
	private DebitoACobrarGeral gerarDebitoACobrarGeral() throws ControladorException {
		DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
		debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
		debitoACobrarGeral.setUltimaAlteracao(new Date());
		
		Integer id = (Integer) getControladorUtil().inserir(debitoACobrarGeral);
		debitoACobrarGeral.setId(id);
		
		return debitoACobrarGeral;
	}
	
	private void gerarDebitos(CancelarParcelamentoHelper helper, Conta conta, Usuario usuario) throws ControladorException {
		inserirDebitoACobrar(helper.getSaldoDevedorContasCurtoPrazo(), helper, conta, DebitoTipo.CANCELAMENTO_PARCELAMENTO_CONTAS_CURTO, usuario);
		inserirDebitoACobrar(helper.getSaldoDevedorContasLongoPrazo(), helper, conta, DebitoTipo.CANCELAMENTO_PARCELAMENTO_CONTAS_LONGO, usuario);
		inserirDebitoACobrar(helper.getSaldoDevedorAcrescimosCurtoPrazo(), helper, conta, DebitoTipo.CANCELAMENTO_PARCELAMENTO_ACRESCIMOS_CURTO, usuario);
		inserirDebitoACobrar(helper.getSaldoDevedorAcrescimosLongoPrazo(), helper, conta, DebitoTipo.CANCELAMENTO_PARCELAMENTO_ACRESCIMOS_LONGO, usuario);
		inserirDebitoACobrar(helper.getTotalCancelamentoDescontos(), helper, conta, DebitoTipo.CANCELAMENTO_PARCELAMENTO_DESCONTO_ACRESCIMOS, usuario);
		
		gerarNovosAcrescimos(helper, conta, usuario);
	}

	private void gerarNovosAcrescimos(CancelarParcelamentoHelper helper, Conta conta, Usuario usuario) throws ControladorException {
		CalcularAcrescimoPorImpontualidadeHelper acrescimos = calcularAcrescimosPorImpontualidade(helper, conta.getId());
		
		inserirDebitoACobrar(acrescimos.getValorJurosMora(), helper, conta, DebitoTipo.JUROS_MORA, usuario);
		inserirDebitoACobrar(acrescimos.getValorMulta(), helper, conta, DebitoTipo.MULTA_IMPONTUALIDADE, usuario);
		inserirDebitoACobrar(acrescimos.getValorAtualizacaoMonetaria(), helper, conta, DebitoTipo.ATUALIZACAO_MONETARIA, usuario);
	}
	
	private CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimosPorImpontualidade(CancelarParcelamentoHelper helper, Integer idConta) throws ControladorException {
		CalcularAcrescimoPorImpontualidadeHelper acrescimos = null;
		
		try {
			Date dataProximaConta = obterDataProximaConta(helper.getImovel());
			
			acrescimos = getControladorCobranca().calcularAcrescimoPorImpontualidade(
					helper.getParcelamento().getAnoMesReferenciaFaturamento(),
					helper.getParcelamento().getUltimaAlteracao(), 
					dataProximaConta,
					helper.getSaldoDevedorTotal(),
					BigDecimal.ZERO,
					ConstantesSistema.SIM,
					sistemaParametro.getAnoMesArrecadacao().toString(),
					idConta,
					ConstantesSistema.INDICADOR_ARRECADACAO_DESATIVO);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("Erro ao calcular acréscimo para cancelamento de parcelamento.", e);
		}

		return acrescimos;
	}

	private DebitoCreditoSituacao getSituacaoCancelada() {
		return new DebitoCreditoSituacao(DebitoCreditoSituacao.CANCELADA);
	}
}