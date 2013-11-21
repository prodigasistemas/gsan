package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class CobrancaBoletimDescontoPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer cobrancaBoletimMedicaoId;

    private Integer ordemServicoId;

	public CobrancaBoletimDescontoPK() {
		super();
	}

	public CobrancaBoletimDescontoPK(Integer cobrancaBoletimMedicaoId, Integer ordemServicoId) {
		super();
		this.cobrancaBoletimMedicaoId = cobrancaBoletimMedicaoId;
		this.ordemServicoId = ordemServicoId;
	}

	public Integer getCobrancaBoletimMedicaoId() {
		return cobrancaBoletimMedicaoId;
	}

	public void setCobrancaBoletimMedicaoId(Integer cobrancaBoletimMedicaoId) {
		this.cobrancaBoletimMedicaoId = cobrancaBoletimMedicaoId;
	}

	public Integer getOrdemServicoId() {
		return ordemServicoId;
	}

	public void setOrdemServicoId(Integer ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "cobrancaBoletimMedicaoId";
		retorno[1] = "ordemServicoId";
		return retorno;
	} 

}
