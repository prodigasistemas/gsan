package gcom.cadastro.imovel;

import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class PiscinaVolumeFaixa extends TabelaAuxiliarFaixaReal implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field *//*
	private Integer id;

	*//** persistent field *//*
	private BigDecimal volumeMenorFaixa;

	*//** persistent field *//*
	private BigDecimal volumeMaiorFaixa;

	*//** nullable persistent field *//*
	private Short indicadorUso;

	*//** nullable persistent field *//*
	private Date ultimaAlteracao;

	*//** atributo para fazer a junçao das faixas *//*
	private String faixaCompleta;


	*//** full constructor *//*
	public PiscinaVolumeFaixa(BigDecimal volumeMenorFaixa,
			BigDecimal volumeMaiorFaixa, Short indicadorUso,
			Date ultimaAlteracao) {
		this.volumeMenorFaixa = volumeMenorFaixa;
		this.volumeMaiorFaixa = volumeMaiorFaixa;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	*//** default constructor *//*
	public PiscinaVolumeFaixa() {
	}

	*//** minimal constructor *//*
	public PiscinaVolumeFaixa(BigDecimal volumeMenorFaixa,
			BigDecimal volumeMaiorFaixa) {
		this.volumeMenorFaixa = volumeMenorFaixa;
		this.volumeMaiorFaixa = volumeMaiorFaixa;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getVolumeMenorFaixa() {
		return this.volumeMenorFaixa;
	}

	public void setVolumeMenorFaixa(BigDecimal volumeMenorFaixa) {
		this.volumeMenorFaixa = volumeMenorFaixa;
	}

	public BigDecimal getVolumeMaiorFaixa() {
		return this.volumeMaiorFaixa;
	}

	public void setVolumeMaiorFaixa(BigDecimal volumeMaiorFaixa) {
		this.volumeMaiorFaixa = volumeMaiorFaixa;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	*//**
	 * Retorna o valor de faixaCompleta
	 * 
	 * @return O valor de faixaCompleta
	 *//*
	public String getFaixaCompleta() {
		faixaCompleta = this.getVolumeMenorFaixa() + " a "
				+ this.getVolumeMaiorFaixa() + "m3";
		return faixaCompleta;
	}

	*//**
	 * Seta o valor de faixaCompleta
	 * 
	 * @param faixaCompleta
	 *            O novo valor de faixaCompleta
	 *//*
	public void setFaixaCompleta(String faixaCompleta) {
		this.faixaCompleta = faixaCompleta;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
*/}
