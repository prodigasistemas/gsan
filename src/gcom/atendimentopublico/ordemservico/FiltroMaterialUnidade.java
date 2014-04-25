package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 28/07/2006
 */
public class FiltroMaterialUnidade extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroModulo object
	 */
	public FiltroMaterialUnidade() {
	}

	/**
	 * Constructor for the FiltroModulo object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroMaterialUnidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Descricao do Material Unidade
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Descricao Abreviada do Material Unidade
	 */
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	
	public final static String INDICADOR_USO = "indicadorUso";

}
