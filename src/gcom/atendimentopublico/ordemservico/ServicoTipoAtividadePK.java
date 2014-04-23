package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoAtividadePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idServicoTipo;

    /** identifier field */
    private Integer idAtividade;

    /** full constructor */
    public ServicoTipoAtividadePK(Integer idServicoTipo, Integer idAtividade) {
        this.idServicoTipo = idServicoTipo;
        this.idAtividade = idAtividade;
    }

    /** default constructor */
    public ServicoTipoAtividadePK() {
    }

    public Integer getIdServicoTipo() {
        return this.idServicoTipo;
    }

    public void setIdServicoTipo(Integer idServicoTipo) {
        this.idServicoTipo = idServicoTipo;
    }

    public Integer getIdAtividade() {
        return this.idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idServicoTipo", getIdServicoTipo())
            .append("idAtividade", getIdAtividade())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof ServicoTipoAtividadePK) ) return false;
        ServicoTipoAtividadePK castOther = (ServicoTipoAtividadePK) other;
        return new EqualsBuilder()
            .append(this.getIdServicoTipo(), castOther.getIdServicoTipo())
            .append(this.getIdAtividade(), castOther.getIdAtividade())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdServicoTipo())
            .append(getIdAtividade())
            .toHashCode();
    }

}
