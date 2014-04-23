package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * NegativacaoCriterio
 *
 * @author Yara Taciane
 * @date 15/05/2008
 */
public class FiltroNegativadorResultadoSimulacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativadorResultadoSimulacao() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativadorResultadoSimulacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String IMOVEL_ID = "imovel.id";
    


}
