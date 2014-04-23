package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente imovel tipo
 * 
 * @author Sávio Luiz
 * @created 18 de Maio de 2004
 */

public class FiltroClienteRelacaoTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroClienteImovelTipo object
     */
    public FiltroClienteRelacaoTipo() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroClienteRelacaoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String CLIENTE_RELACAO_TIPO_ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
