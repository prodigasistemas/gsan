package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class ResolucaoDiretoria extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String numeroResolucaoDiretoria;

	private String descricaoAssunto;

	private Date dataVigenciaInicio;

	private Date dataVigenciaFim;

	private Date ultimaAlteracao;

	private Short indicadorParcelamentoUnico;
	
	private Short indicadorUtilizacaoLivre;
	
	private Short indicadorDescontoFaixaReferenciaConta;
	
	private Short indicadorDescontoSancoes;
	
	private Short indicadorParcelamentoLojaVirtual;
	
	private Short indicadorParcelasEmAtraso;
	
	private Short indicadorParcelamentoEmAndamento;
	
	private ResolucaoDiretoria rdParcelasEmAtraso;
	
	private ResolucaoDiretoria rdParcelamentoEmAndamento;
	
	private Short indicadorNegociacaoSoAVista;
	
	private Short indicadorDescontoSoEmContaAVista;
	
	private BigDecimal percentualDoacao;

	public ResolucaoDiretoria() {}
	
	public ResolucaoDiretoria(
			String numeroResolucaoDiretoria, 
			String descricaoAssunto, 
			Date dataVigenciaInicio, 
			Date dataVigenciaFim, 
			Date ultimaAlteracao, 
			Short indicadorParcelamentoUnico,
			Short indicadorUtilizacaoLivre, 
			Short indicadorDescontoSancoes) {
		
		this.numeroResolucaoDiretoria = numeroResolucaoDiretoria;
		this.descricaoAssunto = descricaoAssunto;
		this.dataVigenciaInicio = dataVigenciaInicio;
		this.dataVigenciaFim = dataVigenciaFim;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorParcelamentoUnico = indicadorParcelamentoUnico;
		this.indicadorUtilizacaoLivre = indicadorUtilizacaoLivre;
		this.indicadorDescontoSancoes = indicadorDescontoSancoes;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroResolucaoDiretoria() {
		return numeroResolucaoDiretoria;
	}

	public void setNumeroResolucaoDiretoria(String numeroResolucaoDiretoria) {
		this.numeroResolucaoDiretoria = numeroResolucaoDiretoria;
	}

	public String getDescricaoAssunto() {
		return descricaoAssunto;
	}

	public void setDescricaoAssunto(String descricaoAssunto) {
		this.descricaoAssunto = descricaoAssunto;
	}

	public Date getDataVigenciaInicio() {
		return dataVigenciaInicio;
	}

	public void setDataVigenciaInicio(Date dataVigenciaInicio) {
		this.dataVigenciaInicio = dataVigenciaInicio;
	}

	public Date getDataVigenciaFim() {
		return dataVigenciaFim;
	}

	public void setDataVigenciaFim(Date dataVigenciaFim) {
		this.dataVigenciaFim = dataVigenciaFim;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorParcelamentoUnico() {
		return indicadorParcelamentoUnico;
	}

	public void setIndicadorParcelamentoUnico(Short indicadorParcelamentoUnico) {
		this.indicadorParcelamentoUnico = indicadorParcelamentoUnico;
	}

	public Short getIndicadorUtilizacaoLivre() {
		return indicadorUtilizacaoLivre;
	}

	public void setIndicadorUtilizacaoLivre(Short indicadorUtilizacaoLivre) {
		this.indicadorUtilizacaoLivre = indicadorUtilizacaoLivre;
	}

	public Short getIndicadorDescontoFaixaReferenciaConta() {
		return indicadorDescontoFaixaReferenciaConta;
	}

	public void setIndicadorDescontoFaixaReferenciaConta(Short indicadorDescontoFaixaReferenciaConta) {
		this.indicadorDescontoFaixaReferenciaConta = indicadorDescontoFaixaReferenciaConta;
	}

	public Short getIndicadorDescontoSancoes() {
		return indicadorDescontoSancoes;
	}

	public void setIndicadorDescontoSancoes(Short indicadorDescontoSancoes) {
		this.indicadorDescontoSancoes = indicadorDescontoSancoes;
	}

	public Short getIndicadorParcelamentoLojaVirtual() {
		return indicadorParcelamentoLojaVirtual;
	}

	public void setIndicadorParcelamentoLojaVirtual(Short indicadorParcelamentoLojaVirtual) {
		this.indicadorParcelamentoLojaVirtual = indicadorParcelamentoLojaVirtual;
	}

	public Short getIndicadorParcelasEmAtraso() {
		return indicadorParcelasEmAtraso;
	}

	public void setIndicadorParcelasEmAtraso(Short indicadorParcelasEmAtraso) {
		this.indicadorParcelasEmAtraso = indicadorParcelasEmAtraso;
	}

	public Short getIndicadorParcelamentoEmAndamento() {
		return indicadorParcelamentoEmAndamento;
	}

	public void setIndicadorParcelamentoEmAndamento(Short indicadorParcelamentoEmAndamento) {
		this.indicadorParcelamentoEmAndamento = indicadorParcelamentoEmAndamento;
	}

	public ResolucaoDiretoria getRdParcelasEmAtraso() {
		return rdParcelasEmAtraso;
	}

	public void setRdParcelasEmAtraso(ResolucaoDiretoria rdParcelasEmAtraso) {
		this.rdParcelasEmAtraso = rdParcelasEmAtraso;
	}

	public ResolucaoDiretoria getRdParcelamentoEmAndamento() {
		return rdParcelamentoEmAndamento;
	}

	public void setRdParcelamentoEmAndamento(ResolucaoDiretoria rdParcelamentoEmAndamento) {
		this.rdParcelamentoEmAndamento = rdParcelamentoEmAndamento;
	}

	public Short getIndicadorNegociacaoSoAVista() {
		return indicadorNegociacaoSoAVista;
	}

	public void setIndicadorNegociacaoSoAVista(Short indicadorNegociacaoSoAVista) {
		this.indicadorNegociacaoSoAVista = indicadorNegociacaoSoAVista;
	}

	public Short getIndicadorDescontoSoEmContaAVista() {
		return indicadorDescontoSoEmContaAVista;
	}

	public void setIndicadorDescontoSoEmContaAVista(Short indicadorDescontoSoEmContaAVista) {
		this.indicadorDescontoSoEmContaAVista = indicadorDescontoSoEmContaAVista;
	}

	public BigDecimal getPercentualDoacao() {
		return percentualDoacao;
	}

	public void setPercentualDoacao(BigDecimal percentualDoacao) {
		this.percentualDoacao = percentualDoacao;
	}

	public Filtro retornaFiltro() {
		FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
		filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, this.getId()));

		return filtro;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
