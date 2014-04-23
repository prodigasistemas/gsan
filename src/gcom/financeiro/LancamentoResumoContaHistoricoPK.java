package gcom.financeiro;

import gcom.faturamento.conta.ContaHistorico;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoResumoContaHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.financeiro.LancamentoResumo lancamentoResumo;

    /** identifier field */
    private ContaHistorico contaHistorico;

    /** full constructor */
    public LancamentoResumoContaHistoricoPK(gcom.financeiro.LancamentoResumo lancamentoResumo, ContaHistorico contaHistorico) {
        this.lancamentoResumo = lancamentoResumo;
        this.contaHistorico = contaHistorico;
    }

    /** default constructor */
    public LancamentoResumoContaHistoricoPK() {
    }

    public gcom.financeiro.LancamentoResumo getLancamentoResumo() {
        return this.lancamentoResumo;
    }

    public void setLancamentoResumo(gcom.financeiro.LancamentoResumo lancamentoResumo) {
        this.lancamentoResumo = lancamentoResumo;
    }

    public ContaHistorico getContaHistorico() {
        return this.contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("lancamentoResumo", getLancamentoResumo())
            .append("contaHistorico", getContaHistorico())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LancamentoResumoContaHistoricoPK) ) return false;
        LancamentoResumoContaHistoricoPK castOther = (LancamentoResumoContaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getLancamentoResumo(), castOther.getLancamentoResumo())
            .append(this.getContaHistorico(), castOther.getContaHistorico())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLancamentoResumo())
            .append(getContaHistorico())
            .toHashCode();
    }

}
