package gcom.faturamento.conta;

import gcom.interceptor.ObjetoGcom;

public class ContaMotivoRetificacaoColunaPK extends ObjetoGcom{
	private static final long serialVersionUID = 1L;

	private Integer contaMotivoRetificacaoId;
	
	private Integer tabelaColunaId;


	public ContaMotivoRetificacaoColunaPK() {
		super();
	}

	public ContaMotivoRetificacaoColunaPK(Integer contaMotivoRetificacaoId, Integer tabelaColunaId) {
		super();
		this.contaMotivoRetificacaoId = contaMotivoRetificacaoId;
		this.tabelaColunaId = tabelaColunaId;
	}

	public Integer getContaMotivoRetificacaoId() {
		return contaMotivoRetificacaoId;
	}

	public void setContaMotivoRetificacaoId(Integer contaMotivoRetificacaoId) {
		this.contaMotivoRetificacaoId = contaMotivoRetificacaoId;
	}

	public Integer getTabelaColunaId() {
		return tabelaColunaId;
	}

	public void setTabelaColunaId(Integer tabelaColunaId) {
		this.tabelaColunaId = tabelaColunaId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "contaMotivoRetificacaoId";
 		retorno[1] = "tabelaColunaId";
 		return retorno;
	}
	
}
