package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer debitoTipoId;

    /** identifier field */
    private Integer guiaPagamentoId;

    /** full constructor */
    public GuiaPagamentoItemPK(Integer debitoTipoId, Integer guiaPagamentoId) {
        this.debitoTipoId = debitoTipoId;
        this.guiaPagamentoId = guiaPagamentoId;
    }

    /** default constructor */
    public GuiaPagamentoItemPK() {
    }

    public Integer getDebitoTipoId() {
		return debitoTipoId;
	}

	public void setDebitoTipoId(Integer debitoTipoId) {
		this.debitoTipoId = debitoTipoId;
	}

	public Integer getGuiaPagamentoId() {
        return this.guiaPagamentoId;
    }

    public void setGuiaPagamentoId(Integer guiaPagamentoId) {
        this.guiaPagamentoId = guiaPagamentoId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("debitoTipoId", getDebitoTipoId())
            .append("guiaPagamentoId", getGuiaPagamentoId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoCategoriaPK) ) return false;
        GuiaPagamentoItemPK castOther = (GuiaPagamentoItemPK) other;
        return new EqualsBuilder()
            .append(this.getDebitoTipoId(), castOther.getDebitoTipoId())
            .append(this.getGuiaPagamentoId(), castOther.getGuiaPagamentoId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDebitoTipoId())
            .append(getGuiaPagamentoId())
            .toHashCode();
    }

}
