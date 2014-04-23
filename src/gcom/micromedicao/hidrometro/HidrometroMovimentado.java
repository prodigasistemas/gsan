package gcom.micromedicao.hidrometro;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class HidrometroMovimentado implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.micromedicao.hidrometro.HidrometroMovimentadoPK comp_id;

    /** nullable persistent field */
    private gcom.micromedicao.hidrometro.Hidrometro hidrometro;

    /** nullable persistent field */
    private gcom.micromedicao.hidrometro.HidrometroMovimentacao hidrometroMovimentacao;

    /** full constructor */
    public HidrometroMovimentado(gcom.micromedicao.hidrometro.HidrometroMovimentadoPK comp_id, gcom.micromedicao.hidrometro.Hidrometro hidrometro, gcom.micromedicao.hidrometro.HidrometroMovimentacao hidrometroMovimentacao) {
        this.comp_id = comp_id;
        this.hidrometro = hidrometro;
        this.hidrometroMovimentacao = hidrometroMovimentacao;
    }

    /** default constructor */
    public HidrometroMovimentado() {
    }

    /** minimal constructor */
    public HidrometroMovimentado(gcom.micromedicao.hidrometro.HidrometroMovimentadoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.micromedicao.hidrometro.HidrometroMovimentadoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.micromedicao.hidrometro.HidrometroMovimentadoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.micromedicao.hidrometro.Hidrometro getHidrometro() {
        return this.hidrometro;
    }

    public void setHidrometro(gcom.micromedicao.hidrometro.Hidrometro hidrometro) {
        this.hidrometro = hidrometro;
    }

    public gcom.micromedicao.hidrometro.HidrometroMovimentacao getHidrometroMovimentacao() {
        return this.hidrometroMovimentacao;
    }

    public void setHidrometroMovimentacao(gcom.micromedicao.hidrometro.HidrometroMovimentacao hidrometroMovimentacao) {
        this.hidrometroMovimentacao = hidrometroMovimentacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof HidrometroMovimentado) ) return false;
        HidrometroMovimentado castOther = (HidrometroMovimentado) other;
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
