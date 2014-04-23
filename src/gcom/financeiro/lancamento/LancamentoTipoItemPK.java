package gcom.financeiro.lancamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoTipoItemPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer lancamentoTipoId;

    /** identifier field */
    private Integer lancamentoItemId;

    /** full constructor */
    public LancamentoTipoItemPK(Integer lancamentoTipoId, Integer lancamentoItemId) {
        this.lancamentoTipoId = lancamentoTipoId;
        this.lancamentoItemId = lancamentoItemId;
    }

    /** default constructor */
    public LancamentoTipoItemPK() {
    }

    public Integer getLancamentoTipoId() {
        return this.lancamentoTipoId;
    }

    public void setLancamentoTipoId(Integer lancamentoTipoId) {
        this.lancamentoTipoId = lancamentoTipoId;
    }

    public Integer getLancamentoItemId() {
        return this.lancamentoItemId;
    }

    public void setLancamentoItemId(Integer lancamentoItemId) {
        this.lancamentoItemId = lancamentoItemId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("lancamentoTipoId", getLancamentoTipoId())
            .append("lancamentoItemId", getLancamentoItemId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LancamentoTipoItemPK) ) return false;
        LancamentoTipoItemPK castOther = (LancamentoTipoItemPK) other;
        return new EqualsBuilder()
            .append(this.getLancamentoTipoId(), castOther.getLancamentoTipoId())
            .append(this.getLancamentoItemId(), castOther.getLancamentoItemId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLancamentoTipoId())
            .append(getLancamentoItemId())
            .toHashCode();
    }

}
