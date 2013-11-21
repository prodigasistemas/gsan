package gcom.faturamento;

import gcom.faturamento.credito.CreditoTipo;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResumoFaturamentoSimulacaoCreditoPK extends ObjetoGcom  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResumoFaturamentoSimulacao resumoFaturamentoSimulacao;
	
	private CreditoTipo creditoTipo;

	public ResumoFaturamentoSimulacao getResumoFaturamentoSimulacao() {
		return resumoFaturamentoSimulacao;
	}

	public void setResumoFaturamentoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao) {
		this.resumoFaturamentoSimulacao = resumoFaturamentoSimulacao;
	}
	
	public CreditoTipo getCreditoTipo() {
		return creditoTipo;
	}

	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("resumoFaturamentoSimulacao", getResumoFaturamentoSimulacao())
            .append("creditoTipo", getCreditoTipo())
            .toString();
    }
	@Override
	public boolean equals(Object other) {
    	boolean retorno = false;
		
		if (other instanceof ResumoFaturamentoSimulacaoDebitoPK) {
			
			ResumoFaturamentoSimulacaoCreditoPK castOther = (ResumoFaturamentoSimulacaoCreditoPK) other;
			
			if(castOther.getResumoFaturamentoSimulacao()!=null && castOther.getCreditoTipo()!=null){
				retorno = this.getResumoFaturamentoSimulacao().getId().compareTo(
							castOther.getResumoFaturamentoSimulacao().getId())==0 && 
						  this.getCreditoTipo().getId().compareTo(
							castOther.getCreditoTipo().getId())==0;
			}
		}
		

		return retorno;
    }
    
    @Override
    public int hashCode() {
        return (getResumoFaturamentoSimulacao().getId()+""+getCreditoTipo().getId()).hashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "resumoFaturamentoSimulacao";
 		retorno[1] = "creditoTipo";
 		return retorno;		
	}


}
