package gcom.cobranca;

import org.apache.commons.lang.builder.HashCodeBuilder;

import gcom.interceptor.ObjetoGcom;

public class CobrancaAcaoOrdemServicoNaoAceitasPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer ordemServicoId;

    /** identifier field 
    private Integer cobrancaAcaoId; */
    
	public CobrancaAcaoOrdemServicoNaoAceitasPK(Integer ordemServicoId/*, Integer cobrancaAcaoId*/) {
		super();
		this.ordemServicoId = ordemServicoId;
		/*this.cobrancaAcaoId = cobrancaAcaoId;*/
	}

	public CobrancaAcaoOrdemServicoNaoAceitasPK() {
		super();
	}

	public Integer getOrdemServicoId() {
		return ordemServicoId;
	}
	
	/*
	public Integer getCobrancaAcaoId() {
		return cobrancaAcaoId;
	}

	public void setCobrancaAcaoId(Integer cobrancaAcaoId) {
		this.cobrancaAcaoId = cobrancaAcaoId;
	}
	*/
	
	public void setOrdemServicoId(Integer ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}

    public int hashCode() {
        return new HashCodeBuilder()
          /*  .append(getCobrancaAcaoId())*/
            .append(getOrdemServicoId())
            .toHashCode();
    }
    
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "ordemServicoId";
		/*retorno[1] = "cobrancaAcaoId";*/
		
		return retorno;
	}

}
