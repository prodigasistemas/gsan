package gcom.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ParcelamentoPerfil extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private BigDecimal percentualDescontoAcrescimoMulta;

	private BigDecimal percentualDescontoAcrescimoJurosMora;

	private BigDecimal percentualDescontoAcrescimoAtualizacaoMonetaria;

	private Date ultimaAlteracao;

	private Subcategoria subcategoria;

	private ImovelSituacaoTipo imovelSituacaoTipo;

	private ImovelPerfil imovelPerfil;

	private ResolucaoDiretoria resolucaoDiretoria;

	private BigDecimal percentualTarifaMinimaPrestacao;

	private Integer numeroConsumoMinimo;

	private BigDecimal percentualVariacaoConsumoMedio;

	private Short indicadorChequeDevolvido;

	private Short indicadorSancoesUnicaConta;

	private Short indicadorRetroativoTarifaSocial;

	private Integer anoMesReferenciaLimiteInferior;

	private Integer anoMesReferenciaLimiteSuperior;

	private BigDecimal percentualDescontoTarifaSocial;

	private Integer parcelaQuantidadeMinimaFatura;

	private Short indicadorAlertaParcelaMinima;

	private Integer numeroConsumoEconomia;

	private BigDecimal numeroAreaConstruida;

	private Categoria categoria;

	private BigDecimal percentualDescontoSancao;

	private Integer quantidadeEconomias;

	private Short capacidadeHidrometro;

	private Short indicadorEntradaMinima;

	private Integer quantidadeMaximaReparcelamento;

	private BigDecimal percentualDescontoAcrescimoPagamentoAVista;

	private Date dataLimiteDescontoPagamentoAVista;
	
	private BigDecimal percentualDescontoTotalPagamentoAVista;

	public Short getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(Short capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public BigDecimal getPercentualTarifaMinimaPrestacao() {
		return percentualTarifaMinimaPrestacao;
	}

	public void setPercentualTarifaMinimaPrestacao(BigDecimal percentualTarifaMinimaPrestacao) {
		this.percentualTarifaMinimaPrestacao = percentualTarifaMinimaPrestacao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();

		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.ID, this.getId()));
		return filtroParcelamentoPerfil;
	}

	public ParcelamentoPerfil(BigDecimal percentualDescontoAcrescimoMulta, BigDecimal percentualDescontoAcrescimoJurosMora, BigDecimal percentualDescontoAcrescimoAtualizacaoMonetaria,
			Date ultimaAlteracao, Subcategoria subcategoria, ImovelSituacaoTipo imovelSituacaoTipo, ImovelPerfil imovelPerfil, ResolucaoDiretoria resolucaoDiretoria) {
		this.percentualDescontoAcrescimoMulta = percentualDescontoAcrescimoMulta;
		this.percentualDescontoAcrescimoJurosMora = percentualDescontoAcrescimoJurosMora;
		this.percentualDescontoAcrescimoAtualizacaoMonetaria = percentualDescontoAcrescimoAtualizacaoMonetaria;
		this.ultimaAlteracao = ultimaAlteracao;
		this.subcategoria = subcategoria;
		this.imovelSituacaoTipo = imovelSituacaoTipo;
		this.imovelPerfil = imovelPerfil;
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public ParcelamentoPerfil() {
	}

	public ParcelamentoPerfil(Subcategoria subcategoria, ImovelSituacaoTipo imovelSituacaoTipo, ImovelPerfil imovelPerfil, ResolucaoDiretoria resolucaoDiretoria) {
		this.subcategoria = subcategoria;
		this.imovelSituacaoTipo = imovelSituacaoTipo;
		this.imovelPerfil = imovelPerfil;
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentualDescontoAcrescimoMulta() {
		return this.percentualDescontoAcrescimoMulta;
	}

	public void setPercentualDescontoAcrescimoMulta(BigDecimal percentualDescontoAcrescimoMulta) {
		this.percentualDescontoAcrescimoMulta = percentualDescontoAcrescimoMulta;
	}

	public BigDecimal getPercentualDescontoAcrescimoJurosMora() {
		return this.percentualDescontoAcrescimoJurosMora;
	}

	public void setPercentualDescontoAcrescimoJurosMora(BigDecimal percentualDescontoAcrescimoJurosMora) {
		this.percentualDescontoAcrescimoJurosMora = percentualDescontoAcrescimoJurosMora;
	}

	public BigDecimal getPercentualDescontoAcrescimoAtualizacaoMonetaria() {
		return this.percentualDescontoAcrescimoAtualizacaoMonetaria;
	}

	public void setPercentualDescontoAcrescimoAtualizacaoMonetaria(BigDecimal percentualDescontoAcrescimoAtualizacaoMonetaria) {
		this.percentualDescontoAcrescimoAtualizacaoMonetaria = percentualDescontoAcrescimoAtualizacaoMonetaria;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Subcategoria getSubcategoria() {
		return this.subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public ImovelSituacaoTipo getImovelSituacaoTipo() {
		return this.imovelSituacaoTipo;
	}

	public void setImovelSituacaoTipo(ImovelSituacaoTipo imovelSituacaoTipo) {
		this.imovelSituacaoTipo = imovelSituacaoTipo;
	}

	public ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return this.resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getIndicadorChequeDevolvido() {
		return indicadorChequeDevolvido;
	}

	public void setIndicadorChequeDevolvido(Short indicadorChequeDevolvido) {
		this.indicadorChequeDevolvido = indicadorChequeDevolvido;
	}

	public Short getIndicadorSancoesUnicaConta() {
		return indicadorSancoesUnicaConta;
	}

	public void setIndicadorSancoesUnicaConta(Short indicadorSancoesUnicaConta) {
		this.indicadorSancoesUnicaConta = indicadorSancoesUnicaConta;
	}

	public Integer getNumeroConsumoMinimo() {
		return numeroConsumoMinimo;
	}

	public void setNumeroConsumoMinimo(Integer numeroConsumoMinimo) {
		this.numeroConsumoMinimo = numeroConsumoMinimo;
	}

	public BigDecimal getPercentualVariacaoConsumoMedio() {
		return percentualVariacaoConsumoMedio;
	}

	public void setPercentualVariacaoConsumoMedio(BigDecimal percentualVariacaoConsumoMedio) {
		this.percentualVariacaoConsumoMedio = percentualVariacaoConsumoMedio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getNumeroAreaConstruida() {
		return numeroAreaConstruida;
	}

	public void setNumeroAreaConstruida(BigDecimal numeroAreaConstruida) {
		this.numeroAreaConstruida = numeroAreaConstruida;
	}

	public Integer getNumeroConsumoEconomia() {
		return numeroConsumoEconomia;
	}

	public void setNumeroConsumoEconomia(Integer numeroConsumoEconomia) {
		this.numeroConsumoEconomia = numeroConsumoEconomia;
	}

	public Integer getAnoMesReferenciaLimiteInferior() {
		return anoMesReferenciaLimiteInferior;
	}

	public void setAnoMesReferenciaLimiteInferior(Integer anoMesReferenciaLimiteInferior) {
		this.anoMesReferenciaLimiteInferior = anoMesReferenciaLimiteInferior;
	}

	public Integer getAnoMesReferenciaLimiteSuperior() {
		return anoMesReferenciaLimiteSuperior;
	}

	public void setAnoMesReferenciaLimiteSuperior(Integer anoMesReferenciaLimiteSuperior) {
		this.anoMesReferenciaLimiteSuperior = anoMesReferenciaLimiteSuperior;
	}

	public Short getIndicadorAlertaParcelaMinima() {
		return indicadorAlertaParcelaMinima;
	}

	public void setIndicadorAlertaParcelaMinima(Short indicadorAlertaParcelaMinima) {
		this.indicadorAlertaParcelaMinima = indicadorAlertaParcelaMinima;
	}

	public Short getIndicadorRetroativoTarifaSocial() {
		return indicadorRetroativoTarifaSocial;
	}

	public void setIndicadorRetroativoTarifaSocial(Short indicadorRetroativoTarifaSocial) {
		this.indicadorRetroativoTarifaSocial = indicadorRetroativoTarifaSocial;
	}

	public Integer getParcelaQuantidadeMinimaFatura() {
		return parcelaQuantidadeMinimaFatura;
	}

	public void setParcelaQuantidadeMinimaFatura(Integer parcelaQuantidadeMinimaFatura) {
		this.parcelaQuantidadeMinimaFatura = parcelaQuantidadeMinimaFatura;
	}

	public BigDecimal getPercentualDescontoTarifaSocial() {
		return percentualDescontoTarifaSocial;
	}

	public void setPercentualDescontoTarifaSocial(BigDecimal percentualDescontoTarifaSocial) {
		this.percentualDescontoTarifaSocial = percentualDescontoTarifaSocial;
	}

	public BigDecimal getPercentualDescontoSancao() {
		return percentualDescontoSancao;
	}

	public void setPercentualDescontoSancao(BigDecimal percentualDescontoSancao) {
		this.percentualDescontoSancao = percentualDescontoSancao;
	}

	public Short getIndicadorEntradaMinima() {
		return indicadorEntradaMinima;
	}

	public void setIndicadorEntradaMinima(Short indicadorEntradaMinima) {
		this.indicadorEntradaMinima = indicadorEntradaMinima;
	}

	public Integer getQuantidadeMaximaReparcelamento() {
		return quantidadeMaximaReparcelamento;
	}

	public void setQuantidadeMaximaReparcelamento(Integer quantidadeMaximaReparcelamento) {
		this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
	}

	public Date getDataLimiteDescontoPagamentoAVista() {
		return dataLimiteDescontoPagamentoAVista;
	}

	public void setDataLimiteDescontoPagamentoAVista(Date dataLimiteDescontoPagamentoAVista) {
		this.dataLimiteDescontoPagamentoAVista = dataLimiteDescontoPagamentoAVista;
	}

	public BigDecimal getPercentualDescontoAcrescimoPagamentoAVista() {
		return percentualDescontoAcrescimoPagamentoAVista;
	}

	public void setPercentualDescontoAcrescimoPagamentoAVista(BigDecimal percentualDescontoAcrescimoPagamentoAVista) {
		this.percentualDescontoAcrescimoPagamentoAVista = percentualDescontoAcrescimoPagamentoAVista;
	}

	public BigDecimal getPercentualDescontoTotalPagamentoAVista() {
		return percentualDescontoTotalPagamentoAVista;
	}

	public void setPercentualDescontoTotalPagamentoAVista(BigDecimal percentualDescontoTotalPagamentoAVista) {
		this.percentualDescontoTotalPagamentoAVista = percentualDescontoTotalPagamentoAVista;
	}
	
}
