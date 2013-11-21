package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoGcom;

public class RegistroAtendimentoPagamentoDuplicidadePK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	private Integer registroAtendimentoId;
	
	private Integer pagamentoId;

	public RegistroAtendimentoPagamentoDuplicidadePK() {
		super();
	}

	public RegistroAtendimentoPagamentoDuplicidadePK(Integer registroAtendimentoId, Integer pagamentoId) {
		super();
		this.registroAtendimentoId = registroAtendimentoId;
		this.pagamentoId = pagamentoId;
	}

	public Integer getRegistroAtendimentoId() {
		return registroAtendimentoId;
	}

	public void setRegistroAtendimentoId(Integer registroAtendimentoId) {
		this.registroAtendimentoId = registroAtendimentoId;
	}

	public Integer getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(Integer pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "registroAtendimentoId";
 		retorno[1] = "pagamentoId";
 		return retorno;
	}
}
