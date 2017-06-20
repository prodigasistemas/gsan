package gcom.cobranca.controladores;

import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.parcelamento.FiltroParcelamento;
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

	protected IRepositorioParcelamentoHBM repositorioParcelamento;

	public void ejbCreate() throws CreateException {
		repositorioParcelamento = RepositorioParcelamentoHBM.getInstancia();
	}

	public void cancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) throws ControladorException {
		int idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {
			List<CancelarParcelamentoDTO> parcelamentos = repositorioParcelamento.pesquisarParcelamentosParaCancelar();

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
		try {
			cancelarDebitoACobrar(dto.getIdParcelamento());
			cancelarCreditoARealizar(dto.getIdParcelamento());
			
			Parcelamento parcelamento = repositorioParcelamento.pesquisarPorId(dto.getIdParcelamento());
			parcelamento.setParcelamentoSituacao(new ParcelamentoSituacao(ParcelamentoSituacao.CANCELADO));
			parcelamento.setUltimaAlteracao(new Date());
			getControladorUtil().atualizar(parcelamento);
			
			gerarContaIncluida(dto, usuario);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	@SuppressWarnings("unchecked")
	private void cancelarDebitoACobrar(Integer idParcelamento) {
		try {
			Filtro filtro = new FiltroDebitoACobrar();
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, idParcelamento));
			Collection<DebitoACobrar> colecao = getControladorUtil().pesquisar(filtro, DebitoACobrar.class.toString());

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
			Collection<CreditoARealizar> colecao = getControladorUtil().pesquisar(filtro, CreditoARealizar.class.toString());

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

	private BigDecimal calcularSaldoDevedor(CancelarParcelamentoDTO dto, Imovel imovel) throws ControladorException {
		BigDecimal valorPrestacoesCobradas = dto.getValorPrestacao().multiply(BigDecimal.valueOf(dto.getNumeroPrestacoesCobradas()));
		BigDecimal valorCobrado = dto.getValorEntrada().add(valorPrestacoesCobradas);
		BigDecimal saldoDevedor = dto.getValorOriginal().subtract(valorCobrado);
		
		BigDecimal acrescimos = calcularAcrescimosPorImpontualidade(dto, imovel, saldoDevedor);
		
		return saldoDevedor.add(acrescimos).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal calcularAcrescimosPorImpontualidade(CancelarParcelamentoDTO dto, Imovel imovel, BigDecimal saldoDevedor) throws ControladorException {
		BigDecimal acrescimos = BigDecimal.ZERO;
		
		try {
			Parcelamento parcelamento = obterParcelamento(dto.getIdParcelamento());
			Date dataProximaConta = obterDataProximaConta(imovel);
			
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			CalcularAcrescimoPorImpontualidadeHelper helper = getControladorCobranca().calcularAcrescimoPorImpontualidade(
					parcelamento.getAnoMesReferenciaFaturamento(),
					parcelamento.getUltimaAlteracao(), 
					dataProximaConta,
					saldoDevedor,
					BigDecimal.ZERO,
					ConstantesSistema.SIM,
					sistemaParametro.getAnoMesArrecadacao().toString(),
					null,
					ConstantesSistema.INDICADOR_ARRECADACAO_DESATIVO);
			
			acrescimos = helper.getValorTotalAcrescimosImpontualidade();
			
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("Erro ao calcular acréscimo para cancelamento de parcelamento.", e);
		}

		return acrescimos;
	}
	
	@SuppressWarnings("unchecked")
	private Parcelamento obterParcelamento(Integer idParcelamento) {
		Parcelamento parcelamento = null;
		try {
			Filtro filtro = new FiltroParcelamento();
			filtro.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, idParcelamento));
			Collection<Parcelamento> colecao;

			colecao = getControladorUtil().pesquisar(filtro, Parcelamento.class.toString());

			if (colecao != null && colecao.isEmpty())
				parcelamento = (Parcelamento) colecao.iterator().next();
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return parcelamento;
	}
	
	@SuppressWarnings("unchecked")
	private Imovel obterImovel(Integer idImovel) {
		Imovel imovel = null;
		try {
			Filtro filtro = new FiltroImovel();
			filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			Collection<Imovel> colecao;

			colecao = getControladorUtil().pesquisar(filtro, Imovel.class.toString());

			if (colecao != null && colecao.isEmpty())
				imovel = (Imovel) colecao.iterator().next();
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return imovel;
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
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		
		Conta conta = getControladorFaturamento().obterContaImovel(idImovel, sistemaParametro.getAnoMesFaturamento());
		
		if (conta == null) {
			return sistemaParametro.getAnoMesFaturamento();
		} else {
			return Util.somaMesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento(), 1);
		}
	}

	private DebitoACobrar gerarDebitoACobrar(CancelarParcelamentoDTO dto, Imovel imovel, Usuario usuario) throws ControladorException {
		DebitoACobrar debitoACobrar = new DebitoACobrar();

		try {
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			DebitoACobrarGeral debitoACobrarGeral = gerarDebitoACobrarGeral();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);
			debitoACobrar.setImovel(imovel);
			debitoACobrar.setValorDebito(calcularSaldoDevedor(dto, imovel));
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(sistemaParametro.getAnoMesFaturamento());
			debitoACobrar.setAnoMesCobrancaDebito(sistemaParametro.getAnoMesArrecadacao());
			debitoACobrar.setAnoMesReferenciaContabil(getControladorFaturamento().obterReferenciaContabilConta(sistemaParametro));
			debitoACobrar.setLocalidade(imovel.getLocalidade());
			debitoACobrar.setQuadra(imovel.getQuadra());
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(imovel.getLote());
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());
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
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		
		Imovel imovel = obterImovel(dto.getIdImovel());
		DebitoACobrar debitoACobrar = gerarDebitoACobrar(dto, imovel, usuario);
		Integer anoMesConta = getControladorFaturamento().pesquisarAnoMesReferenciaFaturamentoGrupo(imovel.getId());
		
		ContaGeral contaGeral = gerarContaGeral();
		Conta conta = new Conta();
		conta.setId(contaGeral.getId());
		conta.setContaGeral(contaGeral);
		conta.setImovel(imovel);
		conta.setReferencia(anoMesConta);
		conta.setIndicadorAlteracaoVencimento(ConstantesSistema.NAO);
		conta.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.INCLUIDA));
		conta.setLigacaoAguaSituacao(imovel.getLigacaoAguaSituacao());
		conta.setLigacaoEsgotoSituacao(imovel.getLigacaoEsgotoSituacao());
		conta.setLocalidade(imovel.getLocalidade());
		conta.setQuadraConta(imovel.getQuadra());
		conta.setLote(imovel.getLote());
		conta.setSubLote(imovel.getSubLote());
		conta.setCodigoSetorComercial(imovel.getQuadra().getSetorComercial().getCodigo());
		conta.setQuadra(imovel.getQuadra().getNumeroQuadra());
		conta.setRota(obterRota(imovel));
		conta.setDigitoVerificadorConta(new Short(String.valueOf(Util.calculoRepresentacaoNumericaCodigoBarrasModulo10(anoMesConta))));
		conta.setIndicadorCobrancaMulta(ConstantesSistema.NAO);
		conta.setDataVencimentoConta(new Date());
		conta.setDataVencimentoOriginal(new Date());
		conta.setDataValidadeConta(getControladorFaturamento().retornaDataValidadeConta(new Date()));
		conta.setDataInclusao(new GregorianCalendar().getTime());
		conta.setDataEmissao(new GregorianCalendar().getTime());
		conta.setReferenciaContabil(getControladorFaturamento().obterReferenciaContabilConta(sistemaParametro, conta.getReferencia()));
		conta.setContaMotivoInclusao(new ContaMotivoInclusao(ContaMotivoInclusao.CANCELAMENTO_DE_PARCELAMENTO));
		conta.setConsumoTarifa(imovel.getConsumoTarifa());
		conta.setImovelPerfil(imovel.getImovelPerfil());
		conta.setIndicadorDebitoConta(ConstantesSistema.NAO);
		conta.setNumeroRetificacoes(0);
		conta.setFaturamentoGrupo(getControladorImovel().pesquisarGrupoImovel(imovel.getId()));
		conta.setNumeroAlteracoesVencimento(0);
		conta.setNumeroBoleto(getControladorFaturamento().verificarGeracaoBoleto(sistemaParametro, conta));
		conta.setUltimaAlteracao(new Date());
		conta.setUsuario(usuario);

		BigDecimal percentualEsgoto = imovel.getLigacaoEsgoto().getPercentual();
		conta.setPercentualEsgoto(percentualEsgoto != null ? percentualEsgoto : BigDecimal.ZERO);
		
		conta.setValorAgua(BigDecimal.ZERO);
		conta.setValorEsgoto(BigDecimal.ZERO);
		conta.setDebitos(debitoACobrar.getValorDebito());
		conta.setValorCreditos(BigDecimal.ZERO);
		GerarImpostosDeduzidosContaHelper impostosDeduzidos = getControladorFaturamento().gerarImpostosDeduzidosConta(
				conta.getImovel().getId(), anoMesConta, conta.getValorAgua(), conta.getValorEsgoto(), conta.getDebitos(), BigDecimal.ZERO, false);
		conta.setValorImposto(impostosDeduzidos.getValorTotalImposto());

		getControladorUtil().inserir(conta);
		gerarDebitoCobrado(conta, imovel, debitoACobrar);
		getControladorFaturamento().inserirClienteConta(conta, imovel);
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