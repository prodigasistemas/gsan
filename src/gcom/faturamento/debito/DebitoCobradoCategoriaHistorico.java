package gcom.faturamento.debito;

import gcom.cadastro.imovel.Categoria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class DebitoCobradoCategoriaHistorico implements Serializable, IDebitoCobradoCategoria {
	private static final long serialVersionUID = 1L;
   
    private DebitoCobradoCategoriaHistoricoPK comp_id;
    private Integer quantidadeEconomia;
    private BigDecimal valorCategoria;
    private Date ultimaAlteracao;
    private DebitoCobradoHistorico debitoCobradoHistorico;
    private Categoria categoria;

    public DebitoCobradoCategoriaHistorico(DebitoCobradoCategoriaHistoricoPK comp_id, Integer quantidadeEconomia, BigDecimal valorCategoria, Date ultimaAlteracao, DebitoCobradoHistorico debitoCobradoHistorico, Categoria categoria) {
        this.comp_id = comp_id;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorCategoria = valorCategoria;
        this.ultimaAlteracao = ultimaAlteracao;
        this.debitoCobradoHistorico = debitoCobradoHistorico;
        this.categoria = categoria;
    }

    public DebitoCobradoCategoriaHistorico() {
    }

    public DebitoCobradoCategoriaHistorico(DebitoCobradoCategoriaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public DebitoCobradoCategoriaHistoricoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(DebitoCobradoCategoriaHistoricoPK comp_id) {
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

    public DebitoCobradoHistorico getDebitoCobradoHistorico() {
        return this.debitoCobradoHistorico;
    }

    public void setDebitoCobradoHistorico(DebitoCobradoHistorico debitoCobradoHistorico) {
    	if (comp_id == null) {
    		comp_id = new DebitoCobradoCategoriaHistoricoPK();
    	}
    	
    	comp_id.setDebitoCobradoHistoricoId(debitoCobradoHistorico != null ? debitoCobradoHistorico.getId() : null);
    	this.debitoCobradoHistorico = debitoCobradoHistorico;
    }

    public Categoria getCategoria() {
    	if (comp_id == null) {
    		comp_id = new DebitoCobradoCategoriaHistoricoPK();
    	}
    	
    	comp_id.setCategoriaId(categoria != null ? categoria.getId() : null);
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
        if ( !(other instanceof DebitoCobradoCategoriaHistorico) ) return false;
        DebitoCobradoCategoriaHistorico castOther = (DebitoCobradoCategoriaHistorico) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

	public DebitoCobrado getDebitoCobrado() {
		return new DebitoCobrado(this.getDebitoCobradoHistorico().getId());
	}

	public void setDebitoCobrado(IDebitoCobrado debitoCobrado) {
		if (comp_id == null) {
			comp_id = new DebitoCobradoCategoriaHistoricoPK();
		}
		
		comp_id.setDebitoCobradoHistoricoId(debitoCobradoHistorico != null ? debitoCobradoHistorico.getId() : null);
		this.comp_id.getDebitoCobradoHistoricoId();
	}

}
