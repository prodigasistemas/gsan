package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Descrição da classe
 * 
 * @author Hugo Leonardo
 * @date 01/09/2010
 */
public class SolicitacaoDocumentoObrigatorioPK extends ObjetoGcom{
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;

    /** identifier field */
    private MeioSolicitacao meioSolicitacao;

    /** full constructor */
    public SolicitacaoDocumentoObrigatorioPK(SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, MeioSolicitacao meioSolicitacao) {
        this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
        this.meioSolicitacao = meioSolicitacao;
    }

    /** default constructor */
    public SolicitacaoDocumentoObrigatorioPK() {
    }
    
	public MeioSolicitacao getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(MeioSolicitacao meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("solicitacaoTipoEspecificacao", getSolicitacaoTipoEspecificacao())
            .append("meioSolicitacao", getMeioSolicitacao())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof SolicitacaoDocumentoObrigatorioPK) ) return false;
        SolicitacaoDocumentoObrigatorioPK castOther = (SolicitacaoDocumentoObrigatorioPK) other;
        return new EqualsBuilder()
            .append(this.getSolicitacaoTipoEspecificacao(), castOther.getSolicitacaoTipoEspecificacao())
            .append(this.getMeioSolicitacao(), castOther.getMeioSolicitacao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSolicitacaoTipoEspecificacao())
            .append(getMeioSolicitacao())
            .toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
 		retorno[0] = "solicitacaoTipoEspecificacao";
 		retorno[1] = "meioSolicitacao";
 		return retorno;
	}
	
	public void initializeLazy(){
		
		if (this.getSolicitacaoTipoEspecificacao() != null){
			this.getSolicitacaoTipoEspecificacao().initializeLazy();
		}		
	}

}
