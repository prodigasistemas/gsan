package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Perfil Tipo
 * @author Leonardo Regis
 * @since 25/07/2006
 *
 */
public class FiltroServicoPerfilTipo extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroServicoPerfilTipo object
	 */
	public FiltroServicoPerfilTipo() {
	}
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO = "descricao";
	
	/**
	 * Description of the Field
	 */
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";


	/**
	 * Description of the Field
	 */
	public final static String COMPONENTES_EQUIPE = "componentesEquipe";
	
	/**
	 * Description of the Field
	 */
	public final static String ID_EQUIPES_COMPONENTES = "equipamentosEspeciais.id";
	
	/**
	 * Description of the Field
	 */
	public final static String EQUIPAMENTOS_ESPECIAIS = "equipamentosEspeciais";
	
	/**
	 * Description of the Field
	 */
	public final static String VEICULO_PROPIO = "indicadorVeiculoProprio";	
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";	
	
	
	public FiltroServicoPerfilTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
