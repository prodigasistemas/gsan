/**
 * 
 */
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author gcom
 *
 */
public class CriterioSituacaoLigacaoEsgotoPK extends ObjetoGcom {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CobrancaCriterio cobrancaCriterio;
	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	
	public CriterioSituacaoLigacaoEsgotoPK() {
	
	}

	public CriterioSituacaoLigacaoEsgotoPK(CobrancaCriterio cobrancaCriterio, LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		super();
		// TODO Auto-generated constructor stub
		this.cobrancaCriterio = cobrancaCriterio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}



	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}


	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}



	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}



	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	 public String toString() {
	    return new ToStringBuilder(this).append("cobrancaCriterio", getCobrancaCriterio())
	    	.append("ligacaoEsgotoSituacao", getLigacaoEsgotoSituacao()).toString();
	}
	
	public boolean equals(Object other) {
	    if ((this == other))
	        return true;
	    if (!(other instanceof CriterioSituacaoLigacaoEsgotoPK))
	        return false;
	    CriterioSituacaoLigacaoEsgotoPK castOther = (CriterioSituacaoLigacaoEsgotoPK) other;
	    return new EqualsBuilder().append(this.getCobrancaCriterio(),
	            castOther.getCobrancaCriterio()).append(this.getLigacaoEsgotoSituacao(),
	            castOther.getLigacaoEsgotoSituacao()).isEquals();
	}
	
	public int hashCode() {
	    return new HashCodeBuilder().append(getCobrancaCriterio()).append(
	            getLigacaoEsgotoSituacao()).toHashCode();
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
		retorno[0] = "cobrancaCriterio";
		retorno[1] = "ligacaoEsgotoSituacao";
		return retorno;		
	}
	
}
