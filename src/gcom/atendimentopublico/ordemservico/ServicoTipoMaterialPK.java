package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoMaterialPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idServicoTipo;

    /** identifier field */
    private Integer idMaterial;

    /** full constructor */
    public ServicoTipoMaterialPK(Integer idServicoTipo, Integer idMaterial) {
        this.idServicoTipo = idServicoTipo;
        this.idMaterial = idMaterial;
    }

    /** default constructor */
    public ServicoTipoMaterialPK() {
    }

    public Integer getIdServicoTipo() {
        return this.idServicoTipo;
    }

    public void setIdServicoTipo(Integer idServicoTipo) {
        this.idServicoTipo = idServicoTipo;
    }

    public Integer getIdMaterial() {
        return this.idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idServicoTipo", getIdServicoTipo())
            .append("idMaterial", getIdMaterial())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ServicoTipoMaterialPK) ) return false;
        ServicoTipoMaterialPK castOther = (ServicoTipoMaterialPK) other;
        return new EqualsBuilder()
            .append(this.getIdServicoTipo(), castOther.getIdServicoTipo())
            .append(this.getIdMaterial(), castOther.getIdMaterial())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdServicoTipo())
            .append(getIdMaterial())
            .toHashCode();
    }

}
