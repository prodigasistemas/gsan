package gcom.cadastro.imovel;

import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 */
public class ReservatorioVolumeFaixa extends TabelaAuxiliarFaixaReal implements Serializable {
	
	private static final long serialVersionUID = 1L;

/*    *//**
     * identifier field
     *//*
    private Integer id;

    *//**
     * persistent field
     *//*
    private BigDecimal volumeMenorFaixa;

    *//**
     * nullable persistent field
     *//*
    private BigDecimal volumeMaiorFaixa;

    *//**
     * nullable persistent field
     *//*
    private Short indicadorUso;

    *//**
     * nullable persistent field
     *//*
    private Date ultimaAlteracao;

    //atributo para fazer a junçao das faixas
    private String faixaCompleta;

    *//**
     * full constructor
     * 
     * @param volumeMenorFaixa
     *            Descrição do parâmetro
     * @param volumeMaiorFaixa
     *            Descrição do parâmetro
     * @param indicadorUso
     *            Descrição do parâmetro
     * @param ultimaAlteracao
     *            Descrição do parâmetro
     *//*
    public ReservatorioVolumeFaixa(BigDecimal volumeMenorFaixa,
            BigDecimal volumeMaiorFaixa, Short indicadorUso,
            Date ultimaAlteracao) {
        this.volumeMenorFaixa = volumeMenorFaixa;
        this.volumeMaiorFaixa = volumeMaiorFaixa;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    *//**
     * default constructor
     *//*
    public ReservatorioVolumeFaixa() {
    }

    *//**
     * minimal constructor
     * 
     * @param volumeMenorFaixa
     *            Descrição do parâmetro
     *//*
    public ReservatorioVolumeFaixa(BigDecimal volumeMenorFaixa) {
        this.volumeMenorFaixa = volumeMenorFaixa;
    }

    *//**
     * Retorna o valor de id
     * 
     * @return O valor de id
     *//*
    public Integer getId() {
        return this.id;
    }

    *//**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     *//*
    public void setId(Integer id) {
        this.id = id;
    }

    *//**
     * Retorna o valor de volumeMenorFaixa
     * 
     * @return O valor de volumeMenorFaixa
     *//*
    public BigDecimal getVolumeMenorFaixa() {
        return this.volumeMenorFaixa;
    }

    *//**
     * Seta o valor de volumeMenorFaixa
     * 
     * @param volumeMenorFaixa
     *            O novo valor de volumeMenorFaixa
     *//*
    public void setVolumeMenorFaixa(BigDecimal volumeMenorFaixa) {
        this.volumeMenorFaixa = volumeMenorFaixa;
    }

    *//**
     * Retorna o valor de volumeMaiorFaixa
     * 
     * @return O valor de volumeMaiorFaixa
     *//*
    public BigDecimal getVolumeMaiorFaixa() {
        return this.volumeMaiorFaixa;
    }

    *//**
     * Seta o valor de volumeMaiorFaixa
     * 
     * @param volumeMaiorFaixa
     *            O novo valor de volumeMaiorFaixa
     *//*
    public void setVolumeMaiorFaixa(BigDecimal volumeMaiorFaixa) {
        this.volumeMaiorFaixa = volumeMaiorFaixa;
    }

    *//**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     *//*
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    *//**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     *//*
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    *//**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     *//*
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    *//**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     *//*
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    *//**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     *//*
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
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
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}*/
}
