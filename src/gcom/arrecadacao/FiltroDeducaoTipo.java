package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Rhawi Dantas
 * @created 20 de fevereiro de 2006
 */

public class FiltroDeducaoTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroDeducaoTipo() {
    }

    public FiltroDeducaoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do Deducao Tipo
     */
    public final static String ID = "id";

}
