package gcom.faturamento.debito;

import gcom.cadastro.imovel.Categoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoACobrarCategoriaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.debito.DebitoACobrarCategoriaHistoricoPK comp_id;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorCategoria;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.faturamento.debito.DebitoACobrarHistorico debitoACobrarHistorico;

    /** nullable persistent field */
    private Categoria categoria;

    /** full constructor */
    public DebitoACobrarCategoriaHistorico(gcom.faturamento.debito.DebitoACobrarCategoriaHistoricoPK comp_id, Integer quantidadeEconomia, BigDecimal valorCategoria, Date ultimaAlteracao, gcom.faturamento.debito.DebitoACobrarHistorico debitoACobrarHistorico, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorCategoria = valorCategoria;
        this.ultimaAlteracao = ultimaAlteracao;
        this.debitoACobrarHistorico = debitoACobrarHistorico;
        this.categoria = categoria;
    }

    /** default constructor */
    public DebitoACobrarCategoriaHistorico() {
    }

    /** minimal constructor */
    public DebitoACobrarCategoriaHistorico(gcom.faturamento.debito.DebitoACobrarCategoriaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.faturamento.debito.DebitoACobrarCategoriaHistoricoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.faturamento.debito.DebitoACobrarCategoriaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public BigDecimal getValorCategoria() {
        return this.valorCategoria;
    }

    public void setValorCategoria(BigDecimal valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.debito.DebitoACobrarHistorico getDebitoACobrarHistorico() {
        return this.debitoACobrarHistorico;
    }

    public void setDebitoACobrarHistorico(gcom.faturamento.debito.DebitoACobrarHistorico debitoACobrarHistorico) {
        this.debitoACobrarHistorico = debitoACobrarHistorico;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoACobrarCategoriaHistorico) ) return false;
        DebitoACobrarCategoriaHistorico castOther = (DebitoACobrarCategoriaHistorico) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
