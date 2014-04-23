package gcom.cadastro.geografico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma região
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroRegiao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroRegiao object
     */
    public FiltroRegiao() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroRegiao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String CODIGO = "codigo";
    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";
    
    /**
     * Código da unidade de medição
     */
    public final static String DESCRICAO = "nome";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
