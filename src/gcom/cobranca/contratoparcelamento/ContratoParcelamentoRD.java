package gcom.cobranca.contratoparcelamento;

import gcom.cobranca.CobrancaForma;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

public class ContratoParcelamentoRD extends ObjetoTransacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;
	
	private String numero;
	
	private String assunto;
	
	private Date dataVigenciaInicio;
	
	private Date dataVigenciaFinal;
	
	private Short indicadorDebitoAcrescimo;
	
	private Short indicadorParcelamentoJuros;
	
	private Short indicadorInformarParcela;
	
	private Integer qtdFaturasParceladas;  
	
	private CobrancaForma cobrancaForma;
	
	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** default constructor */
	public ContratoParcelamentoRD() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(Date dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public Date getDataVigenciaInicio() {
		return dataVigenciaInicio;
	}

	public void setDataVigenciaInicio(Date dataVigenciaInicio) {
		this.dataVigenciaInicio = dataVigenciaInicio;
	}

	public CobrancaForma getCobrancaForma() {
		return cobrancaForma;
	}

	public void setCobrancaForma(CobrancaForma cobrancaForma) {
		this.cobrancaForma = cobrancaForma;
	}

	public Short getIndicadorDebitoAcrescimo() {
		return indicadorDebitoAcrescimo;
	}

	public void setIndicadorDebitoAcrescimo(Short indicadorDebitoAcrescimo) {
		this.indicadorDebitoAcrescimo = indicadorDebitoAcrescimo;
	}

	public Short getIndicadorInformarParcela() {
		return indicadorInformarParcela;
	}

	public void setIndicadorInformarParcela(Short indicadorInformarParcela) {
		this.indicadorInformarParcela = indicadorInformarParcela;
	}

	public Short getIndicadorParcelamentoJuros() {
		return indicadorParcelamentoJuros;
	}

	public void setIndicadorParcelamentoJuros(Short indicadorParcelamentoJuros) {
		this.indicadorParcelamentoJuros = indicadorParcelamentoJuros;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getQtdFaturasParceladas() {
		return qtdFaturasParceladas;
	}

	public void setQtdFaturasParceladas(Integer qtdFaturasParceladas) {
		this.qtdFaturasParceladas = qtdFaturasParceladas;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getDataVigenciaInicioFormatada(){
		return Util.formatarData(this.getDataVigenciaInicio());
	}
	
	public String getDataVigenciaFinalFormatada(){
		return Util.formatarData(this.getDataVigenciaFinal());
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroContratoParcelamentoRD filtro = new FiltroContratoParcelamentoRD();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroContratoParcelamentoRD.CONTRATO_PARCELAMENTO_RD_ID, this.getId()));
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "numero" };
		return retorno;
	}

}
