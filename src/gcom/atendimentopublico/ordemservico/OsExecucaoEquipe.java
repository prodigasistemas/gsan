package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsExecucaoEquipe implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.ordemservico.OsExecucaoEquipePK comp_id;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.Equipe equipe;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao osAtividadePeriodoExecucao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao;

    /** full constructor */
    public OsExecucaoEquipe(gcom.atendimentopublico.ordemservico.OsExecucaoEquipePK comp_id, gcom.atendimentopublico.ordemservico.Equipe equipe, gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao osAtividadePeriodoExecucao, gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao) {
        this.comp_id = comp_id;
        this.equipe = equipe;
        this.osAtividadePeriodoExecucao = osAtividadePeriodoExecucao;
        this.ordemServicoProgramacao = ordemServicoProgramacao;
    }

    /** default constructor */
    public OsExecucaoEquipe() {
    }

    /** minimal constructor */
    public OsExecucaoEquipe(gcom.atendimentopublico.ordemservico.OsExecucaoEquipePK comp_id, gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao) {
        this.comp_id = comp_id;
        this.ordemServicoProgramacao = ordemServicoProgramacao;
    }

    public gcom.atendimentopublico.ordemservico.OsExecucaoEquipePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.ordemservico.OsExecucaoEquipePK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.atendimentopublico.ordemservico.Equipe getEquipe() {
        return this.equipe;
    }

    public void setEquipe(gcom.atendimentopublico.ordemservico.Equipe equipe) {
        this.equipe = equipe;
    }

    public gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao getOsAtividadePeriodoExecucao() {
        return this.osAtividadePeriodoExecucao;
    }

    public void setOsAtividadePeriodoExecucao(gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao osAtividadePeriodoExecucao) {
        this.osAtividadePeriodoExecucao = osAtividadePeriodoExecucao;
    }

    public gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao getOrdemServicoProgramacao() {
        return this.ordemServicoProgramacao;
    }

    public void setOrdemServicoProgramacao(gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao) {
        this.ordemServicoProgramacao = ordemServicoProgramacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof OsExecucaoEquipe) ) return false;
        OsExecucaoEquipe castOther = (OsExecucaoEquipe) other;
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
