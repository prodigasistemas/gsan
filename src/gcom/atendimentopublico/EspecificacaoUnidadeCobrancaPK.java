package gcom.atendimentopublico;

import gcom.interceptor.ObjetoGcom;

public class EspecificacaoUnidadeCobrancaPK extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    private Integer solicitacaoTipoEspecificacaoId;

    private Integer cobrancaSituacaoId;

	public EspecificacaoUnidadeCobrancaPK() {
		super();
	}

	public EspecificacaoUnidadeCobrancaPK(Integer solicitacaoTipoEspecificacaoId, Integer cobrancaSituacaoId) {
		super();
		this.solicitacaoTipoEspecificacaoId = solicitacaoTipoEspecificacaoId;
		this.cobrancaSituacaoId = cobrancaSituacaoId;
	}

	public Integer getCobrancaSituacaoId() {
		return cobrancaSituacaoId;
	}

	public void setCobrancaSituacaoId(Integer cobrancaSituacaoId) {
		this.cobrancaSituacaoId = cobrancaSituacaoId;
	}

	public Integer getSolicitacaoTipoEspecificacaoId() {
		return solicitacaoTipoEspecificacaoId;
	}

	public void setSolicitacaoTipoEspecificacaoId(
			Integer solicitacaoTipoEspecificacaoId) {
		this.solicitacaoTipoEspecificacaoId = solicitacaoTipoEspecificacaoId;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "solicitacaoTipoEspecificacaoId";
		retorno[1] = "cobrancaSituacaoId";
		return retorno;
	} 

}
