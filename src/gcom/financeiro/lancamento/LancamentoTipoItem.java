package gcom.financeiro.lancamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoTipoItem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.financeiro.lancamento.LancamentoTipoItemPK comp_id;

    /** nullable persistent field */
    private gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo;

    /** nullable persistent field */
    private gcom.financeiro.lancamento.LancamentoItem lancamentoItem;

    /** full constructor */
    public LancamentoTipoItem(gcom.financeiro.lancamento.LancamentoTipoItemPK comp_id, gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo, gcom.financeiro.lancamento.LancamentoItem lancamentoItem) {
        this.comp_id = comp_id;
        this.lancamentoTipo = lancamentoTipo;
        this.lancamentoItem = lancamentoItem;
    }

    /** default constructor */
    public LancamentoTipoItem() {
    }

    /** minimal constructor */
    public LancamentoTipoItem(gcom.financeiro.lancamento.LancamentoTipoItemPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.financeiro.lancamento.LancamentoTipoItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.financeiro.lancamento.LancamentoTipoItemPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.financeiro.lancamento.LancamentoTipo getLancamentoTipo() {
        return this.lancamentoTipo;
    }

    public void setLancamentoTipo(gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    public gcom.financeiro.lancamento.LancamentoItem getLancamentoItem() {
        return this.lancamentoItem;
    }

    public void setLancamentoItem(gcom.financeiro.lancamento.LancamentoItem lancamentoItem) {
        this.lancamentoItem = lancamentoItem;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LancamentoTipoItem) ) return false;
        LancamentoTipoItem castOther = (LancamentoTipoItem) other;
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
