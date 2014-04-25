package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LocalidadeSolicTipoGrupoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idLocalidade;

    /** identifier field */
    private Integer idSolicitacaoTipoGrupo;

    /** full constructor */
    public LocalidadeSolicTipoGrupoPK(Integer idLocalidade, Integer idSolicitacaoTipoGrupo) {
        this.idLocalidade = idLocalidade;
        this.idSolicitacaoTipoGrupo = idSolicitacaoTipoGrupo;
    }

    /** default constructor */
    public LocalidadeSolicTipoGrupoPK() {
    }

    public Integer getIdLocalidade() {
        return this.idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public Integer getIdSolicitacaoTipoGrupo() {
        return this.idSolicitacaoTipoGrupo;
    }

    public void setIdSolicitacaoTipoGrupo(Integer idSolicitacaoTipoGrupo) {
        this.idSolicitacaoTipoGrupo = idSolicitacaoTipoGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idLocalidade", getIdLocalidade())
            .append("idSolicitacaoTipoGrupo", getIdSolicitacaoTipoGrupo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LocalidadeSolicTipoGrupoPK) ) return false;
        LocalidadeSolicTipoGrupoPK castOther = (LocalidadeSolicTipoGrupoPK) other;
        return new EqualsBuilder()
            .append(this.getIdLocalidade(), castOther.getIdLocalidade())
            .append(this.getIdSolicitacaoTipoGrupo(), castOther.getIdSolicitacaoTipoGrupo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdLocalidade())
            .append(getIdSolicitacaoTipoGrupo())
            .toHashCode();
    }

}
