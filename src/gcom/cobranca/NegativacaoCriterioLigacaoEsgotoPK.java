package gcom.cobranca;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioLigacaoEsgotoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** full constructor */
    public NegativacaoCriterioLigacaoEsgotoPK(NegativacaoCriterio negativacaoCriterio, LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    /** default constructor */
    public NegativacaoCriterioLigacaoEsgotoPK() {
    }

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo negativacaoCriterio.
	 */
	public NegativacaoCriterio getNegativacaoCriterio() {
		return negativacaoCriterio;
	}

	/**
	 * @param negativacaoCriterio O negativacaoCriterio a ser setado.
	 */
	public void setNegativacaoCriterio(NegativacaoCriterio negativacaoCriterio) {
		this.negativacaoCriterio = negativacaoCriterio;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("negativacaoCriterio", getNegativacaoCriterio())
            .append("ligacaoEsgotoSituacao", getLigacaoEsgotoSituacao())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioLigacaoEsgotoPK) ) return false;
        NegativacaoCriterioLigacaoEsgotoPK castOther = (NegativacaoCriterioLigacaoEsgotoPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getLigacaoEsgotoSituacao(), castOther.getLigacaoEsgotoSituacao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getLigacaoEsgotoSituacao())
            .toHashCode();
    }

}
