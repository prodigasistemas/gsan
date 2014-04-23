package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoCategoriaHistoricoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idGuiaPagamentoHistorico;

    /** identifier field */
    private Integer idCategoria;

    public GuiaPagamentoCategoriaHistoricoPK() {
    }
    
    public GuiaPagamentoCategoriaHistoricoPK(Integer idCategoria, Integer idGuiaPagamentoHistorico) {
        this.idCategoria = idCategoria;
        this.idGuiaPagamentoHistorico = idGuiaPagamentoHistorico;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("idGuiaPagamentoHistorico", getIdGuiaPagamentoHistorico())
            .append("idCategoria", getIdCategoria())
            .toString();
    }

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return Retorna o campo idGuiaPagamento.
	 */
	public Integer getIdGuiaPagamentoHistorico() {
		return idGuiaPagamentoHistorico;
	}

	/**
	 * @param idGuiaPagamentoHistorico O idGuiaPagamentoHistorico a ser setado.
	 */
	public void setIdGuiaPagamentoHistorico(Integer idGuiaPagamentoHistorico) {
		this.idGuiaPagamentoHistorico = idGuiaPagamentoHistorico;
	}
	
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoCategoriaHistoricoPK) ) return false;
        GuiaPagamentoCategoriaHistoricoPK castOther = (GuiaPagamentoCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getIdGuiaPagamentoHistorico(), castOther.getIdGuiaPagamentoHistorico())
            .append(this.getIdCategoria(), castOther.getIdCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdGuiaPagamentoHistorico())
            .append(getIdCategoria())
            .toHashCode();
    }
	

}
