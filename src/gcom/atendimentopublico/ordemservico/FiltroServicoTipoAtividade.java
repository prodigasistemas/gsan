package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Perfil Tipo
 * @author Leonardo Regis
 * @since 25/07/2006
 *
 */
public class FiltroServicoTipoAtividade extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroServicoPerfilTipo object
	 */
	public FiltroServicoTipoAtividade() {
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
	public final static String INDICADOR_USO = "indicadorUso";	
	
	public final static String ATIVIDADE_INDICADOR_UNICA = "atividade.indicadorAtividadeUnica";
	
	public final static String ATIVIDADE_ID = "atividade.id";
	
	public final static String SERVICO_TIPO_ID = "servicoTipo.id";
	
	
	public FiltroServicoTipoAtividade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
