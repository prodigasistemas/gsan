package gcom.cobranca;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao 
	extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCobrancaAcao object
	 */
	public FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao() {
	}

	/**
	 * Constructor for the FiltroCobrancaAcao object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_ACAO_ATIVIDADE_COMANDO_ID = "cobrancaAcaoAtividadeComando.id";
	
	/**
	 * Description of the Field
	 */
	public final static String FISCALIZACAO_SITUACAO = "fiscalizacaoSituacao";
}
