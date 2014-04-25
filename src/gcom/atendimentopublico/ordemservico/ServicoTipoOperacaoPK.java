package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoOperacaoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idServicoTipo;

    /** identifier field */
    private Integer idOperacao;

    /** full constructor */
    public ServicoTipoOperacaoPK(Integer idServicoTipo, Integer idOperacao) {
        this.idServicoTipo = idServicoTipo;
        this.idOperacao = idOperacao;
    }

    /** default constructor */
    public ServicoTipoOperacaoPK() {
    }

    public Integer getIdServicoTipo() {
        return this.idServicoTipo;
    }

    public void setIdServicoTipo(Integer idServicoTipo) {
        this.idServicoTipo = idServicoTipo;
    }

    public Integer getIdOperacao() {
        return this.idOperacao;
    }

    public void setIdOperacao(Integer idOperacao) {
        this.idOperacao = idOperacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idServicoTipo", getIdServicoTipo())
            .append("idOperacao", getIdOperacao())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ServicoTipoOperacaoPK) ) return false;
        ServicoTipoOperacaoPK castOther = (ServicoTipoOperacaoPK) other;
        return new EqualsBuilder()
            .append(this.getIdServicoTipo(), castOther.getIdServicoTipo())
            .append(this.getIdOperacao(), castOther.getIdOperacao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdServicoTipo())
            .append(getIdOperacao())
            .toHashCode();
    }

}
