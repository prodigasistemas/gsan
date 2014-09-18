package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoGcom;

public class CapacidHidrComandoOSSPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer comandoOrdemSeletivaId;
	
	private Integer hidrometroCapacidadeId;


	public CapacidHidrComandoOSSPK(Integer comandoOrdemSeletivaId, Integer hidrometroCapacidadeId) {
		super();
		
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
		this.hidrometroCapacidadeId = hidrometroCapacidadeId;
	}

	public CapacidHidrComandoOSSPK() {
		super();
		
	}

	public Integer getComandoOrdemSeletivaId() {
		return comandoOrdemSeletivaId;
	}

	public void setComandoOrdemSeletivaId(Integer comandoOrdemSeletivaId) {
		this.comandoOrdemSeletivaId = comandoOrdemSeletivaId;
	}

	public Integer getHidrometroCapacidadeId() {
		return hidrometroCapacidadeId;
	}

	public void setHidrometroCapacidadeId(Integer hidrometroCapacidadeId) {
		this.hidrometroCapacidadeId = hidrometroCapacidadeId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "comandoOrdemSeletivaId";
 		retorno[1] = "hidrometroCapacidadeId";
 		return retorno;
	}
}
