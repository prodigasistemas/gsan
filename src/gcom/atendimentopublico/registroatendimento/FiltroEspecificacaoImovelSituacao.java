package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro da Especificação da Situação do Imóvel
 * 
 * @author Sávio Luiz
 * @since 31/07/2006
 */
public class FiltroEspecificacaoImovelSituacao extends Filtro implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroEspecificacaoImovelSituacao() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroEspecificacaoImovelSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";

}
