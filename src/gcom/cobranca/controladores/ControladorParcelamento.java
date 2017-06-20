package gcom.cobranca.controladores;

import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.repositorios.IRepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.RepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
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
import gcom.micromedicao.Rota;
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
import java.util.GregorianCalendar;
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
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelar(Integer idParcelamento) throws ControladorException {
		try {
			return repositorio.pesquisarParcelamentoParaCancelar(idParcelamento);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void cancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {
			List<CancelarParcelamentoDTO> parcelamentos = repositorio.pesquisarParcelamentosParaCancelar();

			for (CancelarParcelamentoDTO dto : parcelamentos) {
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

	public void cancelarParcelamento(CancelarParcelamentoDTO dto, Usuario usuario) throws ControladorException {
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

	private BigDecimal calcularSaldoDevedor(CancelarParcelamentoDTO dto) throws ControladorException {
		BigDecimal valorPrestacoesCobradas = dto.getValorPrestacao().multiply(BigDecimal.valueOf(dto.getNumeroPrestacoesCobradas()));
		BigDecimal valorCobrado = dto.getValorEntrada().add(valorPrestacoesCobradas);
		BigDecimal saldoDevedor = dto.getValorOriginal().subtract(valorCobrado);
		
		BigDecimal acrescimos = calcularAcrescimosPorImpontualidade(dto, saldoDevedor);
		
		return saldoDevedor.add(acrescimos).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal calcularAcrescimosPorImpontualidade(CancelarParcelamentoDTO dto, BigDecimal saldoDevedor) throws ControladorException {
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

	private DebitoACobrar gerarDebitoACobrar(CancelarParcelamentoDTO dto, Usuario usuario) throws ControladorException {
		DebitoACobrar debitoACobrar = new DebitoACobrar();

		try {
			DebitoACobrarGeral debitoACobrarGeral = gerarDebitoACobrarGeral();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);
			debitoACobrar.setImovel(dto.getImovel());
			debitoACobrar.setValorDebito(calcularSaldoDevedor(dto));
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(parametros.getAnoMesFaturamento());
			debitoACobrar.setAnoMesCobrancaDebito(parametros.getAnoMesArrecadacao());
			debitoACobrar.setAnoMesReferenciaContabil(getControladorFaturamento().obterReferenciaContabilConta(parametros));
			debitoACobrar.setLocalidade(dto.getImovel().getLocalidade());
			debitoACobrar.setQuadra(dto.getImovel().getQuadra());
			debitoACobrar.setCodigoSetorComercial(dto.getImovel().getSetorComercial().getCodigo());
			debitoACobrar.setNumeroQuadra(dto.getImovel().getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(dto.getImovel().getLote());
			debitoACobrar.setNumeroSubLote(dto.getImovel().getSubLote());
			debitoACobrar.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
			debitoACobrar.setDebitoTipo(new DebitoTipo(DebitoTipo.CANCELAMENTO_DE_PARCELAMENTO));
			debitoACobrar.setFinanciamentoTipo(new FinanciamentoTipo(FinanciamentoTipo.CANCELAMENTO_DE_PARCELAMENTO));
			debitoACobrar.setLancamentoItemContabil(new LancamentoItemContabil(LancamentoItemContabil.CANCELAMENTO_DE_PARCELAMENTO));
			debitoACobrar.setDebitoCreditoSituacaoAnterior(null);
			debitoACobrar.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL));
			debitoACobrar.setParcelamentoGrupo(null);
			debitoACobrar.setCobrancaForma(new CobrancaForma(CobrancaForma.COBRANCA_EM_CONTA));
			debitoACobrar.setUsuario(usuario);
			debitoACobrar.setUltimaAlteracao(new Date());

			this.getControladorUtil().inserir(debitoACobrar);
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
	
	private void gerarContaIncluida(CancelarParcelamentoDTO dto, Usuario usuario) throws ControladorException {
		DebitoACobrar debitoACobrar = gerarDebitoACobrar(dto, usuario);
		Integer anoMesConta = getControladorFaturamento().pesquisarAnoMesReferenciaFaturamentoGrupo(dto.getImovel().getId());
		
		ContaGeral contaGeral = gerarContaGeral();
		Conta conta = new Conta();
		conta.setId(contaGeral.getId());
		conta.setContaGeral(contaGeral);
		conta.setImovel(dto.getImovel());
		conta.setReferencia(anoMesConta);
		conta.setIndicadorAlteracaoVencimento(ConstantesSistema.NAO);
		conta.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.INCLUIDA));
		conta.setLigacaoAguaSituacao(dto.getImovel().getLigacaoAguaSituacao());
		conta.setLigacaoEsgotoSituacao(dto.getImovel().getLigacaoEsgotoSituacao());
		conta.setLocalidade(dto.getImovel().getLocalidade());
		conta.setQuadraConta(dto.getImovel().getQuadra());
		conta.setLote(dto.getImovel().getLote());
		conta.setSubLote(dto.getImovel().getSubLote());
		conta.setCodigoSetorComercial(dto.getImovel().getQuadra().getSetorComercial().getCodigo());
		conta.setQuadra(dto.getImovel().getQuadra().getNumeroQuadra());
		conta.setRota(obterRota(dto.getImovel()));
		conta.setDigitoVerificadorConta(new Short(String.valueOf(Util.calculoRepresentacaoNumericaCodigoBarrasModulo10(anoMesConta))));
		conta.setIndicadorCobrancaMulta(ConstantesSistema.NAO);
		conta.setDataVencimentoConta(new Date());
		conta.setDataVencimentoOriginal(new Date());
		conta.setDataValidadeConta(getControladorFaturamento().retornaDataValidadeConta(new Date()));
		conta.setDataInclusao(new GregorianCalendar().getTime());
		conta.setDataEmissao(new GregorianCalendar().getTime());
		conta.setReferenciaContabil(getControladorFaturamento().obterReferenciaContabilConta(parametros, conta.getReferencia()));
		conta.setContaMotivoInclusao(new ContaMotivoInclusao(ContaMotivoInclusao.CANCELAMENTO_DE_PARCELAMENTO));
		conta.setConsumoTarifa(dto.getImovel().getConsumoTarifa());
		conta.setImovelPerfil(dto.getImovel().getImovelPerfil());
		conta.setIndicadorDebitoConta(ConstantesSistema.NAO);
		conta.setNumeroRetificacoes(0);
		conta.setFaturamentoGrupo(getControladorImovel().pesquisarGrupoImovel(dto.getImovel().getId()));
		conta.setNumeroAlteracoesVencimento(0);
		conta.setNumeroBoleto(getControladorFaturamento().verificarGeracaoBoleto(parametros, conta));
		conta.setUltimaAlteracao(new Date());
		conta.setUsuario(usuario);

		BigDecimal percentualEsgoto = dto.getImovel().getLigacaoEsgoto().getPercentual();
		conta.setPercentualEsgoto(percentualEsgoto != null ? percentualEsgoto : BigDecimal.ZERO);
		
		conta.setValorAgua(BigDecimal.ZERO);
		conta.setValorEsgoto(BigDecimal.ZERO);
		conta.setDebitos(debitoACobrar.getValorDebito());
		conta.setValorCreditos(BigDecimal.ZERO);
		GerarImpostosDeduzidosContaHelper impostosDeduzidos = getControladorFaturamento().gerarImpostosDeduzidosConta(
				conta.getImovel().getId(), anoMesConta, conta.getValorAgua(), conta.getValorEsgoto(), conta.getDebitos(), BigDecimal.ZERO, false);
		conta.setValorImposto(impostosDeduzidos.getValorTotalImposto());

		getControladorUtil().inserir(conta);
		gerarDebitoCobrado(conta, dto.getImovel(), debitoACobrar);
		getControladorFaturamento().inserirClienteConta(conta, dto.getImovel());
		getControladorFaturamento().inserirImpostosDeduzidosConta(impostosDeduzidos, conta);
	}

	private void gerarDebitoCobrado(Conta conta, Imovel imovel, DebitoACobrar debitoACobrar) throws ControladorException {
		DebitoCobrado debitoCobrado = new DebitoCobrado();
		debitoCobrado.setDebitoTipo(new DebitoTipo(DebitoTipo.CANCELAMENTO_DE_PARCELAMENTO));
		debitoCobrado.setDebitoCobrado(new Date());
		debitoCobrado.setConta(conta);
		debitoCobrado.setLancamentoItemContabil(new LancamentoItemContabil(LancamentoItemContabil.CANCELAMENTO_DE_PARCELAMENTO));
		debitoCobrado.setLocalidade(imovel.getLocalidade());
		debitoCobrado.setQuadra(imovel.getQuadra());
		debitoCobrado.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
		debitoCobrado.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
		debitoCobrado.setNumeroLote(imovel.getLote());
		debitoCobrado.setNumeroSubLote(imovel.getSubLote());
		debitoCobrado.setAnoMesReferenciaDebito(debitoACobrar.getAnoMesReferenciaDebito());
		debitoCobrado.setAnoMesCobrancaDebito(debitoACobrar.getAnoMesCobrancaDebito());
		debitoCobrado.setValorPrestacao(debitoACobrar.getValorPrestacao());
		debitoCobrado.setNumeroPrestacao(new Short("1"));
		debitoCobrado.setNumeroPrestacaoDebito(debitoACobrar.getNumeroPrestacaoDebito());
		debitoCobrado.setDebitoACobrarGeral(debitoACobrar.getDebitoACobrarGeral());
		debitoCobrado.setFinanciamentoTipo(new FinanciamentoTipo(FinanciamentoTipo.CANCELAMENTO_DE_PARCELAMENTO));
		debitoCobrado.setNumeroParcelaBonus(new Short("0"));
		debitoCobrado.setUltimaAlteracao(new Date());

		Integer id = (Integer) this.getControladorUtil().inserir(debitoCobrado);
		debitoCobrado.setId(id);

		Collection<?> colecaoCategoria = getControladorImovel().obterColecaoCategoriaOuSubcategoriaDoImovel(imovel);
		getControladorFaturamento().inserirDebitoCobradoCategoria(debitoCobrado, colecaoCategoria);
	}

	private Rota obterRota(Imovel imovel) {
		Rota rota = new Rota();
		if (imovel.getRotaAlternativa() != null) {
			rota = imovel.getRotaAlternativa();

		} else {
			rota = imovel.getQuadra().getRota();
		}
		return rota;
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