package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoServicoACobrarPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idFiscalizacaoSituacao;

    /** identifier field */
    private Integer idDebitoTipo;

    public String toString() {
        return new ToStringBuilder(this)
            .append("fzstId", getIdFiscalizacaoSituacao())
            .append("dbtpId", getIdDebitoTipo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FiscalizacaoSituacaoServicoACobrarPK) ) return false;
        FiscalizacaoSituacaoServicoACobrarPK castOther = (FiscalizacaoSituacaoServicoACobrarPK) other;
        return new EqualsBuilder()
            .append(this.getIdFiscalizacaoSituacao(), castOther.getIdFiscalizacaoSituacao())
            .append(this.getIdDebitoTipo(), castOther.getIdDebitoTipo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdFiscalizacaoSituacao())
            .append(getIdDebitoTipo())
            .toHashCode();
    }
    
    
		/**
		 * @return Retorna o campo idDebitoTipo.
		 */
		public Integer getIdDebitoTipo() {
			return idDebitoTipo;
		}

		/**
		 * @param idDebitoTipo O idDebitoTipo a ser setado.
		 */
		public void setIdDebitoTipo(Integer idDebitoTipo) {
			this.idDebitoTipo = idDebitoTipo;
		}

		/**
		 * @return Retorna o campo idFiscalizacaoSituacao.
		 */
		public Integer getIdFiscalizacaoSituacao() {
			return idFiscalizacaoSituacao;
		}

		/**
		 * @param idFiscalizacaoSituacao O idFiscalizacaoSituacao a ser setado.
		 */
		public void setIdFiscalizacaoSituacao(Integer idFiscalizacaoSituacao) {
			this.idFiscalizacaoSituacao = idFiscalizacaoSituacao;
		}

    
}
