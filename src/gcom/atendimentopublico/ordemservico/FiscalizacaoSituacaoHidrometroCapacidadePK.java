package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoHidrometroCapacidadePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer idFiscalizacaoSituacao;

    /** identifier field */
    private Integer idHidrometroCapacidade;


    public String toString() {
        return new ToStringBuilder(this)
            .append("fzstId", getIdFiscalizacaoSituacao())
            .append("hicpId", getIdHidrometroCapacidade())
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
	 * @return Retorna o campo idHidrometroCapacidade.
	 */
	public Integer getIdHidrometroCapacidade() {
		return idHidrometroCapacidade;
	}


	/**
	 * @param idHidrometroCapacidade O idHidrometroCapacidade a ser setado.
	 */
	public void setIdHidrometroCapacidade(Integer idHidrometroCapacidade) {
		this.idHidrometroCapacidade = idHidrometroCapacidade;
	}

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FiscalizacaoSituacaoHidrometroCapacidadePK) ) return false;
        FiscalizacaoSituacaoHidrometroCapacidadePK castOther = (FiscalizacaoSituacaoHidrometroCapacidadePK) other;
        return new EqualsBuilder()
            .append(this.getIdFiscalizacaoSituacao(), castOther.getIdFiscalizacaoSituacao())
            .append(this.getIdHidrometroCapacidade(), castOther.getIdHidrometroCapacidade())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdFiscalizacaoSituacao())
            .append(getIdHidrometroCapacidade())
            .toHashCode();
    }
	
	
}
