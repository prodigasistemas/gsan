/**
 * 
 */
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author gcom
 *
 */
public class CriterioSituacaoLigacaoAguaPK extends ObjetoGcom {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CobrancaCriterio cobrancaCriterio;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	public CriterioSituacaoLigacaoAguaPK() {
	
	}

	public CriterioSituacaoLigacaoAguaPK(CobrancaCriterio cobrancaCriterio, LigacaoAguaSituacao ligacaoAguaSituacao) {
		super();
		// TODO Auto-generated constructor stub
		this.cobrancaCriterio = cobrancaCriterio;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}



	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}


	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}



	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}



	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	 public String toString() {
	    return new ToStringBuilder(this).append("cobrancaCriterio", getCobrancaCriterio())
	    	.append("ligacaoAguaSituacao", getLigacaoAguaSituacao()).toString();
	}
	
	public boolean equals(Object other) {
	    if ((this == other))
	        return true;
	    if (!(other instanceof CriterioSituacaoLigacaoAguaPK))
	        return false;
	    CriterioSituacaoLigacaoAguaPK castOther = (CriterioSituacaoLigacaoAguaPK) other;
	    return new EqualsBuilder().append(this.getCobrancaCriterio(),
	            castOther.getCobrancaCriterio()).append(this.getLigacaoAguaSituacao(),
	            castOther.getLigacaoAguaSituacao()).isEquals();
	}
	
	public int hashCode() {
	    return new HashCodeBuilder().append(getCobrancaCriterio()).append(
	            getLigacaoAguaSituacao()).toHashCode();
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
		retorno[0] = "cobrancaCriterio";
		retorno[1] = "ligacaoAguaSituacao";
		return retorno;		
	}
	
}
