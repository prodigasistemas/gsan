package gcom.arrecadacao.pagamento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de uma guia de pagamento
 * @author Pedro Alexandre
 * @created 09 de Janeiro de 2006
 */
public class FiltroGuiaPagamentoCategoria extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor for the FiltroGuiaPagamento object
	 */
	public FiltroGuiaPagamentoCategoria() {
	}

	/**
	 * Constructor for the FiltroGuiaPagamento object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroGuiaPagamentoCategoria(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";
	
}
