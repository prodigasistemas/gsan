package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoGcom;

public class RegistroAtendimentoContaPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer registroAtendimentoId;
	
	private Integer contaId;

	public RegistroAtendimentoContaPK() {
		super();
	}

	public RegistroAtendimentoContaPK(Integer registroAtendimentoId, Integer contaId) {
		super();
		this.registroAtendimentoId = registroAtendimentoId;
		this.contaId = contaId;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getRegistroAtendimentoId() {
		return registroAtendimentoId;
	}

	public void setRegistroAtendimentoId(Integer registroAtendimentoId) {
		this.registroAtendimentoId = registroAtendimentoId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "registroAtendimentoId";
 		retorno[1] = "contaId";
 		return retorno;
	}
}
