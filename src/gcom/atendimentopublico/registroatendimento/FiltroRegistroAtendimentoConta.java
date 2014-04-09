package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para pesquisar o Registro de Atendimento
 * @author Rafael Santos
 * @since 09/01/2006
 * 
 */
public class FiltroRegistroAtendimentoConta  extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroCliente object
	 */
	public FiltroRegistroAtendimentoConta() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroRegistroAtendimentoConta(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
    public final static String CONTA = "conta";
    
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	
    public final static String CONTA_ID = "conta.id";
	
	
}
