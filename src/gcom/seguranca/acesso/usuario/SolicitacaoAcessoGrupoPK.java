package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.Grupo;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Descrição da classe SolicitacaoAcessoGrupoPK
 * 
 * @author Hugo Leonardo
 * @date 03/11/2010
 */
public class SolicitacaoAcessoGrupoPK extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private SolicitacaoAcesso solicitacaoAcesso;
	
    /** identifier field */
    private Grupo grupo;

    /** full constructor */
    public SolicitacaoAcessoGrupoPK(SolicitacaoAcesso solicitacaoAcesso, Grupo grupo) {
        this.solicitacaoAcesso = solicitacaoAcesso;
        this.grupo = grupo;
    }

    /** default constructor */
    public SolicitacaoAcessoGrupoPK() {
    }

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public SolicitacaoAcesso getSolicitacaoAcesso() {
		return solicitacaoAcesso;
	}

	public void setSolicitacaoAcesso(SolicitacaoAcesso solicitacaoAcesso) {
		this.solicitacaoAcesso = solicitacaoAcesso;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("solicitacaoAcesso", getSolicitacaoAcesso())
            .append("grupo", getGrupo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        
        if ( !(other instanceof SolicitacaoAcessoGrupoPK) ) return false;
        
        SolicitacaoAcessoGrupoPK castOther = (SolicitacaoAcessoGrupoPK) other;
        return new EqualsBuilder()
            .append(this.getSolicitacaoAcesso(), castOther.getSolicitacaoAcesso())
            .append(this.getGrupo(), castOther.getGrupo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSolicitacaoAcesso())
            .append(getGrupo())
            .toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
 		retorno[0] = "solicitacaoAcesso";
 		retorno[1] = "grupo";
 		return retorno;
	}
	
}
