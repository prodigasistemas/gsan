package gcom.arrecadacao.aviso;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AvisoDeducoesPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer deducaoTipoId;

    /** identifier field */
    private Integer avisoBancarioId;

    /** full constructor */
    public AvisoDeducoesPK(Integer deducaoTipoId, Integer avisoBancarioId) {
        this.deducaoTipoId = deducaoTipoId;
        this.avisoBancarioId = avisoBancarioId;
    }

    /** default constructor */
    public AvisoDeducoesPK() {
    }

    public Integer getDeducaoTipoId() {
        return this.deducaoTipoId;
    }

    public void setDeducaoTipoId(Integer deducaoTipoId) {
        this.deducaoTipoId = deducaoTipoId;
    }

    public Integer getAvisoBancarioId() {
        return this.avisoBancarioId;
    }

    public void setAvisoBancarioId(Integer avisoBancarioId) {
        this.avisoBancarioId = avisoBancarioId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("deducaoTipoId", getDeducaoTipoId())
            .append("avisoBancarioId", getAvisoBancarioId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AvisoDeducoesPK) ) return false;
        AvisoDeducoesPK castOther = (AvisoDeducoesPK) other;
        return new EqualsBuilder()
            .append(this.getDeducaoTipoId(), castOther.getDeducaoTipoId())
            .append(this.getAvisoBancarioId(), castOther.getAvisoBancarioId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDeducaoTipoId())
            .append(getAvisoBancarioId())
            .toHashCode();
    }

}
