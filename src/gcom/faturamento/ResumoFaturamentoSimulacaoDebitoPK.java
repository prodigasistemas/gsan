package gcom.faturamento;

import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResumoFaturamentoSimulacaoDebitoPK extends ObjetoGcom  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResumoFaturamentoSimulacao resumoFaturamentoSimulacao;
	
	private DebitoTipo debitoTipo;

	public ResumoFaturamentoSimulacao getResumoFaturamentoSimulacao() {
		return resumoFaturamentoSimulacao;
	}

	public void setResumoFaturamentoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao) {
		this.resumoFaturamentoSimulacao = resumoFaturamentoSimulacao;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("resumoFaturamentoSimulacao", getResumoFaturamentoSimulacao())
            .append("debitoTipo", getDebitoTipo())
            .toString();
    }
	@Override
    public boolean equals(Object other) {
    	boolean retorno = false;
		
		if (other instanceof ResumoFaturamentoSimulacaoDebitoPK) {
			
			ResumoFaturamentoSimulacaoDebitoPK castOther = (ResumoFaturamentoSimulacaoDebitoPK) other;
			
			if(castOther.getResumoFaturamentoSimulacao()!=null && castOther.getDebitoTipo()!=null){
			
				retorno = this.getResumoFaturamentoSimulacao().getId().compareTo(
							castOther.getResumoFaturamentoSimulacao().getId())==0 && 
						this.getDebitoTipo().getId().compareTo(
							castOther.getDebitoTipo().getId())==0;
			
			}
		}
		

		return retorno;
    }
    
    @Override
    public int hashCode() {
        return (getResumoFaturamentoSimulacao().getId()+""+getDebitoTipo().getId()).hashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "resumoFaturamentoSimulacao";
 		retorno[1] = "debitoTipo";
 		return retorno;		
	}

}
