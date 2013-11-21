package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoGcom;

public class AnormalidadeComandoOSSPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer comandoOrdemSeletivaId;
	
	private Integer leituraAnormalidadeId;

	public AnormalidadeComandoOSSPK() {
		super();
	}

	public Integer getComandoOrdemSeletivaId() {
		return comandoOrdemSeletivaId;
	}

	public void setComandoOrdemSeletivaId(Integer comandoOrdemSeletivaId) {
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
	}

	public Integer getLeituraAnormalidadeId() {
		return leituraAnormalidadeId;
	}

	public void setLeituraAnormalidadeId(Integer leituraAnormalidadeId) {
		this.leituraAnormalidadeId = leituraAnormalidadeId;
	}
	
	public AnormalidadeComandoOSSPK(Integer comandoOrdemSeletivaId, Integer leituraAnormalidadeId) {
		super();
		// TODO Auto-generated constructor stub
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
		this.leituraAnormalidadeId = leituraAnormalidadeId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "comandoOrdemSeletivaId";
 		retorno[1] = "leituraAnormalidadeId";
 		return retorno;
	}
}
