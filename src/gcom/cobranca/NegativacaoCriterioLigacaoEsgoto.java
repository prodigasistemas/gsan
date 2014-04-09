package gcom.cobranca;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioLigacaoEsgoto implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterioLigacaoEsgotoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** nullable persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** full constructor */
    public NegativacaoCriterioLigacaoEsgoto(NegativacaoCriterioLigacaoEsgotoPK comp_id, Date ultimaAlteracao,
    		gcom.cobranca.NegativacaoCriterio negativacaoCriterio, LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    /** default constructor */
    public NegativacaoCriterioLigacaoEsgoto() {
    }

    /** minimal constructor */
    public NegativacaoCriterioLigacaoEsgoto(NegativacaoCriterioLigacaoEsgotoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public NegativacaoCriterioLigacaoEsgotoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(NegativacaoCriterioLigacaoEsgotoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
        return this.negativacaoCriterio;
    }

    public void setNegativacaoCriterio(gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.negativacaoCriterio = negativacaoCriterio;
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

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioLigacaoAgua) ) return false;
        NegativacaoCriterioLigacaoAgua castOther = (NegativacaoCriterioLigacaoAgua) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
