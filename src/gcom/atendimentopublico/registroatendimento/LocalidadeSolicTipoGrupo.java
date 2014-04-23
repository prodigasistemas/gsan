package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LocalidadeSolicTipoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo;

    /** nullable persistent field */
    private Localidade localidade;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacional;

    /** full constructor */
    public LocalidadeSolicTipoGrupo(gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupoPK comp_id, Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo, Localidade localidade, UnidadeOrganizacional unidadeOrganizacional) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
        this.localidade = localidade;
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    /** default constructor */
    public LocalidadeSolicTipoGrupo() {
    }

    /** minimal constructor */
    public LocalidadeSolicTipoGrupo(gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupoPK comp_id, Date ultimaAlteracao, UnidadeOrganizacional unidadeOrganizacional) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    public gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo getSolicitacaoTipoGrupo() {
        return this.solicitacaoTipoGrupo;
    }

    public void setSolicitacaoTipoGrupo(gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo solicitacaoTipoGrupo) {
        this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public UnidadeOrganizacional getUnidadeOrganizacional() {
        return this.unidadeOrganizacional;
    }

    public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof LocalidadeSolicTipoGrupo) ) return false;
        LocalidadeSolicTipoGrupo castOther = (LocalidadeSolicTipoGrupo) other;
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
