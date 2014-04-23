package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EspecificacaoServicoTipoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idSolicitacaoTipoEspecificacao;

    /** identifier field */
    private Integer idServicoTipo;

    /** full constructor */
    public EspecificacaoServicoTipoPK(Integer idSolicitacaoTipoEspecificacao, Integer idServicoTipo) {
        this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
        this.idServicoTipo = idServicoTipo;
    }

    /** default constructor */
    public EspecificacaoServicoTipoPK() {
    }

    public Integer getIdSolicitacaoTipoEspecificacao() {
        return this.idSolicitacaoTipoEspecificacao;
    }

    public void setIdSolicitacaoTipoEspecificacao(Integer idSolicitacaoTipoEspecificacao) {
        this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
    }

    public Integer getIdServicoTipo() {
        return this.idServicoTipo;
    }

    public void setIdServicoTipo(Integer idServicoTipo) {
        this.idServicoTipo = idServicoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idSolicitacaoTipoEspecificacao", getIdSolicitacaoTipoEspecificacao())
            .append("idServicoTipo", getIdServicoTipo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof EspecificacaoServicoTipoPK) ) return false;
        EspecificacaoServicoTipoPK castOther = (EspecificacaoServicoTipoPK) other;
        return new EqualsBuilder()
            .append(this.getIdSolicitacaoTipoEspecificacao(), castOther.getIdSolicitacaoTipoEspecificacao())
            .append(this.getIdServicoTipo(), castOther.getIdServicoTipo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdSolicitacaoTipoEspecificacao())
            .append(getIdServicoTipo())
            .toHashCode();
    }

}
