package gcom.seguranca.acesso;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GrupoAcessoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer grupId;

    /** identifier field */
    private Integer grupIdacesso;

    /** full constructor */
    public GrupoAcessoPK(Integer grupId, Integer grupIdacesso) {
        this.grupId = grupId;
        this.grupIdacesso = grupIdacesso;
    }

    /** default constructor */
    public GrupoAcessoPK() {
    }

    public Integer getGrupId() {
        return this.grupId;
    }

    public void setGrupId(Integer grupId) {
        this.grupId = grupId;
    }

    public Integer getGrupIdacesso() {
        return this.grupIdacesso;
    }

    public void setGrupIdacesso(Integer grupIdacesso) {
        this.grupIdacesso = grupIdacesso;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("grupId", getGrupId())
            .append("grupIdacesso", getGrupIdacesso())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GrupoAcessoPK) ) return false;
        GrupoAcessoPK castOther = (GrupoAcessoPK) other;
        return new EqualsBuilder()
            .append(this.getGrupId(), castOther.getGrupId())
            .append(this.getGrupIdacesso(), castOther.getGrupIdacesso())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getGrupId())
            .append(getGrupIdacesso())
            .toHashCode();
    }

}
