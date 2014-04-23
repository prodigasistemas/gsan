package gcom.cadastro.geografico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma municipio
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroMicrorregiao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroMicrorregiao object
     */
    public FiltroMicrorregiao() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMicrorregiao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";
    
    
    /**
     * Código da unidade de medição
     */
    public final static String DESCRICAO ="nome";

    /**
     * Nome do municipio
     */
    public final static String REGIAO_ID = "regiao.id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
