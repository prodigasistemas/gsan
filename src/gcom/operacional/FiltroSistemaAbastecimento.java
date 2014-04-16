package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 30/08/2006
 */
public class FiltroSistemaAbastecimento extends Filtro {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroModulo object
	 */
	public FiltroSistemaAbastecimento() {
	}

	/**
	 * Constructor for the FiltroSistemaAbastecimento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroSistemaAbastecimento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Descricao do Sistema de Abastecimento
	 */
	public final static String DESCRICAO = "descricao";

	/**
	 * Descricao Abreviada do Sistema de Abastecimento
	 */
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";

	/**
	 * Indicador de Uso do Sistema de Abastecimento
	 */
	public final static String INDICADOR_USO = "indicadorUso";
	
	/**
	 * Id Fonte Captacao
	 */
	public final static String ID_FONTE_CAPTACAO = "fonteCaptacao.id";

}
