package gcom.cadastro.sistemaparametro;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Sistema Alteração Historico 
 * @author Kássia Albuquerque
 * @since 28/11/2006
 *
 */
public class FiltroSistemaAlteracaoHistorico extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroServicoPerfilTipo object
	 */
	public FiltroSistemaAlteracaoHistorico() {
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID_FUNCIONALIDADE = "funcionalidade.id";
	
	/**
	 * Description of the Field
	 */
	public final static String DATA = "data";
	
	
	/**
	 * Description of the Field
	 */
	public final static String NOME = "nome";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";
	
	
	public final static String ID = "id";
	
	
	public FiltroSistemaAlteracaoHistorico(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
