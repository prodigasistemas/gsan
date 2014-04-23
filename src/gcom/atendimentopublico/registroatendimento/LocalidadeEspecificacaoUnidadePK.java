package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Descrição da classe LocalidadeEspecificacaoUnidadePK
 * 
 * @author Hugo Leonardo
 * @date 29/11/2010
 */
public class LocalidadeEspecificacaoUnidadePK extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Localidade localidade;
	
	/** identifier field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
    /** identifier field */
    private UnidadeOrganizacional unidadeOrganizacional;

    /** full constructor */
    public LocalidadeEspecificacaoUnidadePK(Localidade localidade, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, 
    		UnidadeOrganizacional unidadeOrganizacional) {
    	
        this.localidade = localidade;
    	this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    /** default constructor */
    public LocalidadeEspecificacaoUnidadePK() {
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

	public String toString() {
        return new ToStringBuilder(this)
            .append("localidade", getLocalidade())
        	.append("solicitacaoTipoEspecificacao", getSolicitacaoTipoEspecificacao())
            .append("unidadeOrganizacional", getUnidadeOrganizacional())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        
        if ( !(other instanceof LocalidadeEspecificacaoUnidadePK) ) return false;
        
        LocalidadeEspecificacaoUnidadePK castOther = (LocalidadeEspecificacaoUnidadePK) other;
        
        boolean igual = this.getLocalidade().getId().equals(castOther.getLocalidade().getId()) 
        	&& this.getSolicitacaoTipoEspecificacao().getId().equals(castOther.getSolicitacaoTipoEspecificacao().getId())
            && this.getUnidadeOrganizacional().getId().equals(castOther.getUnidadeOrganizacional().getId());
        
        return igual;
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLocalidade())
        	.append(getSolicitacaoTipoEspecificacao())
            .append(getUnidadeOrganizacional())
            .toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[3];
		retorno[0] = "localidade";
 		retorno[1] = "solicitacaoTipoEspecificacao";
 		retorno[2] = "unidadeOrganizacional";
 		return retorno;
	}
	
	public void initializeLazy(){
		if (this.getLocalidade() != null){
			this.getLocalidade().initializeLazy();
		}
		
		if (this.getSolicitacaoTipoEspecificacao() != null){
			this.getSolicitacaoTipoEspecificacao().initializeLazy();
		}	
		
		if (this.getUnidadeOrganizacional() != null){
			this.getUnidadeOrganizacional().initializeLazy();
		}
	}
	
}
