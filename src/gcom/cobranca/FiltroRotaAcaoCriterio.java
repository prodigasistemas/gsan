package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;



/**
 * Filtro utilizado na pesquisa de RotaAcaoCriterio 
 *
 * @author Pedro Alexandre
 * @date 26/04/2006
 */
public class FiltroRotaAcaoCriterio extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroRotaAcaoCriterio object
     */  
    public FiltroRotaAcaoCriterio() {
    }

    /**
     * Constructor for the FiltroRotaAcaoCriterio object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroRotaAcaoCriterio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ROTA_ID = "rota.id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ACAO_ID = "cobrancaAcao.id";
}
