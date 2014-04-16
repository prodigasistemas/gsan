package gcom.cobranca;

import gcom.cadastro.cliente.ClienteTipo;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioClienteTipoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private ClienteTipo clienteTipo;

    /** full constructor */
    public NegativacaoCriterioClienteTipoPK(NegativacaoCriterio negativacaoCriterio, ClienteTipo clienteTipo) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.clienteTipo = clienteTipo;
    }

    /** default constructor */
    public NegativacaoCriterioClienteTipoPK() {
    }

	/**
	 * @return Retorna o campo clienteTipo.
	 */
	public ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	/**
	 * @param clienteTipo O clienteTipo a ser setado.
	 */
	public void setClienteTipo(ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	/**
	 * @return Retorna o campo negativacaoCriterio.
	 */
	public NegativacaoCriterio getNegativacaoCriterio() {
		return negativacaoCriterio;
	}

	/**
	 * @param negativacaoCriterio O negativacaoCriterio a ser setado.
	 */
	public void setNegativacaoCriterio(NegativacaoCriterio negativacaoCriterio) {
		this.negativacaoCriterio = negativacaoCriterio;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("negativacaoCriterio", getNegativacaoCriterio())
            .append("clienteTipo", getClienteTipo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioClienteTipoPK) ) return false;
        NegativacaoCriterioClienteTipoPK castOther = (NegativacaoCriterioClienteTipoPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getClienteTipo(), castOther.getClienteTipo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getClienteTipo())
            .toHashCode();
    }

}
