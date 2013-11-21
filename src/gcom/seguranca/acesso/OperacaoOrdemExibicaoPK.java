package gcom.seguranca.acesso;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoOrdemExibicaoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer operacaoId;

    /** identifier field */
    private Integer tabelaColunaId;

    /** full constructor */
    public OperacaoOrdemExibicaoPK(Integer operacaoId, Integer tabelaColunaId) {
        this.operacaoId = operacaoId;
        this.tabelaColunaId = tabelaColunaId;
    }

    /** default constructor */
    public OperacaoOrdemExibicaoPK() {
    }

    public Integer getOperacaoId() {
        return this.operacaoId;
    }

    public void setOperacaoId(Integer operacaoId) {
        this.operacaoId = operacaoId;
    }

    public Integer getTabelaColunaId() {
        return this.tabelaColunaId;
    }

    public void setTabelaColunaId(Integer tabelaColunaId) {
        this.tabelaColunaId = tabelaColunaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("operacaoId", getOperacaoId())
            .append("tabelaColunaId", getTabelaColunaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OperacaoOrdemExibicaoPK) ) return false;
        OperacaoOrdemExibicaoPK castOther = (OperacaoOrdemExibicaoPK) other;
        return new EqualsBuilder()
            .append(this.getOperacaoId(), castOther.getOperacaoId())
            .append(this.getTabelaColunaId(), castOther.getTabelaColunaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getOperacaoId())
            .append(getTabelaColunaId())
            .toHashCode();
    }

}
