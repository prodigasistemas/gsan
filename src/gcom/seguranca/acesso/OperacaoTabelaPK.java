package gcom.seguranca.acesso;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoTabelaPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer operacaoId;

    /** identifier field */
    private Integer tabelaId;

    /** full constructor */
    public OperacaoTabelaPK(Integer operacaoId, Integer tabelaId) {
        this.operacaoId = operacaoId;
        this.tabelaId = tabelaId;
    }

    /** default constructor */
    public OperacaoTabelaPK() {
    }
	/**
	 * @return Retorna o campo operacaoId.
	 */
	public Integer getOperacaoId() {
		return operacaoId;
	}

	/**
	 * @param operacaoId O operacaoId a ser setado.
	 */
	public void setOperacaoId(Integer operacaoId) {
		this.operacaoId = operacaoId;
	}

	/**
	 * @return Retorna o campo tabelaId.
	 */
	public Integer getTabelaId() {
		return tabelaId;
	}

	/**
	 * @param tabelaId O tabelaId a ser setado.
	 */
	public void setTabelaId(Integer tabelaId) {
		this.tabelaId = tabelaId;
	}

    
    public String toString() {
        return new ToStringBuilder(this)
            .append("operacaoId", getOperacaoId())
            .append("tabelaId", getTabelaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OperacaoTabelaPK) ) return false;
        OperacaoTabelaPK castOther = (OperacaoTabelaPK) other;
        return new EqualsBuilder()
            .append(this.getOperacaoId(), castOther.getOperacaoId())
            .append(this.getTabelaId(), castOther.getTabelaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getOperacaoId())
            .append(getTabelaId())
            .toHashCode();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "operacaoId";
		retorno[1] = "tabelaId";
		return retorno;
	}
}
