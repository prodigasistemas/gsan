package gcom.seguranca.acesso;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaCasoDeUsoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer tabelaId;

    /** identifier field */
    private Integer casoDeUsoId;

    /** full constructor */
    public TabelaCasoDeUsoPK(Integer tabelaId, Integer casoDeUsoId) {
        this.tabelaId = tabelaId;
        this.casoDeUsoId = casoDeUsoId;
    }

    /** default constructor */
    public TabelaCasoDeUsoPK() {
    }

    public Integer getTabelaId() {
        return this.tabelaId;
    }

    public void setTabelaId(Integer tabelaId) {
        this.tabelaId = tabelaId;
    }

    public Integer getCasoDeUsoId() {
        return this.casoDeUsoId;
    }

    public void setCasoDeUsoId(Integer casoDeUsoId) {
        this.casoDeUsoId = casoDeUsoId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("tabelaId", getTabelaId())
            .append("casoDeUsoId", getCasoDeUsoId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof TabelaCasoDeUsoPK) ) return false;
        TabelaCasoDeUsoPK castOther = (TabelaCasoDeUsoPK) other;
        return new EqualsBuilder()
            .append(this.getTabelaId(), castOther.getTabelaId())
            .append(this.getCasoDeUsoId(), castOther.getCasoDeUsoId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getTabelaId())
            .append(getCasoDeUsoId())
            .toHashCode();
    }

}
