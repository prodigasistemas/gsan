package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoEsgotoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idFiscalizacaoSituacao;

    /** identifier field */
    private Integer idLigacaoEsgotoSituacao;


    public String toString() {
        return new ToStringBuilder(this)
            .append("fzstId", getIdFiscalizacaoSituacao())
            .append("lestId", getIdLigacaoEsgotoSituacao())
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
	 * @return Retorna o campo idLigacaoEsgotoSituacao.
	 */
	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}


	/**
	 * @param idLigacaoEsgotoSituacao O idLigacaoEsgotoSituacao a ser setado.
	 */
	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FiscalizacaoSituacaoEsgotoPK) ) return false;
        FiscalizacaoSituacaoEsgotoPK castOther = (FiscalizacaoSituacaoEsgotoPK) other;
        return new EqualsBuilder()
            .append(this.getIdFiscalizacaoSituacao(), castOther.getIdFiscalizacaoSituacao())
            .append(this.getIdLigacaoEsgotoSituacao(), castOther.getIdLigacaoEsgotoSituacao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdFiscalizacaoSituacao())
            .append(getIdLigacaoEsgotoSituacao())
            .toHashCode();
    }	
}
