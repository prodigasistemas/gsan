package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroVersaoMobile extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor Default
	 */
	public FiltroVersaoMobile() {
	}
	/**
	 * Construtor passando order by
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroVersaoMobile(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	
	public final static String ID = "id";

	public final static String VERSAO_NUMERO = "numeroVersao";

}
