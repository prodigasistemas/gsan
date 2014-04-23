package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre
 * @created 07 de Fevereiro de 2006
 */

public class FiltroCobrancaCriterioLinha extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaCriterioLinha object
     */
    public FiltroCobrancaCriterioLinha() {
    }

    /**
     * Constructor for the FiltroCobrancaCriterioLinha object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaCriterioLinha(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_CRITERIO_ID = "cobrancaCriterio.id";
	
    
    public final static String ID_IMOVEL_PERFIL = "imovelPerfil.id";
    
    public final static String ID_CATEGORIA = "categoria.id";
    
    public final static String IMOVEL_PERFIL = "imovelPerfil";
    
    public final static String CATEGORIA = "categoria";
}
