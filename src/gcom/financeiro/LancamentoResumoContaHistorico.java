package gcom.financeiro;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoResumoContaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.financeiro.LancamentoResumoContaHistoricoPK comp_id;

    /** full constructor */
    public LancamentoResumoContaHistorico(gcom.financeiro.LancamentoResumoContaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    /** default constructor */
    public LancamentoResumoContaHistorico() {
    }

    public gcom.financeiro.LancamentoResumoContaHistoricoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.financeiro.LancamentoResumoContaHistoricoPK comp_id) {
        this.comp_id = comp_id;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LancamentoResumoContaHistorico) ) return false;
        LancamentoResumoContaHistorico castOther = (LancamentoResumoContaHistorico) other;
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
