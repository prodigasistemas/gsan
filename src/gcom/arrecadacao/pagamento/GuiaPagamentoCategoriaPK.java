package gcom.arrecadacao.pagamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoCategoriaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer categoriaId;

    /** identifier field */
    private Integer guiaPagamentoId;

    /** full constructor */
    public GuiaPagamentoCategoriaPK(Integer categoriaId, Integer guiaPagamentoId) {
        this.categoriaId = categoriaId;
        this.guiaPagamentoId = guiaPagamentoId;
    }

    /** default constructor */
    public GuiaPagamentoCategoriaPK() {
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getGuiaPagamentoId() {
        return this.guiaPagamentoId;
    }

    public void setGuiaPagamentoId(Integer guiaPagamentoId) {
        this.guiaPagamentoId = guiaPagamentoId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("categoriaId", getCategoriaId())
            .append("guiaPagamentoId", getGuiaPagamentoId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoCategoriaPK) ) return false;
        GuiaPagamentoCategoriaPK castOther = (GuiaPagamentoCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getCategoriaId(), castOther.getCategoriaId())
            .append(this.getGuiaPagamentoId(), castOther.getGuiaPagamentoId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCategoriaId())
            .append(getGuiaPagamentoId())
            .toHashCode();
    }

}
