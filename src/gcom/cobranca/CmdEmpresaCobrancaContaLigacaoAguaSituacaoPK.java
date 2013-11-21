package gcom.cobranca;

import gcom.interceptor.ObjetoGcom;

public class CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer comandoEmpresaCobrancaContaId;

    private Integer ligacaoAguaSituacaoId;

	public CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK() {
		super();
	}

	public CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK(Integer comandoEmpresaCobrancaContaId, Integer ligacaoAguaSituacaoId) {
		super();
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
	}

	public Integer getComandoEmpresaCobrancaContaId() {
		return comandoEmpresaCobrancaContaId;
	}

	public void setComandoEmpresaCobrancaContaId(
			Integer comandoEmpresaCobrancaContaId) {
		this.comandoEmpresaCobrancaContaId = comandoEmpresaCobrancaContaId;
	}

	public Integer getLigacaoAguaSituacaoId() {
		return ligacaoAguaSituacaoId;
	}

	public void setLigacaoAguaSituacaoId(Integer ligacaoAguaSituacaoId) {
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "comandoEmpresaCobrancaContaId";
		retorno[1] = "ligacaoAguaSituacaoId";
		return retorno;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
