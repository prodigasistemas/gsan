package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EspecificacaoServicoTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short ordemExecucao;

    /** nullable persistent field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** full constructor */
    public EspecificacaoServicoTipo(gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK comp_id, Date ultimaAlteracao, Short ordemExecucao, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.ordemExecucao = ordemExecucao;
        this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
        this.servicoTipo = servicoTipo;
    }

    /** default constructor */
    public EspecificacaoServicoTipo() {
    }

    /** minimal constructor */
    public EspecificacaoServicoTipo(gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getOrdemExecucao() {
        return this.ordemExecucao;
    }

    public void setOrdemExecucao(Short ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
    }

    public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
        return this.solicitacaoTipoEspecificacao;
    }

    public void setSolicitacaoTipoEspecificacao(SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
        this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof EspecificacaoServicoTipo) ) return false;
        EspecificacaoServicoTipo castOther = (EspecificacaoServicoTipo) other;
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
