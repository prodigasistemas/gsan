package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNegativador
 *
 * @author Ana Maria
 * @date 07/11/2007
 */
public class FiltroNegativador extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativador() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativador(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String NEGATIVADOR_CLIENTE = "cliente.id";
    
    public final static String CLIENTE = "cliente.nome";
    
    public final static String CODIGO_AGENTE = "codigoAgente";
    
    public final static String INSCRICAO_ESTADUAL = "numeroInscricaoEstadual";
    
    public final static String NEGATIVADOR_IMOVEL = "imovel.id";
    
    public final static String INDICADOR_USO = "indicadorUso";

}
