package gcom.cadastro.endereco;

import gcom.cadastro.geografico.Bairro;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LogradouroBairroPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.cadastro.endereco.Logradouro logradouro;

    /** identifier field */
    private Bairro bairro;

    /** full constructor */
    public LogradouroBairroPK(gcom.cadastro.endereco.Logradouro logradouro,
            Bairro bairro) {
        this.logradouro = logradouro;
        this.bairro = bairro;
    }

    /** default constructor */
    public LogradouroBairroPK() {
    }

    public gcom.cadastro.endereco.Logradouro getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(gcom.cadastro.endereco.Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String toString() {
        return new ToStringBuilder(this).append("logradouro", getLogradouro())
                .append("bairro", getBairro()).toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof LogradouroBairroPK))
            return false;
        LogradouroBairroPK castOther = (LogradouroBairroPK) other;
        return new EqualsBuilder().append(this.getLogradouro(),
                castOther.getLogradouro()).append(this.getBairro(),
                castOther.getBairro()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getLogradouro())
                .append(getBairro()).toHashCode();
    }

}
