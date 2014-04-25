package gcom.cadastro.endereco;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LogradouroCepPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer logradouroId;

    /** identifier field */
    private Integer cepId;

    /** full constructor */
    public LogradouroCepPK (Integer logradouroId, Integer cepId) {
        this.logradouroId = logradouroId;
        this.cepId = cepId;
    }

    /** default constructor */
    public LogradouroCepPK () {
    	
    }
    
    /**
	 * @return Retorna o campo cepId.
	 */
	public Integer getCepId() {
		return cepId;
	}

	/**
	 * @param cepId O cepId a ser setado.
	 */
	public void setCepId(Integer cepId) {
		this.cepId = cepId;
	}

	/**
	 * @return Retorna o campo logradouroId.
	 */
	public Integer getLogradouroId() {
		return logradouroId;
	}

	/**
	 * @param logradouroId O logradouroId a ser setado.
	 */
	public void setLogradouroId(Integer logradouroId) {
		this.logradouroId = logradouroId;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("LogradouroId", getLogradouroId())
            .append("CepId", getCepId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LogradouroCepPK) ) return false;
        LogradouroCepPK castOther = (LogradouroCepPK) other;
        return new EqualsBuilder()
            .append(this.getLogradouroId(), castOther.getLogradouroId())
            .append(this.getCepId(), castOther.getCepId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCepId())
            .append(getLogradouroId())
            .toHashCode();
    }
}
