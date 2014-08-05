package gcom.faturamento.credito;

import gcom.cadastro.imovel.Categoria;
import gcom.faturamento.debito.DebitoCobradoCategoriaPK;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CreditoRealizadoCategoria implements Serializable, ICreditoRealizadoCategoria {
	private static final long serialVersionUID = 1L;

    private CreditoRealizadoCategoriaPK comp_id;
    private Integer quantidadeEconomia;
    private BigDecimal valorCategoria;
    private Date ultimaAlteracao;
    private CreditoRealizado creditoRealizado;
    private Categoria categoria;

    public CreditoRealizadoCategoria(CreditoRealizadoCategoriaPK comp_id, Integer quantidadeEconomia, BigDecimal valorCategoria, Date ultimaAlteracao, CreditoRealizado creditoRealizado, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorCategoria = valorCategoria;
        this.ultimaAlteracao = ultimaAlteracao;
        this.creditoRealizado = creditoRealizado;
        this.categoria = categoria;
    }

    public CreditoRealizadoCategoria() {
    }

    public CreditoRealizadoCategoria(CreditoRealizadoCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public CreditoRealizadoCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CreditoRealizadoCategoriaPK comp_id) {
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

    public CreditoRealizado getCreditoRealizado() {
        return this.creditoRealizado;
    }

    public void setCreditoRealizado(ICreditoRealizado creditoRealizado) {
        if (comp_id == null) {
    		comp_id = new CreditoRealizadoCategoriaPK();
    	}
    	
        if (comp_id.getCreditoRealizado() == null) {
        	comp_id.setCreditoRealizado(new CreditoRealizado());
        	comp_id.getCreditoRealizado().setId((creditoRealizado != null ? creditoRealizado.getId() : null));
        }
        this.creditoRealizado  = (CreditoRealizado) creditoRealizado ;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
    	if (comp_id == null) {
    		comp_id = new CreditoRealizadoCategoriaPK();
    	}
    	if (comp_id.getCategoria() == null) {
        	comp_id.setCategoria(new Categoria());
        	comp_id.getCategoria().setId((categoria != null ? categoria.getId() : null));
        }
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoRealizadoCategoria) ) return false;
        CreditoRealizadoCategoria castOther = (CreditoRealizadoCategoria) other;
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
