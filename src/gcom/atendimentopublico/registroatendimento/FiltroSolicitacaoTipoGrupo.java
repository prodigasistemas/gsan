package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento
 * @author Sávio Luiz
 * @since 25/07/2006
 *
 */
public class FiltroSolicitacaoTipoGrupo  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroSolicitacaoTipoGrupo() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroSolicitacaoTipoGrupo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	public final static String SOLICITACAO_TIPO_GRUPO = "solicitacaoTipoGrupo";
	
	public final static String INDICADOR_FALTA_AGUA = "indicadorFaltaAgua";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
}
