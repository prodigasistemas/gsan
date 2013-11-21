package gcom.faturamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MovimentoContaPrefaturadaCategoria implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.faturamento.MovimentoContaPrefaturadaCategoriaPK comp_id;

    /** nullable persistent field */
    private BigDecimal valorFaturadoAgua;

    /** nullable persistent field */
    private Integer consumoFaturadoAgua;

    /** nullable persistent field */
    private BigDecimal valorTarifaMinimaAgua;

    /** nullable persistent field */
    private Integer consumoMinimoAgua;

    /** nullable persistent field */
    private BigDecimal valorFaturadoEsgoto;

    /** nullable persistent field */
    private Integer consumoFaturadoEsgoto;

    /** nullable persistent field */
    private BigDecimal valorTarifaMinimaEsgoto;

    /** nullable persistent field */
    private Integer consumoMinimoEsgoto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set movimentoContaCategoriaConsumoFaixas;

    /** full constructor */
    public MovimentoContaPrefaturadaCategoria(gcom.faturamento.MovimentoContaPrefaturadaCategoriaPK comp_id, BigDecimal valorFaturadoAgua, Integer consumoFaturadoAgua, BigDecimal valorTarifaMinimaAgua, Integer consumoMinimoAgua, BigDecimal valorFaturadoEsgoto, Integer consumoFaturadoEsgoto, BigDecimal valorTarifaMinimaEsgoto, Integer consumoMinimoEsgoto, Date ultimaAlteracao, gcom.faturamento.MovimentoContaPrefaturada movimentoContaPrefaturada, Set movimentoContaCategoriaConsumoFaixas) {
        this.comp_id = comp_id;
        this.valorFaturadoAgua = valorFaturadoAgua;
        this.consumoFaturadoAgua = consumoFaturadoAgua;
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
        this.consumoMinimoAgua = consumoMinimoAgua;
        this.valorFaturadoEsgoto = valorFaturadoEsgoto;
        this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public MovimentoContaPrefaturadaCategoria() {
    }

    /** minimal constructor */
    public MovimentoContaPrefaturadaCategoria(gcom.faturamento.MovimentoContaPrefaturadaCategoriaPK comp_id, Date ultimaAlteracao, Set movimentoContaCategoriaConsumoFaixas) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.MovimentoContaPrefaturadaCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.faturamento.MovimentoContaPrefaturadaCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public BigDecimal getValorFaturadoAgua() {
        return this.valorFaturadoAgua;
    }

    public void setValorFaturadoAgua(BigDecimal valorFaturadoAgua) {
        this.valorFaturadoAgua = valorFaturadoAgua;
    }

    public Integer getConsumoFaturadoAgua() {
        return this.consumoFaturadoAgua;
    }

    public void setConsumoFaturadoAgua(Integer consumoFaturadoAgua) {
        this.consumoFaturadoAgua = consumoFaturadoAgua;
    }

    public BigDecimal getValorTarifaMinimaAgua() {
        return this.valorTarifaMinimaAgua;
    }

    public void setValorTarifaMinimaAgua(BigDecimal valorTarifaMinimaAgua) {
        this.valorTarifaMinimaAgua = valorTarifaMinimaAgua;
    }

    public Integer getConsumoMinimoAgua() {
        return this.consumoMinimoAgua;
    }

    public void setConsumoMinimoAgua(Integer consumoMinimoAgua) {
        this.consumoMinimoAgua = consumoMinimoAgua;
    }

    public BigDecimal getValorFaturadoEsgoto() {
        return this.valorFaturadoEsgoto;
    }

    public void setValorFaturadoEsgoto(BigDecimal valorFaturadoEsgoto) {
        this.valorFaturadoEsgoto = valorFaturadoEsgoto;
    }

    public Integer getConsumoFaturadoEsgoto() {
        return this.consumoFaturadoEsgoto;
    }

    public void setConsumoFaturadoEsgoto(Integer consumoFaturadoEsgoto) {
        this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
    }

    public BigDecimal getValorTarifaMinimaEsgoto() {
        return this.valorTarifaMinimaEsgoto;
    }

    public void setValorTarifaMinimaEsgoto(BigDecimal valorTarifaMinimaEsgoto) {
        this.valorTarifaMinimaEsgoto = valorTarifaMinimaEsgoto;
    }

    public Integer getConsumoMinimoEsgoto() {
        return this.consumoMinimoEsgoto;
    }

    public void setConsumoMinimoEsgoto(Integer consumoMinimoEsgoto) {
        this.consumoMinimoEsgoto = consumoMinimoEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof MovimentoContaPrefaturadaCategoria) ) return false;
        MovimentoContaPrefaturadaCategoria castOther = (MovimentoContaPrefaturadaCategoria) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    
	public Set getMovimentoContaCategoriaConsumoFaixas() {
		return movimentoContaCategoriaConsumoFaixas;
	}

	public void setMovimentoContaCategoriaConsumoFaixas(
			Set movimentoContaCategoriaConsumoFaixas) {
		this.movimentoContaCategoriaConsumoFaixas = movimentoContaCategoriaConsumoFaixas;
	}

}
