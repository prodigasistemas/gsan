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
	
	private SistemaParametro parametros;

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

			for (CancelarParcelamentoHelper dto : parcelamentos) {
				cancelarParcelamento(dto, usuario);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new EJBException(ex);
		}
	}

	public void cancelarParcelamento(CancelarParcelamentoHelper dto, Usuario usuario) throws ControladorException {
		parametros = getControladorUtil().pesquisarParametrosDoSistema();
		
		cancelarDebitoACobrar(dto.getParcelamento().getId());
		cancelarCreditoARealizar(dto.getParcelamento().getId());
		
		Parcelamento parcelamento = dto.getParcelamento();
		parcelamento.setParcelamentoSituacao(new ParcelamentoSituacao(ParcelamentoSituacao.CANCELADO));
		parcelamento.setUltimaAlteracao(new Date());
		getControladorUtil().atualizar(parcelamento);
		
		gerarContaIncluida(dto, usuario);
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

	private BigDecimal calcularSaldoDevedor(CancelarParcelamentoHelper dto) throws ControladorException {
		BigDecimal valorPrestacoesCobradas = dto.getValorPrestacao().multiply(BigDecimal.valueOf(dto.getNumeroPrestacoesCobradas()));
		BigDecimal valorCobrado = dto.getValorEntrada().add(valorPrestacoesCobradas);
		BigDecimal saldoDevedor = dto.getValorOriginal().subtract(valorCobrado);
		
		BigDecimal acrescimos = calcularAcrescimosPorImpontualidade(dto, saldoDevedor);
		
		return saldoDevedor.add(acrescimos).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal calcularAcrescimosPorImpontualidade(CancelarParcelamentoHelper dto, BigDecimal saldoDevedor) throws ControladorException {
		CalcularAcrescimoPorImpontualidadeHelper helper = null;
		
		try {
			Date dataProximaConta = obterDataProximaConta(dto.getImovel());
			
			helper = getControladorCobranca().calcularAcrescimoPorImpontualidade(
					dto.getParcelamento().getAnoMesReferenciaFaturamento(),
					dto.getParcelamento().getUltimaAlteracao(), 
					dataProximaConta,
					saldoDevedor,
					BigDecimal.ZERO,
					ConstantesSistema.SIM,
					parametros.getAnoMesArrecadacao().toString(),
					null,
					ConstantesSistema.INDICADOR_ARRECADACAO_DESATIVO);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("Erro ao calcular acréscimo para cancelamento de parcelamento.", e);
		}

		return helper.getValorTotalAcrescimosImpontualidade();
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
		Conta conta = getControladorFaturamento().obterContaImovel(idImovel, parametros.getAnoMesFaturamento());
		if (conta == null) {
			return parametros.getAnoMesFaturamento();
		} else {
			return Util.somaMesAnoMesReferencia(parametros.getAnoMesFaturamento(), 1);
		}
	}

	private DebitoACobrar gerarDebitoACobrar(CancelarParcelamentoHelper dto, Usuario usuario) throws ControladorException {
		DebitoACobrar debitoACobrar = new DebitoACobrar(
				gerarDebitoACobrarGeral(),
				dto.getImovel(),
				calcularSaldoDevedor(dto),
				new Short("1"),
				new Short("0"),
				new Date(),
				parametros.getAnoMesFaturamento(),
				parametros.getAnoMesArrecadacao(),
				getControladorFaturamento().obterReferenciaContabilConta(parametros),
				BigDecimal.ZERO,
				DebitoTipo.CANCELAMENTO_DE_PARCELAMENTO,
				FinanciamentoTipo.CANCELAMENTO_DE_PARCELAMENTO,
				LancamentoItemContabil.CANCELAMENTO_DE_PARCELAMENTO,
				DebitoCreditoSituacao.NORMAL,
				null,
				CobrancaForma.COBRANCA_EM_CONTA,
				usuario);
		
		try {
			getControladorUtil().inserir(debitoACobrar);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("Erro ao inserir novo Debito a Cobrar", e);
		}

		return debitoACobrar;
	}

	private DebitoACobrarGeral gerarDebitoACobrarGeral() throws ControladorException {
		DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
		debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
		debitoACobrarGeral.setUltimaAlteracao(new Date());
		
		Integer id = (Integer) getControladorUtil().inserir(debitoACobrarGeral);
		debitoACobrarGeral.setId(id);
		
		return debitoACobrarGeral;
	}
	
	private void gerarContaIncluida(CancelarParcelamentoHelper dto, Usuario usuario) throws ControladorException {
		DebitoACobrar debitoACobrar = gerarDebitoACobrar(dto, usuario);
		Integer referencia = getControladorFaturamento().pesquisarAnoMesReferenciaFaturamentoGrupo(dto.getImovel().getId());
		
		Conta conta = new Conta(gerarContaGeral(),
				referencia,
				getControladorFaturamento().obterReferenciaContabilConta(parametros, referencia),
				ConstantesSistema.NAO,
				new DebitoCreditoSituacao(DebitoCreditoSituacao.INCLUIDA),
				dto.getImovel(),
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
				getControladorImovel().pesquisarGrupoImovel(dto.getImovel().getId()),
				0,
				usuario,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				debitoACobrar.getValorDebito(),
				BigDecimal.ZERO);
		
		GerarImpostosDeduzidosContaHelper impostosDeduzidos = getControladorFaturamento().gerarImpostosDeduzidosConta(
				conta.getImovel().getId(), referencia, conta.getValorAgua(), conta.getValorEsgoto(), conta.getDebitos(), BigDecimal.ZERO, false);
		conta.setValorImposto(impostosDeduzidos.getValorTotalImposto());
		conta.setNumeroBoleto(getControladorFaturamento().verificarGeracaoBoleto(parametros, conta));

		getControladorUtil().inserir(conta);
		gerarDebitoCobrado(conta, dto.getImovel(), debitoACobrar);
		getControladorFaturamento().inserirClienteConta(conta, dto.getImovel());
		getControladorFaturamento().inserirImpostosDeduzidosConta(impostosDeduzidos, conta);
	}

	private void gerarDebitoCobrado(Conta conta, Imovel imovel, DebitoACobrar debitoACobrar) throws ControladorException {
		DebitoCobrado debitoCobrado = new DebitoCobrado(
				new Date(),
				imovel,
				DebitoTipo.CANCELAMENTO_DE_PARCELAMENTO,
				conta,
				LancamentoItemContabil.CANCELAMENTO_DE_PARCELAMENTO,
				debitoACobrar,
				new Short("1"),
				new Short("0"),
				FinanciamentoTipo.CANCELAMENTO_DE_PARCELAMENTO);

		Integer id = (Integer) this.getControladorUtil().inserir(debitoCobrado);
		debitoCobrado.setId(id);

		Collection<?> colecaoCategoria = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imovel);
		getControladorFaturamento().inserirDebitoCobradoCategoria(debitoCobrado, colecaoCategoria);
	}

	private ContaGeral gerarContaGeral() throws ControladorException {
		ContaGeral contaGeral = new ContaGeral();
		contaGeral.setIndicadorHistorico(ConstantesSistema.NAO);
		contaGeral.setUltimaAlteracao(new Date());
		
		Integer id = (Integer) getControladorUtil().inserir(contaGeral);
		contaGeral.setId(id);
		
		return contaGeral;
	}

	private DebitoCreditoSituacao getSituacaoCancelada() {
		return new DebitoCreditoSituacao(DebitoCreditoSituacao.CANCELADA);
	}
}