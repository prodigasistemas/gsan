package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class GuiaPagamentoParcelamentoCartaoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer parcelamentoId;

    private Integer guiaPagamentoId;
    
    public GuiaPagamentoParcelamentoCartaoPK(){}

	public GuiaPagamentoParcelamentoCartaoPK(Integer parcelamentoId, Integer guiaPagamentoId) {
		super();
		
		this.parcelamentoId = parcelamentoId;
		this.guiaPagamentoId = guiaPagamentoId;
	}

	public Integer getGuiaPagamentoId() {
		return guiaPagamentoId;
	}

	public void setGuiaPagamentoId(Integer guiaPagamentoId) {
		this.guiaPagamentoId = guiaPagamentoId;
	}

	public Integer getParcelamentoId() {
		return parcelamentoId;
	}

	public void setParcelamentoId(Integer parcelamentoId) {
		this.parcelamentoId = parcelamentoId;
	}
    
	public String toString() {
        return new ToStringBuilder(this)
            .append("parcelamentoId", getParcelamentoId())
            .append("guiaPagamentoId", getGuiaPagamentoId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoParcelamentoCartaoPK) ) return false;
        GuiaPagamentoParcelamentoCartaoPK castOther = (GuiaPagamentoParcelamentoCartaoPK) other;
        return new EqualsBuilder()
            .append(this.getParcelamentoId(), castOther.getParcelamentoId())
            .append(this.getGuiaPagamentoId(), castOther.getGuiaPagamentoId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getParcelamentoId())
            .append(getGuiaPagamentoId())
            .toHashCode();
    }
}
