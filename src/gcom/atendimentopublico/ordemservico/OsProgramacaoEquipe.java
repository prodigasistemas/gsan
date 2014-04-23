package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OsProgramacaoEquipe implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.ordemservico.OsProgramacaoEquipePK comp_id;

    /** persistent field */
    private Date ultimaalteracao;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.Equipe equipe;

    /** nullable persistent field */
    private gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao;

    /** full constructor */
    public OsProgramacaoEquipe(gcom.atendimentopublico.ordemservico.OsProgramacaoEquipePK comp_id, Date ultimaalteracao, gcom.atendimentopublico.ordemservico.Equipe equipe, gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao ordemServicoProgramacao) {
        this.comp_id = comp_id;
        this.ultimaalteracao = ultimaalteracao;
        this.equipe = equipe;
        this.ordemServicoProgramacao = ordemServicoProgramacao;
    }

    /** default constructor */
    public OsProgramacaoEquipe() {
    }

    /** minimal constructor */
    public OsProgramacaoEquipe(gcom.atendimentopublico.ordemservico.OsProgramacaoEquipePK comp_id, Date ultimaalteracao) {
        this.comp_id = comp_id;
        this.ultimaalteracao = ultimaalteracao;
    }

    public gcom.atendimentopublico.ordemservico.OsProgramacaoEquipePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.ordemservico.OsProgramacaoEquipePK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaalteracao() {
        return this.ultimaalteracao;
    }

    public void setUltimaalteracao(Date ultimaalteracao) {
        this.ultimaalteracao = ultimaalteracao;
    }

    public gcom.atendimentopublico.ordemservico.Equipe getEquipe() {
        return this.equipe;
    }

    public void setEquipe(gcom.atendimentopublico.ordemservico.Equipe equipe) {
        this.equipe = equipe;
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
        if ( !(other instanceof OsProgramacaoEquipe) ) return false;
        OsProgramacaoEquipe castOther = (OsProgramacaoEquipe) other;
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
