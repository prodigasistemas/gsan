package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class IndicesAcrescimosImpontualidade extends ObjetoTransacao  {
	
	private static final long serialVersionUID = 1L;
	


	public Filtro retornaFiltro(){
		FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();

		filtroIndicesAcrescimosImpontualidade.adicionarParametro(new ParametroSimples(FiltroIndicesAcrescimosImpontualidade.ID,
				this.getId()));

		
		return filtroIndicesAcrescimosImpontualidade;
	}
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private BigDecimal percentualMulta;

    /** nullable persistent field */
    private BigDecimal percentualJurosMora;

    /** nullable persistent field */
    private BigDecimal fatorAtualizacaoMonetaria;
    
    /** nullable persistent field */
    private BigDecimal percentualLimiteJuros;

    /** nullable persistent field */
    private BigDecimal percentualLimiteMulta;
    
    /** nullable persistent field */
    private Short indicadorJurosMensal;
    
    /** nullable persistent field */
    private Short indicadorMultaMensal;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
	public IndicesAcrescimosImpontualidade(Integer id, Integer anoMesReferencia, BigDecimal percentualMulta, BigDecimal percentualJurosMora, BigDecimal fatorAtualizacaoMonetaria, BigDecimal percentualLimiteJuros, BigDecimal percentualLimiteMulta, Short indicadorJurosMensal, Short indicadorMultaMensal, Date ultimaAlteracao) {
		this.anoMesReferencia = anoMesReferencia;
		this.percentualMulta = percentualMulta;
		this.percentualJurosMora = percentualJurosMora;
		this.fatorAtualizacaoMonetaria = fatorAtualizacaoMonetaria;
		this.percentualLimiteJuros = percentualLimiteJuros;
		this.percentualLimiteMulta = percentualLimiteMulta;
		this.indicadorJurosMensal = indicadorJurosMensal;
		this.indicadorMultaMensal = indicadorMultaMensal;
		this.ultimaAlteracao = ultimaAlteracao;
	}

    /** default constructor */
    public IndicesAcrescimosImpontualidade() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public BigDecimal getPercentualMulta() {
        return this.percentualMulta;
    }

    public void setPercentualMulta(BigDecimal percentualMulta) {
        this.percentualMulta = percentualMulta;
    }

    public BigDecimal getPercentualJurosMora() {
        return this.percentualJurosMora;
    }

    public void setPercentualJurosMora(BigDecimal percentualJurosMora) {
        this.percentualJurosMora = percentualJurosMora;
    }

    public BigDecimal getFatorAtualizacaoMonetaria() {
        return this.fatorAtualizacaoMonetaria;
    }

    public void setFatorAtualizacaoMonetaria(BigDecimal fatorAtualizacaoMonetaria) {
        this.fatorAtualizacaoMonetaria = fatorAtualizacaoMonetaria;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo indicadorJurosMensal.
	 */
	public Short getIndicadorJurosMensal() {
		return indicadorJurosMensal;
	}

	/**
	 * @param indicadorJurosMensal O indicadorJurosMensal a ser setado.
	 */
	public void setIndicadorJurosMensal(Short indicadorJurosMensal) {
		this.indicadorJurosMensal = indicadorJurosMensal;
	}

	/**
	 * @return Retorna o campo indicadorMultaMensal.
	 */
	public Short getIndicadorMultaMensal() {
		return indicadorMultaMensal;
	}

	/**
	 * @param indicadorMultaMensal O indicadorMultaMensal a ser setado.
	 */
	public void setIndicadorMultaMensal(Short indicadorMultaMensal) {
		this.indicadorMultaMensal = indicadorMultaMensal;
	}

	/**
	 * @return Retorna o campo percentualLimiteJuros.
	 */
	public BigDecimal getPercentualLimiteJuros() {
		return percentualLimiteJuros;
	}

	/**
	 * @param percentualLimiteJuros O percentualLimiteJuros a ser setado.
	 */
	public void setPercentualLimiteJuros(BigDecimal percentualLimiteJuros) {
		this.percentualLimiteJuros = percentualLimiteJuros;
	}

	/**
	 * @return Retorna o campo percentualLimiteMulta.
	 */
	public BigDecimal getPercentualLimiteMulta() {
		return percentualLimiteMulta;
	}

	/**
	 * @param percentualLimiteMulta O percentualLimiteMulta a ser setado.
	 */
	public void setPercentualLimiteMulta(BigDecimal percentualLimiteMulta) {
		this.percentualLimiteMulta = percentualLimiteMulta;
	}

}
