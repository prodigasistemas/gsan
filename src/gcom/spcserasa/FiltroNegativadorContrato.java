package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNegativador
 *
 * @author Yara Taciane de Souza
 * @date 19/12/2007
 */
public class FiltroNegativadorContrato extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativadorContrato() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativadorContrato(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String NUMERO_CONTRATO = "numeroContrato";
    
    public final static String NEGATIVADOR_ID = "negativador.id";
    


}
