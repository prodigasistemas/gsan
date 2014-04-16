package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Mariana Victor
 * @created 18 de março de 2011
 */

public class FiltroCobrancaBoletimMedicao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaGrupo object
     */
    public FiltroCobrancaBoletimMedicao() {
    }

    /**
     * Constructor for the FiltroCobrancaGrupo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaBoletimMedicao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    public final static String COBRANCA_GRUPO_ID = "cobrancaGrupo.id";


}
