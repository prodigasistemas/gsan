package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class ComandoEmpresaCobrancaContaUnidadeNegocioPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer comandoEmpresaCobrancaContaId;

    private Integer unidadeNegocioId;

	public ComandoEmpresaCobrancaContaUnidadeNegocioPK() {
		super();
	}

	public ComandoEmpresaCobrancaContaUnidadeNegocioPK(Integer comandoEmpresaCobrancaContaId, Integer unidadeNegocioId) {
		super();
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
		this.unidadeNegocioId = unidadeNegocioId;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	public void setUnidadeNegocioId(Integer unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "unidadeNegocioId";
		return retorno;
	} 
}
