package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoTipoAtividade implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.ordemservico.ServicoTipoAtividadePK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short numeroExecucao;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.Atividade atividade;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

    /** full constructor */
    public ServicoTipoAtividade(gcom.atendimentopublico.ordemservico.ServicoTipoAtividadePK comp_id, Date ultimaAlteracao, Short numeroExecucao, gcom.atendimentopublico.ordemservico.Atividade atividade, gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroExecucao = numeroExecucao;
        this.atividade = atividade;
        this.servicoTipo = servicoTipo;
    }

    /** default constructor */
    public ServicoTipoAtividade() {
    }

    /** minimal constructor */
    public ServicoTipoAtividade(gcom.atendimentopublico.ordemservico.ServicoTipoAtividadePK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.ServicoTipoAtividadePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.ordemservico.ServicoTipoAtividadePK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getNumeroExecucao() {
        return this.numeroExecucao;
    }

    public void setNumeroExecucao(Short numeroExecucao) {
        this.numeroExecucao = numeroExecucao;
    }

    public gcom.atendimentopublico.ordemservico.Atividade getAtividade() {
        return this.atividade;
    }

    public void setAtividade(gcom.atendimentopublico.ordemservico.Atividade atividade) {
        this.atividade = atividade;
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
        if ( !(other instanceof ServicoTipoAtividade) ) return false;
        ServicoTipoAtividade castOther = (ServicoTipoAtividade) other;
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
