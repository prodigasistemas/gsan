package gcom.arrecadacao;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadadorContratoTarifaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer arrecadadorContratoId;

    /** identifier field */
    private Integer arrecadacaoFormaId;

    /** full constructor */
    public ArrecadadorContratoTarifaPK(Integer arrecadadorContratoId, Integer arrecadacaoFormaId) {
        this.arrecadadorContratoId = arrecadadorContratoId;
        this.arrecadacaoFormaId = arrecadacaoFormaId;
    }

    /** default constructor */
    public ArrecadadorContratoTarifaPK() {
    }

    public Integer getArrecadadorContratoId() {
        return this.arrecadadorContratoId;
    }

    public void setArrecadadorContratoId(Integer arrecadadorContratoId) {
        this.arrecadadorContratoId = arrecadadorContratoId;
    }

    public Integer getArrecadacaoFormaId() {
        return this.arrecadacaoFormaId;
    }

    public void setArrecadacaoFormaId(Integer arrecadacaoFormaId) {
        this.arrecadacaoFormaId = arrecadacaoFormaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("arrecadadorContratoId", getArrecadadorContratoId())
            .append("arrecadacaoFormaId", getArrecadacaoFormaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ArrecadadorContratoTarifaPK) ) return false;
        ArrecadadorContratoTarifaPK castOther = (ArrecadadorContratoTarifaPK) other;
        return new EqualsBuilder()
            .append(this.getArrecadadorContratoId(), castOther.getArrecadadorContratoId())
            .append(this.getArrecadacaoFormaId(), castOther.getArrecadacaoFormaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getArrecadadorContratoId())
            .append(getArrecadacaoFormaId())
            .toHashCode();
    }

}
