package gcom.util.tabelaauxiliar.unidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 31/07/2006
 */
public class FiltroTabelaAuxiliarUnidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroTabelaAuxiliarTipo
	 */
	public FiltroTabelaAuxiliarUnidade() {
	}

	/**
	 * Construtor da classe FiltroTabelaAuxiliarTipo
	 * 
	 * @param campoOrderBy
	 *            Descrição do parâmetro
	 */
	public FiltroTabelaAuxiliarUnidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
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
	public final static String DESCRICAOABREVIADA = "descricaoAbreviada";

	/**
	 * Description of the Field
	 */
	public final static String INDICADORUSO = "indicadorUso";

	/**
	 * Description of the Field
	 */
	public final static String MATERIAL_UNIDADE_ID = "materialUnidade.id";

}
