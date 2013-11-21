package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class CobrancaBoletimSucessoPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer cobrancaBoletimMedicaoId;

    private Integer imovelId;

	public CobrancaBoletimSucessoPK() {
		super();
	}

	public CobrancaBoletimSucessoPK(Integer cobrancaBoletimMedicaoId, Integer imovelId) {
		super();
		this.cobrancaBoletimMedicaoId = cobrancaBoletimMedicaoId;
		this.imovelId = imovelId;
	}

	public Integer getCobrancaBoletimMedicaoId() {
		return cobrancaBoletimMedicaoId;
	}

	public void setCobrancaBoletimMedicaoId(Integer cobrancaBoletimMedicaoId) {
		this.cobrancaBoletimMedicaoId = cobrancaBoletimMedicaoId;
	}

	public Integer getImovelId() {
		return imovelId;
	}

	public void setImovelId(Integer imovelId) {
		this.imovelId = imovelId;
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "cobrancaBoletimMedicaoId";
		retorno[1] = "imovelId";
		return retorno;
	} 

}
