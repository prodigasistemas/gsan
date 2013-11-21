package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class ComandoEmpresaCobrancaContaImovelPerfilPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer comandoEmpresaCobrancaContaId;

    private Integer imovelPerfilId;

	public ComandoEmpresaCobrancaContaImovelPerfilPK() {
		super();
	}

	public ComandoEmpresaCobrancaContaImovelPerfilPK(Integer comandoEmpresaCobrancaContaId, Integer imovelPerfilId) {
		super();
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
		this.imovelPerfilId = imovelPerfilId;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getImovelPerfilId() {
		return imovelPerfilId;
	}

	public void setImovelPerfilId(Integer imovelPerfilId) {
		this.imovelPerfilId = imovelPerfilId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "imovelPerfilId";
		return retorno;
	} 
}
