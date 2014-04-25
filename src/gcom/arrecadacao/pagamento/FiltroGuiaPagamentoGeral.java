package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de uma guia de pagamento
 * @author Fernanda Paiva
 * @created 27 de Setembro de 2006
 */
public class FiltroGuiaPagamentoGeral extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroGuiaPagamento object
	 */
	public FiltroGuiaPagamentoGeral() {
	}

	/**
	 * Constructor for the FiltroGuiaPagamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroGuiaPagamentoGeral(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
		
}
