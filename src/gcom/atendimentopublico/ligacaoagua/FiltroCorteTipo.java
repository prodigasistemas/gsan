package gcom.atendimentopublico.ligacaoagua;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Tipo Corte
 * @author Rafael Pinto
 * @since 19/7/2006
  */
public class FiltroCorteTipo extends Filtro {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe FiltroOcupacaoTipo
	 */
	public FiltroCorteTipo() {
	}

	public final static String ID = "id";
	public final static String DESCRICAO = "descricao";
	public final static String INDICADOR_USO = "indicadorUso";
	public final static String INDICADOR_CORTE_ADMINISTRATIVO = "indicadorCorteAdministrativo";
	
	public FiltroCorteTipo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
}
