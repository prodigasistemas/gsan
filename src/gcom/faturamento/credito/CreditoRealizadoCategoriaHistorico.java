package gcom.faturamento.credito;

import gcom.cadastro.imovel.Categoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CreditoRealizadoCategoriaHistorico implements Serializable, ICreditoRealizadoCategoria {
	private static final long serialVersionUID = 1L;

    private CreditoRealizadoCategoriaHistoricoPK comp_id;
    private Integer quantidadeEconomia;
    private BigDecimal valorCategoria;
    private Date ultimaAlteracao;
    private CreditoRealizadoHistorico creditoRealizadoHistorico;
    private Categoria categoria;

    public CreditoRealizadoCategoriaHistorico(CreditoRealizadoCategoriaHistoricoPK comp_id, Integer quantidadeEconomia, BigDecimal valorCategoria, Date ultimaAlteracao, CreditoRealizadoHistorico creditoRealizadoHistorico, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorCategoria = valorCategoria;
        this.ultimaAlteracao = ultimaAlteracao;
        this.creditoRealizadoHistorico = creditoRealizadoHistorico;
        this.categoria = categoria;
    }

    public CreditoRealizadoCategoriaHistorico() {
    }

    public CreditoRealizadoCategoriaHistorico(CreditoRealizadoCategoriaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public CreditoRealizadoCategoriaHistoricoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CreditoRealizadoCategoriaHistoricoPK comp_id) {
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

    public CreditoRealizadoHistorico getCreditoRealizadoHistorico() {
        return this.creditoRealizadoHistorico;
    }

    public void setCreditoRealizadoHistorico(CreditoRealizadoHistorico creditoRealizadoHistorico) {
        this.creditoRealizadoHistorico = creditoRealizadoHistorico;
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
        if ( !(other instanceof CreditoRealizadoCategoriaHistorico) ) return false;
        CreditoRealizadoCategoriaHistorico castOther = (CreditoRealizadoCategoriaHistorico) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public CreditoRealizado getCreditoRealizado() {
		return new CreditoRealizado(this.getCreditoRealizadoHistorico().getId());
	}

	public void setCreditoRealizado(ICreditoRealizado creditoRealizado) {
		if (comp_id == null) {
			comp_id = new CreditoRealizadoCategoriaHistoricoPK();
		}
		
		comp_id.setCreditoRealizadoHistoricoId(creditoRealizadoHistorico != null ? creditoRealizadoHistorico.getId() : null);
		this.comp_id.getCreditoRealizadoHistoricoId();
	}

}
