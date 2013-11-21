package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class ComandoEmpresaCobrancaContaGerenciaPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer comandoEmpresaCobrancaContaId;

    private Integer gerenciaRegionalId;

	public ComandoEmpresaCobrancaContaGerenciaPK() {
		super();
	}

	public ComandoEmpresaCobrancaContaGerenciaPK(Integer comandoEmpresaCobrancaContaId, Integer gerenciaRegionalId) {
		super();
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	public void setGerenciaRegionalId(Integer gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "gerenciaRegionalId";
		return retorno;
	} 
}
