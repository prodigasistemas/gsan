package gcom.cadastro.geografico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MunicipioFeriadoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Date data;

    /** identifier field */
    private gcom.cadastro.geografico.Municipio municipio;

    /** full constructor */
    public MunicipioFeriadoPK(Date data,
            gcom.cadastro.geografico.Municipio municipio) {
        this.data = data;
        this.municipio = municipio;
    }

    /** default constructor */
    public MunicipioFeriadoPK() {
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public gcom.cadastro.geografico.Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(gcom.cadastro.geografico.Municipio municipio) {
        this.municipio = municipio;
    }

    public String toString() {
        return new ToStringBuilder(this).append("data", getData()).append(
                "municipio", getMunicipio()).toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof MunicipioFeriadoPK))
            return false;
        MunicipioFeriadoPK castOther = (MunicipioFeriadoPK) other;
        return new EqualsBuilder().append(this.getData(), castOther.getData())
                .append(this.getMunicipio(), castOther.getMunicipio())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getData()).append(getMunicipio())
                .toHashCode();
    }

}
