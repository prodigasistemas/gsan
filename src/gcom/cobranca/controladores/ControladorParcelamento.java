package gcom.cobranca.controladores;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.repositorios.IRepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.RepositorioParcelamentoHBM;
import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
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

public class ControladorParcelamento extends ControladorComum {

	private static final long serialVersionUID = -2063305788601928963L;

	protected IRepositorioParcelamentoHBM repositorioParcelamento;

	public void ejbCreate() throws CreateException {
		repositorioParcelamento = RepositorioParcelamentoHBM.getInstancia();
	}
	
	public CancelarParcelamentoDTO pesquisarParcelamentoParaCancelamento(Integer idParcelamento) {
		try {
			return repositorioParcelamento.pesquisarParcelamentoParaCancelar(idParcelamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void cancelarParcelamentos(Usuario usuarioLogado) throws ControladorException {
		try {
			List<CancelarParcelamentoDTO> parcelamentos = repositorioParcelamento.pesquisarParcelamentosParaCancelar();

			for (CancelarParcelamentoDTO dto : parcelamentos) {
				cancelarParcelamento(dto, usuarioLogado);
				gerarContaIncluida(dto, usuarioLogado);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	public void cancelarParcelamento(CancelarParcelamentoDTO parcelamentoDto, Usuario usuarioLogado) {
		cancelarDebitoACobrar(parcelamentoDto.getIdParcelamento());
		cancelarCreditoARealizar(parcelamentoDto.getIdParcelamento());
		
		/**
		 * TODO - CANCELAR PARCELAMENTO
		 */
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

				super.getControladorUtil().atualizar(debito);
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

				super.getControladorUtil().atualizar(credito);
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
		BigDecimal acrescimos = BigDecimal.ZERO;
		
		try {
			Parcelamento parcelamento = this.obterParcelamento(dto.getIdParcelamento());
			Date dataProximaConta = this.obterDataProximaConta(dto.getIdImovel());
			
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

			colecao = super.getControladorUtil().pesquisar(filtro, Parcelamento.class.toString());

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

			colecao = super.getControladorUtil().pesquisar(filtro, Imovel.class.toString());

			if (colecao != null && colecao.isEmpty())
				imovel = (Imovel) colecao.iterator().next();
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return imovel;
	}
	
	private Date obterDataProximaConta(Integer idImovel) throws ControladorException {
		Integer proximaReferencia = obterReferenciaProximaConta(idImovel);
		Imovel imovel = obterImovel(idImovel);
		
		if (imovel.getDiaVencimento() != null) {
			return Util.criarData(imovel.getDiaVencimento(), Util.obterMes(proximaReferencia), Util.obterAno(proximaReferencia));
		} else {
			FaturamentoGrupo grupo = getControladorImovel().pesquisarGrupoImovel(idImovel);
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

	private DebitoACobrar gerarDebitoACobrar(CancelarParcelamentoDTO dto, Usuario usuarioLogado) throws ControladorException {
		DebitoACobrar debitoACobrar = new DebitoACobrar();

		try {
			DebitoACobrarGeral debitoACobrarGeral = gerarDebitoACobrarGeral();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			Imovel imovel = obterImovel(dto.getIdImovel());
			debitoACobrar.setImovel(imovel);

			debitoACobrar.setValorDebito(calcularSaldoDevedor(dto));
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));

			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(sistemaParametro.getAnoMesFaturamento());
			debitoACobrar.setAnoMesCobrancaDebito(sistemaParametro.getAnoMesArrecadacao());

			int anoMesAtual = Util.getAnoMesComoInt(new Date());
			if (sistemaParametro.getAnoMesFaturamento().compareTo(anoMesAtual) < 0) {
				debitoACobrar.setAnoMesReferenciaContabil(anoMesAtual);
			} else {
				debitoACobrar.setAnoMesReferenciaContabil(sistemaParametro.getAnoMesFaturamento());
			}
			
			debitoACobrar.setLocalidade(imovel.getLocalidade());
			debitoACobrar.setQuadra(imovel.getQuadra());
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(imovel.getLote());
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());
			
			debitoACobrar.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
			
			debitoACobrar.setDebitoTipo(new DebitoTipo(DebitoTipo.CANCELAMENTO_PARCELAMENTO));
			debitoACobrar.setFinanciamentoTipo(new FinanciamentoTipo(FinanciamentoTipo.CANCELAMENTO_PARCELAMENTO));
			debitoACobrar.setLancamentoItemContabil(new LancamentoItemContabil(LancamentoItemContabil.CANCELAMENTO_PARCELAMENTO));
			
			debitoACobrar.setDebitoCreditoSituacaoAnterior(null);
			debitoACobrar.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL));
			
			debitoACobrar.setParcelamentoGrupo(null);
			debitoACobrar.setCobrancaForma(new CobrancaForma(CobrancaForma.COBRANCA_EM_CONTA));
			
			debitoACobrar.setUsuario(usuarioLogado);
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
	
	/**
	 * TODO
	 */
	private void gerarContaIncluida(CancelarParcelamentoDTO dto, Usuario usuarioLogado) throws ControladorException {
		DebitoACobrar debito = gerarDebitoACobrar(dto, usuarioLogado);
		
		// TODO - Gerar conta incluída
	}

	private DebitoCreditoSituacao getSituacaoCancelada() {
		return new DebitoCreditoSituacao(DebitoCreditoSituacao.CANCELADA);
	}
}