package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Cria um filtro que será usado na pesquisa de pagamentos
 * 
 * [UC0255] Filtrar Pagamentos
 *
 * O filtro serve para armazenar os critérios de busca de uma conta par um cliente
 * 
 * FiltroClienteConta
 * 
 * @author Roberta Costa
 * @date 10/05/2006
 */
public class FiltroClienteConta extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroClienteConta object
	 */
	public FiltroClienteConta() {
	}

	/**
	 * Constructor for the FiltroClienteConta object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroClienteConta(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}


	public final static String ID = "id";

	public final static String CLIENTE_ID = "cliente.id";

	public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo";

	public final static String CONTA_ID = "conta";
	
	public final static String CONTA = "conta";
	
	public final static String CLIENTE = "cliente";
}
