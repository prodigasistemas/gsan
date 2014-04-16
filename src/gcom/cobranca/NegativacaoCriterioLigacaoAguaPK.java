package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioLigacaoAguaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** full constructor */
    public NegativacaoCriterioLigacaoAguaPK(NegativacaoCriterio negativacaoCriterio, LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    /** default constructor */
    public NegativacaoCriterioLigacaoAguaPK() {
    }

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
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
            .append("ligacaoAguaSituacao", getLigacaoAguaSituacao())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioLigacaoAguaPK) ) return false;
        NegativacaoCriterioLigacaoAguaPK castOther = (NegativacaoCriterioLigacaoAguaPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getLigacaoAguaSituacao(), castOther.getLigacaoAguaSituacao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getLigacaoAguaSituacao())
            .toHashCode();
    }

}
