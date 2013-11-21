package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoGcom;

public class TarifaSocialCartaDebitoPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer contaId;
	
	private Integer imovelId;

	private Integer tarifaSocialComandoCartaID;
	
	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[3];
 		retorno[0] = "contaId";
 		retorno[1] = "imovelId";
 		retorno[2] = "tarifaSocialComandoCartaID";
 		return retorno;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getImovelId() {
		return imovelId;
	}

	public void setImovelId(Integer imovelId) {
		this.imovelId = imovelId;
	}

	public Integer getTarifaSocialComandoCartaID() {
		return tarifaSocialComandoCartaID;
	}

	public void setTarifaSocialComandoCartaID(Integer tarifaSocialComandoCartaID) {
		this.tarifaSocialComandoCartaID = tarifaSocialComandoCartaID;
	}
}
