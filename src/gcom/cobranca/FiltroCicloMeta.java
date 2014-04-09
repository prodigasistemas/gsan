package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Francisco do Nascimento
 * @created 23 de abril de 2009
 */

public class FiltroCicloMeta extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the FiltroCicloMeta object
	 */
	public FiltroCicloMeta() {
	}

	/**
	 * Constructor for the FiltroCicloMeta object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroCicloMeta(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Description of the Field
	 */
	public final static String ID = "id";

	/**
	 * Description of the Field
	 */
	public final static String COBRANCA_ACAO_ID = "cobrancaAcao.id";
	
	public final static String COBRANCA_ACAO = "cobrancaAcao";
	
	public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
	

}
