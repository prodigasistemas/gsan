package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoAguaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idFiscalizacaoSituacao;

    /** identifier field */
    private Integer idLigacaoAguaSituacao;


    public String toString() {
        return new ToStringBuilder(this)
            .append("fzstId", getIdFiscalizacaoSituacao())
            .append("lastId", getIdLigacaoAguaSituacao())
            .toString();
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


	/**
	 * @return Retorna o campo idLigacaoAguaSituacao.
	 */
	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}


	/**
	 * @param idLigacaoAguaSituacao O idLigacaoAguaSituacao a ser setado.
	 */
	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FiscalizacaoSituacaoAguaPK) ) return false;
        FiscalizacaoSituacaoAguaPK castOther = (FiscalizacaoSituacaoAguaPK) other;
        return new EqualsBuilder()
            .append(this.getIdFiscalizacaoSituacao(), castOther.getIdFiscalizacaoSituacao())
            .append(this.getIdLigacaoAguaSituacao(), castOther.getIdLigacaoAguaSituacao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdFiscalizacaoSituacao())
            .append(getIdLigacaoAguaSituacao())
            .toHashCode();
    }	
	
}
