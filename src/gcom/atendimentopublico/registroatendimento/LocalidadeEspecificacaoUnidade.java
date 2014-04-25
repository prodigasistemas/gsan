package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Descrição da classe Localidade Especificacao Unidade
 * 
 * @author Hugo Leonardo
 * @date 29/11/2010
 */
public class LocalidadeEspecificacaoUnidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidadePK comp_id;
    
    /** identifier field */
	private Localidade localidade;
	
	/** identifier field */
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;
	
	/** persistent field */
	private Date ultimaAlteracao;

	/** default constructor */
	public LocalidadeEspecificacaoUnidade() {
		
	}
	
    /** full constructor */
    public LocalidadeEspecificacaoUnidade(gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidadePK comp_id, 
    		Localidade localidade, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, UnidadeOrganizacional unidadeOrganizacional,
    		Date ultimaAlteracao) {
    	
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
        this.unidadeOrganizacional = unidadeOrganizacional;
        this.localidade = localidade;
    }
    
    /** minimal constructor */
    public LocalidadeEspecificacaoUnidade(gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidadePK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidadePK getComp_id() {
		return comp_id;
	}

	public void setComp_id(
			gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidadePK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        
        if ( !(other instanceof LocalidadeEspecificacaoUnidade) ) return false;
        
        LocalidadeEspecificacaoUnidade castOther = (LocalidadeEspecificacaoUnidade) other;
        
        boolean igual = this.getComp_id().equals(castOther.getComp_id());
    
        return igual;
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    
	public void initializeLazy(){
		if (this.getComp_id() != null){
			this.comp_id.initializeLazy();	
		}		
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

}
