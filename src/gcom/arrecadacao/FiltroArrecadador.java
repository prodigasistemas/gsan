package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente endereco
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroArrecadador extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe FiltroClienteEndereco
	 */
	public FiltroArrecadador() {
	}

	/**
	 * Constructor for the FiltroCliente object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroArrecadador(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String INSCRICAO_ESTATAL = "numeroInscricaoEstadual";

	/**
	 * Description of the Field
	 */
	public final static String CLIENTE_ID = "cliente.id";

	/**
	 * Description of the Field
	 */
	public final static String LOCALIDADE_ID = "imovel.localidade.id";

	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	/**
	 * Description of the Field
	 */
	public final static String CODIGO_AGENTE = "codigoAgente";
	
	/**
	 * Description of the Field
	 */
	public final static String INDICADOR_USO = "indicadorUso";	
}
