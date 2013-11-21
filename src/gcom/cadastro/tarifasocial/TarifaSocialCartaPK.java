package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoGcom;

public class TarifaSocialCartaPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer clienteId;
	
	private Integer imovelId;

	private Integer tarifaSocialComandoCartaID;
	
	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[3];
 		retorno[0] = "clienteId";
 		retorno[1] = "imovelId";
 		retorno[2] = "tarifaSocialComandoCartaID";
 		return retorno;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
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
