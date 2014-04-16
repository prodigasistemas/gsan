package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre
 * @created 08 de Fevereiro de 2006
 */

public class FiltroCobrancaGrupoCronogramaMes extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaGrupoCronogramaMes object
     */
    public FiltroCobrancaGrupoCronogramaMes() {
    }

    /**
     * Constructor for the FiltroCobrancaGrupoCronogramaMes object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaGrupoCronogramaMes(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    public final static String ID_COBRANCA_GRUPO = "cobrancaGrupo.id";
    
}
