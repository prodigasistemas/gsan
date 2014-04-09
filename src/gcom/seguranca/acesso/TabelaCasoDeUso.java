package gcom.seguranca.acesso;

import gcom.seguranca.transacao.Tabela;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaCasoDeUso implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.seguranca.acesso.TabelaCasoDeUsoPK comp_id;

    /** nullable persistent field */
    private Tabela tabela;

    /** nullable persistent field */
    private gcom.seguranca.acesso.CasoDeUso casoDeUso;

    /** full constructor */
    public TabelaCasoDeUso(gcom.seguranca.acesso.TabelaCasoDeUsoPK comp_id, Tabela tabela, gcom.seguranca.acesso.CasoDeUso casoDeUso) {
        this.comp_id = comp_id;
        this.tabela = tabela;
        this.casoDeUso = casoDeUso;
    }

    /** default constructor */
    public TabelaCasoDeUso() {
    }

    /** minimal constructor */
    public TabelaCasoDeUso(gcom.seguranca.acesso.TabelaCasoDeUsoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.TabelaCasoDeUsoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.TabelaCasoDeUsoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Tabela getTabela() {
        return this.tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public gcom.seguranca.acesso.CasoDeUso getCasoDeUso() {
        return this.casoDeUso;
    }

    public void setCasoDeUso(gcom.seguranca.acesso.CasoDeUso casoDeUso) {
        this.casoDeUso = casoDeUso;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof TabelaCasoDeUso) ) return false;
        TabelaCasoDeUso castOther = (TabelaCasoDeUso) other;
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
