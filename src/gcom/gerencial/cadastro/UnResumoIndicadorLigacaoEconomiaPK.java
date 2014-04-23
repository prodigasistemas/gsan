package gcom.gerencial.cadastro;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoIndicadorLigacaoEconomiaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** identifier field */
    private Integer idMesAno;

    /** full constructor */
    public UnResumoIndicadorLigacaoEconomiaPK(Integer id, Integer idMesAno) {
        this.id = id;
        this.idMesAno = idMesAno;
    }

    /** default constructor */
    public UnResumoIndicadorLigacaoEconomiaPK() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMesAno() {
        return this.idMesAno;
    }

    public void setIdMesAno(Integer idMesAno) {
        this.idMesAno = idMesAno;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("idMesAno", getIdMesAno())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoIndicadorLigacaoEconomiaPK) ) return false;
        UnResumoIndicadorLigacaoEconomiaPK castOther = (UnResumoIndicadorLigacaoEconomiaPK) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .append(this.getIdMesAno(), castOther.getIdMesAno())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .append(getIdMesAno())
            .toHashCode();
    }

}
