package gcom.cobranca;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import gcom.interceptor.ObjetoGcom;

/**
 * @author gcom
 *
 */
public class CriterioSituacaoCobrancaPK extends ObjetoGcom {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CobrancaCriterio cobrancaCriterio;
	
	private CobrancaSituacao cobrancaSituacao;
	
	public CriterioSituacaoCobrancaPK() {
	}

	public CriterioSituacaoCobrancaPK(CobrancaCriterio cobrancaCriterio, CobrancaSituacao cobrancaSituacao) {
		super();
		// TODO Auto-generated constructor stub
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaSituacao = cobrancaSituacao;
	}



	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}


	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}



	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}



	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

	 public String toString() {
	    return new ToStringBuilder(this).append("cobrancaCriterio", getCobrancaCriterio())
	    	.append("cobrancaSituacao", getCobrancaSituacao()).toString();
	}
	
	public boolean equals(Object other) {
	    if ((this == other))
	        return true;
	    if (!(other instanceof CriterioSituacaoCobrancaPK))
	        return false;
	    CriterioSituacaoCobrancaPK castOther = (CriterioSituacaoCobrancaPK) other;
	    return new EqualsBuilder().append(this.getCobrancaCriterio(),
	            castOther.getCobrancaCriterio()).append(this.getCobrancaSituacao(),
	            castOther.getCobrancaSituacao()).isEquals();
	}
	
	public int hashCode() {
	    return new HashCodeBuilder().append(getCobrancaCriterio()).append(
	            getCobrancaSituacao()).toHashCode();
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
		retorno[0] = "cobrancaCriterio";
		retorno[1] = "cobrancaSituacao";
		return retorno;		
	}
	
}
