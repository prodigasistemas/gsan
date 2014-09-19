package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoGcom;

public class LigacaoSitComandoOSSPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer comandoOrdemSeletivaId;
	
	private Integer ligacaoAguaSituacaoId;

	public LigacaoSitComandoOSSPK() {
		super();
	}

	public Integer getComandoOrdemSeletivaId() {
		return comandoOrdemSeletivaId;
	}

	public void setComandoOrdemSeletivaId(Integer comandoOrdemSeletivaId) {
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
	}
	
	public Integer getLigacaoAguaSituacaoId() {
		return ligacaoAguaSituacaoId;
	}

	public void setLigacaoAguaSituacaoId(Integer ligacaoAguaSituacaoId) {
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
	}

	public LigacaoSitComandoOSSPK(Integer comandoOrdemSeletivaId, Integer ligacaoAguaSituacaoId) {
		super();
		
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
		this.ligacaoAguaSituacaoId = ligacaoAguaSituacaoId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "comandoOrdemSeletivaId";
 		retorno[1] = "ligacaoAguaSituacaoId";
 		return retorno;
	}
}
