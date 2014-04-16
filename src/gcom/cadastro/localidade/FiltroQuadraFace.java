package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroQuadraFace extends Filtro implements Serializable{

	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroQuadraFace(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroQuadraFace() {}
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ID_QUADRA = "quadra.id";
}
