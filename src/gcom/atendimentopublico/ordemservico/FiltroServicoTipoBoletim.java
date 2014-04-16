package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Vivianne Sousa
 * @created 07/01/2011
 */

public class FiltroServicoTipoBoletim extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroBairro object
     */
    public FiltroServicoTipoBoletim() {
    }

    /**
     * Constructor for the Filtro object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroServicoTipoBoletim(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do bairro
     */
    public final static String ID = "id";

}
